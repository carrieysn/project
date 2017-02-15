package com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.xmlbean.qpayent;

import java.util.ArrayList;
import java.util.List;

import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.xmlbean.NCRequest;

/**
 * 工行银企互联支付查询指令请求
 * 
 * 
 *
 */
public class QPayentXmlRequest implements NCRequest {
	private String transCode = "QPAYENT";
	private String cis;
	private String bankCode;
	private String id;
	private String tranDate;
	private String tranTime;
	private String fSeqno;
	private String qryfSeqno;
	private String qrySerialNo;
	private String reqReserved1;
	private String reqReserved2;

	private List<QPayentXmlRequestRecordDetail> rds;

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

	public String getQryfSeqno() {
		return qryfSeqno;
	}

	public void setQryfSeqno(String qryfSeqno) {
		this.qryfSeqno = qryfSeqno;
	}

	public String getQrySerialNo() {
		return qrySerialNo;
	}

	public void setQrySerialNo(String qrySerialNo) {
		this.qrySerialNo = qrySerialNo;
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

	public List<QPayentXmlRequestRecordDetail> getRds() {
		return rds;
	}

	public void setRds(List<QPayentXmlRequestRecordDetail> rds) {
		this.rds = rds;
	}

	public void addRd(QPayentXmlRequestRecordDetail rd) {
		if (null == rds) {
			rds = new ArrayList<QPayentXmlRequestRecordDetail>();
		}
		rds.add(rd);
	}

}
