package com.enji_iot.util.Entity.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *@类:IotSceneInfo
 *@作者:chenrj
 */

public class IotSceneInfo extends BaseBean {


   /**name*/
   private String name;

   /**父场景号*/
   private Integer pid;

   /**user_id*/
   private Integer user_id;


   /**lon*/
   private Double lon;

   /**lat*/
   private Double lat;
   
   private String img_id ;

   /**description*/
   private String description;

   /**场景类型：农业、家居*/
   private Integer iot_scene_type;

   /**布防状态*/
   private Integer guard_status;

   /**remark*/
   private String remark;

   /**seq*/
   private Integer seq;

   /**aid*/
   private Integer aid;

   /**atime*/
   private Date atime;

   /**mid*/
   private Integer mid;

   /**mtime*/
   private Date mtime;

   /**
    * 省份
    */
   private String province;

   /**
    * 省份代码
    */
   private Integer province_code;

   /**
    * 城市
    */
   private String city;

   /**
    * 城市代码
    */
   private Integer city_code;

   public IotSceneInfo(String name, Integer pid, Integer user_id, Double lon, Double lat, String img_id, String description, Integer iot_scene_type, Integer guard_status, String remark, Integer seq, Integer aid, Date atime, Integer mid, Date mtime, String province, Integer province_code, String city, Integer city_code) {
      this.name = name;
      this.pid = pid;
      this.user_id = user_id;
      this.lon = lon;
      this.lat = lat;
      this.img_id = img_id;
      this.description = description;
      this.iot_scene_type = iot_scene_type;
      this.guard_status = guard_status;
      this.remark = remark;
      this.seq = seq;
      this.aid = aid;
      this.atime = atime;
      this.mid = mid;
      this.mtime = mtime;
      this.province = province;
      this.province_code = province_code;
      this.city = city;
      this.city_code = city_code;
   }

   public IotSceneInfo(String sensor_name, Integer scene_id, Integer user_id, String start_time, String end_time, Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, String name, Integer pid, Integer user_id1, Double lon, Double lat, String img_id, String description, Integer iot_scene_type, Integer guard_status, String remark, Integer seq, Integer aid, Date atime, Integer mid, Date mtime, String province, Integer province_code, String city, Integer city_code) {
      super(sensor_name, scene_id, user_id, start_time, end_time, id, id_array, delete_flag, offset, limit, data);
      this.name = name;
      this.pid = pid;
      this.user_id = user_id1;
      this.lon = lon;
      this.lat = lat;
      this.img_id = img_id;
      this.description = description;
      this.iot_scene_type = iot_scene_type;
      this.guard_status = guard_status;
      this.remark = remark;
      this.seq = seq;
      this.aid = aid;
      this.atime = atime;
      this.mid = mid;
      this.mtime = mtime;
      this.province = province;
      this.province_code = province_code;
      this.city = city;
      this.city_code = city_code;
   }

   public IotSceneInfo(Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, String name, Integer pid, Integer user_id, Double lon, Double lat, String img_id, String description, Integer iot_scene_type, Integer guard_status, String remark, Integer seq, Integer aid, Date atime, Integer mid, Date mtime, String province, Integer province_code, String city, Integer city_code) {
      super(id, id_array, delete_flag, offset, limit, data);
      this.name = name;
      this.pid = pid;
      this.user_id = user_id;
      this.lon = lon;
      this.lat = lat;
      this.img_id = img_id;
      this.description = description;
      this.iot_scene_type = iot_scene_type;
      this.guard_status = guard_status;
      this.remark = remark;
      this.seq = seq;
      this.aid = aid;
      this.atime = atime;
      this.mid = mid;
      this.mtime = mtime;
      this.province = province;
      this.province_code = province_code;
      this.city = city;
      this.city_code = city_code;
   }

   public IotSceneInfo() {
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Integer getPid() {
      return pid;
   }

   public void setPid(Integer pid) {
      this.pid = pid;
   }

   @Override
   public Integer getUser_id() {
      return user_id;
   }

   @Override
   public void setUser_id(Integer user_id) {
      this.user_id = user_id;
   }

   public Double getLon() {
      return lon;
   }

   public void setLon(Double lon) {
      this.lon = lon;
   }

   public Double getLat() {
      return lat;
   }

   public void setLat(Double lat) {
      this.lat = lat;
   }

   public String getImg_id() {
      return img_id;
   }

   public void setImg_id(String img_id) {
      this.img_id = img_id;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public Integer getIot_scene_type() {
      return iot_scene_type;
   }

   public void setIot_scene_type(Integer iot_scene_type) {
      this.iot_scene_type = iot_scene_type;
   }

   public Integer getGuard_status() {
      return guard_status;
   }

   public void setGuard_status(Integer guard_status) {
      this.guard_status = guard_status;
   }

   public String getRemark() {
      return remark;
   }

   public void setRemark(String remark) {
      this.remark = remark;
   }

   public Integer getSeq() {
      return seq;
   }

   public void setSeq(Integer seq) {
      this.seq = seq;
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

   public String getProvince() {
      return province;
   }

   public void setProvince(String province) {
      this.province = province;
   }

   public Integer getProvince_code() {
      return province_code;
   }

   public void setProvince_code(Integer province_code) {
      this.province_code = province_code;
   }

   public String getCity() {
      return city;
   }

   public void setCity(String city) {
      this.city = city;
   }

   public Integer getCity_code() {
      return city_code;
   }

   public void setCity_code(Integer city_code) {
      this.city_code = city_code;
   }
}