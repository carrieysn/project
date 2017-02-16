/**
 * File: CifpayServiceAdapter.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月18日 下午4:15:56
 */
package com.cifpay.insurance.lc;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.cifpay.gateway.api.rslt.OpenRslt;
import com.cifpay.gateway.api.rslt.ReturnRslt;
import com.cifpay.gateway.exception.CPBussinessException;
import com.cifpay.gateway.service.CifpayService;
import com.cifpay.insurance.util.DateUtil;
import com.cifpay.starframework.cache.ConfigPropertiesCache;

/**
 * 银信证适配
 * 
 * @author 张均锋
 *
 */
public class CifpayServiceAdapter {
	private static final Logger logger = LogManager.getLogger(CifpayServiceAdapter.class);
	private ConfigPropertiesCache configProperties = ConfigPropertiesCache.getInstance();
	private CifpayService cifpayService;// = new CifpayService(configProperties.get("lc.public_key"), configProperties.get("lc.private_key"));
	
	public CifpayService getCifpayService() {
		return cifpayService;
	}

	public void setCifpayService(CifpayService cifpayService) {
		this.cifpayService = cifpayService;
	}

	/**
	 * 跟单开证。
	 * 
	 * @param pi
	 * @return
	 */
	public OpenRslt openLcWithOrder(OrderPayInfo pi) throws CPBussinessException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("requestId", RandomStringUtils.randomNumeric(10));
		paramMap.put("requestTime", DateUtil.formatDate(new Date(), "yyyyMMddHHmmss"));
		paramMap.put("thirdId", configProperties.get("lc.thirdId"));
		paramMap.put("currency","CNY");
		paramMap.put("openType", 0);//默认普通开证
		paramMap.put("lcType", "CP300");//默认银信证类型
		paramMap.put("openBankCode", pi.getOpenBankCode());
		paramMap.put("orderId", pi.getOrderId());
		paramMap.put("lcAmount",pi.getAmount());//支付金额
		paramMap.put("currency","CNY");//默认RMB
		paramMap.put("openType", 0);//默认普通开证
		paramMap.put("lcType", "CP300");//默认银信证类型
		paramMap.put("payerMobile", pi.getPayerMobile());//付款人在银行保留的手机号码
		paramMap.put("returnUrl", pi.getReturnUrl());//返回地址
		paramMap.put("noticeUrl", pi.getNoticeUrl());//通知地址
		paramMap.put("mrchOrderUrl", pi.getMrchOrderUrl());//商户订单地址默认返回地址
		paramMap.put("isAutoRecv","A");//默认自动收证
		paramMap.put("orderAmount", pi.getAmount());//订单总金额默认保费
		Date rvalidDate = DateUtil.addMonth(new Date(), 1); 
		String recvValidTime = DateUtil.formatDate(rvalidDate, "yyyy-MM-dd");
		paramMap.put("recvValidTime", recvValidTime);//默认收证有效期为一个月后
		Date sValidTime = DateUtil.addDay(rvalidDate, 7);
		String sendValidTime = DateUtil.formatDate(sValidTime, "yyyy-MM-dd");
		paramMap.put("sendValidTime", sendValidTime);//默认履约有效期为收证有效期7天之后
		Date payValidTime = DateUtil.addDay(sValidTime, 7);
		String confirmPayValidTime = DateUtil.formatDate(payValidTime, "yyyy-MM-dd");
		paramMap.put("confirmPayValidTime",confirmPayValidTime);//默认申请解付有效期为履约有效期7天之后
		logger.info("开始处理跟单开证，参数："+paramMap);
		OpenRslt openRslt = cifpayService.openLcWithOrder(paramMap);
		logger.info("跟单开证成功，返回数据："+openRslt);
		return openRslt;
	}
	
	/** 空单开证 **/
	public ReturnRslt noOrderOpenLc(NoOrderPayInfo pi) throws CPBussinessException {
		/* 将请求参数封装成Map */
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("requestId", RandomStringUtils.randomNumeric(10));
		Date now = new Date();
		paramMap.put("requestTime", DateUtil.formatDate(now, "yyyyMMddHHmmss"));
		paramMap.put("thirdId", configProperties.get("lc.thirdId"));
		paramMap.put("amount", pi.getAmount());// 金额
		paramMap.put("currency", "CNY");
		paramMap.put("payerBankCode", pi.getPayerBankCode());
		paramMap.put("payerBankAcctno", pi.getPayerBankAcctno());
		paramMap.put("payerAcctName", pi.getPayerAcctName());
		paramMap.put("payerCardType", pi.getPayerCardType());
		paramMap.put("payerMobile", pi.getPayerMobile());// 开证人手机号码
		paramMap.put("lcType", "CP300");// 默认银信证类型
		paramMap.put("openType", 0);
		paramMap.put("recvBankCode", pi.getRecvBankCode());
		paramMap.put("recvBankAcctno", pi.getRecvBankAcctno());
		paramMap.put("recvAcctName", pi.getRecvAcctName());
		paramMap.put("recvMobile", pi.getRecvMobile());
		Date rvalidDate = DateUtil.addMonth(now, 1);
		paramMap.put("recvTime", DateUtil.formatDate(rvalidDate, "yyyy-MM-dd"));// 默认收证有效期为一个月后
		Date sValidTime = DateUtil.addDay(rvalidDate, 7);
		paramMap.put("sendTime", DateUtil.formatDate(sValidTime, "yyyy-MM-dd"));// 默认履约有效期为收证有效期7天之后
		Date payValidTime = DateUtil.addDay(sValidTime, 7);
		paramMap.put("confirmPayTime", DateUtil.formatDate(payValidTime, "yyyy-MM-dd"));// 默认申请解付有效期为履约有效期7天之后
		paramMap.put("userType", 0);
		paramMap.put("noticeUrl", pi.getNoticeUrl());
		logger.info("开始处理空单开证，参数："+paramMap);
		ReturnRslt rslt = cifpayService.noOrderOpenLc(paramMap); // 没有异常即为成功
		logger.info("空单开证成功，返回数据："+rslt);
		return rslt;
	}

	/** 手工收证 **/
	public ReturnRslt receive(String lcId, String openBankCode) throws CPBussinessException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("requestId", RandomStringUtils.randomNumeric(10));
		Date now = new Date();
		paramMap.put("requestTime", DateUtil.formatDate(now, "yyyyMMddHHmmss"));
		paramMap.put("thirdId", configProperties.get("lc.thirdId"));
		paramMap.put("lcId", lcId);// lcId
		paramMap.put("openBankCode", openBankCode);// 开证行
		logger.info("开始处理手工收证，参数："+paramMap);
		ReturnRslt rslt = cifpayService.receive(paramMap);// 没有异常即为成功
		logger.info("手工收证，返回数据："+rslt);
		return rslt;
	}

	/** 履约 **/
	public ReturnRslt send(String lcId, String orderId) throws CPBussinessException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("requestId", RandomStringUtils.randomNumeric(10));
		Date now = new Date();
		paramMap.put("requestTime", DateUtil.formatDate(now, "yyyyMMddHHmmss"));
		paramMap.put("thirdId", configProperties.get("lc.thirdId"));
		paramMap.put("lcId", lcId);// lcId
		paramMap.put("orderId", orderId);// 开证行
		paramMap.put("content", "运单号：10254515");// 发货数据 默认
		paramMap.put("sendContentId", "10254515");// 发货凭证编号 默认
		logger.info("开始处理履约，参数："+paramMap);
		ReturnRslt rslt = cifpayService.send(paramMap);// 没有异常即为成功
		logger.info("履约成功，返回数据："+rslt);
		return rslt;
	}

	/** 退回（解冻） **/
	public ReturnRslt reBackLc(String lcId) throws CPBussinessException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("requestId", RandomStringUtils.randomNumeric(10));
		Date now = new Date();
		paramMap.put("requestTime", DateUtil.formatDate(now, "yyyyMMddHHmmss"));
		paramMap.put("thirdId", configProperties.get("lc.thirdId"));
		paramMap.put("lcId", lcId);// lcId
		logger.info("开始处理退回，参数："+paramMap);
		ReturnRslt rslt = cifpayService.reBackLc(paramMap);// 没有异常即为成功
		logger.info("退回银信证成功，返回数据："+rslt);
		return rslt;
	}

	/** 解付 **/
	public ReturnRslt paying(String lcId, String orderId, Long amount) throws CPBussinessException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("requestId", RandomStringUtils.randomNumeric(10));
		Date now = new Date();
		paramMap.put("requestTime", DateUtil.formatDate(now, "yyyyMMddHHmmss"));
		paramMap.put("thirdId", configProperties.get("lc.thirdId"));
		paramMap.put("lcId", lcId);// lcId
		paramMap.put("orderId", orderId);// 开证行
		paramMap.put("amount", amount);
		logger.info("开始处理解付，参数："+amount);
		ReturnRslt rslt = cifpayService.paying(paramMap);// 没有异常即为成功
		logger.info("银信证解付成功，返回数据："+rslt);
		return rslt;
	}

}
