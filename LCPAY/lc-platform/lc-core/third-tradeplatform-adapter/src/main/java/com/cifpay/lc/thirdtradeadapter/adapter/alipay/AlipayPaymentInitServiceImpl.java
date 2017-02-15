package com.cifpay.lc.thirdtradeadapter.adapter.alipay;

import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.core.common.CoreBusinessContext;
import com.cifpay.lc.core.common.CoreBusinessServiceImplBase;
import com.cifpay.lc.core.exception.CoreBusinessException;
import com.cifpay.lc.core.exception.CoreValidationRejectException;
import com.cifpay.lc.thirdtradeadapter.api.input.alipay.AlipayPaymentInitInstruction;
import com.cifpay.lc.thirdtradeadapter.api.output.alipay.AlipayPaymentInitResult;
import com.cifpay.lc.thirdtradeadapter.api.service.alipay.AlipayPaymentInitService;
import com.cifpay.lc.util.StringParamPair;

/**
 * 
 *
 */
public class AlipayPaymentInitServiceImpl
		extends CoreBusinessServiceImplBase<AlipayPaymentInitInstruction, AlipayPaymentInitResult>
		implements AlipayPaymentInitService {

	@Override
	protected void validate(AlipayPaymentInitInstruction inputBean, CoreBusinessContext context)
			throws CoreValidationRejectException {

	}

	@Override
	protected BusinessOutput<AlipayPaymentInitResult> processBusiness(AlipayPaymentInitInstruction inputBean,
			CoreBusinessContext context) throws CoreBusinessException {

		// TODO ...

		AlipayPaymentInitResult initResult = new AlipayPaymentInitResult();
		initResult.setInstructionNo("xxxx1");
		initResult.setTotalAmount(888L);
		initResult.setAdapterReturnedSeqNo("xxxx3");

		initResult.addAlipayAutoPostFormParams(new StringParamPair("service", "alipay.wap.create.direct.pay.by.user"));
		initResult.addAlipayAutoPostFormParams(new StringParamPair("partner", "2088221310230022"));
		initResult.addAlipayAutoPostFormParams(new StringParamPair("seller_id", "2088221310230022"));
		initResult.addAlipayAutoPostFormParams(new StringParamPair("_input_charset", "utf-8"));
		initResult.addAlipayAutoPostFormParams(new StringParamPair("payment_type", "1"));
		initResult.addAlipayAutoPostFormParams(
				new StringParamPair("extern_token", String.valueOf(System.currentTimeMillis()))); // TODO
																									// extern_token
		initResult.addAlipayAutoPostFormParams(new StringParamPair("out_trade_no", ""));
		initResult.addAlipayAutoPostFormParams(new StringParamPair("subject", ""));
		initResult.addAlipayAutoPostFormParams(new StringParamPair("total_fee", ""));
		initResult.addAlipayAutoPostFormParams(new StringParamPair("show_url", ""));
		initResult.addAlipayAutoPostFormParams(new StringParamPair("return_url", ""));
		initResult.addAlipayAutoPostFormParams(new StringParamPair("notify_url", ""));
		initResult.addAlipayAutoPostFormParams(new StringParamPair("body", ""));
		initResult.addAlipayAutoPostFormParams(new StringParamPair("it_b_pay", ""));

		initResult.addAlipayAutoPostFormParams(new StringParamPair("sign", ""));
		initResult.addAlipayAutoPostFormParams(new StringParamPair("sign_type", "RSA"));

		BusinessOutput<AlipayPaymentInitResult> output = new BusinessOutput<AlipayPaymentInitResult>();
		output.setReturnCode(ReturnCode.GENERAL_SUCCESS);
		output.setReturnMsg("初化化成功");
		output.setData(initResult);
		return output;
	}

}
