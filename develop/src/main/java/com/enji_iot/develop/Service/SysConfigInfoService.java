package com.enji_iot.develop.Service;


import com.enji_iot.util.Entity.bo.SysConfigInfoBO;
import com.enji_iot.util.Util.PageBean;

import java.util.Map;

public interface SysConfigInfoService {
    Map<String, Object> selectPageList(SysConfigInfoBO sysConfigInfo, PageBean pageBean);
    Map<String, Object> insert(SysConfigInfoBO sysConfigInfo);
    Map<String, Object> update(SysConfigInfoBO sysConfigInfo);
    Map<String, Object> selectOne(SysConfigInfoBO sysConfigInfo);
}
 
 
