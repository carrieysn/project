package com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.xmlbean.payent;

import java.util.ArrayList;
import java.util.List;

import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.xmlbean.NCRequest;

/**
 * 工行银企互联支付指令请求
 * 
 * 
 *
 */
public class PayentXmlRequest implements NCRequest {
	private String transCode = "PAYENT";
	private String cis;
	private String bankCode;
	private String id;
	private String tranDate;
	private String tranTime;
	private String fSeqno;
	private String onlBatF;
	private String settleMode;
	private String totalNum;
	private String totalAmt;
	private String signTime;
	private String reqReserved1;
	private String reqReserved2;

	private List<PayentXmlRequestRecordDetail> rds;

	public String getTransCode() {
		return transCode;
	}

	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}

	public String getCis() {
		return cis;
	}

	public void setCis(String cis) {
		this.cis = cis;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTranDate() {
		return tranDate;
	}

	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
	}

	public String getTranTime() {
		return tranTime;
	}

	public void setTranTime(String tranTime) {
		this.tranTime = tranTime;
	}

	public String getfSeqno() {
		return fSeqno;
	}

	public void setfSeqno(String fSeqno) {
		this.fSeqno = fSeqno;
	}

	public String getOnlBatF() {
		return onlBatF;
	}

	public void setOnlBatF(String onlBatF) {
		this.onlBatF = onlBatF;
	}

	public String getSettleMode() {
		return settleMode;
	}

	public void setSettleMode(String settleMode) {
		this.settleMode = settleMode;
	}

	public String getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(String totalNum) {
		this.totalNum = totalNum;
	}

	public String getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}

	public String getSignTime() {
		return signTime;
	}

	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}

	public String getReqReserved1() {
		return reqReserved1;
	}

	public void setReqReserved1(String reqReserved1) {
		this.reqReserved1 = reqReserved1;
	}

	public String getReqReserved2() {
		return reqReserved2;
	}

	public void setReqReserved2(String reqReserved2) {
		this.reqReserved2 = reqReserved2;
	}

	public List<PayentXmlRequestRecordDetail> getRds() {
		return rds;
	}

	public void setRds(List<PayentXmlRequestRecordDetail> rds) {
		this.rds = rds;
	}

	public void addRd(PayentXmlRequestRecordDetail rd) {
		if (null == rds) {
			rds = new ArrayList<PayentXmlRequestRecordDetail>();
		}
		rds.add(rd);
	}

}
