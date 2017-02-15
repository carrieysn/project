package com.cifpay.lc.constant.enums;

/**
 * 
 */
public enum LcCurrency {

	CNY("CNY", "人民币"),

	USD("USD", "美元");

	private String code;
	private String desc;

	private LcCurrency(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public static LcCurrency parseCode(String lcCurrencyCode) {
		for (LcCurrency status : LcCurrency.values()) {
			if (status.code.equals(lcCurrencyCode)) {
				return status;
			}
		}
		throw new IllegalArgumentException("Illegal lcCurrencyCode [" + lcCurrencyCode + "]");
	}

	@Override
	public String toString() {
		return code + "-" + desc;
	}
}
