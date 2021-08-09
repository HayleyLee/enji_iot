package com.enji_iot.develop.Service;


import com.enji_iot.util.Entity.bo.UserAccountInfoBO;

import java.util.Map;

public interface UserAccountInfoService {
    Map<String,Object> selectDetail(UserAccountInfoBO userAccountInfo);
    Map<String,Object> insertSimple(UserAccountInfoBO userAccountInfo);
}
 
 
