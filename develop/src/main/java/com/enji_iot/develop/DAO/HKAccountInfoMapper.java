package com.enji_iot.develop.DAO;

import com.enji_iot.util.Entity.bo.HkAccountInfoBO;
import com.enji_iot.util.Util.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HKAccountInfoMapper {
    List<HkAccountInfoBO> selectPage(@Param(value = "HKAccountInfo")HkAccountInfoBO HKAccountInfo);
    Integer selectPageCount(@Param(value = "HKAccountInfo")HkAccountInfoBO HKAccountInfo);
    Integer insert(@Param(value = "HKAccountInfo")HkAccountInfoBO HKAccountInfo);
    Integer update(@Param(value = "HKAccountInfo")HkAccountInfoBO HKAccountInfo);
    HkAccountInfoBO selectOne(@Param(value = "HKAccountInfo")HkAccountInfoBO HKAccountInfo);
}
