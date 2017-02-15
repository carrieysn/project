package com.cifpay.lc.exception;

public class ThirdTradePlatformAdapterException extends AbstractCoreException {
	private static final long serialVersionUID = 6095303891633543606L;
	private static final String FORMATED_RETURN_CODE_PREFIX = "3RD";

	public ThirdTradePlatformAdapterException(int returnCode, String message, Throwable cause) {
		super(returnCode, message, cause);
	}

	public ThirdTradePlatformAdapterException(int returnCode, String message) {
		super(returnCode, message);
	}

	public ThirdTradePlatformAdapterException(int returnCode, Throwable cause) {
		super(returnCode, cause);
	}

	@Override
	protected String getFormatedReturnCodePrefix() {
		return FORMATED_RETURN_CODE_PREFIX;
	}

}
