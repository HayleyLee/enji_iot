package com.enji_iot.develop.Service.impl;


import com.enji_iot.develop.DAO.HKAccountInfoMapper;
import com.enji_iot.develop.Service.HkAccountInfoService;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Entity.bo.HkAccountInfoBO;
import com.enji_iot.util.Util.ObjectUtil;
import com.enji_iot.util.Util.PageBean;
import com.enji_iot.util.Util.ResultMapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service(value = "HkAccountInfoService")
public class HkAccountInfoServiceImpl implements HkAccountInfoService {
    @Resource
    private HKAccountInfoMapper hkAccountInfoMapper;
    @Override
    public Map<String, Object> selectPageList(HkAccountInfoBO hkAccountInfo, PageBean pageBean) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int totalCount = hkAccountInfoMapper.selectPageCount(hkAccountInfo);
            if (totalCount > 0) {
                pageBean.setPageParam4Mysql(hkAccountInfo);
                List<?> list = hkAccountInfoMapper.selectPage(hkAccountInfo);
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
    public Map<String, Object> insert(HkAccountInfoBO hkAccountInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = hkAccountInfoMapper.insert(hkAccountInfo);
            if (rows <= 0)  {
                ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> selectOne(HkAccountInfoBO hkAccountInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            Object data = hkAccountInfoMapper.selectOne(hkAccountInfo);
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
    public Map<String, Object> update(HkAccountInfoBO hkAccountInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = hkAccountInfoMapper.update(hkAccountInfo);
            if (rows <= 0)  {
                return ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }
}
 
 
