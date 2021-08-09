package com.enji_iot.util.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enji_iot.util.Common.Code;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;

/**
 * 返回结果工具包
 *
 */
public class ResultMapUtils {
	
	public final static String PLAT_REPONSE_STATUS = "status";
	public final static String PLAT_REPONSE_STATUS_MSG = "statusMsg";
	public final static String PLAT_REPONSE_DATA = "data";
	public final static String USER_KEY = "USER-KEY";
	public final static String APP_KEY = "APP-KEY";
	public final static String CONTENT_TYPE = "Content-Type";
	public final static String APPLICATION_JSON = "application/json";
	
	/**
	 * 初始化返回状态码，数据MAP
	 * @return
	 */
	public static Map<String, Object> getResultMap() {
		Map<String, Object> data = new HashMap<String, Object>();
		// 设置返回数据
		data.put(PLAT_REPONSE_DATA, null);
		data.put(PLAT_REPONSE_STATUS, Code.ResponseCode.SystemCode.OK);
		return data;
	}
	
	
	/**
	 * 获取状态码
	 * @param resultMap
	 * @return
	 */
	public static int getStatusCode(Map<String,Object> resultMap){
		return (int) resultMap.get(PLAT_REPONSE_STATUS);
	}
	/**
	 * 获取数据
	 * @param resultMap
	 * @return
	 */
	public static <T> T getData(Map<String,Object> resultMap){
		return (T) resultMap.get(PLAT_REPONSE_DATA);
	}
	
	/**
	 * 修改状态码
	 * @param resultMap
	 * @param statusCode
	 * @return
	 */
	public static Map<String,Object> putStatusCode(Map<String,Object> resultMap,int statusCode){
		resultMap.put(PLAT_REPONSE_STATUS, statusCode);
		putStatusMsg(resultMap);
		return resultMap;
	}
	/**
	 * 修改状态码和数据
	 * @param resultMap
	 * @param statusCode
	 * @return
	 */
	public static Map<String,Object> putStatusCode(Map<String,Object> resultMap,int statusCode,Object data){
		resultMap.put(PLAT_REPONSE_STATUS, statusCode);
		resultMap.put(PLAT_REPONSE_DATA, data);
		putStatusMsg(resultMap);
		return resultMap;
	}
	/**
	 * 修改状态码
	 * @param resultMap
	 * @param source
	 * @return
	 */
	public static Map<String,Object> putStatusCode(Map<String,Object> resultMap,Map<String,Object> source){
		resultMap.put(PLAT_REPONSE_STATUS, source.get(PLAT_REPONSE_STATUS));
		putStatusMsg(resultMap);
		return resultMap;
	}
	/**
	 * 修改数据
	 * @param resultMap
	 * @param data
	 * @return
	 */
	public static Map<String,Object> putNullData(Map<String,Object> resultMap){
		resultMap.put(PLAT_REPONSE_DATA, null);
		return resultMap;
	}
	/**
	 * 修改数据
	 * @param resultMap
	 * @param data
	 * @return
	 */
	public static Map<String,Object> putData(Map<String,Object> resultMap,Object data){
		resultMap.put(PLAT_REPONSE_DATA, data);
		return resultMap;
	}
	/**
	 * 修改数据
	 * @param resultMap
	 * @param source
	 * @return
	 */
	public static Map<String,Object> putData(Map<String,Object> resultMap,Map<String,Object> source){
		resultMap.put(PLAT_REPONSE_DATA, source.get(PLAT_REPONSE_DATA));
		return resultMap;
	}
	
	/**
	 * 修改状态说明
	 * @param resultMap
	 * @param source
	 * @return
	 */
	public static Map<String,Object> putStatusMsg(Map<String,Object> resultMap){
//		ProDictionaryInfo obj =	ProCacheUtil.getCache(CacheName.DICTIONARY, getStatusCode(resultMap)+"", new ProDictionaryInfo());
//		if(ObjectUtil.isNotEmpty(obj)){
//			String msg= obj.getName();
//			if(!StringUtils.isEmpty(msg))
//				resultMap.put(PLAT_REPONSE_STATUS_MSG,msg);
//		}
		return resultMap;
	}
	
	/**
	 * 修改状态说明
	 * @param resultMap
	 * @param source
	 * @return
	 */
	public static Map<String,Object> putStatusMsg(Map<String,Object> resultMap,String msg){
		if(!StringUtils.isEmpty(msg))
			resultMap.put(PLAT_REPONSE_STATUS_MSG,msg);
		return resultMap;
	}
	
	/**
	 * 修改数据
	 * @param resultMap
	 * @param source
	 * @return
	 */
	public static Map<String,Object> putMapData(Map<String,Object> resultMap,Map<String,Object> data){
		resultMap.put(PLAT_REPONSE_DATA, data);
		return resultMap;
	}
	
	/**
	 * 系统是否成功 
	 * @param map
	 * @return
	 */
	public static boolean isOk(Map<String,Object> map){
		return (int)map.get(PLAT_REPONSE_STATUS)==Code.ResponseCode.SystemCode.OK;
	}
	/**
	 * 系统是否成功 
	 * @param map
	 * @return
	 */
	public static boolean isOk(int statusCode){
		return statusCode==Code.ResponseCode.SystemCode.OK;
	}
	
	
	/**
	 * 初始化返回状态码，数据MAP
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, Object> getResultMap(Object o) {
		Map<String, Object> data = new HashMap<String, Object>();
		// 设置返回数据
		data.put(PLAT_REPONSE_DATA, o);
		if (o != null
				|| (o instanceof List && o != null && ((List) o).size() != 0)) {
			// 设置返回状态
			data.put(PLAT_REPONSE_STATUS,
					Code.ResponseCode.SystemCode.OK);
		}
		return data;
	}
	
	/**
	 * 默认返回的数据视图空对象
	 */
	private final static ModelAndView DEFAULT_MODEL_VIEW = new ModelAndView(StringUtils.EMPTY, StringUtils.EMPTY,
			StringUtils.EMPTY);

	/**
	 * 设置响应请求头,返回数据视图
	 * 
	 * @param statusCode
	 * @param data
	 * @return
	 */
	public ModelAndView getModelAndView(HttpServletResponse response, Map<String, Object> responseData) {
		return new ModelAndView(StringUtils.EMPTY, StringUtils.EMPTY, responseData.getClass().cast(responseData));
	}

	/**
	 * 设置响应请求头,返回数据视图
	 * 
	 * @param statusCode
	 * @param data
	 * @return
	 */
	protected ModelAndView getModelAndView(HttpServletResponse response, int statusCode, Object data) {
		if (statusCode == Code.ResponseCode.SystemCode.OK && data != null) {
			return new ModelAndView(StringUtils.EMPTY, StringUtils.EMPTY, data.getClass().cast(data));
		}
		return DEFAULT_MODEL_VIEW;
	}

	/**
	 * 设置响应请求头,返回页面视图
	 * 
	 * @param statusCode
	 * @param data
	 * @return
	 */
	public ModelAndView getModelAndView(HttpServletResponse response, Map<String, Object> responseData,
			String viewName, String modelName) {
		int statusCode = getStatusCode(responseData);
		if (!isOk(statusCode)) {
			putNullData(responseData);
		}
		if (StringUtils.isBlank(viewName)) {
			viewName = StringUtils.EMPTY;
		}
		if (StringUtils.isBlank(modelName)) {
			modelName = StringUtils.EMPTY;
		}
		return new ModelAndView(viewName, modelName, responseData.getClass().cast(responseData));

	}
	
	/**
	 * 获取客户端ip
	 * 
	 * @param request
	 * @return
	 */
	public String getRemoteAddr(HttpServletRequest request) {
		String ip = request.getHeader(" x-forwarded-for ");
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getHeader(" Proxy-Client-IP ");
		}
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getHeader(" WL-Proxy-Client-IP ");
		}
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	
	/**
	 * 获取PageBean
	 * 
	 * @return
	 */
	public PageBean getPageBean(Integer paged) {
		PageBean pageBean = new PageBean();
		if (paged != null && paged > 0) {
			pageBean.setPaged(paged);
		}
		return pageBean;
	}
	
	public PageBean getPageBean(Integer paged,Integer pageSize) {
		PageBean pageBean = new PageBean();
		if (paged != null && paged > 0) {
			pageBean.setPaged(paged);
		}
		if(pageSize != null && pageSize>0){
			pageBean.setPageSize(pageSize);
		}
		return pageBean;
	}

	/**
	 * 获取PageBean
	 * 
	 * @return
	 */
	public PageBean getPageBean() {
		return new PageBean();
	}
	
	/**
	 * 
	 * 异常处理
	 * 
	 * @param e
	 * @param resultMap
	 */
	protected void exception(Exception e, Object... param) {
		if (param != null && param.length > 0) {
			LogUtil.errorLog(new Object[] { param.getClass().getSimpleName() }, new Object[] { param }, e.getMessage());
		} 
		LogUtil.errorLog(e.getMessage());
	}
	
	/**
	 * 
	 * 异常处理
	 * 
	 * @param e
	 * @param resultMap
	 */
	protected void exception(Exception e, Map<String, Object> resultMap, Object... param) {
		putStatusCode(resultMap, Code.ResponseCode.SystemCode.ERROR);
		if (param != null && param.length > 0) {
			LogUtil.errorLog(new Object[] { param.getClass().getSimpleName() }, new Object[] { param }, e.getMessage());
		} 
		LogUtil.errorLog(e.getMessage());
	}
	
}
