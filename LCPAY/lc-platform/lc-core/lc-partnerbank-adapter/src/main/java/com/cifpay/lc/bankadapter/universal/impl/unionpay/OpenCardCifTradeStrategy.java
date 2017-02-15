package com.cifpay.lc.bankadapter.universal.impl.unionpay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cifpay.lc.bankadapter.api.input.AbsTradeParam;
import com.cifpay.lc.bankadapter.api.input.unionpay.OpenCardParam;
import com.cifpay.lc.bankadapter.api.output.AbsBusinessResult;
import com.cifpay.lc.bankadapter.api.output.GeneralTradeResult;
import com.cifpay.lc.bankadapter.universal.ITradeStrategy;
import com.cifpay.lc.bankadapter.universal.service.impl.unionpay.UnionPayStategyService;
import com.cifpay.lc.bankadapter.universal.tool.BusinessTradeLock;

/**
 * 银联无跳转开通交易处理策略
 * 
 * @author linql
 *
 */
@Component
public class OpenCardCifTradeStrategy implements ITradeStrategy {
	private static final Logger LOGGER = LoggerFactory.getLogger(OpenCardCifTradeStrategy.class);
	@Autowired
	private BusinessTradeLock lock;
	@Autowired
	private UnionPayStategyService stategyService;

	@Override
	public AbsBusinessResult process(AbsTradeParam tradeParam) throws Exception {
		LOGGER.info("OpenCardCifTradeStrategy 开始处理请求...");
		// 1. 检查参数
		new OpenCardCifValidate().validate(tradeParam);
		OpenCardParam param = (OpenCardParam) tradeParam;
		Long lcId = param.getLcId();
		GeneralTradeResult reslut = null;
		try {
			// 2. 锁定记录
			lock.lockLcId(lcId);
			LOGGER.info("准备进行无跳转开通交易。。。");
			reslut = (GeneralTradeResult) stategyService.tradeDealAdapter(param);

		} catch (Exception e) {
			LOGGER.error("####OpenCardCifTradeStrategy处理错误####：{}", e.getMessage());
			throw e;
		} finally {
			lock.unLockLcId(lcId);
		}

		return reslut;
	}

	@Override
	public String getStrategyType() {
		return OpenCardParam.class.getName();
	}

}
