package com.enji_iot.util.Entity.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *@类:IotAlarmInfo
 *@作者:chenrj
 */

public class IotAlarmInfo extends BaseBean {

	private String name ;

   /**description*/
   private String description;

   /**node_id*/
   private Integer node_id;

   /**报警级别*/
   private Integer iot_trigger_alarm_level;

   private Float alarm_sdata ;
   
   /**sdata*/
   private Float sdata;

   /**处理标志*/
   private Integer iot_alarm_process_status;

   /**aid*/
   private Integer aid;

   /**atime*/
   private Date atime;

   /**mid*/
   private Integer mid;

   /**mtime*/
   private Date mtime;

   public IotAlarmInfo(String name, String description, Integer node_id, Integer iot_trigger_alarm_level, Float alarm_sdata, Float sdata, Integer iot_alarm_process_status, Integer aid, Date atime, Integer mid, Date mtime) {
      this.name = name;
      this.description = description;
      this.node_id = node_id;
      this.iot_trigger_alarm_level = iot_trigger_alarm_level;
      this.alarm_sdata = alarm_sdata;
      this.sdata = sdata;
      this.iot_alarm_process_status = iot_alarm_process_status;
      this.aid = aid;
      this.atime = atime;
      this.mid = mid;
      this.mtime = mtime;
   }

   public IotAlarmInfo(String sensor_name, Integer scene_id, Integer user_id, String start_time, String end_time, Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, String name, String description, Integer node_id, Integer iot_trigger_alarm_level, Float alarm_sdata, Float sdata, Integer iot_alarm_process_status, Integer aid, Date atime, Integer mid, Date mtime) {
      super(sensor_name, scene_id, user_id, start_time, end_time, id, id_array, delete_flag, offset, limit, data);
      this.name = name;
      this.description = description;
      this.node_id = node_id;
      this.iot_trigger_alarm_level = iot_trigger_alarm_level;
      this.alarm_sdata = alarm_sdata;
      this.sdata = sdata;
      this.iot_alarm_process_status = iot_alarm_process_status;
      this.aid = aid;
      this.atime = atime;
      this.mid = mid;
      this.mtime = mtime;
   }

   public IotAlarmInfo(Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, String name, String description, Integer node_id, Integer iot_trigger_alarm_level, Float alarm_sdata, Float sdata, Integer iot_alarm_process_status, Integer aid, Date atime, Integer mid, Date mtime) {
      super(id, id_array, delete_flag, offset, limit, data);
      this.name = name;
      this.description = description;
      this.node_id = node_id;
      this.iot_trigger_alarm_level = iot_trigger_alarm_level;
      this.alarm_sdata = alarm_sdata;
      this.sdata = sdata;
      this.iot_alarm_process_status = iot_alarm_process_status;
      this.aid = aid;
      this.atime = atime;
      this.mid = mid;
      this.mtime = mtime;
   }

   public IotAlarmInfo() {
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public Integer getNode_id() {
      return node_id;
   }

   public void setNode_id(Integer node_id) {
      this.node_id = node_id;
   }

   public Integer getIot_trigger_alarm_level() {
      return iot_trigger_alarm_level;
   }

   public void setIot_trigger_alarm_level(Integer iot_trigger_alarm_level) {
      this.iot_trigger_alarm_level = iot_trigger_alarm_level;
   }

   public Float getAlarm_sdata() {
      return alarm_sdata;
   }

   public void setAlarm_sdata(Float alarm_sdata) {
      this.alarm_sdata = alarm_sdata;
   }

   public Float getSdata() {
      return sdata;
   }

   public void setSdata(Float sdata) {
      this.sdata = sdata;
   }

   public Integer getIot_alarm_process_status() {
      return iot_alarm_process_status;
   }

   public void setIot_alarm_process_status(Integer iot_alarm_process_status) {
      this.iot_alarm_process_status = iot_alarm_process_status;
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
}