package com.cifpay.lc.bankadapter.common.impl.unionpay;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cifpay.lc.bankadapter.BusinessJUnitTestBase;
import com.cifpay.lc.bankadapter.api.IBankTradeService;
import com.cifpay.lc.bankadapter.api.helper.GetTradeParamHelper;
import com.cifpay.lc.bankadapter.api.input.unionpay.PayCifParam;
import com.cifpay.lc.bankadapter.api.output.GeneralTradeResult;

public class PayCifTradeStategyTest extends BusinessJUnitTestBase  {
	@Autowired
	IBankTradeService bankTradeService;
	
	@Test
	public void  processTest_TRADE_UNIONPAY_DINGGOU_AUTH_FINISH() throws Exception  {
		/**
		 * 定购 支付 CP300 信用卡
		 */
		PayCifParam param = GetTradeParamHelper.getInstance(GetTradeParamHelper.UNION_PAY)
				.getTradeParam(PayCifParam.class, "CP300", "30");
		Date date =new Date();
		param.setLcId(1479881604775L);
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
		param.setOrderId(time);
		param.setTxnTime(time);
		param.setTxnAmt(new Long(1002L));
		param.setChannelType("07");
		param.setSubMerId("777290058137015");
		param.setOrigOryId("201611230213241329298");
		
		//param.setReqReserved("定购 支付 CP300 信用卡");
		
		GeneralTradeResult result = bankTradeService.doTrade(param);

		System.out.println("/n~~~++++++++++LcId~+++++++++++： " + param.getLcId());
		System.out.println("/n~~~++++++++++orderId~+++++++++++： " + param.getOrderId());
		System.out.println("/n~~~++++++++++queryId~+++++++++++： " + result.getQueryId());
	}
	/**
	 * 无跳转 申请解付  CP300 储蓄卡（预授权完成）
	 */
	//@Test
	public void  process_TRADE_UNIONPAY_WU_AUTH_FINISH() throws Exception  {
		PayCifParam param = GetTradeParamHelper.getInstance(GetTradeParamHelper.UNION_PAY)
				.getTradeParam(PayCifParam.class, "CP300", "10");
		param.setLcId(new Long(20161108042339L));
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		param.setOrderId(time);
		param.setTxnTime(time);
		param.setTxnAmt(new Long(1000L));
		param.setChannelType("07");
		param.setSubMerId("777290058137015");
		param.setOrigOryId("201611080423396269128");
		
		param.setReqReserved("无跳转  支付 CP300 储蓄卡");
		
		bankTradeService.doTrade(param);
	}
	
}
