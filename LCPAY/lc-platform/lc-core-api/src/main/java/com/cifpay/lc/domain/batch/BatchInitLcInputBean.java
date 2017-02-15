package com.cifpay.lc.domain.batch;

import java.util.List;

import com.cifpay.lc.constant.enums.AccountPropertyType;
import com.cifpay.lc.util.logging.LoggerEnum;

public class BatchInitLcInputBean extends AbstractBatchInputBean {

    private static final long serialVersionUID = -8626850354538935598L;

    private String merId; // 商户号
    private List<BatchInitLcInputDto> lcList; // 银信证参数
    private String returnUrl;        // 返回地址
    private String noticeUrl;        // 通知地址

    private String payerBankCode;        // 付款方银行代码
    private String payerBankAccountNo;    // 付款人账号
    private AccountPropertyType payerAccountType; // 付款人类型：1:个人 2:企业
    private String payerMobile;        // 付款人手机号

    private String remark;

    public BatchInitLcInputBean() {
        super(LoggerEnum.Scene.BATCHINITLC);
    }

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public List<BatchInitLcInputDto> getLcList() {
        return lcList;
    }

    public void setLcList(List<BatchInitLcInputDto> lcList) {
        this.lcList = lcList;
    }

    public String getPayerBankCode() {
        return payerBankCode;
    }

    public void setPayerBankCode(String payerBankCode) {
        this.payerBankCode = payerBankCode;
    }

    public String getPayerBankAccountNo() {
        return payerBankAccountNo;
    }

    public void setPayerBankAccountNo(String payerBankAccountNo) {
        this.payerBankAccountNo = payerBankAccountNo;
    }

    public AccountPropertyType getPayerAccountType() {
        return payerAccountType;
    }

    public void setPayerAccountType(AccountPropertyType payerAccountType) {
        this.payerAccountType = payerAccountType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPayerMobile() {
        return payerMobile;
    }

    public void setPayerMobile(String payerMobile) {
        this.payerMobile = payerMobile;
    }

    public String getNoticeUrl() {
        return noticeUrl;
    }

    public void setNoticeUrl(String noticeUrl) {
        this.noticeUrl = noticeUrl;
    }
}
