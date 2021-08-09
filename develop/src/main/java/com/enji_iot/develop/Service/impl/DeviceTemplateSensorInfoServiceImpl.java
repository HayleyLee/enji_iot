package com.enji_iot.develop.Service.impl;


import com.enji_iot.develop.DAO.DeviceTemplateSensorInfoMapper;
import com.enji_iot.develop.Service.DeviceTemplateSensorInfoService;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Entity.bo.DeviceTemplateSensorInfoBO;
import com.enji_iot.util.Util.ObjectUtil;
import com.enji_iot.util.Util.PageBean;
import com.enji_iot.util.Util.ResultMapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service(value = "DeviceTemplateSensorInfoService")
public class DeviceTemplateSensorInfoServiceImpl implements DeviceTemplateSensorInfoService {
    @Resource
    private DeviceTemplateSensorInfoMapper deviceTemplateSensorInfoMapper;
    @Override
    public Map<String, Object> selectPageList(DeviceTemplateSensorInfoBO deviceTemplateSensorInfo, PageBean pageBean) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int totalCount = deviceTemplateSensorInfoMapper.selectPageCount(deviceTemplateSensorInfo);
            if (totalCount > 0) {
                pageBean.setPageParam4Mysql(deviceTemplateSensorInfo);
                List<?> list = deviceTemplateSensorInfoMapper.selectPage(deviceTemplateSensorInfo);
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
    public Map<String, Object> insert(DeviceTemplateSensorInfoBO deviceTemplateSensorInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = deviceTemplateSensorInfoMapper.insert(deviceTemplateSensorInfo);
            if (rows <= 0)  {
                ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> update(DeviceTemplateSensorInfoBO deviceTemplateSensorInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = deviceTemplateSensorInfoMapper.update(deviceTemplateSensorInfo);
            if (rows <= 0)  {
                return ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> selectOne(DeviceTemplateSensorInfoBO deviceTemplateSensorInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            DeviceTemplateSensorInfoBO data = deviceTemplateSensorInfoMapper.selectOne(deviceTemplateSensorInfo);
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
 
 
