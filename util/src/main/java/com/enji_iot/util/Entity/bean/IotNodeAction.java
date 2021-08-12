package com.enji_iot.util.Entity.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class IotNodeAction extends BaseBean {
    private Integer id;
    private Integer scene_id;
    private String device_code;
    private Integer device_status;
    private String command;
    private Date time;

    public IotNodeAction() {
    }

    public IotNodeAction(Integer id, Integer scene_id, String device_code, Integer device_status, String command, Date time) {
        this.id = id;
        this.scene_id = scene_id;
        this.device_code = device_code;
        this.device_status = device_status;
        this.command = command;
        this.time = time;
    }

    public IotNodeAction(String sensor_name, Integer scene_id, Integer user_id, String start_time, String end_time, Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, Integer id1, Integer scene_id1, String device_code, Integer device_status, String command, Date time) {
        super(sensor_name, scene_id, user_id, start_time, end_time, id, id_array, delete_flag, offset, limit, data);
        this.id = id1;
        this.scene_id = scene_id1;
        this.device_code = device_code;
        this.device_status = device_status;
        this.command = command;
        this.time = time;
    }

    public IotNodeAction(Integer id, List<Integer> id_array, Integer delete_flag, Integer offset, Integer limit, Map<String, String> data, Integer id1, Integer scene_id, String device_code, Integer device_status, String command, Date time) {
        super(id, id_array, delete_flag, offset, limit, data);
        this.id = id1;
        this.scene_id = scene_id;
        this.device_code = device_code;
        this.device_status = device_status;
        this.command = command;
        this.time = time;
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

    public Integer getDevice_status() {
        return device_status;
    }

    public void setDevice_status(Integer device_status) {
        this.device_status = device_status;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
