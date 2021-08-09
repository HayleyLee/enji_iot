package com.enji_iot.util.Entity.bo;

/**
 *@类:ContactUserInfo
 *@作者:chenrj
 */

public class CommonInfoBO  {

	private Integer num ;
	
	private Integer param ;

	public CommonInfoBO(Integer num, Integer param) {
		this.num = num;
		this.param = param;
	}

	public CommonInfoBO() {
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getParam() {
		return param;
	}

	public void setParam(Integer param) {
		this.param = param;
	}
}

