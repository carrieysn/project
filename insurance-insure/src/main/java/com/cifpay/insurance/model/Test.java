package com.cifpay.insurance.model;

import java.io.Serializable;

public class Test implements Serializable {

	private static final long serialVersionUID = 19700101000000000L;
	private Long id;
	private String name;
	private Integer age;
	private String address;
	/** 鍒涘缓鏃堕棿 **/
	private Long createTime;
	/** 鏇存柊鏃堕棿 **/
	private Long updateTime;
	/** 鐗堟湰锟�**/
	private int version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
