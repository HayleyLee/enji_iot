package com.enji_iot.develop.Service.base;

import java.util.Map;

/**
 * 
 * 短信服务接口
 * 
 * 
 */
public interface ShortMessageService {
	
	/**
	 * 发送模板短信
	 * 
	 * @param tpl_id
	 * @param tpl_value
	 * @param mobile
	 * @return
	 */
	public Map<String, Object> sendSms(String tpl_id, Map<String, Object> tpl_value, String mobile);

	/**
	 * 发送短信（智能匹配模板）
	 * 
	 * @param message
	 * @param mobile
	 * @return
	 */
	public Map<String, Object> sendSms(String message, String mobile);
	
	/**
	 * 发送验证码短信
	 * 
	 * @param mobile
	 *            手机号
	 * @param code
	 *            验证码
	 * @return
	 */
	public Map<String, Object> sendValidateCode(String mobile, String code);
}
