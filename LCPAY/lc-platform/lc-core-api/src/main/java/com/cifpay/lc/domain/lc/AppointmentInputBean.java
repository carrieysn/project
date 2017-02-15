package com.cifpay.lc.domain.lc;

import com.cifpay.lc.util.logging.LoggerEnum;

import java.math.BigDecimal;

public class AppointmentInputBean extends AbstractLcInputBean {

    private static final long serialVersionUID = -565164240250364515L;

    private String orderId;            // 商户订单号
    private BigDecimal orderAmount;    // 订单金额(*)
    private String orderContent;    // 订单内容

    private String sendSignCode;    // 履约验证码
    private String sendOrgId;        // 履约机构ID
    private String sendOrderId;        // 履约订单号

    private String remark;            // 备注

    public AppointmentInputBean() {
        super(LoggerEnum.Scene.APPOINTMENTLC);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderContent() {
        return orderContent;
    }

    public void setOrderContent(String orderContent) {
        this.orderContent = orderContent;
    }

    public String getSendSignCode() {
        return sendSignCode;
    }

    public void setSendSignCode(String sendSignCode) {
        this.sendSignCode = sendSignCode;
    }

    public String getSendOrgId() {
        return sendOrgId;
    }

    public void setSendOrgId(String sendOrgId) {
        this.sendOrgId = sendOrgId;
    }

    public String getSendOrderId() {
        return sendOrderId;
    }

    public void setSendOrderId(String sendOrderId) {
        this.sendOrderId = sendOrderId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "AppointmentInputBean{" +
                "orderId='" + orderId + '\'' +
                ", orderAmount=" + orderAmount +
                ", orderContent='" + orderContent + '\'' +
                ", sendSignCode='" + sendSignCode + '\'' +
                ", sendOrgId='" + sendOrgId + '\'' +
                ", sendOrderId='" + sendOrderId + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
