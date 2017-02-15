package com.cifpay.lc.api.xds.chinapay;

import com.cifpay.lc.util.logging.LoggerEnum;
import com.cifpay.lc.util.logging.AbstractInputBean;

/**
 * Created by yx on 2016/5/3.
 */
public class UnionpayBusinessInputBean extends AbstractInputBean {
    private static final long serialVersionUID = -2118801089207527738L;

    private Long lcId;
    private String tradeTime;
    private String aliAcctNo;
    private String aliTradeNo;

    /**
     * 是否需要通知商户
     */
    private boolean needSendNoticeToMerchant;
    /**
     * 是否需要通知账务中心
     */
    private boolean needSendNoticeToAccCenter;

    public UnionpayBusinessInputBean(LoggerEnum.Scene scene) {
        super(scene);
    }


    public Long getLcId() {
        return lcId;
    }

    public void setLcId(Long lcId) {
        this.lcId = lcId;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getAliAcctNo() {
        return aliAcctNo;
    }

    public void setAliAcctNo(String aliAcctNo) {
        this.aliAcctNo = aliAcctNo;
    }

    public String getAliTradeNo() {
        return aliTradeNo;
    }

    public void setAliTradeNo(String aliTradeNo) {
        this.aliTradeNo = aliTradeNo;
    }

    public boolean getNeedSendNoticeToMerchant() {
        return needSendNoticeToMerchant;
    }

    public void setNeedSendNoticeToMerchant(boolean needSendNoticeToMerchant) {
        this.needSendNoticeToMerchant = needSendNoticeToMerchant;
    }

    public boolean getNeedSendNoticeToAccCenter() {
        return needSendNoticeToAccCenter;
    }

    public void setNeedSendNoticeToAccCenter(boolean needSendNoticeToAccCenter) {
        this.needSendNoticeToAccCenter = needSendNoticeToAccCenter;
    }

}
