package com.cifpay.lc.bankadapter.open;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.cifpay.lc.bankadapter.api.IBankTradeService;
import com.cifpay.lc.bankadapter.api.constant.TradeConstant;
import com.cifpay.lc.bankadapter.api.helper.RandomTool;
import com.cifpay.lc.bankadapter.api.input.AbsTradeParam;
import com.cifpay.lc.bankadapter.api.input.unionpay.QueryTradeCifParam;
import com.cifpay.lc.bankadapter.api.output.GeneralTradeResult;
import com.cifpay.lc.bankadapter.universal.ITradeService;
import com.cifpay.lc.core.exception.BankAdapterException;

@Component("bankTradeService")
public class BankTradeService implements IBankTradeService {
	private static final Logger LOGGER = LoggerFactory.getLogger(BankTradeService.class);
	@Autowired
	private ITradeService service;

	public GeneralTradeResult doTrade(AbsTradeParam param) {
		String random = RandomTool.generateString(6);
		MDC.put("rid", random);
		LOGGER.info("=====开始处理请求-参数：{}", param);
		GeneralTradeResult result = null;
		try {
			result = service.doTrade(param);
		} catch (Exception e) {
			LOGGER.error("↓↓↓↓ ↓↓↓↓ GeneralTradeResult 异常 ↓↓↓↓↓↓↓↓", e);
			if (!(param instanceof QueryTradeCifParam)) {
				if (result != null) {
					if (StringUtils.isEmpty(result.getTradeResult())) {
						result.setTradeResult(TradeConstant.TRADE_CONFIG.TRADE_RESULT_UNKNOWN_2);
					}
				} else {
					result = new GeneralTradeResult();
					result.setTradeResult(TradeConstant.TRADE_CONFIG.TRADE_RESULT_UNKNOWN_2);
				}
			} else {
				// 查询交易
				result = new GeneralTradeResult();
				result.setQueryTradeResult("1");
			}
			if (e instanceof BankAdapterException) {
				BankAdapterException be = (BankAdapterException) e;
				result.setSysReturnCode(be.getReturnCode());
				result.setResultDesc(be.getMessage());
			}
		}
		
		result.setResultDesc(result.getResultDesc() + " [处理凭证号：" + random + "]");
		LOGGER.info("*****BankTradeService 处理交易结果*****  {}", result);
		LOGGER.info("============================== BANK ADAPTER LOG END ==============================");
		
		return result;

	}
}
