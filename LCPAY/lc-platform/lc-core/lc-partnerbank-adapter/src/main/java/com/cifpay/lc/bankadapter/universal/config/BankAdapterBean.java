package com.cifpay.lc.bankadapter.universal.config;

import com.cifpay.lc.bankadapter.universal.IBankDeal;

public class BankAdapterBean {
	private String tradeBankCode;
	private String tradeDealFreezeAdapter;
	private String tradeDealUnfreezeAdapter;
	private String tradeDealPayAdapter;
	private Class<IBankDeal> clzOfFreeze;
	private Class<IBankDeal> clzOfUnfreeze;
	private Class<IBankDeal> clzOfPay;
	
	
	public Class<IBankDeal> getClzOfFreeze() {
		return clzOfFreeze;
	}

	public void setClzOfFreeze(Class<IBankDeal> clzOfFreeze) {
		this.clzOfFreeze = clzOfFreeze;
	}

	public Class<IBankDeal> getClzOfUnfreeze() {
		return clzOfUnfreeze;
	}

	public void setClzOfUnfreeze(Class<IBankDeal> clzOfUnfreeze) {
		this.clzOfUnfreeze = clzOfUnfreeze;
	}

	public Class<IBankDeal> getClzOfPay() {
		return clzOfPay;
	}

	public void setClzOfPay(Class<IBankDeal> clzOfPay) {
		this.clzOfPay = clzOfPay;
	}


	public String getTradeDealFreezeAdapter() {
		return tradeDealFreezeAdapter;
	}

	public void setTradeDealFreezeAdapter(String tradeDealFreezeAdapter) {
		this.tradeDealFreezeAdapter = tradeDealFreezeAdapter;
	}

	public String getTradeDealUnfreezeAdapter() {
		return tradeDealUnfreezeAdapter;
	}

	public void setTradeDealUnfreezeAdapter(String tradeDealUnfreezeAdapter) {
		this.tradeDealUnfreezeAdapter = tradeDealUnfreezeAdapter;
	}

	public String getTradeDealPayAdapter() {
		return tradeDealPayAdapter;
	}

	public void setTradeDealPayAdapter(String tradeDealPayAdapter) {
		this.tradeDealPayAdapter = tradeDealPayAdapter;
	}

	public String getTradeBankCode() {
		return tradeBankCode;
	}

	public void setTradeBankCode(String tradeBankCode) {
		this.tradeBankCode = tradeBankCode;
	}

}
