package com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.helper;

import org.junit.Test;

import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.transcode.PAYENT;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.transcode.QPAYENT;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.xmlbean.payent.PayentXmlRequest;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.xmlbean.payent.PayentXmlRequestRecordDetail;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.xmlbean.qpayent.QPayentXmlRequest;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.xmlbean.qpayent.QPayentXmlRequestRecordDetail;

/**
 * 
 *
 */
public class ICBCBankEntXmlRequestBuilderTest {

	@Test
	public void testBuildXmlRequestPAYENT() {
		PayentXmlRequest request = new PayentXmlRequest();

		request.setTransCode("111111111111111111");
		request.setCis("集团CIS号");
		request.setBankCode("归属银行编号");
		request.setId("证书ID");
		request.setTranDate("交易日期");
		request.setTranTime("交易时间");
		request.setfSeqno("指令包序列号");
		request.setOnlBatF("联机批量标志");
		request.setSettleMode("入账方式");
		request.setTotalNum("总笔数");
		request.setTotalAmt("总金额");
		request.setSignTime("签名时间");
		request.setReqReserved1("请求备用字段1");
		request.setReqReserved2("请求备用字段2");

		PayentXmlRequestRecordDetail rd = new PayentXmlRequestRecordDetail();
		rd.setiSeqno("指令顺序号");
		rd.setReimburseNo("自定义序号");
		rd.setReimburseNum("单据张数");
		rd.setStartDate("定时启动日期");
		rd.setStartTime("定时启动时间");
		rd.setPayType("记账处理方式");
		rd.setPayAccNo("本方账号");
		rd.setPayAccNameCN("本方账户名称");
		rd.setPayAccNameEN("本方账户英文名称");
		rd.setRecAccNo("对方账号");
		rd.setRecAccNameCN("对方账户名称");
		rd.setRecAccNameEN("对方账户英文名称");
		rd.setSysIOFlg("系统内外标志");
		rd.setIsSameCity("同城异地标志");
		rd.setProp("对公对私标志");
		rd.setRecICBCCode("交易对方工行地区号");
		rd.setRecCityName("收款方所在城市名称");
		rd.setRecBankNo("对方行行号");
		rd.setRecBankName("交易对方银行名称");
		rd.setCurrType("币种");
		rd.setPayAmt("金额");
		rd.setUseCode("用途代码");
		rd.setUseCN("用途中文描述");
		rd.setEnSummary("英文备注");
		rd.setPostScript("附言");
		rd.setSummary("摘要");
		rd.setRef("业务编号（业务参考号）");
		rd.setOref("相关业务编号");
		rd.setErpSqn("ERP流水号");
		rd.setBusCode("业务代码");
		rd.setErpCheckno("ERP支票号");
		rd.setCrvouhType("原始凭证种类");
		rd.setCrvouhName("原始凭证名称");
		rd.setCrvouhNo("原始凭证号");
		rd.setReqReserved3("请求备用字段3");
		rd.setReqReserved4("请求备用字段4");

		request.addRd(rd);

		ICBCBankEntXmlRequestBuilder builder = new ICBCBankEntXmlRequestBuilder();
		String xml = builder.buildXmlRequest(PAYENT.getInstance().getFreemarkerTemplateName(), request);

		System.out.println("PAYENT Build Result: ");
		System.out.println(xml);

	}

	@Test
	public void testBuildXmlRequestPAYENTMultiRds() {
		PayentXmlRequest request = new PayentXmlRequest();

		request.setTransCode("交易代码");
		// request.setCis("集团CIS号");
		request.setBankCode("归属银行编号");
		request.setId("证书ID");
		request.setTranDate("交易日期");
		request.setTranTime("交易时间");
		request.setfSeqno("指令包序列号");
		request.setOnlBatF("联机批量标志");
		request.setSettleMode("入账方式");
		request.setTotalNum("总笔数");
		request.setTotalAmt("总金额");
		// request.setSignTime("签名时间");
		request.setReqReserved1("请求备用字段1");
		request.setReqReserved2("请求备用字段2");

		PayentXmlRequestRecordDetail rd = new PayentXmlRequestRecordDetail();
		rd.setiSeqno("指令顺序号");
		rd.setReimburseNo("自定义序号");
		rd.setReimburseNum("单据张数");
		// rd.setStartDate("定时启动日期");
		// rd.setStartTime("定时启动时间");
		rd.setPayType("记账处理方式");
		rd.setPayAccNo("本方账号");
		rd.setPayAccNameCN("本方账户名称");
		rd.setPayAccNameEN("本方账户英文名称");
		rd.setRecAccNo("对方账号");
		rd.setRecAccNameCN("对方账户名称");
		rd.setRecAccNameEN("对方账户英文名称");
		rd.setSysIOFlg("系统内外标志");
		rd.setIsSameCity("同城异地标志");
		rd.setProp("对公对私标志");
		rd.setRecICBCCode("交易对方工行地区号");
		rd.setRecCityName("收款方所在城市名称");
		rd.setRecBankNo("对方行行号");
		rd.setRecBankName("交易对方银行名称");
		rd.setCurrType("币种");
		rd.setPayAmt("金额");
		rd.setUseCode("用途代码");
		rd.setUseCN("用途中文描述");
		rd.setEnSummary("英文备注");
		rd.setPostScript("附言");
		rd.setSummary("摘要");
		rd.setRef("业务编号（业务参考号）");
		rd.setOref("相关业务编号");
		rd.setErpSqn("ERP流水号");
		rd.setBusCode("业务代码");
		rd.setErpCheckno("ERP支票号");
		rd.setCrvouhType("原始凭证种类");
		rd.setCrvouhName("原始凭证名称");
		rd.setCrvouhNo("原始凭证号");
		rd.setReqReserved3("请求备用字段3");
		rd.setReqReserved4("请求备用字段4");

		request.addRd(rd);

		rd = new PayentXmlRequestRecordDetail();
		rd.setiSeqno("指令顺序号2");
		rd.setReimburseNo("自定义序号2");
		rd.setReimburseNum("单据张数");
		rd.setStartDate("定时启动日期");
		rd.setStartTime("定时启动时间");
		rd.setPayType("记账处理方式");
		rd.setPayAccNo("本方账号");
		rd.setPayAccNameCN("本方账户名称");
		rd.setPayAccNameEN("本方账户英文名称");
		rd.setRecAccNo("对方账号");
		rd.setRecAccNameCN("对方账户名称");
		rd.setRecAccNameEN("对方账户英文名称");
		rd.setSysIOFlg("系统内外标志");
		rd.setIsSameCity("同城异地标志");
		rd.setProp("对公对私标志");
		rd.setRecICBCCode("交易对方工行地区号");
		rd.setRecCityName("收款方所在城市名称");
		rd.setRecBankNo("对方行行号");
		rd.setRecBankName("交易对方银行名称");
		rd.setCurrType("币种");
		rd.setPayAmt("金额");
		rd.setUseCode("用途代码");
		rd.setUseCN("用途中文描述");
		rd.setEnSummary("英文备注");
		rd.setPostScript("附言");
		rd.setSummary("摘要");
		rd.setRef("业务编号（业务参考号）");
		rd.setOref("相关业务编号");
		rd.setErpSqn("ERP流水号");
		rd.setBusCode("业务代码");
		rd.setErpCheckno("ERP支票号");
		// rd.setCrvouhType("原始凭证种类");
		// rd.setCrvouhName("原始凭证名称");
		rd.setCrvouhNo("原始凭证号");
		rd.setReqReserved3("请求备用字段3");
		rd.setReqReserved4("请求备用字段4");

		request.addRd(rd);

		ICBCBankEntXmlRequestBuilder builder = new ICBCBankEntXmlRequestBuilder();
		String xml = builder.buildXmlRequest(PAYENT.getInstance().getFreemarkerTemplateName(), request);

		System.out.println("PAYENT Build Result: ");
		System.out.println(xml);

	}

	@Test
	public void testBuildXmlRequestQPAYENT() {
		QPayentXmlRequest request = new QPayentXmlRequest();

		request.setTransCode("111111111111111111");
		request.setCis("集团CIS号");
		request.setBankCode("归属银行编号");
		request.setId("证书ID");
		request.setTranDate("交易日期");
		request.setTranTime("交易时间");
		request.setfSeqno("指令包序列号");
		request.setQryfSeqno("待查指令包序列号");
		request.setQrySerialNo("待查平台交易序列号");
		request.setReqReserved1("请求备用字段1");
		request.setReqReserved2("请求备用字段2");

		QPayentXmlRequestRecordDetail rd = new QPayentXmlRequestRecordDetail();
		rd.setiSeqno("指令顺序号");
		rd.setQryiSeqno("待查指令包顺序号");
		rd.setQryOrderNo("待查平台交易顺序号");
		rd.setReqReserved3("请求备用字段3");
		rd.setReqReserved4("请求备用字段4");

		request.addRd(rd);

		ICBCBankEntXmlRequestBuilder builder = new ICBCBankEntXmlRequestBuilder();
		String xml = builder.buildXmlRequest(QPAYENT.getInstance().getFreemarkerTemplateName(), request);

		System.out.println("QPAYENT Build Result: ");
		System.out.println(xml);
	}

	@Test
	public void testBuildXmlRequestQPAYENTWithoutRd() {
		QPayentXmlRequest request = new QPayentXmlRequest();

		request.setTransCode("111111111111111111");
		request.setCis("集团CIS号");
		request.setBankCode("归属银行编号");
		request.setId("证书ID");
		request.setTranDate("交易日期");
		request.setTranTime("交易时间");
		request.setfSeqno("指令包序列号");
		request.setQryfSeqno("待查指令包序列号");
		request.setQrySerialNo("待查平台交易序列号");
		request.setReqReserved1("请求备用字段1");
		request.setReqReserved2("请求备用字段2");

		ICBCBankEntXmlRequestBuilder builder = new ICBCBankEntXmlRequestBuilder();
		String xml = builder.buildXmlRequest(QPAYENT.getInstance().getFreemarkerTemplateName(), request);

		System.out.println("QPAYENT Build Result (Without RD): ");
		System.out.println(xml);
	}
}
