package com.enji_iot.develop.Service;

import com.enji_iot.util.Entity.bo.ContactUserInfoBO;
import com.enji_iot.util.Util.PageBean;

import java.util.Map;

public interface ContactUserInfoService {
    Map<String,Object> selectPageList(ContactUserInfoBO contactUserInfoBO, PageBean pageBean);
    Map<String,Object> insert(ContactUserInfoBO contactUserInfoBO);
    Map<String,Object> update(ContactUserInfoBO contactUserInfoBO);
    Map<String,Object> selectOne(ContactUserInfoBO contactUserInfoBO);
}
