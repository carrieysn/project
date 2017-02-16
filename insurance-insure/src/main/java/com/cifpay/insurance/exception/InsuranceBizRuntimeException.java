/**
 * File: InsuranceBizRuntimeException.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年11月26日 上午9:33:14
 */
package com.cifpay.insurance.exception;

import com.cifpay.starframework.exception.CifpaySystemException;

/**
 * 交还险业务运行时异常
 * 
 * @author 张均锋
 *
 */
public class InsuranceBizRuntimeException extends CifpaySystemException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InsuranceBizRuntimeException(String message) {
		super(-1, message);// 规避基类默认值
	}

	/**
	 * 整型的code
	 * 
	 * @param intCode
	 *            整数
	 * @param message
	 */
	public InsuranceBizRuntimeException(String intCode, String message) {
		super(Integer.parseInt(intCode), message);
	}

	/**
	 * 整型的code
	 * 
	 * @param intCode
	 *            整数
	 * @param message
	 * @param root
	 */
	public InsuranceBizRuntimeException(String intCode, String message, Throwable root) {
		super(Integer.parseInt(intCode), message, root);
	}
}
