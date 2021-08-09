package com.enji_iot.develop.DAO;

import com.enji_iot.util.Entity.bo.ContactUserInfoBO;
import com.enji_iot.util.Util.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactUserInfoMapper {
    List<ContactUserInfoBO> selectPage(@Param(value = "contactUserInfo")ContactUserInfoBO contactUserInfo);
    Integer selectPageCount(@Param(value = "contactUserInfo")ContactUserInfoBO contactUserInfo);
    Integer insert(@Param(value = "contactUserInfo")ContactUserInfoBO contactUserInfo);
    Integer update(@Param(value = "contactUserInfo")ContactUserInfoBO contactUserInfo);
    ContactUserInfoBO selectOne(@Param(value = "contactUserInfo")ContactUserInfoBO contactUserInfo);
}
