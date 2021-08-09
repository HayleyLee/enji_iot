package com.enji_iot.util.Entity.bo;

/**
 *@类:IotNodeInfo
 *@作者:chenrj
 */

public class IotStatisticBO  {

	private Integer num ;
	
	private Integer statistic_type ;

	public IotStatisticBO(Integer num, Integer statistic_type) {
		this.num = num;
		this.statistic_type = statistic_type;
	}

	public IotStatisticBO() {
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getStatistic_type() {
		return statistic_type;
	}

	public void setStatistic_type(Integer statistic_type) {
		this.statistic_type = statistic_type;
	}
}

