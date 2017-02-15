package com.cifpay.lc.bankadapter.api.input.bank;

import com.cifpay.lc.bankadapter.api.input.AbsTradeParam;
import com.cifpay.lc.util.logging.LoggerEnum;

/**
 * 冻结业务上送参数
 * 
 * @author linql 2016年5月3日
 *
 */
public class FreezeTradeParam extends AbsTradeParam {
	/**
	 * 必要 交易类型 参照：TradeConfig.TRADE_TYPE_XXX
	 */
	private String tradeType;
	/**
	 * 必要 {@code
	 * 交易即将要发送到的银行代码标识 参照：TradeConfig.TRADE_BANK_XXX
	 * 原则：冻结(冻结查询) - 开证银行的代码标识  
	 *             解冻、划款(解冻、划款查询) - 收证银行的代码标识
	 *    }
	 */
	private String tradeBankCode;

	/**
	 * 必要 客户类型 参照：TradeConfig.TRADE_CUSTOMER_TYPE_XXX
	 */
	private String customerType;
	/**
	 * 必要 银信证ID
	 */
	private Long lcId;
	/**
	 * 必要 冻结金额 单位:分
	 */
	private String freezeAmount;
	/**
	 * 必要 付款人卡号
	 */
	private String payerBankCardNo;
	/**
	 * 非必要 币别，默认CNY
	 */
	private String currency;
	/**
	 * 非必要 截至(保留)日期，默认9999-12-31
	 */
	private String holdDate;
	/**
	 * 非必要 手机号
	 */
	private String mobile;
	/**
	 * 非必要 第三方标识（商家标识）
	 */
	private String thirdId;

	public FreezeTradeParam() {
		super(LoggerEnum.Scene.BANK_FREEZE);
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getTradeBankCode() {
		return tradeBankCode;
	}

	public void setTradeBankCode(String tradeBankCode) {
		this.tradeBankCode = tradeBankCode;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public Long getLcId() {
		return lcId;
	}

	public void setLcId(Long lcId) {
		this.lcId = lcId;
	}

	public String getFreezeAmount() {
		return freezeAmount;
	}

	public void setFreezeAmount(String freezeAmount) {
		this.freezeAmount = freezeAmount;
	}

	public String getPayerBankCardNo() {
		return payerBankCardNo;
	}

	public void setPayerBankCardNo(String payerBankCardNo) {
		this.payerBankCardNo = payerBankCardNo;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getHoldDate() {
		return holdDate;
	}

	public void setHoldDate(String holdDate) {
		this.holdDate = holdDate;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getThirdId() {
		return thirdId;
	}

	public void setThirdId(String thirdId) {
		this.thirdId = thirdId;
	}

	@Override
	public String toString() {
		return "FreezeTradeParam [tradeType=" + tradeType + ", tradeBankCode=" + tradeBankCode + ", customerType="
				+ customerType + ", lcId=" + lcId + ", freezeAmount=" + freezeAmount + ", payerBankCardNo="
				+ payerBankCardNo + ", currency=" + currency + ", holdDate=" + holdDate + ", mobile=" + mobile
				+ ", thirdId=" + thirdId + "]";
	}

}
