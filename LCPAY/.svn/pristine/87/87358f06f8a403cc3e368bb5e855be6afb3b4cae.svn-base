package com.cifpay.lc.domain.message;

import com.cifpay.lc.constant.enums.LcTranStatus;
import com.cifpay.lc.util.logging.LoggerEnum;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 解冻消息
 */
public class LcRefundParamBean extends MessageParamBean {
    private static final long serialVersionUID = -6570188442684420782L;

    private long lcId;                  // 银信证Id
    private Long lcRefundId;
    private BigDecimal refundAmount;    //失效金额（分）

    private String serialNo;            // 交易流水号
    private LcTranStatus lcTranStatus;  // 交易处理状态
    private Date refundTime;            // 交易时间
    private String refundDesc;          // 交易结果描述

    private String lcTransferResponse;

    public LcRefundParamBean() {
        super(LoggerEnum.Scene.QUEUE_REFUND);
    }

    public long getLcId() {
        return lcId;
    }

    public void setLcId(long lcId) {
        this.lcId = lcId;
    }

    public Long getLcRefundId() {
        return lcRefundId;
    }

    public void setLcRefundId(Long lcPayId) {
        this.lcRefundId = lcPayId;
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

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getLcTransferResponse() {
        return lcTransferResponse;
    }

    public void setLcTransferResponse(String lcTransferResponse) {
        this.lcTransferResponse = lcTransferResponse;
    }

    public String getRefundDesc() {
        return refundDesc;
    }

    public void setRefundDesc(String refundDesc) {
        this.refundDesc = refundDesc;
    }

    @Override
    public String toString() {
        return "LcRefundParamBean{" +
                "lcId=" + lcId +
                ", lcRefundId=" + lcRefundId +
                ", refundAmount=" + refundAmount +
                ", serialNo='" + serialNo + '\'' +
                ", lcTranStatus=" + lcTranStatus +
                ", refundTime=" + refundTime +
                ", refundDesc='" + refundDesc + '\'' +
                ", lcTransferResponse='" + lcTransferResponse + '\'' +
                '}';
    }
}
