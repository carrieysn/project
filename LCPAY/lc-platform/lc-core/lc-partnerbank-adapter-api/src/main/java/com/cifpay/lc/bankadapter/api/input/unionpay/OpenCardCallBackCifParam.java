package com.cifpay.lc.bankadapter.api.input.unionpay;

import java.util.HashMap;

import com.cifpay.lc.bankadapter.api.helper.StringTool;
import com.cifpay.lc.bankadapter.api.input.AbsTradeParam;
import com.cifpay.lc.util.logging.LoggerEnum;

/**
 * 银联后台通知所需参数实体封装
 * 
 * @author Administrator
 *
 */
public class OpenCardCallBackCifParam extends AbsTradeParam {
	private static final long serialVersionUID = 8577567441987025481L;

	private String encoding;
	private HashMap<String, String> reqParam;

	public OpenCardCallBackCifParam() {
		super(LoggerEnum.Scene.UNION_OPEN_CARD_CALLBACK);
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public HashMap<String, String> getReqParam() {
		return reqParam;
	}

	public void setReqParam(HashMap<String, String> reqParam) {
		this.reqParam = reqParam;
	}

	@Override
	public String toString() {
		return "OpenCardCallBackCifParam [encoding=" + encoding + ", reqParam=" + StringTool.printMap(reqParam) + "]";
	}

}
