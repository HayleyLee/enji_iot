package com.enji_iot.util.Entity.bo;

public class ContactorBO {

	 private String contactor ;
	 
	 private String control_device_status ;
	 
	 private String control_device ;

	public ContactorBO(String contactor, String control_device_status, String control_device) {
		this.contactor = contactor;
		this.control_device_status = control_device_status;
		this.control_device = control_device;
	}

	public ContactorBO() {
	}

	public String getContactor() {
		return contactor;
	}

	public void setContactor(String contactor) {
		this.contactor = contactor;
	}

	public String getControl_device_status() {
		return control_device_status;
	}

	public void setControl_device_status(String control_device_status) {
		this.control_device_status = control_device_status;
	}

	public String getControl_device() {
		return control_device;
	}

	public void setControl_device(String control_device) {
		this.control_device = control_device;
	}
}
