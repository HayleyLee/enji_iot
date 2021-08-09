package com.enji_iot.util.Util;

import com.enji_iot.util.Entity.dto.ResponseObjectDto;
import net.sf.json.JSONArray;

import java.util.ArrayList;

public class ResponseUtil {
    public static JSONArray getResponse(String param_0,String param_1,Object... data) {
        ArrayList<Object> list = new ArrayList<>();
        ResponseObjectDto responseObjectDto = new ResponseObjectDto(param_0,param_1);
        list.add(responseObjectDto);
        list.add(data);
        return JSONArray.fromObject(list);
    }
}
