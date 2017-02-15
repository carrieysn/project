package com.cifpay.lc.domain.sms;

import java.io.Serializable;

/**
 * Created by sweet on 17-1-10.
 */
public class SmsSendUnionOutputBean implements Serializable {
    private static final long serialVersionUID = -5359536455673790661L;

    private Long lcOpenId;

    public Long getLcOpenId() {
        return lcOpenId;
    }

    public void setLcOpenId(Long lcOpenId) {
        this.lcOpenId = lcOpenId;
    }
}
