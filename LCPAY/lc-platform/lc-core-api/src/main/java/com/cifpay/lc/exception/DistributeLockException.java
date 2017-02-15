package com.cifpay.lc.exception;

public class DistributeLockException extends AbstractCoreException {
	
	private static final long serialVersionUID = -5712873792564410301L;
	
	public static final String FORMATED_RETURN_CODE_PREFIX = "DL";

	public DistributeLockException(int returnCode, String message) {
		super(returnCode, message);
	}

	@Override
	protected String getFormatedReturnCodePrefix() {
		return FORMATED_RETURN_CODE_PREFIX;
	}
}
