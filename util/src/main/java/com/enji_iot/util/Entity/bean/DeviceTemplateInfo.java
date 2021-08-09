package com.enji_iot.util.Entity.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *@类:DeviceTemplateInfo
 *@作者:M
 */

public class DeviceTemplateInfo extends BaseBean {

   /**名称*/
   private String name;

   /**aid*/
   private Integer aid;

   /**atime*/
   private Date atime;

   /**mid*/
   private Integer mid;

   /**mtime*/
   private Date mtime;

   public DeviceTemplateInfo(String name, Integer aid, Date atime, Integer mid, Date mtime) {
      this.name = name;
      this.aid = aid;
      this.atime = atime;
      this.mid = mid;
      this.mtime = mtime;
   }

   public DeviceTemplateInfo(String sensor_name, Integer scene_id, Integer user_id, String start_time, String end_time, Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, String name, Integer aid, Date atime, Integer mid, Date mtime) {
      super(sensor_name, scene_id, user_id, start_time, end_time, id, id_array, delete_flag, offset, limit, data);
      this.name = name;
      this.aid = aid;
      this.atime = atime;
      this.mid = mid;
      this.mtime = mtime;
   }

   public DeviceTemplateInfo(Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, String name, Integer aid, Date atime, Integer mid, Date mtime) {
      super(id, id_array, delete_flag, offset, limit, data);
      this.name = name;
      this.aid = aid;
      this.atime = atime;
      this.mid = mid;
      this.mtime = mtime;
   }

   public DeviceTemplateInfo() {
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
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