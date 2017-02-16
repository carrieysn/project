/**
 * File: InsurerInfo.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年11月25日 下午2:34:55
 */
package com.cifpay.insurance.param.policy;

import java.io.Serializable;

/**
 * 保险人信息
 * 
 * @author 张均锋
 *
 */
public class InsurerInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 保险人名称 **/
	private String insurername;
	/** 服务热线 **/
	private String hotline;
	/** 地址 **/
	private String address;

	public String getInsurername() {
		return insurername;
	}

	public void setInsurername(String insurername) {
		this.insurername = insurername;
	}

	public String getHotline() {
		return hotline;
	}

	public void setHotline(String hotline) {
		this.hotline = hotline;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
