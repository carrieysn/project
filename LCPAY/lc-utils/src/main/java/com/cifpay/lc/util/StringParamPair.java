package com.cifpay.lc.util;

import java.io.Serializable;

/**
 * 
 *
 */
public class StringParamPair implements Serializable {
	private static final long serialVersionUID = 3678215137218910509L;
	private String paramName;
	private String paramValue;

	public StringParamPair() {
		super();
	}

	public StringParamPair(String paramName, String paramValue) {
		super();
		this.paramName = paramName;
		this.paramValue = paramValue;
	}

	public String getParamValueNotNull() {
		if (null != paramValue) {
			return paramValue;
		} else {
			return "";
		}
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

}
