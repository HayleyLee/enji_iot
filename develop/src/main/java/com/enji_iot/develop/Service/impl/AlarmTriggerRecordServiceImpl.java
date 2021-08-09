package com.enji_iot.develop.Service.impl;


import com.enji_iot.develop.DAO.AlarmTriggerRecordMapper;
import com.enji_iot.develop.Service.AlarmTriggerRecordService;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Entity.bo.AlarmTriggerRecordBO;
import com.enji_iot.util.Util.ObjectUtil;
import com.enji_iot.util.Util.PageBean;
import com.enji_iot.util.Util.ResultMapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service(value = "AlarmTriggerRecordService")
public class AlarmTriggerRecordServiceImpl implements AlarmTriggerRecordService {
    
    @Resource
    private AlarmTriggerRecordMapper alarmTriggerRecordMapper;
    
    @Override
    public Map<String, Object> selectPageList(AlarmTriggerRecordBO alarmTriggerRecord, PageBean pageBean) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int totalCount = alarmTriggerRecordMapper.selectPageCount(alarmTriggerRecord);
            if (totalCount > 0) {
                pageBean.setPageParam4Mysql(alarmTriggerRecord);
                List<AlarmTriggerRecordBO> list = alarmTriggerRecordMapper.selectPage(alarmTriggerRecord);
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
    public Map<String, Object> selectStatisticPage(AlarmTriggerRecordBO alarmTriggerRecord, PageBean pageBean) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int totalCount = alarmTriggerRecordMapper.selectPageCount(alarmTriggerRecord);
            if (totalCount > 0) {
                pageBean.setPageParam4Mysql(alarmTriggerRecord);
                List<AlarmTriggerRecordBO> list = alarmTriggerRecordMapper.selectStatisticPage(alarmTriggerRecord);
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
    public Map<String, Object> insert(AlarmTriggerRecordBO alarmTriggerRecord) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = alarmTriggerRecordMapper.insert(alarmTriggerRecord);
            if (rows <= 0)  {
                ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> update(AlarmTriggerRecordBO alarmTriggerRecord) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = alarmTriggerRecordMapper.update(alarmTriggerRecord);
            if (rows <= 0)  {
                return ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> selectOne(AlarmTriggerRecordBO alarmTriggerRecord) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            Object data = alarmTriggerRecordMapper.selectOne(alarmTriggerRecord);
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
    public Map<String, Object> updateSmsVocieNum(AlarmTriggerRecordBO alarmTriggerRecord) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = alarmTriggerRecordMapper.updateSmsVocieNum(alarmTriggerRecord);
            if (rows <= 0)  {
                return ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }
}
 
 
