package com.enji_iot.data.Service;

import com.enji_iot.util.Entity.bo.IotNodeInfoBO;

import java.util.Map;

public interface IotScreenService {
    Map<String,Object> selectAll(IotNodeInfoBO iotNodeInfo);
}
