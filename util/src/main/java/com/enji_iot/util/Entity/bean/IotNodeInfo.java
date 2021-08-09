package com.enji_iot.util.Entity.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *@类:IotNodeInfo
 *@作者:chenrj
 */

public class IotNodeInfo extends BaseBean {


   /**name*/
   private String name;

   /**device_code*/
   private String device_code;

   private Integer iot_node_type;

   /**协议类型*/
   private String iot_protocal_category;

   /**seq*/
   private Integer seq;

   /**scene_id*/
   private Integer scene_id;

   /**节点状态*/
   private Integer iot_node_status;

   /**禁用标志（超级管理员专属）*/
   private Integer disable_flag;

   /**img_id*/
   private Integer img_id;

   /**aid*/
   private Integer aid;

   /**atime*/
   private Date atime;

   /**mid*/
   private Integer mid;

   /**mtime*/
   private Date mtime;
   /**mtime*/
   private Integer template_id ;
   /**单位类型*/
   private Integer measure_unit_type;
   /**设备数据类型*/
   private Integer iot_node_data_type;
   /**存储策略*/
   private Integer store_strage;
   /**公式*/
   private String formula_up;
   /**公式*/
   private String formula_down;

   /**
    * 维保时间
    */
   private String maintenance_time;
   
   private String lonLat ;
   
   private String infos ;
   
   private Integer frequency ;

   public IotNodeInfo(String name, String device_code, Integer iot_node_type, String iot_protocal_category, Integer seq, Integer scene_id, Integer iot_node_status, Integer disable_flag, Integer img_id, Integer aid, Date atime, Integer mid, Date mtime, Integer template_id, Integer measure_unit_type, Integer iot_node_data_type, Integer store_strage, String formula_up, String formula_down, String maintenance_time, String lonLat, String infos, Integer frequency) {
      this.name = name;
      this.device_code = device_code;
      this.iot_node_type = iot_node_type;
      this.iot_protocal_category = iot_protocal_category;
      this.seq = seq;
      this.scene_id = scene_id;
      this.iot_node_status = iot_node_status;
      this.disable_flag = disable_flag;
      this.img_id = img_id;
      this.aid = aid;
      this.atime = atime;
      this.mid = mid;
      this.mtime = mtime;
      this.template_id = template_id;
      this.measure_unit_type = measure_unit_type;
      this.iot_node_data_type = iot_node_data_type;
      this.store_strage = store_strage;
      this.formula_up = formula_up;
      this.formula_down = formula_down;
      this.maintenance_time = maintenance_time;
      this.lonLat = lonLat;
      this.infos = infos;
      this.frequency = frequency;
   }

   public IotNodeInfo(String sensor_name, Integer scene_id, Integer user_id, String start_time, String end_time, Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, String name, String device_code, Integer iot_node_type, String iot_protocal_category, Integer seq, Integer scene_id1, Integer iot_node_status, Integer disable_flag, Integer img_id, Integer aid, Date atime, Integer mid, Date mtime, Integer template_id, Integer measure_unit_type, Integer iot_node_data_type, Integer store_strage, String formula_up, String formula_down, String maintenance_time, String lonLat, String infos, Integer frequency) {
      super(sensor_name, scene_id, user_id, start_time, end_time, id, id_array, delete_flag, offset, limit, data);
      this.name = name;
      this.device_code = device_code;
      this.iot_node_type = iot_node_type;
      this.iot_protocal_category = iot_protocal_category;
      this.seq = seq;
      this.scene_id = scene_id1;
      this.iot_node_status = iot_node_status;
      this.disable_flag = disable_flag;
      this.img_id = img_id;
      this.aid = aid;
      this.atime = atime;
      this.mid = mid;
      this.mtime = mtime;
      this.template_id = template_id;
      this.measure_unit_type = measure_unit_type;
      this.iot_node_data_type = iot_node_data_type;
      this.store_strage = store_strage;
      this.formula_up = formula_up;
      this.formula_down = formula_down;
      this.maintenance_time = maintenance_time;
      this.lonLat = lonLat;
      this.infos = infos;
      this.frequency = frequency;
   }

   public IotNodeInfo(Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, String name, String device_code, Integer iot_node_type, String iot_protocal_category, Integer seq, Integer scene_id, Integer iot_node_status, Integer disable_flag, Integer img_id, Integer aid, Date atime, Integer mid, Date mtime, Integer template_id, Integer measure_unit_type, Integer iot_node_data_type, Integer store_strage, String formula_up, String formula_down, String maintenance_time, String lonLat, String infos, Integer frequency) {
      super(id, id_array, delete_flag, offset, limit, data);
      this.name = name;
      this.device_code = device_code;
      this.iot_node_type = iot_node_type;
      this.iot_protocal_category = iot_protocal_category;
      this.seq = seq;
      this.scene_id = scene_id;
      this.iot_node_status = iot_node_status;
      this.disable_flag = disable_flag;
      this.img_id = img_id;
      this.aid = aid;
      this.atime = atime;
      this.mid = mid;
      this.mtime = mtime;
      this.template_id = template_id;
      this.measure_unit_type = measure_unit_type;
      this.iot_node_data_type = iot_node_data_type;
      this.store_strage = store_strage;
      this.formula_up = formula_up;
      this.formula_down = formula_down;
      this.maintenance_time = maintenance_time;
      this.lonLat = lonLat;
      this.infos = infos;
      this.frequency = frequency;
   }

   public IotNodeInfo() {
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

   public Integer getIot_node_type() {
      return iot_node_type;
   }

   public void setIot_node_type(Integer iot_node_type) {
      this.iot_node_type = iot_node_type;
   }

   public String getIot_protocal_category() {
      return iot_protocal_category;
   }

   public void setIot_protocal_category(String iot_protocal_category) {
      this.iot_protocal_category = iot_protocal_category;
   }

   public Integer getSeq() {
      return seq;
   }

   public void setSeq(Integer seq) {
      this.seq = seq;
   }

   @Override
   public Integer getScene_id() {
      return scene_id;
   }

   @Override
   public void setScene_id(Integer scene_id) {
      this.scene_id = scene_id;
   }

   public Integer getIot_node_status() {
      return iot_node_status;
   }

   public void setIot_node_status(Integer iot_node_status) {
      this.iot_node_status = iot_node_status;
   }

   public Integer getDisable_flag() {
      return disable_flag;
   }

   public void setDisable_flag(Integer disable_flag) {
      this.disable_flag = disable_flag;
   }

   public Integer getImg_id() {
      return img_id;
   }

   public void setImg_id(Integer img_id) {
      this.img_id = img_id;
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

   public Integer getTemplate_id() {
      return template_id;
   }

   public void setTemplate_id(Integer template_id) {
      this.template_id = template_id;
   }

   public Integer getMeasure_unit_type() {
      return measure_unit_type;
   }

   public void setMeasure_unit_type(Integer measure_unit_type) {
      this.measure_unit_type = measure_unit_type;
   }

   public Integer getIot_node_data_type() {
      return iot_node_data_type;
   }

   public void setIot_node_data_type(Integer iot_node_data_type) {
      this.iot_node_data_type = iot_node_data_type;
   }

   public Integer getStore_strage() {
      return store_strage;
   }

   public void setStore_strage(Integer store_strage) {
      this.store_strage = store_strage;
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

   public String getMaintenance_time() {
      return maintenance_time;
   }

   public void setMaintenance_time(String maintenance_time) {
      this.maintenance_time = maintenance_time;
   }

   public String getLonLat() {
      return lonLat;
   }

   public void setLonLat(String lonLat) {
      this.lonLat = lonLat;
   }

   public String getInfos() {
      return infos;
   }

   public void setInfos(String infos) {
      this.infos = infos;
   }

   public Integer getFrequency() {
      return frequency;
   }

   public void setFrequency(Integer frequency) {
      this.frequency = frequency;
   }
}