package com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.helper;

import java.io.StringWriter;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

/**
 * A builder for building the XML request, which will be sent to the NC server.
 * 
 * 
 *
 */
@Component
public class ICBCBankEntXmlRequestBuilder {
	private Logger logger = LoggerFactory.getLogger(ICBCBankEntXmlRequestBuilder.class);
	private Configuration templateConfig;

	public ICBCBankEntXmlRequestBuilder() {
		String tmplBasePackagePath = "com/cifpay/lc/thirdtradeadapter/adapter/icbcbankent/xmltmpl";
		templateConfig = new Configuration(Configuration.VERSION_2_3_23);
		templateConfig.setClassLoaderForTemplateLoading(getClass().getClassLoader(), tmplBasePackagePath);
		templateConfig.setLocale(Locale.SIMPLIFIED_CHINESE);
		templateConfig.setDefaultEncoding("GBK");
		templateConfig.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		templateConfig.setLogTemplateExceptions(false);
		templateConfig.setClassicCompatible(true);
		templateConfig.setOutputEncoding("GBK");
	}

	public String buildXmlRequest(String freemarkerTemplateName, Object requestObject) throws RuntimeException {
		try {
			Template tmpl = templateConfig.getTemplate(freemarkerTemplateName, null, "GBK");
			StringWriter out = new StringWriter();
			tmpl.process(requestObject, out);
			out.flush();
			return out.toString();
		} catch (Exception e) {
			logger.error("Failed to process xml request template.", e);
			throw new RuntimeException("Failed to process xml request template.");
		}
	}

}
