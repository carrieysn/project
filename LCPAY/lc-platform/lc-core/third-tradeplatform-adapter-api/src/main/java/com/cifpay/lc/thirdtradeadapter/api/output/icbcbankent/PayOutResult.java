package com.cifpay.lc.thirdtradeadapter.api.output.icbcbankent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

/**
 * 
 *
 */
public class PayOutResult implements Serializable {
	private static final long serialVersionUID = 4662776734039429129L;
	private String instructionNo;
	private Long totalAmount; // 分
	private String adapterReturnedBatchSeqNo;
	private String bankReturnedSerialNo;
	private List<PayOutResultDetail> resultDetails;

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

	public String getAdapterReturnedBatchSeqNo() {
		return adapterReturnedBatchSeqNo;
	}

	public void setAdapterReturnedBatchSeqNo(String adapterReturnedBatchSeqNo) {
		this.adapterReturnedBatchSeqNo = adapterReturnedBatchSeqNo;
	}

	public String getBankReturnedSerialNo() {
		return bankReturnedSerialNo;
	}

	public void setBankReturnedSerialNo(String bankReturnedSerialNo) {
		this.bankReturnedSerialNo = bankReturnedSerialNo;
	}

	public List<PayOutResultDetail> getResultDetails() {
		return resultDetails;
	}

	public void setResultDetails(List<PayOutResultDetail> resultDetails) {
		this.resultDetails = resultDetails;
	}

	public void addResultDetail(PayOutResultDetail resultDetail) {
		if (null == resultDetails) {
			resultDetails = new ArrayList<PayOutResultDetail>();
		}
		resultDetails.add(resultDetail);
	}

	@Override
	public String toString() {
		try (Formatter f = new Formatter()) {
			return f.format(
					"{instructionNo:\"%s\", " + "totalAmount:\"%s\", " + "adapterReturnedBatchSeqNo:\"%s\", "
							+ "bankReturnedSerialNo:\"%s\", " + "resultDetails:%s}",
					new Object[] { instructionNo, totalAmount, adapterReturnedBatchSeqNo, bankReturnedSerialNo,
							resultDetails })
					.toString();
		} catch (Exception e) {
			return super.toString();
		}
	}
}
