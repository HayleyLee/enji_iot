package com.enji_iot.develop.Service.trigger;

import com.alibaba.fastjson.JSON;
import com.enji_iot.cache.Cache.CacheName;
import com.enji_iot.cache.Cache.ProCacheUtil;
import com.enji_iot.develop.DAO.*;
import com.enji_iot.develop.Service.base.MailService;
import com.enji_iot.mqtt.iot.ProtocolUtil;
import com.enji_iot.util.Common.CodeIot;
import com.enji_iot.util.Common.Constants;
import com.enji_iot.util.Config.ProConfig;
import com.enji_iot.util.Entity.bo.*;
import com.enji_iot.util.Util.*;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.WxMpTemplateMessage;
import net.sf.json.JSONObject;
import org.springframework.core.task.TaskExecutor;

import java.util.*;

public class IotTrigger {
	/**
	 * 触发器工作
	 * @param obj
	 * @param dao 
	 */
	public static Integer trigger(JSONObject obj, String devCode,String nodeId){
		try{
			// 根据 IotSensorInfoBO 获取触发器列表
			//String sensorDevicePort = obj.getNode_id()+"-"+ obj.getSensor_device_id()+"-"+obj.getPort_id();
			// 获取触发器缓存
			ArrayList<IotTriggerInfoBO> iotTrigger = ProCacheUtil.getCache(CacheName.NODETRIGGERINFO, nodeId);
			int exception_flag = 0 ;
			//trigger 不能为空
			if(ObjectUtil.isNotEmpty(iotTrigger) && ObjectUtil.isNotEmpty(obj)){
				//System.err.println("nodeId："+nodeId);
				//System.err.println("devCode："+devCode);
				//遍历obj得到传感数据的key和value
				for (Object o : obj.entrySet()) {
					Map.Entry nodeSensor = (Map.Entry) o;
					if (!nodeSensor.getKey().toString().equals("time")) {
						//遍历并判断这个传感点是否有触发器？
						for( IotTriggerInfoBO trigger : iotTrigger ){
							if (Integer.parseInt(nodeId) == trigger.getNode_id()
									&& nodeSensor.getKey().toString().equals(trigger.getIot_sensor_name())){
								System.err.println("这个传感数据有触发器！");

								boolean trigger_flag = false;
								String message ="" ;
								// 搜集报警信息信息
								BussinessTriggerBO bussinessMessgae = new BussinessTriggerBO() ;

								// 判断触发器是否为开启状态，如果为关闭状态，继续循环下一个触发器
								// 判断是否超过了触发时间间隔，如果没有超过触发时间间隔，继续循环下一个触发器
								if(trigger.getIot_trigger_status().equals(CodeIot.IOT_TRIGGER_STATUS.STOP)
										|| (! ObjectUtil.hasNull(trigger.getLast_trigger_time(),trigger.getTrigger_inteval_time())
										&& DateUtils.getMinusFromCurrent(trigger.getLast_trigger_time(), trigger.getTrigger_inteval_time())<0 )){
									continue ;
								}
								// 触发器条件类型值
								Integer triggerType = trigger.getIot_trigger_condition_type() ;
								// 触发条件参数
								String[] conditions = trigger.getCondition_params().split(",");
								//TODO 获取转换好的值
								//String measure = getConvertValue(nodeSensor.getValue().toString(),obj.getMeasure_unit_type());
								String measure = "";
								//从缓存里得到该传感器的单位
								IotNodeInfoBO node = ProCacheUtil.getCache(CacheName.NODEINFO_DEVICECODE, devCode);
								IotSceneInfoBO scene = ProCacheUtil.getCache(CacheName.SCENEINFO, node.getScene_id().toString());
								IotNodeUnitDataBO nodeUnit = ProCacheUtil.getCache(CacheName.IOT_NODE_UNIT,node.getScene_id()+devCode+nodeSensor.getKey().toString());
								if (ObjectUtil.isNotEmpty(nodeUnit) && nodeUnit.getSensor_name().equals(nodeSensor.getKey().toString())) {
									//赋好单位的数据
									measure = nodeUnit.getUnit();
								}
								//判断触发条件类型，如果触发条件中有带时间的触发器类型
								//TODO 带有时间的触发器
								if( triggerType.equals( CodeIot.TRIGGER_CONDITION_TYPE.OVERTOPX_OVERTIME )
										||  triggerType.equals( CodeIot.TRIGGER_CONDITION_TYPE.UNDERY_OVERTIME ) ){
									// 如果有缓存，判断是否超过了触发间隔，如果超过触发间隔就触发，否则不处理
									IotTriggerInfoBO cacheTrigger = ProCacheUtil.timeTirggerListCache.get(trigger.getId());
									if( triggerType.equals( CodeIot.TRIGGER_CONDITION_TYPE.OVERTOPX_OVERTIME )  ){
										// 数值超过{M}分钟高于{X}
										if (Double.parseDouble(nodeSensor.getValue().toString()) > Double.parseDouble(conditions[0]) ) {
											if (decideTrigger(trigger, cacheTrigger, conditions)) {
												trigger_flag = true;
												// 触发动作,并添加触发历史数据
												message =  trigger.getName()+ ",设备传感点（" + scene.getName()
														+ "/"  +node.getName()+"/"
														+ nodeSensor.getKey().toString() + "），当前为" + Double.parseDouble(nodeSensor.getValue().toString())+ " "+measure
														+ " ,高于" + conditions[0] +" "+ measure + "，超过"
														+ Integer.valueOf(conditions[1])  + "分钟，请及时处理。";
												// 报警信息
												bussinessMessgae.setSmsType(1);
												bussinessMessgae.setVocieType(1);
												bussinessMessgae.setMessage(message);
												// 短信
												Map<String,String> aliyunSms = new HashMap<>();
												aliyunSms.put("alarm_name", trigger.getName()) ;
												aliyunSms.put("pro_name",  scene.getName()) ;
												aliyunSms.put("device", node.getName()) ;
												aliyunSms.put("sensor", nodeSensor.getKey().toString()) ;
												aliyunSms.put("time", DateUtils.format(DateUtils.simpleALL, new Date()) ) ;
												aliyunSms.put("value", nodeSensor.getValue().toString()+ " "+measure) ;
												aliyunSms.put("alarm_condition", " 高于" + conditions[0] +" "+ measure + "，超过"
														+ Integer.valueOf(conditions[1])  + "分钟") ;
												bussinessMessgae.setAliyunSms(aliyunSms);
												// 电话
												Map<String,String> aliyunVoice = new HashMap<>();
												aliyunVoice.put("pro_name",  scene.getName()) ;
												aliyunVoice.put("device", node.getName()) ;
												aliyunVoice.put("sensor", nodeSensor.getKey().toString()) ;
												bussinessMessgae.setAliyunSmsVoice(aliyunVoice);
												// 处理缓存
												ProCacheUtil.timeTirggerListCache.remove(trigger.getId());
											}else{
												continue ;
											}
										} else {
											ProCacheUtil.timeTirggerListCache.remove(trigger.getId());
										}
									}
									// 数值超过{M}分钟低于{Y}
									else {
										if (Double.parseDouble(nodeSensor.getValue().toString()) < Double.parseDouble(conditions[0]) ) {
											if (decideTrigger(trigger, cacheTrigger, conditions)) {
												trigger_flag = true;
												// 触发动作,并添加触发历史数据
												message = trigger.getName()+ ",传感器（" + scene.getName() + "/"  +node.getName()+"/"+
														nodeSensor.getKey().toString() + "），当前为" + nodeSensor.getValue().toString()+" "+ measure
														+ "低于" + conditions[0] +" "+ measure +"，超过"
														+ Integer.valueOf(conditions[1])  + "分钟，请及时处理。";
												// 处理缓存
												ProCacheUtil.timeTirggerListCache.remove(trigger.getId());
												// 报警信息
												bussinessMessgae.setSmsType(1);
												bussinessMessgae.setVocieType(1);
												bussinessMessgae.setMessage(message);
												// 短信
												Map<String,String> aliyunSms = new HashMap<>();
												aliyunSms.put("alarm_name", trigger.getName()) ;
												aliyunSms.put("pro_name",  scene.getName()) ;
												aliyunSms.put("device", node.getName()) ;
												aliyunSms.put("sensor", nodeSensor.getKey().toString()) ;
												aliyunSms.put("time", DateUtils.format(DateUtils.simpleALL, new Date()) ) ;
												aliyunSms.put("value", nodeSensor.getValue().toString()+ " "+measure) ;
												aliyunSms.put("alarm_condition", " 低于" + conditions[0] +" "+ measure + "，超过"
														+ Integer.valueOf(conditions[1])  + "分钟") ;
												bussinessMessgae.setAliyunSms(aliyunSms);
												// 电话
												Map<String,String> aliyunVoice = new HashMap<>();
												aliyunVoice.put("pro_name",  scene.getName()) ;
												aliyunVoice.put("device", node.getName()) ;
												aliyunVoice.put("sensor", nodeSensor.getKey().toString()) ;
												bussinessMessgae.setAliyunSmsVoice(aliyunVoice);
											}else{
												continue ;
											}
										} else {
											ProCacheUtil.timeTirggerListCache.remove(trigger.getId());
										}
									}
								}
								// 没有时间积累型的
								//TODO 没有时间积累型的触发器
								else{
									// 数值高于{X}
									if( triggerType.equals( CodeIot.TRIGGER_CONDITION_TYPE.OVERTOPX )  ){
										if (Double.parseDouble(nodeSensor.getValue().toString()) > Double.parseDouble(conditions[0]) ) {
											trigger_flag = true;
											// 触发动作,并添加触发历史数据
											message = trigger.getName()+ ",传感器（" +scene.getName() + "/"  +node.getName()+"/"+
													nodeSensor.getKey().toString()  + "），当前为" + nodeSensor.getValue().toString()+" "+measure
													+ " ,高于" + conditions[0] +" "+ measure + "，请及时处理。";
											// 报警信息
											bussinessMessgae.setSmsType(1);
											bussinessMessgae.setVocieType(1);
											bussinessMessgae.setMessage(message);
											// 短信
											Map<String,String> aliyunSms = new HashMap<>();
											aliyunSms.put("alarm_name", trigger.getName()) ;
											aliyunSms.put("pro_name",  scene.getName()) ;
											aliyunSms.put("device", node.getName()) ;
											aliyunSms.put("sensor", nodeSensor.getKey().toString()) ;
											aliyunSms.put("time", DateUtils.format(DateUtils.simpleALL, new Date()) ) ;
											aliyunSms.put("value", nodeSensor.getValue().toString()+ " "+measure) ;
											aliyunSms.put("alarm_condition", " 高于" + conditions[0] +" "+ measure) ;
											bussinessMessgae.setAliyunSms(aliyunSms);
											// 电话
											Map<String,String> aliyunVoice = new HashMap<>();
											aliyunVoice.put("pro_name",  scene.getName()) ;
											aliyunVoice.put("device", node.getName()) ;
											aliyunVoice.put("sensor",  nodeSensor.getKey().toString()) ;
											bussinessMessgae.setAliyunSmsVoice(aliyunVoice);
										}
									}
									//  数值低于{Y}
									else if(triggerType.equals( CodeIot.TRIGGER_CONDITION_TYPE.UNDERY ) ){
										if (Double.parseDouble(nodeSensor.getValue().toString()) < Double.parseDouble(conditions[0]) ) {
											trigger_flag = true;
											// 触发动作,并添加触发历史数据
											message = trigger.getName()+ "传感器（" + scene.getName() + "/"  +node.getName()+"/"+
													nodeSensor.getKey().toString() + "），当前为" + nodeSensor.getValue().toString()+" "+measure
													+" 低于" + conditions[0] +" "+ measure + "，请及时处理。";
											// 报警信息
											bussinessMessgae.setSmsType(1);
											bussinessMessgae.setVocieType(1);
											bussinessMessgae.setMessage(message);
											// 短信
											Map<String,String> aliyunSms = new HashMap<>();
											aliyunSms.put("alarm_name", trigger.getName()) ;
											aliyunSms.put("pro_name",  scene.getName()) ;
											aliyunSms.put("device", node.getName()) ;
											aliyunSms.put("sensor", nodeSensor.getKey().toString()) ;
											aliyunSms.put("time", DateUtils.format(DateUtils.simpleALL, new Date()) ) ;
											aliyunSms.put("value", nodeSensor.getValue().toString()+ " "+measure) ;
											aliyunSms.put("alarm_condition", " 低于" + conditions[0] +" "+ measure) ;
											bussinessMessgae.setAliyunSms(aliyunSms);
											// 电话
											Map<String,String> aliyunVoice = new HashMap<>();
											aliyunVoice.put("pro_name",  scene.getName()) ;
											aliyunVoice.put("device", node.getName()) ;
											aliyunVoice.put("sensor", nodeSensor.getKey().toString()) ;
											bussinessMessgae.setAliyunSmsVoice(aliyunVoice);
										}
									}
									// 数值在{X}和{Y}之间
									else if(triggerType.equals( CodeIot.TRIGGER_CONDITION_TYPE.XY_OVERMIDDLE )){
										if (Double.parseDouble(nodeSensor.getValue().toString()) > Double.parseDouble(conditions[0])
												&& Double.parseDouble(nodeSensor.getValue().toString()) < Double.parseDouble(conditions[1]) ) {
											trigger_flag = true;
											// 触发动作,并添加触发历史数据
											message =trigger.getName()+ ",传感器（" +  scene.getName() + "/"  +node.getName()+"/"
													+ nodeSensor.getKey().toString() + "），当前为" + nodeSensor.getValue().toString()+" "+ measure
													+ " ,在" + conditions[0] + "," + conditions[1] +" "+ measure+" 之间，请及时处理。";
											// 报警信息
											bussinessMessgae.setSmsType(1);
											bussinessMessgae.setVocieType(1);
											bussinessMessgae.setMessage(message);
											// 短信
											Map<String,String> aliyunSms = new HashMap<>();
											aliyunSms.put("alarm_name", trigger.getName()) ;
											aliyunSms.put("pro_name",  scene.getName()) ;
											aliyunSms.put("device", node.getName()) ;
											aliyunSms.put("sensor", nodeSensor.getKey().toString()) ;
											aliyunSms.put("time", DateUtils.format(DateUtils.simpleALL, new Date()) ) ;
											aliyunSms.put("value", nodeSensor.getValue().toString()+ " "+measure) ;
											aliyunSms.put("alarm_condition", " 在" + conditions[0] + "," + conditions[1] +" "+ measure) ;
											bussinessMessgae.setAliyunSms(aliyunSms);
											// 电话
											Map<String,String> aliyunVoice = new HashMap<>();
											aliyunVoice.put("pro_name",  scene.getName()) ;
											aliyunVoice.put("device", node.getName()) ;
											aliyunVoice.put("sensor", nodeSensor.getKey().toString()) ;
											bussinessMessgae.setAliyunSmsVoice(aliyunVoice);
										}
									}
									// 数值={X}
									else if(triggerType.equals( CodeIot.TRIGGER_CONDITION_TYPE.EQUAL )){
										if (Double.parseDouble(nodeSensor.getValue().toString()) +0f == Double.parseDouble(conditions[0])+0 ) {
											trigger_flag = true;
											// 触发动作,并添加触发历史数据
											message = trigger.getName()+ ",传感器（" +  scene.getName() + "/"  +node.getName()+"/"
													+ nodeSensor.getKey().toString() + "），当前为" + nodeSensor.getValue().toString() + " "+measure
													+ "  等于" + conditions[0]+" "+measure +"，请及时处理。";
											// 报警信息
											bussinessMessgae.setSmsType(1);
											bussinessMessgae.setVocieType(1);
											bussinessMessgae.setMessage(message);
											// 短信
											Map<String,String> aliyunSms = new HashMap<>();
											aliyunSms.put("alarm_name", trigger.getName()) ;
											aliyunSms.put("pro_name",  scene.getName()) ;
											aliyunSms.put("device", node.getName()) ;
											aliyunSms.put("sensor", nodeSensor.getKey().toString()) ;
											aliyunSms.put("time", DateUtils.format(DateUtils.simpleALL, new Date()) ) ;
											aliyunSms.put("value", nodeSensor.getValue().toString()+ " "+measure) ;
											aliyunSms.put("alarm_condition", " 等于" + conditions[0]+" "+measure) ;
											bussinessMessgae.setAliyunSms(aliyunSms);
											// 电话
											Map<String,String> aliyunVoice = new HashMap<>();
											aliyunVoice.put("pro_name",  scene.getName()) ;
											aliyunVoice.put("device", node.getName()) ;
											aliyunVoice.put("sensor", nodeSensor.getKey().toString()) ;
											bussinessMessgae.setAliyunSmsVoice(aliyunVoice);
										}
									}
									// 数据异常
									else if(triggerType.equals( CodeIot.TRIGGER_CONDITION_TYPE.EXCEPTION_DATA_SOLVE )){
										if (Double.parseDouble(nodeSensor.getValue().toString()) < Double.parseDouble(conditions[0])
												|| Double.parseDouble(nodeSensor.getValue().toString()) > Double.parseDouble(conditions[1]) ) {
											trigger_flag = true;
											exception_flag = 1 ;
											// 数据异常，需要过滤
											message =trigger.getName()+ ",传感器（" +  scene.getName() + "/"  +node.getName()+"/"
													+ nodeSensor.getKey().toString() + "），当前为" + nodeSensor.getValue().toString()+" "+ measure
													+ " ,在" + conditions[0] + "," + conditions[1] +" "+ measure+"区间之外，请及时处理。";
											// 报警信息
											bussinessMessgae.setSmsType(1);
											bussinessMessgae.setVocieType(1);
											bussinessMessgae.setMessage(message);
											// 短信
											Map<String,String> aliyunSms = new HashMap<>();
											aliyunSms.put("alarm_name", trigger.getName()) ;
											aliyunSms.put("pro_name",  scene.getName()) ;
											aliyunSms.put("device", node.getName()) ;
											aliyunSms.put("sensor", nodeSensor.getKey().toString()) ;
											aliyunSms.put("time", DateUtils.format(DateUtils.simpleALL, new Date()) ) ;
											aliyunSms.put("value", nodeSensor.getValue().toString()+ " "+measure) ;
											aliyunSms.put("alarm_condition", " 在" + conditions[0] + "," + conditions[1] +" "+ measure+"区间之外") ;
											bussinessMessgae.setAliyunSms(aliyunSms);
											// 电话
											Map<String,String> aliyunVoice = new HashMap<>();
											aliyunVoice.put("pro_name",  scene.getName()) ;
											aliyunVoice.put("device", node.getName()) ;
											aliyunVoice.put("sensor", nodeSensor.getKey().toString()) ;
											bussinessMessgae.setAliyunSmsVoice(aliyunVoice);
										}
									}
									else{
										continue ;
									}
								}

								if (trigger_flag) {
									System.err.println("触发器准备工作。。。"+trigger.getIs_worked()+"--"+trigger.getTrigger_inteval_time() );
									// 这边对于一次性触发的处理（没有开启报警恢复的状态下）
									if( trigger.getTrigger_inteval_time()!= null && trigger.getTrigger_inteval_time() < 1  ){
										// 单次触发
										if( trigger.getIs_worked()!= null && trigger.getIs_worked() ){
											continue ;
										}
									}
									// 已经触发，更新触发器的缓存
									trigger.setIs_worked(true);
									trigger.setLast_trigger_time(new Date());
									// 触发
									triggerWork(nodeSensor,nodeSensor.getKey().toString(),nodeSensor.getValue().toString(),measure,scene,node, trigger, bussinessMessgae,1);
									// 判断是否需要报警
									if (trigger.getIot_trigger_alarm_flag() != null
											&& CodeIot.ALARM_FLAG.OPEN.equals(trigger.getIot_trigger_alarm_flag())) {
										// 添加报警
										alarmWrok(nodeSensor.getKey().toString(),nodeSensor.getValue().toString(), trigger, message, 1);
									}
								}
								else{
									// 这边处理，报警恢复
									if(trigger.getIs_worked() != null && trigger.getIs_worked() &&
											trigger.getRecovery() == 36){
										// 恢复正常，发送恢复正常的信息
										// 主要的就是发消息
										// 控制设备就不需要了
										IotNodeInfoBO nodeInfo = ProCacheUtil.getCache(CacheName.NODEINFO, trigger.getNode_id().toString());
										message = trigger.getName()  +"恢复正常，设备（" + scene.getName() + "/"  +nodeInfo.getName()+"/"+
												nodeSensor.getKey().toString()+ "）已恢复到正常范围，当前数值为  "+ nodeSensor.getValue().toString()+" "+ measure
												+ "，请及时关注。";
										// 报警信息
										bussinessMessgae.setSmsType(3);
										bussinessMessgae.setVocieType(3);
										bussinessMessgae.setMessage(message);
										// 短信
										Map<String,String> aliyunSms = new HashMap<>();
										aliyunSms.put("alarm_name", trigger.getName()) ;
										aliyunSms.put("pro_name",  scene.getName()) ;
										aliyunSms.put("device", node.getName()) ;
										aliyunSms.put("sensor", nodeSensor.getKey().toString()) ;
										aliyunSms.put("time", DateUtils.format(DateUtils.simpleALL, new Date()) ) ;
										aliyunSms.put("value", nodeSensor.getValue().toString()+ " "+measure) ;
										bussinessMessgae.setAliyunSms(aliyunSms);
										// 电话
										Map<String,String> aliyunVoice = new HashMap<>();
										aliyunVoice.put("pro_name",  scene.getName()) ;
										aliyunVoice.put("device", node.getName()) ;
										aliyunVoice.put("sensor", nodeSensor.getKey().toString()) ;
										bussinessMessgae.setAliyunSmsVoice(aliyunVoice);
										triggerWork(nodeSensor,nodeSensor.getKey().toString(),nodeSensor.getValue().toString(),measure,scene,node, trigger, bussinessMessgae, 2);

										if (trigger.getIot_trigger_alarm_flag() != null
												&& CodeIot.ALARM_FLAG.OPEN.equals(trigger.getIot_trigger_alarm_flag())) {
											// 添加报警
											alarmWrok(nodeSensor.getKey().toString(),nodeSensor.getValue().toString(), trigger, message, 2);

										}
									}
									trigger.setIs_worked(false);
								}
								// 数据过滤退出
								if(exception_flag ==1 ){
									return -1 ;
								}
							}
						}
					}
				}
			}
		}catch(Exception e){
			LogUtil.errorLog(e);
			return -1 ;
		}
		return 0;
	}
	
	/**
	 *  添加报警信息
	 * @param trigger
	 * @param message
	 */
	public static void alarmWrok(String sensorName,String sensorVal, IotTriggerInfoBO trigger, String message, Integer type) {
		try {
			System.err.println("报警器开始工作");
			String[] conditions = trigger.getCondition_params().split(",");
			IotAlarmInfoBO alarm = new IotAlarmInfoBO();
			alarm.setNode_id(trigger.getNode_id());
			alarm.setIot_trigger_alarm_level(trigger.getIot_trigger_alarm_level());
			alarm.setDescription(message);
			if(type == 1){
				alarm.setName(trigger.getName()+"报警");
			}else if( type == 2){
				alarm.setName(trigger.getName()+"恢复");
			}
			alarm.setAlarm_sdata(Float.parseFloat(conditions[0]));
			alarm.setSdata(Float.parseFloat(sensorVal));
			alarm.setIot_alarm_process_status(CodeIot.PROCESS_STATUS.NO);
			IotAlarmInfoMapper iotAlarmInfoMapper = (IotAlarmInfoMapper)SpringApplicationContext.getBean("IotAlarmInfoMapper");
			iotAlarmInfoMapper.insert(alarm);
		} catch (Exception e) {
			LogUtil.errorLog(e);
		}
	}

	/**
	 * 获取转换好的值
	 */
	public static String getConvertValue(double sdata,Integer code_measure){
		try{
			com.enji_iot.util.Entity.bean.ProDictionaryInfo obj = ProCacheUtil.getCache(CacheName.DICTIONARY, code_measure.toString(),
												new com.enji_iot.util.Entity.bean.ProDictionaryInfo());
			if(ObjectUtil.isNotEmpty(obj.getValue())){
				String[] value = obj.getValue().split(",");
				if( Double.parseDouble(value[0].split(":")[0]) == sdata ){
					return value[0].split(":")[1];
				}else if(Double.parseDouble(value[1].split(":")[0]) == sdata){
					return value[1].split(":")[1];
				}				
			}else{
				return obj.getName();
			}
		}catch(Exception e){
			LogUtil.errorLog(e);
		}
		return "" ;
	}

	/**
	 * 判断缓存里的触发器时间是否超过了触发间隔，如果超过触发间隔就触发
	 * @param trigger 触发器列表里的某个触发器
	 * @param cacheTrigger 缓存里的触发器
	 * @param conditions 触发条件参数
	 */
	private static Boolean  decideTrigger(IotTriggerInfoBO trigger, IotTriggerInfoBO cacheTrigger, String[] conditions) {
		if (ObjectUtil.isNotEmpty(cacheTrigger) ) {
			if (DateUtils.getMinusFromCurrent(cacheTrigger.getAtime(),
					Integer.valueOf(conditions[conditions.length - 1])*60 ) >= 0) {
				return true;
			}
		} else {
			trigger.setAtime(new Date());
			ProCacheUtil.timeTirggerListCache.put(trigger.getId(), trigger);
		}
		return false;
	}
	
	
	/**
	 * 事务处理
	 * 按照每个不同的触发动作类型分别发送警报、并将此次警报消息作为历史触发消息存入sql
	 * @param trigger
	 * @return
	 */
	public static void triggerWork(Map.Entry nodeSensor,String sensorName,String sensorVal,String measure,
								   IotSceneInfoBO scene,IotNodeInfoBO node, IotTriggerInfoBO trigger,
								   BussinessTriggerBO message, Integer type) {
		try {
			// 触发详情c
			StringBuilder triggerInfo = new StringBuilder() ;
			System.err.println("触发器开始工作");
			String[] triggerActionType = trigger.getIot_trigger_action_type().split(",");
			for (int i = 0; i < triggerActionType.length; i++) {
				//如果是控制设备
				if ((CodeIot.ACTION_TYPE.CONTROL_DEVICE + "").equals(triggerActionType[i])) {
					triggerInfo.append( sendDevice(sensorName,sensorVal, trigger, message, type ))	;
				}
				//如果是微信通知
				else if ((CodeIot.ACTION_TYPE.MESSAGE_WECHAT + "").equals(triggerActionType[i])) {
					triggerInfo.append( sendWechat(nodeSensor,scene,node,measure, trigger, message, type ));
				}
				//如果是短信通知
				else if ((CodeIot.ACTION_TYPE.MESSAGE_SMS + "").equals(triggerActionType[i])) {
					triggerInfo.append( sendSms(sensorName,sensorVal,trigger, message));
				}
				//如果是邮件通知
				else if ((CodeIot.ACTION_TYPE.MESSAGE_MAIL + "").equals(triggerActionType[i])) {
					triggerInfo.append( sendEmal(sensorName,sensorVal,trigger, message, type));
				}
				//如果是语音控制
				else if ((CodeIot.ACTION_TYPE.MESSAGE_VOICE + "").equals(triggerActionType[i])) {
					triggerInfo.append( sendSmsVoice(sensorName,sensorVal, trigger, message));
				}
			}
			
			if (trigger.getIot_trigger_alarm_flag() != null
					&& CodeIot.ALARM_FLAG.OPEN.equals(trigger.getIot_trigger_alarm_flag())) {
				if(type == 1){
					triggerInfo.append("产生系统报警消息;");					
				}else if(type == 2){
					triggerInfo.append("产生设备报警恢复消息;");		
				}
			}
			
			String[] conditions = trigger.getCondition_params().split(",");
			
			// 插入历史记录中
			IotHistoryTriggerInfoBO history = new IotHistoryTriggerInfoBO();
			history.setVal(Float.parseFloat(nodeSensor.getValue().toString()));
			history.setIot_sensor_name(nodeSensor.getKey().toString());
			history.setNode_id(node.getId());

			history.setDescription(triggerInfo.toString());
			
			history.setName(trigger.getName());
			// 插入触发数值
			history.setTrigger_value(conditions[0]);
			history.setIot_trigger_condition_type(trigger.getIot_trigger_condition_type());
			history.setIot_trigger_action_type(trigger.getIot_trigger_action_type());
			history.setAction_params(trigger.getAction_params());
			history.setCondition_params(trigger.getCondition_params());
			history.setIot_trigger_alarm_level(trigger.getIot_trigger_alarm_level());
			history.setIot_trigger_alarm_flag(trigger.getIot_trigger_alarm_flag());
			history.setTrigger_inteval_time(trigger.getTrigger_inteval_time());
			IotHistoryTriggerInfoMapper iotHistoryTriggerInfoMapper = (IotHistoryTriggerInfoMapper)SpringApplicationContext.getBean("IotHistoryTriggerInfoMapper");
			iotHistoryTriggerInfoMapper.insert(history);
		} catch (Exception e) {
			LogUtil.errorLog(e);
		}
	}
	/**
	 * 设备开关控制
	 * @return 当前设备的状态：打开/关闭
	 */
	public static String sendDevice(String sensorName,String sensorVal, IotTriggerInfoBO trigger, BussinessTriggerBO message,Integer flag ) {
		
		if( ObjectUtil.isEmpty(trigger.getAction_params()) ){
			return "" ;
		}
		
		// 将string 转json
		ContactorBO contactor = JSON.parseObject(trigger.getAction_params(), ContactorBO.class);
		
		String info = "" ;
		
		// 控制设备传感器的信息
		IotNodeInfoBO controlDev = ProCacheUtil.getCache(CacheName.NODEINFO_DEVICECODE, contactor.getControl_device() );
		controlDev.setSensor_name(sensorName);
		controlDev.setVal(Integer.parseInt(sensorVal));

		info = "控制"+controlDev.getName() +"设备、传感器"+controlDev.getName() +"为";
//		ProDictionaryInfoBO bo =  ProCacheUtil.getCache(CacheName.DICTIONARY, controlSensor.getMeasure_unit_type().toString()) ;
//		String[] controlValue =  bo.getValue().split(",") ; 
	
		// 转发 控制传感器 to_sensor_id , action_param
//		IotSensorInfoBO sensor = new IotSensorInfoBO(controlSensor.getId());
		if( flag == 1 ){
			// 正常触发
			if(contactor.getControl_device_status().equals(CodeIot.ALARM_FLAG.OPEN.toString()) ){
				info += "打开";
				controlDev.setVal(1);
			}else{
				info += "关闭";
				controlDev.setVal(0);
			}
		}else if(flag == 2){
			// 恢复触发
			if( ! contactor.getControl_device_status().equals(CodeIot.ALARM_FLAG.OPEN.toString()) ){
				info += "打开";
				controlDev.setVal(1);
			}else{
				info += "关闭";
				controlDev.setVal(0);
			}
		}
		
//		dao.update("IotSensorInfo.update", sensor);
		
		// 发送消息,控制设备
		ProtocolUtil.sendControlSensorCommand(controlDev);
		return info+"状态；" ;
	}
	
	/**
	 * 拨打电话给某人
	 * @param trigger
	 * @param message
	 * @return
	 */
	public static String sendSmsVoice(String sensorName,String sensorVal, IotTriggerInfoBO trigger, final BussinessTriggerBO message){
		// 根据action_param 获取联系人id，然后拨打给某人
		if( ObjectUtil.isEmpty(trigger.getAction_params()) ){
			return "" ;
		}
		
		final Integer add_user_id = trigger.getAid() ;
		if( ObjectUtil.isEmpty(add_user_id) ){
			return "语音报警触发器没有归属用户；" ;
		}
		final UserAccountInfoBO userAccount = ProCacheUtil.getCache(CacheName.USERACCOUNT_ID, add_user_id.toString()) ;
		if( ObjectUtil.isNotEmpty(userAccount) && userAccount.getVoice_num() >0 ){
			// 可以发短信
			userAccount.setVoice_num( userAccount.getVoice_num() -1 );
			// 减少用户 短信数量，插入发短信信息
			TaskExecutor taskExecutor = (TaskExecutor) SpringApplicationContext.getBean("taskExecutor");
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					// 减少用户短信数量
					UserAccountInfoBO userAccountBo = new UserAccountInfoBO();
					userAccountBo.setId(userAccount.getId());
					UserAccountInfoMapper userAccountInfoMapper = (UserAccountInfoMapper) SpringApplicationContext.getBean("UserAccountInfoMapper");
					userAccountInfoMapper.updateVoiceNum(userAccountBo);

					// 增加用户触发
					AlarmTriggerRecordBO alarmTriggerRecord = new AlarmTriggerRecordBO() ;
					alarmTriggerRecord.setUser_id(add_user_id);
					alarmTriggerRecord.setTrigger_type(CodeIot.ACTION_TYPE.MESSAGE_VOICE);
					alarmTriggerRecord.setContent(message.getMessage());
					alarmTriggerRecord.setAtime(new Date());
					AlarmTriggerRecordMapper alarmTriggerRecordMapper = (AlarmTriggerRecordMapper)SpringApplicationContext.getBean("AlarmTriggerRecordMapper");
					alarmTriggerRecordMapper.insert(alarmTriggerRecord);
				}
			});
		}else{
			return "语音报警触发，用户没有余额；" ;
		}
		
		// 将string 转json
		ContactorBO contactor = JSON.parseObject(trigger.getAction_params(), ContactorBO.class);
		String[] contact_user_ids = contactor.getContactor().split(",") ;
		String name = "" ;
		for(int i=0 ; i< contact_user_ids.length ; i++ ){
			ContactUserInfoBO contactUserInfo = new ContactUserInfoBO();
			contactUserInfo.setId(Integer.parseInt(contact_user_ids[i]) );
			ContactUserInfoMapper contactUserInfoMapper = (ContactUserInfoMapper)SpringApplicationContext.getBean("ContactUserInfoMapper");
			contactUserInfo = contactUserInfoMapper.selectOne(contactUserInfo);
			if (ObjectUtil.isNotEmpty(contactUserInfo) && ObjectUtil.isNotEmpty(contactUserInfo.getPhone())) {
				name+= ","+contactUserInfo.getName() ;
				
				AliyunParamBO aliyunParamBo = new AliyunParamBO() ;
				aliyunParamBo.setPhonenumber(contactUserInfo.getPhone());
				if( message.getSmsType() == 1 ){
					aliyunParamBo.setTemplateCode(ProConfig.AliyunShortMessage.VOICE_TEMPLATE1);
				}else if( message.getSmsType() == 2 ){
					aliyunParamBo.setTemplateCode(ProConfig.AliyunShortMessage.VOICE_TEMPLATE2);
				}else if( message.getSmsType() == 3 ){
					aliyunParamBo.setTemplateCode(ProConfig.AliyunShortMessage.VOICE_TEMPLATE3);
				}else if( message.getSmsType() == 4 ){
					aliyunParamBo.setTemplateCode(ProConfig.AliyunShortMessage.VOICE_TEMPLATE4);
				}
				aliyunParamBo.setCalledShowNumber( ProConfig.AliyunShortMessage.CALLEDSHOWNUMBER );
				aliyunParamBo.setTemplateParam( JSON.toJSONString(message.getAliyunSmsVoice()) );
				AliyunSmsAndVoiceUtil.sendSmsVoice(aliyunParamBo);
			}
		}
		if("".equals(name)){
			return "" ;
		}
		return "拨打电话给"+ name.substring(1) +"；" ;
	}

	/**
	 * 发送短信通知
	 */
	public static String sendSms(String sensorName,String sensorVal, IotTriggerInfoBO trigger, final BussinessTriggerBO message) {
		// 根据action_param 获取联系人id，然后发送端短信给某人
		
		if( ObjectUtil.isEmpty(trigger.getAction_params()) ){
			return "" ;
		}
		// 将string 转json
		ContactorBO contactor = JSON.parseObject(trigger.getAction_params(), ContactorBO.class);
		String[] contact_user_ids = contactor.getContactor().split(",") ;
		String name = "" ; 
		// ---
		final Integer add_user_id = trigger.getAid() ;
		if( ObjectUtil.isEmpty(add_user_id) ){
			return "短信报警触发器没有归属用户；" ;
		}
		final UserAccountInfoBO userAccount = ProCacheUtil.getCache(CacheName.USERACCOUNT_ID, add_user_id.toString()) ;
		if( ObjectUtil.isNotEmpty(userAccount) && userAccount.getSms_num() >0 ){
			// 可以发短信
			userAccount.setSms_num( userAccount.getSms_num()-1 );
			// 减少用户 短信数量，插入发短信信息
			TaskExecutor taskExecutor = (TaskExecutor) SpringApplicationContext.getBean("taskExecutor");
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					// 减少用户短信数量
					UserAccountInfoBO userAccountBo = new UserAccountInfoBO();
					userAccountBo.setId(userAccount.getId());
					UserAccountInfoMapper userAccountInfoMapper = (UserAccountInfoMapper)SpringApplicationContext.getBean("UserAccountInfoMapper");
					userAccountInfoMapper.updateSMS_num(userAccountBo);

					// 增加用户触发
					AlarmTriggerRecordBO alarmTriggerRecord = new AlarmTriggerRecordBO() ;
					alarmTriggerRecord.setUser_id(add_user_id);
					alarmTriggerRecord.setTrigger_type(CodeIot.ACTION_TYPE.MESSAGE_SMS);
					alarmTriggerRecord.setContent(message.getMessage());
					alarmTriggerRecord.setAtime(new Date());
					AlarmTriggerRecordMapper alarmTriggerRecordMapper = (AlarmTriggerRecordMapper)SpringApplicationContext.getBean("AlarmTriggerRecordMapper");
					alarmTriggerRecordMapper.insert(alarmTriggerRecord);
				}
			});
		}else{
			return "短信报警触发，用户没有余额；" ;
		}
				
		for(int i=0 ; i< contact_user_ids.length ; i++ ){
			ContactUserInfoBO contactUserInfo = new ContactUserInfoBO();
			contactUserInfo.setId(Integer.parseInt(contact_user_ids[i]) );
			ContactUserInfoMapper contactUserInfoMapper = (ContactUserInfoMapper)SpringApplicationContext.getBean("ContactUserInfoMapper");
			contactUserInfo = contactUserInfoMapper.selectOne(contactUserInfo);
			if (ObjectUtil.isNotEmpty(contactUserInfo) && ObjectUtil.isNotEmpty(contactUserInfo.getPhone())) {
				name+= ","+contactUserInfo.getName() ;
				// 这边通过spring 获取短信的实体
				
				AliyunParamBO aliyunParamBo = new AliyunParamBO() ;
				aliyunParamBo.setPhonenumber(contactUserInfo.getPhone());
				if( message.getSmsType() == 1 ){
					aliyunParamBo.setTemplateCode(ProConfig.AliyunShortMessage.SMS_TEMPCODE1);
				}else if( message.getSmsType() == 2 ){
					aliyunParamBo.setTemplateCode(ProConfig.AliyunShortMessage.SMS_TEMPCODE2);
				}else if( message.getSmsType() == 3 ){
					aliyunParamBo.setTemplateCode(ProConfig.AliyunShortMessage.SMS_TEMPCODE3);
				}else if( message.getSmsType() == 4 ){
					aliyunParamBo.setTemplateCode(ProConfig.AliyunShortMessage.SMS_TEMPCODE4);
				}
				aliyunParamBo.setSignaName( ProConfig.AliyunShortMessage.SIGNATURE );
				aliyunParamBo.setTemplateParam( JSON.toJSONString(message.getAliyunSms()) );
				AliyunSmsAndVoiceUtil.sendSms(aliyunParamBo);
				
				// 云片网
//				ShortMessageService shortMessageService = (ShortMessageService) SpringApplicationContext.getBean("shortMessageServiceImpl");
//				shortMessageService.sendSms(message.getMessage() , contactUserInfo.getPhone());					
			}
		}
		if("".equals(name)){
			return "" ;
		}
		return "发送短信给"+ name.substring(1) +"；" ;
	}
	
	/**
	 * 发送微信通知
	 * 
	 * @param nodeSensor
	 * @param trigger
	 * @return
	 */
	public static String  sendWechat(Map.Entry nodeSensor, IotSceneInfoBO scene,IotNodeInfoBO node, String measure,
									 IotTriggerInfoBO trigger, BussinessTriggerBO message,Integer flag) {
		if( ObjectUtil.isEmpty(trigger.getAction_params()) ){
			return "" ;
		}
		// 将string 转json
		ContactorBO contactor = JSON.parseObject(trigger.getAction_params(), ContactorBO.class);
		String[] contact_user_ids = contactor.getContactor().split(",") ;
		
		String name = "" ;
		for(int i=0 ; i< contact_user_ids.length ; i++ ){
			ContactUserInfoBO contactUserInfo = new ContactUserInfoBO();
			contactUserInfo.setId(Integer.parseInt(contact_user_ids[i]) );
			ContactUserInfoMapper contactUserInfoMapper = (ContactUserInfoMapper)SpringApplicationContext.getBean("ContactUserInfoMapper");
			contactUserInfo = contactUserInfoMapper.selectOne(contactUserInfo);
			if (ObjectUtil.isNotEmpty(contactUserInfo) && ObjectUtil.isNotEmpty(contactUserInfo.getWx_key() )) {
				name += "," +contactUserInfo.getName() ;
				// 给当前联系人发送微信通知
				// 传感器缓存
//				IotSensorInfoBO sensorInfo = ProCacheUtil.getCache(CacheName.SENSORINFO_NSP, param.getNode_id()+"-"+
//												param.getSensor_device_id()+"-"+param.getPort_id());
				// 报警信息
				node.getName() ;
				// 当前传感器数值  
				nodeSensor.getValue() ;
				// 场景名称
				scene.getId();
				scene.getName() ;
//				String measure = getConvertValue(param.getSdata(),param.getMeasure_unit_type()); ; // 单位
				String measures = nodeSensor.getValue().toString()+measure; ; // 单位
				// 时间
				new Date();
				nodeSensor.getKey();
				
				WxMpService wxMpserver = (WxMpService) SpringApplicationContext.getBean("wxMpService");
				
				WxMpTemplateMessage wxTemplate = new WxMpTemplateMessage();
				wxTemplate.setUrl(ProConfig.LOCAL_DOMAIN+"/service/wiot/alarm" );
				
				if(flag == 1){
					wxTemplate.setDatas(Arrays.asList( new WxMpTemplateData(Constants.WeiXinTemplate.FIRST, "您有一条新的报警消息，请及时处理！"),
							new WxMpTemplateData(Constants.WeiXinTemplate.KEYWORD1, scene.getName()+"/"+node.getName() +"/"+nodeSensor.getKey().toString(),"#03a9f4" ),
							new WxMpTemplateData(Constants.WeiXinTemplate.KEYWORD2,  "微信消息" ),
							new WxMpTemplateData(Constants.WeiXinTemplate.KEYWORD3,  DateUtils.format(DateUtils.simpleALL, new Date()) ),
							new WxMpTemplateData(Constants.WeiXinTemplate.KEYWORD4,  message.getMessage() ,"#e62112" ),
							new WxMpTemplateData(Constants.WeiXinTemplate.REMARK,  "点击查看详情" )));
				}else if(flag == 2){
					wxTemplate.setDatas(Arrays.asList( new WxMpTemplateData(Constants.WeiXinTemplate.FIRST, "您有一条新的设备报警恢复消息，请及时关注！"),
							new WxMpTemplateData(Constants.WeiXinTemplate.KEYWORD1, scene.getName()+"/"+node.getName() +"/"+nodeSensor.getKey().toString(),"#03a9f4" ),
							new WxMpTemplateData(Constants.WeiXinTemplate.KEYWORD2,  "微信消息" ),
							new WxMpTemplateData(Constants.WeiXinTemplate.KEYWORD3,  DateUtils.format(DateUtils.simpleALL, new Date()) ),
							new WxMpTemplateData(Constants.WeiXinTemplate.KEYWORD4,  message.getMessage() ,"#e62112" ),
							new WxMpTemplateData(Constants.WeiXinTemplate.REMARK,  "点击查看详情" )));
				}
				
				wxTemplate.setToUser(contactUserInfo.getWx_key() );
				wxTemplate.setTemplateId(ProConfig.WEIXIN.NOTICE_1);
				try {
					wxMpserver.templateSend(wxTemplate);
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
		}
		if("".equals(name)){
			return "" ;
		}
		return "发送微信消息给" + name.substring(1) +"；" ;
	}
	
	/**
	 * 发送邮件通知
	 * 
	 * @param sensorName
	 * @param sensorVal
	 * @param trigger
	 * @return
	 */
	public static String sendEmal(String sensorName,String sensorVal, IotTriggerInfoBO trigger, BussinessTriggerBO message,Integer flag) {

		IotNodeInfoBO nodeinfo = ProCacheUtil.getCache(CacheName.NODEINFO, trigger.getNode_id().toString() );
		nodeinfo.setSensor_name(sensorName);
		nodeinfo.setVal(Integer.parseInt(sensorVal));
		if( ObjectUtil.isEmpty(trigger.getAction_params()) ){
			return "" ;
		}
		// 将string 转json
		ContactorBO contactor = JSON.parseObject(trigger.getAction_params(), ContactorBO.class);
		String[] contact_user_ids = contactor.getContactor().split(",") ;
		String name = "" ;

		for(int i=0 ; i < contact_user_ids.length ; i++ ){
			ContactUserInfoBO contactUserInfo = new ContactUserInfoBO();
			contactUserInfo.setId(Integer.parseInt(contact_user_ids[i]));
			ContactUserInfoMapper contactUserInfoMapper = (ContactUserInfoMapper)SpringApplicationContext.getBean("ContactUserInfoMapper");
			contactUserInfo = contactUserInfoMapper.selectOne(contactUserInfo);
			System.err.println("用户信息："+contactUserInfo.toString());
			if (ObjectUtil.isNotEmpty(contactUserInfo) && ObjectUtil.isNotEmpty(contactUserInfo.getEmail())) {
				// 发邮件
				name += ","+contactUserInfo.getName();
				Map<String, Object> mail = new HashMap<String, Object>();
				mail.put("name", contactUserInfo.getName());
				mail.put("email", contactUserInfo.getEmail());
				mail.put("message", message.getMessage() );
				MailService mailService = (MailService) SpringApplicationContext.getBean("mailServiceImpl");
				if(flag == 1){
					mailService.send(contactUserInfo.getEmail(),  trigger.getName() + "触发提醒邮件",
							"tpl/vm/iot_trigger_notice.vm", mail);
					// 发送消息到设备
					ProtocolUtil.sendControlSensorCommand(nodeinfo);
				}else if(flag == 2){
					mailService.send(contactUserInfo.getEmail(),  trigger.getName() + "设备报警恢复提醒邮件",
							"tpl/vm/iot_trigger_notice.vm", mail);
					ProtocolUtil.sendControlSensorCommand(nodeinfo);
				}
			}
		}
		if("".equals(name)){
			return "" ;
		}
		return "发送邮件给"+name.substring(1)+"；" ;
	}
	
}
