package com.enji_iot.util.Entity.bo;

import com.enji_iot.util.Entity.bean.DeviceTemplateTiggerInfo;

/**
 * @类:DeviceTemplateTiggerInfo
 * @作者:M
 */

public class DeviceTemplateTiggerInfoBO extends DeviceTemplateTiggerInfo {

	public DeviceTemplateTiggerInfoBO(Integer id) {

		super();
		this.setId(id);
	}

	public DeviceTemplateTiggerInfoBO() {
	}

	private String from_sensor_name ;

	private String subActionParam ;

	public String getFrom_sensor_name() {
		return from_sensor_name;
	}

	public void setFrom_sensor_name(String from_sensor_name) {
		this.from_sensor_name = from_sensor_name;
	}

	public String getSubActionParam() {
		return subActionParam;
	}

	public void setSubActionParam(String subActionParam) {
		this.subActionParam = subActionParam;
	}
}
