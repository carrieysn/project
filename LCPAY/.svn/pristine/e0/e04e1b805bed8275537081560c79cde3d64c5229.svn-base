package com.cifpay.lc.bankadapter.api.input.bank;

import com.cifpay.lc.bankadapter.api.input.AbsTradeParam;
import com.cifpay.lc.util.logging.LoggerEnum;

/**
 * 清算中心业务上送参数
 * 
 * @author linql 2016年7月22日
 *
 */
public class SCPayTradeParam extends AbsTradeParam {
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
	 * （必要） 付款方银行代码(标识)
	 */
	private String payerBankCode;
	/**
	 * （必要） 收款方银行代码(标识)
	 */
	private String payeeBankCode;

	/**
	 * （必要）支付金额
	 */
	private String payAmount;

	/**
	 * {@code
	 * (与payeeBankCardNo二选一，或者二选二)
	 * 收款人卡号
	 * }
	 */
	private String payeeBankCardNo;

	/**
	 * {@code
	 * (与payerBankCardNo二选一，或者二选二)
	 * 付款人卡号
	 * }
	 */
	private String payerBankCardNo;

	/**
	 * 非必要 币别，默认CNY
	 */
	private String currency;
	/**
	 * 非必要 手机号
	 */
	private String mobile;
	/**
	 * 非必要 第三方标识（商家标识）
	 */
	private String thirdId;

	public SCPayTradeParam() {
		super(LoggerEnum.Scene.BANK_SC_PAY);
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
	public String getPayerBankCode() {
		return payerBankCode;
	}
	public void setPayerBankCode(String payerBankCode) {
		this.payerBankCode = payerBankCode;
	}
	public String getPayeeBankCode() {
		return payeeBankCode;
	}
	public void setPayeeBankCode(String payeeBankCode) {
		this.payeeBankCode = payeeBankCode;
	}
	public String getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}
	public String getPayeeBankCardNo() {
		return payeeBankCardNo;
	}
	public void setPayeeBankCardNo(String payeeBankCardNo) {
		this.payeeBankCardNo = payeeBankCardNo;
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
	
}
