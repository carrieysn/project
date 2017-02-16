/**
 * File: GetInsuranceCertListInfo.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年11月25日 下午3:29:35
 */
package com.cifpay.insurance.param.cert;

import java.io.Serializable;

import com.cifpay.insurance.param.PageInfo;

/**
 * 查询保险证列表
 * 
 * @author 张均锋
 *
 */
public class GetInsuranceCertListInfo extends PageInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 退货开始日期 **/
	private String returnDateFrom;
	/** 退货结束日期 **/
	private String returnDateTo;
	/** 生成开始日期 **/
	private String createdTimeFrom;

	/** 生成结束日期 **/
	private String createdTimeTo;

	/** 状态（0-未生效；1-已签收；2-生效（正常）；5-待开证；10-待退款；12-已退款；14-拒绝退款；100-已失效） **/
	private String status;

	/** 保险证号 **/
	private String insuranceCertNo;

	public String getReturnDateFrom() {
		return returnDateFrom;
	}

	public void setReturnDateFrom(String returnDateFrom) {
		this.returnDateFrom = returnDateFrom;
	}

	public String getReturnDateTo() {
		return returnDateTo;
	}

	public void setReturnDateTo(String returnDateTo) {
		this.returnDateTo = returnDateTo;
	}

	public String getCreatedTimeFrom() {
		return createdTimeFrom;
	}

	public void setCreatedTimeFrom(String createdTimeFrom) {
		this.createdTimeFrom = createdTimeFrom;
	}

	public String getCreatedTimeTo() {
		return createdTimeTo;
	}

	public void setCreatedTimeTo(String createdTimeTo) {
		this.createdTimeTo = createdTimeTo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInsuranceCertNo() {
		return insuranceCertNo;
	}

	public void setInsuranceCertNo(String insuranceCertNo) {
		this.insuranceCertNo = insuranceCertNo;
	}

}
