package com.cifpay.lc.bankadapter.common.impl.unionpay;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cifpay.lc.bankadapter.BusinessJUnitTestBase;
import com.cifpay.lc.bankadapter.api.IBankTradeService;
import com.cifpay.lc.bankadapter.api.helper.GetTradeParamHelper;
import com.cifpay.lc.bankadapter.api.input.unionpay.ExpiryCifParam;

public class ExpiryCifTradeStrategyTest extends BusinessJUnitTestBase{

	
	@Autowired
	IBankTradeService bankTradeService;

	/**
	 * 失效 CP300 储蓄卡 - 无跳转预授权撤销
	 * @throws Exception
	 */
	//@Test
    public  void  processTest_TRADE_UNIONPAY_WU_AUTH_UNDO() throws Exception  {
		
		ExpiryCifParam param = GetTradeParamHelper.getInstance(GetTradeParamHelper.UNION_PAY)
				.getTradeParam(ExpiryCifParam.class, "CP300", "10");
		param.setLcId(new Long(1478597316868L));
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		param.setOrderId(time);
		param.setTxnTime(time);
		param.setTxnAmt(new Long(1002L));
		param.setChannelType("07");
		param.setSubMerId("777290058137015");
		param.setOrigOryId("201611080528366327368");
		
		bankTradeService.doTrade(param);
	}
	
	/**
	 * 失效 CP300 信用卡 - 定购预授权撤销
	 * @throws Exception
	 */
	@Test
    public  void  processTest_TRADE_UNIONPAY_DINGGOU_AUTH_UNDO() throws Exception  {
		
		ExpiryCifParam param = GetTradeParamHelper.getInstance(GetTradeParamHelper.UNION_PAY)
				.getTradeParam(ExpiryCifParam.class, "CP300", "30");
		param.setLcId(new Long(1478763334354L));
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		param.setOrderId(time);
		param.setTxnTime(time);
		param.setTxnAmt(new Long(1002L));
		param.setChannelType("07");
		param.setSubMerId("777290058137015");
		param.setOrigOryId("201611100335348237938");
		
		bankTradeService.doTrade(param);
	}
	
}
