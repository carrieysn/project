package com.cifpay.lc.thirdtradeadapter.api.input.icbcbankent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

/**
 * 
 *
 */
public class PayOutInstruction implements Serializable {
	private static final long serialVersionUID = 5343580363836020355L;
	private String callerSystemId;
	private String payerEnterpriseCode;
	private String instructionNo;
	private Long totalAmount; // 分
	private List<PayOutInstructionDetail> instructionDetails;

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

	public String getInstructionNo() {
		return instructionNo;
	}

	public void setInstructionNo(String instructionNo) {
		this.instructionNo = instructionNo;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<PayOutInstructionDetail> getInstructionDetails() {
		return instructionDetails;
	}

	public void setInstructionDetails(List<PayOutInstructionDetail> instructionDetails) {
		this.instructionDetails = instructionDetails;
	}

	public void addInstructionDetail(PayOutInstructionDetail instructionDetail) {
		if (null == instructionDetails) {
			instructionDetails = new ArrayList<PayOutInstructionDetail>();
		}
		instructionDetails.add(instructionDetail);
	}

	@Override
	public String toString() {
		try (Formatter f = new Formatter()) {
			return f.format(
					"{callerSystemId:\"%s\", " + "payerEnterpriseCode:\"%s\", " + "instructionNo:\"%s\", "
							+ "totalAmount:\"%s\", " + "instructionDetails:%s}",
					new Object[] { callerSystemId, payerEnterpriseCode, instructionNo, totalAmount,
							instructionDetails })
					.toString();
		} catch (Exception e) {
			return super.toString();
		}
	}

}
