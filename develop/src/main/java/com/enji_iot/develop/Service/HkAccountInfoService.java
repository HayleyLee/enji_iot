package com.enji_iot.develop.Service;


import com.enji_iot.util.Entity.bo.HkAccountInfoBO;
import com.enji_iot.util.Util.PageBean;

import java.util.Map;

public interface HkAccountInfoService {
    Map<String,Object> selectPageList(HkAccountInfoBO hkAccountInfo, PageBean pageBean);
    Map<String,Object> insert(HkAccountInfoBO hkAccountInfo);
    Map<String,Object> selectOne(HkAccountInfoBO hkAccountInfo);
    Map<String,Object> update(HkAccountInfoBO hkAccountInfo);
}
 
 
