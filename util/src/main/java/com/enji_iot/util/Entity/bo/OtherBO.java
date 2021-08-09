package com.enji_iot.util.Entity.bo;

public class OtherBO {

	private Integer online_device_num ;
	
	private Integer all_device_num ;
	
	private Integer all_alarm_num ;
	
	private Integer unsolve_alarm_num ;

	public OtherBO(Integer online_device_num, Integer all_device_num, Integer all_alarm_num, Integer unsolve_alarm_num) {
		this.online_device_num = online_device_num;
		this.all_device_num = all_device_num;
		this.all_alarm_num = all_alarm_num;
		this.unsolve_alarm_num = unsolve_alarm_num;
	}

	public OtherBO() {
	}

	public Integer getOnline_device_num() {
		return online_device_num;
	}

	public void setOnline_device_num(Integer online_device_num) {
		this.online_device_num = online_device_num;
	}

	public Integer getAll_device_num() {
		return all_device_num;
	}

	public void setAll_device_num(Integer all_device_num) {
		this.all_device_num = all_device_num;
	}

	public Integer getAll_alarm_num() {
		return all_alarm_num;
	}

	public void setAll_alarm_num(Integer all_alarm_num) {
		this.all_alarm_num = all_alarm_num;
	}

	public Integer getUnsolve_alarm_num() {
		return unsolve_alarm_num;
	}

	public void setUnsolve_alarm_num(Integer unsolve_alarm_num) {
		this.unsolve_alarm_num = unsolve_alarm_num;
	}
}
