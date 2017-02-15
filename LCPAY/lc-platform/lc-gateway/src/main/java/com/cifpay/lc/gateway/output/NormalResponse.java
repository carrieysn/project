package com.cifpay.lc.gateway.output;

public class NormalResponse {
	private int returnCode = 0;
	private String returnMsg = "General Success";

	public int getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
}
