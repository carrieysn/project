package com.cifpay.lc.domain.lc;

import java.io.Serializable;

public abstract class AbstractLcOutputBean implements Serializable {

    private static final long serialVersionUID = -6809318426234381190L;

    private Long lcId;            // 银信证ID
    private String lcStatus;    // 银信证状态
    private String lcStatusDesc; // 银信证状态描述

    public Long getLcId() {
        return lcId;
    }

    public void setLcId(Long lcId) {
        this.lcId = lcId;
    }

    public String getLcStatus() {
        return lcStatus;
    }

    public void setLcStatus(String lcStatus) {
        this.lcStatus = lcStatus;
    }

    public String getLcStatusDesc() {
        return lcStatusDesc;
    }

    public void setLcStatusDesc(String lcStatusDesc) {
        this.lcStatusDesc = lcStatusDesc;
    }

    @Override
    public String toString() {
        return "AbstractLcOutputBean{" +
                "lcId=" + lcId +
                ", lcStatus='" + lcStatus + '\'' +
                ", lcStatusDesc='" + lcStatusDesc + '\'' +
                '}';
    }
}
