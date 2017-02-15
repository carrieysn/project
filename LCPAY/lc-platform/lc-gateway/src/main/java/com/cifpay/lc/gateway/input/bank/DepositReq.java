package com.cifpay.lc.gateway.input.bank;

public class DepositReq {
    private Long lcId; // 银信证ID(*)

    //    private String mobileNo; // 手机号
//    private String accountNo; // 银联卡号
    private String smsCode;     // 短信验证码

    public Long getLcId() {
        return lcId;
    }

    public void setLcId(Long lcId) {
        this.lcId = lcId;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DepositReq [lcId=");
        builder.append(lcId);
        builder.append(", smsCode=");
        builder.append(smsCode);
        builder.append("]");
        return builder.toString();
    }
}
