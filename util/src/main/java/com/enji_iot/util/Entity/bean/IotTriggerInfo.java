package com.enji_iot.util.Entity.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *@类:IotTriggerInfo
 *@作者:chenrj
 */

public class IotTriggerInfo extends BaseBean {

   /**name*/
   private String name;

   /**源传感器ID*/
   private Integer from_sensor_id;

   /**目标传感器ID*/
   private Integer to_sensor_id;

   /**触发条件类型*/
   private Integer iot_trigger_condition_type;

   /**触发动作类型*/
   private String iot_trigger_action_type;

   /**触发动作参数*/
   private String action_params;

   /**seq*/
   private Integer seq;

   /**条件参数*/
   private String condition_params;

   /**报警级别*/
   private Integer iot_trigger_alarm_level;

   /**是否报警标志*/
   private Integer iot_trigger_alarm_flag;

   /**启停状态*/
   private Integer iot_trigger_status;

   /**触发间隔*/
   private Integer trigger_inteval_time;

   /** 最近触发时间*/
   private Date last_trigger_time;

   /**aid*/
   private Integer aid;

   /**atime*/
   private Date atime;

   /**mid*/
   private Integer mid;

   /**mtime*/
   private Date mtime;
   
   private Integer recovery ;

   public IotTriggerInfo(String name, Integer from_sensor_id, Integer to_sensor_id, Integer iot_trigger_condition_type, String iot_trigger_action_type, String action_params, Integer seq, String condition_params, Integer iot_trigger_alarm_level, Integer iot_trigger_alarm_flag, Integer iot_trigger_status, Integer trigger_inteval_time, Date last_trigger_time, Integer aid, Date atime, Integer mid, Date mtime, Integer recovery) {
      this.name = name;
      this.from_sensor_id = from_sensor_id;
      this.to_sensor_id = to_sensor_id;
      this.iot_trigger_condition_type = iot_trigger_condition_type;
      this.iot_trigger_action_type = iot_trigger_action_type;
      this.action_params = action_params;
      this.seq = seq;
      this.condition_params = condition_params;
      this.iot_trigger_alarm_level = iot_trigger_alarm_level;
      this.iot_trigger_alarm_flag = iot_trigger_alarm_flag;
      this.iot_trigger_status = iot_trigger_status;
      this.trigger_inteval_time = trigger_inteval_time;
      this.last_trigger_time = last_trigger_time;
      this.aid = aid;
      this.atime = atime;
      this.mid = mid;
      this.mtime = mtime;
      this.recovery = recovery;
   }

   public IotTriggerInfo(String sensor_name, Integer scene_id, Integer user_id, String start_time, String end_time, Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, String name, Integer from_sensor_id, Integer to_sensor_id, Integer iot_trigger_condition_type, String iot_trigger_action_type, String action_params, Integer seq, String condition_params, Integer iot_trigger_alarm_level, Integer iot_trigger_alarm_flag, Integer iot_trigger_status, Integer trigger_inteval_time, Date last_trigger_time, Integer aid, Date atime, Integer mid, Date mtime, Integer recovery) {
      super(sensor_name, scene_id, user_id, start_time, end_time, id, id_array, delete_flag, offset, limit, data);
      this.name = name;
      this.from_sensor_id = from_sensor_id;
      this.to_sensor_id = to_sensor_id;
      this.iot_trigger_condition_type = iot_trigger_condition_type;
      this.iot_trigger_action_type = iot_trigger_action_type;
      this.action_params = action_params;
      this.seq = seq;
      this.condition_params = condition_params;
      this.iot_trigger_alarm_level = iot_trigger_alarm_level;
      this.iot_trigger_alarm_flag = iot_trigger_alarm_flag;
      this.iot_trigger_status = iot_trigger_status;
      this.trigger_inteval_time = trigger_inteval_time;
      this.last_trigger_time = last_trigger_time;
      this.aid = aid;
      this.atime = atime;
      this.mid = mid;
      this.mtime = mtime;
      this.recovery = recovery;
   }

   public IotTriggerInfo(Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, String name, Integer from_sensor_id, Integer to_sensor_id, Integer iot_trigger_condition_type, String iot_trigger_action_type, String action_params, Integer seq, String condition_params, Integer iot_trigger_alarm_level, Integer iot_trigger_alarm_flag, Integer iot_trigger_status, Integer trigger_inteval_time, Date last_trigger_time, Integer aid, Date atime, Integer mid, Date mtime, Integer recovery) {
      super(id, id_array, delete_flag, offset, limit, data);
      this.name = name;
      this.from_sensor_id = from_sensor_id;
      this.to_sensor_id = to_sensor_id;
      this.iot_trigger_condition_type = iot_trigger_condition_type;
      this.iot_trigger_action_type = iot_trigger_action_type;
      this.action_params = action_params;
      this.seq = seq;
      this.condition_params = condition_params;
      this.iot_trigger_alarm_level = iot_trigger_alarm_level;
      this.iot_trigger_alarm_flag = iot_trigger_alarm_flag;
      this.iot_trigger_status = iot_trigger_status;
      this.trigger_inteval_time = trigger_inteval_time;
      this.last_trigger_time = last_trigger_time;
      this.aid = aid;
      this.atime = atime;
      this.mid = mid;
      this.mtime = mtime;
      this.recovery = recovery;
   }

   public IotTriggerInfo() {
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Integer getFrom_sensor_id() {
      return from_sensor_id;
   }

   public void setFrom_sensor_id(Integer from_sensor_id) {
      this.from_sensor_id = from_sensor_id;
   }

   public Integer getTo_sensor_id() {
      return to_sensor_id;
   }

   public void setTo_sensor_id(Integer to_sensor_id) {
      this.to_sensor_id = to_sensor_id;
   }

   public Integer getIot_trigger_condition_type() {
      return iot_trigger_condition_type;
   }

   public void setIot_trigger_condition_type(Integer iot_trigger_condition_type) {
      this.iot_trigger_condition_type = iot_trigger_condition_type;
   }

   public String getIot_trigger_action_type() {
      return iot_trigger_action_type;
   }

   public void setIot_trigger_action_type(String iot_trigger_action_type) {
      this.iot_trigger_action_type = iot_trigger_action_type;
   }

   public String getAction_params() {
      return action_params;
   }

   public void setAction_params(String action_params) {
      this.action_params = action_params;
   }

   public Integer getSeq() {
      return seq;
   }

   public void setSeq(Integer seq) {
      this.seq = seq;
   }

   public String getCondition_params() {
      return condition_params;
   }

   public void setCondition_params(String condition_params) {
      this.condition_params = condition_params;
   }

   public Integer getIot_trigger_alarm_level() {
      return iot_trigger_alarm_level;
   }

   public void setIot_trigger_alarm_level(Integer iot_trigger_alarm_level) {
      this.iot_trigger_alarm_level = iot_trigger_alarm_level;
   }

   public Integer getIot_trigger_alarm_flag() {
      return iot_trigger_alarm_flag;
   }

   public void setIot_trigger_alarm_flag(Integer iot_trigger_alarm_flag) {
      this.iot_trigger_alarm_flag = iot_trigger_alarm_flag;
   }

   public Integer getIot_trigger_status() {
      return iot_trigger_status;
   }

   public void setIot_trigger_status(Integer iot_trigger_status) {
      this.iot_trigger_status = iot_trigger_status;
   }

   public Integer getTrigger_inteval_time() {
      return trigger_inteval_time;
   }

   public void setTrigger_inteval_time(Integer trigger_inteval_time) {
      this.trigger_inteval_time = trigger_inteval_time;
   }

   public Date getLast_trigger_time() {
      return last_trigger_time;
   }

   public void setLast_trigger_time(Date last_trigger_time) {
      this.last_trigger_time = last_trigger_time;
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

   public Integer getRecovery() {
      return recovery;
   }

   public void setRecovery(Integer recovery) {
      this.recovery = recovery;
   }
}