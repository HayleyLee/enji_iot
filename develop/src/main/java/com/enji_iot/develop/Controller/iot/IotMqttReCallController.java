package com.enji_iot.develop.Controller.iot;

import com.enji_iot.util.Entity.bean.MqttServerReCall;
import com.enji_iot.util.Entity.bo.IotNodeInfoBO;
import com.enji_iot.cache.Cache.CacheName;
import com.enji_iot.cache.Cache.ProCacheUtil;
import com.enji_iot.util.Config.ProConfig;
import com.enji_iot.util.Common.CodeIot;
import com.enji_iot.util.Common.RequestURLIOT;
import com.enji_iot.mqtt.protocol.ProtocalFactory;
import com.enji_iot.util.Util.ObjectUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Controller
public class IotMqttReCallController {
	
	/**
	 * HTTP 账户、密码鉴权
	 * @param response
	 * @param clientid
	 * @param username
	 * @param password
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.IotMqttCall.IOT_MQTT_AUTH_CLIENT)
	public void authClient(HttpServletResponse response,
                           @RequestParam(required=true) String clientid , @RequestParam(required=false) String username,
                           @RequestParam(required=false) String password ) {
		try {
			if( ProConfig.MQTT.PASSWORD.equals(password) && ProConfig.MQTT.USERNAME.equals(username)){
				response.setStatus(200);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setStatus(401);
	}
	
	/**
	 * 设备上下线
	 * @param response
	 * @param obj
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.IotMqttCall.IOT_MQTT_CLIENT_NOTICE)
	public void authClient(HttpServletResponse response,
			@RequestBody MqttServerReCall obj ) {
		try {
			IotNodeInfoBO node = new IotNodeInfoBO();
			node.setDevice_code(obj.getClient_id());
			
			IotNodeInfoBO nodeInfo = ProCacheUtil.getCache(CacheName.NODEINFO_DEVICECODE, obj.getClient_id() );
			if(ObjectUtil.isEmpty(nodeInfo)){
				response.setStatus(200);
				return ;
			}
			if("session_created".equals(obj.getAction())){
				// 更新网关在线
				node.setIot_node_status(CodeIot.DEVICE_STATUS.ONLINE);
			}else if("session_terminated".equals(obj.getAction())){
				// 更新网关离线
				node.setIot_node_status(CodeIot.DEVICE_STATUS.OFFLINE);
			}
			ProtocalFactory.getInstance(nodeInfo.getIot_protocal_category()).loginProtocal(node);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setStatus(200);
	}
	
	
}
