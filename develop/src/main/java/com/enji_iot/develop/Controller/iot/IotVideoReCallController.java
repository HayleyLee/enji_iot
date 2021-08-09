package com.enji_iot.develop.Controller.iot;

import com.enji_iot.develop.Service.IotVideoInfoService;
import com.enji_iot.develop.Service.IotVideoRecordService;
import com.enji_iot.util.Entity.bean.VideoServerReCall;
import com.enji_iot.util.Entity.bo.IotVideoInfoBO;
import com.enji_iot.util.Entity.bo.IotVideoRecordBO;
import com.enji_iot.cache.Cache.CacheName;
import com.enji_iot.cache.Cache.ProCacheUtil;
import com.enji_iot.util.Common.CodeIot;
import com.enji_iot.util.Common.RequestURLIOT;
import com.enji_iot.util.Util.ObjectUtil;
import com.enji_iot.util.Util.ResultMapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class IotVideoReCallController {
	@Autowired
	@Qualifier(value = "IotVideoInfoService")
	private IotVideoInfoService iotVideoInfoService;
	@Autowired
	@Qualifier(value = "IotVideoRecordService")
	private IotVideoRecordService iotVideoRecordService;
	/**
	 * 
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.IotVideoCall.IOT_VIDEO_CLIENTS)
	public ModelAndView onclients(HttpServletResponse response,
                                  @RequestBody VideoServerReCall obj ) {
		Object reData =0 ;
		try {
			// 客户端发起连接，目前这个没有什么作用；
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(StringUtils.EMPTY, StringUtils.EMPTY, reData.getClass().cast(reData) );
	}
	
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.IotVideoCall.IOT_VIDEO_STREAMS)
	public ModelAndView onStreams(HttpServletResponse response,
                                  @RequestBody VideoServerReCall obj ) {
		Object reData =0 ;
		try {
			// 推流连接，和停止推流，这里可以判断是否允许推流来或者通过其他工具拉流来；
			IotVideoInfoBO videoInfo = ProCacheUtil.getCache(CacheName.VIDEO_INFO, obj.getStream());
			// 判断该stream是否是允许的
			if( ObjectUtil.isNotEmpty(videoInfo) ){
				if( "on_publish".equals(obj.getAction()) ){
					// 更新设备在线
					IotVideoInfoBO udpTmp = new IotVideoInfoBO();
					udpTmp.setId(videoInfo.getId());
					udpTmp.setStatus(CodeIot.DEVICE_STATUS.ONLINE);
					Map<String, Object> reMap = iotVideoInfoService.update(udpTmp);
					if(ResultMapUtils.isOk(reMap)){
						// 更新缓存信息
						videoInfo.setStatus(CodeIot.DEVICE_STATUS.ONLINE);
					}
				}else if("on_unpublish".equals(obj.getAction())){
					IotVideoInfoBO udpTmp = new IotVideoInfoBO();
					udpTmp.setId(videoInfo.getId());
					udpTmp.setStatus(CodeIot.DEVICE_STATUS.OFFLINE);
					Map<String, Object> reMap = iotVideoInfoService.update(udpTmp);
					if(ResultMapUtils.isOk(reMap)){
						// 更新缓存信息
						videoInfo.setStatus(CodeIot.DEVICE_STATUS.OFFLINE);
					}
				}else{
					reData = -1 ;
				}
			}else{
				reData = -1 ;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(StringUtils.EMPTY, StringUtils.EMPTY, reData.getClass().cast(reData) );
	}
	
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.IotVideoCall.IOT_VIDEO_SESSIONS)
	public ModelAndView onSessions(HttpServletResponse response,
                                   @RequestBody VideoServerReCall obj ) {
		Object reData =0 ;
		try {
			// 播放，和停止播放，可以统计人数或者启动或者停止拉流人数
			IotVideoInfoBO videoInfo = ProCacheUtil.getCache(CacheName.VIDEO_INFO, obj.getStream());
			if(ObjectUtil.isNotEmpty(videoInfo)){
				if( "on_play".equals(obj.getAction()) ){
					// 这边牵涉到观看即时拉流
					
				}else if("on_stop".equals(obj.getAction())){
					// 这边牵涉到观看什么时候停止拉流
				}else{
					reData = -1 ;
				}
			}else{
				reData = -1 ;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(StringUtils.EMPTY, StringUtils.EMPTY, reData.getClass().cast(reData) );
	}
	
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.IotVideoCall.IOT_VIDEO_DVRS)
	public ModelAndView onDvrs(HttpServletResponse response,
                               @RequestBody VideoServerReCall obj ) {
		Object reData =0 ;
		try {
			// 将录屏数据出入到表中，目前1min一条数据
			IotVideoInfoBO videoInfo = ProCacheUtil.getCache(CacheName.VIDEO_INFO, obj.getStream());
			if(ObjectUtil.isNotEmpty(videoInfo)){
				if( "on_dvr".equals(obj.getAction()) ){
					// 将文件储存起来
					IotVideoRecordBO iotVideoRecordBo = new IotVideoRecordBO();
					iotVideoRecordBo.setVideo_id(videoInfo.getId());
					String[] str = obj.getFile().split("/live/");
					if(str.length > 1){
						iotVideoRecordBo.setName(str[1]);
						iotVideoRecordService.insert(iotVideoRecordBo);
					}else{
						reData = -1 ;
					}
				}
			}else{
				reData = -1 ;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(StringUtils.EMPTY, StringUtils.EMPTY, reData.getClass().cast(reData) );
	}

}
