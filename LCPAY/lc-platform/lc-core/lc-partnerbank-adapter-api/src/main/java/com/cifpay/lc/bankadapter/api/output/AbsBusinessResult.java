package com.cifpay.lc.bankadapter.api.output;

public abstract class AbsBusinessResult {
	/**
	 * 交易结果 根据业务情况整理出来的交易业务结果：0成功 1失败 2状态未明
	 */
	private String tradeResult;
	/**
	 * 对应系统统一异常处理码，参照：CoreLcBankAdapterReturnCode
	 */
	private int SysReturnCode;
	/**
	 * 交易结果描述
	 */
	private String resultDesc;

	

	public int getSysReturnCode() {
		return SysReturnCode;
	}

	public void setSysReturnCode(int sysReturnCode) {
		SysReturnCode = sysReturnCode;
	}

	public String getTradeResult() {
		return tradeResult;
	}

	public void setTradeResult(String tradeResult) {
		this.tradeResult = tradeResult;
	}

	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}
}
