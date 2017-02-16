/**
 * File: InsuranceCertStateChangeEvent.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月2日 上午9:33:43
 */
package com.cifpay.insurance.push.event;

import java.util.EventObject;

/**
 * 保险证状态改变事件。
 * 
 * @author 张均锋
 *
 */
public class InsuranceCertStateChangeEvent extends EventObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 商户ID **/
	private String vendorId;
	/** 保险证ID **/
	private long insInsuranceCertId;
	/** 保险证号 **/
	private long insInsuranceCertNo;
	/** 改变前状态 **/
	private int preStatus;
	/** 改变后状态 **/
	private int status;
	/** 订单号 **/
	private String orderNo;
	/** 商品编号 **/
	private String goodsNo;
	/** 商品名称 **/
	private String goodsName;
	/** 保险证商品总价 **/
	private long totalPrice;
	/** 是否保险公司退款 **/
	private Integer isIcRefund;
	/** 标识 **/
	private int tag;

	public InsuranceCertStateChangeEvent(Object source) {
		super(source);
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public long getInsInsuranceCertId() {
		return insInsuranceCertId;
	}

	public void setInsInsuranceCertId(long insInsuranceCertId) {
		this.insInsuranceCertId = insInsuranceCertId;
	}

	public long getInsInsuranceCertNo() {
		return insInsuranceCertNo;
	}

	public void setInsInsuranceCertNo(long insInsuranceCertNo) {
		this.insInsuranceCertNo = insInsuranceCertNo;
	}

	public int getPreStatus() {
		return preStatus;
	}

	public void setPreStatus(int preStatus) {
		this.preStatus = preStatus;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getGoodsNo() {
		return goodsNo;
	}

	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getIsIcRefund() {
		return isIcRefund;
	}

	public void setIsIcRefund(Integer isIcRefund) {
		this.isIcRefund = isIcRefund;
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

}
