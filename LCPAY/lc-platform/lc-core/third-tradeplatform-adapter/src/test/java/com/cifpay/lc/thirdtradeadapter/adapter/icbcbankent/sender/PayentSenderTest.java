package com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.sender;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.helper.ICBCBankEntXmlRequestBuilder;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.helper.ICBCBankEntXmlResponseParser;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.helper.NCMessageSender;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.transcode.PAYENT;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.transcode.QPAYENT;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.xmlbean.payent.PayentXmlRequest;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.xmlbean.payent.PayentXmlRequestRecordDetail;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.xmlbean.payent.PayentXmlResponse;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.xmlbean.qpayent.QPayentXmlRequest;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.xmlbean.qpayent.QPayentXmlRequestRecordDetail;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.xmlbean.qpayent.QPayentXmlResponse;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.xmlbean.qpayent.QPayentXmlResponseRecordDetail;

/**
 * 
 *
 */
public class PayentSenderTest {

	@Test
	public void testSend() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -5);
		Date now = cal.getTime();

		String tradeDate = "20160725";
		String payAmt = "1";

		NCMessageSender sender = new NCMessageSender();
		sender.setSignInterfaceURL("http://192.168.163.9:449");
		sender.setTradeInterfaceURL("http://192.168.163.9:448/servlet/ICBCCMPAPIReqServlet");
		sender.setXmlRequestBuilder(new ICBCBankEntXmlRequestBuilder());
		sender.setXmlResponseParser(new ICBCBankEntXmlResponseParser());

//		String fSeqno = String.valueOf(System.currentTimeMillis() + new SecureRandom().nextInt());
		String fSeqno = "fseqno001";
		String iSeqno = "detail001";

//		PayentXmlRequest request = new PayentXmlRequest();
//		request.setTransCode("PAYENT");
//		request.setCis("400090001604411");
//		request.setBankCode("102");
//		request.setId("ncrp.y.4000");
//		request.setTranDate(tradeDate);
//		request.setTranTime(new SimpleDateFormat("HHmmssSSS000").format(now));
//		request.setfSeqno(fSeqno);
//
//		request.setSettleMode("0");
//		request.setTotalNum("1");
//		request.setTotalAmt(payAmt);
//		request.setSignTime(
//				request.getTranDate() + request.getTranTime().substring(0, request.getTranTime().length() - 3));
//
//		PayentXmlRequestRecordDetail rd = new PayentXmlRequestRecordDetail();
//		rd.setiSeqno(iSeqno);
//		rd.setPayType("2");
//		rd.setPayAccNo("4000023029200124946");
//		rd.setRecAccNo("4000020829200148508");
//		rd.setRecAccNameCN("邻商惕半刺尝但农酵瘟入晋率咕");
//		rd.setSysIOFlg("1");
//		rd.setCurrType("001");
//		rd.setPayAmt(payAmt);
//		rd.setUseCN("测试W");
//		rd.setSummary("测试Y");
//		request.addRd(rd);
//
//		PayentXmlResponse response = sender.send(PAYENT.getInstance(), request);
//		Assert.assertNotNull("PayentXmlResponse", response);
//		Assert.assertNotNull("response.getRetCode()", response.getRetCode());
//		Assert.assertTrue("response.getRetCode()", response.getRetCode().trim().length() > 0);
//
//		System.out.println("RetCode: " + response.getRetCode());

		System.out.println("~~~~~~~~~~~~~~Send Enquiry~~~~~~~~~~~~~~~~");
		QPayentXmlRequest qrequest = new QPayentXmlRequest();
		qrequest.setTransCode("QPAYENT");
		qrequest.setCis("400090001604411");
		qrequest.setBankCode("102");
		qrequest.setId("ncrp.y.4000");
		qrequest.setTranDate(tradeDate);
		qrequest.setTranTime(new SimpleDateFormat("HHmmssSSS000").format(new Date()));
//		qrequest.setfSeqno(String.valueOf(System.currentTimeMillis() + new SecureRandom().nextInt()));
		qrequest.setfSeqno("qry001");
		qrequest.setQryfSeqno(fSeqno);

		QPayentXmlRequestRecordDetail qRd = new QPayentXmlRequestRecordDetail();
		qRd.setiSeqno("1");
		qRd.setQryiSeqno(iSeqno);

		qrequest.addRd(qRd);

		QPayentXmlResponse qresponse = sender.send(QPAYENT.getInstance(), qrequest);
		Assert.assertNotNull("QPayentXmlResponse", qresponse);
		Assert.assertNotNull("qresponse.getRetCode()", qresponse.getRetCode());
		Assert.assertTrue("qresponse.getRetCode()", qresponse.getRetCode().trim().length() > 0);
		System.out.println("q-RetCode: " + qresponse.getRetCode());
		System.out.println("q-RetMsg: " + qresponse.getRetMsg());

		if (null != qresponse.getRds() && qresponse.getRds().size() > 0) {
			QPayentXmlResponseRecordDetail qrd = qresponse.getRds().get(0);
			System.out.println("q-result: " + qrd.getResult());
			System.out.println("i-ret-code: " + qrd.getInstrRetCode());
			System.out.println("i-ret-msg: " + qrd.getInstrRetMsg());
		}

	}
}
