package com.cifpay.lc.domain.message.union;

import java.io.Serializable;

/**
 * Created by sweet on 16-10-27.
 */
public class CreditParam implements Serializable {
    private static final long serialVersionUID = -3958778820909153505L;

    private String resultDesc;  // 银联返回结果
    private String tradeResult; // 银联返回码
    private String txnTime; // 订单发送时间(格式YYYYMMDDhhmmss)
    private String accNo;//银联账号
    private String phoneNo;//手机号

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public String getTradeResult() {
        return tradeResult;
    }

    public void setTradeResult(String tradeResult) {
        this.tradeResult = tradeResult;
    }

    public String getTxnTime() {
        return txnTime;
    }

    public void setTxnTime(String txnTime) {
        this.txnTime = txnTime;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Override
    public String toString() {
        return "CreditParam{" +
                "resultDesc='" + resultDesc + '\'' +
                ", tradeResult='" + tradeResult + '\'' +
                ", txnTime='" + txnTime + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                '}';
    }
}
