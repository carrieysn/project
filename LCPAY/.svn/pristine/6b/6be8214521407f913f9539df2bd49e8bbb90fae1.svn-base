package com.cifpay.lc.thirdtradeadapter.api.constant;

/**
 * 
 *
 */
public enum ICBCBankTransactionResultStatus {
	NEW("N", "新交易待处理"),

	BANK_SUCCESS("S", "银行交易成功"),

	BANK_FAILED("F", "银行交易失败"),

	BANK_PROCESSING("P", "银行处理中"),

	RESULT_UNCERTAIN("U", "交易状态暂时不明确");

	private String code;
	private String desc;

	private ICBCBankTransactionResultStatus(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public static ICBCBankTransactionResultStatus parseTxnStatus(String resultStatusCode) {
		for (ICBCBankTransactionResultStatus status : ICBCBankTransactionResultStatus.values()) {
			if (status.code.equals(resultStatusCode)) {
				return status;
			}
		}
		throw new IllegalArgumentException("Illegal resultStatusCode [" + resultStatusCode + "]");
	}

	@Override
	public String toString() {
		return code + "-" + desc;
	}
}
