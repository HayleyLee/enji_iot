package com.enji_iot.util.Entity.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *@类:AlarmTriggerRecord
 *@作者:M
 */

public class AlarmTriggerRecord extends BaseBean {

   /**id*/
   private Integer id;

   /**user_id*/
   private Integer user_id;

   /**触发类型，短信，语音，微信等*/
   private Integer trigger_type;

   /**remark*/
   private String remark;

   /**报警内容*/
   private String content;

   /**atime*/
   private Date atime;

   public AlarmTriggerRecord(Integer id, Integer user_id, Integer trigger_type, String remark, String content, Date atime) {
      this.id = id;
      this.user_id = user_id;
      this.trigger_type = trigger_type;
      this.remark = remark;
      this.content = content;
      this.atime = atime;
   }

   public AlarmTriggerRecord(String sensor_name, Integer scene_id, Integer user_id, String start_time, String end_time, Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, Integer id1, Integer user_id1, Integer trigger_type, String remark, String content, Date atime) {
      super(sensor_name, scene_id, user_id, start_time, end_time, id, id_array, delete_flag, offset, limit, data);
      this.id = id1;
      this.user_id = user_id1;
      this.trigger_type = trigger_type;
      this.remark = remark;
      this.content = content;
      this.atime = atime;
   }

   public AlarmTriggerRecord(Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, Integer id1, Integer user_id, Integer trigger_type, String remark, String content, Date atime) {
      super(id, id_array, delete_flag, offset, limit, data);
      this.id = id1;
      this.user_id = user_id;
      this.trigger_type = trigger_type;
      this.remark = remark;
      this.content = content;
      this.atime = atime;
   }

   public AlarmTriggerRecord() {
   }

   @Override
   public Integer getId() {
      return id;
   }

   @Override
   public void setId(Integer id) {
      this.id = id;
   }

   @Override
   public Integer getUser_id() {
      return user_id;
   }

   @Override
   public void setUser_id(Integer user_id) {
      this.user_id = user_id;
   }

   public Integer getTrigger_type() {
      return trigger_type;
   }

   public void setTrigger_type(Integer trigger_type) {
      this.trigger_type = trigger_type;
   }

   public String getRemark() {
      return remark;
   }

   public void setRemark(String remark) {
      this.remark = remark;
   }

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
   }

   public Date getAtime() {
      return atime;
   }

   public void setAtime(Date atime) {
      this.atime = atime;
   }
}