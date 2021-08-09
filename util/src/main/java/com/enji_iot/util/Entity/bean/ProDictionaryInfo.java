package com.enji_iot.util.Entity.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ProDictionaryInfo extends BaseBean {

	private String p_dictionary_name ;
	
	private String dictionary_name ;
	
	private Integer p_code ;
	
	private Integer code ;
	
	private String name ;
	
	private String value ;
	
	private Integer seq ;
	
	private Date mtime ;
	
	private List<ProDictionaryInfo> sub = new ArrayList<>() ;

	public ProDictionaryInfo(String p_dictionary_name, String dictionary_name, Integer p_code, Integer code, String name, String value, Integer seq, Date mtime, List<ProDictionaryInfo> sub) {
		this.p_dictionary_name = p_dictionary_name;
		this.dictionary_name = dictionary_name;
		this.p_code = p_code;
		this.code = code;
		this.name = name;
		this.value = value;
		this.seq = seq;
		this.mtime = mtime;
		this.sub = sub;
	}

	public ProDictionaryInfo(String sensor_name, Integer scene_id, Integer user_id, String start_time, String end_time, Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, String p_dictionary_name, String dictionary_name, Integer p_code, Integer code, String name, String value, Integer seq, Date mtime, List<ProDictionaryInfo> sub) {
		super(sensor_name, scene_id, user_id, start_time, end_time, id, id_array, delete_flag, offset, limit, data);
		this.p_dictionary_name = p_dictionary_name;
		this.dictionary_name = dictionary_name;
		this.p_code = p_code;
		this.code = code;
		this.name = name;
		this.value = value;
		this.seq = seq;
		this.mtime = mtime;
		this.sub = sub;
	}

	public ProDictionaryInfo(Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, String p_dictionary_name, String dictionary_name, Integer p_code, Integer code, String name, String value, Integer seq, Date mtime, List<ProDictionaryInfo> sub) {
		super(id, id_array, delete_flag, offset, limit, data);
		this.p_dictionary_name = p_dictionary_name;
		this.dictionary_name = dictionary_name;
		this.p_code = p_code;
		this.code = code;
		this.name = name;
		this.value = value;
		this.seq = seq;
		this.mtime = mtime;
		this.sub = sub;
	}

	public ProDictionaryInfo() {
	}

	public String getP_dictionary_name() {
		return p_dictionary_name;
	}

	public void setP_dictionary_name(String p_dictionary_name) {
		this.p_dictionary_name = p_dictionary_name;
	}

	public String getDictionary_name() {
		return dictionary_name;
	}

	public void setDictionary_name(String dictionary_name) {
		this.dictionary_name = dictionary_name;
	}

	public Integer getP_code() {
		return p_code;
	}

	public void setP_code(Integer p_code) {
		this.p_code = p_code;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Date getMtime() {
		return mtime;
	}

	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}

	public List<ProDictionaryInfo> getSub() {
		return sub;
	}

	public void setSub(List<ProDictionaryInfo> sub) {
		this.sub = sub;
	}
}
