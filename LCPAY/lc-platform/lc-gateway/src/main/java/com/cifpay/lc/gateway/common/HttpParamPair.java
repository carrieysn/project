package com.cifpay.lc.gateway.common;

import java.io.Serializable;

/**
 * 表示一个具有name和value的HTTP参数
 * 
 * 
 *
 */
public class HttpParamPair implements Serializable {
	private static final long serialVersionUID = 4954082907965122527L;
	private String name;
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
