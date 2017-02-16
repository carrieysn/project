/**
 * File: LcNoticeResultHelper.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月17日 上午9:53:40
 */
package com.cifpay.insurance.util;

import java.util.Map;

import com.cifpay.gateway.api.rslt.ReturnRslt;
import com.cifpay.insurance.bean.LcNoticeResultInfo;

/**
 * 银信证通知结果信息助手类
 * 
 * @author 张均锋
 *
 */
public class LcNoticeResultHelper {

	/**
	 * 据通知结果转换为通知结果信息
	 * 
	 * @param rst
	 * @return
	 */
	public static LcNoticeResultInfo convertLcNoticeResult(ReturnRslt rst) {
		LcNoticeResultInfo ret = new LcNoticeResultInfo();
		ret.setRequestId(rst.getRequestId());
		ret.setLcId(rst.getLcId());
		ret.setOrderId(rst.getOrderId());
		ret.setLcNo(rst.getLcNo());
		ret.setLcState(rst.getLcState());
		ret.setOrderState(rst.getOrderState());
		ret.setTradeDate(rst.getTradeDate());
		return ret;
	}

	/**
	 * 据通知结果转换为通知结果信息
	 * 
	 * @param bankDataMap
	 * @return
	 */
	public static LcNoticeResultInfo convertLcNoticeResult(Map<String, Object> bankDataMap) {
		LcNoticeResultInfo ret = new LcNoticeResultInfo();
		ret.setRequestId((String) bankDataMap.get("requestId"));
		ret.setLcId((String) bankDataMap.get("lcId"));
		ret.setOrderId((String) bankDataMap.get("orderId"));
		ret.setLcNo((String) bankDataMap.get("lcNo"));
		ret.setLcState((String) bankDataMap.get("lcState"));
		ret.setOrderState((String) bankDataMap.get("orderState"));
		ret.setTradeDate((String) bankDataMap.get("tradeDate"));
		return ret;
	}
}
