package com.cifpay.lc.constant.enums;

/**
 * 支付渠道
 */
public enum OpenChannel {
    UNKONWN("UNKONWN", "未知"),
    BANK("BANK", "银行"),
    UNION("UNION", "银联");

    private String bankCode;
    private String bankName;

    private OpenChannel(String bankCode, String bankName) {
        this.bankCode = bankCode;
        this.bankName = bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public static OpenChannel parse(String code) {
        OpenChannel channel = null;
        if (BANK.bankCode.equals(code)) {
            channel = BANK;
        } else if (UNION.bankCode.equals(code)) {
            channel = UNION;
        }

        return channel;
    }
}
