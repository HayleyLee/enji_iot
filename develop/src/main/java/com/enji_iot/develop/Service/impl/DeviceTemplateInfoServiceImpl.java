package com.enji_iot.develop.Service.impl;


import com.enji_iot.develop.DAO.DeviceTemplateInfoMapper;
import com.enji_iot.develop.Service.DeviceTemplateInfoService;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Entity.bo.DeviceTemplateInfoBO;
import com.enji_iot.util.Util.ObjectUtil;
import com.enji_iot.util.Util.PageBean;
import com.enji_iot.util.Util.ResultMapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service(value = "DeviceTemplateInfoService")
public class DeviceTemplateInfoServiceImpl implements DeviceTemplateInfoService {
    @Resource
    private DeviceTemplateInfoMapper deviceTemplateInfoMapper;
    @Override
    public Map<String, Object> selectPageList(DeviceTemplateInfoBO deviceTemplateInfo, PageBean pageBean) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int totalCount = deviceTemplateInfoMapper.selectPageCount(deviceTemplateInfo);
            if (totalCount > 0) {
                pageBean.setPageParam4Mysql(deviceTemplateInfo);
                List<DeviceTemplateInfoBO> list = deviceTemplateInfoMapper.selectPage(deviceTemplateInfo);
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
    public Map<String, Object> insert(DeviceTemplateInfoBO deviceTemplateInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = deviceTemplateInfoMapper.insert(deviceTemplateInfo);
            if (rows <= 0)  {
                ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> update(DeviceTemplateInfoBO deviceTemplateInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = deviceTemplateInfoMapper.update(deviceTemplateInfo);
            if (rows <= 0)  {
                return ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> selectOne(DeviceTemplateInfoBO deviceTemplateInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            DeviceTemplateInfoBO data = deviceTemplateInfoMapper.selectOne(deviceTemplateInfo);
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
 
 
