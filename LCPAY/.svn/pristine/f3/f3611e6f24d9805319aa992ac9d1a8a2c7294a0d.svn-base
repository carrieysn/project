package com.cifpay.lc.thirdtradeadapter.api.output.icbcbankent;

import java.io.Serializable;
import java.util.Formatter;

import com.cifpay.lc.thirdtradeadapter.api.constant.ICBCAccountPropType;
import com.cifpay.lc.thirdtradeadapter.api.constant.ICBCBankEntCurrencyType;
import com.cifpay.lc.thirdtradeadapter.api.constant.ICBCBankTransactionResultStatus;

/**
 * 
 *
 */
public class PayOutResultDetail implements Serializable {
	private static final long serialVersionUID = -5041833680155260641L;
	private String instructionDetailNo;
	private ICBCBankEntCurrencyType currencyType;
	private Long payAmount; // 分
	private Boolean crossBankFlag;
	private Boolean sameCityFlag;
	private ICBCAccountPropType corporatePersonalFlag;
	private String adapterReturnedDetailSeqNo;
	private String bankReturnedDetailOrderNo;
	private ICBCBankTransactionResultStatus bankResultStatus;
	private String originalDetailResultCode;
	private String bankAdditionReturnCode;
	private String bankAdditionReturnMsg;

	public String getInstructionDetailNo() {
		return instructionDetailNo;
	}

	public void setInstructionDetailNo(String instructionDetailNo) {
		this.instructionDetailNo = instructionDetailNo;
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

	public ICBCAccountPropType getCorporatePersonalFlag() {
		return corporatePersonalFlag;
	}

	public void setCorporatePersonalFlag(ICBCAccountPropType corporatePersonalFlag) {
		this.corporatePersonalFlag = corporatePersonalFlag;
	}

	public String getAdapterReturnedDetailSeqNo() {
		return adapterReturnedDetailSeqNo;
	}

	public void setAdapterReturnedDetailSeqNo(String adapterReturnedDetailSeqNo) {
		this.adapterReturnedDetailSeqNo = adapterReturnedDetailSeqNo;
	}

	public String getBankReturnedDetailOrderNo() {
		return bankReturnedDetailOrderNo;
	}

	public void setBankReturnedDetailOrderNo(String bankReturnedDetailOrderNo) {
		this.bankReturnedDetailOrderNo = bankReturnedDetailOrderNo;
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

	public String getBankAdditionReturnCode() {
		return bankAdditionReturnCode;
	}

	public void setBankAdditionReturnCode(String bankAdditionReturnCode) {
		this.bankAdditionReturnCode = bankAdditionReturnCode;
	}

	public String getBankAdditionReturnMsg() {
		return bankAdditionReturnMsg;
	}

	public void setBankAdditionReturnMsg(String bankAdditionReturnMsg) {
		this.bankAdditionReturnMsg = bankAdditionReturnMsg;
	}

	@Override
	public String toString() {
		try (Formatter f = new Formatter()) {
			return f.format(
					"{instructionDetailNo:\"%s\", " + "currencyType:\"%s\", " + "payAmount:\"%s\", "
							+ "crossBankFlag:\"%s\", " + "sameCityFlag:\"%s\", " + "corporatePersonalFlag:\"%s\", "
							+ "adapterReturnedDetailSeqNo:\"%s\", " + "bankReturnedDetailOrderNo:\"%s\", "
							+ "bankResultStatus:\"%s\", " + "originalDetailResultCode:\"%s\", "
							+ "bankAdditionReturnCode:\"%s\", " + "bankAdditionReturnMsg:\"%s\"}",
					new Object[] { instructionDetailNo, currencyType, payAmount, crossBankFlag, sameCityFlag,
							corporatePersonalFlag, adapterReturnedDetailSeqNo, bankReturnedDetailOrderNo,
							bankResultStatus, originalDetailResultCode, bankAdditionReturnCode, bankAdditionReturnMsg })
					.toString();
		} catch (Exception e) {
			return super.toString();
		}
	}

}
