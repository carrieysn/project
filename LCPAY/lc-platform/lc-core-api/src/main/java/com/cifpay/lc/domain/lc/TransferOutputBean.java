package com.cifpay.lc.domain.lc;

import com.cifpay.lc.constant.enums.PayMethod;

import java.math.BigDecimal;

public class TransferOutputBean extends AbstractLcOutputBean {

    private static final long serialVersionUID = 7433159208758154930L;

    private String orderId;         // 商户订单号
    private BigDecimal transferAmount;    // 银信证交易金额（单位分）

    private Long lcPayId;       // 开证ID
    private PayMethod payMethod;// 支付渠道
    private String serialNo;    // 交易流水号

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(BigDecimal transferAmount) {
        this.transferAmount = transferAmount;
    }

    public Long getLcPayId() {
        return lcPayId;
    }

    public void setLcPayId(Long lcPayId) {
        this.lcPayId = lcPayId;
    }

    public PayMethod getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(PayMethod payMethod) {
        this.payMethod = payMethod;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    @Override
    public String toString() {
        return "TransferOutputBean{" +
                "orderId='" + orderId + '\'' +
                ", transferAmount=" + transferAmount +
                ", lcPayId=" + lcPayId +
                ", payMethod=" + payMethod +
                ", serialNo='" + serialNo + '\'' +
                '}';
    }
}
