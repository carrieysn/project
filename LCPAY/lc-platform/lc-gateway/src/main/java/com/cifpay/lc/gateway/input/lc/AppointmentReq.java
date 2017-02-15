package com.cifpay.lc.gateway.input.lc;

import java.io.Serializable;
import java.math.BigDecimal;

public class AppointmentReq implements Serializable {

    private static final long serialVersionUID = -3035777267485668722L;

    private long lcId;          // 银信证ID
    private BigDecimal amount;  // 履约金额

    private String orderId;     // 商户订单号
    private String orderContent;// 订单内容

    private String thirdPartyCode;      // 第三方机构号
    private String thirdPartyOrderId;   // 第三方订单号
    private String sendEvidence;        // 履约凭证

    private String remark;      // 备注

    public long getLcId() {
        return lcId;
    }

    public void setLcId(long lcId) {
        this.lcId = lcId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderContent() {
        return orderContent;
    }

    public void setOrderContent(String orderContent) {
        this.orderContent = orderContent;
    }

    public String getThirdPartyCode() {
        return thirdPartyCode;
    }

    public void setThirdPartyCode(String thirdPartyCode) {
        this.thirdPartyCode = thirdPartyCode;
    }

    public String getThirdPartyOrderId() {
        return thirdPartyOrderId;
    }

    public void setThirdPartyOrderId(String thirdPartyOrderId) {
        this.thirdPartyOrderId = thirdPartyOrderId;
    }

    public String getSendEvidence() {
        return sendEvidence;
    }

    public void setSendEvidence(String sendEvidence) {
        this.sendEvidence = sendEvidence;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
