package com.cifpay.lc.domain.lc;

import com.cifpay.lc.util.logging.LoggerEnum;
import com.cifpay.lc.util.logging.AbstractInputBean;

import java.io.Serializable;

public abstract class AbstractLcInputBean extends AbstractInputBean implements Serializable {

    private static final long serialVersionUID = -4735987719192372843L;

    private Long lcId;      // 银信证ID(*)
    private String merId;   // 商户号

    public AbstractLcInputBean(LoggerEnum.Scene scene) {
        super(scene);
    }

    public Long getLcId() {
        return lcId;
    }

    public void setLcId(Long lcId) {
        this.lcId = lcId;
    }

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

    @Override
    public String toString() {
        return "AbstractLcInputBean{" +
                "lcId=" + lcId +
                ", merId='" + merId + '\'' +
                '}';
    }
}
