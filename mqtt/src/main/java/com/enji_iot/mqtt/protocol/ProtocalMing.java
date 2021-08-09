package com.enji_iot.mqtt.protocol;

import com.alibaba.fastjson.JSON;
import com.enji_iot.mqtt.MqttService;
import com.enji_iot.cache.Cache.CacheName;
import com.enji_iot.cache.Cache.ProCacheUtil;
import com.enji_iot.mqtt.Service.impl.IotNodeInfoServiceImpl_MQTT;
import com.enji_iot.mqtt.Service.impl.IotSensorInfoServiceImpl_MQTT;
import com.enji_iot.util.Common.CodeIot;
import com.enji_iot.util.Entity.bo.IotNodeInfoBO;
import com.enji_iot.util.Entity.bo.IotSensorInfoBO;
import com.enji_iot.util.Util.DateUtils;
import com.enji_iot.util.Util.ObjectUtil;
import com.enji_iot.util.Util.SpringApplicationContext;
import net.sf.json.JSONObject;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class ProtocalMing implements Iprotocal {

	@Override
	public void analysisData(String topic, byte[] data, String msg) throws InterruptedException, ParseException {
		// 这边可以保证主题都是 /dev/coo/device_id
		msg =msg.replaceAll("\r|\n", "");
//		List<IotSensorDeviceInfo> list = JSON.parseArray(msg, IotSensorDeviceInfo.class);
		String[] tmp = topic.split("/");
		String deviceCode = tmp[tmp.length -1];
		IotNodeInfoBO iotNodeInfo = ProCacheUtil.getCache(CacheName.NODEINFO_DEVICECODE, deviceCode);
		JSONObject jsonObject= JSONObject.fromObject(msg);
		jsonObject.put("time",DateUtils.formatSimple(new Date()));
		// 设备数据处理
		IotSensorInfoServiceImpl_MQTT app = (IotSensorInfoServiceImpl_MQTT) SpringApplicationContext.getBean("iotSensorInfoServiceImpl") ;
		app.updateRealTimeData(jsonObject,deviceCode);

		ProCacheUtil.addCache(CacheName.SENSOR_DEV_INFO,deviceCode,jsonObject);
		//心跳
		ProCacheUtil.addCache(CacheName.NODE_KEEPALIVE,deviceCode,System.currentTimeMillis());

		// 下发通知消息
		if(ObjectUtil.isNotEmpty(iotNodeInfo)){
			// 网关是设备，传感器更新
			String scene_id = ProCacheUtil.getCache(CacheName.SCENE_IPDATE_FLAG ,iotNodeInfo.getScene_id()+"" );
			if(ObjectUtil.isNotEmpty(scene_id)){
				//todo 刷新前端
//				MqttService.pubMessage( "1", "/scene/update/"+  iotNodeInfo.getScene_id() );
			}
		}
	}

	@Override
	public void handbert(String topic,String msg) {
		// TODO Auto-generated method stub
		msg =msg.replaceAll("\r|\n", "");
		List<IotSensorInfoBO> list = JSON.parseArray(msg, IotSensorInfoBO.class);
		String[] tmp = topic.split("/");
		String deviceCode = tmp[tmp.length -1];

		for(IotSensorInfoBO sensorInfo : list){
			// 心跳逻辑处理
			if(ObjectUtil.isNotEmpty(sensorInfo.getStr_sdata())){
				try{
					long sysMilliSecond = Long.parseLong(sensorInfo.getStr_sdata());
					long cache = ProCacheUtil.getCache(CacheName.NODE_KEEPALIVE, deviceCode);
					if(ObjectUtil.isNotEmpty(cache) && sysMilliSecond>cache){
						ProCacheUtil.addCache(CacheName.NODE_KEEPALIVE,deviceCode,sysMilliSecond);
						System.err.println(sysMilliSecond+"  毫秒");
					}
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void loginProtocal(Object obj) {
		// TODO Auto-generated method stub
		 IotNodeInfoServiceImpl_MQTT tmp = SpringApplicationContext.getBeanType("iotNodeInfoServerImpl") ;
		 tmp.updateNodeStatus(((IotNodeInfoBO) obj));
	}

	@Override
	public void logout(Object obj) {
		// TODO Auto-generated method stub
		 IotNodeInfoServiceImpl_MQTT tmp = SpringApplicationContext.getBeanType("iotNodeInfoServerImpl") ;
		 tmp.updateNodeStatus(((IotNodeInfoBO) obj));
	}

	@Override
	public Integer execServerControll(IotNodeInfoBO node) {
		if(node.getIot_node_status().equals(CodeIot.DEVICE_STATUS.ONLINE)){
			MqttService.pubMessage("{\""+node.getSensor_name()+"\":"+node.getVal()  +"}",
			"/server/coo/" + node.getDevice_code() );
			return 0 ;
		}else{
			return -1 ;
		}
	}

	@Override
	public Integer execServerParamWrite(IotSensorInfoBO sensor, IotNodeInfoBO node) {
		if(node.getIot_node_status() == CodeIot.DEVICE_STATUS.ONLINE+0){
			MqttService.pubMessage("{\"sensor_device_id\":"+ sensor.getSensor_device_id()
			+",\"port_id\":"+sensor.getPort_id()+",\"sdata\":"+sensor.getRequest_sdata()  +"}",
			"/server/coo/" + node.getDevice_code() );
			return 0 ;
		}else{
			return -1 ;
		}
	}

	@Override
	public Integer execServerParamRead(IotSensorInfoBO sensorInfo, IotNodeInfoBO node) {
		if(node.getIot_node_status() == CodeIot.DEVICE_STATUS.ONLINE+0){
			MqttService.pubMessage("{\"sensor_device_id\":"+ sensorInfo.getSensor_device_id()
					+",\"port_id\":"+sensorInfo.getPort_id()  +"}",
				"/server/coo/" + node.getDevice_code() );
			return 0;
		}else{
			return -1 ;
		}
	}

	@Override
	public void node_OnLine_OffLine(IotNodeInfoBO node, Integer flag) {
		MqttService.pubMessage("{\"sdata\":"+flag+"}",
				"/server/coo/" + node.getDevice_code() );
	}

	@Override
	public void add_Video_Live(Integer id) {
		MqttService.pubMessage("{\"sdata\":"+id+"}",
				"/server/live/" + id );
	}
}
