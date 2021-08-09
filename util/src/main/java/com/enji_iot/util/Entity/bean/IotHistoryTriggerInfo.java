package com.enji_iot.util.Entity.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *@类:IotHistoryTriggerInfo
 *@作者:chenrj
 */

public class IotHistoryTriggerInfo extends BaseBean {

   /**id*/
   private Integer id;

   /**设备传感点数据*/
   private Float val;

   /**description*/
   private String description;
   /**设备传感点单位*/
   private String unit;
   /**设备传感点昵称*/
   private String iot_sensor_name;

   /**name*/
   private String name;


   /**触发条件类型*/
   private Integer iot_trigger_condition_type;

   /**触发动作类型*/
   private String iot_trigger_action_type;

   /**触发动作参数*/
   private String action_params;

   private String trigger_value ;
   
   /**条件参数*/
   private String condition_params;

   /**报警级别*/
   private Integer iot_trigger_alarm_level;

   /**是否报警标志*/
   private Integer iot_trigger_alarm_flag;

   /**触发间隔*/
   private Integer trigger_inteval_time;

   /**aid*/
   private Integer aid;

   /**atime*/
   private Date atime;

   public IotHistoryTriggerInfo(Integer id, Float val, String description, String unit, String iot_sensor_name, String name, Integer iot_trigger_condition_type, String iot_trigger_action_type, String action_params, String trigger_value, String condition_params, Integer iot_trigger_alarm_level, Integer iot_trigger_alarm_flag, Integer trigger_inteval_time, Integer aid, Date atime) {
      this.id = id;
      this.val = val;
      this.description = description;
      this.unit = unit;
      this.iot_sensor_name = iot_sensor_name;
      this.name = name;
      this.iot_trigger_condition_type = iot_trigger_condition_type;
      this.iot_trigger_action_type = iot_trigger_action_type;
      this.action_params = action_params;
      this.trigger_value = trigger_value;
      this.condition_params = condition_params;
      this.iot_trigger_alarm_level = iot_trigger_alarm_level;
      this.iot_trigger_alarm_flag = iot_trigger_alarm_flag;
      this.trigger_inteval_time = trigger_inteval_time;
      this.aid = aid;
      this.atime = atime;
   }

   public IotHistoryTriggerInfo(String sensor_name, Integer scene_id, Integer user_id, String start_time, String end_time, Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, Integer id1, Float val, String description, String unit, String iot_sensor_name, String name, Integer iot_trigger_condition_type, String iot_trigger_action_type, String action_params, String trigger_value, String condition_params, Integer iot_trigger_alarm_level, Integer iot_trigger_alarm_flag, Integer trigger_inteval_time, Integer aid, Date atime) {
      super(sensor_name, scene_id, user_id, start_time, end_time, id, id_array, delete_flag, offset, limit, data);
      this.id = id1;
      this.val = val;
      this.description = description;
      this.unit = unit;
      this.iot_sensor_name = iot_sensor_name;
      this.name = name;
      this.iot_trigger_condition_type = iot_trigger_condition_type;
      this.iot_trigger_action_type = iot_trigger_action_type;
      this.action_params = action_params;
      this.trigger_value = trigger_value;
      this.condition_params = condition_params;
      this.iot_trigger_alarm_level = iot_trigger_alarm_level;
      this.iot_trigger_alarm_flag = iot_trigger_alarm_flag;
      this.trigger_inteval_time = trigger_inteval_time;
      this.aid = aid;
      this.atime = atime;
   }

   public IotHistoryTriggerInfo(Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, Integer id1, Float val, String description, String unit, String iot_sensor_name, String name, Integer iot_trigger_condition_type, String iot_trigger_action_type, String action_params, String trigger_value, String condition_params, Integer iot_trigger_alarm_level, Integer iot_trigger_alarm_flag, Integer trigger_inteval_time, Integer aid, Date atime) {
      super(id, id_array, delete_flag, offset, limit, data);
      this.id = id1;
      this.val = val;
      this.description = description;
      this.unit = unit;
      this.iot_sensor_name = iot_sensor_name;
      this.name = name;
      this.iot_trigger_condition_type = iot_trigger_condition_type;
      this.iot_trigger_action_type = iot_trigger_action_type;
      this.action_params = action_params;
      this.trigger_value = trigger_value;
      this.condition_params = condition_params;
      this.iot_trigger_alarm_level = iot_trigger_alarm_level;
      this.iot_trigger_alarm_flag = iot_trigger_alarm_flag;
      this.trigger_inteval_time = trigger_inteval_time;
      this.aid = aid;
      this.atime = atime;
   }

   public IotHistoryTriggerInfo() {
   }

   @Override
   public Integer getId() {
      return id;
   }

   @Override
   public void setId(Integer id) {
      this.id = id;
   }

   public Float getVal() {
      return val;
   }

   public void setVal(Float val) {
      this.val = val;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getUnit() {
      return unit;
   }

   public void setUnit(String unit) {
      this.unit = unit;
   }

   public String getIot_sensor_name() {
      return iot_sensor_name;
   }

   public void setIot_sensor_name(String iot_sensor_name) {
      this.iot_sensor_name = iot_sensor_name;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
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

   public String getTrigger_value() {
      return trigger_value;
   }

   public void setTrigger_value(String trigger_value) {
      this.trigger_value = trigger_value;
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

   public Integer getTrigger_inteval_time() {
      return trigger_inteval_time;
   }

   public void setTrigger_inteval_time(Integer trigger_inteval_time) {
      this.trigger_inteval_time = trigger_inteval_time;
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
}