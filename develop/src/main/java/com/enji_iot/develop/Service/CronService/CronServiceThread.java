package com.enji_iot.develop.Service.CronService;

import com.enji_iot.develop.DAO.IotNodeInfoMapper;
import com.enji_iot.develop.DAO.SystemMapper;
import com.enji_iot.util.Common.CodeIot;
import com.enji_iot.util.Entity.bean.TableSystem;
import com.enji_iot.util.Entity.bo.IotNodeInfoBO;
import com.enji_iot.util.Util.LogUtil;
import com.enji_iot.util.Util.ObjectUtil;
import com.enji_iot.util.Util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 
 * 定时器线程类
 * 
 *
 */
@Service("cronServiceThread")
public class CronServiceThread  {

	@Resource
	protected IotNodeInfoMapper iotNodeInfoMapper;
	@Resource
	protected SystemMapper systemMapper;
	
	/**
	 * 
	 * 设置系统中网关状态为离线
	 * 
	 *
	 */
	public class NodeStatusOffLine implements Runnable {
		public void run() {
			try {
				IotNodeInfoBO param = new IotNodeInfoBO();
				param.setIot_node_status(CodeIot.DEVICE_STATUS.OFFLINE);
//				baseDao.update("IotNodeInfo.updateAllOffLine", param);
				iotNodeInfoMapper.updateAllOffLine(param);

				// 设置传感器为离线状态
//				IotSensorInfoBO sensor = new IotSensorInfoBO();
//				sensor.setIot_sensor_status(CodeIot.DEVICE_STATUS.OFFLINE);
//				baseDao.update("IotSensorInfo.updateAllOffLine", sensor);
			} catch (Exception e) {
				LogUtil.errorLog(e);
			}
		}
	}
	
//	public class DeviceNodeOffLine implements Runnable {
//		@Override
//		public void run() {
//			try{
//
//				// 这边只要判断这个传感器的当前时间是否超过指定值即可
//				Cache cache = EhcacheUtil.getCache(CacheName.NODETRIGGERINFO) ;
//				List<String> keys = cache.getKeys() ;
//				// 丢失的Key
//				List<String> lostKey = new ArrayList<String>();
//				for(String key : keys){
//					List<IotTriggerInfoBO> objs = ProCacheUtil.getCache(CacheName.NODETRIGGERINFO,key) ;
//					if(ObjectUtil.isNotEmpty(objs)){
//						for(IotTriggerInfoBO tmp : objs){
//							IotSensorInfoBO sensor =  ProCacheUtil.getCache(CacheName.SENSORINFO, tmp.getFrom_sensor_id().toString() ) ;
//
//							// --
//							IotNodeInfoBO nodeInfo = ProCacheUtil.getCache(CacheName.NODEINFO, tmp.getNode_id().toString());
//
//							if(ObjectUtil.isEmpty(nodeInfo)){
//								lostKey.add(key);
//								continue ;
//							}
//
//							IotSceneInfoBO iotScneneInfo = ProCacheUtil.getCache(CacheName.SCENEINFO, nodeInfo.getScene_id().toString()) ;
//
//
//							if( ObjectUtil.isEmpty(sensor) || ObjectUtil.isEmpty(sensor.getMtime()) ){
//								continue ;
//							}
//
//							if(  tmp.getIot_trigger_status() != CodeIot.IOT_TRIGGER_STATUS.STOP &&
//									DateUtils.getMinusFromCurrent(sensor.getMtime(), Integer.parseInt(tmp.getCondition_params())*60) < 0 &&
//									tmp.getIs_worked()!=null && tmp.getIs_worked() &&
//									tmp.getRecovery() == 36 		// 报警恢复工作
//									){
//								// 恢复正常，发送恢复正常的信息
//								// 主要的就是发消息
//								// 控制设备就不需要了
//								tmp.setIs_worked(false);
//
//								String message = tmp.getName()+"恢复正常，设备（" + iotScneneInfo.getName() + "/"  +nodeInfo.getName()+"/"+
//										sensor.getName() + "） 已正常传输数据"
//										 + "，请及时关注。";
//
//								sensor.setScene_name(iotScneneInfo.getName());
//
//								// 搜集报警信息信息
//								BussinessTriggerBO bussinessMessgae = new BussinessTriggerBO() ;
//								bussinessMessgae.setSmsType(4);
//								bussinessMessgae.setVocieType(4);
//								// 报警信息
//								bussinessMessgae.setMessage(message);
//								// 短信
//								Map<String,String> aliyunSms = new HashMap<>();
//								aliyunSms.put("alarm_name", tmp.getName()) ;
//								aliyunSms.put("pro_name", iotScneneInfo.getName()) ;
//								aliyunSms.put("device", nodeInfo.getName()) ;
//								aliyunSms.put("sensor", sensor.getName()) ;
//								aliyunSms.put("time", DateUtils.format(DateUtils.simpleALL, new Date()) ) ;
//								bussinessMessgae.setAliyunSms(aliyunSms);
//
//								// 电话
//								Map<String,String> aliyunVoice = new HashMap<>();
//								aliyunVoice.put("pro_name", iotScneneInfo.getName()) ;
//								aliyunVoice.put("device", nodeInfo.getName()) ;
//								aliyunVoice.put("sensor", sensor.getName()) ;
//								bussinessMessgae.setAliyunSmsVoice(aliyunVoice);
//
//								IotTrigger.triggerWork(sensor, tmp, bussinessMessgae , baseDao , 2);
//								if (tmp.getIot_trigger_alarm_flag() != null
//										&& CodeIot.ALARM_FLAG.OPEN == tmp
//												.getIot_trigger_alarm_flag()) {
//									// 添加报警
//									IotTrigger.alarmWrok(sensor, tmp, message,baseDao , 2);
//								}
//							}
//
//							// 如果触发间隔为0 ，则为一次触发
//							if(tmp.getTrigger_inteval_time() < 1 ){
//								if(tmp.getIs_worked()!=null && tmp.getIs_worked() ){
//									if(DateUtils.getMinusFromCurrent(sensor.getMtime(), Integer.parseInt(tmp.getCondition_params())*60) < 0 ){
//										// 设备的数据正常在线
//										tmp.setIs_worked(false);
//										// 设备已经恢复正常
//									}
//									continue ;
//								}
//							}
//
//							if( tmp.getIot_trigger_status() == CodeIot.IOT_TRIGGER_STATUS.STOP
//									|| (! ObjectUtil.hasNull(tmp.getLast_trigger_time(),tmp.getTrigger_inteval_time())
//									&& DateUtils.getMinusFromCurrent(tmp.getLast_trigger_time(), tmp.getTrigger_inteval_time())<0 )){
//								continue ;
//							}
//
//							if( DateUtils.getMinusFromCurrent(sensor.getMtime(), Integer.parseInt(tmp.getCondition_params())*60   ) > 0 ){
//								// 有异常，要报警
//								String message = tmp.getName()+ ",设备（" + iotScneneInfo.getName() + "/"  +nodeInfo.getName()+"/"+
//										sensor.getName() + "） 已离线" + ( Integer.parseInt(tmp.getCondition_params()) >0 ?
//												"超过"+ tmp.getCondition_params()+"分钟":""  )
//										 + "，请及时处理。";
//
//								sensor.setScene_name(iotScneneInfo.getName());
//
//								// 搜集报警信息信息
//								BussinessTriggerBO bussinessMessgae = new BussinessTriggerBO() ;
//								bussinessMessgae.setSmsType(2);
//								bussinessMessgae.setVocieType(2);
//								// 报警信息
//								bussinessMessgae.setMessage(message);
//								// 短信
//								Map<String,String> aliyunSms = new HashMap<>();
//								aliyunSms.put("alarm_name", tmp.getName()) ;
//								aliyunSms.put("pro_name", iotScneneInfo.getName()) ;
//								aliyunSms.put("device", nodeInfo.getName()) ;
//								aliyunSms.put("sensor", sensor.getName()) ;
//								aliyunSms.put("time", DateUtils.format(DateUtils.simpleALL, new Date()) ) ;
//								aliyunSms.put("minute", tmp.getCondition_params());
//								bussinessMessgae.setAliyunSms(aliyunSms);
//
//								// 电话
//								Map<String,String> aliyunVoice = new HashMap<>();
//								aliyunVoice.put("pro_name", iotScneneInfo.getName()) ;
//								aliyunVoice.put("device", nodeInfo.getName()) ;
//								aliyunVoice.put("sensor", sensor.getName()) ;
//								bussinessMessgae.setAliyunSmsVoice(aliyunVoice);
//
//								tmp.setLast_trigger_time(new Date());
//
//								IotTrigger.triggerWork(sensor, tmp, bussinessMessgae , baseDao , 1);
//								if (tmp.getIot_trigger_alarm_flag() != null
//										&& CodeIot.ALARM_FLAG.OPEN == tmp.getIot_trigger_alarm_flag()) {
//									// 添加报警
//									IotTrigger.alarmWrok(sensor, tmp, message,baseDao , 1);
//								}
//								// 设置已经工作过
//								tmp.setIs_worked(true);
//							}else{
//								tmp.setIs_worked(false);
//							}
//						}
//					}
//				}
//				// 这边可以把不需要的删除
//				if(lostKey!=null && lostKey.size() >0){
//					for(String skey: lostKey){
//						ProCacheUtil.removeCache(CacheName.NODETRIGGERINFO, skey);
//					}
//				}
//			}catch(Exception e){
//				LogUtil.errorLog(e);
//			}
//		}
//	}
	
	/**
	 * 
	 * @authorM
	 *
	 */
	public class HistoryTableData implements Runnable {
		@Override
		public void run() {
			//
			TableSystem tmp = new TableSystem() ;
			String databasename = PropertiesUtil.getProperty("database.name")  ;
			if(ObjectUtil.isEmpty(databasename)){
				databasename ="easyiot" ;
			}
			tmp.setDb_name(databasename);
//			List<TableSystem> tableSystems = baseDao.selectList("System.selectHistoryInfo", tmp);
			List<TableSystem> tableSystems = systemMapper.selectHistoryInfo(tmp);

			if(ObjectUtil.isNotEmpty(tableSystems)){
				TableSystem tableSystem = new TableSystem();
				tableSystem.setTable_index(tableSystems.size() -1 );
//				Integer num = baseDao.selectCount("System.selectHistoryCount", tableSystem);
				Integer num = systemMapper.selectHistoryCount(tableSystem);
				if(num > 500 * 10000){
					int table_num = tableSystems.size()  ;
					String table_names = "" ;
					tableSystem = new TableSystem();
					tableSystem.setTable_index(table_num);
//					baseDao.update("System.createHistoryTable", tableSystem);
					systemMapper.createHistoryTable(tableSystem);

					if(table_num > 8){
						for(int o =table_num-7; o <= table_num ; o++ ){
							table_names+= ","+"iot_history_sensor_data_"+ o ;
						}
					}else{
						for(int o =1;o<= table_num ;o++){
							table_names+= ","+"iot_history_sensor_data_"+ o ;
						}						
					}
					tableSystem.setTable_names(table_names.substring(1));
//					baseDao.update("System.modifyHistoryTable", tableSystem);
					systemMapper.modifyHistoryTable(tableSystem);
				}
			}
		}
	}

}
