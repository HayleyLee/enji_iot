package com.enji_iot.util.Entity.bean;

/**
 * 放置一些公共业务字段
 * @author chenrj
 *
 */

public class IotBaseBean {

	private String sensor_name ;
	
	private Integer scene_id ;
	
	private Integer user_id ;
	
	private String start_time ;
	
	private String end_time ;

	public IotBaseBean(String sensor_name, Integer scene_id, Integer user_id, String start_time, String end_time) {
		this.sensor_name = sensor_name;
		this.scene_id = scene_id;
		this.user_id = user_id;
		this.start_time = start_time;
		this.end_time = end_time;
	}

	public IotBaseBean() {
	}

	public String getSensor_name() {
		return sensor_name;
	}

	public void setSensor_name(String sensor_name) {
		this.sensor_name = sensor_name;
	}

	public Integer getScene_id() {
		return scene_id;
	}

	public void setScene_id(Integer scene_id) {
		this.scene_id = scene_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
}
