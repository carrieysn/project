package com.cifpay.lc.bankadapter.common.impl.unionpay;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cifpay.lc.bankadapter.BusinessJUnitTestBase;
import com.cifpay.lc.bankadapter.api.IBankTradeService;
import com.cifpay.lc.bankadapter.api.helper.GetTradeParamHelper;
import com.cifpay.lc.bankadapter.api.input.unionpay.OpenCardParam;
import com.cifpay.lc.bankadapter.api.output.GeneralTradeResult;

public class OpenCardStategyTest extends BusinessJUnitTestBase {

	@Autowired
	IBankTradeService bankTradeService;

	@Test
	public void processTest_TRADE_UNIONPAY_WU_OPEN_CARD() throws Exception {
		/**
		 * 无跳转第一次开通 开证 CP200 储蓄卡
		 */
		OpenCardParam param = GetTradeParamHelper.getInstance(GetTradeParamHelper.UNION_PAY)
				.getTradeParam(OpenCardParam.class, "CP200", "10", "0");
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		param.setLcId(new Date().getTime());
		param.setSubMerId("777290058137015");
		param.setAccNo("6216261000000000018");
		param.setOrderId(time);
		param.setTxnTime(time);
		param.setChannelType("07");
		param.setReqReserved("reqReserved-保留域");

		GeneralTradeResult result = bankTradeService.doTrade(param);
		System.out.println("/n~~~++++++++++LcId~+++++++++++： " + param.getLcId());
		System.out.println("/n~~~++++++++++orderId~+++++++++++： " + param.getOrderId());
		System.out.println("/n~~~++++++++++queryId~+++++++++++： " + result.getQueryId());
		System.out.println("/n~~~++++++++++html~+++++++++++： " + result.getResultMap().get("data"));
		// 测试返回结果：tokenPayData={token=6235240000020555286&trId=62000000001&tokenType=01&tokenBegin=20161020143757&tokenEnd=20211016155640&tokenLevel=20}
	}

}
