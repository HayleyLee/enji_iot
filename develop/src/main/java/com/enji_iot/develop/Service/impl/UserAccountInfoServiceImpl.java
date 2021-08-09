package com.enji_iot.develop.Service.impl;


import com.enji_iot.develop.DAO.UserAccountInfoMapper;
import com.enji_iot.develop.Service.UserAccountInfoService;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Entity.bo.UserAccountInfoBO;
import com.enji_iot.util.Util.ResultMapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service(value = "UserAccountInfoService")
public class UserAccountInfoServiceImpl implements UserAccountInfoService {

    @Resource
    private UserAccountInfoMapper userAccountInfoMapper;

    @Override
    public Map<String, Object> selectDetail(UserAccountInfoBO userAccountInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            UserAccountInfoBO data = userAccountInfoMapper.selectDetail(userAccountInfo);
            if (data == null) {
                ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.NO_DATA);
            } else {
                ResultMapUtils.putData(resultMap, data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> insertSimple(UserAccountInfoBO userAccountInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = userAccountInfoMapper.insertSimple(userAccountInfo);
            if (rows <= 0)  {
                ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }
}
 
 
