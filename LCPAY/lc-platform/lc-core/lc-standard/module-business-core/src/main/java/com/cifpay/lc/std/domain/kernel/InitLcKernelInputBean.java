package com.cifpay.lc.std.domain.kernel;

import java.io.Serializable;
import java.math.BigDecimal;

import com.cifpay.lc.constant.enums.LcPayType;
import com.cifpay.lc.constant.enums.AccountPropertyType;
import com.cifpay.lc.constant.enums.OpenChannel;

public class InitLcKernelInputBean implements Serializable {

    private static final long serialVersionUID = 2059052393073325635L;

    private String batchOpenId;    // 批量开证ID

    private String merId;            // 商户号(*)
    private String productCode;    // 银信证产品代码(*)
    private String orderId;        // 商户订单号
    private String orderContent;    // 商户订单信息
    private String merUserId;       // 商户用户标识

    private BigDecimal amount;        // 银信证开证金额(*)
    private String currency;        // 银信证币种(*)
    private LcPayType payType;        // 解付方式：SINGLE:单次解付 MULTIPLE:多次解付(*)
    private OpenChannel openChannel;// 支付渠道

    private int openValidSecond;    // 开证失效时间（单位秒）
    private int recvValidSecond;    // 收证失效时间（单位秒）
    private int sendValidSecond;    // 履约失效时间（单位秒）
    private int confirmValidSecond; // 申请解付失效时间（单位秒）
    private int payValidSecond;    // 执行解付到期时间（单位秒）

    private String payerBankCode;        // 付款方银行代码
    private String payerBankAccountNo;    // 付款人账号
    private AccountPropertyType payerAccountType; // 付款人类型：1:个人 2:企业
    private String payerMobile;        // 付款人手机号

    private String recvBankCode;        // 收款方银行代码
    private String recvBankAccountNo;    // 收款人账号
    private AccountPropertyType recvAccountType; // 收款人类型：1:个人 2:企业
    private String recvMobile;            // 收款人手机号

    private String returnUrl;    // 返回地址
    private String noticeUrl;    // 通知地址
    private String mrchOrderUrl;// 商户订单详情地址

    private String remark;        // 备注

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

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

    public String getRecvBankCode() {
        return recvBankCode;
    }

    public void setRecvBankCode(String recvBankCode) {
        this.recvBankCode = recvBankCode;
    }

    public String getRecvBankAccountNo() {
        return recvBankAccountNo;
    }

    public void setRecvBankAccountNo(String recvBankAccountNo) {
        this.recvBankAccountNo = recvBankAccountNo;
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

    public String getMrchOrderUrl() {
        return mrchOrderUrl;
    }

    public void setMrchOrderUrl(String mrchOrderUrl) {
        this.mrchOrderUrl = mrchOrderUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public AccountPropertyType getPayerAccountType() {
        return payerAccountType;
    }

    public void setPayerAccountType(AccountPropertyType payerAccountType) {
        this.payerAccountType = payerAccountType;
    }

    public AccountPropertyType getRecvAccountType() {
        return recvAccountType;
    }

    public void setRecvAccountType(AccountPropertyType recvAccountType) {
        this.recvAccountType = recvAccountType;
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

    public int getPayValidSecond() {
        return payValidSecond;
    }

    public void setPayValidSecond(int payValidSecond) {
        this.payValidSecond = payValidSecond;
    }

    public LcPayType getPayType() {
        return payType;
    }

    public void setPayType(LcPayType payType) {
        this.payType = payType;
    }

    public OpenChannel getOpenChannel() {
        return openChannel;
    }

    public void setOpenChannel(OpenChannel openChannel) {
        this.openChannel = openChannel;
    }

    public String getPayerMobile() {
        return payerMobile;
    }

    public void setPayerMobile(String payerMobile) {
        this.payerMobile = payerMobile;
    }

    public String getRecvMobile() {
        return recvMobile;
    }

    public void setRecvMobile(String recvMobile) {
        this.recvMobile = recvMobile;
    }

    public String getBatchOpenId() {
        return batchOpenId;
    }

    public void setBatchOpenId(String batchOpenId) {
        this.batchOpenId = batchOpenId;
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
        builder.append("InitLcKernelInputBean [batchOpenId=");
        builder.append(batchOpenId);
        builder.append(", merId=");
        builder.append(merId);
        builder.append(", productCode=");
        builder.append(productCode);
        builder.append(", orderId=");
        builder.append(orderId);
        builder.append(", orderContent=");
        builder.append(orderContent);
        builder.append(", merUserId=");
        builder.append(merUserId);
        builder.append(", amount=");
        builder.append(amount);
        builder.append(", currency=");
        builder.append(currency);
        builder.append(", payType=");
        builder.append(payType);
        builder.append(", openValidSecond=");
        builder.append(openValidSecond);
        builder.append(", recvValidSecond=");
        builder.append(recvValidSecond);
        builder.append(", sendValidSecond=");
        builder.append(sendValidSecond);
        builder.append(", confirmValidSecond=");
        builder.append(confirmValidSecond);
        builder.append(", payValidSecond=");
        builder.append(payValidSecond);
        builder.append(", payerBankCode=");
        builder.append(payerBankCode);
        builder.append(", payerBankAccountNo=");
        builder.append(payerBankAccountNo);
        builder.append(", payerAccountType=");
        builder.append(payerAccountType);
        builder.append(", payerMobile=");
        builder.append(payerMobile);
        builder.append(", recvBankCode=");
        builder.append(recvBankCode);
        builder.append(", recvBankAccountNo=");
        builder.append(recvBankAccountNo);
        builder.append(", recvAccountType=");
        builder.append(recvAccountType);
        builder.append(", recvMobile=");
        builder.append(recvMobile);
        builder.append(", returnUrl=");
        builder.append(returnUrl);
        builder.append(", noticeUrl=");
        builder.append(noticeUrl);
        builder.append(", mrchOrderUrl=");
        builder.append(mrchOrderUrl);
        builder.append(", remark=");
        builder.append(remark);
        builder.append("]");
        return builder.toString();
    }
}
