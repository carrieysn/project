package com.cifpay.lc.std.domain.component.notify;

import com.cifpay.lc.std.domain.enums.NotifyType;

/**
 * Created by sweet on 16-11-2.
 */
public class OpenAsyncNotifyBean extends AbstractNotifyBean {
    private static final long serialVersionUID = -8699043582607278463L;

    private String lcId;        // 银信证ID
    private String lcStatus;    // 银信证状态

    private String orderId;     // 商户订单号
    private String lcAmount;    // 银信证开证金额（单位分）

    private String payTime;     // 交易时间
    private String tranStatus;  // 交易状态

    private String channel;     // 支付渠道
    private String serialNo;    // 交易流水号
    private String message;     // 交易结果描述消息

    public OpenAsyncNotifyBean() {
        super(NotifyType.OPEN);
    }

    public String getLcId() {
        return lcId;
    }

    public void setLcId(String lcId) {
        this.lcId = lcId;
    }

    public String getLcStatus() {
        return lcStatus;
    }

    public void setLcStatus(String lcStatus) {
        this.lcStatus = lcStatus;
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

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getChannel() {
        return channel;
    }

    public String getTranStatus() {
        return tranStatus;
    }

    public void setTranStatus(String tranStatus) {
        this.tranStatus = tranStatus;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "OpenAsyncNotifyBean{" +
                "lcId='" + lcId + '\'' +
                ", lcStatus='" + lcStatus + '\'' +
                ", orderId='" + orderId + '\'' +
                ", lcAmount='" + lcAmount + '\'' +
                ", payTime='" + payTime + '\'' +
                ", tranStatus='" + tranStatus + '\'' +
                ", channel='" + channel + '\'' +
                ", serialNo='" + serialNo + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
