package com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.helper;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.springframework.util.FileCopyUtils;

import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.xmlbean.payent.PayentXmlResponse;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.xmlbean.payent.PayentXmlResponseRecordDetail;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.xmlbean.qpayent.QPayentXmlResponse;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.xmlbean.qpayent.QPayentXmlResponseRecordDetail;

import junit.framework.Assert;

/**
 * 
 *
 */
public class ICBCBankEntXmlResponseParserTest {

	@Test
	public void testParseXmlResponsePAYENT() throws IOException {
		byte[] bytes = FileCopyUtils.copyToByteArray(
				ICBCBankEntXmlResponseParserTest.class.getResourceAsStream("TEST-PAYENT-RESPONSE.xml"));
		String xmlString = new String(bytes, "GBK");

		System.out.println("输入的xmlString: ");
		System.out.println(xmlString);
		
		ICBCBankEntXmlResponseParser parser = new ICBCBankEntXmlResponseParser();
		PayentXmlResponse payentXmlResponse = parser.parseXmlResponse(xmlString, PayentXmlResponse.class);
		Assert.assertNotNull(payentXmlResponse);

		String transCode = payentXmlResponse.getTransCode();
		String cis = payentXmlResponse.getCis();
		String bankCode = payentXmlResponse.getBankCode();
		String id = payentXmlResponse.getId();
		String tranDate = payentXmlResponse.getTranDate();
		String tranTime = payentXmlResponse.getTranTime();
		String fSeqNo = payentXmlResponse.getfSeqno();
		String serialNo = payentXmlResponse.getSerialNo();
		String retCode = payentXmlResponse.getRetCode();
		String retMsg = payentXmlResponse.getRetMsg();
		
		String onlBatF = payentXmlResponse.getOnlBatF();
		String settleMode= payentXmlResponse.getSettleMode();
		String totalNum = payentXmlResponse.getTotalNum();
		String totalAmt = payentXmlResponse.getTotalAmt();
		String repReserved1 = payentXmlResponse.getRepReserved1();
		String repReserved2 = payentXmlResponse.getRepReserved2();
		
		Assert.assertTrue(null != transCode && transCode.trim().length() > 0);
		Assert.assertTrue(null != cis && cis.trim().length() > 0);
		Assert.assertTrue(null != bankCode && bankCode.trim().length() > 0);
		Assert.assertTrue(null != id && id.trim().length() > 0);
		Assert.assertTrue(null != tranDate && tranDate.trim().length() > 0);
		Assert.assertTrue(null != tranTime && tranTime.trim().length() > 0);
		Assert.assertTrue(null != fSeqNo && fSeqNo.trim().length() > 0);
		Assert.assertTrue(null != serialNo && serialNo.trim().length() > 0);
		Assert.assertTrue(null != retCode && retCode.trim().length() > 0);
		Assert.assertTrue(null != retMsg && retMsg.trim().length() > 0);
		Assert.assertTrue(null != onlBatF && onlBatF.trim().length() > 0);
		Assert.assertTrue(null != settleMode && settleMode.trim().length() > 0);
		Assert.assertTrue(null != totalNum && totalNum.trim().length() > 0);
		Assert.assertTrue(null != totalAmt && totalAmt.trim().length() > 0);
		Assert.assertTrue(null != repReserved1 && repReserved1.trim().length() > 0);
		Assert.assertTrue(null != repReserved2 && repReserved2.trim().length() > 0);
		
		
		System.out.println("transCode: " + transCode);
		System.out.println("cis: " + cis);
		System.out.println("bankCode: " + bankCode);
		System.out.println("id: " + id);
		System.out.println("tranDate: " + tranDate);
		System.out.println("tranTime: " + tranTime);
		System.out.println("fSeqNo: " + fSeqNo);
		System.out.println("serialNo: " + serialNo);
		System.out.println("retCode: " + retCode);
		System.out.println("retMsg: " + retMsg);
		System.out.println("onlBatF: " + onlBatF);
		System.out.println("settleMode: " + settleMode);
		System.out.println("totalNum: " + totalNum);
		System.out.println("totalAmt: " + totalAmt);
		System.out.println("repReserved1: " + repReserved1);
		System.out.println("repReserved2: " + repReserved2);

		List<PayentXmlResponseRecordDetail> rds = payentXmlResponse.getRds();
		Assert.assertNotNull(rds);
		Assert.assertTrue(rds.size() > 0);

		for (PayentXmlResponseRecordDetail rd : rds) {
			String iSeqno = rd.getiSeqno();
			String OrderNo = rd.getOrderNo();
			String ReimburseNo = rd.getReimburseNo();
			String ReimburseNum = rd.getReimburseNum();
			String StartDate = rd.getStartDate();
			String StartTime = rd.getStartTime();
			String PayType = rd.getPayType();
			String PayAccNo = rd.getPayAccNo();
			String PayAccNameCN = rd.getPayAccNameCN();
			String PayAccNameEN = rd.getPayAccNameEN();
			String RecAccNo = rd.getRecAccNo();
			String RecAccNameCN = rd.getRecAccNameCN();
			String RecAccNameEN = rd.getRecAccNameEN();
			String SysIOFlg = rd.getSysIOFlg();
			String IsSameCity = rd.getIsSameCity();
			String Prop = rd.getProp();
			String RecICBCCode = rd.getRecICBCCode();
			String RecCityName = rd.getRecCityName();
			String RecBankNo = rd.getRecBankNo();
			String RecBankName = rd.getRecBankName();
			String CurrType = rd.getCurrType();
			String PayAmt = rd.getPayAmt();
			String UseCode = rd.getUseCode();
			String UseCN = rd.getUseCN();
			String EnSummary = rd.getEnSummary();
			String PostScript = rd.getPostScript();
			String Summary = rd.getSummary();
			String Ref = rd.getRef();
			String Oref = rd.getOref();
			String eRPSqn = rd.getErpSqn();
			String BusCode = rd.getBusCode();
			String ERPcheckno = rd.getErpCheckno();
			String CrvouhType = rd.getCrvouhType();
			String CrvouhName = rd.getCrvouhName();
			String CrvouhNo = rd.getCrvouhNo();
			String Result = rd.getResult();
			String iRetCode = rd.getiRetCode();
			String iRetMsg = rd.getiRetMsg();
			String RepReserved3 = rd.getRepReserved3();
			String RepReserved4 = rd.getRepReserved4();
			
			
			Assert.assertTrue(null != iSeqno && iSeqno.trim().length() > 0);
			Assert.assertTrue(null != OrderNo && OrderNo.trim().length() > 0);
			Assert.assertTrue(null != ReimburseNo && ReimburseNo.trim().length() > 0);
			Assert.assertTrue(null != ReimburseNum && ReimburseNum.trim().length() > 0);
			Assert.assertTrue(null != StartDate && StartDate.trim().length() > 0);
			Assert.assertTrue(null != StartTime && StartTime.trim().length() > 0);
			Assert.assertTrue(null != PayType && PayType.trim().length() > 0);
			Assert.assertTrue(null != PayAccNo && PayAccNo.trim().length() > 0);
			Assert.assertTrue(null != PayAccNameCN && PayAccNameCN.trim().length() > 0);
			Assert.assertTrue(null != PayAccNameEN && PayAccNameEN.trim().length() > 0);
			Assert.assertTrue(null != RecAccNo && RecAccNo.trim().length() > 0);
			Assert.assertTrue(null != RecAccNameCN && RecAccNameCN.trim().length() > 0);
			Assert.assertTrue(null != RecAccNameEN && RecAccNameEN.trim().length() > 0);
			Assert.assertTrue(null != SysIOFlg && SysIOFlg.trim().length() > 0);
			Assert.assertTrue(null != IsSameCity && IsSameCity.trim().length() > 0);
			Assert.assertTrue(null != Prop && Prop.trim().length() > 0);
			Assert.assertTrue(null != RecICBCCode && RecICBCCode.trim().length() > 0);
			Assert.assertTrue(null != RecCityName && RecCityName.trim().length() > 0);
			Assert.assertTrue(null != RecBankNo && RecBankNo.trim().length() > 0);
			Assert.assertTrue(null != RecBankName && RecBankName.trim().length() > 0);
			Assert.assertTrue(null != CurrType && CurrType.trim().length() > 0);
			Assert.assertTrue(null != PayAmt && PayAmt.trim().length() > 0);
			Assert.assertTrue(null != UseCode && UseCode.trim().length() > 0);
			Assert.assertTrue(null != UseCN && UseCN.trim().length() > 0);
			Assert.assertTrue(null != EnSummary && EnSummary.trim().length() > 0);
			Assert.assertTrue(null != PostScript && PostScript.trim().length() > 0);
			Assert.assertTrue(null != Summary && Summary.trim().length() > 0);
			Assert.assertTrue(null != Ref && Ref.trim().length() > 0);
			Assert.assertTrue(null != Oref && Oref.trim().length() > 0);
			Assert.assertTrue(null != eRPSqn && eRPSqn.trim().length() > 0);
			Assert.assertTrue(null != BusCode && BusCode.trim().length() > 0);
			Assert.assertTrue(null != ERPcheckno && ERPcheckno.trim().length() > 0);
			Assert.assertTrue(null != CrvouhType && CrvouhType.trim().length() > 0);
			Assert.assertTrue(null != CrvouhName && CrvouhName.trim().length() > 0);
			Assert.assertTrue(null != CrvouhNo && CrvouhNo.trim().length() > 0);
			Assert.assertTrue(null != Result && Result.trim().length() > 0);
			Assert.assertTrue(null != iRetCode && iRetCode.trim().length() > 0);
			Assert.assertTrue(null != iRetMsg && iRetMsg.trim().length() > 0);
			Assert.assertTrue(null != RepReserved3 && RepReserved3.trim().length() > 0);
			Assert.assertTrue(null != RepReserved4 && RepReserved4.trim().length() > 0);
			
			System.out.println("------------------------");
			System.out.println("iSeqno: " + iSeqno);
			System.out.println("OrderNo: " + OrderNo);
			System.out.println("ReimburseNo: " + ReimburseNo);
			System.out.println("ReimburseNum: " + ReimburseNum);
			System.out.println("StartDate: " + StartDate);
			System.out.println("StartTime: " + StartTime);
			System.out.println("PayType: " + PayType);
			System.out.println("PayAccNo: " + PayAccNo);
			System.out.println("PayAccNameCN: " + PayAccNameCN);
			System.out.println("PayAccNameEN: " + PayAccNameEN);
			System.out.println("RecAccNo: " + RecAccNo);
			System.out.println("RecAccNameCN: " + RecAccNameCN);
			System.out.println("RecAccNameEN: " + RecAccNameEN);
			System.out.println("SysIOFlg: " + SysIOFlg);
			System.out.println("IsSameCity: " + IsSameCity);
			System.out.println("Prop: " + Prop);
			System.out.println("RecICBCCode: " + RecICBCCode);
			System.out.println("RecCityName: " + RecCityName);
			System.out.println("RecBankNo: " + RecBankNo);
			System.out.println("RecBankName: " + RecBankName);
			System.out.println("CurrType: " + CurrType);
			System.out.println("PayAmt: " + PayAmt);
			System.out.println("UseCode: " + UseCode);
			System.out.println("UseCN: " + UseCN);
			System.out.println("EnSummary: " + EnSummary);
			System.out.println("PostScript: " + PostScript);
			System.out.println("Summary: " + Summary);
			System.out.println("Ref: " + Ref);
			System.out.println("Oref: " + Oref);
			System.out.println("RPSqn: " + eRPSqn);
			System.out.println("BusCode: " + BusCode);
			System.out.println("ERPcheckno: " + ERPcheckno);
			System.out.println("CrvouhType: " + CrvouhType);
			System.out.println("CrvouhName: " + CrvouhName);
			System.out.println("CrvouhNo: " + CrvouhNo);
			System.out.println("Result: " + Result);
			System.out.println("iRetCode: " + iRetCode);
			System.out.println("iRetMsg: " + iRetMsg);
			System.out.println("RepReserved3: " + RepReserved3);
			System.out.println("RepReserved4: " + RepReserved4);
		}
	}
	
	@Test
	public void testParseXmlResponseQPAYENT() throws IOException {
		byte[] bytes = FileCopyUtils.copyToByteArray(
				ICBCBankEntXmlResponseParserTest.class.getResourceAsStream("TEST-QPAYENT-RESPONSE.xml"));
		String xmlString = new String(bytes, "GBK");

		System.out.println("输入的xmlString: ");
		System.out.println(xmlString);
		
		ICBCBankEntXmlResponseParser parser = new ICBCBankEntXmlResponseParser();
		QPayentXmlResponse payentXmlResponse = parser.parseXmlResponse(xmlString, QPayentXmlResponse.class);
		Assert.assertNotNull(payentXmlResponse);

		String transCode = payentXmlResponse.getTransCode();
		String cis = payentXmlResponse.getCis();
		String bankCode = payentXmlResponse.getBankCode();
		String id = payentXmlResponse.getId();
		String tranDate = payentXmlResponse.getTranDate();
		String tranTime = payentXmlResponse.getTranTime();
		String fSeqNo = payentXmlResponse.getfSeqno();
		String retCode = payentXmlResponse.getRetCode();
		String retMsg = payentXmlResponse.getRetMsg();
		
		String qryfSeqno = payentXmlResponse.getQryfSeqno();
		String qrySerialNo = payentXmlResponse.getQrySerialNo();
		String onlBatF = payentXmlResponse.getOnlBatF();
		String settleMode= payentXmlResponse.getSettleMode();
		String busType = payentXmlResponse.getBusType();
		String repReserved1 = payentXmlResponse.getRepReserved1();
		String repReserved2 = payentXmlResponse.getRepReserved2();
		
		Assert.assertTrue(null != transCode && transCode.trim().length() > 0);
		Assert.assertTrue(null != cis && cis.trim().length() > 0);
		Assert.assertTrue(null != bankCode && bankCode.trim().length() > 0);
		Assert.assertTrue(null != id && id.trim().length() > 0);
		Assert.assertTrue(null != tranDate && tranDate.trim().length() > 0);
		Assert.assertTrue(null != tranTime && tranTime.trim().length() > 0);
		Assert.assertTrue(null != fSeqNo && fSeqNo.trim().length() > 0);
		Assert.assertTrue(null != retCode && retCode.trim().length() > 0);
		Assert.assertTrue(null != retMsg && retMsg.trim().length() > 0);
		Assert.assertTrue(null != qryfSeqno && qryfSeqno.trim().length() > 0);
		Assert.assertTrue(null != qrySerialNo && qrySerialNo.trim().length() > 0);
		Assert.assertTrue(null != onlBatF && onlBatF.trim().length() > 0);
		Assert.assertTrue(null != settleMode && settleMode.trim().length() > 0);
		Assert.assertTrue(null != busType && busType.trim().length() > 0);
		Assert.assertTrue(null != repReserved1 && repReserved1.trim().length() > 0);
		Assert.assertTrue(null != repReserved2 && repReserved2.trim().length() > 0);
		
		
		System.out.println("transCode: " + transCode);
		System.out.println("cis: " + cis);
		System.out.println("bankCode: " + bankCode);
		System.out.println("id: " + id);
		System.out.println("tranDate: " + tranDate);
		System.out.println("tranTime: " + tranTime);
		System.out.println("fSeqNo: " + fSeqNo);
		System.out.println("retCode: " + retCode);
		System.out.println("retMsg: " + retMsg);
		
		System.out.println("qryfSeqno: " + qryfSeqno);
		System.out.println("qrySerialNo: " + qrySerialNo);
		System.out.println("onlBatF: " + onlBatF);
		System.out.println("settleMode: " + settleMode);
		System.out.println("busType: " + busType);
		System.out.println("repReserved1: " + repReserved1);
		System.out.println("repReserved2: " + repReserved2);

		List<QPayentXmlResponseRecordDetail> rds = payentXmlResponse.getRds();
		Assert.assertNotNull(rds);
		Assert.assertTrue(rds.size() > 0);

		for (QPayentXmlResponseRecordDetail rd : rds) {
			String iSeqno = rd.getiSeqno();
			String QryOrderNo = rd.getQryOrderNo();
			String ReimburseNo = rd.getReimburseNo();
			String ReimburseNum = rd.getReimburseNum();
			String StartDate = rd.getStartDate();
			String StartTime = rd.getStartTime();
			String PayType = rd.getPayType();
			String PayAccNo = rd.getPayAccNo();
			String PayAccNameCN = rd.getPayAccNameCN();
			String PayAccNameEN = rd.getPayAccNameEN();
			String RecAccNo = rd.getRecAccNo();
			String RecAccNameCN = rd.getRecAccNameCN();
			String RecAccNameEN = rd.getRecAccNameEN();
			String SysIOFlg = rd.getSysIOFlg();
			String IsSameCity = rd.getIsSameCity();
			String RecICBCCode = rd.getRecICBCCode();
			String RecBankNo = rd.getRecBankNo();
			String RecBankName = rd.getRecBankName();
			String CurrType = rd.getCurrType();
			String PayAmt = rd.getPayAmt();
			String UseCode = rd.getUseCode();
			String UseCN = rd.getUseCN();
			String EnSummary = rd.getEnSummary();
			String PostScript = rd.getPostScript();
			String Summary = rd.getSummary();
			String Ref = rd.getRef();
			String Oref = rd.getOref();
			String eRPSqn = rd.getErpSqn();
			String BusCode = rd.getBusCode();
			String ERPcheckno = rd.getErpCheckno();
			String CrvouhType = rd.getCrvouhType();
			String CrvouhName = rd.getCrvouhName();
			String CrvouhNo = rd.getCrvouhNo();
			String iRetCode = rd.getiRetCode();
			String iRetMsg = rd.getiRetMsg();
			String Result = rd.getResult();
			
			String instrRetCode = rd.getInstrRetCode();
			String instrRetMsg = rd.getInstrRetMsg();
			String BankRetTime = rd.getBankRetTime();
			
			String RepReserved3 = rd.getRepReserved3();
			String RepReserved4 = rd.getRepReserved4();
			
			
			Assert.assertTrue(null != iSeqno && iSeqno.trim().length() > 0);
			Assert.assertTrue(null != QryOrderNo && QryOrderNo.trim().length() > 0);
			Assert.assertTrue(null != ReimburseNo && ReimburseNo.trim().length() > 0);
			Assert.assertTrue(null != ReimburseNum && ReimburseNum.trim().length() > 0);
			Assert.assertTrue(null != StartDate && StartDate.trim().length() > 0);
			Assert.assertTrue(null != StartTime && StartTime.trim().length() > 0);
			Assert.assertTrue(null != PayType && PayType.trim().length() > 0);
			Assert.assertTrue(null != PayAccNo && PayAccNo.trim().length() > 0);
			Assert.assertTrue(null != PayAccNameCN && PayAccNameCN.trim().length() > 0);
			Assert.assertTrue(null != PayAccNameEN && PayAccNameEN.trim().length() > 0);
			Assert.assertTrue(null != RecAccNo && RecAccNo.trim().length() > 0);
			Assert.assertTrue(null != RecAccNameCN && RecAccNameCN.trim().length() > 0);
			Assert.assertTrue(null != RecAccNameEN && RecAccNameEN.trim().length() > 0);
			Assert.assertTrue(null != SysIOFlg && SysIOFlg.trim().length() > 0);
			Assert.assertTrue(null != IsSameCity && IsSameCity.trim().length() > 0);
			Assert.assertTrue(null != RecICBCCode && RecICBCCode.trim().length() > 0);
			Assert.assertTrue(null != RecBankNo && RecBankNo.trim().length() > 0);
			Assert.assertTrue(null != RecBankName && RecBankName.trim().length() > 0);
			Assert.assertTrue(null != CurrType && CurrType.trim().length() > 0);
			Assert.assertTrue(null != PayAmt && PayAmt.trim().length() > 0);
			Assert.assertTrue(null != UseCode && UseCode.trim().length() > 0);
			Assert.assertTrue(null != UseCN && UseCN.trim().length() > 0);
			Assert.assertTrue(null != EnSummary && EnSummary.trim().length() > 0);
			Assert.assertTrue(null != PostScript && PostScript.trim().length() > 0);
			Assert.assertTrue(null != Summary && Summary.trim().length() > 0);
			Assert.assertTrue(null != Ref && Ref.trim().length() > 0);
			Assert.assertTrue(null != Oref && Oref.trim().length() > 0);
			Assert.assertTrue(null != eRPSqn && eRPSqn.trim().length() > 0);
			Assert.assertTrue(null != BusCode && BusCode.trim().length() > 0);
			Assert.assertTrue(null != ERPcheckno && ERPcheckno.trim().length() > 0);
			Assert.assertTrue(null != CrvouhType && CrvouhType.trim().length() > 0);
			Assert.assertTrue(null != CrvouhName && CrvouhName.trim().length() > 0);
			Assert.assertTrue(null != CrvouhNo && CrvouhNo.trim().length() > 0);
			Assert.assertTrue(null != Result && Result.trim().length() > 0);
			Assert.assertTrue(null != iRetCode && iRetCode.trim().length() > 0);
			Assert.assertTrue(null != iRetMsg && iRetMsg.trim().length() > 0);
			Assert.assertTrue(null != RepReserved3 && RepReserved3.trim().length() > 0);
			Assert.assertTrue(null != RepReserved4 && RepReserved4.trim().length() > 0);
			
			System.out.println("------------------------");
			System.out.println("iSeqno: " + iSeqno);
			System.out.println("QryOrderNo: " + QryOrderNo);
			System.out.println("ReimburseNo: " + ReimburseNo);
			System.out.println("ReimburseNum: " + ReimburseNum);
			System.out.println("StartDate: " + StartDate);
			System.out.println("StartTime: " + StartTime);
			System.out.println("PayType: " + PayType);
			System.out.println("PayAccNo: " + PayAccNo);
			System.out.println("PayAccNameCN: " + PayAccNameCN);
			System.out.println("PayAccNameEN: " + PayAccNameEN);
			System.out.println("RecAccNo: " + RecAccNo);
			System.out.println("RecAccNameCN: " + RecAccNameCN);
			System.out.println("RecAccNameEN: " + RecAccNameEN);
			System.out.println("SysIOFlg: " + SysIOFlg);
			System.out.println("IsSameCity: " + IsSameCity);
			System.out.println("RecICBCCode: " + RecICBCCode);
			System.out.println("RecBankNo: " + RecBankNo);
			System.out.println("RecBankName: " + RecBankName);
			System.out.println("CurrType: " + CurrType);
			System.out.println("PayAmt: " + PayAmt);
			System.out.println("UseCode: " + UseCode);
			System.out.println("UseCN: " + UseCN);
			System.out.println("EnSummary: " + EnSummary);
			System.out.println("PostScript: " + PostScript);
			System.out.println("Summary: " + Summary);
			System.out.println("Ref: " + Ref);
			System.out.println("Oref: " + Oref);
			System.out.println("RPSqn: " + eRPSqn);
			System.out.println("BusCode: " + BusCode);
			System.out.println("ERPcheckno: " + ERPcheckno);
			System.out.println("CrvouhType: " + CrvouhType);
			System.out.println("CrvouhName: " + CrvouhName);
			System.out.println("CrvouhNo: " + CrvouhNo);
			System.out.println("iRetCode: " + iRetCode);
			System.out.println("iRetMsg: " + iRetMsg);
			System.out.println("Result: " + Result);
			System.out.println("instrRetCode: " + instrRetCode);
			System.out.println("instrRetMsg: " + instrRetMsg);
			System.out.println("BankRetTime: " + BankRetTime);
			System.out.println("RepReserved3: " + RepReserved3);
			System.out.println("RepReserved4: " + RepReserved4);
		}
	}

}
