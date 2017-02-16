package com.cifpay.insurance.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class DateUtil {
   /** 
     * 获取几天后的时间 
     */  
	public static Date getDateAfter(Date d, int day) {  
        Calendar now = Calendar.getInstance();  
        now.setTime(d);  
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);  
        return now.getTime();  
    }
	
	/**
	 * 按指定格式,把日期字符串转换成日期类型值并返回
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static Date parseDate(String dateStr, String pattern) {
		try {
		    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.parse(dateStr);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 按指定格式把日期类型的值转换成字符串并返回.
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formatDate(Date date, String pattern) {
		try {
		    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
		} catch (Exception e) {
			return "";
		}
	}
	
	public static Date add(Date date, int field, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(field, amount);
		return cal.getTime();
	}
	
	/**
	 * 增加指定小时，返回增加小时后的日期。
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addHour(Date date, int amount) {
		return add(date, Calendar.HOUR_OF_DAY, amount);
	}
	
	/**
	 * 增加指定日期天数,并返回增加天数后的日期
	 * 
	 * @param date
	 * @param amount 天数,可以是负数
	 * @return
	 */
	public static Date addDay(Date date, int amount) {
		return add(date, Calendar.DAY_OF_MONTH, amount);
	}
	
	/**
	 * 增加指定月数。
	 * 
	 * @param date
	 * @param amount 月数
	 * @return
	 */
	public static Date addMonth(Date date, int amount) {
		return add(date, Calendar.MONTH, amount);
	}
	
	/**
	 * 增加指定年数
	 * 
	 * @param date
	 * @param amount 年数
	 * @return
	 */
	public static Date addYear(Date date, int amount) {
		return add(date, Calendar.YEAR, amount);
	}
	
	/**
	 * 获取纯日期。不包含时间，如：2015-12-05
	 * 
	 * @return
	 */
	public static Date getDateYMD() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		
		return c.getTime();
	}
	
	/**
	 * 获取本月起始日期，如2015-12-01
	 * 
	 * @return
	 */
	public static Date getThisMonthStartDate() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		
		return c.getTime();
	}
	
	/**
	 * 获取今年起始日期，如2015-01-01
	 * 
	 * @return
	 */
	public static Date getThisYearStartDate() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		
		return c.getTime();
	}
}
