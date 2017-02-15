package com.cifpay.lc.std.domain.kernel;

import java.math.BigDecimal;

public class InvalidateAppointmentKernelOutputBean extends KernelBaseOutputBean{

	private static final long serialVersionUID = -3271450202923917299L;

	private long lcInvalidateId; 			// 失效流水Id
	private BigDecimal invalidateAmount; 	// 失效金额

	public long getLcInvalidateId() {
		return lcInvalidateId;
	}

	public void setLcInvalidateId(long lcInvalidateId) {
		this.lcInvalidateId = lcInvalidateId;
	}

	public BigDecimal getInvalidateAmount() {
		return invalidateAmount;
	}

	public void setInvalidateAmount(BigDecimal invalidateAmount) {
		this.invalidateAmount = invalidateAmount;
	}
}
