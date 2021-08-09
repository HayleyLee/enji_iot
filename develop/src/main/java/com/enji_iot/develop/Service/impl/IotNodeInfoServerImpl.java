package com.enji_iot.develop.Service.impl;

import com.enji_iot.develop.DAO.IotNodeInfoMapper;
import com.enji_iot.develop.DAO.IotSceneInfoMapper;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Common.CodeIot;
import com.enji_iot.util.Common.Constants;
import com.enji_iot.util.Entity.bo.*;
import com.enji_iot.cache.Cache.CacheName;
import com.enji_iot.cache.Cache.ProCacheUtil;
import com.enji_iot.util.Config.ProConfig;
import com.enji_iot.mqtt.MqttService;
import com.enji_iot.mqtt.protocol.ProtocalFactory;
import com.enji_iot.develop.Service.IotNodeInfoService;
import com.enji_iot.util.Util.*;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.*;

@Service(value = "IotNodeInfoService")
public class IotNodeInfoServerImpl implements IotNodeInfoService {
	@Resource
	private IotNodeInfoMapper iotNodeInfoMapper;
	@Resource
	private IotSceneInfoMapper iotSceneInfoMapper;

	@Override
	public Map<String, Object> updateNodeStatus(IotNodeInfoBO obj) {
		Map<String,Object> resultMap = ResultMapUtils.getResultMap();
		try{
			IotNodeInfoBO nodeInfo = ProCacheUtil.getCache(CacheName.NODEINFO_DEVICECODE, obj.getDevice_code()  );
			if( ObjectUtil.isEmpty(nodeInfo) ){
				return resultMap ;
			}
			if(obj.getIot_node_status().equals(CodeIot.DEVICE_STATUS.ONLINE)){
				if( ObjectUtil.isNotEmpty(nodeInfo) ){
					if(ObjectUtil.isNotEmpty(obj.getLpmKey())){
						// 储存LPM 和 NodeId 添加session
						ProCacheUtil.addCache(CacheName.DEVICECODE_LPM, obj.getDevice_code(), obj.getLpmKey());						
					}
					List<IotNodeInfoBO> nodeInfotmp = iotNodeInfoMapper.selectNodeSensorList(nodeInfo);
					ResultMapUtils.putData(resultMap,nodeInfotmp) ;
					// 设置设备在线
					nodeInfo.setIot_node_status(16);

					//TODO 新增mqtt指令
					ProtocalFactory.getInstance(nodeInfo.getIot_protocal_category()).node_OnLine_OffLine(obj,1);

				}else{
					ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.NO_DATA);
				}
			}
			else{
				ProCacheUtil.removeCache(CacheName.DEVICECODE_LPM, obj.getDevice_code());
				// 更新node所有的传感器为离线状态
//				IotSensorInfoBO sensorBo = new IotSensorInfoBO();
//				sensorBo.setDevice_code(obj.getDevice_code());
//				sensorBo.setIot_sensor_status(CodeIot.DEVICE_STATUS.OFFLINE);
//				dao.update("IotSensorInfo.updateStatusByNode", sensorBo);
				// 设置设备缓存离线
				nodeInfo.setIot_node_status(17);

				//TODO 新增mqtt指令
				ProtocalFactory.getInstance(nodeInfo.getIot_protocal_category()).node_OnLine_OffLine(obj,0);
			}
			Integer num = iotNodeInfoMapper.updateNodeStatus(obj);
			if(num <=0){
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.NO_DATA);			
			}
			//更新缓存
			List<IotNodeInfoBO> iotNodeInfoLists = iotNodeInfoMapper.selectList(new IotNodeInfoBO());
			for (IotNodeInfoBO nodei: iotNodeInfoLists) {
				ProCacheUtil.addCache(CacheName.NODEINFO,nodei.getId().toString(),nodei);
			}
			// 网关是设备，更新了
//			String scene_id = ProCacheUtil.getCache(CacheName.SCENE_IPDATE_FLAG ,nodeInfo.getScene_id()+"" );
//			if(ObjectUtil.isNotEmpty(scene_id)){
				MqttService.pubMessage( "1", "/scene/update/"+  nodeInfo.getScene_id() );				
//			}
		}catch (Exception e) {
			e.printStackTrace();
			ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.ERROR);
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> saveNodeInfo(IotNodeInfoBO obj) {
		Map<String,Object> resultMap = ResultMapUtils.getResultMap();
		try{

			//
//			obj.setTemplate_id(obj.getDevice_template_id());
			if( ObjectUtil.isNotEmpty( obj.getCopy_device_code() ) ){
				// 则需要克隆添加，否则就正常添加
				// 查询缓存，获取设备的信息
				IotNodeInfoBO nodeInfo = ProCacheUtil.getCache(CacheName.NODEINFO_DEVICECODE, obj.getCopy_device_code()) ;
				if(ObjectUtil.isNotEmpty(nodeInfo)){
					obj.setIot_node_type(nodeInfo.getIot_node_type()  );
					obj.setIot_protocal_category(nodeInfo.getIot_protocal_category());
					obj.setSeq(1);
					obj.setIot_node_status(CodeIot.DEVICE_STATUS.UNCONTECT);
//					obj.setScene_id(nodeInfo.getScene_id());
					obj.setDelete_flag(0);
					obj.setInfos(nodeInfo.getInfos());
					obj.setFrequency(nodeInfo.getFrequency());
					obj.setMeasure_unit_type(nodeInfo.getMeasure_unit_type());
					obj.setIot_node_data_type(nodeInfo.getIot_node_data_type());
					obj.setStore_strage(nodeInfo.getStore_strage());
					obj.setFormula_up(nodeInfo.getFormula_up());
					obj.setFormula_down(nodeInfo.getFormula_down());
//					int row = dao.insert("IotNodeInfo.insert", obj);
					int row = iotNodeInfoMapper.insert(obj);
					if(row > 0){
						// 这边可以做一个生成二维码图片的
						// 增加二维码，提供微信公众号绑定 [ 增加新的文件储存隔离系统 ]
						try{
							QRCodeUtil.encode("device_id:"+ obj.getDevice_code()  ,null, 
									ProConfig.LOCAL_FILE_PATH  + Constants.FileRealPath.DEVICEPATH+"/"+ (int) (obj.getId()/100+1)*100
									, false,obj.getId()+"");
							
							String os = System.getProperty("os.name");  
							if(os.toLowerCase().startsWith("win")){  
									QRCodeUtil.addImgText( "SN:" + obj.getDevice_code() ,ProConfig.LOCAL_FILE_PATH +Constants.FileRealPath.DEVICEPATH+"/"
										+ (int) (obj.getId()/100+1)*100+"/"+obj.getId()+".jpg"  ,ProConfig.LOCAL_FILE_PATH  + Constants.FileRealPath.DEVICEPATH+"/"
												+ (int) (obj.getId()/100+1)*100+"/"+obj.getId()+".jpg",455,455  );
							}  
						}
						catch (Exception e) {
							e.printStackTrace();
						}
					}
					
//					if(row>0){
//						// 添加设备下的传感器和触发器
//						IotSensorInfoBO sensorBo = new IotSensorInfoBO();
//						sensorBo.setNode_id(nodeInfo.getId());
//						sensorBo.setData_type(-1);
//						List<IotSensorInfoBO> sensorList = dao.selectList("IotSensorInfo.select", sensorBo);
//						for(int i=0;i< sensorList.size() ; i++ ){
//							IotSensorInfoBO sensor = new IotSensorInfoBO() ;
//							sensor.setName(sensorList.get(i).getName() );
//							sensor.setMeasure_unit_type(sensorList.get(i).getMeasure_unit_type());
//							sensor.setIot_sensor_type(sensorList.get(i).getIot_sensor_type());
//							sensor.setIot_sensor_category(sensorList.get(i).getIot_sensor_category());
//							sensor.setNode_id(obj.getId());
//							sensor.setSensor_device_id(sensorList.get(i).getSensor_device_id() );
//							sensor.setPort_id( sensorList.get(i).getPort_id() );
//							sensor.setSdata(0f);
//							sensor.setStr_sdata("");
//							sensor.setSeq(i);
//							sensor.setDelete_flag(0);
//							sensor.setIot_sensor_status(17);
//							sensor.setRequest_sdata(0f);
//							sensor.setSdata_degree(sensorList.get(i).getSdata_degree());
//							sensor.setFormula_up(sensorList.get(i).getFormula_up());
//							sensor.setFormula_down(sensorList.get(i).getFormula_down());
//							sensor.setRegister_time(new Date());
//							sensor.setData_type( sensorList.get(i).getData_type() );
//							sensor.setParam_type( sensorList.get(i).getParam_type() );
//							sensor.setParam_names(sensorList.get(i).getParam_names());
//							sensor.setParam_config( sensorList.get(i).getParam_config() );
//							sensor.setInfos( sensorList.get(i).getInfos() );
//							sensor.setStore_strage(sensorList.get(i).getStore_strage());
//							sensor.setMtime(new Date());
//
//							int re = dao.insert("IotSensorInfo.insert", sensor) ;
//							if(re>0){
//								ProCacheUtil.addCache(CacheName.SENSORINFO, sensor.getId().toString(), sensor);
//								ProCacheUtil.addCache(CacheName.SENSORINFO_NSP, sensor.getNode_id()+"-"+
//													sensor.getSensor_device_id()+"-"+sensor.getPort_id(), sensor);
//							}
//
//							// 添加触发器
//							IotTriggerInfoBO trigger = new IotTriggerInfoBO() ;
//							trigger.setNode_id(nodeInfo.getId());
//							trigger.setFrom_sensor_id( sensorList.get(i).getId() );
//							List<IotTriggerInfoBO> triggerList = dao.selectList("IotTriggerInfo.select", trigger) ;
//							for(int j=0; j<triggerList.size() ; j++ ){
//								IotTriggerInfoBO triggerBo = new IotTriggerInfoBO() ;
//								triggerBo.setName( triggerList.get(j).getName() );
//								triggerBo.setFrom_sensor_id( sensor.getId() );
//								triggerBo.setIot_trigger_condition_type(triggerList.get(j).getIot_trigger_condition_type());
//								triggerBo.setIot_trigger_action_type( triggerList.get(j).getIot_trigger_action_type() );
//								triggerBo.setAction_params(triggerList.get(j).getAction_params()  );
//								triggerBo.setSeq(triggerList.get(j).getSeq());
//								triggerBo.setDelete_flag(triggerList.get(j).getDelete_flag());
//								triggerBo.setCondition_params(triggerList.get(j).getCondition_params());
//								triggerBo.setIot_trigger_alarm_level(triggerList.get(j).getIot_trigger_alarm_level());
//								triggerBo.setIot_trigger_alarm_flag(triggerList.get(j).getIot_trigger_alarm_flag());
//								// 触发器默认是暂停
//								triggerBo.setIot_trigger_status( CodeIot.IOT_TRIGGER_STATUS.STOP );
//								triggerBo.setTrigger_inteval_time(triggerList.get(j).getTrigger_inteval_time());
//								triggerBo.setLast_trigger_time(triggerList.get(j).getLast_trigger_time());
//								triggerBo.setNode_id( obj.getId() );
//								triggerBo.setRecovery( triggerList.get(j).getRecovery() );
//
//								int res = dao.insert("IotTriggerInfo.insert", triggerBo) ;
//								if(res >0){
//									// 更新触发器缓存
//									List<IotSensorInfoBO> list = dao.selectList("IotSensorInfo.selectSensorTriggerList", new IotSensorInfoBO(sensor.getId()));
//									if( ObjectUtil.isNotEmpty(list) ){
//										ProCacheUtil.addCache(CacheName.SENSORTRIGGERINFO, sensor.getNode_id()+"-"+
//												sensor.getSensor_device_id()+"-"+sensor.getPort_id(),  list.get(0));
//									}
//								}
//							}
//						}
//					}
				}else{
					// 设备号，不存在，需要返回给前台
					ResultMapUtils.putStatusCode(resultMap, CodeIot.ResponseCode.IotInfo.DEVICE_CODE_NOT_EXIST) ;
				}
			}else{
				System.err.println("else:");
//				int row = dao.insert("IotNodeInfo.insert", obj);
				iotNodeInfoMapper.insert(obj);
			}
			
			// 设备模板的业务，
//			if(row >0 && ObjectUtil.isNotEmpty(obj.getDevice_template_id())){
//				// 传感器列表
//				DeviceTemplateSensorInfoBO param = new DeviceTemplateSensorInfoBO();
//				param.setDevice_template_id(obj.getDevice_template_id());
//				List<DeviceTemplateSensorInfoBO> deviceTemplateList = dao.selectList("DeviceTemplateSensorInfo.select", param);
//				// 触发器列表
//				DeviceTemplateTiggerInfoBO param1 = new DeviceTemplateTiggerInfoBO();
//				param1.setDevice_template_id(obj.getDevice_template_id());
//				List<DeviceTemplateTiggerInfoBO> deviceTemplateTriggerList = dao.selectList("DeviceTemplateTiggerInfo.select", param1) ;
//				
//				for(int i=deviceTemplateList.size()-1; i>=0 ; i-- ){
//					DeviceTemplateSensorInfoBO tmp = deviceTemplateList.get(i);
//					
//					IotSensorInfoBO sensorInfo = new IotSensorInfoBO();
//					sensorInfo.setIot_sensor_status(CodeIot.DEVICE_STATUS.UNCONTECT);
//					sensorInfo.setRegister_time(new Date());
//					sensorInfo.setSdata(0f);
//					sensorInfo.setRequest_sdata(0f);
//					sensorInfo.setName(tmp.getName());
//					sensorInfo.setMeasure_unit_type(tmp.getMeasure_unit_type());
//					sensorInfo.setIot_sensor_type(tmp.getIot_sensor_type());
//					sensorInfo.setIot_sensor_category(tmp.getIot_sensor_category());
//					sensorInfo.setNode_id(obj.getId());
//					sensorInfo.setSensor_device_id(tmp.getSensor_device_id());
//					sensorInfo.setPort_id(tmp.getPort_id());
//					sensorInfo.setSdata_degree(tmp.getSdata_degree());
//					sensorInfo.setFormula_up(tmp.getFormula_up());
//					sensorInfo.setFormula_down(tmp.getFormula_down());
//					sensorInfo.setSeq( tmp.getSeq() );
//					sensorInfo.setInfos( tmp.getInfos() );
//					// 新增的
//					sensorInfo.setData_type(tmp.getData_type());
//					sensorInfo.setParam_names(tmp.getParam_names());
//					sensorInfo.setParam_type(tmp.getParam_type());
//					sensorInfo.setParam_config(tmp.getParam_config());
//					sensorInfo.setMtime(new Date());
//					// -- 通过模板增加，没有初始值
//					sensorInfo.setStr_sdata("0");
//					if(tmp.getIot_sensor_type() == CodeIot.SENSOR_TYPE.POSITION){
//						if( ObjectUtil.isNotEmpty( obj.getLonLat() )){
//							sensorInfo.setStr_sdata(obj.getLonLat() );
//						}
//					}
//					
//					
//					int re = dao.insert("IotSensorInfo.insert", sensorInfo) ;
//					if(re>0){
//						ProCacheUtil.addCache(CacheName.SENSORINFO, sensorInfo.getId().toString(), sensorInfo);
//						ProCacheUtil.addCache(CacheName.SENSORINFO_NSP, sensorInfo.getNode_id()+"-"+sensorInfo.getSensor_device_id()+"-"+sensorInfo.getPort_id(), sensorInfo);
//					}
//					
//					// 这边判断是否有该传感器关联的触发器，如果有，则需要添加触发器
//					for(int j =0;j<deviceTemplateTriggerList.size(); j++){
//						if( deviceTemplateTriggerList.get(j).getFrom_sensor_id() == tmp.getId() ){
//							DeviceTemplateTiggerInfoBO tmp1 = deviceTemplateTriggerList.get(j) ;
//							IotTriggerInfoBO triggerInfo = new IotTriggerInfoBO();
//							triggerInfo.setIot_trigger_status(CodeIot.IOT_TRIGGER_STATUS.NORMAL);
//							triggerInfo.setName( tmp1.getName() );
//							triggerInfo.setFrom_sensor_id( sensorInfo.getId() );
//							triggerInfo.setTo_sensor_id(tmp1.getTo_sensor_id());
//							triggerInfo.setIot_trigger_condition_type(tmp1.getIot_trigger_condition_type());
//							triggerInfo.setIot_trigger_action_type(tmp1.getIot_trigger_action_type());
//							triggerInfo.setAction_params(tmp1.getAction_params());
//							triggerInfo.setCondition_params(tmp1.getCondition_params());
//							triggerInfo.setIot_trigger_alarm_level(tmp1.getIot_trigger_alarm_level());
//							triggerInfo.setIot_trigger_alarm_flag(tmp1.getIot_trigger_alarm_flag());
//							triggerInfo.setTrigger_inteval_time(tmp1.getTrigger_inteval_time());
//							int res = dao.insert("IotTriggerInfo.insert", triggerInfo) ;
//							if(res >0){
//								// 更新触发器缓存
//								List<IotSensorInfoBO> list = dao.selectList("IotSensorInfo.selectSensorTriggerList", new IotSensorInfoBO(sensorInfo.getId()));
//								if( ObjectUtil.isNotEmpty(list) ){
//									ProCacheUtil.addCache(CacheName.SENSORTRIGGERINFO, sensorInfo.getNode_id()+"-"+ 
//											sensorInfo.getSensor_device_id()+"-"+sensorInfo.getPort_id(),  list.get(0));
//								}
//							}
//						}
//					}
//				}
//			}
		}catch (Exception e) {
			e.printStackTrace();
			ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.ERROR);
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> importTemplateNodeInfo(MultipartFile file) {
		Map<String,Object> resultMap = ResultMapUtils.getResultMap();
		try{
			
			String[] fields = {"name","device_code","copy_device_code"}; 
			List<IotImportNodeInfoBO> list = ExcelUtil.getExcelInfo(file, IotImportNodeInfoBO.class, 0, 3, fields, 2) ;
			// 不能重复，并且不能已存在，先判断，然后在开始添加，文件内设备不重复，文件外不能跟已有重复
			for(int i=0; i < list.size() ;i++){
				if( ObjectUtil.isEmpty(list.get(i).getDevice_code()) || ObjectUtil.isEmpty(list.get(i).getCopy_device_code()) ){
					continue ;
				}
				IotNodeInfoBO nodeInfo = ProCacheUtil.getCache(CacheName.NODEINFO_DEVICECODE, list.get(i).getDevice_code()) ;
				IotNodeInfoBO nodeInfoCopy = ProCacheUtil.getCache(CacheName.NODEINFO_DEVICECODE, list.get(i).getCopy_device_code()) ;
				if(ObjectUtil.isNotEmpty(nodeInfo)){
					// 该设备已存在
					ResultMapUtils.putStatusCode(resultMap, 10000);
					ResultMapUtils.putStatusMsg(resultMap, "设备号已存在，设备号为："+list.get(i).getDevice_code()  ) ;
					return resultMap ;
				}
				if(ObjectUtil.isEmpty(nodeInfoCopy)){
					// 
					ResultMapUtils.putStatusCode(resultMap, 10001);
					ResultMapUtils.putStatusMsg(resultMap, "类型设备号不存在，类型设备号为："+list.get(i).getCopy_device_code() ) ;
					return resultMap ;
				}
			}
			Set<String> nodeSet = new HashSet<>();
 			for(int i=0; i < list.size() ;i++){
				if( ObjectUtil.isEmpty(list.get(i).getDevice_code()) || ObjectUtil.isEmpty(list.get(i).getCopy_device_code()) ){
					continue ;
				}
				if(!nodeSet.contains(list.get(i).getDevice_code())){
					nodeSet.add(list.get(i).getDevice_code());
					IotNodeInfoBO nodeInfo = new IotNodeInfoBO();
					nodeInfo.setName(list.get(i).getName());
					nodeInfo.setDevice_code(list.get(i).getDevice_code());
					nodeInfo.setCopy_device_code(list.get(i).getCopy_device_code());
					nodeInfo.setMtime(new Date());
					nodeInfo.setDisable_flag(1);
					// 保存信息
					saveNodeInfo(nodeInfo) ;
					ProCacheUtil.addCache(CacheName.NODEINFO, nodeInfo.getId().toString(), nodeInfo);
					ProCacheUtil.addCache(CacheName.NODEINFO_DEVICECODE, nodeInfo.getDevice_code(), nodeInfo);
				}else{
					continue ;
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.ERROR);
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> saveNodeUnit(IotNodeUnitDataBO obj) {
		Map<String,Object> resultMap = ResultMapUtils.getResultMap();
		try {
			iotNodeInfoMapper.addUnit(obj);
		}catch (Exception e){
			e.printStackTrace();
			ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.ERROR);
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> selectPageList(IotNodeInfoBO iotNodeInfo, PageBean pageBean) {

		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			pageBean.setPageParam4Mysql(iotNodeInfo);
			List<IotNodeInfoBO> data = iotNodeInfoMapper.selectPage(iotNodeInfo);
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

	@Override
	public Map<String, Object> selectPage(IotNodeInfoBO iotNodeInfo, PageBean pageBean) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			int totalCount = iotNodeInfoMapper.selectPageCount(iotNodeInfo);
			if (totalCount > 0) {
				pageBean.setPageParam4Mysql(iotNodeInfo);
				List<?> list = iotNodeInfoMapper.selectPage(iotNodeInfo);
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
	public Map<String, Object> selectStatisticNodeInfo(IotNodeInfoBO iotNodeInfo) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			List<IotStatisticBO> data = iotNodeInfoMapper.selectStatisticNodeInfo(iotNodeInfo);
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

	@Override
	public Map<String, Object> selectNodeDevCode(IotNodeInfoBO iotNodeInfo) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			List<IotNodeInfoBO> data = iotNodeInfoMapper.selectNodeDevCode(iotNodeInfo);
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

	@Override
	public Map<String, Object> update(IotNodeInfoBO iotNodeInfo) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			int rows = iotNodeInfoMapper.update(iotNodeInfo);
			if (rows <= 0)  {
				return ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> deleteNode(IotNodeInfoBO iotNodeInfo) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			int rows = iotNodeInfoMapper.deleteNode(iotNodeInfo);
			if (rows == 0) {
				return ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> selectOne(IotNodeInfoBO iotNodeInfo) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			IotNodeInfoBO data = iotNodeInfoMapper.selectOne(iotNodeInfo);
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
	public Map<String, Object> selectUnUsedNode(IotNodeInfoBO iotNodeInfo, PageBean pageBean) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			int totalCount = iotNodeInfoMapper.selectUnUsedNodeCount(iotNodeInfo);
			if (totalCount > 0) {
				pageBean.setPageParam4Mysql(iotNodeInfo);
				List<?> list = iotNodeInfoMapper.selectUnUsedNode(iotNodeInfo);
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
	public List<IotNodeInfoBO> select(IotNodeInfoBO iotNodeInfo) {
		return iotNodeInfoMapper.select(iotNodeInfo);
	}

	@Override
	public List<IotNodeUnitDataBO> selectNodeUnitList(IotNodeUnitDataBO iotNodeUnitData) {
		return iotNodeInfoMapper.selectNodeUnitList(iotNodeUnitData);
	}

	@Override
	public Map<String, Object> selectNodeUnitList(IotNodeUnitDataBO iotNodeUnitData, PageBean... pageBean) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			List<IotNodeUnitDataBO> data = iotNodeInfoMapper.selectNodeUnitList(iotNodeUnitData);
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

	@Override
	public Map<String, Object> selectNodeSensorList(IotNodeInfoBO iotNodeInfo, PageBean pageBean) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			int totalCount = iotNodeInfoMapper.selectNodeSensorListCount(iotNodeInfo);
			if (totalCount > 0) {
				pageBean.setPageParam4Mysql(iotNodeInfo);
				List<?> list = iotNodeInfoMapper.selectNodeSensorList(iotNodeInfo);
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
	public Map<String, Object> deviceUnitUpdate(IotNodeUnitDataBO iotNodeUnitData) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			int rows = iotNodeInfoMapper.deviceUnitUpdate(iotNodeUnitData);
			if (rows <= 0)  {
				return ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> deviceUnitDelete(IotNodeUnitDataBO iotNodeUnitData) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			int rows = iotNodeInfoMapper.deviceUnitDelete(iotNodeUnitData);
			if (rows == 0) {
				return ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	@Override
	public JSONArray selectCount(IotSceneInfoBO sceneInfo) {
		List<IotSceneInfoBO> sceneList = iotSceneInfoMapper.selects(sceneInfo);
		if(sceneList.size()>0){
			Integer r = 0;
			for(IotSceneInfoBO scene : sceneList){
				IotNodeInfoBO nodeInfo = new IotNodeInfoBO();
				nodeInfo.setScene_id(scene.getId());
				r += iotNodeInfoMapper.selectCount(nodeInfo);
			}
			return ResponseUtil.getResponse(ResponseType.SUCCESS_ID,ResponseType.SUCCESS_MESSAGES,r);
		}
		else return ResponseUtil.getResponse(ResponseType.SUCCESS_ID,ResponseType.SUCCESS_MESSAGES,0);
	}
}
