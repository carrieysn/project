package com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.transcode;

import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.xmlbean.NCRequest;

/**
 * 
 *
 */
public interface TransCodeDefinition<I extends NCRequest, O> {

	String getTransCode();

	String getFreemarkerTemplateName();

	boolean isNeedSignRequestBeforeSending();

	Class<I> getXmlRequestType();

	Class<O> getXmlResponseType();

}
