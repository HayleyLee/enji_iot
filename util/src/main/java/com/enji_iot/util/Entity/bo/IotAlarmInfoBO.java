package com.enji_iot.util.Entity.bo;

import com.enji_iot.util.Entity.bean.IotAlarmInfo;

/**
 * @类:IotAlarmInfo
 * @作者:chenrj
 */

public class IotAlarmInfoBO extends IotAlarmInfo {

	public IotAlarmInfoBO(Integer id) {
		super();
		this.setId(id);
	}

	public IotAlarmInfoBO() {
	}

	private String atimestr ;
	
	private String node_name ;

	public String getAtimestr() {
		return atimestr;
	}

	public void setAtimestr(String atimestr) {
		this.atimestr = atimestr;
	}

	public String getNode_name() {
		return node_name;
	}

	public void setNode_name(String node_name) {
		this.node_name = node_name;
	}
}
