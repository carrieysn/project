package com.cifpay.lc.bankadapter.api.input;

import com.cifpay.lc.util.logging.AbstractInputBean;
import com.cifpay.lc.util.logging.LoggerEnum;

public abstract class AbsTradeParam extends AbstractInputBean {

    private Long LcId;// 银信证ID

    private String txnId;// 交易ID

    private String bizType;// 业务类型

    public AbsTradeParam(LoggerEnum.Scene scene) {
        super(scene);
    }

    public Long getLcId() {
        return LcId;
    }

    public void setLcId(Long lcId) {
        LcId = lcId;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

}
