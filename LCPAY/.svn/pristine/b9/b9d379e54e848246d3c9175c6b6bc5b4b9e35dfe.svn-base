package com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.transcode;

import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.xmlbean.payent.PayentXmlRequest;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.xmlbean.payent.PayentXmlResponse;

/**
 * PAYENT
 * 
 * 
 *
 */
public class PAYENT implements TransCodeDefinition<PayentXmlRequest, PayentXmlResponse> {

	private static final String TRANS_CODE = "PAYENT";
	private static final String TEMPLATE_FILE_NAME = "PAYENT-Request-Tempalte.xml";
	private static final PAYENT INSTANCE = new PAYENT();

	private PAYENT() {
	}

	public static PAYENT getInstance() {
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
		return true;
	}

	@Override
	public Class<PayentXmlRequest> getXmlRequestType() {
		return PayentXmlRequest.class;
	}

	@Override
	public Class<PayentXmlResponse> getXmlResponseType() {
		return PayentXmlResponse.class;
	}

}
