package com.cifpay.lc.core.db.pojo;

import java.util.Date;

public class AdminCredentials {
    private String id;

    private String creName;

    private String crePath;

    private Byte creType;

    private Date createTime;

    private String userId;

    private Integer creSum;

    private String creRemark;

    private Byte creState;

    private String creFileName;

    private String crePassword;

    private byte[] creConent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCreName() {
        return creName;
    }

    public void setCreName(String creName) {
        this.creName = creName == null ? null : creName.trim();
    }

    public String getCrePath() {
        return crePath;
    }

    public void setCrePath(String crePath) {
        this.crePath = crePath == null ? null : crePath.trim();
    }

    public Byte getCreType() {
        return creType;
    }

    public void setCreType(Byte creType) {
        this.creType = creType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getCreSum() {
        return creSum;
    }

    public void setCreSum(Integer creSum) {
        this.creSum = creSum;
    }

    public String getCreRemark() {
        return creRemark;
    }

    public void setCreRemark(String creRemark) {
        this.creRemark = creRemark == null ? null : creRemark.trim();
    }

    public Byte getCreState() {
        return creState;
    }

    public void setCreState(Byte creState) {
        this.creState = creState;
    }

    public String getCreFileName() {
        return creFileName;
    }

    public void setCreFileName(String creFileName) {
        this.creFileName = creFileName == null ? null : creFileName.trim();
    }

    public String getCrePassword() {
        return crePassword;
    }

    public void setCrePassword(String crePassword) {
        this.crePassword = crePassword == null ? null : crePassword.trim();
    }

    public byte[] getCreConent() {
        return creConent;
    }

    public void setCreConent(byte[] creConent) {
        this.creConent = creConent;
    }
}