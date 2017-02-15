package com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.exception;

import com.cifpay.lc.constant.ReturnCode;

/**
 * NC签名失败时抛出此异常
 * 
 * 
 *
 */
public class NCSignException extends NCResultFailedException {
	private static final long serialVersionUID = 6965580742036939944L;
	private static final int ERR_CODE = ReturnCode.CORE_3RD_ICBC_BANKENT_NC_SIGN_FAILED;

	public NCSignException(String message) {
		super(ERR_CODE, message);
	}

	public NCSignException(String message, Throwable cause) {
		super(ERR_CODE, message, cause);
	}
}
