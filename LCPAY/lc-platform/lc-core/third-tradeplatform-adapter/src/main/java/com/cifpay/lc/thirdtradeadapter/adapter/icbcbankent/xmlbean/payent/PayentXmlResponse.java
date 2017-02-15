package com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.xmlbean.payent;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.eclipse.persistence.oxm.annotations.XmlPath;

/**
 * 工行银企互联支付指令返回报文
 * 
 * 
 *
 */
@XmlRootElement(name = "CMS")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({ PayentXmlResponseRecordDetail.class })
public class PayentXmlResponse {
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

	@XmlPath("eb/pub/SerialNo/text()")
	private String serialNo;

	@XmlPath("eb/pub/RetCode/text()")
	private String retCode;

	@XmlPath("eb/pub/RetMsg/text()")
	private String retMsg;

	@XmlPath("eb/out/OnlBatF/text()")
	private String onlBatF;

	@XmlPath("eb/out/SettleMode/text()")
	private String settleMode;

	@XmlPath("eb/out/TotalNum/text()")
	private String totalNum;

	@XmlPath("eb/out/TotalAmt/text()")
	private String totalAmt;

	@XmlPath("eb/out/RepReserved1/text()")
	private String repReserved1;

	@XmlPath("eb/out/RepReserved2/text()")
	private String repReserved2;

	@XmlPath("eb/out/rd")
	private List<PayentXmlResponseRecordDetail> rds;

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

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
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

	public String getRepReserved1() {
		return repReserved1;
	}

	public void setRepReserved1(String repReserved1) {
		this.repReserved1 = repReserved1;
	}

	public String getRepReserved2() {
		return repReserved2;
	}

	public void setRepReserved2(String repReserved2) {
		this.repReserved2 = repReserved2;
	}

	public List<PayentXmlResponseRecordDetail> getRds() {
		return rds;
	}

	public void setRds(List<PayentXmlResponseRecordDetail> rds) {
		this.rds = rds;
	}

}
