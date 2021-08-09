package com.enji_iot.util.Util;

import java.util.List;

import com.alibaba.fastjson.JSON;

import net.sf.json.JSONObject;

public class JsonUtils {

	/**
	 * 提取LSP的http response的data数据,并转化为对象/对象列表
	 * 
	 * @param jsonStr
	 * @param target
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T convertLspJsonStr2Object(String jsonStr, Class<?> target) {
		try {
			Object obj = JSON.parse(jsonStr);
			Integer status = (Integer) ((com.alibaba.fastjson.JSONObject) obj).get("status") ;
			if(status == 10001){
				if(((com.alibaba.fastjson.JSONObject) obj).get("data") instanceof List){
					com.alibaba.fastjson.JSONArray datas = (com.alibaba.fastjson.JSONArray) ((com.alibaba.fastjson.JSONObject) obj).get("data") ;
					return  (T) com.alibaba.fastjson.JSONObject.parseArray(datas.toJSONString(), target); 
				}else{
					com.alibaba.fastjson.JSONObject data = (com.alibaba.fastjson.JSONObject) ((com.alibaba.fastjson.JSONObject) obj).get("data") ;
					return  (T) data.toJavaObject(target) ;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * json对象/列表转化为java对象/列表
	 * 
	 * @param jsonStr
	 * @param target
	 * @return
	 */
	public static <T> T convertJsonStr2Object(String jsonStr, Class<?> target) {
		Object obj = JSON.parse(jsonStr);
		if(obj instanceof List){
			// 转换列表
			return (T) JSON.parseArray(jsonStr, target);
		}else{
			// 转换对象
			return (T) JSON.parseObject(jsonStr, target);
		}
	}
	
	public static void main(String[] args) {
//		String gameListStr = "[{\"gameId\":\"1\",\"gameName\":\"哈哈\"},{\"gameId\":\"2\",\"gameName\":\"呵呵\"}]";
		String gameListStr = "{\"status\":10001, data:[{\"gameId\":\"1\",\"gameName\":\"哈哈\"},{\"gameId\":\"2\",\"gameName\":\"呵呵\"}] }";
//		List<BaseBean> o = convertLspJsonStr2Object(gameListStr,BaseBean.class);
//		System.out.println(o);
	}
	
	
	
	/**
	 * json格式转换为对象
	 * @param json
	 * @param target
	 * @return
	 */
	public static Object json2Object(String json,Class<?> target){
		Object obj=null;
		try{
			JSONObject jo = JSONObject.fromObject(json);
			obj=JSONObject.toBean(jo,target);
		}catch(Exception e){
			LogUtil.errorLog(e.getMessage());
		}
		return obj;
	}
	
	/**
	 * json格式转换为对象
	 * @param json
	 * @param key
	 * @param target
	 * @return
	 */
	public static Object json2Object(String json,String key,Class<?> target){
		Object obj=null;
		try{
			JSONObject jo = JSONObject.fromObject(json);
			obj=JSONObject.toBean(jo.getJSONObject(key),target);
			
		}catch(Exception e){
			LogUtil.errorLog(e.getMessage());
		}
		return obj;
	}
	
	
	/**
	 * 获取 JSONObject对象 
	 * @param json
	 * @return
	 */
	public static JSONObject getJSONObject(String json){
		JSONObject result=null;
		try{
			result = JSONObject.fromObject(json);
		}catch(Exception e){
			LogUtil.errorLog(e.getMessage());
		}
		return result;
	}
	/**
	 * 获取 JSONObject对象 
	 * @param json
	 * @param key
	 * @return
	 */
	public static JSONObject getJSONObject(String json,String key){
		JSONObject result=null;
		try{
			JSONObject jo = JSONObject.fromObject(json);
			result=jo.getJSONObject(key);
		}catch(Exception e){
			LogUtil.errorLog(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 获取值
	 * @param json
	 * @param key
	 * @return
	 */
	public static Object getValue(JSONObject jo,String key){
		Object obj=null;
		if(jo==null)
			return obj;
		try{
			obj=jo.get(key);
		}catch(Exception e){
			LogUtil.errorLog(e.getMessage());
		}
		return obj;
	}
}
