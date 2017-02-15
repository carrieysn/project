package com.cifpay.lc.exception;

public class CoreBusinessException extends AbstractCoreException {
	private static final long serialVersionUID = -7708882126654130602L;
	private static final String FORMATED_RETURN_CODE_PREFIX = "CB";

	public CoreBusinessException(int returnCode, String message, Throwable cause) {
		super(returnCode, message, cause);
	}

	public CoreBusinessException(int returnCode, String message) {
		super(returnCode, message);
	}

	public CoreBusinessException(int returnCode, Throwable cause) {
		super(returnCode, cause);
	}

	@Override
	protected String getFormatedReturnCodePrefix() {
		return FORMATED_RETURN_CODE_PREFIX;
	}

}
