package com.enji_iot.util.Util;

import java.util.Calendar;

public class TimeUtil {
    public static int getNowYear(){
        return Calendar.getInstance().get(Calendar.YEAR);
    }
    public static int getNowMonth(){
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }
    public static int getNowDay(){
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }
    public static String toString(int time){
        String strTime;
        if(time<10){
            strTime = "0"+time;
        }
        else strTime = Integer.toString(time);
        return strTime;
    }
}
