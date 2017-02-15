package com.cifpay.lc.constant.enums;

/**
 * 证件类型
 * 
 * 
 */
public enum CretificationType {

	ID_CARD("1", "身份证"),

	OTHERS("2", "其他");

	private String code;
	private String desc;

	private CretificationType(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public static CretificationType parseCode(String typeCode) {
		for (CretificationType status : CretificationType.values()) {
			if (status.code.equals(typeCode)) {
				return status;
			}
		}
		throw new IllegalArgumentException("Illegal typeCode [" + typeCode + "]");
	}

	@Override
	public String toString() {
		return code + "-" + desc;
	}
}
