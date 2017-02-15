package com.cifpay.lc.thirdtradeadapter.api.output.icbcbankent;

import java.io.Serializable;
import java.util.Formatter;

import com.cifpay.lc.thirdtradeadapter.api.constant.ICBCBankEntCurrencyType;

/**
 * 
 *
 */
public class BalanceEnquiryResult implements Serializable {
	private static final long serialVersionUID = -1310792098131450409L;
	private String accNo;
	private ICBCBankEntCurrencyType currencyType;
	private String acctProperty;
	private Long accBalance; // 昨日余额，以币种的最小单位为单位，即人民币为分
	private Long balance; // 当前余额，以币种的最小单位为单位，即人民币为分
	private Long usableBalance; // 可用余额，以币种的最小单位为单位
	private Long frzAmt; // 冻结额度合计，以币种的最小单位为单位

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public ICBCBankEntCurrencyType getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(ICBCBankEntCurrencyType currencyType) {
		this.currencyType = currencyType;
	}

	public String getAcctProperty() {
		return acctProperty;
	}

	public void setAcctProperty(String acctProperty) {
		this.acctProperty = acctProperty;
	}

	public Long getAccBalance() {
		return accBalance;
	}

	public void setAccBalance(Long accBalance) {
		this.accBalance = accBalance;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public Long getUsableBalance() {
		return usableBalance;
	}

	public void setUsableBalance(Long usableBalance) {
		this.usableBalance = usableBalance;
	}

	public Long getFrzAmt() {
		return frzAmt;
	}

	public void setFrzAmt(Long frzAmt) {
		this.frzAmt = frzAmt;
	}

	@Override
	public String toString() {
		try (Formatter f = new Formatter()) {
			return f.format(
					"{accNo:\"%s\", " + "currencyType:\"%s\", " + "acctProperty:\"%s\", " + "accBalance:\"%s\", "
							+ "balance:\"%s\", usableBalance:\"%s\", frzAmt:\"%s\"}",
					new Object[] { accNo, currencyType, acctProperty, accBalance, balance, usableBalance, frzAmt })
					.toString();
		} catch (Exception e) {
			return super.toString();
		}
	}

}
