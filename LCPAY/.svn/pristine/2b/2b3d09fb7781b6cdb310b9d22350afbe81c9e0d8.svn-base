package com.cifpay.lc.bankadapter.universal.impl.unionpay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cifpay.lc.bankadapter.api.input.AbsTradeParam;
import com.cifpay.lc.bankadapter.api.input.unionpay.BackRcvResponseCifParam;
import com.cifpay.lc.bankadapter.api.output.AbsBusinessResult;
import com.cifpay.lc.bankadapter.api.output.GeneralTradeResult;
import com.cifpay.lc.bankadapter.universal.ITradeStrategy;
import com.cifpay.lc.bankadapter.universal.service.impl.unionpay.UnionPayStategyService;

/**
 * 银联查询交易处理策略
 * 
 * @author linql
 *
 */
@Component
public class BackRcvResponseCifTradeStrategy implements ITradeStrategy {
	private static final Logger LOGGER = LoggerFactory.getLogger(BackRcvResponseCifTradeStrategy.class);
	@Autowired
	private UnionPayStategyService stategyService;

	@Override
	public AbsBusinessResult process(AbsTradeParam tradeParam) throws Exception {
		LOGGER.info("BackRcvResponseCifTradeStrategy 开始处理请求...");
		// 1. 检查参数
		BackRcvResponseCifParam param = (BackRcvResponseCifParam) tradeParam;
		GeneralTradeResult reslut = null;
		try {
			reslut = (GeneralTradeResult) stategyService.tradeDealAdapter(param);

		} catch (Exception e) {
			LOGGER.error("####BackRcvResponseCifTradeStrategy处理错误####：{}", e.getMessage());
			throw e;
		} 

		return reslut;
	}

	@Override
	public String getStrategyType() {
		return BackRcvResponseCifParam.class.getName();
	}

}
