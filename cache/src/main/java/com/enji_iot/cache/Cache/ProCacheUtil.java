package com.enji_iot.cache.Cache;

import com.enji_iot.cache.Service.EhcacheUtil;
import com.enji_iot.util.Entity.bo.IotTriggerInfoBO;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProCacheUtil {
	
	/**
	 * 临时存储触发条件带时间的触发器（触发是判断是否需要触发）
	 */
	public static Map<Integer, IotTriggerInfoBO> timeTirggerListCache = new ConcurrentHashMap<Integer, IotTriggerInfoBO>();
	
	
	public static void addCache(String cacheName , String key ,Object o ){
		EhcacheUtil.put(cacheName, key, o);
	}
	
	public static <T> T getCache(String cacheName , String key,T a){
		return (T) EhcacheUtil.get(cacheName, key);
	}
	
	/**
	 * 这边作为一个优化补丁
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public static <T> T getCache(String cacheName , String key){
		return (T) EhcacheUtil.get(cacheName, key);
	}
	
	public static void removeCache(String cacheName , String key){
		EhcacheUtil.remove(cacheName, key);
	}
	
	public static void removeAll(String cacheName ){
		EhcacheUtil.removeAll(cacheName);
	}

}
