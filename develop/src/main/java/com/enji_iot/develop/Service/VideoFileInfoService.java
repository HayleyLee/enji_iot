package com.enji_iot.develop.Service;

import com.enji_iot.util.Entity.bo.VideoFileInfoBO;
import com.enji_iot.util.Util.PageBean;

import java.util.Map;

public interface VideoFileInfoService {
    Map<String,Object> selectPage(VideoFileInfoBO videoFileInfo, PageBean pageBean);
    Map<String,Object> select(VideoFileInfoBO videoFileInfo);
    Map<String,Object> insert(VideoFileInfoBO videoFileInfo);
    Map<String,Object> update(VideoFileInfoBO videoFileInfo);
    Map<String,Object> selectOne(VideoFileInfoBO videoFileInfo);
}
 
 
