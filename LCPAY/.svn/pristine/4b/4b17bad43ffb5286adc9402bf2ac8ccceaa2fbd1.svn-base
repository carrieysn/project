package com.cifpay.lc.thirdtradeadapter.api.input.icbcbankent;

import com.cifpay.lc.thirdtradeadapter.api.constant.ICBCBankEntCurrencyType;

import java.util.Formatter;

import com.cifpay.lc.thirdtradeadapter.api.constant.ICBCAccountPropType;

/**
 * 
 *
 */
public class PayOutInstructionDetail {
	private String instructionDetailNo;
	private ICBCAccountPropType recvAccountPropType;
	private String recvBankCode;
	private String recvBankName;
	private String recvBankAccountNo;
	private String recvBankAccountName;
	private String recvBankCityName;
	private ICBCBankEntCurrencyType currencyType;
	private Long payAmount; // 分
	private String fundUseDesc;
	private String summary;

	public String getInstructionDetailNo() {
		return instructionDetailNo;
	}

	public void setInstructionDetailNo(String instructionDetailNo) {
		this.instructionDetailNo = instructionDetailNo;
	}

	public ICBCAccountPropType getRecvAccountPropType() {
		return recvAccountPropType;
	}

	public void setRecvAccountPropType(ICBCAccountPropType recvAccountPropType) {
		this.recvAccountPropType = recvAccountPropType;
	}

	public String getRecvBankCode() {
		return recvBankCode;
	}

	public void setRecvBankCode(String recvBankCode) {
		this.recvBankCode = recvBankCode;
	}

	public String getRecvBankName() {
		return recvBankName;
	}

	public void setRecvBankName(String recvBankName) {
		this.recvBankName = recvBankName;
	}

	public String getRecvBankAccountNo() {
		return recvBankAccountNo;
	}

	public void setRecvBankAccountNo(String recvBankAccountNo) {
		this.recvBankAccountNo = recvBankAccountNo;
	}

	public String getRecvBankAccountName() {
		return recvBankAccountName;
	}

	public void setRecvBankAccountName(String recvBankAccountName) {
		this.recvBankAccountName = recvBankAccountName;
	}

	public String getRecvBankCityName() {
		return recvBankCityName;
	}

	public void setRecvBankCityName(String recvBankCityName) {
		this.recvBankCityName = recvBankCityName;
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

	public String getFundUseDesc() {
		return fundUseDesc;
	}

	public void setFundUseDesc(String fundUseDesc) {
		this.fundUseDesc = fundUseDesc;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Override
	public String toString() {
		try (Formatter f = new Formatter()) {
			return f.format(
					"{instructionDetailNo:\"%s\", " + "recvAccountPropType:\"%s\", " + "recvBankCode:\"%s\", "
							+ "recvBankName:\"%s\", "
							+ "recvBankAccountNo:\"%s\", recvBankAccountName:\"%s\", recvBankCityName:\"%s\", "
							+ "currencyType:\"%s\", payAmount:\"%s\", fundUseDesc:\"%s\", summary:\"%s\"}",
					new Object[] { instructionDetailNo, recvAccountPropType, recvBankCode, recvBankName,
							recvBankAccountNo, recvBankAccountName, recvBankCityName, currencyType, payAmount,
							fundUseDesc, summary })
					.toString();
		} catch (Exception e) {
			return super.toString();
		}
	}
}
