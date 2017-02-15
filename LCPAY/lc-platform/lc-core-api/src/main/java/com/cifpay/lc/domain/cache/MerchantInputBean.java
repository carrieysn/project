package com.cifpay.lc.domain.cache;

import java.io.Serializable;

public class MerchantInputBean extends CacheInput implements Serializable {

    private static final long serialVersionUID = -1136380270049818143L;

    private String merCode;

    public String getMerCode() {
        return merCode;
    }

    public void setMerCode(String merCode) {
        this.merCode = merCode;
    }
}
