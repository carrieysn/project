package com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.transcode;

import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.xmlbean.qpayent.QPayentXmlRequest;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.xmlbean.qpayent.QPayentXmlResponse;

/**
 * QPAYENT
 * 
 * 
 *
 */
public class QPAYENT implements TransCodeDefinition<QPayentXmlRequest, QPayentXmlResponse> {

	private static final String TRANS_CODE = "QPAYENT";
	private static final String TEMPLATE_FILE_NAME = "QPAYENT-Request-Tempalte.xml";
	private static final QPAYENT INSTANCE = new QPAYENT();

	private QPAYENT() {
	}

	public static QPAYENT getInstance() {
		return INSTANCE;
	}

	@Override
	public String getTransCode() {
		return TRANS_CODE;
	}

	@Override
	public String getFreemarkerTemplateName() {
		return TEMPLATE_FILE_NAME;
	}

	@Override
	public boolean isNeedSignRequestBeforeSending() {
		return false;
	}

	@Override
	public Class<QPayentXmlRequest> getXmlRequestType() {
		return QPayentXmlRequest.class;
	}

	@Override
	public Class<QPayentXmlResponse> getXmlResponseType() {
		return QPayentXmlResponse.class;
	}

}
