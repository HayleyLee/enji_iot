package com.enji_iot.util.Entity.bean;

public class TableSystem {

	private String table_name ;
	
	private Integer table_index ;
	
	private String table_names ;
	
	private Integer num ;
	
	private String db_name ;

	public TableSystem(String table_name, Integer table_index, String table_names, Integer num, String db_name) {
		this.table_name = table_name;
		this.table_index = table_index;
		this.table_names = table_names;
		this.num = num;
		this.db_name = db_name;
	}

	public TableSystem() {
	}

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

	public Integer getTable_index() {
		return table_index;
	}

	public void setTable_index(Integer table_index) {
		this.table_index = table_index;
	}

	public String getTable_names() {
		return table_names;
	}

	public void setTable_names(String table_names) {
		this.table_names = table_names;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getDb_name() {
		return db_name;
	}

	public void setDb_name(String db_name) {
		this.db_name = db_name;
	}
}
