package com.cifpay.lc.gateway.input.lc;

import java.math.BigDecimal;

public class InitReq {

    private String productCode; // 银信证产品代码
    private String orderId;     // 商户订单号
    private String orderContent;// 商户订单信息
    private String merUserId;   // 商户用户标识, 新增字段:2016/10/17

    private BigDecimal amount;  // 银信证开证金额
    private String currency;    // 银信证币种
    private String channel;     // 支付渠道

    private int openValidSecond;    // 开证失效时间（单位秒）
    private int recvValidSecond;    // 收证失效时间（单位秒）
    private int sendValidSecond;    // 履约失效时间（单位秒）
    private int confirmValidSecond; // 申请解付失效时间（单位秒）

    private String payerAccountType;// 付款人类型：c:个人 b:企业
    private String payerBankCode;   // 付款方银行代码
    private String payerBankAccountNo;// 付款人账号
    private String payerMobile;     //付款人手机号

    private String returnUrl;       // 返回地址
    private String noticeUrl;       // 通知地址
    private String merOrderUrl;     // 商户订单详情地址

    private String remark;          // 备注

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getRecvValidSecond() {
        return recvValidSecond;
    }

    public void setRecvValidSecond(int recvValidSecond) {
        this.recvValidSecond = recvValidSecond;
    }

    public int getSendValidSecond() {
        return sendValidSecond;
    }

    public void setSendValidSecond(int sendValidSecond) {
        this.sendValidSecond = sendValidSecond;
    }

    public int getConfirmValidSecond() {
        return confirmValidSecond;
    }

    public void setConfirmValidSecond(int confirmValidSecond) {
        this.confirmValidSecond = confirmValidSecond;
    }


    public String getPayerBankCode() {
        return payerBankCode;
    }

    public void setPayerBankCode(String payerBankCode) {
        this.payerBankCode = payerBankCode;
    }

    public String getPayerBankAccountNo() {
        return payerBankAccountNo;
    }

    public void setPayerBankAccountNo(String payerBankAccountNo) {
        this.payerBankAccountNo = payerBankAccountNo;
    }

    public String getPayerAccountType() {
        return payerAccountType;
    }

    public void setPayerAccountType(String payerAccountType) {
        this.payerAccountType = payerAccountType;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getNoticeUrl() {
        return noticeUrl;
    }

    public void setNoticeUrl(String noticeUrl) {
        this.noticeUrl = noticeUrl;
    }

    public String getMerOrderUrl() {
        return merOrderUrl;
    }

    public void setMerOrderUrl(String merOrderUrl) {
        this.merOrderUrl = merOrderUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getPayerMobile() {
        return payerMobile;
    }

    public void setPayerMobile(String payerMobile) {
        this.payerMobile = payerMobile;
    }

    public String getOrderContent() {
        return orderContent;
    }

    public void setOrderContent(String orderContent) {
        this.orderContent = orderContent;
    }

    public int getOpenValidSecond() {
        return openValidSecond;
    }

    public void setOpenValidSecond(int openValidSecond) {
        this.openValidSecond = openValidSecond;
    }

    public String getMerUserId() {
        return merUserId;
    }

    public void setMerUserId(String merUserId) {
        this.merUserId = merUserId;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("InitReq [productCode=");
        builder.append(productCode);
        builder.append(", orderId=");
        builder.append(orderId);
        builder.append(", orderContent=");
        builder.append(orderContent);
        builder.append(", amount=");
        builder.append(amount);
        builder.append(", currency=");
        builder.append(currency);
        builder.append(", channel=");
        builder.append(channel);
        builder.append(", openValidSecond=");
        builder.append(openValidSecond);
        builder.append(", recvValidSecond=");
        builder.append(recvValidSecond);
        builder.append(", sendValidSecond=");
        builder.append(sendValidSecond);
        builder.append(", confirmValidSecond=");
        builder.append(confirmValidSecond);
        builder.append(", payerAccountType=");
        builder.append(payerAccountType);
        builder.append(", payerBankCode=");
        builder.append(payerBankCode);
        builder.append(", payerBankAccountNo=");
        builder.append(payerBankAccountNo);
        builder.append(", payerMobile=");
        builder.append(payerMobile);
        builder.append(", returnUrl=");
        builder.append(returnUrl);
        builder.append(", noticeUrl=");
        builder.append(noticeUrl);
        builder.append(", merOrderUrl=");
        builder.append(merOrderUrl);
        builder.append(", remark=");
        builder.append(remark);
        builder.append(", merUserId=");
        builder.append(merUserId);
        builder.append("]");
        return builder.toString();
    }
}
