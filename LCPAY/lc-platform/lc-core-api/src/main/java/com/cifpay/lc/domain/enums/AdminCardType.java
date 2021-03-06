package com.cifpay.lc.domain.enums;

/**
 * 管理后台使用的银行卡类型
 * 0:储蓄卡，1：信用卡
 */
public enum AdminCardType {
    CREDIT(1, "信用卡"),
    DEPOSIT(0, "储蓄卡");

    private int code;
    private String desc;

    AdminCardType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
