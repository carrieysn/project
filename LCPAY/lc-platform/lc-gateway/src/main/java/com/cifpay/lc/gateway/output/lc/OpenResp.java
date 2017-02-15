package com.cifpay.lc.gateway.output.lc;

import com.cifpay.lc.domain.security.AbstractMerchantResponse;

/**
 * Created by sweet on 16-9-21.
 */
public class OpenResp extends AbstractMerchantResponse {

    private String lcId;        // 银信证ID

    private String orderId;     // 商户订单号
    private String lcAmount;    // 银信证开证金额（单位分）

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

    public String getLcAmount() {
        return lcAmount;
    }

    public void setLcAmount(String lcAmount) {
        this.lcAmount = lcAmount;
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
