package com.enji_iot.util.Entity.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *@类:FileInfo
 *@作者:M
 */

public class FileInfo extends BaseBean {

   /**id*/
   private Integer id;

   /**name*/
   private String name;

   /**fix*/
   private String fix;

   /**size*/
   private Integer size;

   /**delete_flag*/
   private Integer delete_flag;

   /**add_id*/
   private Integer add_id;

   /**add_time*/
   private Date add_time;

   private String file_path ;

   public FileInfo(Integer id, String name, String fix, Integer size, Integer delete_flag, Integer add_id, Date add_time, String file_path) {
      this.id = id;
      this.name = name;
      this.fix = fix;
      this.size = size;
      this.delete_flag = delete_flag;
      this.add_id = add_id;
      this.add_time = add_time;
      this.file_path = file_path;
   }

   public FileInfo(String sensor_name, Integer scene_id, Integer user_id, String start_time, String end_time, Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, Integer id1, String name, String fix, Integer size, Integer delete_flag1, Integer add_id, Date add_time, String file_path) {
      super(sensor_name, scene_id, user_id, start_time, end_time, id, id_array, delete_flag, offset, limit, data);
      this.id = id1;
      this.name = name;
      this.fix = fix;
      this.size = size;
      this.delete_flag = delete_flag1;
      this.add_id = add_id;
      this.add_time = add_time;
      this.file_path = file_path;
   }

   public FileInfo(Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, Integer id1, String name, String fix, Integer size, Integer delete_flag1, Integer add_id, Date add_time, String file_path) {
      super(id, id_array, delete_flag, offset, limit, data);
      this.id = id1;
      this.name = name;
      this.fix = fix;
      this.size = size;
      this.delete_flag = delete_flag1;
      this.add_id = add_id;
      this.add_time = add_time;
      this.file_path = file_path;
   }

   public FileInfo() {
   }

   @Override
   public Integer getId() {
      return id;
   }

   @Override
   public void setId(Integer id) {
      this.id = id;
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

   public Integer getSize() {
      return size;
   }

   public void setSize(Integer size) {
      this.size = size;
   }

   @Override
   public Integer getDelete_flag() {
      return delete_flag;
   }

   @Override
   public void setDelete_flag(Integer delete_flag) {
      this.delete_flag = delete_flag;
   }

   public Integer getAdd_id() {
      return add_id;
   }

   public void setAdd_id(Integer add_id) {
      this.add_id = add_id;
   }

   public Date getAdd_time() {
      return add_time;
   }

   public void setAdd_time(Date add_time) {
      this.add_time = add_time;
   }

   public String getFile_path() {
      return file_path;
   }

   public void setFile_path(String file_path) {
      this.file_path = file_path;
   }
}