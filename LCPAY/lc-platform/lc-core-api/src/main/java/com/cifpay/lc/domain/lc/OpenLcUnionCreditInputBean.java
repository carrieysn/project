package com.cifpay.lc.domain.lc;

import com.cifpay.lc.constant.enums.PayMethod;

import java.util.Date;

/**
 * Created by sweet on 16-10-13.
 * 银联信用卡付款
 */
public class OpenLcUnionCreditInputBean extends OpenLcInputBean {
    private static final long serialVersionUID = -2290586597040278376L;

    public OpenLcUnionCreditInputBean() {
        super(PayMethod.UNION_CREDIT);
    }

    private String accNo;   // 账号
    private String cvn2;    // CVN2
    private Date expired; // 有效期(格式 YYMM)
    private String phoneNo; // 手机号
    private String smsCode; // 短信验证码

    private String certifTp;// 证件类型（可选）
    private String certifId;// 证件号码（可选）
    private String customerNm;// 姓名（可选）
//    private String reqReserved;// 请求方保留域（可选）

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public String getCertifTp() {
        return certifTp;
    }

    public void setCertifTp(String certifTp) {
        this.certifTp = certifTp;
    }

    public String getCertifId() {
        return certifId;
    }

    public void setCertifId(String certifId) {
        this.certifId = certifId;
    }

    public String getCustomerNm() {
        return customerNm;
    }

    public void setCustomerNm(String customerNm) {
        this.customerNm = customerNm;
    }

    public String getCvn2() {
        return cvn2;
    }

    public void setCvn2(String cvn2) {
        this.cvn2 = cvn2;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    @Override
    public String toString() {
        return "OpenLcUnionCreditInputBean{" +
                "phoneNo='" + phoneNo + '\'' +
                ", certifTp='" + certifTp + '\'' +
                ", certifId='" + certifId + '\'' +
                ", customerNm='" + customerNm + '\'' +
                '}';
    }
}
