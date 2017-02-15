package com.cifpay.lc.thirdtradeadapter.api.constant;

/**
 * 
 *
 */
public enum ICBCBankEntCurrencyType {

	CNY("001", "人民币");

	private String code;
	private String desc;

	private ICBCBankEntCurrencyType(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public static ICBCBankEntCurrencyType parseCurrencyTypeCode(String currentTypeCode) {
		for (ICBCBankEntCurrencyType status : ICBCBankEntCurrencyType.values()) {
			if (status.code.equals(currentTypeCode)) {
				return status;
			}
		}
		throw new IllegalArgumentException("Illegal currency tpye code [" + currentTypeCode + "]");
	}

	@Override
	public String toString() {
		return code + "-" + desc;
	}
}
