package com.enji_iot.develop.Service;

import com.enji_iot.util.Entity.bo.IotSceneInfoBO;
import net.sf.json.JSONArray;

public interface IotNodeActionService {
    JSONArray month_count(IotSceneInfoBO iotSceneInfo);

    JSONArray month_data(IotSceneInfoBO sceneInfo);
}
