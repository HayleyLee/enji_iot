package com.enji_iot.util.Entity.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *@类:IotHistorySensorData
 *@作者:chenrj
 */

public class IotHistorySensorData extends BaseBean {

   /**id*/
   private Integer id;

   /**sensor_id*/
   private Integer sensor_id;

   /**sdata*/
   private String sdata;

   /**aid*/
   private Integer aid;

   /**atime*/
   private Date atime;

   public IotHistorySensorData(Integer id, Integer sensor_id, String sdata, Integer aid, Date atime) {
      this.id = id;
      this.sensor_id = sensor_id;
      this.sdata = sdata;
      this.aid = aid;
      this.atime = atime;
   }

   public IotHistorySensorData(String sensor_name, Integer scene_id, Integer user_id, String start_time, String end_time, Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, Integer id1, Integer sensor_id, String sdata, Integer aid, Date atime) {
      super(sensor_name, scene_id, user_id, start_time, end_time, id, id_array, delete_flag, offset, limit, data);
      this.id = id1;
      this.sensor_id = sensor_id;
      this.sdata = sdata;
      this.aid = aid;
      this.atime = atime;
   }

   public IotHistorySensorData(Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, Integer id1, Integer sensor_id, String sdata, Integer aid, Date atime) {
      super(id, id_array, delete_flag, offset, limit, data);
      this.id = id1;
      this.sensor_id = sensor_id;
      this.sdata = sdata;
      this.aid = aid;
      this.atime = atime;
   }

   public IotHistorySensorData() {
   }

   @Override
   public Integer getId() {
      return id;
   }

   @Override
   public void setId(Integer id) {
      this.id = id;
   }

   public Integer getSensor_id() {
      return sensor_id;
   }

   public void setSensor_id(Integer sensor_id) {
      this.sensor_id = sensor_id;
   }

   public String getSdata() {
      return sdata;
   }

   public void setSdata(String sdata) {
      this.sdata = sdata;
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