package com.cifpay.lc.bankadapter.api.input.unionpay;//

import com.cifpay.lc.bankadapter.api.input.AbsTradeParam;//
import com.cifpay.lc.util.logging.LoggerEnum;

/**
 * 银联实现执行解付所需参数实体封装
 *
 * @author Administrator
 *
 */
public class PayCifParam extends AbsTradeParam {
	private static final long serialVersionUID = 5647055876714341132L;

	private Long businessId;// 业务ID(
	private String txnType;// 交易类型
	private String txnSubType;// 交易子类型
	private String channelType;// 渠道类型(*)
	private String merId;// 商户代码
	private String userId;// 商户用户唯一ID
	private String subMerId;// 二级商户代码(*)
	private String orderId;// 商户订单号(*)
	private String origOryId;// 原始交易流水号，即原始消费的queryId(*)
	private String txnTime;// 订单发送时间(*)
	private Long txnAmt;// 交易金额(*)
	private String currencyCode;// 交易币种 (默认:RMB 156)

	public PayCifParam( ) {
		super(LoggerEnum.Scene.UNION_PAY);
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	private String reqReserved;// 请求方保留域

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

	public String getOrigOryId() {
		return origOryId;
	}

	public void setOrigOryId(String origOryId) {
		this.origOryId = origOryId;
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

	public String getReqReserved() {
		return reqReserved;
	}

	public void setReqReserved(String reqReserved) {
		this.reqReserved = reqReserved;
	}

	@Override
	public String toString() {
		return "PayCifParam [businessId=" + businessId + ", txnType=" + txnType + ", txnSubType=" + txnSubType
				+ ", channelType=" + channelType + ", merId=" + merId + ", userId=" + userId + ", subMerId=" + subMerId
				+ ", orderId=" + orderId + ", origOryId=" + origOryId + ", txnTime=" + txnTime + ", txnAmt=" + txnAmt
				+ ", currencyCode=" + currencyCode + ", reqReserved=" + reqReserved + ", getLcId()=" + getLcId()
				+ ", getTxnId()=" + getTxnId() + ", getBizType()=" + getBizType() + "]";
	}

}
