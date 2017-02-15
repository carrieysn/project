package com.cifpay.lc.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static String defaultSimpleFormater = "yyyy-MM-dd hh:mm:ss";

	/**
	 * 默认简单日期字符串
	 * 
	 * @return
	 */
	public static String getDefaultSimpleFormater() {
		return defaultSimpleFormater;
	}

	/**
	 * 设置默认简单日期格式字符串
	 * 
	 * @param defaultFormatString
	 */
	public static void setDefaultSimpleFormater(String defaultFormatString) {
		DateUtil.defaultSimpleFormater = defaultFormatString;
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 * @param formatString
	 * @return
	 */
	public static String format(Date date, String formatString) {
		SimpleDateFormat df = new SimpleDateFormat(formatString);
		return df.format(date);
	}

	public static String getDateYYYYMMDDhhmmss() {
		return format(new Date(), "yyyyMMddHHmmss");
	}

	/**
	 * 格式化日期(使用默认格式)
	 * 
	 * @param date
	 * @return
	 */
	public static String format(Date date) {
		return format(date, defaultSimpleFormater);
	}

	/**
	 * 转换成日期
	 * 
	 * @param dateString
	 * @param formatString
	 * @return
	 */
	public static Date parse(String dateString, String formatString) {
		SimpleDateFormat df = new SimpleDateFormat(formatString);
		try {
			return df.parse(dateString);
		} catch (Exception e) {
			throw new IllegalArgumentException("invalid date", e);
		}
	}

	/**
	 * 转换成日期(使用默认格式)
	 * 
	 * @param dateString
	 * @return
	 */
	public static Date parse(String dateString) {
		return parse(dateString, defaultSimpleFormater);
	}

	/**
	 * 昨天
	 * 
	 * @return
	 */
	public static Date yesterday() {
		return addDay(-1);
	}

	/**
	 * 明天
	 * 
	 * @return
	 */
	public static Date tomorrow() {
		return addDay(1);
	}

	/**
	 * 现在
	 * 
	 * @return
	 */
	public static Date now() {
		return new Date(System.currentTimeMillis());
	}

	/**
	 * 按日加
	 * 
	 * @param value
	 * @return
	 */
	public static Date addDay(int value) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_YEAR, value);
		return now.getTime();
	}

	/**
	 * 按日加,指定日期
	 * 
	 * @param date
	 * @param value
	 * @return
	 */
	public static Date addDay(Date date, int value) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.add(Calendar.DAY_OF_YEAR, value);
		return now.getTime();
	}

	/**
	 * 按月加
	 * 
	 * @param value
	 * @return
	 */
	public static Date addMonth(int value) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MONTH, value);
		return now.getTime();
	}

	/**
	 * 按月加,指定日期
	 * 
	 * @param date
	 * @param value
	 * @return
	 */
	public static Date addMonth(Date date, int value) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.add(Calendar.MONTH, value);
		return now.getTime();
	}

	/**
	 * 按年加
	 * 
	 * @param value
	 * @return
	 */
	public static Date addYear(int value) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.YEAR, value);
		return now.getTime();
	}

	/**
	 * 按年加,指定日期
	 * 
	 * @param date
	 * @param value
	 * @return
	 */
	public static Date addYear(Date date, int value) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.add(Calendar.YEAR, value);
		return now.getTime();
	}

	/**
	 * 按小时加
	 * 
	 * @param value
	 * @return
	 */
	public static Date addHour(int value) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.HOUR_OF_DAY, value);
		return now.getTime();
	}

	/**
	 * 按小时加,指定日期
	 * 
	 * @param date
	 * @param value
	 * @return
	 */
	public static Date addHour(Date date, int value) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.add(Calendar.HOUR_OF_DAY, value);
		return now.getTime();
	}

	/**
	 * 按分钟加
	 * 
	 * @param value
	 * @return
	 */
	public static Date addMinute(int value) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, value);
		return now.getTime();
	}

	/**
	 * 按分钟加,指定日期
	 * 
	 * @param date
	 * @param value
	 * @return
	 */
	public static Date addMinute(Date date, int value) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.add(Calendar.MINUTE, value);
		return now.getTime();
	}

	/**
	 * 按秒加
	 * 
	 * @param value
	 * @return
	 */
	public static Date addSecond(int value) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.SECOND, value);
		return now.getTime();
	}

	/**
	 * 按秒加,指定日期
	 * 
	 * @param date
	 * @param value
	 * @return
	 */
	public static Date addSecond(Date date, int value) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.add(Calendar.SECOND, value);
		return now.getTime();
	}

	/**
	 * 年份
	 * 
	 * @return
	 */
	public static int year() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.YEAR);
	}

	/**
	 * 月份
	 * 
	 * @return
	 */
	public static int month() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.MONTH);
	}

	/**
	 * 日(号)
	 * 
	 * @return
	 */
	public static int day() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 小时(点)
	 * 
	 * @return
	 */
	public static int hour() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.HOUR);
	}

	/**
	 * 分钟
	 * 
	 * @return
	 */
	public static int minute() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.MINUTE);
	}

	/**
	 * 秒
	 * 
	 * @return
	 */
	public static int second() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.SECOND);
	}

	/**
	 * 星期几(礼拜几)
	 * 
	 * @return
	 */
	public static int weekday() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.DAY_OF_WEEK) - 1;
	}

	/**
	 * 取最小值
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static Date min(Date x, Date y) {
		if (x.before(y)) {
			return x;
		}
		return y;
	}

	/**
	 * 取最大值
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static Date max(Date x, Date y) {
		if (x.before(y)) {
			return y;
		}
		return x;
	}

	/**
	 * 是上午吗?
	 * 
	 * @return
	 */
	public static boolean isAm() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.AM_PM) == 0;
	}

	/**
	 * 是下午吗?
	 * 
	 * @return
	 */
	public static boolean isPm() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.AM_PM) == 1;
	}

	/**
	 * 去除时间对象中的毫秒、纳秒部分
	 * 
	 * @param date
	 * @return
	 */
	public static Date clearMillisecondAndNanosPart(Date date) {
		if (null == date) {
			return null;
		}
		if (date instanceof Timestamp) {
			Timestamp t = (Timestamp) date;
			t.setNanos(0);
			date = t;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
}
