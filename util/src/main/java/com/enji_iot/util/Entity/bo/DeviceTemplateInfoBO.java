package com.enji_iot.util.Entity.bo;


import com.enji_iot.util.Entity.bean.DeviceTemplateInfo;

/**
 * @类:DeviceTemplateInfo
 * @作者:M
 */

public class DeviceTemplateInfoBO extends DeviceTemplateInfo {

	public DeviceTemplateInfoBO(Integer id) {

		super();
		this.setId(id);
	}

	public DeviceTemplateInfoBO() {
	}

	private String sensor_num ;
	
	private String trigger_num ;

	public String getSensor_num() {
		return sensor_num;
	}

	public void setSensor_num(String sensor_num) {
		this.sensor_num = sensor_num;
	}

	public String getTrigger_num() {
		return trigger_num;
	}

	public void setTrigger_num(String trigger_num) {
		this.trigger_num = trigger_num;
	}
}
