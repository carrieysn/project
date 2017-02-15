package com.cifpay.lc.std.sched.task.util;

import java.util.Date;

public class CPayTimeUtil {

	//计算时间差
	public static String getTimeDiff(Date startDate, Date endDate) {
		long l = endDate.getTime() - startDate.getTime();
		long day=l/(24*60*60*1000);
		long hour=(l/(60*60*1000)-day*24);
		long min=((l/(60*1000))-day*24*60-hour*60);
		long s=(l/1000-day*24*60*60-hour*60*60-min*60);
		String strRet = day+"天"+hour+"小时"+min+"分"+s+"秒";
		return strRet;
	}
}
