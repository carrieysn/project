package com.cifpay.lc.core.db.pojo;

import java.util.Date;

public class LcRecv {
    private Long lcRecvId;

    private Long lcId;

    private String mid;

    private String recvId;

    private String recvAccno;

    private String recvType;

    private String recvBankCode;

    private String recvBankName;

    private String recvMobile;

    private Date validTime;

    private String remark;

    private Date createTime;

    private Date updateTime;

    public Long getLcRecvId() {
        return lcRecvId;
    }

    public void setLcRecvId(Long lcRecvId) {
        this.lcRecvId = lcRecvId;
    }

    public Long getLcId() {
        return lcId;
    }

    public void setLcId(Long lcId) {
        this.lcId = lcId;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid == null ? null : mid.trim();
    }

    public String getRecvId() {
        return recvId;
    }

    public void setRecvId(String recvId) {
        this.recvId = recvId == null ? null : recvId.trim();
    }

    public String getRecvAccno() {
        return recvAccno;
    }

    public void setRecvAccno(String recvAccno) {
        this.recvAccno = recvAccno == null ? null : recvAccno.trim();
    }

    public String getRecvType() {
        return recvType;
    }

    public void setRecvType(String recvType) {
        this.recvType = recvType == null ? null : recvType.trim();
    }

    public String getRecvBankCode() {
        return recvBankCode;
    }

    public void setRecvBankCode(String recvBankCode) {
        this.recvBankCode = recvBankCode == null ? null : recvBankCode.trim();
    }

    public String getRecvBankName() {
        return recvBankName;
    }

    public void setRecvBankName(String recvBankName) {
        this.recvBankName = recvBankName == null ? null : recvBankName.trim();
    }

    public String getRecvMobile() {
        return recvMobile;
    }

    public void setRecvMobile(String recvMobile) {
        this.recvMobile = recvMobile == null ? null : recvMobile.trim();
    }

    public Date getValidTime() {
        return validTime;
    }

    public void setValidTime(Date validTime) {
        this.validTime = validTime;
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