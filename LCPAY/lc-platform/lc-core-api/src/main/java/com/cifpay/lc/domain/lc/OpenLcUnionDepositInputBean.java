package com.cifpay.lc.domain.lc;

import com.cifpay.lc.constant.enums.PayMethod;

/**
 * Created by sweet on 16-10-17.
 */
public class OpenLcUnionDepositInputBean extends OpenLcInputBean {

    private static final long serialVersionUID = -8051927988277172848L;

    public OpenLcUnionDepositInputBean() {
        super(PayMethod.UNION_DEPOSIT);
    }

    private String accNo;   // 账号
    private String smsCode; // 短信验证码
    private String phoneNo; // 手机号

    private String certifTp;    // 证件类型（可选）
    private String certifId;    // 证件号码（可选）
    private String customerNm;  // 姓名（可选）

    private Long lcOpenId;      // 开证Id, 调用银联发验证码接口与支付接口的orderId要相同

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
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

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Long getLcOpenId() {
        return lcOpenId;
    }

    public void setLcOpenId(Long lcOpenId) {
        this.lcOpenId = lcOpenId;
    }

    @Override
    public String toString() {
        return "OpenLcUnionDepositInputBean{" +
                "phoneNo='" + phoneNo + '\'' +
                ", customerNm='" + customerNm + '\'' +
                ", lcOpenId=" + lcOpenId +
                '}';
    }
}
