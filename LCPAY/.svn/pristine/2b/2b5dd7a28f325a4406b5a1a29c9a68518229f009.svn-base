package com.cifpay.lc.bankadapter.api.input.unionpay;//

import com.cifpay.lc.bankadapter.api.helper.StringTool;
import com.cifpay.lc.bankadapter.api.input.AbsTradeParam;//
import com.cifpay.lc.util.logging.LoggerEnum;

/**
 * 银联实现执行解付所需参数实体封装
 * 
 * @author Administrator
 *
 */
public class SmsCifParam extends AbsTradeParam {
	private static final long serialVersionUID = 1164595159896906406L;

	private Long businessId;// 业务ID(
	private String txnType;// 交易类型
	private String txnSubType;// 交易子类型
	private String channelType;// 渠道类型(*)
	private String merId;// 商户代码
	private String userId;// 商户用户唯一ID
	private String subMerId;// 二级商户代码(*)
	private String orderId;// 商户订单号(*)
	private String txnTime;// 订单发送时间(*)
	private Long txnAmt;// 交易金额(*)
	private String currencyCode;// 交易币种 (默认:RMB 156)
	private String phoneNo;// 手机号(*)
	private String accType; // 账号类型
	private String accNo; // 账号(*)

	public SmsCifParam() {
		super(LoggerEnum.Scene.UNION_SMS);
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public Long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public String getTxnSubType() {
		return txnSubType;
	}

	public void setTxnSubType(String txnSubType) {
		this.txnSubType = txnSubType;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSubMerId() {
		return subMerId;
	}

	public void setSubMerId(String subMerId) {
		this.subMerId = subMerId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getTxnTime() {
		return txnTime;
	}

	public void setTxnTime(String txnTime) {
		this.txnTime = txnTime;
	}

	public Long getTxnAmt() {
		return txnAmt;
	}

	public void setTxnAmt(Long txnAmt) {
		this.txnAmt = txnAmt;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	@Override
	public String toString() {
		return "SmsCifParam [businessId=" + businessId + ", txnType=" + txnType + ", txnSubType=" + txnSubType
				+ ", channelType=" + channelType + ", merId=" + merId + ", userId=" + userId + ", subMerId=" + subMerId
				+ ", orderId=" + orderId + ", txnTime=" + txnTime + ", txnAmt=" + txnAmt + ", currencyCode="
				+ currencyCode + ", phoneNo=" + StringTool.printPhone(phoneNo) + ", getLcId()=" + getLcId()
				+ ", getTxnId()=" + getTxnId() + ", getBizType()=" + getBizType() + "]";
	}

}
