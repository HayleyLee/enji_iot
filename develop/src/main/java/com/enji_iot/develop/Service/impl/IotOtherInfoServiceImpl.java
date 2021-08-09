package com.enji_iot.develop.Service.impl;

import com.enji_iot.develop.DAO.*;
import com.enji_iot.util.Common.CodeIot;
import com.enji_iot.util.Entity.bo.*;
import com.enji_iot.develop.Service.IotOtherInfoService;
import com.enji_iot.util.Util.ObjectUtil;
import com.enji_iot.util.Util.ResultMapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(value = "IotOtherInfoService")
public class IotOtherInfoServiceImpl implements IotOtherInfoService {
	@Resource
	private IotSceneInfoMapper iotSceneInfoMapper;
	@Resource
	private IotTriggerInfoMapper iotTriggerInfoMapper;
	@Resource
	private UserInfoMapper userInfoMapper;
	@Resource
	private IotNodeInfoMapper iotNodeInfoMapper;
	@Resource
	private IotAlarmInfoMapper iotAlarmInfoMapper;

	@Override
	public Map<String, Object> getAppStatusInfo(UserInfoBO user) {
		Map<String,Object> resultMap = ResultMapUtils.getResultMap();
		try{
			// 场景数据
			IotSceneInfoBO obj = new IotSceneInfoBO();
			obj.setUser_id( user.getId() );
//			Integer num = dao.selectCount("IotSceneInfo.selectCount", obj);
			Integer num = iotSceneInfoMapper.selectCount(obj);
			if(ObjectUtil.isEmpty(num)){
				num = 0;
			}
			resultMap.put("sceneNum", num);
			// 传感器数
//			IotSensorInfoBO iotSensorInfo = new IotSensorInfoBO();
//			iotSensorInfo.setUser_id(user.getId());
//			Integer sensorNum = dao.selectCount("IotSensorInfo.selectPageCount", iotSensorInfo);
//			if(ObjectUtil.isEmpty(sensorNum)){
//				sensorNum = 0;
//			}
//			resultMap.put("sensorNum", sensorNum);
			// 触发器数
			IotTriggerInfoBO triggerInfo = new IotTriggerInfoBO();
			triggerInfo.setUser_id(user.getId());
//			Integer triggerNum = dao.selectCount("IotTriggerInfo.selectPageCount", triggerInfo);
			Integer triggerNum = iotTriggerInfoMapper.selectPageCount(triggerInfo);
			if(ObjectUtil.isEmpty(triggerNum)){
				triggerNum = 0;
			}
			resultMap.put("triggerNum", triggerNum);
			// 子账户数
			UserInfoBO userInfo = new UserInfoBO();
			userInfo.setAid(user.getId());
//			Integer subUserNum = dao.selectCount("UserInfo.selectCount", userInfo);
			Integer subUserNum = userInfoMapper.selectCount(userInfo);
			if(ObjectUtil.isEmpty(subUserNum)){
				subUserNum = 0;
			}
			resultMap.put("subUserNum", subUserNum);
			// 设备总数 设备离线数
			IotNodeInfoBO nodeInfo = new IotNodeInfoBO();
			nodeInfo.setUser_id(user.getId());
//			List<CommonInfoBO> nodeNum = dao.selectList("IotNodeInfo.selectNodeNumGroup", nodeInfo);
			List<CommonInfoBO> nodeNum = iotNodeInfoMapper.selectNodeNumGroup(nodeInfo);
			int onlineNum =0,offlineNum=0 ;
			for(CommonInfoBO in : nodeNum){
				if( in.getParam() == CodeIot.DEVICE_STATUS.ONLINE){
					onlineNum+=in.getNum();
				}else{
					offlineNum += in.getNum() ;
				}
			}
			resultMap.put("onlineNum", onlineNum);
			resultMap.put("offlineNum", offlineNum);
			//今日报警数和已处理数量
			IotAlarmInfoBO alarmInfo = new IotAlarmInfoBO();
			alarmInfo.setUser_id(user.getId());
			alarmInfo.setAtime(new Date());
//			List<CommonInfoBO> alarmNum =dao.selectList("IotAlarmInfo.selectAlarmNumGroup", alarmInfo);
			List<CommonInfoBO> alarmNum =iotAlarmInfoMapper.selectAlarmNumGroup(alarmInfo);
			int solveNum =0 , unsolveNum = 0;
			for(CommonInfoBO in : alarmNum){
				if( in.getParam() == CodeIot.PROCESS_STATUS.NO){
					unsolveNum+=in.getNum();
				}else{
					solveNum += in.getNum() ;
				}
			}
			resultMap.put("solveNum", solveNum);
			resultMap.put("unsolveNum", unsolveNum);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}
}
