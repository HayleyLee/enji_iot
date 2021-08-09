package com.enji_iot.util.Entity.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *@类:IotVideoInfo
 *@作者:M
 */

public class IotVideoInfo extends BaseBean {

   /**pk*/
   private Integer id;

   /**视频名称*/
   private String name;

   /**scene_id*/
   private Integer scene_id;

   /**status*/
   private Integer status;

   /**image_id*/
   private Integer image_id;

   /**video_type*/
   private Integer video_type;

   /**seq*/
   private Integer seq;

   /**关联账户ID*/
   private Integer relate_id;

   private String app_name ;
   
   /**rtmp播放地址*/
   private String rtmp_url_high;

   /**rtmp正常播放地址*/
   private String rtmp_url_common;

   /**hls播放地址*/
   private String hls_url;

   /**aid*/
   private Integer aid;

   /**atime*/
   private Date atime;

   /**mid*/
   private Integer mid;

   /**mtime*/
   private Date mtime;

   private Integer camera_type ;
   
   private String username ;
   
   private String password ;
   
   private String ip ;
   
   private String port ;
   
   private String codectype ;
   
   private String channel ;
   
   private String subtype ;

   public IotVideoInfo(Integer id, String name, Integer scene_id, Integer status, Integer image_id, Integer video_type, Integer seq, Integer relate_id, String app_name, String rtmp_url_high, String rtmp_url_common, String hls_url, Integer aid, Date atime, Integer mid, Date mtime, Integer camera_type, String username, String password, String ip, String port, String codectype, String channel, String subtype) {
      this.id = id;
      this.name = name;
      this.scene_id = scene_id;
      this.status = status;
      this.image_id = image_id;
      this.video_type = video_type;
      this.seq = seq;
      this.relate_id = relate_id;
      this.app_name = app_name;
      this.rtmp_url_high = rtmp_url_high;
      this.rtmp_url_common = rtmp_url_common;
      this.hls_url = hls_url;
      this.aid = aid;
      this.atime = atime;
      this.mid = mid;
      this.mtime = mtime;
      this.camera_type = camera_type;
      this.username = username;
      this.password = password;
      this.ip = ip;
      this.port = port;
      this.codectype = codectype;
      this.channel = channel;
      this.subtype = subtype;
   }

   public IotVideoInfo(String sensor_name, Integer scene_id, Integer user_id, String start_time, String end_time, Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, Integer id1, String name, Integer scene_id1, Integer status, Integer image_id, Integer video_type, Integer seq, Integer relate_id, String app_name, String rtmp_url_high, String rtmp_url_common, String hls_url, Integer aid, Date atime, Integer mid, Date mtime, Integer camera_type, String username, String password, String ip, String port, String codectype, String channel, String subtype) {
      super(sensor_name, scene_id, user_id, start_time, end_time, id, id_array, delete_flag, offset, limit, data);
      this.id = id1;
      this.name = name;
      this.scene_id = scene_id1;
      this.status = status;
      this.image_id = image_id;
      this.video_type = video_type;
      this.seq = seq;
      this.relate_id = relate_id;
      this.app_name = app_name;
      this.rtmp_url_high = rtmp_url_high;
      this.rtmp_url_common = rtmp_url_common;
      this.hls_url = hls_url;
      this.aid = aid;
      this.atime = atime;
      this.mid = mid;
      this.mtime = mtime;
      this.camera_type = camera_type;
      this.username = username;
      this.password = password;
      this.ip = ip;
      this.port = port;
      this.codectype = codectype;
      this.channel = channel;
      this.subtype = subtype;
   }

   public IotVideoInfo(Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, Integer id1, String name, Integer scene_id, Integer status, Integer image_id, Integer video_type, Integer seq, Integer relate_id, String app_name, String rtmp_url_high, String rtmp_url_common, String hls_url, Integer aid, Date atime, Integer mid, Date mtime, Integer camera_type, String username, String password, String ip, String port, String codectype, String channel, String subtype) {
      super(id, id_array, delete_flag, offset, limit, data);
      this.id = id1;
      this.name = name;
      this.scene_id = scene_id;
      this.status = status;
      this.image_id = image_id;
      this.video_type = video_type;
      this.seq = seq;
      this.relate_id = relate_id;
      this.app_name = app_name;
      this.rtmp_url_high = rtmp_url_high;
      this.rtmp_url_common = rtmp_url_common;
      this.hls_url = hls_url;
      this.aid = aid;
      this.atime = atime;
      this.mid = mid;
      this.mtime = mtime;
      this.camera_type = camera_type;
      this.username = username;
      this.password = password;
      this.ip = ip;
      this.port = port;
      this.codectype = codectype;
      this.channel = channel;
      this.subtype = subtype;
   }

   public IotVideoInfo() {
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

   @Override
   public Integer getScene_id() {
      return scene_id;
   }

   @Override
   public void setScene_id(Integer scene_id) {
      this.scene_id = scene_id;
   }

   public Integer getStatus() {
      return status;
   }

   public void setStatus(Integer status) {
      this.status = status;
   }

   public Integer getImage_id() {
      return image_id;
   }

   public void setImage_id(Integer image_id) {
      this.image_id = image_id;
   }

   public Integer getVideo_type() {
      return video_type;
   }

   public void setVideo_type(Integer video_type) {
      this.video_type = video_type;
   }

   public Integer getSeq() {
      return seq;
   }

   public void setSeq(Integer seq) {
      this.seq = seq;
   }

   public Integer getRelate_id() {
      return relate_id;
   }

   public void setRelate_id(Integer relate_id) {
      this.relate_id = relate_id;
   }

   public String getApp_name() {
      return app_name;
   }

   public void setApp_name(String app_name) {
      this.app_name = app_name;
   }

   public String getRtmp_url_high() {
      return rtmp_url_high;
   }

   public void setRtmp_url_high(String rtmp_url_high) {
      this.rtmp_url_high = rtmp_url_high;
   }

   public String getRtmp_url_common() {
      return rtmp_url_common;
   }

   public void setRtmp_url_common(String rtmp_url_common) {
      this.rtmp_url_common = rtmp_url_common;
   }

   public String getHls_url() {
      return hls_url;
   }

   public void setHls_url(String hls_url) {
      this.hls_url = hls_url;
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

   public Integer getCamera_type() {
      return camera_type;
   }

   public void setCamera_type(Integer camera_type) {
      this.camera_type = camera_type;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
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

   public String getCodectype() {
      return codectype;
   }

   public void setCodectype(String codectype) {
      this.codectype = codectype;
   }

   public String getChannel() {
      return channel;
   }

   public void setChannel(String channel) {
      this.channel = channel;
   }

   public String getSubtype() {
      return subtype;
   }

   public void setSubtype(String subtype) {
      this.subtype = subtype;
   }
}