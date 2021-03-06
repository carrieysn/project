package com.cifpay.lc.domain.message;

import com.cifpay.lc.constant.enums.LcTranStatus;
import com.cifpay.lc.util.logging.LoggerEnum;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 失效消息
 */
public class LcExpiryParamBean extends MessageParamBean {
    private static final long serialVersionUID = 4474875530422156954L;

    private long lcId;                  //银信证Id
    private Long lcInvalidId;           //失效Id
    private BigDecimal invalidAmount;   //失效金额（分）

    private String serialNo;            //交易流水号
    private LcTranStatus lcTranStatus;  //交易处理状态
    private Date expiryTime;            //交易时间
    private String expiryDesc;          //交易结果描述

    private String lcExpiryResponse;

    public LcExpiryParamBean() {
        super(LoggerEnum.Scene.QUEUE_EXPIRY);
    }

    public long getLcId() {
        return lcId;
    }

    public void setLcId(long lcId) {
        this.lcId = lcId;
    }

    public Long getLcInvalidId() {
        return lcInvalidId;
    }

    public void setLcInvalidId(Long lcInvalidId) {
        this.lcInvalidId = lcInvalidId;
    }

    public BigDecimal getInvalidAmount() {
        return invalidAmount;
    }

    public void setInvalidAmount(BigDecimal invalidAmount) {
        this.invalidAmount = invalidAmount;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public LcTranStatus getLcTranStatus() {
        return lcTranStatus;
    }

    public void setLcTranStatus(LcTranStatus lcTranStatus) {
        this.lcTranStatus = lcTranStatus;
    }

    public Date getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
    }

    public String getLcExpiryResponse() {
        return lcExpiryResponse;
    }

    public void setLcExpiryResponse(String lcExpiryResponse) {
        this.lcExpiryResponse = lcExpiryResponse;
    }

    public String getExpiryDesc() {
        return expiryDesc;
    }

    public void setExpiryDesc(String expiryDesc) {
        this.expiryDesc = expiryDesc;
    }

    @Override
    public String toString() {
        return "LcExpiryParamBean{" +
                "lcId=" + lcId +
                ", lcInvalidId=" + lcInvalidId +
                ", invalidAmount=" + invalidAmount +
                ", serialNo='" + serialNo + '\'' +
                ", lcTranStatus=" + lcTranStatus +
                ", expiryTime=" + expiryTime +
                ", expiryDesc='" + expiryDesc + '\'' +
                ", lcExpiryResponse='" + lcExpiryResponse + '\'' +
                '}';
    }
}
