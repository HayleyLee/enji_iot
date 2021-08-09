package com.enji_iot.util.Entity.bo;

import com.enji_iot.util.Entity.bean.User;

public class UserInfoBO extends User {

	public UserInfoBO(Integer id)  {
		super();
	 	this.setId(id);
	}

	public UserInfoBO() {
	}

	private Integer scene_num ;
	
	private Integer scene_num_flag ;
	
	// 注册类型，1 短信注册 
	private Integer register_type ;
	
	private String newpassword ;  
	
	private Integer sms_num ;
	
	private Integer voice_num ;

	public Integer getScene_num() {
		return scene_num;
	}

	public void setScene_num(Integer scene_num) {
		this.scene_num = scene_num;
	}

	public Integer getScene_num_flag() {
		return scene_num_flag;
	}

	public void setScene_num_flag(Integer scene_num_flag) {
		this.scene_num_flag = scene_num_flag;
	}

	public Integer getRegister_type() {
		return register_type;
	}

	public void setRegister_type(Integer register_type) {
		this.register_type = register_type;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

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
}
