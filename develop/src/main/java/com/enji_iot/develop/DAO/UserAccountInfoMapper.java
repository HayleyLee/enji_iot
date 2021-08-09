package com.enji_iot.develop.DAO;


import com.enji_iot.util.Entity.bo.UserAccountInfoBO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountInfoMapper {
    Integer insertSimple(@Param(value = "userAccountInfo") UserAccountInfoBO userAccountInfo);
    Integer updateVoiceNum(@Param(value = "userAccountInfo") UserAccountInfoBO userAccountInfo);
    Integer updateSMS_num(@Param(value = "userAccountInfo") UserAccountInfoBO userAccountInfo);
    UserAccountInfoBO selectDetail(@Param(value = "userAccountInfo") UserAccountInfoBO userAccountInfo);
}
