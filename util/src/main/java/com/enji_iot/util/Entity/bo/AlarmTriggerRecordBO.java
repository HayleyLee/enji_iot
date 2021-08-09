package com.enji_iot.util.Entity.bo;

import com.enji_iot.util.Entity.bean.AlarmTriggerRecord;

/**
 * @类:AlarmTriggerRecord
 * @作者:M
 */

public class AlarmTriggerRecordBO extends AlarmTriggerRecord {

	public AlarmTriggerRecordBO(Integer id) {
		super();
		this.setId(id);
	}

	public AlarmTriggerRecordBO() {
	}

	private Integer sms_num ;
	
	private Integer voice_num ;
	
	private String name ;

	public Integer getSms_num() {
		return sms_num;
	}

	public void setSms_num(Integer sms_num) {
		this.sms_num = sms_num;
	}

	public Integer getVoice_num() {
		return voice_num;
	}

	public void setVoice_num(Integer voice_num) {
		this.voice_num = voice_num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
