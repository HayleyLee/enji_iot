package com.enji_iot.develop.Service.impl;

import com.enji_iot.develop.DAO.IotHistoryNodeInfoMapper;
import com.enji_iot.develop.DAO.IotNodeActionMapper;
import com.enji_iot.develop.DAO.IotSceneInfoMapper;
import com.enji_iot.develop.Service.IotNodeActionService;
import com.enji_iot.util.Entity.bo.IotHistoryNodeDataBO;
import com.enji_iot.util.Entity.bo.IotNodeActionBO;
import com.enji_iot.util.Entity.bo.IotSceneInfoBO;
import com.enji_iot.util.Util.ResponseType;
import com.enji_iot.util.Util.ResponseUtil;
import com.enji_iot.util.Util.TimeUtil;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@Service("IotNodeActionService")
public class IotNodeActionServiceImpl implements IotNodeActionService {
    @Resource
    private IotNodeActionMapper iotNodeActionMapper;
    @Resource
    private IotSceneInfoMapper iotSceneInfoMapper;
    @Resource
    private IotHistoryNodeInfoMapper iotHistoryNodeInfoMapper;

    @Override
    public JSONArray month_count(IotSceneInfoBO iotSceneInfo) {
        List<IotSceneInfoBO> sceneList = iotSceneInfoMapper.selects(iotSceneInfo);
        if(sceneList.size()>0){
            int count = 0;
            for(IotSceneInfoBO scene : sceneList){
                IotNodeActionBO nodeAction = new IotNodeActionBO();
                nodeAction.setScene_id(scene.getId());
                count += iotNodeActionMapper.selectCount(nodeAction);
            }
            return ResponseUtil.getResponse(ResponseType.SUCCESS_ID,ResponseType.SUCCESS_MESSAGES,count);
        }
        else return ResponseUtil.getResponse(ResponseType.SUCCESS_ID,ResponseType.SUCCESS_MESSAGES,0);
    }

    @Override
    public JSONArray month_data(IotSceneInfoBO sceneInfo) {
        List<IotSceneInfoBO> sceneList = iotSceneInfoMapper.selects(sceneInfo);
        if(sceneList.size()>0){
            List<IotHistoryNodeDataBO> historyList = new ArrayList<>();
            List<IotNodeActionBO> node_action_list = new ArrayList<>();
            for(IotSceneInfoBO iot_scene : sceneList){
                IotHistoryNodeDataBO historyNodeData = new IotHistoryNodeDataBO();
                historyNodeData.setScene_id(iot_scene.getId());
                historyList.addAll(iotHistoryNodeInfoMapper.select(historyNodeData));
                IotNodeActionBO iotNodeAction = new IotNodeActionBO();
                iotNodeAction.setScene_id(iot_scene.getId());
                node_action_list.addAll(iotNodeActionMapper.select(iotNodeAction));
            }
            int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            String[] time_arrays = new String[day];
            Integer[] history_arrays = new Integer[day];
            Arrays.fill(history_arrays,0);
            Integer[] node_action_arrays = new Integer[day];
            Arrays.fill(node_action_arrays,0);
            String year = Integer.toString(TimeUtil.getNowYear());
            String month = TimeUtil.toString(TimeUtil.getNowMonth());
            for(int i=0;i<time_arrays.length;i++){
                if(i+1<10){
                    time_arrays[i] = "0"+(i+1);
                }
                else time_arrays[i] = Integer.toString(i+1);
                String time = year+":"+month+":"+time_arrays[i];
                for(IotHistoryNodeDataBO historyNode : historyList){
                    if(historyNode.getTime().contains(time)){
                        history_arrays[i]+=1;
                    }
                }
                for(IotNodeActionBO nodeAction : node_action_list){
                    if(nodeAction.getTime().toString().contains(time)){
                        node_action_arrays[i]+=1;
                    }
                }
            }
            return ResponseUtil.getResponse(ResponseType.SUCCESS_ID,ResponseType.SUCCESS_MESSAGES,Arrays.asList(time_arrays,history_arrays,node_action_arrays));
        }
        else return ResponseUtil.getResponse(ResponseType.SUCCESS_ID,ResponseType.SUCCESS_MESSAGES,0);
    }
}
