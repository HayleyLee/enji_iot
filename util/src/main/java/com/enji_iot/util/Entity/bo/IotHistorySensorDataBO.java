package com.enji_iot.util.Entity.bo;

import com.enji_iot.util.Entity.bean.IotHistorySensorData;

/**
 * @类:IotHistorySensorData
 * @作者:chenrj
 */

public class IotHistorySensorDataBO extends IotHistorySensorData {

    public IotHistorySensorDataBO(Integer id) {
        super();
        this.setId(id);
    }

    public IotHistorySensorDataBO() {
    }

    /**
     * 传感器单位
     */
    private Integer measure_unit_type;


    private Integer iot_sensor_type;

    private String atimestr;

    // 查询间隔时间
    private Integer query_interval_type;

    private String interval_p1;
    private String interval_p2;
    private Integer interval_p3;


    private Integer scene_id;

    private Integer node_id;

    private String ids;

    public Integer getMeasure_unit_type() {
        return measure_unit_type;
    }

    public void setMeasure_unit_type(Integer measure_unit_type) {
        this.measure_unit_type = measure_unit_type;
    }

    public Integer getIot_sensor_type() {
        return iot_sensor_type;
    }

    public void setIot_sensor_type(Integer iot_sensor_type) {
        this.iot_sensor_type = iot_sensor_type;
    }

    public String getAtimestr() {
        return atimestr;
    }

    public void setAtimestr(String atimestr) {
        this.atimestr = atimestr;
    }

    public Integer getQuery_interval_type() {
        return query_interval_type;
    }

    public void setQuery_interval_type(Integer query_interval_type) {
        this.query_interval_type = query_interval_type;
    }

    public String getInterval_p1() {
        return interval_p1;
    }

    public void setInterval_p1(String interval_p1) {
        this.interval_p1 = interval_p1;
    }

    public String getInterval_p2() {
        return interval_p2;
    }

    public void setInterval_p2(String interval_p2) {
        this.interval_p2 = interval_p2;
    }

    public Integer getInterval_p3() {
        return interval_p3;
    }

    public void setInterval_p3(Integer interval_p3) {
        this.interval_p3 = interval_p3;
    }

    @Override
    public Integer getScene_id() {
        return scene_id;
    }

    @Override
    public void setScene_id(Integer scene_id) {
        this.scene_id = scene_id;
    }

    public Integer getNode_id() {
        return node_id;
    }

    public void setNode_id(Integer node_id) {
        this.node_id = node_id;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}
