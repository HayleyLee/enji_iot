package com.enji_iot.mqtt.protocol;

import com.enji_iot.util.Entity.bo.IotNodeInfoBO;
import com.enji_iot.util.Entity.bo.IotSensorInfoBO;

import java.text.ParseException;

public interface Iprotocal {

	/**
	 * 登录
	 * @param topic
	 * @param data
	 * @param msg
	 */
	void loginProtocal(Object obj);
	
	/**
	 * 数据解析
	 * @param topic
	 * @param data
	 * @param msg
	 */
	void analysisData(String topic, byte[] data, String msg) throws InterruptedException, ParseException;
	
	/**
	 * 心跳包
	 * @param topic
	 */
	void handbert(String topic, String msg);
	
	/**
	 * 执行server cmd
	 * @param topic
	 * @param data
	 * @param msg
	 */
	Integer execServerControll(IotNodeInfoBO node) ;
	
	/**
	 * 执行server param write
	 * @param topic
	 * @param data
	 * @param msg
	 */
	Integer execServerParamWrite(IotSensorInfoBO sensor, IotNodeInfoBO node) ;
	
	/**
	 * 执行server param read
	 * @param topic
	 * @param data
	 * @param msg
	 */
	Integer execServerParamRead(IotSensorInfoBO sensor, IotNodeInfoBO node) ;
	
	/**
	 * 退出
	 * @param topic
	 */
	void logout(Object obj);

	/**
	 * 新增：设备的上下线
	 * @param node
	 * @param flag 1：上线  0：下线
	 */
	void node_OnLine_OffLine(IotNodeInfoBO node, Integer flag);

	void add_Video_Live(Integer id);

}
