package com.cifpay.lc.bankadapter.api.input.bank;

import com.cifpay.lc.bankadapter.api.input.AbsTradeParam;
import com.cifpay.lc.util.logging.LoggerEnum;

/**
 * 同步业务上送参数
 * 
 * @author linql 2016年5月3日
 *
 */
public class SyncTradeParam extends AbsTradeParam {
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
	 * 必要 用于业务上标识每一笔解冻流水号 应用于一次冻解多次解冻情景
	 */
	private Long bizUnfreezeSerialNo;
	/**
	 * 必要 查询的交易银行返回流水号
	 */
	private String serialNo;
	/**
	 * 必要 查询的交易银行返回交易日期
	 */
	private String tradeDate;

	public SyncTradeParam() {
		super(LoggerEnum.Scene.BANK_SYNC);
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

	public Long getBizUnfreezeSerialNo() {
		return bizUnfreezeSerialNo;
	}

	public void setBizUnfreezeSerialNo(Long bizUnfreezeSerialNo) {
		this.bizUnfreezeSerialNo = bizUnfreezeSerialNo;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	@Override
	public String toString() {
		return "SyncTradeParam [tradeType=" + tradeType + ", tradeBankCode=" + tradeBankCode + ", customerType="
				+ customerType + ", lcId=" + lcId + ", bizUnfreezeSerialNo=" + bizUnfreezeSerialNo + ", serialNo="
				+ serialNo + ", tradeDate=" + tradeDate + "]";
	}
}
