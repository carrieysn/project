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
public class BackRcvResponseCifParam extends AbsTradeParam {
	private static final long serialVersionUID = -5016816252934804443L;

	private String encoding;
	private HashMap<String, String> reqParam;

    public BackRcvResponseCifParam() {
        super(LoggerEnum.Scene.UNION_BACKRCV);
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
		return "BackRcvResponseCifParam [encoding=" + encoding + ", reqParam=" + StringTool.printMap(reqParam) + "]";
	}

}
