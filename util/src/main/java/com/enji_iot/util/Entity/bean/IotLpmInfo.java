package com.enji_iot.util.Entity.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *@类:IotLpmInfo
 *@作者:chenrj
 */

public class IotLpmInfo extends BaseBean {

   /**name*/
   private String name;

   /**ip*/
   private String ip;

   /**port*/
   private String port;

   /**lpm_key*/
   private String lpm_key;

   /**aid*/
   private Integer aid;

   /**atime*/
   private Date atime;

   /**mid*/
   private Integer mid;

   /**mtime*/
   private Date mtime;

   public IotLpmInfo(String name, String ip, String port, String lpm_key, Integer aid, Date atime, Integer mid, Date mtime) {
      this.name = name;
      this.ip = ip;
      this.port = port;
      this.lpm_key = lpm_key;
      this.aid = aid;
      this.atime = atime;
      this.mid = mid;
      this.mtime = mtime;
   }

   public IotLpmInfo(String sensor_name, Integer scene_id, Integer user_id, String start_time, String end_time, Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, String name, String ip, String port, String lpm_key, Integer aid, Date atime, Integer mid, Date mtime) {
      super(sensor_name, scene_id, user_id, start_time, end_time, id, id_array, delete_flag, offset, limit, data);
      this.name = name;
      this.ip = ip;
      this.port = port;
      this.lpm_key = lpm_key;
      this.aid = aid;
      this.atime = atime;
      this.mid = mid;
      this.mtime = mtime;
   }

   public IotLpmInfo(Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, String name, String ip, String port, String lpm_key, Integer aid, Date atime, Integer mid, Date mtime) {
      super(id, id_array, delete_flag, offset, limit, data);
      this.name = name;
      this.ip = ip;
      this.port = port;
      this.lpm_key = lpm_key;
      this.aid = aid;
      this.atime = atime;
      this.mid = mid;
      this.mtime = mtime;
   }

   public IotLpmInfo() {
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getIp() {
      return ip;
   }

   public void setIp(String ip) {
      this.ip = ip;
   }

   public String getPort() {
      return port;
   }

   public void setPort(String port) {
      this.port = port;
   }

   public String getLpm_key() {
      return lpm_key;
   }

   public void setLpm_key(String lpm_key) {
      this.lpm_key = lpm_key;
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