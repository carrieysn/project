/**
 * File: SystemParamReqInfo.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年11月25日 下午8:02:03
 */
package com.cifpay.insurance.bean;

import java.io.Serializable;

/**
 * 系统参数请求信息
 * 
 * @author 张均锋
 *
 */
public class SystemParamReqInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 接口名称 **/
	private String service;

	/** 商户号 **/
	private String vendorId;

	/** 应用请求时间戳。 **/
	private String timestamp;

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}
