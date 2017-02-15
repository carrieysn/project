package com.cifpay.lc.thirdtradeadapter.api.input.icbcbankent;

import java.io.Serializable;
import java.util.Formatter;

/**
 * 
 *
 */
public class BalanceEnquiry implements Serializable {
	private static final long serialVersionUID = 2336375606745349958L;
	private String callerSystemId;
	private String payerEnterpriseCode;

	public String getCallerSystemId() {
		return callerSystemId;
	}

	public void setCallerSystemId(String callerSystemId) {
		this.callerSystemId = callerSystemId;
	}

	public String getPayerEnterpriseCode() {
		return payerEnterpriseCode;
	}

	public void setPayerEnterpriseCode(String payerEnterpriseCode) {
		this.payerEnterpriseCode = payerEnterpriseCode;
	}

	@Override
	public String toString() {
		try (Formatter f = new Formatter()) {
			return f.format("{callerSystemId:\"%s\", " + "payerEnterpriseCode:\"%s\"}",
					new Object[] { callerSystemId, payerEnterpriseCode }).toString();
		} catch (Exception e) {
			return super.toString();
		}
	}

}
