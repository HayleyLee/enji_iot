package com.enji_iot.util.Entity.bean;

import java.util.List;
import java.util.Map;

public class IotHistoryNodeData extends BaseBean{

    private  Integer id;
    private  Integer scene_id;
    /**device_code*/
    private String device_code;

    /**name*/
    private String name;

    /**val*/
    private Float val;

    /**time*/
    private String time;

    private String unit;

    private  Integer startPage;
    private  Integer endPage;

    public IotHistoryNodeData(Integer id, Integer scene_id, String device_code, String name, Float val, String time, String unit, Integer startPage, Integer endPage) {
        this.id = id;
        this.scene_id = scene_id;
        this.device_code = device_code;
        this.name = name;
        this.val = val;
        this.time = time;
        this.unit = unit;
        this.startPage = startPage;
        this.endPage = endPage;
    }

    public IotHistoryNodeData(String sensor_name, Integer scene_id, Integer user_id, String start_time, String end_time, Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, Integer id1, Integer scene_id1, String device_code, String name, Float val, String time, String unit, Integer startPage, Integer endPage) {
        super(sensor_name, scene_id, user_id, start_time, end_time, id, id_array, delete_flag, offset, limit, data);
        this.id = id1;
        this.scene_id = scene_id1;
        this.device_code = device_code;
        this.name = name;
        this.val = val;
        this.time = time;
        this.unit = unit;
        this.startPage = startPage;
        this.endPage = endPage;
    }

    public IotHistoryNodeData(Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, Integer id1, Integer scene_id, String device_code, String name, Float val, String time, String unit, Integer startPage, Integer endPage) {
        super(id, id_array, delete_flag, offset, limit, data);
        this.id = id1;
        this.scene_id = scene_id;
        this.device_code = device_code;
        this.name = name;
        this.val = val;
        this.time = time;
        this.unit = unit;
        this.startPage = startPage;
        this.endPage = endPage;
    }

    public IotHistoryNodeData() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getScene_id() {
        return scene_id;
    }

    @Override
    public void setScene_id(Integer scene_id) {
        this.scene_id = scene_id;
    }

    public String getDevice_code() {
        return device_code;
    }

    public void setDevice_code(String device_code) {
        this.device_code = device_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getVal() {
        return val;
    }

    public void setVal(Float val) {
        this.val = val;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getStartPage() {
        return startPage;
    }

    public void setStartPage(Integer startPage) {
        this.startPage = startPage;
    }

    public Integer getEndPage() {
        return endPage;
    }

    public void setEndPage(Integer endPage) {
        this.endPage = endPage;
    }
}
