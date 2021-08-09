package com.enji_iot.cache.Service;

import com.enji_iot.cache.Cache.CacheName;
import com.enji_iot.cache.Cache.ProCacheUtil;
import com.enji_iot.cache.DAO.CacheMapper;
import com.enji_iot.util.Config.ProConfig;
import com.enji_iot.util.Entity.bean.IotLpmInfo;
import com.enji_iot.util.Entity.bo.*;
import com.enji_iot.util.Util.ObjectUtil;
import com.enji_iot.util.Util.ResultMapUtils;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service("ProCache")
@DependsOn(value = "ehcacheUtil")
@EnableScheduling
public class ProCache extends ResultMapUtils {

	@Resource
	private CacheMapper cacheMapper;
	
	@Autowired
	private TaskExecutor taskExecutor ;
	
	@PostConstruct
	protected void initCache() throws Exception {
		// 数据字典
		taskExecutor.execute(new DictionaryThread(cacheMapper));
		// 用户缓存
		taskExecutor.execute(new UserInfoThread(cacheMapper));
		// LPM 中间件缓存
		taskExecutor.execute(new LpmInfoThread(cacheMapper));
		// 节点缓存
		taskExecutor.execute(new IotNodeInfoThread(cacheMapper));
		// 场景缓存
		taskExecutor.execute(new IotSceneInfoThread(cacheMapper));
		// 视频信息缓存
		taskExecutor.execute(new IotVideoInfoThread(cacheMapper));
		// 触发器缓存信息
		taskExecutor.execute(new IotNodeSensorTriggerThread(cacheMapper));
		// 用户缓存信息
		taskExecutor.execute(new UserAccountThread(cacheMapper));
		// 系统信息初始化
		taskExecutor.execute(new SysInfoInit(cacheMapper));
		//设备心跳缓存添加
		taskExecutor.execute(new HandBertInit(cacheMapper));
		// 设备数据
		taskExecutor.execute(new IotSensorDevInfoThread(cacheMapper));
		//设备传感点单位缓存
		taskExecutor.execute(new IotNodeUnitDataThread(cacheMapper));
	}
	@Scheduled(cron="0/10 * *  * * ? ")
	public void HandBertInit(){
		List<IotNodeInfoBO> iotNodeInfoList = cacheMapper.iot_node_info_selectList();
		for(IotNodeInfoBO nodeInfo : iotNodeInfoList){
			long cache = ProCacheUtil.getCache(CacheName.NODE_KEEPALIVE, nodeInfo.getDevice_code());
			long sysMillis = System.currentTimeMillis();
			long time = sysMillis - cache;
			System.err.println(time+"   time");
			if(time<180000) {
				nodeInfo.setIot_node_status(16);
			}
			else {
				nodeInfo.setIot_node_status(17);
				//触发器
			}
//			Integer update = baseDao.update("IotNodeInfo.updateNodeStatus", nodeInfo);
			Integer update = cacheMapper.iot_node_info_updateNodeStatus(nodeInfo);
			if (update>0){
				//更新缓存
//				List<IotNodeInfoBO> iotNodeInfoLists = baseDao.selectList("IotNodeInfo.select", new IotNodeInfoBO());
				List<IotNodeInfoBO> iotNodeInfoLists = cacheMapper.iot_node_info_selectList();

				for (IotNodeInfoBO nodei: iotNodeInfoLists) {
					ProCacheUtil.addCache(CacheName.NODEINFO,nodei.getId().toString(),nodei);
				}
			}
			//todo 刷新前端
//			MqttService. pubMessage("1", "/scene/update/"+  nodeInfo.getScene_id());
		}
	}
	/**
	 * 系统信息初始化
	 * @author chenrj
	 *
	 */
	public class SysInfoInit implements Runnable {
		private CacheMapper cacheMapper;
		public SysInfoInit(CacheMapper cacheMapper) {
			super();
			this.cacheMapper = cacheMapper;
		}
		@Override
		public void run() {
//			List<SysConfigInfoBO> sysList = baseDao.selectList("SysConfigInfo.select", new SysConfigInfoBO());
			List<SysConfigInfoBO> sysList = cacheMapper.sys_config_info_selectList();
			Map<String,String> map = new HashedMap();
			for(SysConfigInfoBO obj :sysList ){
				map.put(obj.getName(), obj.getValue());
			}
			// 更新配置信息
			ProConfig.LOCAL_DOMAIN = map.get("server.domain" );
			ProConfig.IMAGE_DOMAIN = map.get( "server.image.domain" );
			ProConfig.LOCAL_FILE_PATH = map.get("server.file.local.path"  );
			
			ProConfig.PROJECT_NAME = map.get("sys.borwser.name" );
			ProConfig.SYS_WEB_LOGIN_NAME = map.get("sys.web.login.name" );
			ProConfig.SYS_APP_LOGIN_NAME = map.get("sys.app.login.name" );
			ProConfig.SYS_INFO_NAME = map.get("sys.info.name" );
			
			ProConfig.SYS_TECH_HELP = map.get("sys.tech.help") ;
			
			ProConfig.SYS_BEIAN_NAME = map.get("sys.beian.name");
			
			ProConfig.APP_TIME_TASK_PERIOD = map.get("app.time.task.period" );
		}
	}
	/**
	 * 手动刷新整个缓存
	 */
	public void refleshCache(String name){
		if(CacheName.DICTIONARY.equals(name)){
			ProCacheUtil.removeAll(name);
			ProCacheUtil.removeAll(CacheName.DICTIONARY_RELATION);
			// 数据字典
			taskExecutor.execute(new DictionaryThread(cacheMapper));
			
		}else if("sysInfo".equals(name)){
			// 系统信息初始化
			taskExecutor.execute(new SysInfoInit(cacheMapper));
		}
	}
	/**
	 * 项目字典
	 */
	public class DictionaryThread implements Runnable {
		
		private CacheMapper cacheMapper;
		
		public DictionaryThread(CacheMapper cacheMapper) {
			super();
			this.cacheMapper = cacheMapper;
		}

		@Override
		public void run() {
//			List<ProDictionaryInfoBO> list = baseDao.selectList("ProDictionaryInfo.selectList", new ProDictionaryInfoBO());
			List<ProDictionaryInfoBO> list = cacheMapper.pro_dictionary_info_selectList();
			if(!ObjectUtil.isEmpty(list)){
				// 线性缓存
				for(ProDictionaryInfoBO obj : list){
					EhcacheUtil.put(CacheName.DICTIONARY, obj.getCode().toString() ,obj);
				}
				// 父子关系缓存 - 目前只处理二级关系 
				for(ProDictionaryInfoBO obj : list){
					if( ObjectUtil.isEmpty(obj.getP_code()) ){
						ProCacheUtil.addCache(CacheName.DICTIONARY_RELATION, obj.getCode().toString(), obj);
					}else{
						if(ObjectUtil.isNotEmpty(ProCacheUtil.getCache(CacheName.DICTIONARY_RELATION,obj.getP_code().toString(),obj))){
							ProCacheUtil.getCache(CacheName.DICTIONARY_RELATION, 
										obj.getP_code().toString(), obj).getSub().add(obj);
						}
					}
				}
			}
		}
	}
	/**
	 * 用户账户表缓存
	 * @author chenrj
	 *
	 */
	public class UserAccountThread implements Runnable {
		private CacheMapper cacheMapper;
		
		public UserAccountThread(CacheMapper cacheMapper) {
			super();
			this.cacheMapper = cacheMapper;
		}

		@Override
		public void run() {
//			List<UserAccountInfoBO> list = baseDao.selectList("UserAccountInfo.select", new UserAccountInfoBO());
			List<UserAccountInfoBO> list = cacheMapper.user_account_info_selectList();
			if(!ObjectUtil.isEmpty(list)){
				for(UserAccountInfoBO obj : list){
					ProCacheUtil.addCache(CacheName.USERACCOUNT_ID, obj.getUser_id().toString() ,obj);
				}
			}
		}
	}
	/**
	 * 用户缓存
	 */
	public class UserInfoThread implements Runnable {
		
		private CacheMapper cacheMapper;
		
		public UserInfoThread(CacheMapper cacheMapper) {
			super();
			this.cacheMapper = cacheMapper;
		}

		@Override
		public void run() {
//			List<UserInfoBO> list = baseDao.selectList("UserInfo.selects", null);
			List<UserInfoBO> list = cacheMapper.user_info_selectList();
			if(!ObjectUtil.isEmpty(list)){
				for(UserInfoBO obj : list){
					ProCacheUtil.addCache(CacheName.USERINFO, obj.getUser_key() ,obj);
					ProCacheUtil.addCache(CacheName.USERINFO_OPENID, obj.getWx_open_id(), obj);
				}
			}
		}
		
	}
	/**
	 * lpm 缓存
	 */
	public class LpmInfoThread implements Runnable{
		
		private CacheMapper cacheMapper;
		public LpmInfoThread(CacheMapper cacheMapper) {
			super();
			this.cacheMapper = cacheMapper;
		}
		
		@Override
		public void run() {
//			List<IotLpmInfo> iotLpmList = baseDao.selectList("IotLpmInfo.select", new IotLpmInfo());
			List<IotLpmInfo> iotLpmList = cacheMapper.iot_lpm_info_selectList();
			if( ObjectUtil.isNotEmpty(iotLpmList) ){
				for(IotLpmInfo obj: iotLpmList){
					ProCacheUtil.addCache(CacheName.LPMINFO, obj.getLpm_key(), obj);
				}
			}
		}
	}
	/**
	 * Scene 缓存
	 */
	public class IotSceneInfoThread implements Runnable{
		
		private CacheMapper cacheMapper;
		public IotSceneInfoThread(CacheMapper cacheMapper) {
			super();
			this.cacheMapper = cacheMapper;
		}
		
		@Override
		public void run() {
//			List<IotSceneInfoBO> iotSceneInfo = baseDao.selectList("IotSceneInfo.select", new IotSceneInfoBO());
			List<IotSceneInfoBO> iotSceneInfo = cacheMapper.iot_scene_info_selectList();
			if( ObjectUtil.isNotEmpty(iotSceneInfo) ){
				for(IotSceneInfoBO obj: iotSceneInfo){
					ProCacheUtil.addCache(CacheName.SCENEINFO, obj.getId().toString(), obj);
				}
			}
		}
	}
	/**
	 * Node 缓存
	 */
	public class IotNodeInfoThread implements Runnable{
		
		private CacheMapper cacheMapper;
		public IotNodeInfoThread(CacheMapper cacheMapper) {
			super();
			this.cacheMapper = cacheMapper;
		}
		
		@Override
		public void run() {
//			List<IotNodeInfoBO> iotNodeInfoList = baseDao.selectList("IotNodeInfo.select", new IotNodeInfoBO());
			List<IotNodeInfoBO> iotNodeInfoList = cacheMapper.iot_node_info_selectList();
			if( ObjectUtil.isNotEmpty(iotNodeInfoList) ){
				for(IotNodeInfoBO obj: iotNodeInfoList){
					ProCacheUtil.addCache(CacheName.NODEINFO, obj.getId().toString(), obj);
					ProCacheUtil.addCache(CacheName.NODEINFO_DEVICECODE, obj.getDevice_code(), obj);
				}
			}
		}
	}
	/**
	 * node trigger List 缓存
	 */
	public class IotNodeSensorTriggerThread implements Runnable{
		private CacheMapper cacheMapper;
		public IotNodeSensorTriggerThread(CacheMapper cacheMapper) {
			super();
			this.cacheMapper = cacheMapper;
		}
		
		@Override
		public void run() {
			IotTriggerInfoBO triggerInfo = new IotTriggerInfoBO();
//			triggerInfo.setIot_trigger_condition_type(280);
//			List<IotTriggerInfoBO> iotTriggerInfoBOList = baseDao.selectList("IotTriggerInfo.select", triggerInfo) ;
			List<IotTriggerInfoBO> iotTriggerInfoBOList = cacheMapper.iot_trigger_info_selectList();
			if( ObjectUtil.isNotEmpty(iotTriggerInfoBOList) ){
				for(IotTriggerInfoBO obj: iotTriggerInfoBOList){
//					obj.setIs_worked(true);
					List<IotTriggerInfoBO> objs = ProCacheUtil.getCache(CacheName.NODETRIGGERINFO, obj.getNode_id().toString()) ;
					if( ObjectUtil.isNotEmpty( objs ) ){
						objs.add(obj);
					}else{
						objs = new ArrayList<>();
						objs.add(obj);
						ProCacheUtil.addCache(CacheName.NODETRIGGERINFO, obj.getNode_id().toString() , objs);
					}
				}
			}
		}
	}
	/**
	 * Video 缓存
	 */
	public class IotVideoInfoThread implements Runnable{
		
		private CacheMapper cacheMapper;
		public IotVideoInfoThread(CacheMapper cacheMapper) {
			super();
			this.cacheMapper = cacheMapper;
		}
		
		@Override
		public void run() {
//			List<IotVideoInfoBO> iotVideoInfoBOList = baseDao.selectList("IotVideoInfo.select", new IotVideoInfoBO());
			List<IotVideoInfoBO> iotVideoInfoBOList = cacheMapper.iot_video_info_selectList();
			if( ObjectUtil.isNotEmpty(iotVideoInfoBOList) ){
				for(IotVideoInfoBO obj: iotVideoInfoBOList){
					ProCacheUtil.addCache(CacheName.VIDEO_INFO, obj.getApp_name(), obj);
				}
			}
		}
	}
	/**
	 * 心跳缓存添加
	 * @Author TsinghuaLee
	 */
	public static class HandBertInit implements Runnable{

		private CacheMapper cacheMapper;

		public HandBertInit(CacheMapper cacheMapper) {
			super();
			this.cacheMapper = cacheMapper;
		}

		@Override
		public void run() {
			List<IotNodeInfoBO> iotNodeInfoList = cacheMapper.iot_node_info_selectList();
			for(IotNodeInfoBO nodeInfo : iotNodeInfoList){
				ProCacheUtil.addCache(CacheName.NODE_KEEPALIVE,nodeInfo.getDevice_code(),0L);
			}
		}
	}
	/**
	 * 设备传感数据缓存
	 */
	public class IotSensorDevInfoThread implements Runnable{

		private CacheMapper cacheMapper;
		public IotSensorDevInfoThread(CacheMapper cacheMapper) {
			super();
			this.cacheMapper = cacheMapper;
		}

		@Override
		public void run() {
//			List<IotNodeInfoBO> iotNodeInfoList = baseDao.selectList("IotNodeInfo.select", new IotNodeInfoBO());
			List<IotNodeInfoBO> iotNodeInfoList = cacheMapper.iot_node_info_selectList();
			for(IotNodeInfoBO nodeInfo : iotNodeInfoList){
				ProCacheUtil.addCache(CacheName.SENSOR_DEV_INFO, nodeInfo.getDevice_code(),new JSONObject());
			}
		}
	}
	/**
	 * 设备单位缓存
	 */
	public class IotNodeUnitDataThread implements Runnable{

		private CacheMapper cacheMapper;
		public IotNodeUnitDataThread(CacheMapper cacheMapper) {
			super();
			this.cacheMapper = cacheMapper;
		}

		@Override
		public void run() {
//			List<IotNodeUnitDataBO> iotUnitList = baseDao.selectList("IotNodeInfo.selectNodeUnitList", new IotNodeUnitDataBO());
			List<IotNodeUnitDataBO> iotUnitList = cacheMapper.iot_node_unit_data_selectList();
			ProCacheUtil.addCache(CacheName.IOT_NODE_UNIT,"unitList" ,iotUnitList);
			for(IotNodeUnitDataBO nodeUnit : iotUnitList){
				//用于触发器
				String key = nodeUnit.getScene_id() + nodeUnit.getDevice_code() + nodeUnit.getSensor_name();
				ProCacheUtil.addCache(CacheName.IOT_NODE_UNIT,key ,nodeUnit);
			}
		}
	}
}
