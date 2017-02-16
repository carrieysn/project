/**
 * File: MockCifpayService.java
 *
 * Copyright：Copyright (c) 2016
 * Company：深圳市银信网银科技有限公司
 * Created on：2016年1月22日 下午4:16:20
 */
package com.cifpay.insurance.lc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import com.cifpay.gateway.api.rslt.OpenRslt;
import com.cifpay.gateway.api.rslt.ReturnRslt;
import com.cifpay.gateway.exception.CPBussinessException;
import com.cifpay.gateway.service.CifpayService;
import com.cifpay.insurance.util.DateUtil;
import com.cifpay.insurance.util.StringUtils;
import com.cifpay.lc.security.SerializerBean;
import com.cifpay.lc.security.util.JsonBase64Util;
import com.cifpay.starframework.cache.ConfigPropertiesCache;

/**
 * 
 * @author 张均锋
 *
 */
public class MockCifpayService extends CifpayService {
	private ConfigPropertiesCache configProperties = ConfigPropertiesCache.getInstance();
	private SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	
	public MockCifpayService() {
		super();
	}
	
    public MockCifpayService (String recvBankPubKey, String priKey) {	
    	super(recvBankPubKey, priKey);
    }
    
	@Override
	public OpenRslt openLcWithOrder(Map<String, Object> paramMap) throws CPBussinessException {
		Long lcAmount = (Long)paramMap.get("lcAmount");
		paramMap.put("recvBankName", "工商银行");
		paramMap.put("openBankName", "工商银行");
		UUID uuid = UUID.randomUUID();
		paramMap.put("lcId", "ICBC_"+uuid.toString().replaceAll("-", "").toUpperCase());
		paramMap.put("lcNo", "银信证*"+(lcAmount*1.00/100)+"RMB*110524*"+sd.format(new Date()));
		paramMap.put("mrchName", "融E-维也纳,");
		paramMap.put("userType", "1.0");
		paramMap.put("openChannel", "R");
		paramMap.put("payType", "S");
		OpenRslt r = new OpenRslt();
		r.setUrl(configProperties.get("lc.mockUrl"));//跳转到mock的银信证界面的URL
		SerializerBean sb = JsonBase64Util.writeObject(paramMap);
		r.setData(sb.getData());
		r.setKey(sb.getKey());
		r.setMac(sb.getMac());
		return r;
	}
	
	@Override
	public ReturnRslt noOrderOpenLc(Map<String, Object> paramMap)
			throws CPBussinessException {
		String pbc = (String)paramMap.get("payerBankCode");
		if (StringUtils.isEmpty(pbc)) {
			throw new CPBussinessException("PARAM_IS_NULL", "付款人账号为空！");
		}
		ReturnRslt rst = new ReturnRslt();
		rst.setRequestId((String)paramMap.get("requestId"));
		Long amount = (Long)paramMap.get("amount");
		UUID uuid = UUID.randomUUID();
		rst.setLcId( "ICBC_"+uuid.toString().replaceAll("-", "").toUpperCase());
		rst.setLcNo("银信证*"+(amount*1.00/100)+"RMB*110524*"+sd.format(new Date()));
		rst.setOrderId("O_"+pbc+"_"+sd.format(new Date()));
		rst.setLcState("CREDIT_OPENED");
		rst.setOrderState("CREDIT_OPENED");
		rst.setTradeDate(DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
		return rst;
	}
	
	@Override
	public ReturnRslt receive(Map<String, Object> paramMap)
			throws CPBussinessException {
		ReturnRslt rst = new ReturnRslt();
		rst.setRequestId((String)paramMap.get("requestId"));
		rst.setLcId((String)paramMap.get("lcId"));
		rst.setOrderId((String)paramMap.get("orderId"));
		rst.setLcNo((String)paramMap.get("lcNo"));
		rst.setLcState("CREDIT_RECEIVED");
		rst.setOrderState("CREDIT_RECEIVED");
		rst.setTradeDate(DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
		return rst;
	}
	
	@Override
	public ReturnRslt send(Map<String, Object> paramMap)
			throws CPBussinessException {
		ReturnRslt rst = new ReturnRslt();
		rst.setRequestId((String)paramMap.get("requestId"));
		rst.setLcId((String)paramMap.get("lcId"));
		rst.setOrderId((String)paramMap.get("orderId"));
		rst.setLcNo((String)paramMap.get("lcNo"));
		rst.setLcState("CREDIT_RECEIVED");
		rst.setOrderState("CREDIT_SEND");
		rst.setTradeDate(DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
		return rst;
	}
	
	@Override
	public ReturnRslt reBackLc(Map<String, Object> paramMap)
			throws CPBussinessException {
		ReturnRslt rst = new ReturnRslt();
		rst.setRequestId((String)paramMap.get("requestId"));
		rst.setLcId((String)paramMap.get("lcId"));
		rst.setOrderId((String)paramMap.get("orderId"));
		rst.setLcNo((String)paramMap.get("lcNo"));
		rst.setLcState("CREDIT_SEND_BACK");
		rst.setOrderState("");
		rst.setTradeDate(DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
		return rst;
	}
	
	@Override
	public ReturnRslt paying(Map<String, Object> paramMap)
			throws CPBussinessException {
		ReturnRslt rst = new ReturnRslt();
		rst.setRequestId((String)paramMap.get("requestId"));
		rst.setLcId((String)paramMap.get("lcId"));
		rst.setOrderId((String)paramMap.get("orderId"));
		rst.setLcNo((String)paramMap.get("lcNo"));
		rst.setLcState("CREDIT_PAYED");
		rst.setOrderState("CREDIT_PAYED");
		rst.setTradeDate(DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
		return rst;
	}
}
