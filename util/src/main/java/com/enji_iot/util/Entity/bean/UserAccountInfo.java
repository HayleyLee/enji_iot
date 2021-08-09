package com.enji_iot.util.Entity.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *@类:UserAccountInfo
 *@作者:M
 */

public class UserAccountInfo extends BaseBean {

   /**id*/
   private Integer id;

   /**user_id*/
   private Integer user_id;

   /**金额，分为单位*/
   private Integer amount;

   /**短信数量*/
   private Integer sms_num;

   /**语音报警数量*/
   private Integer voice_num;

   /**delete_flag*/
   private Integer delete_flag;

   /**aid*/
   private Integer aid;

   /**atime*/
   private Date atime;

   /**mid*/
   private Integer mid;

   /**mtime*/
   private Date mtime;

   public UserAccountInfo(Integer id, Integer user_id, Integer amount, Integer sms_num, Integer voice_num, Integer delete_flag, Integer aid, Date atime, Integer mid, Date mtime) {
      this.id = id;
      this.user_id = user_id;
      this.amount = amount;
      this.sms_num = sms_num;
      this.voice_num = voice_num;
      this.delete_flag = delete_flag;
      this.aid = aid;
      this.atime = atime;
      this.mid = mid;
      this.mtime = mtime;
   }

   public UserAccountInfo(String sensor_name, Integer scene_id, Integer user_id, String start_time, String end_time, Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, Integer id1, Integer user_id1, Integer amount, Integer sms_num, Integer voice_num, Integer delete_flag1, Integer aid, Date atime, Integer mid, Date mtime) {
      super(sensor_name, scene_id, user_id, start_time, end_time, id, id_array, delete_flag, offset, limit, data);
      this.id = id1;
      this.user_id = user_id1;
      this.amount = amount;
      this.sms_num = sms_num;
      this.voice_num = voice_num;
      this.delete_flag = delete_flag1;
      this.aid = aid;
      this.atime = atime;
      this.mid = mid;
      this.mtime = mtime;
   }

   public UserAccountInfo(Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, Integer id1, Integer user_id, Integer amount, Integer sms_num, Integer voice_num, Integer delete_flag1, Integer aid, Date atime, Integer mid, Date mtime) {
      super(id, id_array, delete_flag, offset, limit, data);
      this.id = id1;
      this.user_id = user_id;
      this.amount = amount;
      this.sms_num = sms_num;
      this.voice_num = voice_num;
      this.delete_flag = delete_flag1;
      this.aid = aid;
      this.atime = atime;
      this.mid = mid;
      this.mtime = mtime;
   }

   public UserAccountInfo() {
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

   public Integer getAmount() {
      return amount;
   }

   public void setAmount(Integer amount) {
      this.amount = amount;
   }

   public Integer getSms_num() {
      return sms_num;
   }

   public void setSms_num(Integer sms_num) {
      this.sms_num = sms_num;
   }

   public Integer getVoice_num() {
      return voice_num;
   }

   public void setVoice_num(Integer voice_num) {
      this.voice_num = voice_num;
   }

   @Override
   public Integer getDelete_flag() {
      return delete_flag;
   }

   @Override
   public void setDelete_flag(Integer delete_flag) {
      this.delete_flag = delete_flag;
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