package com.enji_iot.util.Entity.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *@类:HkAccountInfo
 *@作者:M
 */

public class HkAccountInfo extends BaseBean {

   /**pk*/
   private Integer id;

   /**appKey*/
   private String appKey;

   /**secret*/
   private String secret;

   /**accessToken*/
   private String accessToken;

   /**aid*/
   private Integer aid;

   /**atime*/
   private Date atime;

   /**mid*/
   private Integer mid;

   /**mtime*/
   private Date mtime;

   public HkAccountInfo(Integer id, String appKey, String secret, String accessToken, Integer aid, Date atime, Integer mid, Date mtime) {
      this.id = id;
      this.appKey = appKey;
      this.secret = secret;
      this.accessToken = accessToken;
      this.aid = aid;
      this.atime = atime;
      this.mid = mid;
      this.mtime = mtime;
   }

   public HkAccountInfo(String sensor_name, Integer scene_id, Integer user_id, String start_time, String end_time, Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, Integer id1, String appKey, String secret, String accessToken, Integer aid, Date atime, Integer mid, Date mtime) {
      super(sensor_name, scene_id, user_id, start_time, end_time, id, id_array, delete_flag, offset, limit, data);
      this.id = id1;
      this.appKey = appKey;
      this.secret = secret;
      this.accessToken = accessToken;
      this.aid = aid;
      this.atime = atime;
      this.mid = mid;
      this.mtime = mtime;
   }

   public HkAccountInfo(Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, Integer id1, String appKey, String secret, String accessToken, Integer aid, Date atime, Integer mid, Date mtime) {
      super(id, id_array, delete_flag, offset, limit, data);
      this.id = id1;
      this.appKey = appKey;
      this.secret = secret;
      this.accessToken = accessToken;
      this.aid = aid;
      this.atime = atime;
      this.mid = mid;
      this.mtime = mtime;
   }

   public HkAccountInfo() {
   }

   @Override
   public Integer getId() {
      return id;
   }

   @Override
   public void setId(Integer id) {
      this.id = id;
   }

   public String getAppKey() {
      return appKey;
   }

   public void setAppKey(String appKey) {
      this.appKey = appKey;
   }

   public String getSecret() {
      return secret;
   }

   public void setSecret(String secret) {
      this.secret = secret;
   }

   public String getAccessToken() {
      return accessToken;
   }

   public void setAccessToken(String accessToken) {
      this.accessToken = accessToken;
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