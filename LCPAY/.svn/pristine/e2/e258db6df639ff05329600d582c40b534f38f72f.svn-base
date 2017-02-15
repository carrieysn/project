package com.cifpay.lc.domain.lc;

import com.cifpay.lc.constant.enums.AccountPropertyType;
import com.cifpay.lc.util.logging.LoggerEnum;

public class RecvLcInputBean extends AbstractLcInputBean {

    private static final long serialVersionUID = 4100379068773292602L;

    private String recvBankCode;        // 收款方银行代码
    private String recvBankAccountNo;    // 收款人账号
    private AccountPropertyType recvAccountType;    // 收款人类型：PERSONAL:个人 ENTERPRISE:企业
    private String recvMobile;                // 收款人手机号

    private String remark;    // 备注

    public RecvLcInputBean() {
        super(LoggerEnum.Scene.RECVLC);
    }

    public String getRecvBankCode() {
        return recvBankCode;
    }

    public void setRecvBankCode(String recvBankCode) {
        this.recvBankCode = recvBankCode;
    }

    public String getRecvBankAccountNo() {
        return recvBankAccountNo;
    }

    public void setRecvBankAccountNo(String recvBankAccountNo) {
        this.recvBankAccountNo = recvBankAccountNo;
    }

    public AccountPropertyType getRecvAccountType() {
        return recvAccountType;
    }

    public void setRecvAccountType(AccountPropertyType recvAccountType) {
        this.recvAccountType = recvAccountType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRecvMobile() {
        return recvMobile;
    }

    public void setRecvMobile(String recvMobile) {
        this.recvMobile = recvMobile;
    }

    @Override
    public String toString() {
        return "RecvLcInputBean{" +
                "recvMobile='" + recvMobile + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
