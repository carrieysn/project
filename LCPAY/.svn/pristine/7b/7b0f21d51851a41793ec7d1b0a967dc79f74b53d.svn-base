package com.cifpay.lc.thirdtradeadapter.api.input.icbcbankent;

import java.io.Serializable;
import java.util.Formatter;

/**
 * 
 *
 */
public class PayOutEnquiry implements Serializable {
	private static final long serialVersionUID = 6620483407309736694L;
	private String callerSystemId;
	private String payerEnterpriseCode;
	private String originalInstructionNo;
	private String originalInstructionDetailNo;

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

	public String getOriginalInstructionNo() {
		return originalInstructionNo;
	}

	public void setOriginalInstructionNo(String originalInstructionNo) {
		this.originalInstructionNo = originalInstructionNo;
	}

	public String getOriginalInstructionDetailNo() {
		return originalInstructionDetailNo;
	}

	public void setOriginalInstructionDetailNo(String originalInstructionDetailNo) {
		this.originalInstructionDetailNo = originalInstructionDetailNo;
	}

	@Override
	public String toString() {
		try (Formatter f = new Formatter()) {
			return f.format(
					"{callerSystemId:\"%s\", " + "payerEnterpriseCode:\"%s\", " + "originalInstructionNo:\"%s\", "
							+ "originalInstructionDetailNo:\"%s\"}",
					new Object[] { callerSystemId, payerEnterpriseCode, originalInstructionNo,
							originalInstructionDetailNo })
					.toString();
		} catch (Exception e) {
			return super.toString();
		}
	}

}
