package com.cifpay.lc.gateway.output.lc;

import com.cifpay.lc.domain.security.AbstractMerchantResponse;

public class TransferResp extends AbstractMerchantResponse {
    private static final long serialVersionUID = -7006772403293234589L;

    private String lcId;        // 银信证ID

    private String orderId;     // 商户订单号
    private String transferAmount;    // 银信证交易金额（单位分）

    private String lcPayId;     // 开证ID
    private String channel;     // 支付渠道
    private String serialNo;    // 交易流水号

    public String getLcId() {
        return lcId;
    }

    public void setLcId(String lcId) {
        this.lcId = lcId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(String transferAmount) {
        this.transferAmount = transferAmount;
    }

    public String getLcPayId() {
        return lcPayId;
    }

    public void setLcPayId(String lcPayId) {
        this.lcPayId = lcPayId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }
}
