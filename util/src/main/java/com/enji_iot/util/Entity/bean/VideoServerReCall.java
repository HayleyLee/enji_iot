package com.enji_iot.util.Entity.bean;

/**
 *@类:VideoServerReCall
 *@作者:M
 */

public class VideoServerReCall  {

	// 动作类型
	private String action ;
	
	// 
	private Integer client_id ;
	
	private String ip ;
	
	private String vhost ;
	
	private String app ;
	
	private String tcUrl ;
	
	private String pageUrl ;
	
	private String stream ;
	
	private String file ;
	
	private String cwd ;
	
	private Long send_bytes ;
	
	private Long recv_bytes ;
	
	private String param ;

	public VideoServerReCall(String action, Integer client_id, String ip, String vhost, String app, String tcUrl, String pageUrl, String stream, String file, String cwd, Long send_bytes, Long recv_bytes, String param) {
		this.action = action;
		this.client_id = client_id;
		this.ip = ip;
		this.vhost = vhost;
		this.app = app;
		this.tcUrl = tcUrl;
		this.pageUrl = pageUrl;
		this.stream = stream;
		this.file = file;
		this.cwd = cwd;
		this.send_bytes = send_bytes;
		this.recv_bytes = recv_bytes;
		this.param = param;
	}

	public VideoServerReCall() {
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Integer getClient_id() {
		return client_id;
	}

	public void setClient_id(Integer client_id) {
		this.client_id = client_id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getVhost() {
		return vhost;
	}

	public void setVhost(String vhost) {
		this.vhost = vhost;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getTcUrl() {
		return tcUrl;
	}

	public void setTcUrl(String tcUrl) {
		this.tcUrl = tcUrl;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getCwd() {
		return cwd;
	}

	public void setCwd(String cwd) {
		this.cwd = cwd;
	}

	public Long getSend_bytes() {
		return send_bytes;
	}

	public void setSend_bytes(Long send_bytes) {
		this.send_bytes = send_bytes;
	}

	public Long getRecv_bytes() {
		return recv_bytes;
	}

	public void setRecv_bytes(Long recv_bytes) {
		this.recv_bytes = recv_bytes;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
}