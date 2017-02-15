package com.cifpay.lc.bankadapter.universal.impl.unionpay;

import org.springframework.util.Assert;

import com.cifpay.lc.bankadapter.api.input.AbsTradeParam;
import com.cifpay.lc.bankadapter.universal.IParamValidate;

public class OpenCardCifValidate implements IParamValidate {
	//private static final Logger LOGGER = LoggerFactory.getLogger(GeneralTradeService.class);

	@Override
	public void validate(AbsTradeParam tradeParam) {
		Assert.notNull(tradeParam, "业务参数不能为空！");
		/*
		 * LOGGER.info("OpenCardCifValidate-开始校验业务参数：" + tradeParam.toString());
		 * // TODO LOGGER.info("OpenCardCifValidate-业务参数校验通过！");
		 */
	}
}
