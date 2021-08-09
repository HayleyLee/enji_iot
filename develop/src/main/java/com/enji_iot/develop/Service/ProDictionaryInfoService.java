package com.enji_iot.develop.Service;

import com.enji_iot.util.Entity.bo.ProDictionaryInfoBO;
import com.enji_iot.util.Util.PageBean;

import java.util.Map;

public interface ProDictionaryInfoService {
    Map<String, Object> selectPageList(PageBean pageBean, ProDictionaryInfoBO proDictionaryInfo);
    Map<String, Object> selectOne(ProDictionaryInfoBO proDictionaryInfo);
    Map<String, Object> generateCode(ProDictionaryInfoBO proDictionaryInfo);
    Map<String, Object> insert(ProDictionaryInfoBO proDictionaryInfo);
    Map<String, Object> update(ProDictionaryInfoBO proDictionaryInfo);
    Map<String, Object> delete(ProDictionaryInfoBO proDictionaryInfo);
    Map<String, Object> deleteByPcode(ProDictionaryInfoBO proDictionaryInfo);
    Map<String, Object> updateByCondition(ProDictionaryInfoBO proDictionaryInfo);
}
