/**
 * File: Sort.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月9日 上午10:27:18
 */
package com.cifpay.insurance.base;

import java.util.ArrayList;
import java.util.List;

/**
 * 排序,可随意增加排序字段及排序类型.
 * 
 * @author 张均锋
 *
 */
public class Sort {
	private List<Sort> nextSort;
	private String sortField;
	private Order order;

	public Sort(String sortField) {
		this.sortField = sortField;
	}

	public Sort(String sortField, Order order) {
		this.sortField = sortField;
		this.order = order;
	}

	/**
	 * add a Sort at the end of the list .
	 * <p>
	 * 
	 * NOTE:Not Thread-Safe
	 * 
	 * @param sort
	 * @return
	 */
	public Sort and(Sort sort) {
		if (this.nextSort == null) {
			this.nextSort = new ArrayList<Sort>();
		}
		this.nextSort.add(sort);
		return this;
	}

	public List<Sort> getNextSort() {
		return nextSort;
	}

	public void setNextSort(List<Sort> nextSort) {
		this.nextSort = nextSort;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	/**
	 * 排序类型
	 * 
	 * @author 张均锋
	 *
	 */
	public enum Order {
		ASC("asc"), DESC("desc");

		private String val;

		private Order(String val) {
			this.val = val;
		}

		/**
		 * 返回排序名称
		 * 
		 * @return
		 */
		public String val() {
			return this.val;
		}
	}
}
