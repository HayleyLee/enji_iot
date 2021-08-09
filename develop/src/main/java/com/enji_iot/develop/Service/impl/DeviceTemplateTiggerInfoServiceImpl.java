package com.enji_iot.develop.Service.impl;


import com.enji_iot.develop.DAO.DeviceTemplateTriggerInfoMapper;
import com.enji_iot.develop.Service.DeviceTemplateTiggerInfoService;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Entity.bo.DeviceTemplateTiggerInfoBO;
import com.enji_iot.util.Util.ObjectUtil;
import com.enji_iot.util.Util.PageBean;
import com.enji_iot.util.Util.ResultMapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service(value = "DeviceTemplateTiggerInfoService")
public class DeviceTemplateTiggerInfoServiceImpl implements DeviceTemplateTiggerInfoService {
    @Resource
    private DeviceTemplateTriggerInfoMapper deviceTemplateTriggerInfoMapper;
    @Override
    public Map<String, Object> selectPageList(DeviceTemplateTiggerInfoBO deviceTemplateTiggerInfo, PageBean pageBean) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int totalCount = deviceTemplateTriggerInfoMapper.selectPageCount(deviceTemplateTiggerInfo);
            if (totalCount > 0) {
                pageBean.setPageParam4Mysql(deviceTemplateTiggerInfo);
                List<DeviceTemplateTiggerInfoBO> list = deviceTemplateTriggerInfoMapper.selectPage(deviceTemplateTiggerInfo);
                if(ObjectUtil.isNotEmpty(list)){
                    pageBean.setTotalCount(totalCount);
                    pageBean.setData(list);
                    ResultMapUtils.putData(resultMap, pageBean);
                }else{
                    ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.NO_DATA);
                }
            } else {
                ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.NO_DATA);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> insert(DeviceTemplateTiggerInfoBO deviceTemplateTiggerInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = deviceTemplateTriggerInfoMapper.insert(deviceTemplateTiggerInfo);
            if (rows <= 0)  {
                ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> update(DeviceTemplateTiggerInfoBO deviceTemplateTiggerInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = deviceTemplateTriggerInfoMapper.update(deviceTemplateTiggerInfo);
            if (rows <= 0)  {
                return ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> selectOne(DeviceTemplateTiggerInfoBO deviceTemplateTiggerInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            Object data = deviceTemplateTriggerInfoMapper.selectOne(deviceTemplateTiggerInfo);
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
}
 
 
