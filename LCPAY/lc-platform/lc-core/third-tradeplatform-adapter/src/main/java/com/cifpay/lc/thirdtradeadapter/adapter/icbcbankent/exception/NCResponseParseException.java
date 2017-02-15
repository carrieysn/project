package com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.exception;

import com.cifpay.lc.constant.ReturnCode;

/**
 * 解析NC交易结果报文失败时抛出此异常
 * 
 * 
 *
 */
public class NCResponseParseException extends NCResultUncertainException {
	private static final long serialVersionUID = 6226259295440025149L;
	private static final int ERR_CODE = ReturnCode.CORE_3RD_ICBC_BANKENT_NC_TRADE_RESPONSE_PARSE_FAILED;

	public NCResponseParseException(String message) {
		super(ERR_CODE, message);
	}

	public NCResponseParseException(String message, Throwable cause) {
		super(ERR_CODE, message, cause);
	}

}
