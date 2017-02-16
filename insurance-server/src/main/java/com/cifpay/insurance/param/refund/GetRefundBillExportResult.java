package com.cifpay.insurance.param.refund;

import java.io.Serializable;

import com.cifpay.insurance.param.PageInfo;
import com.cifpay.insurance.util.StringUtils;
/**
 * 描述：导出退款单对象类型
 * 类名：GetRefundBillExportResult
 * @author 叶胜南
 *
 */
public class GetRefundBillExportResult extends PageInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 保险证号 **/
	private String insuranceCertNo;
	/** 退款状态（0-待退款；1-退款中；2-退款成功；9-退款失败；10-拒绝退款） **/
	private String billStatus;
	/** 开证时间 **/
	private String lcOpenTime;
	/** 解付时间 **/
	private String lcPayedTime;
	/** 退款金额（分） **/
	private String refundAmount;

	public String getInsuranceCertNo() {
		return insuranceCertNo;
	}

	public void setInsuranceCertNo(String insuranceCertNo) {
		this.insuranceCertNo = insuranceCertNo;
	}

	public String getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(String billStatus) {
		if(StringUtils.isNotEmpty(billStatus)){
			if(billStatus.equals("10")){
				this.billStatus ="拒绝解付";
			}else if(billStatus.equals("2")){
				this.billStatus ="已解付";
			}else if(billStatus.equals("1")){
				this.billStatus ="待解付";
			}else if(billStatus.equals("0")){
				this.billStatus ="已开证";
			}else if(billStatus.equals("-1")){
				this.billStatus ="开证失败";
			}else{
				this.billStatus ="未知";
			}
		}
		
	}

	public String getLcOpenTime() {
		return lcOpenTime;
	}

	public void setLcOpenTime(String lcOpenTime) {
		this.lcOpenTime = lcOpenTime;
	}

	public String getLcPayedTime() {
		return lcPayedTime;
	}

	public void setLcPayedTime(String lcPayedTime) {
		this.lcPayedTime = lcPayedTime;
	}

	public String getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}
	
	public String getStatusDes(){
		String statusDes = "";
		if(this.billStatus == "10")
	    statusDes = "拒绝解付";
		return statusDes;
	}

}
