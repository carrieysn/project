/**
 * File: ResponseInfo.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年11月25日 下午7:35:15
 */
package com.cifpay.insurance.param;

import java.io.Serializable;

/**
 * 接口返回结果定义
 * 
 * @author 张均锋
 *
 */
public class ResponseInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 返回代码，0为正常，其它值参考错误代码表 **/
	private String code = "0";
	/** 返回提示信息 **/
	private String msg;

	public ResponseInfo() {
	}

	public ResponseInfo(String code) {
		this(code, null);
	}

	public ResponseInfo(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
