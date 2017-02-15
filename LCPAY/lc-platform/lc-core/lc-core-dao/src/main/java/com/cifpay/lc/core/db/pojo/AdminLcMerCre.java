package com.cifpay.lc.core.db.pojo;

public class AdminLcMerCre {
    private String id;

    private String merId;

    private String merCode;

    private String xnMerId;

    private String creId;

    private Integer xnType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId == null ? null : merId.trim();
    }

    public String getMerCode() {
        return merCode;
    }

    public void setMerCode(String merCode) {
        this.merCode = merCode == null ? null : merCode.trim();
    }

    public String getXnMerId() {
        return xnMerId;
    }

    public void setXnMerId(String xnMerId) {
        this.xnMerId = xnMerId == null ? null : xnMerId.trim();
    }

    public String getCreId() {
        return creId;
    }

    public void setCreId(String creId) {
        this.creId = creId == null ? null : creId.trim();
    }

    public Integer getXnType() {
        return xnType;
    }

    public void setXnType(Integer xnType) {
        this.xnType = xnType;
    }
}