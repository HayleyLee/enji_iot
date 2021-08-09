package com.enji_iot.develop.Controller.iot;

import com.enji_iot.develop.Service.IotHistoryNodeInfoService;
import com.enji_iot.develop.Util.UserUtil;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Common.Constants;
import com.enji_iot.util.Common.RequestURLIOT;
import com.enji_iot.util.Entity.bo.IotHistoryNodeDataBO;
import com.enji_iot.util.Entity.bo.IotHistorySensorDataBO;
import com.enji_iot.util.Config.ProConfig;
import com.enji_iot.util.Entity.bo.IotSceneInfoBO;
import com.enji_iot.util.Entity.bo.UserInfoBO;
import com.enji_iot.util.Util.DateUtils;
import com.enji_iot.util.Util.ObjectUtil;
import com.enji_iot.util.Util.ResultMapUtils;
import net.sf.json.JSONArray;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller
public class IotHistoryNodeInfoController {

	@Autowired
	@Qualifier(value = "IotHistoryNodeInfoService")
	private IotHistoryNodeInfoService iotHistoryNodeInfoService;

	/**
	 * 统计
	 */
	@RequestMapping(method = RequestMethod.POST,value = RequestURLIOT.SensorHistoryInfo.HISTORY_COUNT)
	public @ResponseBody JSONArray selectCount(String user_id){
		IotSceneInfoBO scene = new IotSceneInfoBO();
		scene.setUser_id(Integer.parseInt(user_id));
		return iotHistoryNodeInfoService.selectCount(scene);
	}

	/**
	 * 检索
	 * @param response
	 * @param 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.SensorHistoryInfo.SENSOR_HISTORY_INFO_PAGE)
	public ModelAndView selectPage(HttpServletResponse response,
                                   @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                   @RequestBody IotHistorySensorDataBO obj,
                                   @RequestParam(required=false) Integer pageSize ,
                                   @RequestParam Integer paged ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			// 这边暂时不管，当前场景是否是该有用户的数据
			if( ObjectUtil.isNotEmpty(obj.getSensor_id()) || ObjectUtil.isNotEmpty(obj.getScene_id()) ){
				if(  ObjectUtil.isNotEmpty( obj.getQuery_interval_type())){
					if(obj.getQuery_interval_type() == 1){
						obj.setInterval_p1("%Y%d%m%k%i");
						obj.setInterval_p2("%s");
						obj.setInterval_p3(30);
					}else if(obj.getQuery_interval_type() == 2){
						obj.setInterval_p1("%Y%d%m%k");
						obj.setInterval_p2("%i");
						obj.setInterval_p3(1);
					}else if(obj.getQuery_interval_type() == 3){
						obj.setInterval_p1("%Y%d%m%k");
						obj.setInterval_p2("%i");
						obj.setInterval_p3(30);
					}else if(obj.getQuery_interval_type() == 4){
						obj.setInterval_p1("%Y%d%m");
						obj.setInterval_p2("%k");
						obj.setInterval_p3(1);
					}
					resultMap = iotHistoryNodeInfoService.selectGroupByPage(obj,new ResultMapUtils().getPageBean(paged,pageSize));
				}else{
					resultMap = iotHistoryNodeInfoService.selectPageList(obj,new ResultMapUtils().getPageBean(paged,pageSize));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	/**
	 * 检索列表
	 * @param response
	 * @param 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.SensorHistoryInfo.SENSOR_HISTORY_INFO_LIST)
	public ModelAndView selectList(HttpServletResponse response,
                                   @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                   @RequestBody IotHistorySensorDataBO obj) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			if(ObjectUtil.isNotEmpty(obj.getStart_time()) && ObjectUtil.isNotEmpty(obj.getEnd_time()) ){
				// 这边判断时间不允许超过一个月，否则强行设置一个月；
				Date start_time = DateUtils.parse(DateUtils.simpleALL, obj.getStart_time());
				Date end_time = DateUtils.parse(DateUtils.simpleALL,obj.getEnd_time());
				if( start_time.getTime() + (long)32*24*3600*1000 < end_time.getTime()){
					// 时间出错
					obj.setStart_time( DateUtils.format(DateUtils.simpleALL, DateUtils.getBeforeOneDateTime(end_time))  );
				}
				// 这边暂时不管，当前场景是否是该有用户的数据
				if( ObjectUtil.isNotEmpty(obj.getSensor_id()) || ObjectUtil.isNotEmpty(obj.getScene_id()) ){
					if(  ObjectUtil.isNotEmpty( obj.getQuery_interval_type())){
						if(obj.getQuery_interval_type() == 1){
							obj.setInterval_p1("%Y%d%m%k%i");
							obj.setInterval_p2("%s");
							obj.setInterval_p3(30);
						}else if(obj.getQuery_interval_type() == 2){
							obj.setInterval_p1("%Y%d%m%k");
							obj.setInterval_p2("%i");
							obj.setInterval_p3(1);
						}else if(obj.getQuery_interval_type() == 3){
							obj.setInterval_p1("%Y%d%m%k");
							obj.setInterval_p2("%i");
							obj.setInterval_p3(30);
						}else if(obj.getQuery_interval_type() == 4){
							obj.setInterval_p1("%Y%d%m");
							obj.setInterval_p2("%k");
							obj.setInterval_p3(1);
						}
						resultMap = iotHistoryNodeInfoService.selectGroupByPage(obj);
					}else{
						resultMap = iotHistoryNodeInfoService.selectPageList(obj);
					}
				}
			}else{
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.PARAM_ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	/**
	 * Excel 下载
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = RequestURLIOT.SensorHistoryInfo.SENSOR_HISTORY_INFO_INFO )
	public void excel(HttpServletResponse response, IotHistoryNodeDataBO obj ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			// 这边需要对查询的数据进行判断是否数据量过大，如果大于5000 条，则通过CSV下载，并且CSV通过分段下载
			int allCount = iotHistoryNodeInfoService.selectHistoryNodeCount(obj);
			if(allCount < 5000){
				List<?> data = ResultMapUtils.getData(iotHistoryNodeInfoService.selectHistoryNodeData(obj));
				UserUtil.downExcel("历史数据下载","tpl/xls/history_sensor_data_template",data, response);
			}
			else{
				// 这边就需要分段下载
				int num = allCount / 5000 +1 ;
				
				 String fileRealPath = ProConfig.LOCAL_FILE_PATH + Constants.FileRealPath.NORMAL +"/"+ DateUtils.format(DateUtils.dtShort , new Date()) + "/";
				 if(! new File(fileRealPath).exists()){
					 new File(fileRealPath).mkdirs();
				 }
				 File f = new File(fileRealPath 
							+ "historySensorData" + DateUtils.format(DateUtils.dtShort , new Date())+".csv");
				FileOutputStream fos = new FileOutputStream(f );
				OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
				// csv 格式
				CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader("序号", "设备SN号", "传感点名称", "数值", "时间");
				CSVPrinter csvPrinter = new CSVPrinter(osw, csvFormat);
				for (int i = 0; i < num; i++) {
					obj.setLimit(5000);
					obj.setOffset(i);
					List<IotHistoryNodeDataBO> list = ResultMapUtils.getData(iotHistoryNodeInfoService.selectHistoryNodeData(obj));
					for(int j=0;j<list.size();j++){
						csvPrinter.printRecord(i*5000 + (j+1) ,list.get(j).getDevice_code(), list.get(j).getName(),
								list.get(j).getName()+list.get(j).getUnit(),list.get(j).getTime() );
					}
					// 这边需要对list 清空缓存
					list = null ;
					System.gc();
				}
				csvPrinter.flush();
				csvPrinter.close();
				
				// 返回文件流
				InputStream bis = new BufferedInputStream(new FileInputStream(f));
				byte[] buffer = new byte[bis.available()];
				bis.read(buffer);
				bis.close();
				// 清空response
				response.reset();
				// 设置response的Header
				response.addHeader("Content-Disposition",
						"attachment;filename=" + new String(f.getName().getBytes("gb2312"), "ISO8859-1"));
				response.addHeader("Content-Length", "" + f.length());
				OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
				response.setContentType("application/vnd.ms-excel;charset=gb2312");
				// excel兼容
				osw.write(new String(new byte[] { (byte) 0xEF, (byte) 0xBB,(byte) 0xBF }));    
				toClient.write(buffer);
				toClient.flush();
				toClient.close();
				// 删除生成的临时文件
				if (f.exists()) {
					f.delete();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 插入
	 * @param response
	 * @param 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.SensorHistoryInfo.SENSOR_HISTORY_INFO )
	public ModelAndView save(HttpServletResponse response,
                             @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                             @RequestBody IotHistoryNodeDataBO obj ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = iotHistoryNodeInfoService.insert(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}

	/**
	 * 查询单个
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = RequestURLIOT.SensorHistoryInfo.SENSOR_HISTORY_INFO)
	public ModelAndView selectOne(HttpServletResponse response,
                                  @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                  @RequestParam Integer id ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = iotHistoryNodeInfoService.selectOne(new IotHistorySensorDataBO(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	/**
	 * 更新
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value = RequestURLIOT.SensorHistoryInfo.SENSOR_HISTORY_INFO)
	public ModelAndView update(HttpServletResponse response,
                               @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                               @RequestBody IotHistorySensorDataBO obj ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = iotHistoryNodeInfoService.update(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	/**
	 * 删除
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = RequestURLIOT.SensorHistoryInfo.SENSOR_HISTORY_INFO)
	public ModelAndView delete(HttpServletResponse response,
                               @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                               @RequestParam Integer id ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			IotHistorySensorDataBO obj = new IotHistorySensorDataBO();
			if(ObjectUtil.isEmpty(id)){
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.PARAM_ERROR);
			}else{
				obj.setId(id);
				obj.setDelete_flag(Constants.DELETE.YES);
				resultMap = iotHistoryNodeInfoService.update(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}

	/**
	 * 查找设备历史数据
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.NodeInfo.NODE_HISTORY_INFO_NODE)
	public ModelAndView selectHistoryNode(HttpServletResponse response,
                                          @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                          @RequestBody IotHistoryNodeDataBO obj ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = iotHistoryNodeInfoService.selectHistoryNodeData(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	/**
	 * 设备传感点历史数据分析
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.NodeInfo.NODE_HISTORY_DATA)
	public ModelAndView historyNodeData(HttpServletResponse response,
                                        @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                        @RequestBody IotHistoryNodeDataBO obj ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			List<Object> list = new ArrayList<>();
			if(ObjectUtil.isNotEmpty(obj.getName())){
				String[] names = obj.getName().split(",");
				for(String n : names ){
					obj.setName(n);
					list.add(iotHistoryNodeInfoService.singleSelectHistoryNodeData(obj));
				}
				ResultMapUtils.putData(resultMap, list) ;
			}else{
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.PARAM_ERROR) ;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}


	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.SensorHistoryInfo.SENSOR_HISTORY_INFO_NODE_COUNT)
	public ModelAndView selectHistoryNodeCount(HttpServletResponse response,
                                               @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                               @RequestBody IotHistoryNodeDataBO obj ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = iotHistoryNodeInfoService.selectList(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
}
