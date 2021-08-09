package com.enji_iot.develop.Service;

import com.enji_iot.util.Entity.bo.UserInfoBO;
import com.enji_iot.util.Util.PageBean;

import java.util.Map;

public interface UserService{

	Map<String, Object> sendSecurityCode(String name) ;

	Map<String, Object> userRegister(UserInfoBO user);

	Map<String, Object> validateCode(String validatecode);

	Map<String, Object> resetPassword(UserInfoBO user);

	Map<String, Object> mailValidatePassword(String validatecode);

	Map<String, Object> userRegisterByPhone(UserInfoBO user);

	Map<String, Object> updatePassword(UserInfoBO user);

	Map<String, Object> updatePasswordByKey(UserInfoBO user);

	Map<String, Object> selectOne(UserInfoBO user);

	Map<String, Object> update(UserInfoBO user);

	Map<String, Object> selectOneLogin(UserInfoBO user);

	Integer count(UserInfoBO user);

	Map<String, Object> select(UserInfoBO user, PageBean pageBean);

	Map<String, Object> insert(UserInfoBO user);

}
