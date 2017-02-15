package com.cifpay.lc.bankadapter.common.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cifpay.lc.bankadapter.BusinessJUnitTestBase;
import com.cifpay.lc.bankadapter.api.IBankTradeService;
import com.cifpay.lc.bankadapter.api.constant.TradeConstant;
import com.cifpay.lc.bankadapter.api.input.bank.UnfreezeTradeParam;

public class UnfreezeTradeStategyTest extends BusinessJUnitTestBase  {
	@Autowired
	IBankTradeService bankTradeService;
	
	@Test
	public void  processTest() throws Exception  {
		UnfreezeTradeParam param = new UnfreezeTradeParam();
		param.setTradeType(TradeConstant.TRADE_CONFIG.TRADE_TYPE_UNFREEZE);
		param.setTradeBankCode(TradeConstant.TRADE_CONFIG.TRADE_BANK_ICBC);
		param.setCustomerType(TradeConstant.TRADE_CONFIG.TRADE_CUSTOMER_TYPE_PERSONAL);
		param.setLcId(new Long(8596231487L));
		param.setUnfreezeAmount("123.56");
		param.setPayerBankCardNo("9638527411234565");
		param.setBizUnfreezeSerialNo(951753258741L);
		param.setCurrency("CNY");
		param.setThirdId("taobao");
		param.setMobile("13899999999");
		
		bankTradeService.doTrade(param);
	}
}
