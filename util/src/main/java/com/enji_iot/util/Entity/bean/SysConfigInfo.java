package com.enji_iot.util.Entity.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *@类:SysConfigInfo
 *@作者:M
 */

public class SysConfigInfo extends BaseBean {

   /**id*/
   private Integer id;

   /**name*/
   private String name;

   /**value*/
   private String value;

   /**remark*/
   private String remark;

   /**atime*/
   private Date atime;

   public SysConfigInfo(Integer id, String name, String value, String remark, Date atime) {
      this.id = id;
      this.name = name;
      this.value = value;
      this.remark = remark;
      this.atime = atime;
   }

   public SysConfigInfo(String sensor_name, Integer scene_id, Integer user_id, String start_time, String end_time, Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, Integer id1, String name, String value, String remark, Date atime) {
      super(sensor_name, scene_id, user_id, start_time, end_time, id, id_array, delete_flag, offset, limit, data);
      this.id = id1;
      this.name = name;
      this.value = value;
      this.remark = remark;
      this.atime = atime;
   }

   public SysConfigInfo(Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, Integer id1, String name, String value, String remark, Date atime) {
      super(id, id_array, delete_flag, offset, limit, data);
      this.id = id1;
      this.name = name;
      this.value = value;
      this.remark = remark;
      this.atime = atime;
   }

   public SysConfigInfo() {
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

   public String getValue() {
      return value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public String getRemark() {
      return remark;
   }

   public void setRemark(String remark) {
      this.remark = remark;
   }

   public Date getAtime() {
      return atime;
   }

   public void setAtime(Date atime) {
      this.atime = atime;
   }
}