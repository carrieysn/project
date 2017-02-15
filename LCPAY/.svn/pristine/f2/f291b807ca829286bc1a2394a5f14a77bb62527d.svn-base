package com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.helper;

import java.io.StringReader;
import java.util.HashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.cifpay.lc.util.ClassScanUtils;

/**
 * Parser for parsing the XML content responded from NC server.
 * 
 * 
 *
 */
@Component
public class ICBCBankEntXmlResponseParser {
	protected Unmarshaller unmarshaller;

	public ICBCBankEntXmlResponseParser() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (null == classLoader) {
			classLoader = getClass().getClassLoader();
		}

		try {
			Class<?>[] xmlBeanClasses = ClassScanUtils.scanClassesWithAnnotation(XmlRootElement.class.getName(), false,
					false, classLoader, "com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.xmlbean");
			JAXBContext jaxbContext = JAXBContextFactory.createContext(xmlBeanClasses, new HashMap<String, Object>(),
					classLoader);
			unmarshaller = jaxbContext.createUnmarshaller();
		} catch (JAXBException e) {
			throw new RuntimeException("ICBCBankEntXmlResponseParser初始化失败", e);
		}
	}

	public <T> T parseXmlResponse(String responsedXmlContent, Class<T> expectedXmlBeanType) {
		if (!StringUtils.hasText(responsedXmlContent)) {
			throw new IllegalArgumentException("参数responsedXmlContent不能为空");
		}
		if (null == expectedXmlBeanType) {
			throw new IllegalArgumentException("参数expectedXmlBeanType不能为null");
		}

		try {
			T xmlBean = unmarshaller
					.unmarshal(new StreamSource(new StringReader(responsedXmlContent)), expectedXmlBeanType).getValue();
			return xmlBean;
		} catch (JAXBException e) {
			throw new IllegalArgumentException("输入的XML内容不能正常解析，" + e.getMessage(), e);
		}
	}

}
