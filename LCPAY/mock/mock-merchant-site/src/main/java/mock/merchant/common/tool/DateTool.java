package mock.merchant.common.tool;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

/**
 * 日期工具类
 * 
 * @author Administrator
 * 
 */
public class DateTool {
	
	/**
	 * format = yyyyMMdd
	 */
	public static String DATE_NUMBER = "yyyyMMdd";
	
	/**
	 * (default, format = yyyy-MM-dd HH:mm:ss)
	 */
	public static String DATE_TIMES = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * format = yyyy-MM-dd HH:mm
	 */
	public static String DATE_MINUTE = "yyyy-MM-dd HH:mm";
	
	/**
	 * format = yyyy-MM-dd HH:mm:ss:S
	 */
	public static String DATE_TIMES_MILI = "yyyy-MM-dd HH:mm:ss:S";
	
	/**
	 * format = yyyyMMddHHmmss
	 */
	public static String DATE_NUMBER_TIMES = "yyyyMMddHHmmss";
	
	/**
	 * format = yyyyMMddHHmmssS
	 */
	public static String DATE_NUMBER_TIMES_MILI = "yyyyMMddHHmmssS";
	
	/**
	 * format = HHmmssS
	 */
	public static String TIMES = "HHmmssS";
	
	/**
	 * format = yyyy-MM-dd
	 */
	public static String SHORT_DATE = "yyyy-MM-dd";
	
	//public static String TIMESTAMP="yyyy-mm-dd hh:mm:ss.f...";

	public static Date getCurrentDate() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * (default, DATE_TIMES=yyyy-MM-dd HH:mm:ss)
	 */
	public static String format(Date time) {
		return getSimpleDateFormat(DATE_TIMES).format(time);
	}

	/**
	 * (default, DATE_TIMES=yyyy-MM-dd HH:mm:ss)
	 */
	public static String format(Date time, String format) {
		if (format == null) {
			format = DATE_TIMES;
		}
		return getSimpleDateFormat(format).format(time);
	}

	/**
	 * (default, DATE_TIMES=yyyy-MM-dd HH:mm:ss)
	 */
	public static Date parse(String date) {
		Date result = null;
		try {
			result = getSimpleDateFormat(DATE_TIMES).parse(date);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Invalid date format");
		}
		return result;
	}

	/**
	 * (default, DATE_TIMES=yyyy-MM-dd HH:mm:ss)
	 */
	public static Date parse(String date, String format) throws IllegalArgumentException {
		if (format == null) {
			format = DATE_TIMES;
		}
		Date result = null;
		try {
			result = getSimpleDateFormat(format).parse(date);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Invalid date format");
		}
		return result;
	}

	/**
	 * 向前滚动quantity个时间单元(dateUnitType类型)
	 * 
	 * @param date
	 *            原时间
	 * @param dateUnitType
	 *            时间单位类型, 月份：Calendar.MONTH，天数：Calendar.DAY_OF_MONTH
	 * @param quantity
	 *            滚动的数量
	 * @return
	 */
	public static Date rollDateByUnit(Date date, int dateUnitType, int quantity) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(dateUnitType, quantity);
		return cal.getTime();
	}

	/**
	 * 向前或向后滚动quantity个时间单元(dateUnitType类型)
	 * 
	 * @param date
	 *            原时间
	 * @param dateUnitType
	 *            时间单位类型, 月份：Calendar.MONTH，天数：Calendar.DAY_OF_MONTH
	 * @param quantity
	 *            滚动的数量
	 * @param up
	 *            true向后滚动, false向前滚动
	 * @return
	 */
	public static Date rollDateByUnit(Date date, int dateUnitType, int quantity, boolean up) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(dateUnitType, up ? quantity : -quantity);
		return cal.getTime();
	}

	/**
	 * 获取当前日期(default, DATE_TIMES=yyyy-MM-dd HH:mm:ss)
	 * 
	 * @return
	 */
	public static String getCurDate() {
		return generByDateFormat(DATE_TIMES);
	}

	/**
	 * 获取当前时间(default, DATE_TIMES=yyyy-MM-dd HH:mm:ss)
	 * 
	 * @return
	 */
	public static String getCurTime() {
		return generByDateFormat(DATE_TIMES);
	}
	/**
	 * 获取当前时间(default, DATE_TIMES=yyyy-MM-dd HH:mm:ss)
	 * @return
	 */
	public static String getCurNumberTime() {
		return generByDateFormat(DATE_TIMES);
	}
	
	/**
	 * 先去掉非数字，再转成java.sql.Timestamp
	 */
    public static Timestamp getTimestamp(String dbTime){
    	String dateNumberTime=getDigitsOnly(dbTime);
		int timeLength = dateNumberTime.length();
		
		if(timeLength<11){
			dateNumberTime = dateNumberTime.substring(0, 4)+"-"+dateNumberTime.substring(4, 6)+"-"+dateNumberTime.substring(6, 8)+" "+dateNumberTime.substring(8)+"00:00:00.000000";
		}else{
			if(timeLength<15){
				dateNumberTime= dateNumberTime.substring(0, 4)+"-"+dateNumberTime.substring(4, 6)+"-"+dateNumberTime.substring(6, 8)+" "+dateNumberTime.substring(8,10)+":"+dateNumberTime.substring(10, 12)+":"+dateNumberTime.substring(12, 14)+".000000";
			}else{
				dateNumberTime= dateNumberTime.substring(0, 4)+"-"+dateNumberTime.substring(4, 6)+"-"+dateNumberTime.substring(6, 8)+" "+dateNumberTime.substring(8,10)+":"+dateNumberTime.substring(10, 12)+":"+dateNumberTime.substring(12, 14)+"."+dateNumberTime.substring(14);	
			}
		}
    	return Timestamp.valueOf(dateNumberTime);
    }

	/**
	 * 获得当前时间到分钟(default, DATE_TIMES=yyyy-MM-dd HH:mm:ss)
	 */
	public static String getCurMinute() {
		return generByDateFormat(DATE_TIMES);
	}

	public static String generByDateFormat(String dateFormat) {
		Calendar calendar = Calendar.getInstance();
		return getSimpleDateFormat(dateFormat).format(calendar.getTime());
	}

	/**
	 * 转换成精确到分钟的字符串(default, DATE_TIMES=yyyy-MM-dd HH:mm:ss)
	 */
	public static String getDateMinute(Date date) {
		return getSimpleDateFormat(DATE_TIMES).format(date);
	}

	/**
	 * 获取当前时间(default, DATE_TIMES=yyyy-MM-dd HH:mm:ss)
	 * @return
	 */
	public static String getCurTimeMilis() {
		Calendar calendar = Calendar.getInstance();
		return getSimpleDateFormat(DATE_TIMES).format(calendar.getTime());
	}

	/**
	 * 验证时间格式(default, DATE_TIMES=yyyy-MM-dd HH:mm:ss)
	 * @Title: validTime
	 * @author fubin
	 * @param
	 * @return void
	 * @throws
	 * @date: 2013年11月28日
	 * @time: 下午3:04:16
	 */
	public static boolean validTime(String time) {
		try {
			getSimpleDateFormat(DATE_TIMES).parse(time);
		} catch (ParseException e) {
			return false;
		}

		return true;
	}

	/**
	 * 格式化日期
	 * 
	 * @param format
	 * @return
	 */
	public static SimpleDateFormat getSimpleDateFormat(String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat;
	}

	/**
	 * 解析分钟格式的时间(default, DATE_TIMES=yyyy-MM-dd HH:mm:ss)
	 * 
	 * @throws ParseException
	 */
	public static Date parseDateMinute(String str) throws ParseException {
		return getSimpleDateFormat(DATE_TIMES).parse(str);
	}

	/**
	 * 解析分钟格式的时间
	 * 
	 * @throws ParseException
	 */
	public static Date parseDate(String str, String format)
			throws ParseException {
		return getSimpleDateFormat(format).parse(str);
	}

	/**
	 * 增减分钟
	 */
	public static Date addMinutes(Date date, int m) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, m);
		return calendar.getTime();
	}

	/**
	 * 增减小时
	 */
	public static Date addHours(Date date, int h) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, h);
		return calendar.getTime();
	}

	/**
	 * 增减秒
	 */
	public static Date addSecond(Date date, int s) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, s);
		return calendar.getTime();
	}

	/**
	 * 增减天
	 */
	public static Date addDays(Date date, int d) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, d);
		return calendar.getTime();
	}
	
	public static Date getTodayBeging(){	
		Calendar currentDate = new GregorianCalendar(); 	  
		currentDate.set(Calendar.HOUR_OF_DAY, 0);  
		currentDate.set(Calendar.MINUTE, 0);  
		currentDate.set(Calendar.SECOND, 0);  
		return currentDate.getTime();
	}
	
	public static Date getTodayEnd(){	
		Calendar currentDate = new GregorianCalendar();   
		currentDate.set(Calendar.HOUR_OF_DAY, 23);  
		currentDate.set(Calendar.MINUTE, 59);  
		currentDate.set(Calendar.SECOND, 59);  
		return currentDate.getTime();

	}

	/**
	 * (default, DATE_TIMES=yyyy-MM-dd HH:mm:ss)
	 */
	public static String formatTime(Date time) {
		return getSimpleDateFormat(DATE_TIMES).format(time);
	}

	/**
	 * (default, DATE_TIMES=yyyy-MM-dd HH:mm:ss)
	 */
	public static String getTradeTime(Date time) {
		return getSimpleDateFormat(DATE_TIMES).format(time);
	}

	/**
	 * 先去掉非数字，加上hour小时后，再转成(default, DATE_TIMES=yyyy-MM-dd HH:mm:ss)
	 */
	public static String addHours(String dateNumberTime, int hour) {
		String time = tranformNumberTimeToDateTime(dateNumberTime);
		try {
			Date date =DateTool.addHours(DateTool.parseDate(time, DATE_TIMES), hour);
			return DateTool.formatTime(addHours(date, hour));
		} catch (ParseException e) {
			throw new IllegalArgumentException("Invalid date format");
		}
	}

	/**
	 * (default, DATE_TIMES=yyyy-MM-dd HH:mm:ss)
	 */
	public static Date addDays(String validTimeStr, int d) {
		try {
			Date date = getSimpleDateFormat(DATE_TIMES).parse(validTimeStr);
			return addDays(date, d);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Invalid date format");
		}
	}

	public static Date addDays(int d) {
		return addDays(new Date(), d);
	}

	public static String addYears(int year){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(calendar.getTime());
		calendar.add(Calendar.YEAR, year);
		return formatTime(calendar.getTime());
	}

	public static String getCurDate(String format) {
		return getSimpleDateFormat(format).format(new Date());
	}

	/** 返回指定日期格式的字串 */
	public static String getFormatDate(Date date, String format) {

		SimpleDateFormat sdf = new SimpleDateFormat(format);

		return sdf.format(date);
	}

	/** 返回指定日期的Date */
	public static Date getDateFromString(String date, String format)
			throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat(format);

		return sdf.parse(date);
	}

	/** 转换日期为指定格式 */
	public static String transStringFormat(String sourceDate, String sourceFmt,
			String format) throws ParseException {

		Date date = getDateFromString(sourceDate, sourceFmt);

		return getFormatDate(date, format);
	}

	/**
	 * 两个参数格式相同，均为(default, DATE_TIMES=yyyy-MM-dd HH:mm:ss)
	 */
	public static int compareStringDate(String compareTime, String deadlineTime) throws ParseException{
		Date compare = parseDate(compareTime, DATE_TIMES);
		Date deadline = parseDate(deadlineTime, DATE_TIMES);
		return compareDate(compare, deadline);
	}
	
	public static int compareDate(Date compareDate, Date deadLine){
		if (compareDate.getTime() > deadLine.getTime()) {
			return 1;
		} else if (compareDate.getTime() < deadLine.getTime()) {
			return -1;
		} else {
			return 0;
		}
	}
	
	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static String getCurMonthFirstDay(String dateFormat) {
        Calendar calendar = Calendar.getInstance();    
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH,1);
       // calendar.add(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.HOUR, -1);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
		return getSimpleDateFormat(dateFormat).format(calendar.getTime());
	}
	
	public static String getCurMonthEndDay(String dateFormat) {
		Calendar calendar = Calendar.getInstance();    
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));  
		return getSimpleDateFormat(dateFormat).format(calendar.getTime());
	}
	
	/**
	 * 获得当前月份的第一天
	 * @return
	 * @throws ParseException 
	 */
	public static String getCurtMonthFirstDay(String dateFormat) {
		String firstDay = getCurMinute().substring(0, 8) + "01";
		try {
			Date date = parseDate(firstDay, SHORT_DATE);
			return getFormatDate(date, dateFormat);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Invalid date format");
		}
		
	}
	
	/**
	 * 先去掉非数字，再转换成(default, DATE_TIMES=yyyy-MM-dd HH:mm:ss)
	 * @param dateNumberTime
	 * @return
	 */
	public static String tranformNumberTimeToDateTime(String dateNumberTime){
		dateNumberTime=getDigitsOnly(dateNumberTime);
		int timeLength = dateNumberTime.length();
		if(timeLength<11){
			return dateNumberTime.substring(0, 4)+"-"+dateNumberTime.substring(4, 6)+"-"+dateNumberTime.substring(6, 8)+" "+dateNumberTime.substring(8)+"00:00:00";
		}else{
			return dateNumberTime.substring(0, 4)+"-"+dateNumberTime.substring(4, 6)+"-"+dateNumberTime.substring(6, 8)+" "+dateNumberTime.substring(8,10)+":"+dateNumberTime.substring(10, 12)+":"+dateNumberTime.substring(12, 14);
		}
	}

	public static String dateToString(Date date, String dateFormat) {
		return getSimpleDateFormat(dateFormat).format(date);
	}

	public static Date stringToDate(String str,String dateFormat ) {
		DateFormat format = new SimpleDateFormat(dateFormat);
		Date date = null;
		try {
			date = format.parse(str); 
		} catch (ParseException e) {
			throw new IllegalArgumentException("Invalid date format");
		}
		return date;
	}
	
	public static String getDigitsOnly (String s) {
	    StringBuffer digitsOnly = new StringBuffer ();
	    char c;
	    for (int i = 0; i < s.length (); i++) {
	      c = s.charAt (i);
	      if (Character.isDigit (c)) {
	        digitsOnly.append (c);
	      }
	    }
	    return digitsOnly.toString ();
	  } 
	
	/*public static Timestamp generateDBTimestamp(Date date){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

		String time = df.format(new Date());

		Timestamp ts = Timestamp.valueOf(time);
		return ts;
	}*/
	
	/**
	 * 将时间戳格式的字符串格式化, 默认转成(default, DATE_TIMES=yyyy-MM-dd HH:mm:ss)
	 * @param dateStr 时间戳字符串，只允许为14位全数字
	 * @param format 格式化字符串，返回字符串的format
	 * @return
	 * @throws CPRuntimeException
	 */
	public static String formatToTimestamp(String dateStr, String format) {
		String temp = format;
		if(format == null || "".equals(format)){
			temp = "yyyy-MM-dd HH:mm:ss";
		}
		String result = null;
		Pattern pattern = Pattern.compile("[0-9]*"); 
		if(dateStr != null && pattern.matcher(dateStr).matches() 
				&& dateStr.length() == 14){
			boolean flag = true;
			String year = dateStr.substring(0, 4);
			int yearVal = Integer.parseInt(year);
			//验证月是否合法
			String month = dateStr.substring(4, 6);
			int monthVal = Integer.parseInt(month);
			if(monthVal < 0 || monthVal > 12){
				flag = false;
			}
			
			//验证日是否合法
			String day = dateStr.substring(6, 8);
			int dayVal = Integer.parseInt(day);
			int maxDayVal;
			if (monthVal == 2) {
				// 闰年是指可以被4整除时且不可以被100整除，或可以被400整除的年份
				boolean isLeapYear = (yearVal % 4 == 0 && yearVal % 100 != 0) || yearVal % 400 == 0;
				maxDayVal = isLeapYear ? 29 : 28;
			} else if (monthVal == 4 || monthVal == 6 || monthVal == 9 || monthVal == 11) {
				// 小月天数最大为30
				maxDayVal = 30;
			} else {
				// 大月天数最大为31
				maxDayVal = 31;
			}
			if (dayVal < 0 || dayVal > maxDayVal) {
				flag = false;
			}
			
			//验证小时是否合法
			String hour = dateStr.substring(8, 10);
			int hourVal = Integer.parseInt(hour);
			if(hourVal < 0 || hourVal > 23){
				flag = false;
			}
			
			//验证分钟数是否合法
			String minutes = dateStr.substring(10, 12);
			int minutesVal = Integer.parseInt(minutes);
			if(minutesVal < 0 || minutesVal > 59){
				flag = false;
			}
			//验证秒数是否合法
			String seconds = dateStr.substring(12, 14);
			int secondsVal = Integer.parseInt(seconds);
			if(secondsVal < 0 || secondsVal > 59){
				flag = false;
			}
			if (flag) {
				result = temp.replaceAll("yyyy", year).replaceAll("MM", month).replaceAll("dd", day)
				.replaceAll("HH", hour).replaceAll("mm", minutes).replaceAll("ss", seconds);
			}
		}
		if (result != null) {
			return result;
		} else {
			return null;
		}
	}

	/**
	 * 获取传入日期下一日的开始时间，精确到秒，参数格式(SHORT_DATE=yyyy-MM-dd)加1天后转成(default, DATE_TIMES=yyyy-MM-dd HH:mm:ss)
	 * @param dateStr (yyyy-MM-dd)
	 * @return (yyyy-MM-dd HH:mm:ss)
	 * @throws ParseException
	 */
	public static String getNextDateStart(String dateStr) throws ParseException {
		Date date = getDateFromString(dateStr, SHORT_DATE);
		Date nextDate = addDays(date, 1);
		return getFormatDate(nextDate,SHORT_DATE);
	}

	/**
	 * 获取小时分钟秒字符串(TIMES=HHmmssS)
	 * @return
	 */
	public static String getHHmmss(){
		return getFormatDate(new Date(),DATE_NUMBER_TIMES_MILI);
	}
	public static String getYYYYMMddHHmmss(){
		return getFormatDate(new Date(),DATE_NUMBER_TIMES);
	}

}
