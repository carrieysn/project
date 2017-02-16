/**
 * File: DataCacheManager.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月31日 上午10:07:21
 */
package com.cifpay.insurance.push;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.cifpay.insurance.bean.TodayAddedCertBean;
import com.cifpay.insurance.bean.VendorReturnCertBean;
import com.cifpay.insurance.param.msg.RefundCertInfo;
import com.cifpay.insurance.param.msg.ReturnCertInfo;
import com.cifpay.insurance.param.msg.TodayAddedCertInfo;
import com.cifpay.insurance.push.event.InsuranceCertStateChangeEvent;
import com.cifpay.insurance.service.InsInsuranceCertService;
import com.cifpay.insurance.util.RedisUtil;
import com.cifpay.insurance.util.SpringContextUtil;

/**
 * 数据缓存管理
 * 
 * @author 张均锋
 *
 */
public class DataCacheManager {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	public final static String K_TODAY_ADDED_CERT = "today_added_cert";
	public final static String K_RETURN_CERT = "return_cert";
	/**今天保险证 **/
	private static String[] todayAddedCertFields = new String[] {"todayAddedCertCount", "todayAddedAmount", "todayAllCertCount", "todayAllAmount" };
	/** 退货的（包含所有退款） **/
	private static String[] returnCertFields = new String[] {"todayReturnCertCount", "todayAmount", "allToRefundCertCount", "allToRefundAmount" };
	/**所有退款的 **/
	private static String[] refundCertFields = new String[] {"allToRefundCertCount", "allToRefundAmount" };
    /** 一天秒数 **/
	private final static int ONE_DAY_SECONDS = 24*60*60;
	
	private static InsInsuranceCertService insInsuranceCertService = (InsInsuranceCertService)SpringContextUtil.getBean("insInsuranceCertService");
	
	public static String getKey1(String vendorId, String mainKey) {
		return new StringBuilder().append(mainKey).append(":").append(vendorId).append(":").append(sdf.format(new Date())).toString();
	}
	
	/**
	 * 获取商户今日新增key
	 * 
	 * @param vendorId
	 * @return
	 */
	public static String getTodayAddedCertKey(String vendorId) {
		return new StringBuilder().append(K_TODAY_ADDED_CERT).append(":").append(vendorId).append(":").append(sdf.format(new Date())).toString();
	}
	
	/**
	 * 获取商户退货key
	 * 
	 * @param vendorId
	 * @return
	 */
	public static String getReturnCertKey(String vendorId) {
		return new StringBuilder().append(K_RETURN_CERT).append(":").append(vendorId).append(":").append(sdf.format(new Date())).toString();
	}
	
	/**
	 * 加载今日商户相关保险证数据
	 * 
	 * @param vendorId
	 */
	public static void loadTodayVendorDataCache(String vendorId) {
		//今日新增保险证
		reloadVendorTodayCert(vendorId);
		//TODO 并发问题
		//今日退货
		reloadVendorReturnCert(vendorId);
	}
	
	private static TodayAddedCertInfo reloadVendorTodayCert(String vendorId) {
		if (!DataCacheManager.existsVendorTodayCertStatic(vendorId)) {//不存在从数据库获取
			TodayAddedCertInfo tac = new TodayAddedCertInfo();
			TodayAddedCertBean bean = insInsuranceCertService.getTodayAddedCert(vendorId);
			tac.setTodayAddedCertCount(bean.getTodayAddedCertCount());
			tac.setTodayAddedAmount(bean.getAmount());
			TodayAddedCertBean beanAll = insInsuranceCertService.getTodayAllCert(vendorId);
			tac.setTodayAllCertCount(beanAll.getTodayAddedCertCount());
			tac.setTodayAllAmount(beanAll.getAmount());
			retsetVendorTodayCertStatic(vendorId, tac, true);
			return tac;
		}
		return null;
	}
	
	private static ReturnCertInfo reloadVendorReturnCert(String vendorId) {
		if (!DataCacheManager.existsVendorReturnCertStatic(vendorId)) {//不存在从数据库获取
			VendorReturnCertBean todayCert = insInsuranceCertService.getVendorTodayReturnCert(vendorId);
			ReturnCertInfo rc = new ReturnCertInfo();
			rc.setTodayReturnCertCount(todayCert.getCertCount());
			rc.setTodayAmount(todayCert.getAmount());
			VendorReturnCertBean allTodayCert = insInsuranceCertService.getVendorAllToRefundCert(vendorId);
			rc.setAllToRefundCertCount(allTodayCert.getCertCount());
			rc.setAllToRefundAmount(allTodayCert.getAmount());
			retsetVendorReturnCertStatic(vendorId, rc, true);
			return rc;
		}
		return null;
	}
	
	/**
	 * 增加今日保险证数数量及金额值。
	 * 
	 * @param event
	 * @param tac
	 */
	public static void getsetVendorTodayCertStatic(InsuranceCertStateChangeEvent event, TodayAddedCertInfo tac) {
		String key = getTodayAddedCertKey(event.getVendorId());//今日商户键
		long[] values = new long[4];
		values[0] = 1;
		values[1] = event.getTotalPrice();
		values[2] = 1;
		values[3] = event.getTotalPrice();
		Long[] rets = RedisUtil.hincrBatch(key, todayAddedCertFields, values, 0, false);
		if (rets == null) {
			reloadVendorTodayCert(event.getVendorId());//之前的？事务未提交？
			rets = RedisUtil.hincrBatch(key, todayAddedCertFields, values, 0, false);//本次的
			/*tac.setTodayAddedCertCount(taci.getTodayAddedCertCount());
			tac.setTodayAddedAmount(taci.getTodayAddedAmount());
			tac.setTodayAllCertCount(taci.getTodayAllCertCount());
			tac.setTodayAllAmount(taci.getTodayAllAmount());*/
		}
		tac.setTodayAddedCertCount(rets[0]);
		tac.setTodayAddedAmount(rets[1]);
		tac.setTodayAllCertCount(rets[2]);
		tac.setTodayAllAmount(rets[3]);
	}
	
	/**
	 * 是否存在商户今日保险证缓存数据.
	 * 
	 * @param vendorId
	 * @return
	 */
	public static boolean existsVendorTodayCertStatic(String vendorId) {
		String key = getTodayAddedCertKey(vendorId);//今日商户键
		return RedisUtil.exists(key);
	}
	
	/**
	 * 重新替换今日保险证缓存值。
	 * 
	 * @param vendorId
	 * @param tac
	 * @param force true－强制替换；false-存在时不替换。
	 */
	public static void retsetVendorTodayCertStatic(String vendorId, TodayAddedCertInfo tac, boolean force) {
		String key = getTodayAddedCertKey(vendorId);//今日商户键
		//if (RedisUtil.exists(key)) return;
		long[] values = new long[4];
		values[0] = tac.getTodayAddedCertCount();
		values[1] = tac.getTodayAddedAmount();
		values[2] = tac.getTodayAllCertCount();
		values[3] = tac.getTodayAllAmount();
		RedisUtil.hincrBatch(key, todayAddedCertFields, values, ONE_DAY_SECONDS, force);
	}
	
	/**
	 * 增加退货保险证数量及金额值。
	 * 
	 * @param event
	 * @param tac
	 */
	public static void getsetVendorReturnCertStatic(InsuranceCertStateChangeEvent event, ReturnCertInfo rc) {
		String key = getReturnCertKey(event.getVendorId());//今日商户退货键
		long[] values = new long[4];
		//if (event.getStatus() == InsuranceCertStatusEnum.TO_OPEN.val) {
			values[0] = 1;
			values[1] = event.getTotalPrice();
		//} else if (event.getStatus() == InsuranceCertStatusEnum.TO_REFUND.val) {
			values[2] = 1;
			values[3] = event.getTotalPrice();
		//}
		Long[] rets = RedisUtil.hincrBatch(key, returnCertFields, values, 0, false);
		if (rets == null) {
			reloadVendorReturnCert(event.getVendorId());//本次之前的？事务未提交
			rets = RedisUtil.hincrBatch(key, returnCertFields, values, 0, false);//本次
			/*rc.setTodayReturnCertCount(rci.getTodayReturnCertCount());
			rc.setTodayAmount(rci.getTodayAmount());
			rc.setAllToRefundCertCount(rci.getAllToRefundCertCount());
			rc.setAllToRefundAmount(rci.getAllToRefundAmount());*/
		}
		rc.setTodayReturnCertCount(rets[0]);
		rc.setTodayAmount(rets[1]);
		rc.setAllToRefundCertCount(rets[2]);
		rc.setAllToRefundAmount(rets[3]);
	}
	
	/**
	 * 减少和设置所有待退款
	 * 
	 * @param event
	 * @param tac
	 */
	public static void decVendorRefundCertStatic(InsuranceCertStateChangeEvent event, RefundCertInfo rc) {
		String key = getReturnCertKey(event.getVendorId());//今日商户退货键
		long[] values = new long[2];
		values[0] = -1;//减少待退款保险证数
		values[1] = -event.getTotalPrice();//及金额
		Long[] rets = RedisUtil.hincrBatch(key, refundCertFields, values, 0, false);
		rc.setAllToRefundCertCount(rets[0]);
		rc.setAllToRefundAmount(rets[1]);
	}
	
	/**
	 * 是否存在商户今日缓存数据.
	 * 
	 * @param vendorId
	 * @return
	 */
	public static boolean existsVendorReturnCertStatic(String vendorId) {
		String key = getReturnCertKey(vendorId);//今日商户退货键
		return RedisUtil.exists(key);
	}
	
	/**
	 * 重新替换退货保险证缓存值。
	 * 
	 * @param vendorId
	 * @param tac
	 * @param force true－强制替换；false-存在时不替换。
	 */
	public static void retsetVendorReturnCertStatic(String vendorId, ReturnCertInfo rc, boolean force) {
		String key = getReturnCertKey(vendorId);//今日商户退货键
		//if (RedisUtil.exists(key)) return;
		long[] values = new long[4];
		values[0] = rc.getTodayReturnCertCount();
		values[1] = rc.getTodayAmount();
		values[2] = rc.getAllToRefundCertCount();
		values[3] = rc.getAllToRefundAmount();
		RedisUtil.hincrBatch(key, returnCertFields, values, ONE_DAY_SECONDS, force);
	}
	 
}
