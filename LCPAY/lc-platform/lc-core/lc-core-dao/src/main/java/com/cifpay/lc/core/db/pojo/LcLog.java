package com.cifpay.lc.core.db.pojo;

import java.util.Date;

public class LcLog {
    private Long logId;

    private Long lcId;

    private Long stepLogId;

    private String tradeCode;

    private String fromStatus;

    private String toStatus;

    private String lcResponse;

    private String remark;

    private Date createTime;

    private Date updateTime;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Long getLcId() {
        return lcId;
    }

    public void setLcId(Long lcId) {
        this.lcId = lcId;
    }

    public Long getStepLogId() {
        return stepLogId;
    }

    public void setStepLogId(Long stepLogId) {
        this.stepLogId = stepLogId;
    }

    public String getTradeCode() {
        return tradeCode;
    }

    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode == null ? null : tradeCode.trim();
    }

    public String getFromStatus() {
        return fromStatus;
    }

    public void setFromStatus(String fromStatus) {
        this.fromStatus = fromStatus == null ? null : fromStatus.trim();
    }

    public String getToStatus() {
        return toStatus;
    }

    public void setToStatus(String toStatus) {
        this.toStatus = toStatus == null ? null : toStatus.trim();
    }

    public String getLcResponse() {
        return lcResponse;
    }

    public void setLcResponse(String lcResponse) {
        this.lcResponse = lcResponse == null ? null : lcResponse.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}