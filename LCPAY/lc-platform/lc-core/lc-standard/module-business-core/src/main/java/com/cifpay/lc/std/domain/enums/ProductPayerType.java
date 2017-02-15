package com.cifpay.lc.std.domain.enums;

import com.cifpay.lc.constant.enums.AccountPropertyType;

public enum ProductPayerType {
	PERSONAL("10", "个人"), 
	CORPORATE("20", "企业"),
	ALL("30","全部");

	private String code;
	private String desc;

	private ProductPayerType(String code, String desc) {
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
