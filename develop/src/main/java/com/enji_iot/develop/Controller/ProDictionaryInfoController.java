package com.enji_iot.develop.Controller;

import com.enji_iot.develop.Service.ProDictionaryInfoService;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Common.RequestURL;
import com.enji_iot.util.Entity.bean.ProDictionaryInfo;
import com.enji_iot.util.Entity.bo.ProDictionaryInfoBO;
import com.enji_iot.cache.Cache.CacheName;
import com.enji_iot.cache.Cache.ProCacheUtil;
import com.enji_iot.cache.Service.ProCache;
import com.enji_iot.cache.Service.EhcacheUtil;
import com.enji_iot.util.Util.MysqlDbGenerateBean;
import com.enji_iot.util.Util.ObjectUtil;
import com.enji_iot.util.Util.ResultMapUtils;
import com.enji_iot.develop.Util.UserUtil;
import net.sf.ehcache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
public class ProDictionaryInfoController {


	@Autowired
	@Qualifier(value = "ProCache")
	private ProCache procahce;

	@Autowired
	@Qualifier(value = "ProDictionaryInfoService")
	private ProDictionaryInfoService proDictionaryInfoService;
	
	/**
	 * 
	 * @param response
	 * @param proDictionaryInfo
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURL.ProDictionaryInfo.PRO_DICTIONARY_INFO_PAGE)
	public ModelAndView selectDictionArys(HttpServletResponse response,
                                          @RequestHeader(value = ResultMapUtils.USER_KEY, required = false) String userKey ,
                                          @RequestBody ProDictionaryInfoBO proDictionaryInfo,
                                          @RequestParam(required=false) Integer pageSize ,
                                          @RequestParam Integer paged ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			if(UserUtil.verifyUserRole(userKey, Code.UserType.SUPER) ){
				resultMap = proDictionaryInfoService.selectPageList(new ResultMapUtils().getPageBean(paged,pageSize), proDictionaryInfo);
			}else{
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.NO_AUTHORIZATION);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = RequestURL.ProDictionaryInfo.PRO_DICTIONARY_INFO)
	public ModelAndView saveDictionAry(HttpServletResponse response,
                                       @RequestHeader(value = ResultMapUtils.USER_KEY, required = false) String userKey ,
                                       @RequestBody ProDictionaryInfoBO proDictionaryInfo ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			if(UserUtil.verifyUserRole(userKey, Code.UserType.SUPER) ){
				Integer code = (Integer) ResultMapUtils.getData(proDictionaryInfoService.generateCode(proDictionaryInfo));
				if(ObjectUtil.isNotEmpty(code)){
					proDictionaryInfo.setCode(code);
				}else{
					proDictionaryInfo.setCode(1);
				}
				resultMap = proDictionaryInfoService.insert(proDictionaryInfo);
				procahce.refleshCache(CacheName.DICTIONARY);
			}else{
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.NO_AUTHORIZATION);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = RequestURL.ProDictionaryInfo.PRO_DICTIONARY_INFO)
	public ModelAndView updateDictionAry(HttpServletResponse response,
                                         @RequestHeader(value = ResultMapUtils.USER_KEY, required = false) String userKey ,
                                         @RequestParam(required=false) Integer id , @RequestParam(required=false) String ids ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			if(ObjectUtil.isNotEmpty(id)){
				ResultMapUtils.putData(resultMap,ProCacheUtil.getCache(CacheName.DICTIONARY, id.toString(), new ProDictionaryInfo()) );
			}else if(ObjectUtil.isNotEmpty(ids)){
				String[] idArray = ids.split(",");
				List<ProDictionaryInfo> list = new ArrayList<>();
				for(int i=0;i<idArray.length;i++){
					list.add(ProCacheUtil.getCache(CacheName.DICTIONARY, idArray[i], new ProDictionaryInfo()) );
				}
				ResultMapUtils.putData(resultMap, list);
			}				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = RequestURL.ProDictionaryInfo.PRO_DICTIONARY_INFO)
	public ModelAndView updateDictionAry(HttpServletResponse response,
                                         @RequestHeader(value = ResultMapUtils.USER_KEY, required = false) String userKey ,
                                         @RequestBody ProDictionaryInfoBO proDictionaryInfo ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			if(UserUtil.verifyUserRole(userKey, Code.UserType.SUPER) ){
				resultMap = proDictionaryInfoService.update(proDictionaryInfo) ;
				if(ResultMapUtils.isOk(resultMap)){
					resultMap = proDictionaryInfoService.selectOne(proDictionaryInfo) ;
					if(ResultMapUtils.isOk(resultMap)){
						ProDictionaryInfoBO tmp =  (ProDictionaryInfoBO)ResultMapUtils.getData(resultMap) ;
						if(ObjectUtil.isNotEmpty(tmp)){
							//更新子字典中，父字典的类型名称
							proDictionaryInfo.setP_code(tmp.getCode());
							proDictionaryInfo.setP_dictionary_name(tmp.getDictionary_name());
							resultMap = proDictionaryInfoService.updateByCondition(proDictionaryInfo);
							procahce.refleshCache(CacheName.DICTIONARY);
						}
					}				
				}			
			}else{
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.NO_AUTHORIZATION);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = RequestURL.ProDictionaryInfo.PRO_DICTIONARY_INFO)
	public ModelAndView deleteDictionAry(HttpServletResponse response,
                                         @RequestHeader(value = ResultMapUtils.USER_KEY, required = false) String userKey ,
                                         @RequestParam Integer id ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			if(UserUtil.verifyUserRole(userKey, Code.UserType.SUPER) ){
				ProDictionaryInfoBO proDictionaryInfo = new ProDictionaryInfoBO();
				if(ObjectUtil.isNotEmpty(id)){
					proDictionaryInfo.setId(id);
				}
				resultMap = proDictionaryInfoService.selectOne(proDictionaryInfo);
				if(ResultMapUtils.isOk(resultMap)){
					ProDictionaryInfoBO tmp =  (ProDictionaryInfoBO)ResultMapUtils.getData(resultMap) ;
					if(ObjectUtil.isNotEmpty(tmp)){
						// 删除父字典下的子字典值
						proDictionaryInfo.setP_code(tmp.getCode());
						resultMap = proDictionaryInfoService.deleteByPcode(proDictionaryInfo);
					}
				}
				resultMap = proDictionaryInfoService.delete(proDictionaryInfo);

				procahce.refleshCache(CacheName.DICTIONARY);
			}else{
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.NO_AUTHORIZATION);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = RequestURL.ProDictionaryInfo.PRO_DICTIONARY_INFO_SEL)
	public ModelAndView getDictionarySub(HttpServletResponse response,
                                         @PathVariable Integer p_code ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			ResultMapUtils.putData(resultMap, ProCacheUtil.getCache(CacheName.DICTIONARY_RELATION, p_code.toString(), new ProDictionaryInfoBO()).getSub() );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = RequestURL.ProDictionaryInfo.GEN_DICTIONARY_INFO_SEL)
	public void genDictionaryFile(HttpServletResponse response ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			// string buffer信息
			StringBuffer stbuffer = new StringBuffer();
			Cache cache = EhcacheUtil.getCache(CacheName.DICTIONARY_RELATION) ;
			List<String> code =  cache.getKeys() ;
			for(int i=0;i<code.size();i++){
				// System.out.println(code.get(i)+","+ cache.get(code.get(i)).getObjectValue() );
				MysqlDbGenerateBean.generateDictionaryCode(stbuffer, (ProDictionaryInfoBO) cache.get(code.get(i)).getObjectValue());
			}
			stbuffer.append("}\n\n");
			
			
			// 取得文件名。
			String filename = "Codes.java";
			byte[] buffer = stbuffer.toString().getBytes();
		
			// 清空response
			response.reset();
			//
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			// 设置response的Header
			response.addHeader("Content-Disposition",
					"attachment;filename=" + new String(filename.getBytes("gb2312"), "ISO-8859-1"));
			response.addHeader("Content-Length", "" + buffer.length);
			response.setContentType("java"); 			// 设置返回的文件类型

			toClient.write(buffer);
			toClient.flush();
			toClient.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = RequestURL.ProDictionaryInfo.GET_PRO_DICTIONARY_INFO)
	public ModelAndView getDictionary(HttpServletResponse response, @RequestParam Integer p_code ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			ResultMapUtils.putData(resultMap, ProCacheUtil.getCache(CacheName.DICTIONARY_RELATION, p_code.toString(), new ProDictionaryInfoBO()).getSub() );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
}
