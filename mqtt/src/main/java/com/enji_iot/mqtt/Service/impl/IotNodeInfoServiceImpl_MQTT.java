package com.enji_iot.mqtt.Service.impl;

import com.enji_iot.cache.Cache.CacheName;
import com.enji_iot.cache.Cache.ProCacheUtil;
import com.enji_iot.mqtt.DAO.MQTTMapper;
import com.enji_iot.mqtt.Service.IotNodeInfoService_MQTT;
import com.enji_iot.mqtt.MqttService;
import com.enji_iot.mqtt.protocol.ProtocalFactory;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Common.CodeIot;
import com.enji_iot.util.Entity.bo.IotNodeInfoBO;
import com.enji_iot.util.Entity.bo.IotSensorInfoBO;
import com.enji_iot.util.Util.ObjectUtil;
import com.enji_iot.util.Util.ResultMapUtils;
import com.enji_iot.util.Util.SpringApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service(value = " IotNodeInfoService_MQTT")
public class IotNodeInfoServiceImpl_MQTT implements IotNodeInfoService_MQTT {
//	@Resource
//	@Qualifier(value = "MQTTMapper")
//	private MQTTMapper mqttMapper;

	@Override
	public Map<String, Object> updateNodeStatus(IotNodeInfoBO obj) {
		Map<String,Object> resultMap = ResultMapUtils.getResultMap();
		try{
			IotNodeInfoBO nodeInfo = ProCacheUtil.getCache(CacheName.NODEINFO_DEVICECODE, obj.getDevice_code()  );
			if( ObjectUtil.isEmpty(nodeInfo) ){
				return resultMap ;
			}
			if(obj.getIot_node_status().equals(CodeIot.DEVICE_STATUS.ONLINE)){
				if( ObjectUtil.isNotEmpty(nodeInfo) ){
					if(ObjectUtil.isNotEmpty(obj.getLpmKey())){
						// 储存LPM 和 NodeId 添加session
						ProCacheUtil.addCache(CacheName.DEVICECODE_LPM, obj.getDevice_code(), obj.getLpmKey());						
					}
					// 设置设备在线
					nodeInfo.setIot_node_status(16);

					//TODO 新增mqtt指令
					ProtocalFactory.getInstance(nodeInfo.getIot_protocal_category()).node_OnLine_OffLine(obj,1);

				}else{
					ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.NO_DATA);
				}
			}
			else{
				ProCacheUtil.removeCache(CacheName.DEVICECODE_LPM, obj.getDevice_code());
				// 更新node所有的传感器为离线状态
				IotSensorInfoBO sensorBo = new IotSensorInfoBO();
				sensorBo.setDevice_code(obj.getDevice_code());
				sensorBo.setIot_sensor_status(CodeIot.DEVICE_STATUS.OFFLINE);
				//todo can't find IotSensorInfo
//				dao.update("IotSensorInfo.updateStatusByNode", sensorBo);
				// 设置设备缓存离线
				nodeInfo.setIot_node_status(17);

				//TODO 新增mqtt指令
				ProtocalFactory.getInstance(nodeInfo.getIot_protocal_category()).node_OnLine_OffLine(obj,0);
			}
//			Integer num = dao.update("IotNodeInfo.updateNodeStatus", obj);
			MQTTMapper mqttMapper = (MQTTMapper)SpringApplicationContext.getBean("MQTTMapper");
			Integer num = mqttMapper.updateNodeStatus(obj);
			if(num <=0){
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.NO_DATA);
			}
			//更新缓存
//			List<IotNodeInfoBO> iotNodeInfoLists = baseDao.selectList("IotNodeInfo.select", new IotNodeInfoBO());
			List<IotNodeInfoBO> iotNodeInfoLists = mqttMapper.selectList(new IotNodeInfoBO());
			for (IotNodeInfoBO nodei: iotNodeInfoLists) {
				ProCacheUtil.addCache(CacheName.NODEINFO,nodei.getId().toString(),nodei);
			}
			// 网关是设备，更新了
//			String scene_id = ProCacheUtil.getCache(CacheName.SCENE_IPDATE_FLAG ,nodeInfo.getScene_id()+"" );
//			if(ObjectUtil.isNotEmpty(scene_id)){
				MqttService.pubMessage( "1", "/scene/update/"+  nodeInfo.getScene_id() );				
//			}
		}catch (Exception e) {
			e.printStackTrace();
			ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.ERROR);
		}
		return resultMap;
	}

}
