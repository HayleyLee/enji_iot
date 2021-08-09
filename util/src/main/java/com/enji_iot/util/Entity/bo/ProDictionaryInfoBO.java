package com.enji_iot.util.Entity.bo;

import com.enji_iot.util.Entity.bean.ProDictionaryInfo;

public class ProDictionaryInfoBO extends ProDictionaryInfo {
	
	public ProDictionaryInfoBO(Integer id) {
		// TODO Auto-generated constructor stub
		super.setId(id);
	}

	public ProDictionaryInfoBO() {
	}

	private Integer isOnlyP ;

	public Integer getIsOnlyP() {
		return isOnlyP;
	}

	public void setIsOnlyP(Integer isOnlyP) {
		this.isOnlyP = isOnlyP;
	}
}
