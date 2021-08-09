package com.enji_iot.util.Entity.bo;

import java.util.Map;

public class BussinessTriggerBO {

	/**
	 * 消息
	 */
	private String message ;
	
	/**
	 * 阿里云短信报警格式
	 */
	private Map<String,String> aliyunSms ;
	
	/**
	 * 阿里云语音格式
	 */
	private Map<String,String> aliyunSmsVoice ;
	
	/**
	 * 1 模板1 - 设备报警
	 * 2 模板2 - 设备离线
	 * 3 模板3 - 设备报警恢复
	 * 4 模板4 - 设备离线恢复
	 */
	private Integer smsType ;
	
	/**
	 * 1 模板1 - 设备报警
	 * 2 模板2 - 设备离线
	 * 3 模板3 - 设备报警恢复
	 * 4 模板4 - 设备离线恢复
	 */
	private Integer vocieType ;

	public BussinessTriggerBO(String message, Map<String, String> aliyunSms, Map<String, String> aliyunSmsVoice, Integer smsType, Integer vocieType) {
		this.message = message;
		this.aliyunSms = aliyunSms;
		this.aliyunSmsVoice = aliyunSmsVoice;
		this.smsType = smsType;
		this.vocieType = vocieType;
	}

	public BussinessTriggerBO() {
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, String> getAliyunSms() {
		return aliyunSms;
	}

	public void setAliyunSms(Map<String, String> aliyunSms) {
		this.aliyunSms = aliyunSms;
	}

	public Map<String, String> getAliyunSmsVoice() {
		return aliyunSmsVoice;
	}

	public void setAliyunSmsVoice(Map<String, String> aliyunSmsVoice) {
		this.aliyunSmsVoice = aliyunSmsVoice;
	}

	public Integer getSmsType() {
		return smsType;
	}

	public void setSmsType(Integer smsType) {
		this.smsType = smsType;
	}

	public Integer getVocieType() {
		return vocieType;
	}

	public void setVocieType(Integer vocieType) {
		this.vocieType = vocieType;
	}
}
