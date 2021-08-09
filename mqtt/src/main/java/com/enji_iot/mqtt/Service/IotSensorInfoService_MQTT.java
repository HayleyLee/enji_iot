package com.enji_iot.mqtt.Service;

import net.sf.json.JSONObject;

import java.util.Map;

public interface IotSensorInfoService_MQTT {

	/**
	 * 更新实时数据
	 */
	Map<String, Object> updateRealTimeData(JSONObject tmp, String devCode);

}
