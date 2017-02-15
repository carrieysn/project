package com.cifpay.lc.constant.enums;

/**
 * 银信证的解付类型
 * 
 * 
 *
 */
public enum LcPayType {
	SINGLE_PAY("SINGLE", "单次解付"), 
	MULTIPAY_PAY_WITH_SAME_PAYEE("MULTIPLE", "同收款人多次解付");

	private String code;
	private String desc;

	private LcPayType(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public static LcPayType parseCode(String code) {
		switch (code) {

		case "SINGLE":
			return LcPayType.SINGLE_PAY;
		case "MULTIPLE":
			return LcPayType.MULTIPAY_PAY_WITH_SAME_PAYEE;

		default:
			return null;
		}
	}
}
