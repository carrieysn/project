package com.cifpay.lc.thirdtradeadapter.api.constant;

/**
 * 
 *
 */
public enum ICBCAccountPropType {

	PERSONAL_ACCOUNT("1", "个人账户"),

	CORPRATE_ACCOUNT("0", "对公账户");

	private String code;
	private String desc;

	private ICBCAccountPropType(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public static ICBCAccountPropType parseAccountPropType(String accountPropType) {
		for (ICBCAccountPropType status : ICBCAccountPropType.values()) {
			if (status.code.equals(accountPropType)) {
				return status;
			}
		}
		throw new IllegalArgumentException("Illegal accountPropType [" + accountPropType + "]");
	}

	@Override
	public String toString() {
		return code + "-" + desc;
	}
}
