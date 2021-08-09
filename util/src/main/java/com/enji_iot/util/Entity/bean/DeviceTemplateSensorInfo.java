package com.enji_iot.util.Entity.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *@类:DeviceTemplateSensorInfo
 *@作者:M
 */

public class DeviceTemplateSensorInfo extends BaseBean {

   /**name*/
   private String name;

   /**传感器单位*/
   private Integer measure_unit_type;

   /**类型：连续型、开关性等*/
   private Integer iot_sensor_type;

   /**种类：温度、IO、开关*/
   private Integer iot_sensor_category;

   /**device_template_id*/
   private Integer device_template_id;

   /**sensor_device_id*/
   private Integer sensor_device_id;

   /**port_id*/
   private Integer port_id;

   /**seq*/
   private Integer seq;

   /**sdata_degree*/
   private Integer sdata_degree;

   /**formula_up*/
   private String formula_up;

   /**formula_down*/
   private String formula_down;

   /**aid*/
   private Integer aid;

   /**atime*/
   private Date atime;

   /**mid*/
   private Integer mid;

   /**mtime*/
   private Date mtime;
   
   /** 数据类型  0 common 1， 配置 */
   private Integer data_type  ;

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

   public DeviceTemplateSensorInfo(String name, Integer measure_unit_type, Integer iot_sensor_type, Integer iot_sensor_category, Integer device_template_id, Integer sensor_device_id, Integer port_id, Integer seq, Integer sdata_degree, String formula_up, String formula_down, Integer aid, Date atime, Integer mid, Date mtime, Integer data_type, String param_names, Integer param_type, String param_config, String infos) {
      this.name = name;
      this.measure_unit_type = measure_unit_type;
      this.iot_sensor_type = iot_sensor_type;
      this.iot_sensor_category = iot_sensor_category;
      this.device_template_id = device_template_id;
      this.sensor_device_id = sensor_device_id;
      this.port_id = port_id;
      this.seq = seq;
      this.sdata_degree = sdata_degree;
      this.formula_up = formula_up;
      this.formula_down = formula_down;
      this.aid = aid;
      this.atime = atime;
      this.mid = mid;
      this.mtime = mtime;
      this.data_type = data_type;
      this.param_names = param_names;
      this.param_type = param_type;
      this.param_config = param_config;
      this.infos = infos;
   }

   public DeviceTemplateSensorInfo(String sensor_name, Integer scene_id, Integer user_id, String start_time, String end_time, Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, String name, Integer measure_unit_type, Integer iot_sensor_type, Integer iot_sensor_category, Integer device_template_id, Integer sensor_device_id, Integer port_id, Integer seq, Integer sdata_degree, String formula_up, String formula_down, Integer aid, Date atime, Integer mid, Date mtime, Integer data_type, String param_names, Integer param_type, String param_config, String infos) {
      super(sensor_name, scene_id, user_id, start_time, end_time, id, id_array, delete_flag, offset, limit, data);
      this.name = name;
      this.measure_unit_type = measure_unit_type;
      this.iot_sensor_type = iot_sensor_type;
      this.iot_sensor_category = iot_sensor_category;
      this.device_template_id = device_template_id;
      this.sensor_device_id = sensor_device_id;
      this.port_id = port_id;
      this.seq = seq;
      this.sdata_degree = sdata_degree;
      this.formula_up = formula_up;
      this.formula_down = formula_down;
      this.aid = aid;
      this.atime = atime;
      this.mid = mid;
      this.mtime = mtime;
      this.data_type = data_type;
      this.param_names = param_names;
      this.param_type = param_type;
      this.param_config = param_config;
      this.infos = infos;
   }

   public DeviceTemplateSensorInfo(Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, String name, Integer measure_unit_type, Integer iot_sensor_type, Integer iot_sensor_category, Integer device_template_id, Integer sensor_device_id, Integer port_id, Integer seq, Integer sdata_degree, String formula_up, String formula_down, Integer aid, Date atime, Integer mid, Date mtime, Integer data_type, String param_names, Integer param_type, String param_config, String infos) {
      super(id, id_array, delete_flag, offset, limit, data);
      this.name = name;
      this.measure_unit_type = measure_unit_type;
      this.iot_sensor_type = iot_sensor_type;
      this.iot_sensor_category = iot_sensor_category;
      this.device_template_id = device_template_id;
      this.sensor_device_id = sensor_device_id;
      this.port_id = port_id;
      this.seq = seq;
      this.sdata_degree = sdata_degree;
      this.formula_up = formula_up;
      this.formula_down = formula_down;
      this.aid = aid;
      this.atime = atime;
      this.mid = mid;
      this.mtime = mtime;
      this.data_type = data_type;
      this.param_names = param_names;
      this.param_type = param_type;
      this.param_config = param_config;
      this.infos = infos;
   }

   public DeviceTemplateSensorInfo() {
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

   public Integer getDevice_template_id() {
      return device_template_id;
   }

   public void setDevice_template_id(Integer device_template_id) {
      this.device_template_id = device_template_id;
   }

   public Integer getSensor_device_id() {
      return sensor_device_id;
   }

   public void setSensor_device_id(Integer sensor_device_id) {
      this.sensor_device_id = sensor_device_id;
   }

   public Integer getPort_id() {
      return port_id;
   }

   public void setPort_id(Integer port_id) {
      this.port_id = port_id;
   }

   public Integer getSeq() {
      return seq;
   }

   public void setSeq(Integer seq) {
      this.seq = seq;
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
}