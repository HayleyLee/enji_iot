package com.enji_iot.mqtt.Service.impl;

import com.enji_iot.cache.Cache.CacheName;
import com.enji_iot.cache.Cache.ProCacheUtil;
import com.enji_iot.mqtt.DAO.MQTTMapper;
import com.enji_iot.mqtt.Service.IotSensorInfoService_MQTT;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Common.CodeIot;
import com.enji_iot.util.Entity.bo.IotHistoryNodeDataBO;
import com.enji_iot.util.Entity.bo.IotNodeInfoBO;
import com.enji_iot.util.Entity.bo.IotNodeUnitDataBO;
import com.enji_iot.util.Entity.bo.IotSceneInfoBO;
import com.enji_iot.util.Util.DateUtils;
import com.enji_iot.util.Util.ObjectUtil;
import com.enji_iot.util.Util.ResultMapUtils;
import com.enji_iot.util.Util.SpringApplicationContext;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import static com.enji_iot.util.Util.DateUtils.formatSimple;

@Service(value = "IotSensorInfoService_MQTT")
public class IotSensorInfoServiceImpl_MQTT implements IotSensorInfoService_MQTT {
//	@Resource
//	@Qualifier(value = "MQTTMapper")
//	private MQTTMapper mqttMapper;

	@Override
	public Map<String, Object> updateRealTimeData(JSONObject obj, String devCode) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try{
			// 通过网关缓存 deviceCode -> nodeInfo -> id (or nodeId)
			IotNodeInfoBO iotNodeInfo = ProCacheUtil.getCache(CacheName.NODEINFO_DEVICECODE, devCode);
			JSONObject nodeData = ProCacheUtil.getCache(CacheName.SENSOR_DEV_INFO, devCode);
			// 网关没有项目信息
			IotSceneInfoBO sceneBo = ProCacheUtil.getCache(CacheName.SCENEINFO, iotNodeInfo.getScene_id().toString() );
			if(ObjectUtil.isEmpty(sceneBo)){
				System.err.println("sceneBo is Null");
				return resultMap;
			}
//			String lpk = ProCacheUtil.getCache(CacheName.DEVICECODE_LPM, devCode);
			if(ObjectUtil.isEmpty(iotNodeInfo)){
				// 这个设备已经离线了；返回错误，让用户重新登录；
				ResultMapUtils.putStatusCode(resultMap, CodeIot.DEVICE_STATUS.OFFLINE);
				// 服务端发送重新登录的命令
				return resultMap;
			}
//			obj.setNode_name(iotNodeInfo.getName());
//			obj.setNode_id( iotNodeInfo.getId() );
			// 获取传感器缓存信息
//			IotSensorInfoBO sensorInfo = ProCacheUtil.getCache(CacheName.SENSORINFO_NSP, obj.getNode_id()+"-"+obj.getSensor_device_id()+"-"+obj.getPort_id(), obj);
			
//			if( ObjectUtil.isEmpty(sensorInfo) ){
//				// 不存在的数据
//				return resultMap;
//			}
			
			// 这边判断如果是经纬度的传感器信息，则需要更新网关信息的经纬度数据
//			if( iotNodeInfo.getIot_node_data_type()!=null && iotNodeInfo.getIot_node_data_type() == CodeIot.SENSOR_TYPE.POSITION ){
//				iotNodeInfo.setLonLat( obj.getStr_sdata() );
//			}
			
			// 公式转化 - 有公式则将数据通过公式转换一下
//			if(ObjectUtil.isNotEmpty(iotNodeInfo.getFormula_up()) && iotNodeInfo.getFormula_up().contains("x")  ){
//				obj.setSdata( (float) Calculator.conversion( iotNodeInfo.getFormula_up().replaceAll("x", obj.getSdata()+"")));
//			}
			// 小数位处理
//			if(  sensorInfo.getSdata_degree()==null ){
//				sensorInfo.setSdata_degree(2);
//			}
//			if( ObjectUtil.isEmpty( obj.getStr_sdata()) ){
//				float ft = obj.getSdata() ;
//				BigDecimal bd  = new BigDecimal((double)ft);
//				bd   =  bd.setScale(sensorInfo.getSdata_degree() ,4);
//				ft = bd.floatValue() ;
//				obj.setSdata(ft);
//				obj.setRequest_sdata(obj.getSdata());
//			}
			
			//*** 20190405 如果是配置数据，则直接更新数据库，并更新缓存，数据不进入历史表里面
//			if( sensorInfo.getData_type() == 1 ){
//				// 配置数据
//				Integer num = dao.update("IotSensorInfo.updateRealTimeData", obj);
//				if( num >0){
//					IotSensorInfoBO sensor = ProCacheUtil.getCache(CacheName.SENSORINFO, sensorInfo.getId().toString() );
//					sensor.setStr_sdata( obj.getStr_sdata() );
//					return resultMap ;
//				}
//			}
			
			// 上传一些无用的传感器信息
			if( ObjectUtil.isEmpty(iotNodeInfo)){
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.NO_DATA);
				System.err.println("no data");
				return resultMap;
			}
			if (iotNodeInfo.getDisable_flag()!=1){
				/**
				 * 触发器处理
				 */
				IotTrigger.trigger(obj,devCode,iotNodeInfo.getId().toString());
				/**
				 * TODO
				 * 添加历史数据
				 */
				if(ObjectUtil.isNotEmpty(obj)){
					ArrayList<String> newDataStr = new ArrayList<>();
					ArrayList<Float> newDataInt = new ArrayList<>();
					ArrayList<String> oldDataStr = new ArrayList<>();
					ArrayList<Float> oldDataInt = new ArrayList<>();

					Iterator iter = obj.entrySet().iterator();
					Iterator iter2 = nodeData.entrySet().iterator();
					while (iter.hasNext()) {
						Map.Entry entry = (Map.Entry) iter.next();
						if (!entry.getKey().toString().equals("time")){
							newDataStr.add(entry.getKey().toString());
							newDataInt.add(Float.parseFloat(entry.getValue().toString()));
						}
					}
					while (iter2.hasNext()){
						Map.Entry entry2 = (Map.Entry) iter2.next();
						if (!entry2.getKey().toString().equals("time")) {
							oldDataStr.add(entry2.getKey().toString());
							oldDataInt.add(Float.parseFloat(entry2.getValue().toString()));
						}
					}
					if (sensorDataStratety(oldDataStr,oldDataInt,newDataInt,iotNodeInfo,devCode)){
						for (int i = 0; i < newDataInt.size(); i++) {
							//从缓存里得到该传感器的单位
							IotNodeUnitDataBO nodeUnit = ProCacheUtil.getCache(CacheName.IOT_NODE_UNIT,iotNodeInfo.getScene_id()+devCode+newDataStr.get(i));
							if (ObjectUtil.isEmpty(nodeUnit)) continue;
							IotHistoryNodeDataBO iotHistoryNodeDataBO = new IotHistoryNodeDataBO();
							iotHistoryNodeDataBO.setScene_id(iotNodeInfo.getScene_id());
							iotHistoryNodeDataBO.setDevice_code(iotNodeInfo.getDevice_code());
							iotHistoryNodeDataBO.setName(newDataStr.get(i));
							iotHistoryNodeDataBO.setVal(Float.parseFloat(newDataInt.get(i).toString()));
							iotHistoryNodeDataBO.setUnit(nodeUnit.getUnit());
							iotHistoryNodeDataBO.setTime(formatSimple(new Date()));
//							dao.insert("IotHistoryNodeData.insert", iotHistoryNodeDataBO);
							MQTTMapper mqttMapper = (MQTTMapper) SpringApplicationContext.getBean("MQTTMapper");
							mqttMapper.insertIotHistoryNodeData(iotHistoryNodeDataBO);
						}
					}
				}
			}
		}catch(Exception e){
			System.err.println("异常了");
			e.printStackTrace();
			ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.ERROR);
		}
		return resultMap;
	}

	/**
	 * 传感器数据策略
	 */

	private Boolean sensorDataStratety(ArrayList<String> oldDataStr , ArrayList<Float> oldDataInt ,
									   ArrayList<Float> newDataInt, IotNodeInfoBO node,String devCode ) throws ParseException {
		if( ObjectUtil.isEmpty(node.getStoreTime() ) ){
			node.setStoreTime(new Date());
		}
		boolean flag = false ;
		if(node.getStore_strage()== null || node.getStore_strage() == 271 ){
			// 变化储存
			for (int i = 0; i < oldDataInt.size(); i++) {
				for (int j = 0; j < newDataInt.size(); j++) {
					if (!oldDataInt.get(i).equals(newDataInt.get(j))){
						//从缓存里得到该传感器的单位
						IotNodeUnitDataBO nodeUnit = ProCacheUtil.getCache(CacheName.IOT_NODE_UNIT,node.getScene_id()+devCode+oldDataStr.get(j));
						//开始存储
						IotHistoryNodeDataBO iotHistoryNodeDataBO = new IotHistoryNodeDataBO();
						iotHistoryNodeDataBO.setScene_id(node.getScene_id());
						iotHistoryNodeDataBO.setDevice_code(node.getDevice_code());
						iotHistoryNodeDataBO.setName(oldDataStr.get(j));
						iotHistoryNodeDataBO.setVal(Float.parseFloat(newDataInt.get(j).toString()));
						iotHistoryNodeDataBO.setUnit(nodeUnit.getUnit());
						iotHistoryNodeDataBO.setTime(formatSimple(new Date()));
//						dao.insert("IotHistoryNodeData.insert", iotHistoryNodeDataBO);
						MQTTMapper mqttMapper = (MQTTMapper)SpringApplicationContext.getBean("MQTTMapper");
						mqttMapper.insertIotHistoryNodeData(iotHistoryNodeDataBO);
					}
					i++;
					if (i > oldDataInt.size()){
						break;
					}
				}
			}
			flag = false;
		}else if( node.getStore_strage() == 272 ){
			// 实时储存
			flag = true ;
		}else if( node.getStore_strage() == 273 ){
			// 30s
			if( DateUtils.getMinusFromCurrent(node.getStoreTime(), 30) >= 0){
				flag = true ;
			}
		}else if( node.getStore_strage() == 274 ){
			// 1min
			if( DateUtils.getMinusFromCurrent(node.getStoreTime(), 60) >= 0){
				flag = true ;
			}
		}else if( node.getStore_strage() == 275 ){
			// 5min
			if( DateUtils.getMinusFromCurrent(node.getStoreTime(), 5*60) >= 0){
				flag = true ;
			}
		}else if( node.getStore_strage() == 276 ){
			// 10min
			if( DateUtils.getMinusFromCurrent(node.getStoreTime(), 10*60) >= 0){
				flag = true ;
			}
		}else if( node.getStore_strage() == 277 ){
			// 30min
			if( DateUtils.getMinusFromCurrent(node.getStoreTime(), 30*60) >= 0){
				flag = true ;
			}
		}else if( node.getStore_strage() == 278 ){
			// 1h
			if( DateUtils.getMinusFromCurrent(node.getStoreTime(), 60 * 60) >= 0){
				flag = true ;
			}
		}else if( node.getStore_strage() == 279 ){
			// 1d
			if( DateUtils.getMinusFromCurrent(node.getStoreTime(), 24 * 60) >= 0){
				flag = true ;
			}
		}
		if(flag){
			node.setStoreTime(new Date());
		}
		return flag ;
	}

}
