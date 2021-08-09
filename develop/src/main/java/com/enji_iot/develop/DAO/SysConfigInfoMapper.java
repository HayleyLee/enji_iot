package com.enji_iot.develop.DAO;

import com.enji_iot.util.Entity.bo.SysConfigInfoBO;
import com.enji_iot.util.Util.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysConfigInfoMapper {
    List<SysConfigInfoBO> selectPage(@Param(value = "sysConfigInfo")SysConfigInfoBO sysConfigInfo);
    Integer selectPageCount(@Param(value = "sysConfigInfo")SysConfigInfoBO sysConfigInfo);
    Integer insert(@Param(value = "sysConfigInfo")SysConfigInfoBO sysConfigInfo);
    Integer update(@Param(value = "sysConfigInfo")SysConfigInfoBO sysConfigInfo);
    SysConfigInfoBO selectOne(@Param(value = "sysConfigInfo")SysConfigInfoBO sysConfigInfo);
}
