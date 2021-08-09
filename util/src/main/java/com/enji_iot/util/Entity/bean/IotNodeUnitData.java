package com.enji_iot.util.Entity.bean;

import java.util.List;
import java.util.Map;

public class IotNodeUnitData extends BaseBean{

    private String device_code;

    private String unit;

    /**
     * customize_name用户自定义昵称
     */
    private String customize_name;

    private Integer sensor_type;

    private String sensor_type_name;

    public IotNodeUnitData(String device_code, String unit, String customize_name, Integer sensor_type, String sensor_type_name) {
        this.device_code = device_code;
        this.unit = unit;
        this.customize_name = customize_name;
        this.sensor_type = sensor_type;
        this.sensor_type_name = sensor_type_name;
    }

    public IotNodeUnitData(String sensor_name, Integer scene_id, Integer user_id, String start_time, String end_time, Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, String device_code, String unit, String customize_name, Integer sensor_type, String sensor_type_name) {
        super(sensor_name, scene_id, user_id, start_time, end_time, id, id_array, delete_flag, offset, limit, data);
        this.device_code = device_code;
        this.unit = unit;
        this.customize_name = customize_name;
        this.sensor_type = sensor_type;
        this.sensor_type_name = sensor_type_name;
    }

    public IotNodeUnitData(Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, String device_code, String unit, String customize_name, Integer sensor_type, String sensor_type_name) {
        super(id, id_array, delete_flag, offset, limit, data);
        this.device_code = device_code;
        this.unit = unit;
        this.customize_name = customize_name;
        this.sensor_type = sensor_type;
        this.sensor_type_name = sensor_type_name;
    }

    public IotNodeUnitData() {
    }

    public String getDevice_code() {
        return device_code;
    }

    public void setDevice_code(String device_code) {
        this.device_code = device_code;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCustomize_name() {
        return customize_name;
    }

    public void setCustomize_name(String customize_name) {
        this.customize_name = customize_name;
    }

    public Integer getSensor_type() {
        return sensor_type;
    }

    public void setSensor_type(Integer sensor_type) {
        this.sensor_type = sensor_type;
    }

    public String getSensor_type_name() {
        return sensor_type_name;
    }

    public void setSensor_type_name(String sensor_type_name) {
        this.sensor_type_name = sensor_type_name;
    }
}
