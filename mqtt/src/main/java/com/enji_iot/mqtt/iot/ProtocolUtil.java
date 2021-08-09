package com.enji_iot.mqtt.iot;

import com.enji_iot.cache.Cache.CacheName;
import com.enji_iot.cache.Cache.ProCacheUtil;
import com.enji_iot.util.Common.CodeIot;
import com.enji_iot.util.Entity.bean.IotLpmInfo;
import com.enji_iot.util.Entity.bean.IotSensorInfo;
import com.enji_iot.util.Entity.bo.IotNodeInfoBO;
import com.enji_iot.util.Entity.bo.IotSensorInfoBO;
import com.enji_iot.mqtt.MqttService;
import com.enji_iot.mqtt.protocol.ProtocalFactory;
import com.enji_iot.util.Util.Calculator;
import com.enji_iot.util.Util.ObjectUtil;


/**
 * 协议
 * @author chenrj
 *
 */
public class ProtocolUtil {
	
	/**
	 * 开关控制
	 * @param obj
	 * @return
	 */
	public static Integer sendControlSensorCommand(IotNodeInfoBO obj ){
		//IOT_SERVER_LPM:TYPE,deviceCode,SENSOR_DEVICE_ID,PORT_ID,DATA,FORMULATE
		StringBuffer strBuffer = new StringBuffer();

		IotNodeInfoBO iotNodeInfo = ProCacheUtil.getCache(CacheName.NODEINFO, obj.getId().toString() );
		iotNodeInfo.setSensor_name(obj.getSensor_name());
		iotNodeInfo.setVal(obj.getVal());
//		if(ObjectUtil.isNotEmpty(sensorInfo.getFormula_down()) && sensorInfo.getFormula_down().contains("x") ){
//			obj.setRequest_sdata( (float)
//					Calculator.conversion(sensorInfo.getFormula_down().replaceAll("x", obj.getRequest_sdata()+""))  );
//		}
		//如果是mqtt协议
		if(iotNodeInfo.getIot_node_type().equals(CodeIot.IOT_NODE_STATUS.MQTT)){
//			obj.setSensor_device_id(sensorInfo.getSensor_device_id());
//			obj.setPort_id(sensorInfo.getPort_id());
			return ProtocalFactory.getInstance(iotNodeInfo.getIot_protocal_category()).execServerControll(iotNodeInfo);
			
		}else if(iotNodeInfo.getIot_node_type().equals(CodeIot.IOT_NODE_STATUS.TCP)){
			strBuffer.append("IOT_SERVER_LPM:control,");
			
		}else if(iotNodeInfo.getIot_node_type().equals(CodeIot.IOT_NODE_STATUS.HTTP)){
			// 直接更新
			return 0 ;
		}else if(iotNodeInfo.getIot_node_type().equals(CodeIot.IOT_NODE_STATUS.UDP)){
			strBuffer.append("IOT_SERVER_LPM:udp_control,");
		}else{
			// 异常
			return -1 ;
		}
		
//		strBuffer.append( iotNodeInfo.getDevice_code()+"," );
//		strBuffer.append( sensorInfo.getSensor_device_id() +"," );
//		strBuffer.append( sensorInfo.getPort_id() +"," );
//		strBuffer.append( obj.getRequest_sdata() +"," );
//		if( ObjectUtil.isNotEmpty( sensorInfo.getInfos() )  ){
//			strBuffer.append(sensorInfo.getInfos() ) ;
//		}else{
//			strBuffer.append("{}" ) ;
//		}

		if(ObjectUtil.isNotEmpty(iotNodeInfo)){
	
			String lpmKey = ProCacheUtil.getCache(CacheName.DEVICECODE_LPM, iotNodeInfo.getDevice_code() );
				
			if(ObjectUtil.isNotEmpty(lpmKey)){
				IotLpmInfo iotLpmInfo = ProCacheUtil.getCache(CacheName.LPMINFO, lpmKey );
				
				MqttService.pubMessage(strBuffer.toString(), "/lpm/"+ iotLpmInfo.getLpm_key() );
				return 0 ;
			}
		}
		return -1 ;
	}
	
	// 设备重启命令
	public static Integer sendGatewayRestart(IotNodeInfoBO obj){
		StringBuffer strBuffer = new StringBuffer();
		if(obj.getIot_node_type() == CodeIot.IOT_NODE_STATUS.MQTT){
			return 0 ;
		}else if(obj.getIot_node_type() == CodeIot.IOT_NODE_STATUS.TCP){
			strBuffer.append("IOT_SERVER_LPM:reset,");
		}else if(obj.getIot_node_type() == CodeIot.IOT_NODE_STATUS.HTTP){
			// 直接更新
			return 0 ;
		}else if(obj.getIot_node_type() == CodeIot.IOT_NODE_STATUS.UDP){
			return 0 ;
		}else{
			// 异常
			return -1 ;
		}
		strBuffer.append( obj.getDevice_code() );
		
		if(ObjectUtil.isNotEmpty(obj)){
			
			String lpmKey = ProCacheUtil.getCache(CacheName.DEVICECODE_LPM, obj.getDevice_code() );
				
			if(ObjectUtil.isNotEmpty(lpmKey)){
				IotLpmInfo iotLpmInfo = ProCacheUtil.getCache(CacheName.LPMINFO, lpmKey );
				
				MqttService.pubMessage(strBuffer.toString(), "/lpm/"+ iotLpmInfo.getLpm_key() );
				return 0 ;
			}
		}
		return -1 ;
	}
	
	/**
	 * 参数读取
	 * @param obj
	 * @return
	 */
	public static Integer sendSensorParamRead(IotSensorInfoBO obj ){
		//IOT_SERVER_LPM:TYPE,deviceCode,param_type
		// TYPE = param_read
		StringBuffer strBuffer = new StringBuffer();
		//
		IotSensorInfo sensorInfo =  ProCacheUtil.getCache(CacheName.SENSORINFO, obj.getId().toString() );
		IotNodeInfoBO iotNodeInfo = ProCacheUtil.getCache(CacheName.NODEINFO, sensorInfo.getNode_id().toString() );
		
		if(iotNodeInfo.getIot_node_type() == CodeIot.IOT_NODE_STATUS.MQTT){
			obj.setSensor_device_id(sensorInfo.getSensor_device_id());
			obj.setPort_id(sensorInfo.getPort_id());

			return ProtocalFactory.getInstance(iotNodeInfo.getIot_protocal_category()).execServerParamRead(obj, iotNodeInfo);
		}else if(iotNodeInfo.getIot_node_type() == CodeIot.IOT_NODE_STATUS.TCP ){
			// tcp
			strBuffer.append("IOT_SERVER_LPM:param_read,");
			strBuffer.append( iotNodeInfo.getDevice_code()+"," );
			strBuffer.append( sensorInfo.getSensor_device_id()+"," );
			strBuffer.append( sensorInfo.getPort_id()+"," );
			strBuffer.append( sensorInfo.getInfos()  );
			
		}else{
			return -1 ;
		}
		
		if(ObjectUtil.isNotEmpty(iotNodeInfo)){
	
			String lpmKey = ProCacheUtil.getCache(CacheName.DEVICECODE_LPM, iotNodeInfo.getDevice_code() );
				
			if(ObjectUtil.isNotEmpty(lpmKey)){
				IotLpmInfo iotLpmInfo = ProCacheUtil.getCache(CacheName.LPMINFO, lpmKey );
				
				MqttService.pubMessage(strBuffer.toString(), "/lpm/"+ iotLpmInfo.getLpm_key() );
				return 0 ;
			}
		}
		return -1 ;
	}
	
	/**
	 * 参数下发
	 * @param obj
	 * @return
	 */
	public static Integer sendSensorParamDown(IotSensorInfoBO obj ){
		//IOT_SERVER_LPM:TYPE,deviceCode,param_type:values
		// TYPE = param_write
		//
		StringBuffer strBuffer = new StringBuffer();
		
		IotSensorInfo sensorInfo =  ProCacheUtil.getCache(CacheName.SENSORINFO, obj.getId().toString() );
				
		IotNodeInfoBO iotNodeInfo = ProCacheUtil.getCache(CacheName.NODEINFO, sensorInfo.getNode_id().toString() );
		if(iotNodeInfo.getIot_node_type() == CodeIot.IOT_NODE_STATUS.MQTT){

			float sdata_info = obj.getSdata() ;
			if(ObjectUtil.isNotEmpty(sensorInfo.getFormula_down()) && sensorInfo.getFormula_down().contains("x")  ){
				sdata_info = (float) Calculator.conversion( sensorInfo.getFormula_down().replaceAll("x", obj.getSdata()+"")) ;
			}
			obj.setSensor_device_id(sensorInfo.getSensor_device_id());
			obj.setPort_id(sensorInfo.getPort_id());
			obj.setRequest_sdata(sdata_info);

			return ProtocalFactory.getInstance(iotNodeInfo.getIot_protocal_category()).execServerParamWrite(obj, iotNodeInfo);
			
		}else if(iotNodeInfo.getIot_node_type() == CodeIot.IOT_NODE_STATUS.TCP){
			strBuffer.append("IOT_SERVER_LPM:param_write,");
			strBuffer.append( iotNodeInfo.getDevice_code()+"," );
			strBuffer.append( sensorInfo.getSensor_device_id() +"," );
			strBuffer.append( sensorInfo.getPort_id() +"," );
			
			if(ObjectUtil.isNotEmpty(sensorInfo.getFormula_down()) && sensorInfo.getFormula_down().contains("x")  ){
				float sdata_info = (float) Calculator.conversion( sensorInfo.getFormula_down().replaceAll("x", obj.getSdata()+"")) ;
				strBuffer.append(sdata_info +",") ;
			}else{
				strBuffer.append(obj.getSdata() +",") ;
			}
			
			
			if( ObjectUtil.isNotEmpty( sensorInfo.getInfos() )  ){
				strBuffer.append(sensorInfo.getInfos() ) ;				
			}else{
				strBuffer.append("{}" ) ;	
			}
		}
		
		if(ObjectUtil.isNotEmpty(iotNodeInfo)){
	
			String lpmKey = ProCacheUtil.getCache(CacheName.DEVICECODE_LPM, iotNodeInfo.getDevice_code() );
				
			if(ObjectUtil.isNotEmpty(lpmKey)){
				IotLpmInfo iotLpmInfo = ProCacheUtil.getCache(CacheName.LPMINFO, lpmKey );
						
				MqttService.pubMessage(strBuffer.toString(), "/lpm/"+ iotLpmInfo.getLpm_key() );
				return 0 ;
			}
		}
		return -1 ;
	}
	
	
}
