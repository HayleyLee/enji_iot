package com.enji_iot.util.Entity.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *@类:IotVideoRecord
 *@作者:M
 */

public class IotVideoRecord extends BaseBean {

   /**pk*/
   private Integer id;

   /**视频id*/
   private Integer video_id;

   /**名称*/
   private String name;

   /**开始时间*/
   private Date start_date;

   /**结束时间*/
   private Date end_date;

   /**atime*/
   private Date atime;

   public IotVideoRecord(Integer id, Integer video_id, String name, Date start_date, Date end_date, Date atime) {
      this.id = id;
      this.video_id = video_id;
      this.name = name;
      this.start_date = start_date;
      this.end_date = end_date;
      this.atime = atime;
   }

   public IotVideoRecord(String sensor_name, Integer scene_id, Integer user_id, String start_time, String end_time, Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, Integer id1, Integer video_id, String name, Date start_date, Date end_date, Date atime) {
      super(sensor_name, scene_id, user_id, start_time, end_time, id, id_array, delete_flag, offset, limit, data);
      this.id = id1;
      this.video_id = video_id;
      this.name = name;
      this.start_date = start_date;
      this.end_date = end_date;
      this.atime = atime;
   }

   public IotVideoRecord(Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, Integer id1, Integer video_id, String name, Date start_date, Date end_date, Date atime) {
      super(id, id_array, delete_flag, offset, limit, data);
      this.id = id1;
      this.video_id = video_id;
      this.name = name;
      this.start_date = start_date;
      this.end_date = end_date;
      this.atime = atime;
   }

   public IotVideoRecord() {
   }

   @Override
   public Integer getId() {
      return id;
   }

   @Override
   public void setId(Integer id) {
      this.id = id;
   }

   public Integer getVideo_id() {
      return video_id;
   }

   public void setVideo_id(Integer video_id) {
      this.video_id = video_id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Date getStart_date() {
      return start_date;
   }

   public void setStart_date(Date start_date) {
      this.start_date = start_date;
   }

   public Date getEnd_date() {
      return end_date;
   }

   public void setEnd_date(Date end_date) {
      this.end_date = end_date;
   }

   public Date getAtime() {
      return atime;
   }

   public void setAtime(Date atime) {
      this.atime = atime;
   }
}