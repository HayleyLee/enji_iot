package com.enji_iot.util.Util;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.enji_iot.util.Entity.bo.AliyunParamBO;
import com.enji_iot.util.Config.ProConfig;
import com.enji_iot.util.Entity.dto.AliyunSmsDto;

public class AliyunSmsAndVoiceUtil {

	private static String accessKeyId = ProConfig.AliyunShortMessage.ACCESSKEY ;
	
	private static String accessKeySecret = ProConfig.AliyunShortMessage.ACCESSKEYSECRET ;
	
	private static DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", 
    		accessKeyId, accessKeySecret); ;
	
	private static IAcsClient client  = new DefaultAcsClient(profile); ;
	
	public AliyunSmsAndVoiceUtil(){
	
	}
	
	/**
	 * 发送阿里云短信
	 * @param aliyunParamBo
	 * @return
	 */
	public static AliyunSmsDto sendSms(AliyunParamBO aliyunParamBo){
		CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        
        request.putQueryParameter("PhoneNumbers", aliyunParamBo.getPhonenumber());
        request.putQueryParameter("SignName", aliyunParamBo.getSignaName());
        request.putQueryParameter("TemplateCode", aliyunParamBo.getTemplateCode());
        request.putQueryParameter("TemplateParam", aliyunParamBo.getTemplateParam());
        try {
            CommonResponse response = client.getCommonResponse(request);
            
            AliyunSmsDto aliyunSms =JSON.parseObject(response.getData(), AliyunSmsDto.class) ;
            
            return aliyunSms ;
            
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return null ;
	}
	
	
	public static AliyunSmsDto sendSmsVoice(AliyunParamBO aliyunParamBo){
		CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dyvmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SingleCallByTts");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("CalledShowNumber", aliyunParamBo.getCalledShowNumber());
        request.putQueryParameter("CalledNumber",aliyunParamBo.getPhonenumber());
        request.putQueryParameter("TtsCode", aliyunParamBo.getTemplateCode() );
        request.putQueryParameter("TtsParam",aliyunParamBo.getTemplateParam() );
        // 重播几次
//        request.putQueryParameter("PlayTimes", "2");
        try {
            CommonResponse response = client.getCommonResponse(request);
            return JSON.parseObject(response.getData(), AliyunSmsDto.class) ;
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return null ;
	}
	
	public static void main(String[] args) {

		sendSms(new AliyunParamBO());
		sendSmsVoice(new AliyunParamBO());
		
//			发送短信
//	        CommonRequest request = new CommonRequest();
//	        request.setMethod(MethodType.POST);
//	        request.setDomain("dysmsapi.aliyuncs.com");
//	        request.setVersion("2017-05-25");
//	        request.setAction("SendSms");
//	        request.putQueryParameter("RegionId", "cn-hangzhou");
//	        
//	        request.putQueryParameter("PhoneNumbers", "15850771966");
//	        
//	        request.putQueryParameter("SignName", "南京爱贝生物科技有限公司");
//	        
//	        request.putQueryParameter("TemplateCode", "SMS_169665457");
//	        
//	        request.putQueryParameter("TemplateParam", "{\"code\":\"125645\"}");
//	        
//	        
//	        try {
//	            CommonResponse response = client.getCommonResponse(request);
//	            System.out.println(response.getData());
//	            
//	            AliyunSmsDto aliyunSms =JSON.parseObject(response.getData(), AliyunSmsDto.class) ;
//	            
//	            // {"Message":"OK","RequestId":"D070E477-FF9E-4AEB-BF94-6FE1FE111C16","BizId":"468523081428635478^0","Code":"OK"}
//	            
//	        } catch (ServerException e) {
//	            e.printStackTrace();
//	        } catch (ClientException e) {
//	            e.printStackTrace();
//	        }
		
		// 语音
//		CommonRequest request = new CommonRequest();
//        request.setMethod(MethodType.POST);
//        request.setDomain("dyvmsapi.aliyuncs.com");
//        request.setVersion("2017-05-25");
//        request.setAction("SingleCallByTts");
//        request.putQueryParameter("RegionId", "cn-hangzhou");
//        request.putQueryParameter("CalledShowNumber", "051068644496");
//        request.putQueryParameter("CalledNumber", "15850771966");
//        request.putQueryParameter("TtsCode", "TTS_183195429");
////        request.putQueryParameter("TtsParam", "{ \"code\":123 }");
////        request.putQueryParameter("PlayTimes", "2");
//        try {
//            CommonResponse response = client.getCommonResponse(request);
//            System.out.println(response.getData());
//        } catch (ServerException e) {
//            e.printStackTrace();
//        } catch (ClientException e) {
//            e.printStackTrace();
//        }
	    
    }
	
}
