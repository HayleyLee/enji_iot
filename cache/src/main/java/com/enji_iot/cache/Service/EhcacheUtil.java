package com.enji_iot.cache.Service;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EhcacheUtil {
	
	private static CacheManager manager;
	@Autowired
	public void setManager(CacheManager manager) {
		EhcacheUtil.manager = manager;
	}
	public static CacheManager getCacheManager(){
		return manager;
	}
	public static void put(String cacheName, String key, Object value) {
		Cache cache = manager.getCache(cacheName);
		Element element = new Element(key, value);
		cache.put(element);
	}

	public static Object get(String cacheName, String key) {
		Cache cache = manager.getCache(cacheName);
		Element element = cache.get(key);
		return element == null ? null : element.getObjectValue();
	}

	public static Cache getCache(String cacheName) {
		return manager.getCache(cacheName);
	}
	
	public static void remove(String cacheName, String key) {
		Cache cache = manager.getCache(cacheName);
		cache.remove(key);
	}
	
	public static void removeAll(String cacheName) {
		Cache cache = manager.getCache(cacheName);
		cache.removeAll();
	}
}
