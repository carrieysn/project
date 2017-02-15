package com.cifpay.lc.core.db.pojo;

import java.util.Date;

public class TrdUnionPayFlow {
    private Long flowId;

    private Long businessId;

    private Long lcId;

    private String txnType;

    private String txnSubType;

    private String bizType;

    private String merId;

    private String subMerId;

    private String orderId;

    private String origFlowId;//原始交易流水号

    private String txnTime;

    private Long txnAmt;

    private String currencyCode;

    private String newFlowId;//银联交易流水号（返回结果）

    private String traceNo;

    private String traceTime;

    private String asynRespCode;

    private String asynRespMsg;

    private String syncRespCode;

    private String syncRespMsg;

    private Long settleAmt;

    private String settleCurrencyCode;

    private String settleDate;

    private String syncTradeResult;

    private String asynTradeResult;

    private Date insertTime;

    private Date lastUpdTime;

    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }

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

    public String getTxnType() {
        return txnType;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType == null ? null : txnType.trim();
    }

    public String getTxnSubType() {
        return txnSubType;
    }

    public void setTxnSubType(String txnSubType) {
        this.txnSubType = txnSubType == null ? null : txnSubType.trim();
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType == null ? null : bizType.trim();
    }

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId == null ? null : merId.trim();
    }

    public String getSubMerId() {
        return subMerId;
    }

    public void setSubMerId(String subMerId) {
        this.subMerId = subMerId == null ? null : subMerId.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getOrigFlowId() {
        return origFlowId;
    }

    public void setOrigFlowId(String origFlowId) {
        this.origFlowId = origFlowId == null ? null : origFlowId.trim();
    }

    public String getTxnTime() {
        return txnTime;
    }

    public void setTxnTime(String txnTime) {
        this.txnTime = txnTime == null ? null : txnTime.trim();
    }

    public Long getTxnAmt() {
        return txnAmt;
    }

    public void setTxnAmt(Long txnAmt) {
        this.txnAmt = txnAmt;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode == null ? null : currencyCode.trim();
    }

    public String getNewFlowId() {
        return newFlowId;
    }

    public void setNewFlowId(String newFlowId) {
        this.newFlowId = newFlowId == null ? null : newFlowId.trim();
    }

    public String getTraceNo() {
        return traceNo;
    }

    public void setTraceNo(String traceNo) {
        this.traceNo = traceNo == null ? null : traceNo.trim();
    }

    public String getTraceTime() {
        return traceTime;
    }

    public void setTraceTime(String traceTime) {
        this.traceTime = traceTime == null ? null : traceTime.trim();
    }

    public String getAsynRespCode() {
        return asynRespCode;
    }

    public void setAsynRespCode(String asynRespCode) {
        this.asynRespCode = asynRespCode == null ? null : asynRespCode.trim();
    }

    public String getAsynRespMsg() {
        return asynRespMsg;
    }

    public void setAsynRespMsg(String asynRespMsg) {
        this.asynRespMsg = asynRespMsg == null ? null : asynRespMsg.trim();
    }

    public String getSyncRespCode() {
        return syncRespCode;
    }

    public void setSyncRespCode(String syncRespCode) {
        this.syncRespCode = syncRespCode == null ? null : syncRespCode.trim();
    }

    public String getSyncRespMsg() {
        return syncRespMsg;
    }

    public void setSyncRespMsg(String syncRespMsg) {
        this.syncRespMsg = syncRespMsg == null ? null : syncRespMsg.trim();
    }

    public Long getSettleAmt() {
        return settleAmt;
    }

    public void setSettleAmt(Long settleAmt) {
        this.settleAmt = settleAmt;
    }

    public String getSettleCurrencyCode() {
        return settleCurrencyCode;
    }

    public void setSettleCurrencyCode(String settleCurrencyCode) {
        this.settleCurrencyCode = settleCurrencyCode == null ? null : settleCurrencyCode.trim();
    }

    public String getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate == null ? null : settleDate.trim();
    }

    public String getSyncTradeResult() {
        return syncTradeResult;
    }

    public void setSyncTradeResult(String syncTradeResult) {
        this.syncTradeResult = syncTradeResult == null ? null : syncTradeResult.trim();
    }

    public String getAsynTradeResult() {
        return asynTradeResult;
    }

    public void setAsynTradeResult(String asynTradeResult) {
        this.asynTradeResult = asynTradeResult == null ? null : asynTradeResult.trim();
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