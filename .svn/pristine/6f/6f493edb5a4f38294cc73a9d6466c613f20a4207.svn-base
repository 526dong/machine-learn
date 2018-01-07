package com.ccx.models.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {

	public static final String DATE_FORMAT_STR = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT_PAR = "yyyy-MM-dd";

	public static final SimpleDateFormat formatAll = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat formatDate = new SimpleDateFormat(
			"yyyy-MM-dd");
	public static final SimpleDateFormat formatDate_1 = new SimpleDateFormat(
			"yyyyMMdd");
	public static final SimpleDateFormat formatYearMonth = new SimpleDateFormat("yyyy-MM");

	/**
	 * 将短时间格式字符串yyyy-MM-dd转换为时间
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDate(String strDate) {
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatDate.parse(strDate, pos);
		return strtodate;
	}
	/**
	 * 显示年月日 格式yyyy-MM-dd
	 * 
	 * @param dateStr时间字符串
	 *            2011-08-26 修改人：韩建新 修改内容：增加dateStr为空值处理
	 * @return
	 */
	public static String getDateFromString(Date date) {
		return formatDate.format(date);
	}
	// 得到一个时间延后或前移几天的时间
	public static String getCountedDay(String strDate, Integer delay) {
		try {
			String mdate = "";
			Date d = strToDate(strDate);
			long myTime = (d.getTime() / 1000) + delay * 24 * 60 * 60;
			d.setTime(myTime * 1000);
			mdate = formatDate.format(d);
			return mdate;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 判断两个日期之间差了多少天，不足一天，则按一天计算，即20.01天也算21天
	 */
	public static int dateDiff(Date date1, Date date2) {
		if (date1 == null || date2 == null)
			return 0;
		long baseNum = 3600 * 1000 * 24;
		long absNum = Math.abs(date1.getTime() - date2.getTime());
		long mod = absNum % baseNum;
		int num = (int) (absNum / baseNum);
		if (mod > 0)
			num++;
		return num;
	}

	/**
	 * 判断两个日期之间差了多少天
	 */
	public static int dateDiffDown(Date end, Date begin) {
		if (end == null || begin == null) {
			return 0;
		}

		long baseNum = 3600 * 1000 * 24;
		long absNum = Math.abs(end.getTime() - begin.getTime());
		int num = (int) (absNum / baseNum);

		return num;
	}
	
	/**
	 * 判断两个日期之间差了多少天，不足一天，则按一天计算，即20.01天也算21天
	 */
	public static int dateDiff1(Date date1, Date date2) {
		if (date1 == null || date2 == null)
			return 0;
		long baseNum = 3600 * 1000 * 24;
		long absNum = date1.getTime() - date2.getTime();
		long mod = absNum % baseNum;
		int num = (int) (absNum / baseNum);
		if (mod > 0)
			num++;
		return num;
	}

	/**
	 * 设置两个日期相差几个月
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 * @throws ParseException
	 */
	public static int getMonthDeffDate(String date1, String date2) throws ParseException {
		Calendar bef = Calendar.getInstance();
		Calendar aft = Calendar.getInstance();
		bef.setTime(formatAll.parse(date1));
		aft.setTime(formatAll.parse(date2));
		int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
		int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
		int day = (aft.get(Calendar.DAY_OF_MONTH) - bef
				.get(Calendar.DAY_OF_MONTH));
		day = day > 0 ? 1 : 0;
		return month + result + day;

	}

	/**
	 * 得到一个时间延后或前移几月的时间
	 * 
	 * @param strDate
	 * @param delay
	 * @return
	 */
	public static String getCountedMonth(String strDate, Integer delay) {
		try {
			Date dd = strToDate(strDate);
			Calendar currentDate = Calendar.getInstance();
			currentDate.setTime(dd);
			currentDate.add(Calendar.MONDAY, delay);
			return formatDate.format(currentDate.getTime());
		} catch (Exception e) {
			return "";
		}
	}
	
	
	/**
	 * 得到一个时间延后或前移几月的时间
	 * 
	 * @param strDate
	 * @param delay
	 * @return
	 */
	public static String getCountedMonthAll(String strDate, Integer delay) {
		try {
			Date dd = strToDate(strDate);
			Calendar currentDate = Calendar.getInstance();
			currentDate.setTime(dd);
			currentDate.add(Calendar.MONDAY, delay);
			return formatAll.format(currentDate.getTime());
		} catch (Exception e) {
			return "";
		}
	}

	

	/**
	 * 日期转毫秒
	 * 
	 * @param expireDate
	 * @return
	 */
	public static Long getSecondsFromDateTime(String expireDate) {
		if (expireDate == null || expireDate.trim().equals(""))
			return 0l;
		Date date = null;
		try {
			date = formatAll.parse(expireDate);
			return date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return 0l;
		}
	}

	/**
	 * 日期转毫秒
	 * 
	 * @param expireDate
	 * @return
	 */
	public static Long getSecondsFromDate(String expireDate) {
		if (expireDate == null || expireDate.trim().equals(""))
			return 0l;
		Date date = null;
		try {
			date = formatDate.parse(expireDate);
			return date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return 0l;
		}
	}
	
	/**
	 * 根据天数返回失效时间的总计秒数，不传day默认一天
	 * 比如当前时间为23:59:00 传入参数1天，返回60秒
	 * @param day
	 * @return
	 */
	public static Integer getSmsFailSecond(Integer day){
		SimpleDateFormat sf = new SimpleDateFormat(DATE_FORMAT_PAR);
		String curDateStr = sf.format(new Date());
		Integer failSecond = 24 * 60 * 60;
		if(day == null){
			day = 1;
		}
		try {
			Date todayDate = sf.parse(curDateStr);
			long todayMilliSecond = todayDate.getTime();
			long curMilliSecond = System.currentTimeMillis();
			long afterMilliSecond = todayMilliSecond + (day * 24 * 60 * 60 * 1000);
//			System.out.println("今天开始的毫秒数：" + todayMilliSecond);
//			System.out.println( day + "天后开始的毫秒数：" + afterMilliSecond);
//			System.out.println("今天当前的毫秒数：" + curMilliSecond);
//			System.out.println("相差毫秒数：" + (afterMilliSecond - curMilliSecond)/1000);
//			System.out.println("相差毫秒数：" + (afterMilliSecond - todayMilliSecond));
			failSecond = (int) (afterMilliSecond - curMilliSecond)/1000;
			return (Integer)failSecond;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return failSecond;
	}
	
	
	public static String formatDate(String date) throws ParseException {
		return formatAll.format(formatAll.parse(date));
	}
	public static String formatDate1(String date) throws ParseException {
		return formatDate.format(formatDate.parse(date));
	}
	public static String formatDate(Date date) throws ParseException {
		return formatAll.format(date);
	}
	public static String formatDateStr(Date date) throws ParseException {
		return formatDate.format(date);
	}
	public static Date parseStr2Date(String date) throws ParseException {
		return formatAll.parse(date);
	}
	
	public static Date parseStr2Date1(String date) throws ParseException {
		return formatDate.parse(date);
	}

	public static Date parseStr2DYearMonth(String date) throws ParseException {
		return formatYearMonth.parse(date);
	}
	
	public static String cidGetDate(String cid) throws ParseException {
		String bd = cid.substring(6, 14);
		return formatDate.format((formatDate_1.parse(bd)));
	}


	
	/**
	 * 获取两个日期之间的日期
	 * @param start 开始日期
	 * @param end 结束日期
	 * @return 日期集合
	 */
	public static List<String> getBetweenDates(Date start, Date end) {
	    List<String> result = new ArrayList<String>();
	    Calendar tempStart = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    tempStart.setTime(start);
	    tempStart.add(Calendar.MARCH, 0);
	    tempStart.set(Calendar.DAY_OF_MONTH, tempStart.getActualMaximum(Calendar.DAY_OF_MONTH));  
	    
	    Calendar tempEnd = Calendar.getInstance();
	    tempEnd.setTime(end);
	    tempEnd.set(Calendar.DAY_OF_MONTH, tempEnd.getActualMaximum(Calendar.DAY_OF_MONTH));
	    while (tempStart.before(tempEnd) || tempStart.compareTo(tempEnd)==0) {
	        result.add(sdf.format(tempStart.getTime()));
	        tempStart.add(Calendar.MARCH, 1);
	        tempStart.set(Calendar.DAY_OF_MONTH, tempStart.getActualMaximum(Calendar.DAY_OF_MONTH));  
	    }
	    return result;
	}
	
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		System.err.println(getBetweenDates(sdf.parse("2016-01"), sdf.parse("2018-11")));
		String date = "2016/01/03";
		date = date.replaceAll("/", "-");
		date = date.substring(0,date.lastIndexOf("-"));
		System.err.println(date);
	}

}
