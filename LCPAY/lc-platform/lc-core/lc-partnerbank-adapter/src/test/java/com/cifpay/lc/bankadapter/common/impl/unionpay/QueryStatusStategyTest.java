package com.cifpay.lc.bankadapter.common.impl.unionpay;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cifpay.lc.bankadapter.BusinessJUnitTestBase;
import com.cifpay.lc.bankadapter.api.IBankTradeService;
import com.cifpay.lc.bankadapter.api.helper.GetTradeParamHelper;
import com.cifpay.lc.bankadapter.api.input.unionpay.QueryTradeCifParam;

public class QueryStatusStategyTest extends BusinessJUnitTestBase{

	
	@Autowired
	IBankTradeService bankTradeService;

	@Test
	public void processTest() throws Exception {
		
		 QueryTradeCifParam param = GetTradeParamHelper.getInstance(GetTradeParamHelper.UNION_PAY)
				.getTradeParam(QueryTradeCifParam.class, "CP300", "30");
		param.setLcId(1482220107023L);
		param.setSubMerId("777290058137015");
		param.setOrderId("20161220034827");
		param.setTxnTime("20161220034827");
		param.setOnline(true);
		bankTradeService.doTrade(param);
		
	}
	
}
