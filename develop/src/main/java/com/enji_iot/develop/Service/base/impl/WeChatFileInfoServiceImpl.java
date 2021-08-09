package com.enji_iot.develop.Service.base.impl;


import com.enji_iot.develop.DAO.FileInfoMapper;
import com.enji_iot.util.Entity.bo.FileInfoBO;
import com.enji_iot.util.Config.ProConfig;
import com.enji_iot.develop.Service.base.WeChatFileInfoService;
import com.enji_iot.util.Util.ResultMapUtils;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Map;

@Service(value = "WeChatFileInfoService")
public class WeChatFileInfoServiceImpl implements WeChatFileInfoService {

	@Resource
	private FileInfoMapper fileInfoMapper;
	
	@Override
	public Map<String, Object> downFileImgFromWxServer(String access_token,String mediaId, Integer user_id) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		// 获取图片
		String[] response = new String[2];
		response[0] = "-1";
		HttpClient client = new HttpClient();
		GetMethod getMethod = new GetMethod("http://file.api.weixin.qq.com/cgi-bin/media/get?access_token="
				+ access_token + "&media_id=" + mediaId);
		getMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		InputStream inputStream = null;
		try {
			client.executeMethod(getMethod);
			response[0] = String.valueOf(getMethod.getStatusCode());
			if (getMethod.getStatusCode() == 200) {
				// 文件后缀
				String fileFix = ".jpg" ;
				inputStream = getMethod.getResponseBodyAsStream();
				
				// 将文件信息存储到数据库
				FileInfoBO fileInfo = new FileInfoBO();
				fileInfo.setName("IMG_WX_media_id " + mediaId);
				fileInfo.setFix(fileFix);
//				dao.insert("FileInfo.insert", fileInfo) ;
				fileInfoMapper.insert(fileInfo);
				// 保存文件
				int len = 0;
				byte[] data = new byte[1024];
				 FileOutputStream fileOutputStream = fileOutputStream = new FileOutputStream(
						ProConfig.LOCAL_FILE_PATH + "/" + fileInfo.getId() +fileFix);
				while ((len = inputStream.read(data)) != -1) {
					fileOutputStream.write(data, 0, len);
				}
				fileOutputStream.close();
				
				// 文件信息返回
				ResultMapUtils.putData(resultMap, fileInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();
		}
		return resultMap;
	} 
	
	
	/**
	 * 根据内容类型判断文件扩展名
	 *
	 * @param contentType 内容类型
	 * @return
	 */
	private static String getFileexpandedName(String contentType) {
		String fileEndWitsh = "";
		if ("image/jpeg".equals(contentType))
			fileEndWitsh = ".jpg";
		else if ("audio/mpeg".equals(contentType))
			fileEndWitsh = ".mp3";
		else if ("audio/amr".equals(contentType))
			fileEndWitsh = ".amr";
		else if ("video/mp4".equals(contentType))
			fileEndWitsh = ".mp4";
		else if ("video/mpeg4".equals(contentType))
			fileEndWitsh = ".mp4";
		return fileEndWitsh;
	}
 
} 
 
 
