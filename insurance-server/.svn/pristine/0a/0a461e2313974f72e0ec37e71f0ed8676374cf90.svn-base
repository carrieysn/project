/**
 * File: GetInsuranceCertListResult.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年11月25日 下午3:42:39
 */
package com.cifpay.insurance.param.cert;

import java.io.Serializable;

/**
 * 查询保险证列表结果
 * 
 * @author 张均锋
 *
 */
public class GetInsuranceCertListResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 保单号 **/
	private String policyNo;
	/** 保险证号 **/
	private String insuranceCertNo;
	/** 商品单价（分） **/
	private Long price;
	/** 数量 **/
	private Integer quantity;

	/** 生成时间 **/
	private String createdTime;
	/** 过期时间 由生效时间和生效期计算出 **/
	private String expiredTime;
	/** 退货时间 **/
	private String returnDate;
	/** 是否开证（1-是；0-否） **/
	private Integer isLcOpen;
	/** 状态：0-未生效；10-待退款；12-已退款；14-拒绝退款；100-已失效 **/
	private Integer status;

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getInsuranceCertNo() {
		return insuranceCertNo;
	}

	public void setInsuranceCertNo(String insuranceCertNo) {
		this.insuranceCertNo = insuranceCertNo;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(String expiredTime) {
		this.expiredTime = expiredTime;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public Integer getIsLcOpen() {
		return isLcOpen;
	}

	public void setIsLcOpen(Integer isLcOpen) {
		this.isLcOpen = isLcOpen;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
