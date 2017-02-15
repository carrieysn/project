package com.cifpay.lc.core.db.pojo;

import java.util.Date;

public class TrdLock {
    private Long lcId;

    private Date insertTime;

    public Long getLcId() {
        return lcId;
    }

    public void setLcId(Long lcId) {
        this.lcId = lcId;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
}