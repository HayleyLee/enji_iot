package com.enji_iot.develop.Service.impl;


import com.enji_iot.develop.DAO.SysConfigInfoMapper;
import com.enji_iot.develop.Service.SysConfigInfoService;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Entity.bo.SysConfigInfoBO;
import com.enji_iot.util.Util.ObjectUtil;
import com.enji_iot.util.Util.PageBean;
import com.enji_iot.util.Util.ResultMapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service(value = "SysConfigInfoService")
public class SysConfigInfoServiceImpl implements SysConfigInfoService {
    @Resource
    private SysConfigInfoMapper sysConfigInfoMapper;

    @Override
    public Map<String, Object> selectPageList(SysConfigInfoBO sysConfigInfo, PageBean pageBean) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int totalCount = sysConfigInfoMapper.selectPageCount(sysConfigInfo);
            if (totalCount > 0) {
                pageBean.setPageParam4Mysql(sysConfigInfo);
                List<SysConfigInfoBO> list = sysConfigInfoMapper.selectPage(sysConfigInfo);
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
    public Map<String, Object> insert(SysConfigInfoBO sysConfigInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = sysConfigInfoMapper.insert(sysConfigInfo);
            if (rows <= 0)  {
                ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> update(SysConfigInfoBO sysConfigInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = sysConfigInfoMapper.update(sysConfigInfo);
            if (rows <= 0)  {
                return ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> selectOne(SysConfigInfoBO sysConfigInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            SysConfigInfoBO data = sysConfigInfoMapper.selectOne(sysConfigInfo);
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
 
 
