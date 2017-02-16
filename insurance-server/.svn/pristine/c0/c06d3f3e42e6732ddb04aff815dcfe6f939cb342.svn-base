/**
 * File: OrderInfo.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年11月25日 下午3:09:55
 */
package com.cifpay.insurance.param.cert;

import java.io.Serializable;
import java.util.List;

/**
 * 订单信息
 * 
 * @author 张均锋
 *
 */
public class OrderInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 订单号 **/
	private String orderNo;
	/** 买家支付方式（1-银信证，0-其它） **/
	private Integer payMode;
	/** 订单日期 **/
	private String orderDate;
	/** 买家姓名 **/
	private String buyerName;
	/** 买家手机号 **/
	private String buyerMobilePhone;

	/** 商品订单明细信息 **/
	private List<OrderItemsInfo> orderItemsInfo;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getPayMode() {
		return payMode;
	}

	public void setPayMode(Integer payMode) {
		this.payMode = payMode;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getBuyerMobilePhone() {
		return buyerMobilePhone;
	}

	public void setBuyerMobilePhone(String buyerMobilePhone) {
		this.buyerMobilePhone = buyerMobilePhone;
	}

	public List<OrderItemsInfo> getOrderItemsInfo() {
		return orderItemsInfo;
	}

	public void setOrderItemsInfo(List<OrderItemsInfo> orderItemsInfo) {
		this.orderItemsInfo = orderItemsInfo;
	}

}
