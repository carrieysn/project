package com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.xmlbean.qpayent;

import javax.xml.bind.annotation.XmlElement;

/**
 * QPAYENT responded RD
 * 
 * 
 *
 */
public class QPayentXmlResponseRecordDetail {
	@XmlElement(name = "iSeqno")
	private String iSeqno;

	@XmlElement(name = "QryiSeqno")
	private String qryiSeqno;

	@XmlElement(name = "QryOrderNo")
	private String qryOrderNo;

	@XmlElement(name = "ReimburseNo")
	private String reimburseNo;

	@XmlElement(name = "ReimburseNum")
	private String reimburseNum;

	@XmlElement(name = "StartDate")
	private String startDate;

	@XmlElement(name = "StartTime")
	private String startTime;

	@XmlElement(name = "PayType")
	private String payType;

	@XmlElement(name = "PayAccNo")
	private String payAccNo;

	@XmlElement(name = "PayAccNameCN")
	private String payAccNameCN;

	@XmlElement(name = "PayAccNameEN")
	private String payAccNameEN;

	@XmlElement(name = "RecAccNo")
	private String recAccNo;

	@XmlElement(name = "RecAccNameCN")
	private String recAccNameCN;

	@XmlElement(name = "RecAccNameEN")
	private String recAccNameEN;

	@XmlElement(name = "SysIOFlg")
	private String sysIOFlg;

	@XmlElement(name = "IsSameCity")
	private String isSameCity;

	@XmlElement(name = "RecICBCCode")
	private String recICBCCode;

	@XmlElement(name = "RecBankNo")
	private String recBankNo;

	@XmlElement(name = "RecBankName")
	private String recBankName;

	@XmlElement(name = "CurrType")
	private String currType;

	@XmlElement(name = "PayAmt")
	private String payAmt;

	@XmlElement(name = "UseCode")
	private String useCode;

	@XmlElement(name = "UseCN")
	private String useCN;

	@XmlElement(name = "EnSummary")
	private String enSummary;

	@XmlElement(name = "PostScript")
	private String postScript;

	@XmlElement(name = "Summary")
	private String summary;

	@XmlElement(name = "Ref")
	private String ref;

	@XmlElement(name = "Oref")
	private String oref;

	@XmlElement(name = "ERPSqn")
	private String erpSqn;

	@XmlElement(name = "BusCode")
	private String busCode;

	@XmlElement(name = "ERPcheckno")
	private String erpCheckno;

	@XmlElement(name = "CrvouhType")
	private String crvouhType;

	@XmlElement(name = "CrvouhName")
	private String crvouhName;

	@XmlElement(name = "CrvouhNo")
	private String crvouhNo;

	@XmlElement(name = "iRetCode")
	private String iRetCode;

	@XmlElement(name = "iRetMsg")
	private String iRetMsg;

	@XmlElement(name = "Result")
	private String result;

	@XmlElement(name = "instrRetCode")
	private String instrRetCode;

	@XmlElement(name = "instrRetMsg")
	private String instrRetMsg;

	@XmlElement(name = "BankRetTime")
	private String bankRetTime;

	@XmlElement(name = "RepReserved3")
	private String repReserved3;

	@XmlElement(name = "RepReserved4")
	private String repReserved4;

	public String getiSeqno() {
		return iSeqno;
	}

	public void setiSeqno(String iSeqno) {
		this.iSeqno = iSeqno;
	}

	public String getQryiSeqno() {
		return qryiSeqno;
	}

	public void setQryiSeqno(String qryiSeqno) {
		this.qryiSeqno = qryiSeqno;
	}

	public String getQryOrderNo() {
		return qryOrderNo;
	}

	public void setQryOrderNo(String qryOrderNo) {
		this.qryOrderNo = qryOrderNo;
	}

	public String getReimburseNo() {
		return reimburseNo;
	}

	public void setReimburseNo(String reimburseNo) {
		this.reimburseNo = reimburseNo;
	}

	public String getReimburseNum() {
		return reimburseNum;
	}

	public void setReimburseNum(String reimburseNum) {
		this.reimburseNum = reimburseNum;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPayAccNo() {
		return payAccNo;
	}

	public void setPayAccNo(String payAccNo) {
		this.payAccNo = payAccNo;
	}

	public String getPayAccNameCN() {
		return payAccNameCN;
	}

	public void setPayAccNameCN(String payAccNameCN) {
		this.payAccNameCN = payAccNameCN;
	}

	public String getPayAccNameEN() {
		return payAccNameEN;
	}

	public void setPayAccNameEN(String payAccNameEN) {
		this.payAccNameEN = payAccNameEN;
	}

	public String getRecAccNo() {
		return recAccNo;
	}

	public void setRecAccNo(String recAccNo) {
		this.recAccNo = recAccNo;
	}

	public String getRecAccNameCN() {
		return recAccNameCN;
	}

	public void setRecAccNameCN(String recAccNameCN) {
		this.recAccNameCN = recAccNameCN;
	}

	public String getRecAccNameEN() {
		return recAccNameEN;
	}

	public void setRecAccNameEN(String recAccNameEN) {
		this.recAccNameEN = recAccNameEN;
	}

	public String getSysIOFlg() {
		return sysIOFlg;
	}

	public void setSysIOFlg(String sysIOFlg) {
		this.sysIOFlg = sysIOFlg;
	}

	public String getIsSameCity() {
		return isSameCity;
	}

	public void setIsSameCity(String isSameCity) {
		this.isSameCity = isSameCity;
	}

	public String getRecICBCCode() {
		return recICBCCode;
	}

	public void setRecICBCCode(String recICBCCode) {
		this.recICBCCode = recICBCCode;
	}

	public String getRecBankNo() {
		return recBankNo;
	}

	public void setRecBankNo(String recBankNo) {
		this.recBankNo = recBankNo;
	}

	public String getRecBankName() {
		return recBankName;
	}

	public void setRecBankName(String recBankName) {
		this.recBankName = recBankName;
	}

	public String getCurrType() {
		return currType;
	}

	public void setCurrType(String currType) {
		this.currType = currType;
	}

	public String getPayAmt() {
		return payAmt;
	}

	public void setPayAmt(String payAmt) {
		this.payAmt = payAmt;
	}

	public String getUseCode() {
		return useCode;
	}

	public void setUseCode(String useCode) {
		this.useCode = useCode;
	}

	public String getUseCN() {
		return useCN;
	}

	public void setUseCN(String useCN) {
		this.useCN = useCN;
	}

	public String getEnSummary() {
		return enSummary;
	}

	public void setEnSummary(String enSummary) {
		this.enSummary = enSummary;
	}

	public String getPostScript() {
		return postScript;
	}

	public void setPostScript(String postScript) {
		this.postScript = postScript;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getOref() {
		return oref;
	}

	public void setOref(String oref) {
		this.oref = oref;
	}

	public String getErpSqn() {
		return erpSqn;
	}

	public void setErpSqn(String erpSqn) {
		this.erpSqn = erpSqn;
	}

	public String getBusCode() {
		return busCode;
	}

	public void setBusCode(String busCode) {
		this.busCode = busCode;
	}

	public String getErpCheckno() {
		return erpCheckno;
	}

	public void setErpCheckno(String erpCheckno) {
		this.erpCheckno = erpCheckno;
	}

	public String getCrvouhType() {
		return crvouhType;
	}

	public void setCrvouhType(String crvouhType) {
		this.crvouhType = crvouhType;
	}

	public String getCrvouhName() {
		return crvouhName;
	}

	public void setCrvouhName(String crvouhName) {
		this.crvouhName = crvouhName;
	}

	public String getCrvouhNo() {
		return crvouhNo;
	}

	public void setCrvouhNo(String crvouhNo) {
		this.crvouhNo = crvouhNo;
	}

	public String getiRetCode() {
		return iRetCode;
	}

	public void setiRetCode(String iRetCode) {
		this.iRetCode = iRetCode;
	}

	public String getiRetMsg() {
		return iRetMsg;
	}

	public void setiRetMsg(String iRetMsg) {
		this.iRetMsg = iRetMsg;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getInstrRetCode() {
		return instrRetCode;
	}

	public void setInstrRetCode(String instrRetCode) {
		this.instrRetCode = instrRetCode;
	}

	public String getInstrRetMsg() {
		return instrRetMsg;
	}

	public void setInstrRetMsg(String instrRetMsg) {
		this.instrRetMsg = instrRetMsg;
	}

	public String getBankRetTime() {
		return bankRetTime;
	}

	public void setBankRetTime(String bankRetTime) {
		this.bankRetTime = bankRetTime;
	}

	public String getRepReserved3() {
		return repReserved3;
	}

	public void setRepReserved3(String repReserved3) {
		this.repReserved3 = repReserved3;
	}

	public String getRepReserved4() {
		return repReserved4;
	}

	public void setRepReserved4(String repReserved4) {
		this.repReserved4 = repReserved4;
	}

}
