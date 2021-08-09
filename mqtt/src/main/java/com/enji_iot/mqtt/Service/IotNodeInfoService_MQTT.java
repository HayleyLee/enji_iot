package com.enji_iot.mqtt.Service;

import com.enji_iot.util.Entity.bo.IotNodeInfoBO;

import java.util.Map;

public interface IotNodeInfoService_MQTT {

	Map<String, Object> updateNodeStatus(IotNodeInfoBO obj);

}
