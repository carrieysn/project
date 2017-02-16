package com.cifpay.insurance.model;

import java.io.Serializable;

public class InsInsurerInfo implements Serializable {

	private static final long serialVersionUID = 19700101000000000L;
	private Long id;
	/** 保险人名称 **/
	private String insurername;
	/** 服务热线 **/
	private String hotline;
	/** 地址 **/
	private String address;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
