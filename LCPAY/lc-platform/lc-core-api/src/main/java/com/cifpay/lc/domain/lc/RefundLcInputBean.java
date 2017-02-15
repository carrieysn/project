package com.cifpay.lc.domain.lc;

import com.cifpay.lc.util.logging.LoggerEnum;

import java.math.BigDecimal;

public class RefundLcInputBean extends AbstractLcInputBean {

    private static final long serialVersionUID = 6177685315386046106L;

    private BigDecimal refundAmount; // 退款金额
    private String refundOrderId;    // 退款订单号

    public RefundLcInputBean() {
        super(LoggerEnum.Scene.REFUNDLC);
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getRefundOrderId() {
        return refundOrderId;
    }

    public void setRefundOrderId(String refundOrderId) {
        this.refundOrderId = refundOrderId;
    }

    @Override
    public String toString() {
        return "RefundLcInputBean{" +
                "refundAmount=" + refundAmount +
                ", refundOrderId='" + refundOrderId + '\'' +
                '}';
    }
}
