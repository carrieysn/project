package com.cifpay.lc.domain.sms;

import com.cifpay.lc.util.logging.LoggerEnum;
import com.cifpay.lc.util.logging.AbstractInputBean;
import com.cifpay.lc.domain.enums.AdminCardType;

/**
 * Created by sweet on 17-1-10.
 */
public class SmsSendUnionInputBean extends AbstractInputBean {
    private static final long serialVersionUID = -4834589362699900641L;

    private Long lcId;      // 银信证ID(*)

    private String phoneNo; // 手机号
    private String accNo;   // 银行卡号
    private AdminCardType cardType; // 卡类型

    public SmsSendUnionInputBean() {
        super(LoggerEnum.Scene.SENDSMS);
    }

    public Long getLcId() {
        return lcId;
    }

    public void setLcId(Long lcId) {
        this.lcId = lcId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public AdminCardType getCardType() {
        return cardType;
    }

    public void setCardType(AdminCardType cardType) {
        this.cardType = cardType;
    }
}
