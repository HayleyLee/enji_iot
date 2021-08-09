package com.enji_iot.develop.Service.base;

import java.util.Map;

public interface WeChatFileInfoService {
 
	/**
	 * 微信下载图片
	 * @return
	 */
	Map<String,Object> downFileImgFromWxServer(String access_token, String mediaId, Integer user_id);
 
	
}
 
 
