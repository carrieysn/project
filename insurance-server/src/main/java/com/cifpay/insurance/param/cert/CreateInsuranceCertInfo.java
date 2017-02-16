/**
 * File: CreateInsuranceCertInfo.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年11月25日 下午3:07:51
 */
package com.cifpay.insurance.param.cert;

import java.io.Serializable;

/**
 * 创建单个保险证信息
 * 
 * @author 张均锋
 *
 */
public class CreateInsuranceCertInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 保单流水号 **/
	//private Long policyId;
	/** 后台通知变更结果URL **/
	private String noticeUrl;

	/** 订单信息　 **/
	private OrderInfo orderInfo;

	/*public Long getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Long policyId) {
		this.policyId = policyId;
	}*/

	public String getNoticeUrl() {
		return noticeUrl;
	}

	public void setNoticeUrl(String noticeUrl) {
		this.noticeUrl = noticeUrl;
	}

	public OrderInfo getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}


}
