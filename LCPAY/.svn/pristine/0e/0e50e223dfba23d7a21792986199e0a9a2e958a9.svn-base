package com.cifpay.lc.domain.security;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Securable implements Serializable {
    private static final long serialVersionUID = -2989783794411676337L;

    private String type;

    private String signKey;

    private String verifyData;

    private Map<String, String> signData;

    public Securable() {
        signData = new HashMap<String, String>();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSignKey() {
        return signKey;
    }

    public void setSignKey(String signKey) {
        this.signKey = signKey;
    }

    public String getVerifyData() {
        return verifyData;
    }

    public void setVerifyData(String verifyData) {
        this.verifyData = verifyData;
    }

    public Map<String, String> getSignData() {
        return signData;
    }

    public void setSignData(Map<String, String> signData) {
        this.signData = signData;
    }

    public void setData(String key, String val) {
        this.signData.put(key, val);
    }

}
