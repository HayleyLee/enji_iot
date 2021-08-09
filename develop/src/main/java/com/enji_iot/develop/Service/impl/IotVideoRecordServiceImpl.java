package com.enji_iot.develop.Service.impl;


import com.enji_iot.develop.DAO.IotVideoRecordMapper;
import com.enji_iot.develop.Service.IotVideoRecordService;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Entity.bo.IotVideoRecordBO;
import com.enji_iot.util.Util.ObjectUtil;
import com.enji_iot.util.Util.PageBean;
import com.enji_iot.util.Util.ResultMapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service(value = "IotVideoRecordService")
public class IotVideoRecordServiceImpl implements IotVideoRecordService {
    @Resource
    private IotVideoRecordMapper iotVideoRecordMapper;
    @Override
    public Map<String, Object> insert(IotVideoRecordBO iotVideoRecord) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = iotVideoRecordMapper.insert(iotVideoRecord);
            if (rows <= 0)  {
                ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> update(IotVideoRecordBO iotVideoRecord) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = iotVideoRecordMapper.update(iotVideoRecord);
            if (rows <= 0)  {
                ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> selectOne(IotVideoRecordBO iotVideoRecord) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            IotVideoRecordBO data = iotVideoRecordMapper.selectOne(iotVideoRecord);
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
    public Map<String, Object> selectPageList(IotVideoRecordBO iotVideoRecord, PageBean pageBean) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int totalCount = iotVideoRecordMapper.selectPageCount(iotVideoRecord);
            if (totalCount > 0) {
                pageBean.setPageParam4Mysql(iotVideoRecord);
                List<?> list = iotVideoRecordMapper.selectPage(iotVideoRecord);
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
}
 
 
