package com.cifpay.lc.bankadapter.common.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cifpay.lc.bankadapter.BusinessJUnitTestBase;
import com.cifpay.lc.bankadapter.api.IBankTradeService;
import com.cifpay.lc.bankadapter.api.constant.TradeConstant;
import com.cifpay.lc.bankadapter.api.input.bank.SCPayTradeParam;

public class SCPayTradeStategyTest extends BusinessJUnitTestBase  {
	@Autowired
	IBankTradeService bankTradeService;
	
	@Test
	public void  processTest() throws Exception  {
		SCPayTradeParam param = new SCPayTradeParam();
		param.setTradeType(TradeConstant.TRADE_CONFIG.TRADE_TYPE_SCPAY);
		param.setTradeBankCode(TradeConstant.TRADE_CONFIG.TRADE_BANK_ICBC);
		param.setCustomerType(TradeConstant.TRADE_CONFIG.TRADE_CUSTOMER_TYPE_PERSONAL);
		param.setLcId(new Long(8596231488L));
		param.setPayAmount("123.56");
		param.setPayerBankCode(TradeConstant.TRADE_CONFIG.TRADE_BANK_ICBC);
		param.setPayeeBankCode(TradeConstant.TRADE_CONFIG.TRADE_BANK_BOC);
		param.setPayeeBankCardNo("9638527411234565");
		param.setPayerBankCardNo("6184943256022525");
		param.setCurrency("CNY");
		param.setThirdId("taobao");
		param.setMobile("13899999999");
		
		bankTradeService.doTrade(param);
	}
}
