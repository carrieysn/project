package com.cifpay.lc.bankadapter.api;

import com.cifpay.lc.bankadapter.api.input.AbsTradeParam;
import com.cifpay.lc.bankadapter.api.output.GeneralTradeResult;

public interface IBankTradeService {
	public GeneralTradeResult doTrade(AbsTradeParam param);
}
