package com.enji_iot.develop.Controller.iot;

import com.enji_iot.develop.Service.IotNodeActionService;
import com.enji_iot.util.Common.RequestURLIOT;
import com.enji_iot.util.Entity.bo.IotSceneInfoBO;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IotNodeActionController {
    @Autowired
    @Qualifier(value = "IotNodeActionService")
    private IotNodeActionService iotNodeActionService;

    @RequestMapping(method = RequestMethod.POST,value = RequestURLIOT.IotNodeAction.MONTH_COUNT)
    public @ResponseBody JSONArray month_send(String user_id){
        IotSceneInfoBO sceneInfo = new IotSceneInfoBO();
        sceneInfo.setUser_id(Integer.parseInt(user_id));
        return iotNodeActionService.month_count(sceneInfo);
    }
    @RequestMapping(method = RequestMethod.POST,value = RequestURLIOT.IotNodeAction.MONTH_DATA)
    public @ResponseBody JSONArray month_data(String user_id){
        IotSceneInfoBO sceneInfo = new IotSceneInfoBO();
        sceneInfo.setUser_id(Integer.parseInt(user_id));
        return iotNodeActionService.month_data(sceneInfo);
    }
}
