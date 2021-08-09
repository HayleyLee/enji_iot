package com.enji_iot.util.Entity.bo;

import com.enji_iot.util.Entity.bean.IotSceneInfo;

public class IotSceneInfoBO extends IotSceneInfo {

	private Integer is_parent ;

	private Integer unread_alarm ;
	
	private Integer device_num ;
	
	public IotSceneInfoBO(Integer id) {
		super();
		this.setId(id);
	}

	public IotSceneInfoBO() {
	}

	public Integer getIs_parent() {
		return is_parent;
	}

	public void setIs_parent(Integer is_parent) {
		this.is_parent = is_parent;
	}

	public Integer getUnread_alarm() {
		return unread_alarm;
	}

	public void setUnread_alarm(Integer unread_alarm) {
		this.unread_alarm = unread_alarm;
	}

	public Integer getDevice_num() {
		return device_num;
	}

	public void setDevice_num(Integer device_num) {
		this.device_num = device_num;
	}
}
