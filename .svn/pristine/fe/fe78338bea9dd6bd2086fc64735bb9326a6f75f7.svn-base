package com.ccx.models.util;

import java.text.*;
import java.util.*;

/**
 * Title: Description: 处理与日期有关的方法 Copyright: Copyright (c) 2002 Company: huawei
 *
 * @author lilong
 * @version 1.0
 */

public class DateTool {

	public static final int YEAR = 0;

	public static final int MONTH = 1;

	public static final int WEEK = 2;

	public static final int DAY = 3;

	public static final int HOUR = 4;

	public static final int MINUTE = 5;

	public static final int SECOND = 6;

	public static final int MILLISECOND = 7;

	public static final int MINUTEOFDAY = 8;
	private static final SimpleDateFormat dateFormat  = (SimpleDateFormat) DateFormat.getInstance();


	/**
	 * 把一个Date类的实例转换成8位由数字组成的字符串
	 */
	public static String transferDateToString(Date date) {
		return datetime2Str(date, "yyyyMMdd");

	}

	/**
	 * 把一个Date类的实例转换成10位由数字组成的字符串<br>
	 * yyyy-MM-dd
	 */
	public static String transferDateToString10(Date date) {
		return datetime2Str(date, "yyyy-MM-dd");

	}
    public static String transferDateToString11(Date date){
    	return datetime2Str(date, "HH:mm:ss");
    }
    public static String transferDateToString12(Date date){
    	return datetime2Str(date, "MM-dd HH:mm:ss");
    }
	/**
	 * 把一个Date类的实例转换成 yyyyMMdd hh:mm:ss 字符串
	 */
	public static String transferDateTimeToString(Date date) {

		return datetime2Str(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 把一个Date类的实例转换成指定格式的字符串
	 */
	public static String datetime2Str(Date date, String pattern) {
		// 2005-08-28任立修改
		if (date == null) {
			return null;
		}
		// 修改结束
		SimpleDateFormat dateFormat = (SimpleDateFormat) DateFormat
				.getInstance();
		dateFormat.applyPattern(pattern);
		return dateFormat.format(date);
	}

	/**
	 * 按要求转化时间的显示格式 参数：oldpattern，旧日期格式，如:yyyyMMddhhmmss 格式描述符的含义参见JDK
	 * simpleDateFormat类 newpattern，新日期格式
	 */
	public static String timeTransform(String time, String oldpattern,
			String newpattern) {
		if (time != null) {

			try {
				int length = newpattern.length();
				return time.substring(0, length);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	/**
	 * 获取指定格式的当前日期 参数：pattern，日期格式，如:yyyyMMddhhmmss 格式描述符的含义参见JDK
	 * simpleDateFormat类
	 */
	public static String getCurrentDate(String pattern) {
		if (pattern == null) {
			throw new IllegalArgumentException("input string parameter is null");
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date now = new Date();
		return sdf.format(now);
	}

	/**
	 * 将日期长整型转换成字符串 参数：time，long，从格林威治时间：1970年1月1日0点起的毫秒数 pattern, String,
	 * 转换的目标格式
	 */
	public static String long2TimeStr(long time, String pattern) {
		if (pattern == null) {
			throw new IllegalArgumentException(
					"pattern parameter can not be null");
		}
		Date dt = new Date(time);
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(dt);
	}

	/**
	 * 将日期型转换成字符串 参数：time，Date pattern, String, 转换的目标格式
	 */
	public static String date2TimeStr(Date time, String pattern) {
		if (pattern == null) {
			throw new IllegalArgumentException(
					"pattern parameter can not be null");
		}
		if (time == null) {
			return  null;
			//throw new IllegalArgumentException("time parameter can not be null");
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(time);
	}

    public static String addDate(Date d, int delta,String pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.add(Calendar.MONTH, delta);
        // 获取新的时间，并转换成长整形
        return date2TimeStr(calendar.getTime(),pattern);
    }
	/**
	 * 将日期增加一个增量，目前只能是，年，月，周，日，时、分、秒、毫秒 参数：date, long，原始时间 delta，int，增量的大小 unit,
	 * int, 增量的单位，YEAR, MONTH, DAY, HOUR, MINUTE, SECOND, MILLISECOND
	 * 返回：long，从格林威治时间：1970年1月1日0点起的毫秒数
	 */
	public static long addDate(long date, int delta, int unit) {
		if ((unit < YEAR) || (unit > MILLISECOND)) {
			throw new IllegalArgumentException(
					"time unit must in [YEAR, MONTH, WEEK, DAY, HOUR, MINUTE, SECOND, MILLISECOND], others not support");
		}
		Date dt = new Date(date);
		Calendar calendar = getLocalCalendar(dt);
		// 增加增量
		switch (unit) {
		case YEAR:
			calendar.add(Calendar.YEAR, delta);
			break;
		case MONTH:
			calendar.add(Calendar.MONTH, delta);
			break;
		case WEEK:
			calendar.add(Calendar.DAY_OF_WEEK, delta);
			break;
		case DAY:
			calendar.add(Calendar.DAY_OF_MONTH, delta);
			break;
		case HOUR:
			calendar.add(Calendar.HOUR, delta);
			break;
		case MINUTE:
			calendar.add(Calendar.MINUTE, delta);
			break;
		case SECOND:
			calendar.add(Calendar.SECOND, delta);
			break;
		case MILLISECOND:
			calendar.add(Calendar.MILLISECOND, delta);
		}
		// 获取新的时间，并转换成长整形
		Date ndt = calendar.getTime();
		return ndt.getTime();
	}

	/**
	 * 将日期增加一个增量，目前只能是，年，月，周，日，时，分，秒，毫秒 参数：date, long，原始时间 delta，int，增量的大小 unit,
	 * int, 增量的单位，YEAR, MONTH, WEEK, DAY, HOUR, MINUTE, SECOND, MILLISECOND
	 * pattern, String, 转换的目标格式 返回：String，指定格式的日期字符串
	 */
	public static String addDate(long date, int delta, int unit, String pattern) {
		if (pattern == null) {
			throw new IllegalArgumentException(
					"pattern parameter can not be null");
		}
		return long2TimeStr(addDate(date, delta, unit), pattern);
	}

	/**
	 * 将字符串转换成日期长整形 参数：time，String，日期字符串 pattern, String, 解析的格式 返回：long，日期长整形
	 */
	public static long timeStr2Long(String time, String pattern) {
		try {
			return timeStr2Date(time, pattern).getTime();
		} catch (Exception e) {
			System.out.println("时间格式不正确");
			e.printStackTrace();
		}
		return 0l;
	}

	/**
	 * 将字符串转换成日期形 参数：time，String，日期字符串 pattern, String, 解析的格式 返回：Date，日期形
	 */
	public static Date timeStr2Date(String time, String pattern) {
		if (time == null) {
			throw new IllegalArgumentException("time parameter can not be null");
		}
		if (pattern == null) {
			throw new IllegalArgumentException(
					"pattern parameter can not be null");
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			return sdf.parse(time);
		} catch (ParseException e) {
			throw new IllegalArgumentException("using [" + pattern
					+ "] parse [" + time + "] failed");
		}
	}

	/**
	 * 获取日期字符串的某一部分 参数：date，有效的日期字符串 pattern，日期格式字符串
	 * part，时间部分的指示符，只能是：YEAR,MONTH,WEEK,DAY,HOUR,MINUTE,SECOND，MILLISECOND
	 */
	public static int getDatePart(String date, String pattern, int part) {
		if (date == null) {
			throw new IllegalArgumentException("date parameter is null");
		}
		if (pattern == null) {
			throw new IllegalArgumentException("pattern parameter is null");
		}
		if ((part < YEAR) || (part > MINUTEOFDAY)) {
			throw new IllegalArgumentException(
					"the part parameter must be in [YEAR,MONTH, DAY, HOUR, MINUTE, SECOND]");
		}
		Date dt = timeStr2Date(date, pattern);
		return getDatePart(dt, part);
	}

	/**
	 * 获取日期的某一部分 参数：date，有效的日期类型
	 * part，时间部分的指示符，只能是：YEAR,MONTH,WEEK,DAY,HOUR,MINUTE,SECOND，MILLISECOND
	 */
	public static int getDatePart(Date date, int part) {
		if (date == null) {
			throw new IllegalArgumentException("date parameter is null");
		}
		if ((part < YEAR) || (part > MINUTEOFDAY)) {
			throw new IllegalArgumentException(
					"the part parameter must be in [YEAR,MONTH, DAY, HOUR, MINUTE, SECOND]");
		}
		Calendar calendar = getLocalCalendar(date);
		int result = 0;
		switch (part) {
		case YEAR:
			result = calendar.get(Calendar.YEAR);
			break;
		case MONTH:
			result = calendar.get(Calendar.MONTH);
			break;
		case WEEK:
			result = calendar.get(Calendar.DAY_OF_WEEK);
			break;
		case DAY:
			result = calendar.get(Calendar.DAY_OF_MONTH);
			break;
		case HOUR:
			result = calendar.get(Calendar.HOUR_OF_DAY);
			break;
		case MINUTE:
			result = calendar.get(Calendar.MINUTE);
			break;
		case SECOND:
			result = calendar.get(Calendar.SECOND);
			break;
		case MILLISECOND:
			result = calendar.get(Calendar.MILLISECOND);
			break;
		case MINUTEOFDAY:
			result = calendar.get(Calendar.HOUR_OF_DAY) * 60
					+ calendar.get(Calendar.MINUTE);
		}
		return result;
	}

	/**
	 * 获取下一个周期的开始时间 参数：date，String类型，有效的时间 pattern，String类型，时间格式字符串
	 * part，int类型，周期类型，可以是年、月、日、周
	 */
	public static String getNextPeriodTime(Date galeday, String pattern,
			int part) {
		// 打印调试信息
		/*
		 * if (Log.isLoggable(Log.DEBUG)) {
		 * Log.debug("DateTool::getNextPeriodTime(" + galeday + "," + pattern +
		 * "," + part + ")"); }
		 */
		if (galeday == null) {
			throw new IllegalArgumentException("date parameter is null");
		}
		if (pattern == null) {
			throw new IllegalArgumentException("pattern parameter is null");
		}
		if ((part < YEAR) || (part > DAY)) {
			throw new IllegalArgumentException(
					"the part parameter must be in [YEAR,MONTH, WEEK, DAY]");
		}
		String result = null;
		Calendar caldeduct = getLocalCalendar(galeday);
		Calendar calnow = getLocalCalendar(new Date());
		switch (part) {
		case DAY: // 扣费周期为每天
			return recursiveGet(caldeduct, calnow, pattern,
					Calendar.DAY_OF_MONTH, Calendar.HOUR, Calendar.HOUR);
		case WEEK: // 周期为每周
			return recursiveGetWeek(caldeduct, calnow, pattern,
					Calendar.DAY_OF_WEEK, Calendar.DAY_OF_MONTH, 0,
					Calendar.DAY_OF_WEEK);
		case YEAR: // 周期为每年
			return recursiveGet(caldeduct, calnow, pattern, Calendar.YEAR,
					Calendar.MONTH, Calendar.MONTH);
		case MONTH: // 周期为每月
			return recursiveGet(caldeduct, calnow, pattern, Calendar.MONTH,
					Calendar.DAY_OF_MONTH, Calendar.DAY_OF_MONTH);
		default:
			result = "unsupport period : " + String.valueOf(part);
		}
		return result;
	}

	private static String recursiveGetWeek(Calendar caldeduct, Calendar calnow,
			String pattern, int largepart, int part, int gap, int step) {
		/*
		 * if (Log.isLoggable(Log.DEBUG)) {
		 * Log.debug("DateTool::recursiveGetWeek:\n" + caldeduct + "\n" +
		 * calnow); }
		 */
		int deduct = caldeduct.get(step);
		int now = calnow.get(step);
		if (step == Calendar.DAY_OF_WEEK) {
			gap = deduct - now;
		}
		if (deduct > now) {
			calnow.add(step, gap);
			return DateTool.date2TimeStr(calnow.getTime(), pattern);
		} else if (deduct < now) {
			calnow.add(step, 7 + gap);
			return DateTool.date2TimeStr(calnow.getTime(), pattern);
		} else {
			switch (step) {
			case Calendar.DAY_OF_WEEK:
				step = Calendar.HOUR;
				break;
			case Calendar.HOUR:
				step = Calendar.MINUTE;
				break;
			case Calendar.MINUTE:
				step = Calendar.SECOND;
				break;
			case Calendar.SECOND:
				step = Calendar.MILLISECOND;
				break;
			case Calendar.MILLISECOND:
				return date2TimeStr(calnow.getTime(), pattern);
			}
			return recursiveGetWeek(caldeduct, calnow, pattern, largepart,
					part, gap, step);
		}
	}
	public static Long getCurrentDateTime(){
		return new Date().getTime();
	}
	private static String recursiveGet(Calendar caldeduct, Calendar calnow,
			String pattern, int largepart, int part, int step) {
		// 打印调试信息
		/*
		 * if (Log.isLoggable(Log.DEBUG)) {
		 * Log.debug("DateTool::recursiveGet(\n" + caldeduct + "\n" + calnow +
		 * "\n" + pattern + "\n" + largepart + "\n" + part + "\n" + step + ")"); }
		 */
		int deduct = caldeduct.get(step);
		int now = calnow.get(step);
		if (deduct > now) {
			calnow.set(part, caldeduct.get(part));
			if (largepart == Calendar.YEAR) {
				calnow.set(Calendar.DAY_OF_MONTH, caldeduct
						.get(Calendar.DAY_OF_MONTH));
			}
			return DateTool.date2TimeStr(calnow.getTime(), pattern);
		} else if (deduct < now) {
			calnow.add(largepart, 1);
			calnow.set(part, caldeduct.get(part));
			if (largepart == Calendar.YEAR) {
				calnow.set(Calendar.DAY_OF_MONTH, caldeduct
						.get(Calendar.DAY_OF_MONTH));
			}
			return DateTool.date2TimeStr(calnow.getTime(), pattern);
		} else {
			switch (step) {
			case Calendar.YEAR:
				step = Calendar.MONTH;
				break;
			case Calendar.MONTH:
				step = Calendar.DATE;
				break;
			case Calendar.DAY_OF_MONTH:
				step = Calendar.HOUR;
				break;
			case Calendar.HOUR:
				step = Calendar.MINUTE;
				break;
			case Calendar.MINUTE:
				step = Calendar.SECOND;
				break;
			case Calendar.SECOND:
				step = Calendar.MILLISECOND;
				break;
			case Calendar.MILLISECOND:
				return date2TimeStr(calnow.getTime(), pattern);
			}
			return recursiveGet(caldeduct, calnow, pattern, largepart, part,
					step);
		}
	}

	/**
	 * 获得东八时区的日历，并设置日历的当前日期 参数：date，Date，日期型
	 */
	public static Calendar getLocalCalendar(Date date) {
		// 设置为GMT+08:00时区
		String[] ids = TimeZone.getAvailableIDs(8 * 60 * 60 * 1000);
		if (ids.length == 0) {
			throw new IllegalArgumentException(
					"get id of GMT+08:00 time zone failed");
		}
		// SimpleTimeZone stz = new SimpleTimeZone(8 * 60 * 60 * 1000, ids[0]);
		// 创建Calendar对象，并设置为指定时间
		// Calendar calendar = new GregorianCalendar(stz);
		Calendar calendar = new GregorianCalendar(TimeZone.getDefault());
		// 设置成宽容方式
		if (!calendar.isLenient()) {
			calendar.setLenient(true);
		}
		// 设置SUNDAY为每周的第一天
		calendar.setFirstDayOfWeek(Calendar.SUNDAY);
		// 设置日历的当前时间
		calendar.setTime(date);
		return calendar;
	}
	/**
     *
    * @param dateTime 格式为"yyyy-mm-dd"的日期格式
     * @return string类型的下一天的日期
     * @throws ParseException
     */
    public  String getNextDate(String dateTime) throws ParseException{

       SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
       long dif = df.parse(dateTime).getTime()+86400*1000;
        Date date=new Date();
        date.setTime(dif);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

       return sdf.format(date);
      }
    /**
     * 验证是否为月末最后一天.
     * @author YangLei
     * @param date
     * @return
     */
    public static boolean isLastDayOfMonth(Date date) {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        if(calendar.get(Calendar.DAY_OF_MONTH)==calendar.getActualMaximum(Calendar.DATE)) {
            return true;
        }
        return false;
    }
    /**
     * 验证是否为月末最后一天财务结算时间.
     * @author YangLei
     * @param date
     * @return
     */
    public static boolean isFinTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (calendar.get(Calendar.DAY_OF_MONTH) == calendar.getActualMaximum(Calendar.DATE)) {
            if (calendar.get(Calendar.HOUR_OF_DAY) == 23) {
                if (calendar.get(Calendar.MINUTE) > 50 || calendar.get(Calendar.MINUTE) <= 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
