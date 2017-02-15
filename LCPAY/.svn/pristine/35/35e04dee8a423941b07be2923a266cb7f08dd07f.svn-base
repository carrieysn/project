package com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.transcode;

import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.xmlbean.qaccbal.QaccbalXmlRequest;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.xmlbean.qaccbal.QaccbalXmlResponse;

/**
 * QACCBAL
 * 
 * 
 *
 */
public class QACCBAL implements TransCodeDefinition<QaccbalXmlRequest, QaccbalXmlResponse> {

	private static final String TRANS_CODE = "QACCBAL";
	private static final String TEMPLATE_FILE_NAME = "QACCBAL-Request-Tempalte.xml";
	private static final QACCBAL INSTANCE = new QACCBAL();

	private QACCBAL() {
	}

	public static QACCBAL getInstance() {
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
	public Class<QaccbalXmlRequest> getXmlRequestType() {
		return QaccbalXmlRequest.class;
	}

	@Override
	public Class<QaccbalXmlResponse> getXmlResponseType() {
		return QaccbalXmlResponse.class;
	}

}
