package com.enji_iot.util.Entity.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *@类:IotSceneUserRelation
 *@作者:chenrj
 */

public class IotSceneUserRelation extends BaseBean {

   /**id*/
   private Integer id;

   /**scene_id*/
   private Integer scene_id;

   /**user_id*/
   private Integer user_id;

   /**aid*/
   private Integer aid;

   /**atime*/
   private Date atime;

   public IotSceneUserRelation(Integer id, Integer scene_id, Integer user_id, Integer aid, Date atime) {
      this.id = id;
      this.scene_id = scene_id;
      this.user_id = user_id;
      this.aid = aid;
      this.atime = atime;
   }

   public IotSceneUserRelation(String sensor_name, Integer scene_id, Integer user_id, String start_time, String end_time, Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, Integer id1, Integer scene_id1, Integer user_id1, Integer aid, Date atime) {
      super(sensor_name, scene_id, user_id, start_time, end_time, id, id_array, delete_flag, offset, limit, data);
      this.id = id1;
      this.scene_id = scene_id1;
      this.user_id = user_id1;
      this.aid = aid;
      this.atime = atime;
   }

   public IotSceneUserRelation(Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, Integer id1, Integer scene_id, Integer user_id, Integer aid, Date atime) {
      super(id, id_array, delete_flag, offset, limit, data);
      this.id = id1;
      this.scene_id = scene_id;
      this.user_id = user_id;
      this.aid = aid;
      this.atime = atime;
   }

   public IotSceneUserRelation() {
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
   public Integer getScene_id() {
      return scene_id;
   }

   @Override
   public void setScene_id(Integer scene_id) {
      this.scene_id = scene_id;
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
}