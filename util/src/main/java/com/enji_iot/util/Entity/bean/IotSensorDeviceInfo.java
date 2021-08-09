package com.enji_iot.util.Entity.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *@类:IotSensorDeviceInfo
 *@作者:chenrj
 */

public class IotSensorDeviceInfo extends BaseBean {

   /**name*/
   private String name;

   /**node_id*/
   private String deviceCode;

   /**datas*/
   private Integer datas;

   /**datas*/
   private Date atime;

   /**datas*/
   private Date mtime;

   public IotSensorDeviceInfo(String name, String deviceCode, Integer datas, Date atime, Date mtime) {
      this.name = name;
      this.deviceCode = deviceCode;
      this.datas = datas;
      this.atime = atime;
      this.mtime = mtime;
   }

   public IotSensorDeviceInfo(String sensor_name, Integer scene_id, Integer user_id, String start_time, String end_time, Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, String name, String deviceCode, Integer datas, Date atime, Date mtime) {
      super(sensor_name, scene_id, user_id, start_time, end_time, id, id_array, delete_flag, offset, limit, data);
      this.name = name;
      this.deviceCode = deviceCode;
      this.datas = datas;
      this.atime = atime;
      this.mtime = mtime;
   }

   public IotSensorDeviceInfo(Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, String name, String deviceCode, Integer datas, Date atime, Date mtime) {
      super(id, id_array, delete_flag, offset, limit, data);
      this.name = name;
      this.deviceCode = deviceCode;
      this.datas = datas;
      this.atime = atime;
      this.mtime = mtime;
   }

   public IotSensorDeviceInfo() {
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDeviceCode() {
      return deviceCode;
   }

   public void setDeviceCode(String deviceCode) {
      this.deviceCode = deviceCode;
   }

   public Integer getDatas() {
      return datas;
   }

   public void setDatas(Integer datas) {
      this.datas = datas;
   }

   public Date getAtime() {
      return atime;
   }

   public void setAtime(Date atime) {
      this.atime = atime;
   }

   public Date getMtime() {
      return mtime;
   }

   public void setMtime(Date mtime) {
      this.mtime = mtime;
   }
}