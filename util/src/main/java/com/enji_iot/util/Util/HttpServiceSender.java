package com.enji_iot.util.Util;


import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpServiceSender {
	protected static final Logger LOGGER = LoggerFactory.getLogger(HttpServiceSender.class);
	protected static final int SC_OK=200;// 请求成功
	protected static final int SC_REDIRECT=403;// 请求重定向
	protected static final int SC_UNRESOURCE=404;//请求失败（找不到对应资源）
	protected static final int SC_SERVER_ERROE=500;//请求失败（服务器内部错误）
	protected static final int SC_ERROR_REQUEST=503;// 请求失败（服务器非请求类型）
	
	/**
	 * 普通get请求
	 * 
	 * @param url
	 *            请求url
	 * @param bodyString
	 *            提交参数
	 * @return [响应状态头][响应正文]
	 */
	public static String[] doGet(String url){
		String[] response = new String[2];
		response[0] = "-1";
		HttpClient client=new HttpClient();
		GetMethod getMethod=new GetMethod(url);
		getMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		getMethod.setRequestHeader("Content-Type", "application/json");
		try{ 
			client.executeMethod(getMethod);
			response[0]=String.valueOf(getMethod.getStatusCode());
			if(getMethod.getStatusCode()==SC_OK){	
				
				response[1] = getMethod.getResponseBodyAsString();
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			getMethod.releaseConnection();
		}
		return response;
	}
	
	public static String[] doGet(String url,String apiKey){
		String[] response = new String[2];
		response[0] = "-1";
		HttpClient client=new HttpClient();
		GetMethod getMethod=new GetMethod(url);
		getMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		getMethod.setRequestHeader("Content-Type", "application/json");
		getMethod.setRequestHeader("USER-KEY", apiKey);
		try{ 
			client.executeMethod(getMethod);
			response[0]=String.valueOf(getMethod.getStatusCode());
			if(getMethod.getStatusCode()==SC_OK){	
				
				response[1] = getMethod.getResponseBodyAsString();
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			getMethod.releaseConnection();
		}
		return response;
	}
	
	/**
	 * 普通POST请求
	 * 
	 * @param url
	 *            请求url
	 * @param bodyString
	 *            提交参数
	 * @return [响应状态头][响应正文]
	 */
	@SuppressWarnings("deprecation")
	public static String[] doPost(String url,String lproKey, String bodyString) {
		String[] response = new String[2];
		response[0] = "-1";
		HttpClient client = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		postMethod.setRequestHeader("Content-Type", "application/json");
		postMethod.setRequestHeader("apiKey", lproKey);
		postMethod.setRequestBody(bodyString);
		try {
			client.executeMethod(postMethod);
			response[0] = postMethod.getStatusCode()+"";
			if (postMethod.getStatusCode() == SC_OK) {
				response[1] = postMethod.getResponseBodyAsString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();
		}
		return response;
	}
	
	/**
	 * 普通PUT请求
	 * 
	 * @param url
	 * @return [响应状态头][响应正文]
	 */
	@SuppressWarnings("deprecation")
	public static String[] doPUT(String url,String lproKey, String bodyString) {
		String[] response = new String[2];
		response[0] = "-1";
		HttpClient client = new HttpClient();
		PutMethod putMethod = new PutMethod(url);
		putMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		putMethod.setRequestHeader("Content-Type", "application/json");
		putMethod.setRequestHeader("apiKey", lproKey);
		putMethod.setRequestBody(bodyString);
		try {
			client.executeMethod(putMethod);
			if (putMethod.getStatusCode() == SC_OK) {
				response[1] = putMethod.getResponseBodyAsString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			putMethod.releaseConnection();
		}
		return response;
	}
	
	/**
	 * 带认证的DELETE请求
	 * 
	 * @param url
	 *            请求地址
	 * @param iblueyeKey
	 *            密钥
	 * @return [响应状态头][响应正文]
	 */
	public static String[] doDelete(String url) {
		String[] response = new String[2];
		response[0] = "-1";
		HttpClient client = new HttpClient();
		DeleteMethod deleteMethod = new DeleteMethod(url);		
		deleteMethod.setRequestHeader("Content-Type", "application/json");
		try {
			client.executeMethod(deleteMethod);
			response[0]=String.valueOf(deleteMethod.getStatusCode());
			if (deleteMethod.getStatusCode() == SC_OK) {				
				response[1] = deleteMethod.getResponseBodyAsString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			deleteMethod.releaseConnection();
		}
		return response;
	}

	public static void main(String[]args){
	
	}
}
