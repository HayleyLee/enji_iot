package com.enji_iot.mqtt;

import com.enji_iot.mqtt.protocol.ProtocalFactory;
import com.enji_iot.cache.Cache.CacheName;
import com.enji_iot.cache.Cache.ProCacheUtil;
import com.enji_iot.util.Util.LogUtil;
import com.enji_iot.util.Util.ObjectUtil;

public class MessageHandler implements Runnable {
	
	private String message ;
	private String topic ;
	private byte[] msg ;
	
	public MessageHandler(byte[] msg,String message,String topic){
		this.message = message ;
		this.topic = topic;
		this.msg= msg;
	}
	
	@Override
	public void run() {
		try{
			if(topic.contains("/update/scene")){
				// 系统消息
				String[] tmp = topic.split("/");
				String scene_id = ProCacheUtil.getCache(CacheName.SCENE_IPDATE_FLAG ,tmp[3] );
				if( ObjectUtil.isEmpty(scene_id) ){
					ProCacheUtil.addCache(CacheName.SCENE_IPDATE_FLAG ,tmp[3],tmp[3] );
				}
			}
			else if( topic.contains("/dev/coo") ){
				// 小名智能协议
				ProtocalFactory.getInstance("ProtocalMing").analysisData(topic, msg, message);
			}
			//心跳
			else if(topic.contains("/handbert/coo")){
//				ProtocalFactory.getInstance("ProtocalMing").handbert(topic,message);
			}

		}catch(Exception e){
			LogUtil.errorLog(e);
		}
	}
}
