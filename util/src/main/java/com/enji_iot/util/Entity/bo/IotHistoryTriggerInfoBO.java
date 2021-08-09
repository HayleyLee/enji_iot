package com.enji_iot.util.Entity.bo;

import com.enji_iot.util.Entity.bean.IotHistoryTriggerInfo;

/**
 * @类:IotHistoryTriggerInfo
 * @作者:chenrj
 */

public class IotHistoryTriggerInfoBO extends IotHistoryTriggerInfo {

	public IotHistoryTriggerInfoBO(Integer id) {

		super();
		this.setId(id);
	}

	public IotHistoryTriggerInfoBO() {
	}

	private Integer node_id ;

	public Integer getNode_id() {
		return node_id;
	}

	public void setNode_id(Integer node_id) {
		this.node_id = node_id;
	}
}
