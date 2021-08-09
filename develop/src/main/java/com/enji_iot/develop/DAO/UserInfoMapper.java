package com.enji_iot.develop.DAO;

import com.enji_iot.util.Entity.bo.UserInfoBO;
import com.enji_iot.util.Util.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoMapper {
    Integer selectCount(@Param(value = "userInfo")UserInfoBO userInfo);
    UserInfoBO selectOne(@Param(value = "userInfo")UserInfoBO userInfo);
    UserInfoBO selectOneLogin(@Param(value = "userInfo")UserInfoBO userInfo);
    List<UserInfoBO> select(@Param(value = "userInfo")UserInfoBO userInfo);
    Integer update(@Param(value = "userInfo")UserInfoBO userInfo);
    Integer insert(@Param(value = "userInfo")UserInfoBO userInfo);
    Integer updatePassword(@Param(value = "userInfo")UserInfoBO userInfo);
    Integer updatePasswordByKey(@Param(value = "userInfo")UserInfoBO userInfo);
}
