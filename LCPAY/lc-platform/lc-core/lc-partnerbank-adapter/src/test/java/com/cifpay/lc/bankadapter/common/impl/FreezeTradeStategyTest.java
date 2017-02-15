package com.cifpay.lc.bankadapter.common.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cifpay.lc.bankadapter.BusinessJUnitTestBase;
import com.cifpay.lc.bankadapter.api.IBankTradeService;
import com.cifpay.lc.bankadapter.api.constant.TradeConstant;
import com.cifpay.lc.bankadapter.api.input.bank.FreezeTradeParam;

public class FreezeTradeStategyTest extends BusinessJUnitTestBase  {
	@Autowired
	IBankTradeService bankTradeService;
	
	@Test
	public void  processTest() throws Exception  {
		FreezeTradeParam param = new FreezeTradeParam();
		param.setTradeType(TradeConstant.TRADE_CONFIG.TRADE_TYPE_FREEZE);
		param.setTradeBankCode(TradeConstant.TRADE_CONFIG.TRADE_BANK_ICBC);
		param.setCustomerType(TradeConstant.TRADE_CONFIG.TRADE_CUSTOMER_TYPE_PERSONAL);
		param.setLcId(new Long(8596231487L));
		param.setFreezeAmount("123.56");
		param.setPayerBankCardNo("9638527411234565");
		param.setCurrency("CNY");
		
		bankTradeService.doTrade(param);
	}
}
