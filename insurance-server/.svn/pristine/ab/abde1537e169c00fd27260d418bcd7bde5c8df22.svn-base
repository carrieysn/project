/**
 * File: InsCacheManager.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年11月30日 下午8:10:51
 */
package com.cifpay.insurance.cache;

import com.cifpay.insurance.model.InsPolicy;
import com.cifpay.insurance.param.msg.ReturnCertInfo;
import com.cifpay.insurance.param.msg.TodayAddedCertInfo;
import com.cifpay.insurance.util.RedisUtil;

/**
 * 缓存管理类
 * 
 * @author 张均锋
 *
 */
public class InsCacheManager {

	/** 保单key前缀 **/
	public final static String PRE_POLICY_KEY = "insPolicy_";
	/** 占用保额Key前缀 **/
	public final static String PRE_USEDINSUREDAMOUNT_KEY = "usedInsuredAmount_";
	/** 今日新增保险证key **/
	public final static String PRE_TODAY_ADDEDCERT_KEY = "todayAddedCert_";
	/** 今日退货保险证key **/
	public final static String PRE_TODAY_RETURNCERT_KEY = "todayReturnCert_";
	/** 昨日新增保险证key **/
	public final static String PRE_YESTERDAY_ADDEDCERT_KEY = "yesterdayAddedCert_";
	/** 昨日退货保险证key **/
	public final static String PRE_YESTERDAY_RETURNCERT_KEY = "yesterdayReturnCert_";
	/** 本月新增保险证key **/
	public final static String PRE_THISMONTH_ADDEDCERT_KEY = "thismonthAddedCert_";
	/** 本月退货保险证key **/
	public final static String PRE_THISMONTH_RETURNCERT_KEY = "thismonthReturnCert_";
	/** 上月新增保险证key **/
	public final static String PRE_LASTMONTH_ADDEDCERT_KEY = "lastmonthAddedCert_";
	/** 上月退货保险证key **/
	public final static String PRE_LASTMONTH_RETURNCERT_KEY = "lastmonthReturnCert_";
	/** 今年新增保险证key **/
	public final static String PRE_THISYEAR_ADDEDCERT_KEY = "thisyearAddedCert_";
	/** 今年退货保险证key **/
	public final static String PRE_THISYEAR_RETURNCERT_KEY = "thisyearReturnCert_";
	/** 去年新增保险证key **/
	public final static String PRE_LASTYEAR_ADDEDCERT_KEY = "lastyearAddedCert_";
	/** 去年退货保险证key **/
	public final static String PRE_LASTYEAR_RETURNCERT_KEY = "lastyearReturnCert_";

	public static void setInsPolicyCache(String key, InsPolicy insPolicy) {
		RedisUtil.setObject(key, insPolicy);
	}

	public static InsPolicy getInsPolicyCache(String key) {
		return RedisUtil.getObject(key, InsPolicy.class);
	}

	public static void delInsPolicyCache(String key) {
		RedisUtil.delObject(key);
	}
	
	public static void setTodayAddedCert(String key, TodayAddedCertInfo taci) {
		RedisUtil.setObject(key, taci);
	}
	
	public static TodayAddedCertInfo getTodayAddedCertInfo(String key) {
		return RedisUtil.getObject(key, TodayAddedCertInfo.class);
	}
	
	public static void delTodayAddedCertInfo(String key) {
		RedisUtil.delObject(key);
	}
	
	public static void setTodayReturnCert(String key, ReturnCertInfo taci) {
		RedisUtil.setObject(key, taci);
	}
	
	public static ReturnCertInfo getTodayReturnCertInfo(String key) {
		return RedisUtil.getObject(key, ReturnCertInfo.class);
	}
	
	public static void delTodayReturnCertInfo(String key) {
		RedisUtil.delObject(key);
	}
}
