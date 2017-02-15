package com.cifpay.lc.core.db.pojo;

import java.util.HashMap;
import java.util.Map;

public class BaseDomain {

	private String orderField;
	
	private Integer size;
	
	private Integer offset;
	
	private Map<String,Object> extendParams = new HashMap<String,Object>();

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Map<String, Object> getExtendParams() {
		return extendParams;
	}

	public void setExtendParams(Map<String, Object> extendParams) {
		this.extendParams = extendParams;
	}
	
	
}
