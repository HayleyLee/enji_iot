package com.enji_iot.util.Util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import me.chanjar.weixin.common.util.StringUtils;

public class DateUtils {

	/** 年月日时分秒(无下划线) yyyyMMddHHmmss */
	public static final String dtLong = "yyyyMMddHHmmss";

	/** 年月日时分秒毫秒(无下划线) yyyyMMddHHmmssSSS */
	public static final String dtVeryLong = "yyyyMMddHHmmssSSS";

	/** 完整时间 yyyy-MM-dd HH:mm */
	public static final String simple = "yyyy-MM-dd HH:mm";
	
	/** 完整时间 yyyy/MM/dd HH:mm:ss */
	public static final String simpleALL = "yyyy/MM/dd HH:mm:ss";

	/** 年月日(无下划线) yyyyMMdd */
	public static final String dtSimple = "yyyy-MM-dd";

	/** 年月日(无下划线) yyyyMMdd */
	public static final String dtShort = "yyyyMMdd";

	/** 年月日 yyyy.MM.dd */
	public static final String dtShortPoint = "yyyy.MM.dd";
	/** 年月日 yyyy.MM.dd HH:mm */
	public static final String dtLongPoint = "yyyy.MM.dd HH:mm";

	/**
	 * 字符串转日期
	 * 
	 * @param pattern
	 * @param text
	 * @return
	 * @throws ParseException
	 */
	public static Date parse(String pattern, String text) throws ParseException {
		if (StringUtils.isEmpty(text)) {
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		return df.parse(text);
	}

	/**
	 * 日期转字符串
	 * 
	 * @param pattern
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String format(String pattern, Date date) {
		if (date == null) {
			return "-";
		}
		try{
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			return df.format(date);
		}catch(Exception e){
			return "-";
		}
	}

	/**
	 * 格式:年月日时分秒(无下划线) yyyyMMddHHmmss
	 * 
	 * @param text
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDtLong(String text) throws ParseException {
		return parse(dtLong, text);
	}

	/**
	 * 格式:完整时间 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param text
	 * @return
	 * @throws ParseException
	 */
	public static Date parseSimple(String text) throws ParseException {
		return parse(simple, text);
	}

	/**
	 * 格式:年月日时分秒毫秒(无下划线) yyyyMMddHHmmssSSS
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String formatDtVeryLong(Date date) throws ParseException {
		return format(dtVeryLong, date);
	}

	/**
	 * 格式:年月日时分秒(无下划线) yyyyMMddHHmmss
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String formatDtLong(Date date) throws ParseException {
		return format(dtLong, date);
	}

	/**
	 * 格式:完整时间 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String formatSimple(Date date) throws ParseException {
		return format(simple, date);
	}

	/**
	 * 获取当前的时间 格式: 时:分:秒
	 * 
	 * @return
	 */
	public static String getNowTimeStr() {
		Calendar calendar = Calendar.getInstance();
		/**
		 * 小时
		 */
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		/**
		 * 分钟
		 */
		int minute = calendar.get(Calendar.MINUTE);
		/**
		 * 秒钟
		 */
		int second = calendar.get(Calendar.SECOND);
		/**
		 * 拼接时间（时分秒）
		 */
		String time = (hour < 10 ? "0" + hour : hour) + ":" + (minute < 10 ? "0" + minute : minute) + ":"
				+ (second < 10 ? "0" + second : second);
		/**
		 * 返回时分秒
		 */
		return time;
	}

	/**
	 * 获取当前时间的前一天
	 */
	public static Date getBeforeOneDateTime(Date day) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(day);

		int dateday = calendar.get(Calendar.DATE);

		calendar.set(Calendar.DATE, dateday - 1);

		return calendar.getTime();
	}

	/**
	 * 获取当前时间的后一天
	 */
	public static Date getAfterOneDateTime(Date day) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(day);

		int dateday = calendar.get(Calendar.DATE);

		calendar.set(Calendar.DATE, dateday + 1);

		return calendar.getTime();
	}

	/**
	 * 获取当前的时间毫秒数,格林尼治时间 格式: 时:分:秒
	 * 
	 * @return the number of milliseconds since January 1, 1970, 00:00:00 GMT
	 *         represented by this date.
	 */
	public static long getNowTime() {
		Calendar calendar = Calendar.getInstance();
		/**
		 * 小时
		 */
		int hour = calendar.get(Calendar.HOUR_OF_DAY) - 8;
		/**
		 * 分钟
		 */
		int minute = calendar.get(Calendar.MINUTE);
		/**
		 * 秒钟
		 */
		int second = calendar.get(Calendar.SECOND);
		/**
		 * 返回毫秒数
		 */
		return (hour * 60 * 60 + minute * 60 + second) * 1000;
	}

	/**
	 * 获取当前日期
	 * 
	 * @param args
	 */
	public static Date getCurrentDate() {
		// 获取当前日期
		Calendar calendar = new GregorianCalendar();
		GregorianCalendar today = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH));
		return today.getTime();
	}

	/**
	 * 获取当前日期
	 * 
	 * @param args
	 */
	public static Date getCurrentDate(long time) {
		// 获取当前日期
		Calendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(time);
		GregorianCalendar today = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH));
		return today.getTime();
	}
	
	/**
	 * 获取日期
	 * 
	 * @param args
	 */
	public static Date getDate(long time) {
		// 获取当前日期
		Calendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(time);
		return calendar.getTime();
	}
	
	public static Boolean getTimeBeforetimes(Date time, Integer minute) {
		Calendar nowTime = Calendar.getInstance();
		nowTime.add(Calendar.MINUTE, -1* minute);
		return nowTime.getTime().getTime() < time.getTime() ;
	}
	
	/**
	 * 获取抽象的时间描述
	 * 
	 * @param insert_time
	 * @return
	 */
	public static String getTimeDesc(Date insert_time) {
		String insert_time_desc = "";
		long inTime = insert_time.getTime();
		long curTime = System.currentTimeMillis();
		// 差值，分钟
		double dValue = Math.ceil((curTime - inTime) / 1000 / 60);
		if (dValue < 1)
			insert_time_desc = "刚刚";
		else if (dValue < 60)
			insert_time_desc = (long) dValue + "分钟前";
		else if (dValue < 60 * 24)
			insert_time_desc = (long) Math.floor(dValue / 60) + "小时前";
		else if (dValue < 60 * 24 * 30)
			insert_time_desc = (long) Math.floor(dValue / 60 / 24) + "天前";
		else if (dValue < 60 * 24 * 365)
			insert_time_desc = (long) Math.floor(dValue / 60 / 24 / 30) + "月前";
		else
			insert_time_desc = (long) Math.floor(dValue / 60 / 24 / 365) + "年前";
		return insert_time_desc;
	}

	/**
	 * 
	 * 取得时间
	 * 
	 * @param type
	 * @return
	 */
	public static Date getDate(int type) {
		Date date = null;
		try {
			switch (type) {
			case 1:
				// 当天12:00
				date = DateUtils.parse(DateUtils.simple, DateUtils.format(DateUtils.dtSimple, new Date()) + " 12:00");
				break;
			case 2:
				// 明天12:00
				date = DateUtils.parse(DateUtils.simple,
						DateUtils.format(DateUtils.dtSimple, DateUtils.getAfterOneDateTime(new Date())) + " 12:00");
				break;
			case 3:
				// 后天12:00
				date = DateUtils
						.parse(DateUtils.simple,
								DateUtils.format(DateUtils.dtSimple,
										DateUtils.getAfterOneDateTime(DateUtils.getAfterOneDateTime(new Date())))
										+ " 12:00");
				break;
			case 4:
				// 当天09:00
				date = DateUtils.parse(DateUtils.simple, DateUtils.format(DateUtils.dtSimple, new Date()) + " 09:00");
				break;
			case 5:
				// 当天00:00
				date = DateUtils.parse(DateUtils.simple, DateUtils.format(DateUtils.dtSimple, new Date()) + " 00:00");
				break;

			case 6:
				// 明天00:00
				date = DateUtils.parse(DateUtils.simple,
						DateUtils.format(DateUtils.dtSimple, DateUtils.getAfterOneDateTime(new Date())) + " 00:00");
				break;
			case 7:
				// 昨天00:00
				date = DateUtils.parse(DateUtils.simple,
						DateUtils.format(DateUtils.dtSimple, DateUtils.getBeforeOneDateTime(new Date())) + " 00:00");
				break;
			case 8:
				// 本周一00:00
				//
				date = DateUtils.parse(DateUtils.simple,
						DateUtils.format(DateUtils.dtSimple, getMondayOfThisWeek()) + " 00:00");
				break;
			case 9:
				// 本周日23:59
				//
				date = DateUtils.parse(DateUtils.simple,
						DateUtils.format(DateUtils.dtSimple, getSundayOfThisWeek()) + " 23:59");
				break;
			case 10:
				// 当天23:59
				//
				date = DateUtils.parse(DateUtils.simple,
						DateUtils.format(DateUtils.dtSimple, new Date()) + " 23:59");
				break;
			case 11:
				// 当月第1天 00:00
				//
				date = DateUtils.parse(DateUtils.simple,
						DateUtils.format(DateUtils.dtSimple, getFirstDayOfThisMonth()) + " 00:00");
				break;
			case 12:
				// 当月最后一天 23:59
				//
				date = DateUtils.parse(DateUtils.simple,
						DateUtils.format(DateUtils.dtSimple, getLastDayOfThisMonth()) + " 23:59");
				break;
			case 13:
				// 当天23:59
				date = DateUtils.parse(DateUtils.simple, DateUtils.format(DateUtils.dtSimple, new Date()) + " 23:59");
				break;
			default:
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return date;
	}

	public static void main(String[] args) {
		getDate(6);
		getDate(7);
		getDate(13);
		
	}

	/**
	 * 得到本周周一
	 *
	 * @return yyyy-MM-dd
	 */
	public static Date getMondayOfThisWeek() {
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + 1);
		return c.getTime();
	}

	/**
	 * 得到本周周日
	 *
	 * @return yyyy-MM-dd
	 */
	public static Date getSundayOfThisWeek() {
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + 7);
		return c.getTime();
	}

	
	 /**
     * 当月第一天
     * @return
     */
    private static Date getFirstDayOfThisMonth() {
    	Calendar calendar = Calendar.getInstance();     
        calendar.set(Calendar.DAY_OF_MONTH, calendar     
                .getActualMinimum(Calendar.DAY_OF_MONTH)); 
//        calendar.setTime(getDate(5));
        return calendar.getTime();
    }
    
	 /**
     * 当月最后一天
     * @return
     */
    private static Date getLastDayOfThisMonth() {
    	Calendar calendar = Calendar.getInstance();     
        calendar.set(Calendar.DAY_OF_MONTH, calendar     
                .getActualMaximum(Calendar.DAY_OF_MONTH)); 
        return calendar.getTime();
    }
	
	/**
	 * 
	 * 取得时间
	 * 
	 * @param type
	 * @return
	 */
	public static String getDateStrDt(int type) {
		try {
			return DateUtils.format(DateUtils.dtSimple, getDate(type));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}
	
	/**
	 * 取得当前时间与参数时间的差额,如果minus不为空的话,则返回当前时间-参数时间-差额时间
	 * 
	 * @param ts
	 * @param minus_seconds
	 *            差额
	 * @return
	 */
	public static long getMinusFromCurrent(Timestamp ts, int... minus_seconds) {
		long curTimeMill = System.currentTimeMillis();
		if (minus_seconds.length > 0) {
			return curTimeMill - ts.getTime() - minus_seconds[0] * 1000;
		}
		return System.currentTimeMillis() - ts.getTime();
	}

	/**
	 * @see getMinusFromCurrent()
	 * 
	 * @param date
	 * @param minus_seconds
	 *            差额
	 * @return
	 */
	public static long getMinusFromCurrent(Date date, int... minus_seconds) {
		long curTimeMill = System.currentTimeMillis();
		if (minus_seconds.length > 0) {
			return curTimeMill - date.getTime() - minus_seconds[0] * 1000;
		}
		return curTimeMill - date.getTime();
	}

	/**
	 * 获取过去第几天的日期
	 * @param past
	 * @return
	 */
	public static String getPastDate(int past) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String result = format.format(today);
		return result;
	}


	/**
	 * 获取未来第几天的日期
	 * @param past
	 * @return
	 */
	public static String getFutureDate(int past) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String result = format.format(today);
		return result;
	}

	/**
	 * 获取过去7天内的日期数组
	 * @param intervals      intervals天内
	 * @return              日期数组
	 */
	public static ArrayList<String> getDays(int intervals) {
		ArrayList<String> pastDaysList = new ArrayList<>();
		for (int i = intervals -1; i >= 0; i--) {
			pastDaysList.add(getPastDate(i));
		}
		return pastDaysList;
	}
}


