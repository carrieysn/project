package com.cifpay.lc.domain.cache;

import java.io.Serializable;

public class CacheOutput  implements Serializable{

	
	private static final long serialVersionUID = -9079068902843893714L;
	
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
