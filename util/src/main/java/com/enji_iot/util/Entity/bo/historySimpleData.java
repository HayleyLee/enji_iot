package com.enji_iot.util.Entity.bo;

import java.util.Date;

/**
 * @作者:M
 */

public class historySimpleData  {

	private String sdata ;
	
	private Date atime ;

	public historySimpleData(String sdata, Date atime) {
		this.sdata = sdata;
		this.atime = atime;
	}

	public historySimpleData() {
	}

	public String getSdata() {
		return sdata;
	}

	public void setSdata(String sdata) {
		this.sdata = sdata;
	}

	public Date getAtime() {
		return atime;
	}

	public void setAtime(Date atime) {
		this.atime = atime;
	}
}
