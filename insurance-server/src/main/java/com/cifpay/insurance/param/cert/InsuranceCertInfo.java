/**
 * File: InsuranceCertInfo.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月19日 下午2:28:40
 */
package com.cifpay.insurance.param.cert;

import java.util.Date;

/**
 * 
 * 保险证及其关联保单、商品订单信息。
 * 
 * @author 张均锋
 *
 */
public class InsuranceCertInfo {
	/** 保险证ID **/
	private String insuranceCertId;
	/** 保单ID **/
	private Long policyId;
	/** 保单号 **/
	private String policyNo;
	/** 保险证号 **/
	private String insuranceCertNo;
	/** 生效时间 **/
	private Date effectiveTime;
	/** 过期时间 **/
	private Date expiredTime;
	/** 状态（0-未生效；1-已签收；2-生效（正常）；10-待退款；12-已退款；14-拒绝退款；100-已失效） **/
	private Integer status;
	/** 是否签收（1-是；0-否） **/
	private Integer isSign;
	/** 商品销售订单信息 **/
	private InsCertOrderItem insCertOrderItem;
	/** 保单信息 **/
	private InsPolicyInfo insPolicyInfo;

	public String getInsuranceCertId() {
		return insuranceCertId;
	}

	public void setInsuranceCertId(String insuranceCertId) {
		this.insuranceCertId = insuranceCertId;
	}

	public Long getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Long policyId) {
		this.policyId = policyId;
	}

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

	public Date getEffectiveTime() {
		return effectiveTime;
	}

	public void setEffectiveTime(Date effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	public Date getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsSign() {
		return isSign;
	}

	public void setIsSign(Integer isSign) {
		this.isSign = isSign;
	}

	public InsCertOrderItem getInsCertOrderItem() {
		return insCertOrderItem;
	}

	public void setInsCertOrderItem(InsCertOrderItem insCertOrderItem) {
		this.insCertOrderItem = insCertOrderItem;
	}

	public InsPolicyInfo getInsPolicyInfo() {
		return insPolicyInfo;
	}

	public void setInsPolicyInfo(InsPolicyInfo insPolicyInfo) {
		this.insPolicyInfo = insPolicyInfo;
	}

	public class InsPolicyInfo {
		/** 保单流水号 **/
		private Long policyId;
		/** 保单号 **/
		private String policyNo;
		/** 投保人 **/
		private String holderName;
		/** 被保人 **/
		private String insuredName;
		/** 保险人名称 **/
		private String insurername;

		public Long getPolicyId() {
			return policyId;
		}

		public void setPolicyId(Long policyId) {
			this.policyId = policyId;
		}

		public String getPolicyNo() {
			return policyNo;
		}

		public void setPolicyNo(String policyNo) {
			this.policyNo = policyNo;
		}

		public String getHolderName() {
			return holderName;
		}

		public void setHolderName(String holderName) {
			this.holderName = holderName;
		}

		public String getInsuredName() {
			return insuredName;
		}

		public void setInsuredName(String insuredName) {
			this.insuredName = insuredName;
		}

		public String getInsurername() {
			return insurername;
		}

		public void setInsurername(String insurername) {
			this.insurername = insurername;
		}

	}

	/** 保险证订单信息 **/
	public class InsCertOrderInfo {
		/** 订单号 **/
		private String orderNo;
		/** 退货受理地址 **/
		private String returnAddress;
		/** 退货受理联系人 **/
		private String returnContacts;
		/** 退货受理联系人电话 **/
		private String returnPhone;

		public String getOrderNo() {
			return orderNo;
		}

		public void setOrderNo(String orderNo) {
			this.orderNo = orderNo;
		}

		public String getReturnAddress() {
			return returnAddress;
		}

		public void setReturnAddress(String returnAddress) {
			this.returnAddress = returnAddress;
		}

		public String getReturnContacts() {
			return returnContacts;
		}

		public void setReturnContacts(String returnContacts) {
			this.returnContacts = returnContacts;
		}

		public String getReturnPhone() {
			return returnPhone;
		}

		public void setReturnPhone(String returnPhone) {
			this.returnPhone = returnPhone;
		}

	}

	/** 保险证商品信息 **/
	public class InsCertOrderItem {
		/** 商品编号 **/
		private String goodsNo;
		/** 商品名称 **/
		private String goodsName;
		/** 商品单价（分） **/
		private Long price;
		/** 数量 **/
		private Integer quantity;
		/** 商品总价（分） **/
		private Long totalPrice;
		/** 保险证商品订单信息 **/
		private InsCertOrderInfo insCertOrderInfo;

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

		public Long getTotalPrice() {
			return totalPrice;
		}

		public void setTotalPrice(Long totalPrice) {
			this.totalPrice = totalPrice;
		}

		public InsCertOrderInfo getInsCertOrderInfo() {
			return insCertOrderInfo;
		}

		public void setInsCertOrderInfo(InsCertOrderInfo insCertOrderInfo) {
			this.insCertOrderInfo = insCertOrderInfo;
		}

	}
}
