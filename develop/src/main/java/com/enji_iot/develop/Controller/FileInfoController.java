package com.enji_iot.develop.Controller;

import com.enji_iot.develop.Service.FileInfoService;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Common.Constants;
import com.enji_iot.util.Common.RequestURL;
import com.enji_iot.util.Entity.bo.FileInfoBO;
import com.enji_iot.util.Config.ProConfig;
import com.enji_iot.util.Util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;

@Controller

public class FileInfoController {
	@Autowired
	@Qualifier(value = "FileInfoService")
	private FileInfoService fileInfoService;
	/**
	 * 检索
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURL.FileInfo.FILE_INFO_PAGE)
	public ModelAndView selectPage(HttpServletResponse response,
                                   @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey,
                                   @RequestBody FileInfoBO obj, @RequestParam(required = false) Integer pageSize,
                                   @RequestParam Integer paged) {
		return new ResultMapUtils().getModelAndView(response, fileInfoService.selectPageList(new ResultMapUtils().getPageBean(paged, pageSize), obj));
	}

	/**
	 * 查询单个
	 */
	@RequestMapping(method = RequestMethod.GET, value = RequestURL.FileInfo.FILE_INFO)
	public ModelAndView selectOne(HttpServletResponse response, @RequestParam Integer id) {
		return new ResultMapUtils().getModelAndView(response, fileInfoService.selectOne(new FileInfoBO(id)));
	}

	/**
	 * 更新
	 */
	@RequestMapping(method = RequestMethod.PUT, value = RequestURL.FileInfo.FILE_INFO)
	public ModelAndView update(HttpServletResponse response, @RequestBody FileInfoBO obj) {
		return new ResultMapUtils().getModelAndView(response, fileInfoService.update(obj));
	}

	/**
	 * 删除
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = RequestURL.FileInfo.FILE_INFO)
	public ModelAndView delete(HttpServletResponse response, @RequestParam Integer id) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			FileInfoBO obj = new FileInfoBO();
			if (ObjectUtil.isEmpty(id)) {
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.PARAM_ERROR);
			} else {
				obj.setId(id);
				obj.setDelete_flag(Constants.DELETE.YES);
				resultMap = fileInfoService.update(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = RequestURL.FileInfo.FILE_SENSOR_ICO )
	public ModelAndView upload(HttpServletResponse response, HttpServletRequest  request,
                               @RequestParam(required = false,value="code") String code,
                               @RequestParam(required = false, value = "file") MultipartFile file) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			// 将文件保存起来
			if( ObjectUtil.isNotEmpty(file) && !file.isEmpty() ){
				try {  
	                // 文件保存路径  
	                String path = this.getClass().getClassLoader().getResource("").getPath() ;
	                
	                String rootPath = "" ;
	                
	                String os = System.getProperty("os.name");  
	                if(os.toLowerCase().startsWith("win")){  
	                	rootPath = path.substring(1, path.indexOf("/WEB-INF/"));
	                }else{
	                	rootPath = path.substring(0, path.indexOf("/WEB-INF/"));
	                }
	                // 转存文件  
	                file.transferTo(new File(rootPath+"/image/oss/iot/"+ code+".png" ));  
	            } catch (Exception e) {  
	            	 LogUtil.errorLog(e); 
	            }  
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	/**
	 * 上传文件数据
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURL.FileInfo.UPLOAD)
	public ModelAndView uploadCommonFile(HttpServletResponse response, HttpSession session,
                                         @RequestParam(required = false,value="category") String category,
                                         @RequestParam(required = false, value = "file") MultipartFile file) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try{
			// 将文件保存起来
			if( ObjectUtil.isNotEmpty(file) && !file.isEmpty() ){
				try {  
					 String fix = file.getOriginalFilename();
					 if(fix.contains(".")){
						 fix = fix.split("\\.")[1];
					 }
					 String relativeFilePath = "/" + category  +"/"
							 	+ DateUtils.format(DateUtils.dtShort , new Date()) ;
					 String fileRealPath = ProConfig.LOCAL_FILE_PATH + relativeFilePath + "/" ;
	                
					 if(! new File(fileRealPath).exists()){
						 new File(fileRealPath).mkdirs();
					 }
					 String newFileName = System.currentTimeMillis() + "." + fix;
					 String filePath = fileRealPath + newFileName ;
	                // 转存文件  
	                file.transferTo(new File(filePath));  
	                // 返回所有值
	                ResultMapUtils.putData(resultMap, relativeFilePath+newFileName) ;
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            }  
			}
		}catch(Exception e){
			LogUtil.errorLog(e);
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	/**
	 * 插入base64数据
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURL.FileInfo.BASE64_FILE_INFO)
	public ModelAndView uploadPortait(HttpServletResponse response, HttpSession session,
                                      @RequestBody FileInfoBO param) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		if (param.getBase64File() != null) {
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] b;
			try {
				if (param.getBase64File().indexOf(',') > 0) {
					b = decoder.decodeBuffer(param.getBase64File().split(",")[1]);
					for (int i = 0; i < b.length; ++i) {
						if (b[i] < 0) {
							b[i] += 256;
						}
					}
					 String fix = ArithHelper.getTypeByStream(b);
					 String relativeFilePath = Constants.FileRealPath.NORMAL +"/"
							 	+ DateUtils.format(DateUtils.dtShort , new Date()) ;
					 String fileRealPath = ProConfig.LOCAL_FILE_PATH + relativeFilePath + "/" ;
					 
					 FileInfoBO fileInfo = new FileInfoBO();
					 fileInfo.setName("用户头像");
					 fileInfo.setFile_path(relativeFilePath);
					 fileInfo.setFix(fix.trim().toLowerCase());
					 fileInfo.setSize( b.length);
					 fileInfo.setAdd_time(new Date());
					 fileInfoService.insert(fileInfo);
					//
					 if (fileInfo.getId() != null) {
						 if(! new File(fileRealPath).exists()){
							 new File(fileRealPath).mkdirs();
						 }
						 String filePath = fileRealPath + fileInfo.getId() + "." + fix;
						 // 2.图片存储到文件系统
						 File f = new File(filePath);
						 OutputStream out = new FileOutputStream(f);
						 out.write(b);
						 out.flush();
						 out.close();
						 
						 // 保存成功，返回图片信息
						 ResultMapUtils.putData(resultMap, fileInfo);
					 } else {
						 ResultMapUtils.putStatusCode(resultMap,
						 Code.ResponseCode.SystemCode.NO_DATA);
					 }
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	
}
