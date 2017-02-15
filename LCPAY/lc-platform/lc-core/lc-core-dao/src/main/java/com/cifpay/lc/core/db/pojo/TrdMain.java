package com.cifpay.lc.core.db.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class TrdMain {
    private Long businessId;

    private Long lcId;

    private String tradeType;

    private String tradeBankCode;

    private Long unfreezeSerialNo;

    private String payerBankCode;

    private String payeeBankCode;

    private String accountNo;

    private String cardNo;

    private String currency;

    private BigDecimal amount;

    private String holdDate;

    private String holdType;

    private String mobile;

    private String orderId;

    private String thirdId;

    private String payeeCardNo;

    private String payeeAccountNo;

    private String branchNo;

    private String rtnSerialNo;

    private String rtnTradeDate;

    private String rtnCode;

    private String rtnMsg;

    private String rtnCreateFreezeDate;

    private Long flowId;

    private String tradeResult;

    private Integer sysReturnCode;

    private Date insertTime;

    private Date lastUpdTime;

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public Long getLcId() {
        return lcId;
    }

    public void setLcId(Long lcId) {
        this.lcId = lcId;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
    }

    public String getTradeBankCode() {
        return tradeBankCode;
    }

    public void setTradeBankCode(String tradeBankCode) {
        this.tradeBankCode = tradeBankCode == null ? null : tradeBankCode.trim();
    }

    public Long getUnfreezeSerialNo() {
        return unfreezeSerialNo;
    }

    public void setUnfreezeSerialNo(Long unfreezeSerialNo) {
        this.unfreezeSerialNo = unfreezeSerialNo;
    }

    public String getPayerBankCode() {
        return payerBankCode;
    }

    public void setPayerBankCode(String payerBankCode) {
        this.payerBankCode = payerBankCode == null ? null : payerBankCode.trim();
    }

    public String getPayeeBankCode() {
        return payeeBankCode;
    }

    public void setPayeeBankCode(String payeeBankCode) {
        this.payeeBankCode = payeeBankCode == null ? null : payeeBankCode.trim();
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo == null ? null : accountNo.trim();
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getHoldDate() {
        return holdDate;
    }

    public void setHoldDate(String holdDate) {
        this.holdDate = holdDate == null ? null : holdDate.trim();
    }

    public String getHoldType() {
        return holdType;
    }

    public void setHoldType(String holdType) {
        this.holdType = holdType == null ? null : holdType.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getThirdId() {
        return thirdId;
    }

    public void setThirdId(String thirdId) {
        this.thirdId = thirdId == null ? null : thirdId.trim();
    }

    public String getPayeeCardNo() {
        return payeeCardNo;
    }

    public void setPayeeCardNo(String payeeCardNo) {
        this.payeeCardNo = payeeCardNo == null ? null : payeeCardNo.trim();
    }

    public String getPayeeAccountNo() {
        return payeeAccountNo;
    }

    public void setPayeeAccountNo(String payeeAccountNo) {
        this.payeeAccountNo = payeeAccountNo == null ? null : payeeAccountNo.trim();
    }

    public String getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(String branchNo) {
        this.branchNo = branchNo == null ? null : branchNo.trim();
    }

    public String getRtnSerialNo() {
        return rtnSerialNo;
    }

    public void setRtnSerialNo(String rtnSerialNo) {
        this.rtnSerialNo = rtnSerialNo == null ? null : rtnSerialNo.trim();
    }

    public String getRtnTradeDate() {
        return rtnTradeDate;
    }

    public void setRtnTradeDate(String rtnTradeDate) {
        this.rtnTradeDate = rtnTradeDate == null ? null : rtnTradeDate.trim();
    }

    public String getRtnCode() {
        return rtnCode;
    }

    public void setRtnCode(String rtnCode) {
        this.rtnCode = rtnCode == null ? null : rtnCode.trim();
    }

    public String getRtnMsg() {
        return rtnMsg;
    }

    public void setRtnMsg(String rtnMsg) {
        this.rtnMsg = rtnMsg == null ? null : rtnMsg.trim();
    }

    public String getRtnCreateFreezeDate() {
        return rtnCreateFreezeDate;
    }

    public void setRtnCreateFreezeDate(String rtnCreateFreezeDate) {
        this.rtnCreateFreezeDate = rtnCreateFreezeDate == null ? null : rtnCreateFreezeDate.trim();
    }

    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }

    public String getTradeResult() {
        return tradeResult;
    }

    public void setTradeResult(String tradeResult) {
        this.tradeResult = tradeResult == null ? null : tradeResult.trim();
    }

    public Integer getSysReturnCode() {
        return sysReturnCode;
    }

    public void setSysReturnCode(Integer sysReturnCode) {
        this.sysReturnCode = sysReturnCode;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Date getLastUpdTime() {
        return lastUpdTime;
    }

    public void setLastUpdTime(Date lastUpdTime) {
        this.lastUpdTime = lastUpdTime;
    }
}