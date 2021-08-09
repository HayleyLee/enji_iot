package com.enji_iot.data.Service.Impl;

import com.enji_iot.data.Service.IotScreenService;
import com.enji_iot.data.DAO.IotScreenMapper;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Entity.bo.IotNodeInfoBO;
import com.enji_iot.util.Util.ResultMapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service(value = "IotScreenService")
public class IotScreenServiceImpl implements IotScreenService {
    @Resource
    private IotScreenMapper iotScreenMapper;

    @Override
    public Map<String, Object> selectAll(IotNodeInfoBO iotNodeInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            List<IotNodeInfoBO> data = iotScreenMapper.selectAll(iotNodeInfo);
            if (data==null || data.size() ==0) {
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
