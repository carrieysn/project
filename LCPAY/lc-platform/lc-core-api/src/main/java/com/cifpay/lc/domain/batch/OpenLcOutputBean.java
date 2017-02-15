package com.cifpay.lc.domain.batch;

import java.io.Serializable;
import java.math.BigDecimal;

public class OpenLcOutputBean implements Serializable {
	private static final long serialVersionUID = -3661061990684236634L;

	private Long lcId;// 银信证ID
	private String orderId;// 商户订单号
	private BigDecimal lcAmount; // 银信证开证金额（单位分）

	private String lcStatus;// 银信证状态

	public Long getLcId() {
		return lcId;
	}

	public void setLcId(Long lcId) {
		this.lcId = lcId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getLcAmount() {
		return lcAmount;
	}

	public void setLcAmount(BigDecimal lcAmount) {
		this.lcAmount = lcAmount;
	}

	public String getLcStatus() {
		return lcStatus;
	}

	public void setLcStatus(String lcStatus) {
		this.lcStatus = lcStatus;
	}
}
