package com.cifpay.lc.constant.enums;

/**
 * 1=撤回失效、2=退回失效、3=强制失效（我方客服操作）、4=到期失效
 *
 * @author sweet
 *
 */
public enum LcInvalidateType {
	WITHDRAW("1", "撤回失效"),
	SENDBACK("2", "退回失效"),
	FORCE("3", "强制失效"),
	MULTIPLE_INVALID("4", "多次履约失效");

	private String code;
	private String desc;

	private LcInvalidateType(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public static LcInvalidateType parseCode(String code) {
		switch (code) {

		case "1":
			return LcInvalidateType.WITHDRAW;
		case "2":
			return LcInvalidateType.SENDBACK;
		case "3":
			return LcInvalidateType.FORCE;
		case "4":
			return LcInvalidateType.MULTIPLE_INVALID;

		default:
			return null;
		}
	}
}
