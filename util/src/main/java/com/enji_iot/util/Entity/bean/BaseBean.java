package com.enji_iot.util.Entity.bean;

import java.util.List;
import java.util.Map;

public class BaseBean extends IotBaseBean {

	private Integer id ;
	
	private List<Integer> id_array ;
	
	/**
	 * 删除标志，0正常，1删除
	 */
	private Integer delete_flag = 0 ;
	
	public Integer offset ;
	
	public Integer limit ;
	
	public Map<String, String> data = null;

	public BaseBean() {
	}

	public BaseBean(String sensor_name, Integer scene_id, Integer user_id, String start_time, String end_time, Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data) {
		super(sensor_name, scene_id, user_id, start_time, end_time);
		this.id = id;
		this.id_array = id_array;
		this.delete_flag = delete_flag;
		this.offset = offset;
		this.limit = limit;
		this.data = data;
	}

	public BaseBean(Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data) {
		this.id = id;
		this.id_array = id_array;
		this.delete_flag = delete_flag;
		this.offset = offset;
		this.limit = limit;
		this.data = data;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Integer> getId_array() {
		return id_array;
	}

	public void setId_array(List<Integer> id_array) {
		this.id_array = id_array;
	}

	public Integer getDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(Integer delete_flag) {
		this.delete_flag = delete_flag;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

}
