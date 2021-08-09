package com.enji_iot.util.Entity.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class User extends BaseBean {

	private String name ;
	
	private String password ;
	
	private String phone ;
	
	private String email ;
	
	private String user_key ;
	
	private String nick_name ;
	
	private String real_name ;
	
	private String id_no ;
	
	private String wx_img_url ;

	private Integer type ;

	private Integer status ;

	private Integer sex ;
	
	private Integer img_id ;
	
	private String remark ;
	
	private Date register_time ;
	
	private String wx_open_id ;
	
	private String wp_id ;
	
	private String validate_code ;
	
	private Date validate_time ;
	/**aid*/
	private Integer aid;
	
	/**atime*/
	private Date atime;
	
	/**mid*/
	private Integer mid;
	
	/**mtime*/
	private Date mtime;

	public User(String name, String password, String phone, String email, String user_key, String nick_name, String real_name, String id_no, String wx_img_url, Integer type, Integer status, Integer sex, Integer img_id, String remark, Date register_time, String wx_open_id, String wp_id, String validate_code, Date validate_time, Integer aid, Date atime, Integer mid, Date mtime) {
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.user_key = user_key;
		this.nick_name = nick_name;
		this.real_name = real_name;
		this.id_no = id_no;
		this.wx_img_url = wx_img_url;
		this.type = type;
		this.status = status;
		this.sex = sex;
		this.img_id = img_id;
		this.remark = remark;
		this.register_time = register_time;
		this.wx_open_id = wx_open_id;
		this.wp_id = wp_id;
		this.validate_code = validate_code;
		this.validate_time = validate_time;
		this.aid = aid;
		this.atime = atime;
		this.mid = mid;
		this.mtime = mtime;
	}

	public User(String sensor_name, Integer scene_id, Integer user_id, String start_time, String end_time, Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, String name, String password, String phone, String email, String user_key, String nick_name, String real_name, String id_no, String wx_img_url, Integer type, Integer status, Integer sex, Integer img_id, String remark, Date register_time, String wx_open_id, String wp_id, String validate_code, Date validate_time, Integer aid, Date atime, Integer mid, Date mtime) {
		super(sensor_name, scene_id, user_id, start_time, end_time, id, id_array, delete_flag, offset, limit, data);
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.user_key = user_key;
		this.nick_name = nick_name;
		this.real_name = real_name;
		this.id_no = id_no;
		this.wx_img_url = wx_img_url;
		this.type = type;
		this.status = status;
		this.sex = sex;
		this.img_id = img_id;
		this.remark = remark;
		this.register_time = register_time;
		this.wx_open_id = wx_open_id;
		this.wp_id = wp_id;
		this.validate_code = validate_code;
		this.validate_time = validate_time;
		this.aid = aid;
		this.atime = atime;
		this.mid = mid;
		this.mtime = mtime;
	}

	public User(Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, String name, String password, String phone, String email, String user_key, String nick_name, String real_name, String id_no, String wx_img_url, Integer type, Integer status, Integer sex, Integer img_id, String remark, Date register_time, String wx_open_id, String wp_id, String validate_code, Date validate_time, Integer aid, Date atime, Integer mid, Date mtime) {
		super(id, id_array, delete_flag, offset, limit, data);
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.user_key = user_key;
		this.nick_name = nick_name;
		this.real_name = real_name;
		this.id_no = id_no;
		this.wx_img_url = wx_img_url;
		this.type = type;
		this.status = status;
		this.sex = sex;
		this.img_id = img_id;
		this.remark = remark;
		this.register_time = register_time;
		this.wx_open_id = wx_open_id;
		this.wp_id = wp_id;
		this.validate_code = validate_code;
		this.validate_time = validate_time;
		this.aid = aid;
		this.atime = atime;
		this.mid = mid;
		this.mtime = mtime;
	}

	public User() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUser_key() {
		return user_key;
	}

	public void setUser_key(String user_key) {
		this.user_key = user_key;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public String getId_no() {
		return id_no;
	}

	public void setId_no(String id_no) {
		this.id_no = id_no;
	}

	public String getWx_img_url() {
		return wx_img_url;
	}

	public void setWx_img_url(String wx_img_url) {
		this.wx_img_url = wx_img_url;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getImg_id() {
		return img_id;
	}

	public void setImg_id(Integer img_id) {
		this.img_id = img_id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getRegister_time() {
		return register_time;
	}

	public void setRegister_time(Date register_time) {
		this.register_time = register_time;
	}

	public String getWx_open_id() {
		return wx_open_id;
	}

	public void setWx_open_id(String wx_open_id) {
		this.wx_open_id = wx_open_id;
	}

	public String getWp_id() {
		return wp_id;
	}

	public void setWp_id(String wp_id) {
		this.wp_id = wp_id;
	}

	public String getValidate_code() {
		return validate_code;
	}

	public void setValidate_code(String validate_code) {
		this.validate_code = validate_code;
	}

	public Date getValidate_time() {
		return validate_time;
	}

	public void setValidate_time(Date validate_time) {
		this.validate_time = validate_time;
	}

	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public Date getAtime() {
		return atime;
	}

	public void setAtime(Date atime) {
		this.atime = atime;
	}

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public Date getMtime() {
		return mtime;
	}

	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}
}
