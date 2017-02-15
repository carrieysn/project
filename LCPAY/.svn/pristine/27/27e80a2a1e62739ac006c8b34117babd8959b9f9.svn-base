package com.cifpay.lc.core.exception;

import com.cifpay.lc.exception.AbstractCoreException;

/**
 * 核心业务层对输入进行校验，若校验不通过，则抛出此异常。
 * 
 * 
 *
 */
public class CoreValidationRejectException extends AbstractCoreException {
	private static final long serialVersionUID = 4184159042639813502L;
	public static final String FORMATED_RETURN_CODE_PREFIX = "CV";

	public CoreValidationRejectException(int returnCode, String message) {
		super(returnCode, message);
	}

	@Override
	protected String getFormatedReturnCodePrefix() {
		return FORMATED_RETURN_CODE_PREFIX;
	}

}
