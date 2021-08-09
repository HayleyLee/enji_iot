package com.enji_iot.util.Entity.bean;

public class MqttServerReCall {

	private String action ;
	
	private String client_id ;
	
	private String username ;
	
	private String reason ;

	public MqttServerReCall(String action, String client_id, String username, String reason) {
		this.action = action;
		this.client_id = client_id;
		this.username = username;
		this.reason = reason;
	}

	public MqttServerReCall() {
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
