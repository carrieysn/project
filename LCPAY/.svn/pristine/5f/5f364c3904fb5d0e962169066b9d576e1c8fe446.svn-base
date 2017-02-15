package com.cifpay.lc.thirdtradeadapter.api.output.icbcbankent;

import java.io.Serializable;
import java.util.Formatter;

import com.cifpay.lc.thirdtradeadapter.api.constant.ICBCBankEntCurrencyType;
import com.cifpay.lc.thirdtradeadapter.api.constant.ICBCBankTransactionResultStatus;

/**
 * 
 *
 */
public class PayOutEnquiryResult implements Serializable {
	private static final long serialVersionUID = 4662776734039429129L;
	private String originalInstructionNo;
	private String originalInstructionDetailNo;
	private String adapterReturnedBatchSeqNo;
	private String adapterReturnedDetailSeqNo;
	private String bankReturnedSerialNo;
	private ICBCBankEntCurrencyType currencyType;
	private Long payAmount; // 分
	private Boolean crossBankFlag;
	private Boolean sameCityFlag;
	private ICBCBankTransactionResultStatus bankResultStatus;
	private String originalDetailResultCode;
	private String bankAddiDetailReturnCode;
	private String bankAddiDetailReturnMsg;

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

	public String getAdapterReturnedBatchSeqNo() {
		return adapterReturnedBatchSeqNo;
	}

	public void setAdapterReturnedBatchSeqNo(String adapterReturnedBatchSeqNo) {
		this.adapterReturnedBatchSeqNo = adapterReturnedBatchSeqNo;
	}

	public String getAdapterReturnedDetailSeqNo() {
		return adapterReturnedDetailSeqNo;
	}

	public void setAdapterReturnedDetailSeqNo(String adapterReturnedDetailSeqNo) {
		this.adapterReturnedDetailSeqNo = adapterReturnedDetailSeqNo;
	}

	public String getBankReturnedSerialNo() {
		return bankReturnedSerialNo;
	}

	public void setBankReturnedSerialNo(String bankReturnedSerialNo) {
		this.bankReturnedSerialNo = bankReturnedSerialNo;
	}

	public ICBCBankEntCurrencyType getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(ICBCBankEntCurrencyType currencyType) {
		this.currencyType = currencyType;
	}

	public Long getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(Long payAmount) {
		this.payAmount = payAmount;
	}

	public Boolean getCrossBankFlag() {
		return crossBankFlag;
	}

	public void setCrossBankFlag(Boolean crossBankFlag) {
		this.crossBankFlag = crossBankFlag;
	}

	public Boolean getSameCityFlag() {
		return sameCityFlag;
	}

	public void setSameCityFlag(Boolean sameCityFlag) {
		this.sameCityFlag = sameCityFlag;
	}

	public ICBCBankTransactionResultStatus getBankResultStatus() {
		return bankResultStatus;
	}

	public void setBankResultStatus(ICBCBankTransactionResultStatus bankResultStatus) {
		this.bankResultStatus = bankResultStatus;
	}

	public String getOriginalDetailResultCode() {
		return originalDetailResultCode;
	}

	public void setOriginalDetailResultCode(String originalDetailResultCode) {
		this.originalDetailResultCode = originalDetailResultCode;
	}

	public String getBankAddiDetailReturnCode() {
		return bankAddiDetailReturnCode;
	}

	public void setBankAddiDetailReturnCode(String bankAddiDetailReturnCode) {
		this.bankAddiDetailReturnCode = bankAddiDetailReturnCode;
	}

	public String getBankAddiDetailReturnMsg() {
		return bankAddiDetailReturnMsg;
	}

	public void setBankAddiDetailReturnMsg(String bankAddiDetailReturnMsg) {
		this.bankAddiDetailReturnMsg = bankAddiDetailReturnMsg;
	}

	@Override
	public String toString() {
		try (Formatter f = new Formatter()) {
			return f.format(
					"{originalInstructionNo:\"%s\", " + "originalInstructionDetailNo:\"%s\", "
							+ "adapterReturnedBatchSeqNo:\"%s\", " + "adapterReturnedDetailSeqNo:\"%s\", "
							+ "bankReturnedSerialNo:\"%s\", currencyType:\"%s\", payAmount:\"%s\", crossBankFlag:\"%s\", sameCityFlag:\"%s\", bankResultStatus:\"%s\", originalDetailResultCode:\"%s\", bankAddiDetailReturnCode:\"%s\", bankAddiDetailReturnMsg:\"%s\"}",
					new Object[] { originalInstructionNo, originalInstructionDetailNo, adapterReturnedBatchSeqNo,
							adapterReturnedDetailSeqNo, bankReturnedSerialNo, currencyType, payAmount, crossBankFlag,
							sameCityFlag, bankResultStatus, originalDetailResultCode, bankAddiDetailReturnCode,
							bankAddiDetailReturnMsg })
					.toString();
		} catch (Exception e) {
			return super.toString();
		}
	}

}
