package com.cifpay.lc.constant.enums;

/**
 * Created by sweet on 16-11-9.
 */
public enum PayHandler {

    FREEZE("FREEZE", "冻结"),
    UNFREEZE("UNFREEZE", "解冻"),
    Expiry("Expiry", "失效"),
    Refund("Refund", "退款");

    private String code;
    private String desc;

    private PayHandler(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static PayHandler parse(String code) {
        PayHandler handler = null;
        if (FREEZE.code.equals(code)) {
            handler = FREEZE;
        } else if (UNFREEZE.code.equals(code)) {
            handler = UNFREEZE;
        }

        return handler;
    }
}
