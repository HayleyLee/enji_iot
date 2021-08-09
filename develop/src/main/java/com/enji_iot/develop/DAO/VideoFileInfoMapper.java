package com.enji_iot.develop.DAO;


import com.enji_iot.util.Entity.bo.VideoFileInfoBO;
import com.enji_iot.util.Util.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoFileInfoMapper {
    List<VideoFileInfoBO> selectPage(@Param(value = "videoFileInfo")VideoFileInfoBO videoFileInfo);
    List<VideoFileInfoBO> select(@Param(value = "videoFileInfo")VideoFileInfoBO videoFileInfo);
    Integer selectPageCount(@Param(value = "videoFileInfo")VideoFileInfoBO videoFileInfo);
    Integer insert(@Param(value = "videoFileInfo")VideoFileInfoBO videoFileInfo);
    Integer update(@Param(value = "videoFileInfo")VideoFileInfoBO videoFileInfo);
    VideoFileInfoBO selectOne(@Param(value = "videoFileInfo")VideoFileInfoBO videoFileInfo);
}
