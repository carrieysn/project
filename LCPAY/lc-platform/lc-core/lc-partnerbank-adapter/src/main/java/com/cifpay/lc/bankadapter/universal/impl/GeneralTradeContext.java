package com.cifpay.lc.bankadapter.universal.impl;

import com.cifpay.lc.bankadapter.api.input.AbsTradeParam;
import com.cifpay.lc.bankadapter.api.output.AbsBusinessResult;
import com.cifpay.lc.bankadapter.api.output.GeneralTradeResult;
import com.cifpay.lc.bankadapter.universal.ITradeContext;
import com.cifpay.lc.bankadapter.universal.ITradeStrategy;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.core.exception.BankAdapterException;

public class GeneralTradeContext implements ITradeContext {

	private ITradeStrategy tradeStrategy;
	
	public GeneralTradeContext(ITradeStrategy tradeStrategy)
	{
		this.tradeStrategy = tradeStrategy;
	}

	@Override
	public GeneralTradeResult action(AbsTradeParam tradeParam) throws Exception {
		AbsBusinessResult result = tradeStrategy.process(tradeParam);
		if(result != null && result instanceof GeneralTradeResult)
		{
			return  (GeneralTradeResult)result;
		}
		else
		{
			throw new BankAdapterException(ReturnCode.CORE_BA_UNDEFINE_N105999, "错误的结果返回类型！");
		}
	}

}
