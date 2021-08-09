package com.enji_iot.util.Entity.bo;

public class AliyunParamBO {

	public String phonenumber ;
	
	private String SignaName ;
	
	private String templateCode ;
	
	private String templateParam ;
	
	private String calledShowNumber ;

	public AliyunParamBO(String phonenumber, String signaName, String templateCode, String templateParam, String calledShowNumber) {
		this.phonenumber = phonenumber;
		this.SignaName = signaName;
		this.templateCode = templateCode;
		this.templateParam = templateParam;
		this.calledShowNumber = calledShowNumber;
	}

	public AliyunParamBO() {
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getSignaName() {
		return SignaName;
	}

	public void setSignaName(String signaName) {
		this.SignaName = signaName;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getTemplateParam() {
		return templateParam;
	}

	public void setTemplateParam(String templateParam) {
		this.templateParam = templateParam;
	}

	public String getCalledShowNumber() {
		return calledShowNumber;
	}

	public void setCalledShowNumber(String calledShowNumber) {
		this.calledShowNumber = calledShowNumber;
	}
}
