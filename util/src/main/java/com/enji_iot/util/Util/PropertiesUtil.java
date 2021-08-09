package com.enji_iot.util.Util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;


public class PropertiesUtil {
	
	private static Map<String,Properties> mp = new HashedMap();
	
	private static Properties p=null;
	
	private static String filePath="config/config.properties";

	public static String getProperty(String property){
		if(p==null)
			p = PropertiesUtil.getProperties(filePath);
		return (p.containsKey(property)) ? p.getProperty(property) : "";
	}
	
	public static String getProperty(String filePathName,String property){
		if( StringUtils.isBlank(filePathName) ){
			return "" ;
		}
		filePathName = "config/"+ filePathName +".properties";
		if(mp.containsKey(filePathName)){
			return (mp.get(filePathName).containsKey(property)) ? mp.get(filePathName).getProperty(property) : "";
		}else{
			Properties tmp = PropertiesUtil.getProperties(filePathName);
			mp.put(filePathName, tmp);
			return (tmp.containsKey(property)) ? tmp.getProperty(property) : "";						
		}
	}
	
	public static Properties getProperties(String filePath){
		InputStream in=null ;

		Properties p = new Properties(); 
		try {
			in=PropertiesUtil.class.getClassLoader().getResourceAsStream(filePath);
			p.load(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return p;
	}

}
