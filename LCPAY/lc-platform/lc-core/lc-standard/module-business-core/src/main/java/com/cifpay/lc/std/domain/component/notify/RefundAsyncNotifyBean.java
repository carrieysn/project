package com.cifpay.lc.std.domain.component.notify;

import com.cifpay.lc.std.domain.enums.NotifyType;

/**
 * Created by sweet on 16-11-2.
 */
public class RefundAsyncNotifyBean extends AbstractNotifyBean {
    private static final long serialVersionUID = -8699043582607278463L;

    private String lcId;        // 银信证ID
    private String lcStatus;    // 银信证状态

    private String orderId;     // 商户订单号
    private String lcAmount;    // 银信证开证金额（单位分）
    private String refundAmount;// 银信证退款金额（单位分）
    private String refundTime;  // 退款时间

    private String tranStatus;  // 交易状态
    private String serialNo;    // 交易流水号
    private String message;     // 交易结果描述消息

    public RefundAsyncNotifyBean() {
        super(NotifyType.REFUND);
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

    public String getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(String refundAmount) {
        this.refundAmount = refundAmount;
    }

    public void setLcAmount(String lcAmount) {
        this.lcAmount = lcAmount;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(String refundTime) {
        this.refundTime = refundTime;
    }

    public String getTranStatus() {
        return tranStatus;
    }

    public void setTranStatus(String tranStatus) {
        this.tranStatus = tranStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "RefundAsyncNotifyBean{" +
                "lcId='" + lcId + '\'' +
                ", lcStatus='" + lcStatus + '\'' +
                ", orderId='" + orderId + '\'' +
                ", lcAmount='" + lcAmount + '\'' +
                ", refundAmount='" + refundAmount + '\'' +
                ", refundTime='" + refundTime + '\'' +
                ", tranStatus='" + tranStatus + '\'' +
                ", serialNo='" + serialNo + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
