/**
 * File: CreateInsuranceCertBatchInfo.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年11月25日 下午3:07:51
 */
package com.cifpay.insurance.param.cert;

import java.io.Serializable;
import java.util.List;

/**
 * 创建单个保险证信息
 * 
 * @author 张均锋
 *
 */
public class CreateInsuranceCertBatchInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 保单流水号 **/
	private Long policyId;
	/** 后台通知变更结果URL **/
	private String noticeUrl;

	/** 订单信息　 **/
	private List<OrderInfo> orderInfos;

	public Long getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Long policyId) {
		this.policyId = policyId;
	}

	public String getNoticeUrl() {
		return noticeUrl;
	}

	public void setNoticeUrl(String noticeUrl) {
		this.noticeUrl = noticeUrl;
	}

	public List<OrderInfo> getOrderInfos() {
		return orderInfos;
	}

	public void setOrderInfos(List<OrderInfo> orderInfos) {
		this.orderInfos = orderInfos;
	}

}
