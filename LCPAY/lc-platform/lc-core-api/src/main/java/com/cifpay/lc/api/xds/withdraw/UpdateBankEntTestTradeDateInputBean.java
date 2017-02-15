package com.cifpay.lc.api.xds.withdraw;

import java.io.Serializable;
import java.util.Date;

/**
 * 请求参数，用于更新银企互联服务测试环境的交易日期
 * 
 * 
 *
 */
public class UpdateBankEntTestTradeDateInputBean implements Serializable {
	private static final long serialVersionUID = -326816031690452128L;
	private String bankCode;
	private Date tradeDateForTest;

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public Date getTradeDateForTest() {
		return tradeDateForTest;
	}

	public void setTradeDateForTest(Date tradeDateForTest) {
		this.tradeDateForTest = tradeDateForTest;
	}

}
