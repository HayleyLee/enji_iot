package com.enji_iot.develop.Service.impl;

import com.enji_iot.develop.DAO.ContactUserInfoMapper;
import com.enji_iot.develop.Service.ContactUserInfoService;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Entity.bo.ContactUserInfoBO;
import com.enji_iot.util.Util.ObjectUtil;
import com.enji_iot.util.Util.PageBean;
import com.enji_iot.util.Util.ResultMapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service(value = "ContactUserInfoService")
public class ContactUserInfoServiceImpl implements ContactUserInfoService {
    @Resource
    private ContactUserInfoMapper contactUserInfoMapper;

    @Override
    public Map<String, Object> selectPageList(ContactUserInfoBO contactUserInfoBO, PageBean pageBean) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int totalCount = contactUserInfoMapper.selectPageCount(contactUserInfoBO);
            if (totalCount > 0) {
                pageBean.setPageParam4Mysql(contactUserInfoBO);
                List<ContactUserInfoBO> list = contactUserInfoMapper.selectPage(contactUserInfoBO);
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
    public Map<String, Object> insert(ContactUserInfoBO contactUserInfoBO) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = contactUserInfoMapper.insert(contactUserInfoBO);
            if (rows <= 0)  {
                ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> update(ContactUserInfoBO contactUserInfoBO) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = contactUserInfoMapper.update(contactUserInfoBO);
            if (rows <= 0)  {
                return ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> selectOne(ContactUserInfoBO contactUserInfoBO) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            ContactUserInfoBO data = contactUserInfoMapper.selectOne(contactUserInfoBO);
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
