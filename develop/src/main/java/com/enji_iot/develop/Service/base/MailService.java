package com.enji_iot.develop.Service.base;

import java.util.Map;

/**
 * 邮件服务
 *
 */
public interface MailService {
	/**
	 * 
	 * 把模板换算成字符串
	 * 
	 * @return
	 */
	public String mergeTemplateIntoString(String tpl, Map<String, Object> param);
	
	/**
	 * 
	 * 发送邮件
	 * 
	 * @param mailTo
	 * @param mailSubject
	 * @param templateLocation
	 * @param param
	 */
	public void send(String mailTo, String mailSubject, String templateLocation, Map<String, Object> param);
}
