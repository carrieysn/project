package com.cifpay.lc.bankadapter.common.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cifpay.lc.bankadapter.BusinessJUnitTestBase;
import com.cifpay.lc.bankadapter.api.IBankTradeService;
import com.cifpay.lc.bankadapter.api.constant.TradeConstant;
import com.cifpay.lc.bankadapter.api.input.bank.SCSyncTradeParam;

public class SCSyncTradeStategyTest extends BusinessJUnitTestBase  {
	@Autowired
	IBankTradeService bankTradeService;
	
	@Test
	public void  processTest() throws Exception  {
		SCSyncTradeParam param = new SCSyncTradeParam();
		param.setTradeType(TradeConstant.TRADE_CONFIG.TRADE_TYPE_SCSYNC);
		param.setTradeBankCode(TradeConstant.TRADE_CONFIG.TRADE_BANK_ICBC);
		param.setCustomerType(TradeConstant.TRADE_CONFIG.TRADE_CUSTOMER_TYPE_PERSONAL);
		param.setLcId(new Long(8596231488L));
		param.setSerialNo("654654635138");
		param.setTradeDate("2016-05-03");
		
		bankTradeService.doTrade(param);
	}
}
