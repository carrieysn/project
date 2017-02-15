package com.cifpay.lc.bankadapter.common.impl.unionpay;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cifpay.lc.bankadapter.BusinessJUnitTestBase;
import com.cifpay.lc.bankadapter.api.IBankTradeService;
import com.cifpay.lc.bankadapter.api.helper.GetTradeParamHelper;
import com.cifpay.lc.bankadapter.api.input.unionpay.SmsCifParam;
import com.cifpay.lc.bankadapter.api.output.GeneralTradeResult;

public class SmsCifTradeStategyTest extends BusinessJUnitTestBase {
	@Autowired
	IBankTradeService bankTradeService;

	@Test
	public void processTest_TRADE_UNIONPAY_SMS() throws Exception {
		/**
		 * 短信测试
		 */
		SmsCifParam param = GetTradeParamHelper.getInstance(GetTradeParamHelper.UNION_PAY)
				.getTradeParam(SmsCifParam.class);
		Date date = new Date();
		param.setLcId(date.getTime());
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
		param.setOrderId(time);
		param.setTxnTime(time);
		param.setTxnAmt(new Long(1001L));
		param.setSubMerId("777290058137015");
		param.setPhoneNo("13552535506");
		param.setAccNo("6216261000000000018");

		GeneralTradeResult result = bankTradeService.doTrade(param);

		System.out.println("/n~~~++++++++++LcId~+++++++++++： " + param.getLcId());
		System.out.println("/n~~~++++++++++result~+++++++++++： " + result);
	}

}
