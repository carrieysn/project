package com.cifpay.lc.bankadapter.api.output;

import java.util.HashMap;
import java.util.Map;

import com.cifpay.lc.bankadapter.api.helper.StringTool;

public class GeneralTradeResult extends AbsBusinessResult {

	/**
	 * 本地交易流水号
	 */
	private Long flowId;

	private String queryId;// 银行返回的交易流水号

	/**
	 * 查询业务结果：0成功 1失败 2记录不存在
	 */
	private String queryTradeResult;

	private Map<String, String> resultMap = new HashMap<String, String>();

	public Map<String, String> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, String> resultMap) {
		this.resultMap = resultMap;
	}

	public Long getFlowId() {
		return flowId;
	}

	public void setFlowId(Long flowId) {
		this.flowId = flowId;
	}

	public String getQueryId() {
		return queryId;
	}

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}

	public String getQueryTradeResult() {
		return queryTradeResult;
	}

	public void setQueryTradeResult(String queryTradeResult) {
		this.queryTradeResult = queryTradeResult;
	}

	@Override
	public String toString() {
		return "GeneralTradeResult [flowId=" + flowId + ", queryId=" + queryId + ", queryTradeResult="
				+ queryTradeResult + ", resultMap=" + StringTool.printMap(resultMap)  + ", getSysReturnCode()=" + getSysReturnCode()
				+ ", getTradeResult()=" + getTradeResult() + ", getResultDesc()=" + getResultDesc() + "]";
	}


}
