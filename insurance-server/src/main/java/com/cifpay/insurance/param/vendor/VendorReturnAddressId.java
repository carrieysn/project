/**
 * File: VendorReturnAddressId.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月5日 下午2:55:50
 */
package com.cifpay.insurance.param.vendor;

import java.io.Serializable;

/**
 * 收货人地址ID
 * 
 * @author 张均锋
 *
 */
public class VendorReturnAddressId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String vendorReturnAddressId;

	public String getVendorReturnAddressId() {
		return vendorReturnAddressId;
	}

	public void setVendorReturnAddressId(String vendorReturnAddressId) {
		this.vendorReturnAddressId = vendorReturnAddressId;
	}


}
