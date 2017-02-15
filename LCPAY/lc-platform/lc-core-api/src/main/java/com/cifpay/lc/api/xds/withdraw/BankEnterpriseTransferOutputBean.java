package com.cifpay.lc.api.xds.withdraw;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 银企互联转账服务接口输出参数。
 * 
 * 
 *
 */
public class BankEnterpriseTransferOutputBean implements Serializable {
	private static final long serialVersionUID = -6714522057411868856L;
	private String orderId;
	private String requestId;

	private Long lcId;
	private String lcNo;
	private String lcState;

	private BigDecimal tradeAmt;
	private Date tradeTime;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public Long getLcId() {
		return lcId;
	}

	public void setLcId(Long lcId) {
		this.lcId = lcId;
	}

	public String getLcNo() {
		return lcNo;
	}

	public void setLcNo(String lcNo) {
		this.lcNo = lcNo;
	}

	public String getLcState() {
		return lcState;
	}

	public void setLcState(String lcState) {
		this.lcState = lcState;
	}

	public BigDecimal getTradeAmt() {
		return tradeAmt;
	}

	public void setTradeAmt(BigDecimal tradeAmt) {
		this.tradeAmt = tradeAmt;
	}

	public Date getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}

}
