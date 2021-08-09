package com.enji_iot.util.Entity.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *@类:IotSensorInfo
 *@作者:chenrj
 */

public class IotSensorInfo extends BaseBean {

   /**name*/
   private String name;

   /**传感器单位*/
   private Integer measure_unit_type;

   /**类型：连续型、开关性等*/
   private Integer iot_sensor_type;

   /**种类：温度、IO、开关*/
   private Integer iot_sensor_category;

   /**node_id*/
   private Integer node_id;

   /**sensor_device_id*/
   private String sensor_device_id;

   /**port_id*/
   private Integer port_id;

   /**sdata*/
   private Float sdata;

   /**seq*/
   private Integer seq;

   /**传感器状态*/
   private Integer iot_sensor_status;

   /**request_sdata*/
   private Float request_sdata;

   /**精度*/
   private Integer sdata_degree;

   /**直接公式处理，公式可在数据字典选择*/
   private String formula_up;
   
   private String formula_down;

   /**register_time*/
   private Date register_time;

   /**aid*/
   private Integer aid;

   /**atime*/
   private Date atime;

   /**mid*/
   private Integer mid;

   /**mtime*/
   private Date mtime;
   
   private String str_sdata ;
   
   /** 数据类型  0 common 1， 配置 */
   private Integer data_type = 0 ;

   private String param_names ;
   
   /**
    * 配置参数类型
    */
   private Integer param_type ;
   /**
    * 配置参数单位
    */
   private String param_config ;
   
   private String infos ;
   /**
    * 储存策略
    */
   private Integer store_strage ;

   public IotSensorInfo(String name, Integer measure_unit_type, Integer iot_sensor_type, Integer iot_sensor_category, Integer node_id, String sensor_device_id, Integer port_id, Float sdata, Integer seq, Integer iot_sensor_status, Float request_sdata, Integer sdata_degree, String formula_up, String formula_down, Date register_time, Integer aid, Date atime, Integer mid, Date mtime, String str_sdata, Integer data_type, String param_names, Integer param_type, String param_config, String infos, Integer store_strage) {
      this.name = name;
      this.measure_unit_type = measure_unit_type;
      this.iot_sensor_type = iot_sensor_type;
      this.iot_sensor_category = iot_sensor_category;
      this.node_id = node_id;
      this.sensor_device_id = sensor_device_id;
      this.port_id = port_id;
      this.sdata = sdata;
      this.seq = seq;
      this.iot_sensor_status = iot_sensor_status;
      this.request_sdata = request_sdata;
      this.sdata_degree = sdata_degree;
      this.formula_up = formula_up;
      this.formula_down = formula_down;
      this.register_time = register_time;
      this.aid = aid;
      this.atime = atime;
      this.mid = mid;
      this.mtime = mtime;
      this.str_sdata = str_sdata;
      this.data_type = data_type;
      this.param_names = param_names;
      this.param_type = param_type;
      this.param_config = param_config;
      this.infos = infos;
      this.store_strage = store_strage;
   }

   public IotSensorInfo(String sensor_name, Integer scene_id, Integer user_id, String start_time, String end_time, Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, String name, Integer measure_unit_type, Integer iot_sensor_type, Integer iot_sensor_category, Integer node_id, String sensor_device_id, Integer port_id, Float sdata, Integer seq, Integer iot_sensor_status, Float request_sdata, Integer sdata_degree, String formula_up, String formula_down, Date register_time, Integer aid, Date atime, Integer mid, Date mtime, String str_sdata, Integer data_type, String param_names, Integer param_type, String param_config, String infos, Integer store_strage) {
      super(sensor_name, scene_id, user_id, start_time, end_time, id, id_array, delete_flag, offset, limit, data);
      this.name = name;
      this.measure_unit_type = measure_unit_type;
      this.iot_sensor_type = iot_sensor_type;
      this.iot_sensor_category = iot_sensor_category;
      this.node_id = node_id;
      this.sensor_device_id = sensor_device_id;
      this.port_id = port_id;
      this.sdata = sdata;
      this.seq = seq;
      this.iot_sensor_status = iot_sensor_status;
      this.request_sdata = request_sdata;
      this.sdata_degree = sdata_degree;
      this.formula_up = formula_up;
      this.formula_down = formula_down;
      this.register_time = register_time;
      this.aid = aid;
      this.atime = atime;
      this.mid = mid;
      this.mtime = mtime;
      this.str_sdata = str_sdata;
      this.data_type = data_type;
      this.param_names = param_names;
      this.param_type = param_type;
      this.param_config = param_config;
      this.infos = infos;
      this.store_strage = store_strage;
   }

   public IotSensorInfo(Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, String name, Integer measure_unit_type, Integer iot_sensor_type, Integer iot_sensor_category, Integer node_id, String sensor_device_id, Integer port_id, Float sdata, Integer seq, Integer iot_sensor_status, Float request_sdata, Integer sdata_degree, String formula_up, String formula_down, Date register_time, Integer aid, Date atime, Integer mid, Date mtime, String str_sdata, Integer data_type, String param_names, Integer param_type, String param_config, String infos, Integer store_strage) {
      super(id, id_array, delete_flag, offset, limit, data);
      this.name = name;
      this.measure_unit_type = measure_unit_type;
      this.iot_sensor_type = iot_sensor_type;
      this.iot_sensor_category = iot_sensor_category;
      this.node_id = node_id;
      this.sensor_device_id = sensor_device_id;
      this.port_id = port_id;
      this.sdata = sdata;
      this.seq = seq;
      this.iot_sensor_status = iot_sensor_status;
      this.request_sdata = request_sdata;
      this.sdata_degree = sdata_degree;
      this.formula_up = formula_up;
      this.formula_down = formula_down;
      this.register_time = register_time;
      this.aid = aid;
      this.atime = atime;
      this.mid = mid;
      this.mtime = mtime;
      this.str_sdata = str_sdata;
      this.data_type = data_type;
      this.param_names = param_names;
      this.param_type = param_type;
      this.param_config = param_config;
      this.infos = infos;
      this.store_strage = store_strage;
   }

   public IotSensorInfo() {
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Integer getMeasure_unit_type() {
      return measure_unit_type;
   }

   public void setMeasure_unit_type(Integer measure_unit_type) {
      this.measure_unit_type = measure_unit_type;
   }

   public Integer getIot_sensor_type() {
      return iot_sensor_type;
   }

   public void setIot_sensor_type(Integer iot_sensor_type) {
      this.iot_sensor_type = iot_sensor_type;
   }

   public Integer getIot_sensor_category() {
      return iot_sensor_category;
   }

   public void setIot_sensor_category(Integer iot_sensor_category) {
      this.iot_sensor_category = iot_sensor_category;
   }

   public Integer getNode_id() {
      return node_id;
   }

   public void setNode_id(Integer node_id) {
      this.node_id = node_id;
   }

   public String getSensor_device_id() {
      return sensor_device_id;
   }

   public void setSensor_device_id(String sensor_device_id) {
      this.sensor_device_id = sensor_device_id;
   }

   public Integer getPort_id() {
      return port_id;
   }

   public void setPort_id(Integer port_id) {
      this.port_id = port_id;
   }

   public Float getSdata() {
      return sdata;
   }

   public void setSdata(Float sdata) {
      this.sdata = sdata;
   }

   public Integer getSeq() {
      return seq;
   }

   public void setSeq(Integer seq) {
      this.seq = seq;
   }

   public Integer getIot_sensor_status() {
      return iot_sensor_status;
   }

   public void setIot_sensor_status(Integer iot_sensor_status) {
      this.iot_sensor_status = iot_sensor_status;
   }

   public Float getRequest_sdata() {
      return request_sdata;
   }

   public void setRequest_sdata(Float request_sdata) {
      this.request_sdata = request_sdata;
   }

   public Integer getSdata_degree() {
      return sdata_degree;
   }

   public void setSdata_degree(Integer sdata_degree) {
      this.sdata_degree = sdata_degree;
   }

   public String getFormula_up() {
      return formula_up;
   }

   public void setFormula_up(String formula_up) {
      this.formula_up = formula_up;
   }

   public String getFormula_down() {
      return formula_down;
   }

   public void setFormula_down(String formula_down) {
      this.formula_down = formula_down;
   }

   public Date getRegister_time() {
      return register_time;
   }

   public void setRegister_time(Date register_time) {
      this.register_time = register_time;
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

   public String getStr_sdata() {
      return str_sdata;
   }

   public void setStr_sdata(String str_sdata) {
      this.str_sdata = str_sdata;
   }

   public Integer getData_type() {
      return data_type;
   }

   public void setData_type(Integer data_type) {
      this.data_type = data_type;
   }

   public String getParam_names() {
      return param_names;
   }

   public void setParam_names(String param_names) {
      this.param_names = param_names;
   }

   public Integer getParam_type() {
      return param_type;
   }

   public void setParam_type(Integer param_type) {
      this.param_type = param_type;
   }

   public String getParam_config() {
      return param_config;
   }

   public void setParam_config(String param_config) {
      this.param_config = param_config;
   }

   public String getInfos() {
      return infos;
   }

   public void setInfos(String infos) {
      this.infos = infos;
   }

   public Integer getStore_strage() {
      return store_strage;
   }

   public void setStore_strage(Integer store_strage) {
      this.store_strage = store_strage;
   }
}