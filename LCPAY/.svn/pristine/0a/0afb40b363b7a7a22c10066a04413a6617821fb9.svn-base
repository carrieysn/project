package com.cifpay.lc.constant.enums;

/**
 * 账户（包括付款账户、收款账户）类型，共有个人账户、企业账户两种。
 * 
 * 
 *
 */
public enum AccountPropertyType {
	PERSONAL("1", "个人账户"), CORPORATE("2", "企业账户");

	private String code;
	private String desc;

	private AccountPropertyType(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public static AccountPropertyType parseCode(String code) {
		switch (code) {

		case "1":
			return AccountPropertyType.PERSONAL;
		case "2":
			return AccountPropertyType.CORPORATE;

		default:
			return null;
		}
	}
}
