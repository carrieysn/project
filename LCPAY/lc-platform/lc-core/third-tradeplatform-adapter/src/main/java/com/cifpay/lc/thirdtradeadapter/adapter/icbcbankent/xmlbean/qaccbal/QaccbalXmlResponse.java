package com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.xmlbean.qaccbal;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.eclipse.persistence.oxm.annotations.XmlPath;

/**
 * QACCBAL
 * 
 * 
 *
 */
@XmlRootElement(name = "CMS")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({ QaccbalXmlResponseRecordDetail.class })
public class QaccbalXmlResponse {

	@XmlPath("eb/pub/TransCode/text()")
	private String transCode;

	@XmlPath("eb/pub/CIS/text()")
	private String cis;

	@XmlPath("eb/pub/BankCode/text()")
	private String bankCode;

	@XmlPath("eb/pub/ID/text()")
	private String id;

	@XmlPath("eb/pub/TranDate/text()")
	private String tranDate;

	@XmlPath("eb/pub/TranTime/text()")
	private String tranTime;

	@XmlPath("eb/pub/fSeqno/text()")
	private String fSeqno;

	@XmlPath("eb/pub/RetCode/text()")
	private String retCode;

	@XmlPath("eb/pub/RetMsg/text()")
	private String retMsg;

	@XmlPath("eb/out/rd")
	private List<QaccbalXmlResponseRecordDetail> rds;

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

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	public List<QaccbalXmlResponseRecordDetail> getRds() {
		return rds;
	}

	public void setRds(List<QaccbalXmlResponseRecordDetail> rds) {
		this.rds = rds;
	}

}
