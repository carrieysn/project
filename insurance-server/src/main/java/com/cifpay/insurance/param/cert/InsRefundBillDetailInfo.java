package com.cifpay.insurance.param.cert;

import java.io.Serializable;
import java.util.List;

/**
 * 退款单详细信息封装对象
 * 
 * @author 叶胜南
 *
 */
public class InsRefundBillDetailInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GetRefundBillListResult billInfo;
	private List<ReturnTraceListResult> tracelistResult;
	public GetRefundBillListResult getBillInfo() {
		return billInfo;
	}
	public void setBillInfo(GetRefundBillListResult billInfo) {
		this.billInfo = billInfo;
	}
	public List<ReturnTraceListResult> getTracelistResult() {
		return tracelistResult;
	}
	public void setTracelistResult(List<ReturnTraceListResult> tracelistResult) {
		this.tracelistResult = tracelistResult;
	}
	
	
	


}
