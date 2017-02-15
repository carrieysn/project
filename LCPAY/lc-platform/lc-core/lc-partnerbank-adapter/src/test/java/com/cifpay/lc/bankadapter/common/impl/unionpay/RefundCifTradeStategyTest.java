package com.cifpay.lc.bankadapter.common.impl.unionpay;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cifpay.lc.bankadapter.BusinessJUnitTestBase;
import com.cifpay.lc.bankadapter.api.IBankTradeService;
import com.cifpay.lc.bankadapter.api.helper.GetTradeParamHelper;
import com.cifpay.lc.bankadapter.api.input.unionpay.RefundCifParam;

public class RefundCifTradeStategyTest extends BusinessJUnitTestBase {

	@Autowired
	IBankTradeService bankTradeService;

	/**
	 * 退证 CP300 储蓄卡 未清算 - 无跳转预授权完成撤销
	 * 
	 * @throws Exception
	 */
	// @Test
	public void processTest_TRADE_UNIONPAY_WU_AUTH_FINISH_UNDO() throws Exception {

		RefundCifParam param = GetTradeParamHelper.getInstance(GetTradeParamHelper.UNION_PAY)
				.getTradeParam(RefundCifParam.class, "CP300", "10");
		param.setLcId(new Long(1478765608549L));
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		param.setOrderId(time);
		param.setTxnTime(time);
		param.setTxnAmt(new Long(1002L));
		param.setChannelType("07");
		param.setSubMerId("777290058137015");
		param.setOrigOryId("201611100419228303218");

		bankTradeService.doTrade(param);
	}

	/**
	 * 退证 CP300 信用卡 未清算 - 订购预授权完成撤销
	 * 
	 * @throws Exception
	 */
	 @Test
	public void processTest_TRADE_UNIONPAY_DINGGOU_AUTH_FINISH_UNDO() throws Exception {

		RefundCifParam param = GetTradeParamHelper.getInstance(GetTradeParamHelper.UNION_PAY)
				.getTradeParam(RefundCifParam.class, "CP200", "30");
		param.setLcId(new Long(1480385557500L));
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		param.setOrderId(time);
		param.setTxnTime(time);
		param.setTxnAmt(new Long(1001L));
		param.setChannelType("07");
		param.setSubMerId("777290058137015");
		param.setOrigOryId("201611291012373741458");
		param.setReqReserved("{\"payHandler\":\"Refund\"}");
		bankTradeService.doTrade(param);
	}

	/**
	 * 退证 CP300 储蓄卡 清算 - 无跳转退货
	 * 
	 * @throws Exception
	 */
	// @Test
	public void process_TRADE_UNIONPAY_WU_CONSUME_REFUND() throws Exception {
		RefundCifParam param = GetTradeParamHelper.getInstance(GetTradeParamHelper.UNION_PAY)
				.getTradeParam(RefundCifParam.class, "CP300", "10");
		param.setLcId(new Long(1478593419493L));
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		param.setOrderId(time);
		param.setTxnTime(time);
		param.setTxnAmt(new Long(1002L));
		param.setChannelType("07");
		param.setSubMerId("777290058137015");
		param.setOrigOryId("201611080428356274248");

		bankTradeService.doTrade(param);
	}

	/**
	 * 退证 CP300 信用卡 清算 - 定购退货
	 * 
	 * @throws Exception
	 */
	// @Test
	public void process_TRADE_UNIONPAY_DINGGOU_CONSUME_REFUND() throws Exception {
		RefundCifParam param = GetTradeParamHelper.getInstance(GetTradeParamHelper.UNION_PAY)
				.getTradeParam(RefundCifParam.class, "CP200", "30");
		param.setLcId(new Long(1480385557500L));
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		param.setOrderId(time);
		param.setTxnTime(time);
		param.setTxnAmt(new Long(1L));
		param.setChannelType("07");
		param.setSubMerId("777290058137015");
		param.setOrigOryId("201611221047223765938");

		bankTradeService.doTrade(param);
	}

	/**
	 * 退证 CP200 信用卡 未清算 - 定购消费撤销
	 * 
	 * @throws Exception
	 */
	//@Test
	public void process_TRADE_UNIONPAY_DINGGOU_CONSUME_UNDO() throws Exception {
		RefundCifParam param = GetTradeParamHelper.getInstance(GetTradeParamHelper.UNION_PAY)
				.getTradeParam(RefundCifParam.class, "CP200", "30");
		param.setLcId(new Long(1478772266649L));
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		param.setOrderId(time);
		param.setTxnTime(time);
		param.setTxnAmt(new Long(1001L));
		param.setChannelType("07");
		param.setSubMerId("777290058137015");
		param.setOrigOryId("201611100604267789608");

		bankTradeService.doTrade(param);
	}
}
