package com.enji_iot.develop.Service.impl;

import com.enji_iot.develop.DAO.UserAccountInfoMapper;
import com.enji_iot.develop.DAO.UserInfoMapper;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Entity.bo.UserAccountInfoBO;
import com.enji_iot.util.Entity.bo.UserInfoBO;
import com.enji_iot.cache.Cache.CacheName;
import com.enji_iot.cache.Cache.ProCacheUtil;
import com.enji_iot.util.Config.ProConfig;
import com.enji_iot.develop.Service.UserService;
import com.enji_iot.develop.Service.base.MailService;
import com.enji_iot.util.Util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "UserService")
public class UserServiceImpl implements UserService  {

	@Autowired
	@Qualifier(value = "MailService")
	private MailService mailService;

	@Resource
	private UserInfoMapper userInfoMapper;

	@Resource
	private UserAccountInfoMapper userAccountInfoMapper;
	
	@Override
	public Map<String, Object> sendSecurityCode(String name) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			// 检索当前用户存在与否
			UserInfoBO userInfoBO = new UserInfoBO();
			userInfoBO.setEmail(name);
//			UserInfoBO user = (UserInfoBO) dao.selectOne("UserInfo.selectOne", userInfoBO);
			UserInfoBO user = userInfoMapper.selectOne(userInfoBO);
			if (user == null) {
				return ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.UserInfo.USER_NOT_EXISTS);
			}
			// 邮箱验证码
			String uuid = CommonUtil.UUIDString.getEncodeUUIDString();
			// 更新用户
			userInfoBO.setValidate_code(uuid);
			userInfoBO.setValidate_time(new Date());
			userInfoBO.setId(user.getId());
//			dao.update("UserInfo.update", userInfoBO);
			userInfoMapper.update(userInfoBO);
			// 发邮件
			Map<String, Object> mail = new HashMap<String, Object>();
			mail.put("name", name);
			mail.put("email", name);
			mail.put("url",ProConfig.SERVER_DOMAIN + MessageFormat.format("/service/mail/{0}", new Object[] { 2 + uuid }));
			mailService.send(name, ProConfig.PROJECT_NAME + "用户激活邮件", "tpl/vm/user_register_activation", mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> userRegisterByPhone(UserInfoBO user) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			UserInfoBO userInfoBO = new UserInfoBO();
			userInfoBO.setName(user.getPhone());

//			userInfoBO = dao.selectOne("UserInfo.selectOne", userInfoBO);
			userInfoBO = userInfoMapper.selectOne(userInfoBO);
			if(ObjectUtil.isNotEmpty(userInfoBO)){
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.UserInfo.PHONE_EXIST);
			}else{
				user.setPhone(user.getPhone());
				user.setName(user.getPhone());
				user.setUser_key(ObjectUtil.UUIDString.getUUIDString());
				user.setStatus(Code.UserStatus.NORMAL);
				// 注册为管理员用户
				user.setType(Code.UserType.MANAGER);
				user.setValidate_time(new Date());
//				Integer i = dao.insert("UserInfo.insert", user);
				userInfoMapper.insert(user);
				// 添加用户账户表
				UserAccountInfoBO userAccountBo = new UserAccountInfoBO();
				userAccountBo.setUser_id(user.getId());
//				Integer j = dao.insert("UserAccountInfo.insertSimple", userAccountBo);
				Integer j = userAccountInfoMapper.insertSimple(userAccountBo);
				if( j> 0){
					ProCacheUtil.addCache(CacheName.USERACCOUNT_ID, userAccountBo.getUser_id().toString() ,userAccountBo);
				}
				ResultMapUtils.putData(resultMap, user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> userRegister(UserInfoBO user) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			UserInfoBO userInfoBO = new UserInfoBO();
			userInfoBO.setEmail(user.getName());
//			userInfoBO = dao.selectOne("UserInfo.selectOne", userInfoBO);
			userInfoBO = userInfoMapper.selectOne(userInfoBO);
			if(ObjectUtil.isNotEmpty(userInfoBO)){
				if(userInfoBO.getStatus() == Code.UserStatus.UN_ACTIVED){
					user.setId(userInfoBO.getId());
					user.setEmail(user.getName());
					user.setValidate_code( ObjectUtil.UUIDString.getUUIDString());
					user.setValidate_time(new Date());
					user.setMtime(new Date()); ;
//					Integer i = dao.update("UserInfo.update", user);
					Integer i = userInfoMapper.update(user);
					if( ObjectUtil.isIntegerOverZero(i) ){
						// 发邮件 待激活用户
						Map<String, Object> mail = new HashMap<String, Object>();
						mail.put("name", user.getEmail());
						mail.put("email", user.getEmail());
						mail.put("url",ProConfig.SERVER_DOMAIN + MessageFormat.format("/service/mail/{0}", new Object[] {  user.getValidate_code() }));
						mailService.send(user.getEmail(), ProConfig.PROJECT_NAME + "用户激活邮件", "tpl/vm/user_register_activation.vm", mail);					
					}
					ResultMapUtils.putData(resultMap, userInfoBO);
				}else{
					return ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.UserInfo.USER_EXIST);					
				}
			}else{
				user.setEmail(user.getName());
				user.setValidate_code( ObjectUtil.UUIDString.getUUIDString());
				user.setUser_key(ObjectUtil.UUIDString.getUUIDString());
				user.setStatus(Code.UserStatus.UN_ACTIVED);
				// 注册只有普通用户 @20181103 这边修改一下，注册为管理员用户
				user.setType(Code.UserType.MANAGER);
				user.setValidate_time(new Date());
				user.setMtime(new Date());
//				Integer i = dao.insert("UserInfo.insert", user);
				Integer i = userInfoMapper.insert(user);

				UserAccountInfoBO userAccountBo = new UserAccountInfoBO();
				userAccountBo.setUser_id(user.getId());
//				Integer j = dao.insert("UserAccountInfo.insertSimple", userAccountBo);
				Integer j = userAccountInfoMapper.insertSimple(userAccountBo);
				if( j> 0){
					ProCacheUtil.addCache(CacheName.USERACCOUNT_ID, userAccountBo.getUser_id().toString() ,userAccountBo);
				}
				
				if( ObjectUtil.isIntegerOverZero(i) ){
					// 发邮件 待激活用户
					Map<String, Object> mail = new HashMap<String, Object>();
					mail.put("name", user.getEmail());
					mail.put("email", user.getEmail());
					mail.put("url",ProConfig.SERVER_DOMAIN + MessageFormat.format("/service/mail/{0}", new Object[] {  user.getValidate_code() }));
					mailService.send(user.getEmail(), ProConfig.PROJECT_NAME + "用户激活邮件", "tpl/vm/user_register_activation.vm", mail);					
				}
				ResultMapUtils.putData(resultMap, user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> validateCode(String validatecode) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			UserInfoBO userInfoBO = new UserInfoBO();
			userInfoBO.setValidate_code(validatecode);
//			userInfoBO =  dao.selectOne("UserInfo.selectOne", userInfoBO);
			userInfoBO =  userInfoMapper.selectOne(userInfoBO);
			if(ObjectUtil.isNotEmpty(userInfoBO)){
				if(userInfoBO.getStatus() == Code.UserStatus.UN_ACTIVED){
					if(DateUtils.getTimeBeforetimes(userInfoBO.getValidate_time(), 15)){
						UserInfoBO user = new UserInfoBO();
						user.setId(userInfoBO.getId());
						user.setStatus(Code.UserStatus.NORMAL);
//						Integer num = dao.update("UserInfo.update", user);
						Integer num = userInfoMapper.update(user);
						if(!ObjectUtil.isIntegerOverZero(num)){
							ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.ERROR);
						}
					}else{
						ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.ACTIVE_CODE_OVERDUE);
					}
				}else{
					ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.ACTIVED);
				}
			}else{
				// 当前激活码不存在 
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.NO_ACTIVE_CODE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> resetPassword(UserInfoBO user) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			UserInfoBO userInfoBO = new UserInfoBO();
			userInfoBO.setEmail(user.getEmail());
//			userInfoBO = dao.selectOne("UserInfo.selectOne", userInfoBO);
			userInfoBO = userInfoMapper.selectOne(userInfoBO);
			if(ObjectUtil.isNotEmpty(userInfoBO)){
				user.setId(userInfoBO.getId());
				user.setValidate_code( ObjectUtil.UUIDString.getUUIDString());
				user.setValidate_time(new Date());
//				Integer i = dao.update("UserInfo.update", user);
				Integer i = userInfoMapper.update(user);
				if( ObjectUtil.isIntegerOverZero(i) ){
					// 发邮件 重置密码
					Map<String, Object> mail = new HashMap<String, Object>();
					mail.put("name", user.getEmail());
					mail.put("email", user.getEmail());
					mail.put("url",ProConfig.SERVER_DOMAIN + MessageFormat.format("/service/mail/reset/password/{0}", new Object[] { user.getValidate_code() }));
					mailService.send(user.getEmail(), ProConfig.PROJECT_NAME + "重置密码邮件", "tpl/vm/user_reset_password.vm", mail);
				}
			}else{
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.UserInfo.USER_NOT_EXISTS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> mailValidatePassword(String validatecode) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			UserInfoBO userInfoBO = new UserInfoBO();
			userInfoBO.setValidate_code(validatecode);
//			userInfoBO =  dao.selectOne("UserInfo.selectOne", userInfoBO);
			userInfoBO =  userInfoMapper.selectOne(userInfoBO);
			if(!ObjectUtil.isNotEmpty(userInfoBO)){
				ResultMapUtils.putStatusMsg(resultMap, "验证码不存在");
			}else{
				if(DateUtils.getTimeBeforetimes(userInfoBO.getValidate_time(), 15)){
					ResultMapUtils.putData(resultMap, userInfoBO);					
				}else{
					// 验证码已过期
					ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.ACTIVE_CODE_OVERDUE);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> updatePassword(UserInfoBO user) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			int rows = userInfoMapper.updatePassword(user);
			if (rows <= 0)  {
				// 执行失败
				return ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> updatePasswordByKey(UserInfoBO user) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			int rows = userInfoMapper.updatePasswordByKey(user);
			if (rows <= 0)  {
				return ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> selectOne(UserInfoBO user) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			UserInfoBO data = userInfoMapper.selectOne(user);
			if (data == null) {
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.NO_DATA);
			} else {
				ResultMapUtils.putData(resultMap, data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> update(UserInfoBO user) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			int rows = userInfoMapper.update(user);
			if (rows <= 0)  {
				return ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> selectOneLogin(UserInfoBO user) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			UserInfoBO data = userInfoMapper.selectOneLogin(user);
			if (data == null) {
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.NO_DATA);
			} else {
				ResultMapUtils.putData(resultMap, data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	@Override
	public Integer count(UserInfoBO user) {
		int result = -1;
		try {
			result = userInfoMapper.selectCount(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Map<String, Object> select(UserInfoBO user, PageBean pageBean) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			int totalCount = userInfoMapper.selectCount(user);
			if (totalCount > 0) {
				pageBean.setPageParam4Mysql(user);
				List<UserInfoBO> list = userInfoMapper.select(user);
				if(ObjectUtil.isNotEmpty(list)){
					pageBean.setTotalCount(totalCount);
					pageBean.setData(list);
					ResultMapUtils.putData(resultMap, pageBean);
				}else{
					ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.NO_DATA);
				}
			} else {
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.NO_DATA);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> insert(UserInfoBO user) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			int rows = userInfoMapper.insert(user);
			if (rows <= 0)  {
				// 执行失败
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}
}
