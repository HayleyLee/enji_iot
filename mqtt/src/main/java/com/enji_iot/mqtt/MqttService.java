package com.enji_iot.mqtt;

import com.enji_iot.util.Util.LogUtil;
import com.enji_iot.util.Util.PropertiesUtil;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@DependsOn(value = "taskExecutor")
public class MqttService {
	
	@Autowired
	private TaskExecutor taskExecutor ;
	
	// MQTT安装的服务器地址:MQTT定义的端口号  
    public static final String HOST = PropertiesUtil.getProperty("mqtt.config" ,"mqtt.serverURI1") ;  
    // 定阅的主题  
    public static final String TOPIC = PropertiesUtil.getProperty("mqtt.config" ,"mqtt.service.topic")  ;  
    // 定义MQTT的ID
    private static final String clientid = PropertiesUtil.getProperty("mqtt.config" ,"mqtt.clientId")  ;  
    
    private static final String userName = PropertiesUtil.getProperty("mqtt.config" ,"mqtt.username")  ;  
    private static final String passWord = PropertiesUtil.getProperty("mqtt.config" ,"mqtt.password")  ; 

    private static final Boolean cleanSession = "true".equalsIgnoreCase(PropertiesUtil.getProperty("mqtt.config" ,"mqtt.cleanSession"))  ;
    
    private static MqttClient client;

    /** 
     * 构造函数 
     * @throws MqttException 
     */  
    public MqttService() throws MqttException {  
        // MemoryPersistence设置
        client = new MqttClient(HOST, clientid, new MemoryPersistence());  
    }

    @PostConstruct
    public void init(){
    	connect();  
    }
    
    /** 
     *  用来连接服务器 
     */  
    private void connect() {
        System.err.println("HOST:                           "+HOST);
        System.err.println("TOPIC:                           "+TOPIC);
        System.err.println("client_id:                           "+clientid);
        System.err.println("userName:                           "+userName);
        System.err.println("passWord:                           "+passWord);

        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);  
        options.setUserName(userName);  
        options.setPassword(passWord.toCharArray());  
        // 设置超时时间  
        options.setConnectionTimeout(20);  
        // 设置会话心跳时间  
        options.setKeepAliveInterval(30);
        // 重连
        options.setAutomaticReconnect(true);
        // 清楚缓存
        options.setCleanSession(cleanSession);
        try {  
            client.setCallback(new MessageCallback(taskExecutor));
            client.connect(options);
        } catch (Exception e) {
        	LogUtil.errorLog(e);
        } 
    }  

    public static void subscribe(){
    	try{
	    	 // 订阅消息  
    		String[] topic1 = TOPIC.split(",")  ;  
    		int[] Qos = new int[topic1.length];
            /**
             * 循环将两个主题的Qos设置为1
             */
    		for(int i = 0; i< Qos.length ;i++ ){
    			Qos[i] =1 ;
    		}
	        client.subscribe(topic1, Qos);  
    	}catch (Exception e) {
    		LogUtil.errorLog(e);
		}
    }
    /**
     * 消息发送
     * @param message byte
     * @param topic
     */
    public static void pubMessage(byte[] message,String topic){
    	MqttMessage mess = new MqttMessage();
    	mess.setQos(1);
    	mess.setRetained(false);
    	mess.setPayload(message);
    	try {
			client.publish(topic, mess);
		} catch (Exception e) {
			LogUtil.errorLog(e);
		}
    }
    
    /**
     * 消息发送
     * @param message
     * @param topic
     */
    public static void pubMessage(String message,String topic){
    	MqttMessage mess = new MqttMessage();
    	mess.setQos(1);
    	mess.setRetained(false);
    	mess.setPayload(message.getBytes());
    	try {
			client.publish(topic, mess);
		} catch (Exception e) {
			LogUtil.errorLog(e);
		}
    }
}

	

