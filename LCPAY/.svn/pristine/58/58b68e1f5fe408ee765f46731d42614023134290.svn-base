package com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.exception;

import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.core.exception.ThirdTradePlatformAdapterException;

/**
 * 交易明确失败时抛出此异常
 * 
 * 
 *
 */
public class NCResultFailedException extends ThirdTradePlatformAdapterException {
	private static final long serialVersionUID = 6226259295440025149L;
	private static final int ERR_CODE = ReturnCode.CORE_3RD_ICBC_BANKENT_PAYOUT_RESULT_FAILED;

	public NCResultFailedException(int errCode, String errMsg) {
		super(errCode, errMsg);
	}

	public NCResultFailedException(int errCode, String errMsg, Throwable cause) {
		super(errCode, errMsg, cause);
	}

	public NCResultFailedException(String message) {
		super(ERR_CODE, message);
	}

	public NCResultFailedException(String message, Throwable cause) {
		super(ERR_CODE, message, cause);
	}

}
