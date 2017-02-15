package com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.exception;

import com.cifpay.lc.core.exception.ThirdTradePlatformAdapterException;

/**
 * NC交易结果未能确定
 * 
 * 
 *
 */
public class NCResultUncertainException extends ThirdTradePlatformAdapterException {
	private static final long serialVersionUID = -8637188446735450836L;

	public NCResultUncertainException(int errCode, String errMsg) {
		super(errCode, errMsg);
	}

	public NCResultUncertainException(int errCode, String errMsg, Throwable cause) {
		super(errCode, errMsg, cause);
	}

}
