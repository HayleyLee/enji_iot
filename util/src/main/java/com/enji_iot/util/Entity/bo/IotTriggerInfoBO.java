package com.enji_iot.util.Entity.bo;

import com.enji_iot.util.Entity.bean.IotTriggerInfo;

/**
 * @类:IotTriggerInfo
 * @作者:chenrj
 */

public class IotTriggerInfoBO extends IotTriggerInfo {

    public IotTriggerInfoBO(Integer id) {
        super();
        this.setId(id);
    }

    public IotTriggerInfoBO() {
    }

    private String from_sensor_name;

    private String subActionParam;

    private Integer node_id;

    private String node_dev_code;
    private String iot_sensor_name;
    // 最近是否触发过
    private Boolean is_worked;

    public String getFrom_sensor_name() {
        return from_sensor_name;
    }

    public void setFrom_sensor_name(String from_sensor_name) {
        this.from_sensor_name = from_sensor_name;
    }

    public String getSubActionParam() {
        return subActionParam;
    }

    public void setSubActionParam(String subActionParam) {
        this.subActionParam = subActionParam;
    }

    public Integer getNode_id() {
        return node_id;
    }

    public void setNode_id(Integer node_id) {
        this.node_id = node_id;
    }

    public String getNode_dev_code() {
        return node_dev_code;
    }

    public void setNode_dev_code(String node_dev_code) {
        this.node_dev_code = node_dev_code;
    }

    public String getIot_sensor_name() {
        return iot_sensor_name;
    }

    public void setIot_sensor_name(String iot_sensor_name) {
        this.iot_sensor_name = iot_sensor_name;
    }

    public Boolean getIs_worked() {
        return is_worked;
    }

    public void setIs_worked(Boolean is_worked) {
        this.is_worked = is_worked;
    }
}

