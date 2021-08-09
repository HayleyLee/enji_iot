package com.enji_iot.util.Entity.bo;

import com.enji_iot.util.Entity.bean.IotNodeInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *@类:IotNodeInfo
 *@作者:chenrj
 */

public class IotNodeInfoBO extends IotNodeInfo {

	private String scene_name;
	 
	 private String lpmKey ;
	
	 private Integer device_template_id ;
	 
	 private String template_name ;

	 private List<IotSensorInfoBO> iotSensorList;
	 
	 // 设备下 数据传感点，或者配置传感点
	 private Integer node_data_type ; // 0 ,1配置
	 
	 private String copy_device_code ;

	/**
	 * 储存时间
	 */
	private Date storeTime;

	/**
	 * 开关 0或者1
	 */
	private Integer val;

	private String user_name;

	public IotNodeInfoBO(String name, String device_code, Integer iot_node_type, String iot_protocal_category, Integer seq, Integer scene_id, Integer iot_node_status, Integer disable_flag, Integer img_id, Integer aid, Date atime, Integer mid, Date mtime, Integer template_id, Integer measure_unit_type, Integer iot_node_data_type, Integer store_strage, String formula_up, String formula_down, String maintenance_time, String lonLat, String infos, Integer frequency, String scene_name, String lpmKey, Integer device_template_id, String template_name, List<IotSensorInfoBO> iotSensorList, Integer node_data_type, String copy_device_code, Date storeTime, Integer val, String user_name) {
		super(name, device_code, iot_node_type, iot_protocal_category, seq, scene_id, iot_node_status, disable_flag, img_id, aid, atime, mid, mtime, template_id, measure_unit_type, iot_node_data_type, store_strage, formula_up, formula_down, maintenance_time, lonLat, infos, frequency);
		this.scene_name = scene_name;
		this.lpmKey = lpmKey;
		this.device_template_id = device_template_id;
		this.template_name = template_name;
		this.iotSensorList = iotSensorList;
		this.node_data_type = node_data_type;
		this.copy_device_code = copy_device_code;
		this.storeTime = storeTime;
		this.val = val;
		this.user_name = user_name;
	}

	public IotNodeInfoBO(String sensor_name, Integer scene_id, Integer user_id, String start_time, String end_time, Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, String name, String device_code, Integer iot_node_type, String iot_protocal_category, Integer seq, Integer scene_id1, Integer iot_node_status, Integer disable_flag, Integer img_id, Integer aid, Date atime, Integer mid, Date mtime, Integer template_id, Integer measure_unit_type, Integer iot_node_data_type, Integer store_strage, String formula_up, String formula_down, String maintenance_time, String lonLat, String infos, Integer frequency, String scene_name, String lpmKey, Integer device_template_id, String template_name, List<IotSensorInfoBO> iotSensorList, Integer node_data_type, String copy_device_code, Date storeTime, Integer val, String user_name) {
		super(sensor_name, scene_id, user_id, start_time, end_time, id, id_array, delete_flag, offset, limit, data, name, device_code, iot_node_type, iot_protocal_category, seq, scene_id1, iot_node_status, disable_flag, img_id, aid, atime, mid, mtime, template_id, measure_unit_type, iot_node_data_type, store_strage, formula_up, formula_down, maintenance_time, lonLat, infos, frequency);
		this.scene_name = scene_name;
		this.lpmKey = lpmKey;
		this.device_template_id = device_template_id;
		this.template_name = template_name;
		this.iotSensorList = iotSensorList;
		this.node_data_type = node_data_type;
		this.copy_device_code = copy_device_code;
		this.storeTime = storeTime;
		this.val = val;
		this.user_name = user_name;
	}

	public IotNodeInfoBO(Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, String name, String device_code, Integer iot_node_type, String iot_protocal_category, Integer seq, Integer scene_id, Integer iot_node_status, Integer disable_flag, Integer img_id, Integer aid, Date atime, Integer mid, Date mtime, Integer template_id, Integer measure_unit_type, Integer iot_node_data_type, Integer store_strage, String formula_up, String formula_down, String maintenance_time, String lonLat, String infos, Integer frequency, String scene_name, String lpmKey, Integer device_template_id, String template_name, List<IotSensorInfoBO> iotSensorList, Integer node_data_type, String copy_device_code, Date storeTime, Integer val, String user_name) {
		super(id, id_array, delete_flag, offset, limit, data, name, device_code, iot_node_type, iot_protocal_category, seq, scene_id, iot_node_status, disable_flag, img_id, aid, atime, mid, mtime, template_id, measure_unit_type, iot_node_data_type, store_strage, formula_up, formula_down, maintenance_time, lonLat, infos, frequency);
		this.scene_name = scene_name;
		this.lpmKey = lpmKey;
		this.device_template_id = device_template_id;
		this.template_name = template_name;
		this.iotSensorList = iotSensorList;
		this.node_data_type = node_data_type;
		this.copy_device_code = copy_device_code;
		this.storeTime = storeTime;
		this.val = val;
		this.user_name = user_name;
	}

	public IotNodeInfoBO(String scene_name, String lpmKey, Integer device_template_id, String template_name, List<IotSensorInfoBO> iotSensorList, Integer node_data_type, String copy_device_code, Date storeTime, Integer val, String user_name) {
		this.scene_name = scene_name;
		this.lpmKey = lpmKey;
		this.device_template_id = device_template_id;
		this.template_name = template_name;
		this.iotSensorList = iotSensorList;
		this.node_data_type = node_data_type;
		this.copy_device_code = copy_device_code;
		this.storeTime = storeTime;
		this.val = val;
		this.user_name = user_name;
	}

	public IotNodeInfoBO(Integer id) {
		super();
		this.setId(id);
	}

	public IotNodeInfoBO(){

	}

	public String getScene_name() {
		return scene_name;
	}

	public void setScene_name(String scene_name) {
		this.scene_name = scene_name;
	}

	public String getLpmKey() {
		return lpmKey;
	}

	public void setLpmKey(String lpmKey) {
		this.lpmKey = lpmKey;
	}

	public Integer getDevice_template_id() {
		return device_template_id;
	}

	public void setDevice_template_id(Integer device_template_id) {
		this.device_template_id = device_template_id;
	}

	public String getTemplate_name() {
		return template_name;
	}

	public void setTemplate_name(String template_name) {
		this.template_name = template_name;
	}

	public List<IotSensorInfoBO> getIotSensorList() {
		return iotSensorList;
	}

	public void setIotSensorList(List<IotSensorInfoBO> iotSensorList) {
		this.iotSensorList = iotSensorList;
	}

	public Integer getNode_data_type() {
		return node_data_type;
	}

	public void setNode_data_type(Integer node_data_type) {
		this.node_data_type = node_data_type;
	}

	public String getCopy_device_code() {
		return copy_device_code;
	}

	public void setCopy_device_code(String copy_device_code) {
		this.copy_device_code = copy_device_code;
	}

	public Date getStoreTime() {
		return storeTime;
	}

	public void setStoreTime(Date storeTime) {
		this.storeTime = storeTime;
	}

	public Integer getVal() {
		return val;
	}

	public void setVal(Integer val) {
		this.val = val;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
}

