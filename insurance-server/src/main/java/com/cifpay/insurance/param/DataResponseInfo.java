/**
 * File: DataResponseInfo.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月15日 上午10:33:14
 */
package com.cifpay.insurance.param;

import java.io.Serializable;

/**
 * 带data属性的接口返回结果定义
 * 
 * @author 张均锋
 *
 */
public class DataResponseInfo extends ResponseInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 返回业务数据结果，也可能是集合。
	 */
	private Object data;
	
	public DataResponseInfo() {
		super();
	}

	public DataResponseInfo(Object data) {
		this.data = data;
	}

	public DataResponseInfo(String code, Object data) {
		this(code, null, data);
	}

	public DataResponseInfo(String code, String msg, Object data) {
		super(code, msg);
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
