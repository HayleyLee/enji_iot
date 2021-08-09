package com.enji_iot.util.Entity.dto;

public class AliyunSmsDto {

	private String RequestId ;
	
	private String Message ;
	
	private String Code ;
	
	private String BizId ;
	
	private String CallId ;

	public AliyunSmsDto(String requestId, String message, String code, String bizId, String callId) {
		RequestId = requestId;
		Message = message;
		Code = code;
		BizId = bizId;
		CallId = callId;
	}

	public AliyunSmsDto() {
	}

	public String getRequestId() {
		return RequestId;
	}

	public void setRequestId(String requestId) {
		RequestId = requestId;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getBizId() {
		return BizId;
	}

	public void setBizId(String bizId) {
		BizId = bizId;
	}

	public String getCallId() {
		return CallId;
	}

	public void setCallId(String callId) {
		CallId = callId;
	}
}
