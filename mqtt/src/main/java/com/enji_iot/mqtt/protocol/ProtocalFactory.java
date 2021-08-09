package com.enji_iot.mqtt.protocol;

import com.enji_iot.util.Util.LogUtil;

import java.util.HashMap;
import java.util.Map;

public class ProtocalFactory {
	
	// 对象列表
	private static Map<String, Iprotocal> objectList = new HashMap<>();
	
	/**
	 * 根据路径新建对象
	 * @param path
	 * @return
	 */
	public static Iprotocal getInstance(String path) {
    	if( objectList.containsKey(path) ){
    		return objectList.get(path);
    	}else{
    		try {
				Class<Iprotocal> classObject =  (Class<Iprotocal>) Class.forName("com.lp.mqtt.protocol." + path);
				Iprotocal iprotocal = classObject.newInstance() ;
				objectList.put(path,iprotocal ) ;
				return iprotocal ;
			} catch (Exception e) {
				LogUtil.errorLog(e);
			}
    	}
    	return null;
    }
	
}
