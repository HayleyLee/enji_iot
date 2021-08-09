package com.enji_iot.util.Entity.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *@类:VideoFileInfo
 *@作者:M
 */

public class VideoFileInfo extends BaseBean {

   /**pk*/
   private Integer id;

   /**视频id*/
   private Integer video_id;

   /**名称*/
   private String name;

   /**fix*/
   private String fix;

   /**atime*/
   private Date atime;

   public VideoFileInfo(Integer id, Integer video_id, String name, String fix, Date atime) {
      this.id = id;
      this.video_id = video_id;
      this.name = name;
      this.fix = fix;
      this.atime = atime;
   }

   public VideoFileInfo(String sensor_name, Integer scene_id, Integer user_id, String start_time, String end_time, Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, Integer id1, Integer video_id, String name, String fix, Date atime) {
      super(sensor_name, scene_id, user_id, start_time, end_time, id, id_array, delete_flag, offset, limit, data);
      this.id = id1;
      this.video_id = video_id;
      this.name = name;
      this.fix = fix;
      this.atime = atime;
   }

   public VideoFileInfo(Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, Integer id1, Integer video_id, String name, String fix, Date atime) {
      super(id, id_array, delete_flag, offset, limit, data);
      this.id = id1;
      this.video_id = video_id;
      this.name = name;
      this.fix = fix;
      this.atime = atime;
   }

   public VideoFileInfo() {
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

   public String getFix() {
      return fix;
   }

   public void setFix(String fix) {
      this.fix = fix;
   }

   public Date getAtime() {
      return atime;
   }

   public void setAtime(Date atime) {
      this.atime = atime;
   }
}