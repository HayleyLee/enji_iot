package com.enji_iot.util.Entity.bo;

import com.enji_iot.util.Entity.bean.IotSensorInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *@类:IotSensorInfo
 *@作者:chenrj
 */

public class IotSensorInfoBO extends IotSensorInfo {

	public IotSensorInfoBO(Integer id){
		setId(id);
	}

	public IotSensorInfoBO() {
	}

	private String device_code ;
	
	private String node_name ;
	
	private String scene_name ;
	
	private Integer scene_id ;
	
	private String iot_sensor_type_array ;
	
	private List<IotTriggerInfoBO> triggerList = new ArrayList<>();
	
	private List<historySimpleData> historyDara = new ArrayList<>();
	
	/**
	 * 储存时间
	 */
	private Date storeTime;

	public String getDevice_code() {
		return device_code;
	}

	public void setDevice_code(String device_code) {
		this.device_code = device_code;
	}

	public String getNode_name() {
		return node_name;
	}

	public void setNode_name(String node_name) {
		this.node_name = node_name;
	}

	public String getScene_name() {
		return scene_name;
	}

	public void setScene_name(String scene_name) {
		this.scene_name = scene_name;
	}

	@Override
	public Integer getScene_id() {
		return scene_id;
	}

	@Override
	public void setScene_id(Integer scene_id) {
		this.scene_id = scene_id;
	}

	public String getIot_sensor_type_array() {
		return iot_sensor_type_array;
	}

	public void setIot_sensor_type_array(String iot_sensor_type_array) {
		this.iot_sensor_type_array = iot_sensor_type_array;
	}

	public List<IotTriggerInfoBO> getTriggerList() {
		return triggerList;
	}

	public void setTriggerList(List<IotTriggerInfoBO> triggerList) {
		this.triggerList = triggerList;
	}

	public List<historySimpleData> getHistoryDara() {
		return historyDara;
	}

	public void setHistoryDara(List<historySimpleData> historyDara) {
		this.historyDara = historyDara;
	}

	public Date getStoreTime() {
		return storeTime;
	}

	public void setStoreTime(Date storeTime) {
		this.storeTime = storeTime;
	}
}

