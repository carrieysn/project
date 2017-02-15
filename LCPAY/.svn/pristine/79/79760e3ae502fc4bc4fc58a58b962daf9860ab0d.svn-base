package com.cifpay.lc.bankadapter.common.impl.unionpay;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cifpay.lc.bankadapter.BusinessJUnitTestBase;
import com.cifpay.lc.bankadapter.api.IBankTradeService;
import com.cifpay.lc.bankadapter.api.constant.TradeConstant;
import com.cifpay.lc.bankadapter.api.helper.GetTradeParamHelper;
import com.cifpay.lc.bankadapter.api.input.unionpay.OpenCifParam;
/**
 * Token  Test
 * @author Administrator
 *
 */
public class OpenCifTokenTradeStategyTest  extends BusinessJUnitTestBase{
	
	
	@Autowired
	IBankTradeService bankTradeService;
	/**
	 * Token 消费交易 
	 */
	@Test
	public void processTest() throws Exception {
	
		//OpenCifParam param = GetTradeParamHelper.getInstance(GetTradeParamHelper.UNION_PAY).getTradeParam(OpenCifParam.class, "CP300", "0","0");
		//OpenCifParam param = GetTradeParamHelper.getInstance(GetTradeParamHelper.UNION_PAY).getTradeParam(OpenCifParam.class, "CP300", "0");
		OpenCifParam param = new OpenCifParam();
		param.setBusinessId(004122553l);
		param.setLcId(new Date().getTime());
		param.setTxnId(TradeConstant.TRADE_CONFIG.TRADE_UNIONPAY_WU_CONSUME);
		param.setTxnType("01");
		param.setTxnSubType("01");
		param.setMerId("777290058137015");
		param.setAccNo("6216261000000000018");
		String txnTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		param.setTxnTime(txnTime);
		param.setOrderId(txnTime);
		param.setTxnAmt(new Long(1001L));
		param.setChannelType("07");
		param.setSubMerId("777290058137015");
		param.setAccType("01");
		param.setCurrencyCode("156");
		param.setSmsCode("111111");
		param.setReqReserved("reqReserved-保留域");
		param.setOrderDesc("订单描述。。");

		bankTradeService.doTrade(param);
	}
	
	/**
	 * Token 预授权
	 * @throws Exception 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	//@Test
	public void processAuthTest() throws Exception{
		
		OpenCifParam param = GetTradeParamHelper.getInstance(GetTradeParamHelper.UNION_PAY)
				.getTradeParam(OpenCifParam.class, "CP300", "10");
		param.setLcId(new Date().getTime());
		param.setUserId("138000138000");
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		param.setOrderId("20161026112433");
		param.setTxnTime("20161026112433");
		param.setTxnAmt(new Long(1001L));
		param.setChannelType("07");
		param.setSubMerId("777290058137015");
		param.setAccType("01");
		param.setAccNo("6216261000000000018");
		param.setCurrencyCode("156");
		/*param.setCertifTp("01");
		param.setCertifId("341126197709218366");
	    param.setCustomerNm("互联网");*/
		param.setCvn2("123");
		param.setExpired("1711");
		param.setPhoneNo("13552535506");
		param.setReqReserved("reqReserved-保留域");
		param.setOrderDesc("订单描述。。");

		bankTradeService.doTrade(param);
		
		
	}

}
