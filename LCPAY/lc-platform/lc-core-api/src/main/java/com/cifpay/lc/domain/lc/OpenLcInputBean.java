package com.cifpay.lc.domain.lc;

import com.cifpay.lc.util.logging.LoggerEnum;
import com.cifpay.lc.constant.enums.PayMethod;

public class OpenLcInputBean extends AbstractLcInputBean {

    private static final long serialVersionUID = 4100379068773292602L;

    private String phoneNo; // 手机号
    private String smsCode; // 短信验证码
    private String remark;  // 备注
    private PayMethod payMethod;

    public OpenLcInputBean() {
        super(LoggerEnum.Scene.OPENLC);
        this.payMethod = PayMethod.NORMAL;
    }

    public OpenLcInputBean(PayMethod payMethod) {
        super(LoggerEnum.Scene.OPENLC);
        this.payMethod = payMethod;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public PayMethod getPayMethod() {
        return payMethod;
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
        StringBuilder builder = new StringBuilder();
        builder.append("OpenLcInputBean [phoneNo=");
        builder.append(phoneNo);
        builder.append(", smsCode=");
        builder.append(smsCode);
        builder.append(", remark=");
        builder.append(remark);
        builder.append(", payMethod=");
        builder.append(payMethod);
        builder.append("]");
        return builder.toString();
    }
}
