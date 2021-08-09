package com.enji_iot.develop.DAO;

import com.enji_iot.util.Entity.bean.TableSystem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemMapper {
    List<TableSystem> selectHistoryInfo(@Param(value = "tableSystem")TableSystem tableSystem);
    Integer selectHistoryCount(@Param(value = "tableSystem")TableSystem tableSystem);
    void createHistoryTable(@Param(value = "tableSystem")TableSystem tableSystem);
    void modifyHistoryTable(@Param(value = "tableSystem")TableSystem tableSystem);
}
