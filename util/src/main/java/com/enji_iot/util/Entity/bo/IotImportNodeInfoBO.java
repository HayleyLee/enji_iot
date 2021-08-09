package com.enji_iot.util.Entity.bo;

/**
 *@类:IotNodeInfo
 *@作者:chenrj
 */

public class IotImportNodeInfoBO {

	private String name ;
	
	private String device_code ;
	 
	private String copy_device_code ;

	public IotImportNodeInfoBO(String name, String device_code, String copy_device_code) {
		this.name = name;
		this.device_code = device_code;
		this.copy_device_code = copy_device_code;
	}

	public IotImportNodeInfoBO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDevice_code() {
		return device_code;
	}

	public void setDevice_code(String device_code) {
		this.device_code = device_code;
	}

	public String getCopy_device_code() {
		return copy_device_code;
	}

	public void setCopy_device_code(String copy_device_code) {
		this.copy_device_code = copy_device_code;
	}
}

