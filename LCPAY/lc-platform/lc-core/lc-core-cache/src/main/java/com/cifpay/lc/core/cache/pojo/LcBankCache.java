package com.cifpay.lc.core.cache.pojo;

import java.io.Serializable;

import com.cifpay.lc.core.cache.bean.BaseCache;

public class LcBankCache extends BaseCache  implements Serializable {
	
	private static final long serialVersionUID = 2807041223550150614L;
	
	private String bankCode;

    private String bankName;

    private String bankType;

    private Integer isValid;

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}
    
    
}
