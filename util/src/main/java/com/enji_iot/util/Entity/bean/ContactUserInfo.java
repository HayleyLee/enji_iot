package com.enji_iot.util.Entity.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *@类:ContactUserInfo
 *@作者:chenrj
 */

public class ContactUserInfo extends BaseBean {

   /**name*/
   private String name;

   /**sex*/
   private String sex;

   /**phone*/
   private String phone;

   /**email*/
   private String email;

   /**address*/
   private String address;

   /**user_id*/
   private Integer user_id;


   /**aid*/
   private Integer aid;

   /**atime*/
   private Date atime;

   /**mid*/
   private Integer mid;

   /**mtime*/
   private Date mtime;
   
   private String wx_key ;
   
   private String wx_img ;

   public ContactUserInfo() {
   }

   public ContactUserInfo(String name, String sex, String phone, String email, String address, Integer user_id, Integer aid, Date atime, Integer mid, Date mtime, String wx_key, String wx_img) {
      this.name = name;
      this.sex = sex;
      this.phone = phone;
      this.email = email;
      this.address = address;
      this.user_id = user_id;
      this.aid = aid;
      this.atime = atime;
      this.mid = mid;
      this.mtime = mtime;
      this.wx_key = wx_key;
      this.wx_img = wx_img;
   }

   public ContactUserInfo(String sensor_name, Integer scene_id, Integer user_id, String start_time, String end_time, Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, String name, String sex, String phone, String email, String address, Integer user_id1, Integer aid, Date atime, Integer mid, Date mtime, String wx_key, String wx_img) {
      super(sensor_name, scene_id, user_id, start_time, end_time, id, id_array, delete_flag, offset, limit, data);
      this.name = name;
      this.sex = sex;
      this.phone = phone;
      this.email = email;
      this.address = address;
      this.user_id = user_id1;
      this.aid = aid;
      this.atime = atime;
      this.mid = mid;
      this.mtime = mtime;
      this.wx_key = wx_key;
      this.wx_img = wx_img;
   }

   public ContactUserInfo(Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, String name, String sex, String phone, String email, String address, Integer user_id, Integer aid, Date atime, Integer mid, Date mtime, String wx_key, String wx_img) {
      super(id, id_array, delete_flag, offset, limit, data);
      this.name = name;
      this.sex = sex;
      this.phone = phone;
      this.email = email;
      this.address = address;
      this.user_id = user_id;
      this.aid = aid;
      this.atime = atime;
      this.mid = mid;
      this.mtime = mtime;
      this.wx_key = wx_key;
      this.wx_img = wx_img;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getSex() {
      return sex;
   }

   public void setSex(String sex) {
      this.sex = sex;
   }

   public String getPhone() {
      return phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   @Override
   public Integer getUser_id() {
      return user_id;
   }

   @Override
   public void setUser_id(Integer user_id) {
      this.user_id = user_id;
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

   public String getWx_key() {
      return wx_key;
   }

   public void setWx_key(String wx_key) {
      this.wx_key = wx_key;
   }

   public String getWx_img() {
      return wx_img;
   }

   public void setWx_img(String wx_img) {
      this.wx_img = wx_img;
   }
}