package com.cifpay.lc.bankadapter.api.input.unionpay;

import com.cifpay.lc.bankadapter.api.helper.StringTool;
import com.cifpay.lc.bankadapter.api.input.AbsTradeParam;
import com.cifpay.lc.util.logging.LoggerEnum;

/**
 * 银联实现无跳转开通参数
 * 
 * @author linql
 *
 */
public class OpenCardParam extends AbsTradeParam {
	private static final long serialVersionUID = -4250109957762114548L;

	private Long businessId;// 业务ID
	private String txnType;// 交易类型
	private String txnSubType;// 交易子类型
	private String merId;// 商户代码
	private String userId;// 商户用户唯一ID
	private String orderId;// 商户订单号(*)
	private String txnTime;// 订单发送时间
	private String channelType;// 渠道类型(*)
	private String subMerId;// 二级商户代码(*)
	private String accType;// 账号类型
	private String accNo;//用户帐号(*)
	
	private String frontUrl;//前台通知地址
	private String backUrl;//后台通知地址
	private String reqReserved;//请求方保留域

    public OpenCardParam() {
        super(LoggerEnum.Scene.UNION_OPEN_CARD);
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
	public String getChannelType() {
		return channelType;
	}
	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}
	public String getSubMerId() {
		return subMerId;
	}
	public void setSubMerId(String subMerId) {
		this.subMerId = subMerId;
	}
	public String getAccType() {
		return accType;
	}
	public void setAccType(String accType) {
		this.accType = accType;
	}
	public String getFrontUrl() {
		return frontUrl;
	}
	public void setFrontUrl(String frontUrl) {
		this.frontUrl = frontUrl;
	}
	public String getBackUrl() {
		return backUrl;
	}
	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}
	public String getReqReserved() {
		return reqReserved;
	}
	public void setReqReserved(String reqReserved) {
		this.reqReserved = reqReserved;
	}
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	@Override
	public String toString() {
		return "OpenCardParam [businessId=" + businessId + ", txnType=" + txnType + ", txnSubType=" + txnSubType
				+ ", merId=" + merId + ", userId=" + userId + ", orderId=" + orderId + ", txnTime=" + txnTime
				+ ", channelType=" + channelType + ", subMerId=" + subMerId + ", accType=" + accType + ", accNo="
				+ StringTool.printCard(accNo) + ", frontUrl=" + frontUrl + ", backUrl=" + backUrl + ", reqReserved=" + reqReserved
				+ ", getLcId()=" + getLcId() + ", getTxnId()=" + getTxnId() + ", getBizType()=" + getBizType() + "]";
	}
	
}
