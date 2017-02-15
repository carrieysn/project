package com.cifpay.lc.bankadapter.api;

import com.cifpay.lc.bankadapter.api.constant.TradeConfig;
import com.cifpay.lc.bankadapter.api.input.AbsTradeParam;
import com.cifpay.lc.bankadapter.api.output.GeneralTradeResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BankTradeServiceStub implements IBankTradeService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private IBankTradeService remote;

    public BankTradeServiceStub(IBankTradeService remote) {
        this.remote = remote;
    }

    /**
     * 远程服务暂时不可用
     */
    int CORE_COMMON_REMOTE_SERVICE_TEMP_UNAVAILABLE = -102997;

    @Override
    public GeneralTradeResult doTrade(AbsTradeParam param) {
        try {
            return remote.doTrade(param);
        } catch (Exception e) {
            logger.error("银行适配器服务暂时不可用，err:{}", e);

            GeneralTradeResult result = new GeneralTradeResult();
            result.setSysReturnCode(CORE_COMMON_REMOTE_SERVICE_TEMP_UNAVAILABLE);
            result.setTradeResult(TradeConfig.TRADE_RESULT_FAIL_1);
            result.setResultDesc("银行适配器服务暂时不可用");

            return result;
        }
    }
}
