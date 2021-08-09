package com.enji_iot.develop.DAO;

import com.enji_iot.util.Entity.bo.ProDictionaryInfoBO;
import com.enji_iot.util.Util.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProDictionaryInfoMapper {
    Integer generateCode();
    Integer selectPageListCount(@Param(value = "proDictionaryInfo")ProDictionaryInfoBO proDictionaryInfo);
    Integer insert(@Param(value = "proDictionaryInfo")ProDictionaryInfoBO proDictionaryInfo);
    Integer update(@Param(value = "proDictionaryInfo")ProDictionaryInfoBO proDictionaryInfo);
    Integer delete(@Param(value = "proDictionaryInfo")ProDictionaryInfoBO proDictionaryInfo);
    Integer deleteByPcode(@Param(value = "proDictionaryInfo")ProDictionaryInfoBO proDictionaryInfo);
    Integer updateByCondition(@Param(value = "proDictionaryInfo")ProDictionaryInfoBO proDictionaryInfo);
    List<ProDictionaryInfoBO> selectPageList(@Param(value = "proDictionaryInfo")ProDictionaryInfoBO proDictionaryInfo);
    ProDictionaryInfoBO selectOne(@Param(value = "proDictionaryInfo")ProDictionaryInfoBO proDictionaryInfo);
}
