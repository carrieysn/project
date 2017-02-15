package com.cifpay.lc.gateway.input.bank;

/**
 * Created by sweet on 16-10-18.
 */
public class SendVerifyCodeReq {
    private Long lcId;          // 银信证ID(*)
    private String cardType;    // 卡类型(credit, deposit)

    private String mobileNo;    // 手机号(信用卡支付时才有)

    public Long getLcId() {
        return lcId;
    }

    public void setLcId(Long lcId) {
        this.lcId = lcId;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
}
