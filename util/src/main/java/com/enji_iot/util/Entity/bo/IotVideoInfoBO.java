package com.enji_iot.util.Entity.bo;

import com.enji_iot.util.Entity.bean.IotVideoInfo;

/**
 * @类:IotVideoInfo
 * @作者:M
 */

public class IotVideoInfoBO extends IotVideoInfo {

	public IotVideoInfoBO(Integer id) {

		super();
		this.setId(id);
	}

	public IotVideoInfoBO() {
	}

	private String scene_name ;

	public String getScene_name() {
		return scene_name;
	}

	public void setScene_name(String scene_name) {
		this.scene_name = scene_name;
	}
}
