package com.enji_iot.develop.DAO;

import com.enji_iot.util.Entity.bo.FileInfoBO;
import com.enji_iot.util.Util.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileInfoMapper {
    Integer insert(@Param(value = "fileInfo")FileInfoBO fileInfo);
    Integer update(@Param(value = "fileInfo")FileInfoBO fileInfo);
    Integer selectPageCount(@Param(value = "fileInfo")FileInfoBO fileInfo);
    List<FileInfoBO> selectPage(@Param(value = "fileInfo")FileInfoBO fileInfo);
    FileInfoBO selectOne(@Param(value = "fileInfo")FileInfoBO fileInfo);
}
