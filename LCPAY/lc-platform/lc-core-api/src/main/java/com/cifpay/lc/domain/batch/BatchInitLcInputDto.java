package com.cifpay.lc.domain.batch;

import java.io.Serializable;
import java.math.BigDecimal;

import com.cifpay.lc.constant.enums.AccountPropertyType;

public class BatchInitLcInputDto implements Serializable {
	private static final long serialVersionUID = -8721109080565895270L;

	// private String merId; // 商户号
	private String productCode; // 银信证产品代码
	private String orderId; // 商户订单号

	private String currency; // 银信证币种
	private BigDecimal amount; // 银信证开证金额

	private int recvValidSecond; // 收证失效时间（单位秒）
	private int sendValidSecond; // 履约失效时间（单位秒）
	private int confirmValidSecond; // 申请解付失效时间（单位秒）
	private int payValidSecond; // 执行解付到期时间（单位秒）

	// private String payerBankCode; // 付款方银行代码
	// private String payerBankAccountNo; // 付款人账号
	// private AccountPropertyType payerAccountType; // 付款人类型：1:个人 2:企业

	private String recvBankCode; // 收款方银行代码
	private String recvBankAccountNo; // 收款人账号
	private AccountPropertyType recvAccountType; // 收款人类型：single:个人 multiple:企业
	private String recvMobile; // 收款人手机号

	private String merOrderUrl;// 商户订单详情地址
	// private String returnUrl; // 返回地址
	// private String noticeUrl; // 通知地址

	private String remark; // 备注

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

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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

	public AccountPropertyType getRecvAccountType() {
		return recvAccountType;
	}

	public void setRecvAccountType(AccountPropertyType recvAccountType) {
		this.recvAccountType = recvAccountType;
	}

	public String getMerOrderUrl() {
		return merOrderUrl;
	}

	public void setMerOrderUrl(String mrchOrderUrl) {
		this.merOrderUrl = mrchOrderUrl;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	//
	// public String getNoticeUrl() {
	// return noticeUrl;
	// }
	//
	// public void setNoticeUrl(String noticeUrl) {
	// this.noticeUrl = noticeUrl;
	// }

	// public String getReturnUrl() {
	// return returnUrl;
	// }
	//
	// public void setReturnUrl(String returnUrl) {
	// this.returnUrl = returnUrl;
	// }

	public String getRecvMobile() {
		return recvMobile;
	}

	public void setRecvMobile(String recvMobile) {
		this.recvMobile = recvMobile;
	}
}
