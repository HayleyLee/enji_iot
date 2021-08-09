package com.enji_iot.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.core.task.TaskExecutor;


public class MessageCallback implements MqttCallbackExtended {

	private TaskExecutor taskExecutor ;
	
	public MessageCallback(TaskExecutor taskExecutor2) {
		super();
		this.taskExecutor = taskExecutor2 ;
	}

	@Override
	/**
	 * 与服务器的连接丢失时，将调用此方法。
	 * @param arg0 ：失去连接的原因
	 */
	public void connectionLost(Throwable arg0) {
		// TODO 连接断开，可以做重连,目前重连失败，还没有设置
		System.err.println("断开连接的原因是："+arg0);
	}

	@Override
	/**
	* 在完成消息传递并收到所有确认后调用
	 * @param token :与消息关联的传递令牌
	* */
	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO  delivery 传送OK
	}

	@Override
	/**
	 * 从服务器收到消息时，将调用此方法
	 */
	public void messageArrived(String topic, MqttMessage message) {
		try{
			// 消息放入线程池中处理
			taskExecutor.execute(new MessageHandler(message.getPayload(),new String(message.getPayload()), topic));
			Thread.sleep(50);			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void connectComplete(boolean arg0, String arg1) {
		// 连接成功后，重新订阅自己的主题
		MqttService.subscribe();
	}
}
