package com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.exception;

import com.cifpay.lc.constant.ReturnCode;

/**
 * NC交易通信失败时抛出此异常
 * 
 * 
 *
 */
public class NCWaitResponseTimeoutException extends NCResultUncertainException {
	private static final long serialVersionUID = -3918689224833153813L;
	private static final int ERR_CODE = ReturnCode.CORE_3RD_ICBC_BANKENT_NC_TRADE_COMMUNICATION_FAILED;

	public NCWaitResponseTimeoutException(String message) {
		super(ERR_CODE, message);
	}

	public NCWaitResponseTimeoutException(String message, Throwable cause) {
		super(ERR_CODE, message, cause);
	}

}
