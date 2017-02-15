package com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.helper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.cifpay.lc.util.spring.StrictProgramExternalPropertyPlaceholderConfigurer;

/**
 * Helper for initializing and obtaining the ICBC - Bank Enterprise paying
 * definitions.
 * 
 * 
 *
 */
@Component
public class EnterprisePayDefinitionHelper implements BeanFactoryAware {

	private static final String PROP_KEY_SUPPORTED_ENTERPRISE_CODES = "icbcBankEnt.supportedEnterpriseCodes";
	private static final String PROP_KEY_PREFIX = "icbcBankEnt.enterprise.";
	private static final String PROP_KEY_SUFFIX_PAYER_BANK_CODE = ".payerBankCode";
	private static final String PROP_KEY_SUFFIX_PAYER_CITY_NAME = ".payerCityName";
	private static final String PROP_KEY_SUFFIX_ICBC_CIS = ".icbcCIS";
	private static final String PROP_KEY_SUFFIX_ICBC_BANK_CODE = ".icbcBankCode";
	private static final String PROP_KEY_SUFFIX_ICBC_CERT_ID = ".icbcCertId";
	private static final String PROP_KEY_SUFFIX_ICBC_PAY_ACC_NO = ".icbcPayAccNo";
	private static final String PROP_KEY_SUFFIX_ICBC_PAY_ACC_NAME_CN = ".icbcPayAccNameCN";
	private static final String PROP_KEY_SUFFIX_ICBC_DEFAULT_PAY_TYPE = ".icbcDefaultPayType";

	private Logger logger = LoggerFactory.getLogger(EnterprisePayDefinitionHelper.class);
	private BeanFactory beanFactory;
	private boolean definitionsInitialized;
	private final Map<String, EnterprisePayDefinition> definitions = new HashMap<String, EnterprisePayDefinition>();

	/**
	 * 
	 * @param callerSystemId
	 * @param payerEnterpriseCode
	 * @return
	 * @throws IllegalArgumentException
	 *             Throws IllegalArgumentException if no definition is found.
	 */
	public EnterprisePayDefinition findEnterprisePayDefinition(String callerSystemId, String payerEnterpriseCode)
			throws IllegalArgumentException {
		checkAndInit();

		EnterprisePayDefinition foundDefition = definitions.get(payerEnterpriseCode);
		if (null == foundDefition) {
			throw new IllegalArgumentException("未能找到企业代码[" + payerEnterpriseCode + "]对应的工行银企互联配置");
		}

		// TODO verify the callerSystemId if has permission to use this
		// payerEnterpriseCode

		return foundDefition;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

	private void checkAndInit() {
		if (!definitionsInitialized) {
			synchronized (definitions) {
				if (!definitionsInitialized) {
					initializeDefinitions();
				}
			}
		}
	}

	private void initializeDefinitions() {
		StrictProgramExternalPropertyPlaceholderConfigurer propConfigurer = beanFactory
				.getBean(StrictProgramExternalPropertyPlaceholderConfigurer.class);
		if (null != propConfigurer) {
			Properties props = propConfigurer.getMergedProperties();
			String strEnterpriseCodes = props.getProperty(PROP_KEY_SUPPORTED_ENTERPRISE_CODES, "");
			String[] enterpriseCodes = strEnterpriseCodes.trim().split(",");

			logger.info("系统总共配置了{}个企业的工行银企互联配置信息：{}", enterpriseCodes.length, Arrays.deepToString(enterpriseCodes));

			for (String enterpriseCode : enterpriseCodes) {
				String payerBankCodeKey = PROP_KEY_PREFIX + enterpriseCode + PROP_KEY_SUFFIX_PAYER_BANK_CODE;
				String payerCityNameKey = PROP_KEY_PREFIX + enterpriseCode + PROP_KEY_SUFFIX_PAYER_CITY_NAME;
				String icbcCISKey = PROP_KEY_PREFIX + enterpriseCode + PROP_KEY_SUFFIX_ICBC_CIS;
				String icbcBankCodeKey = PROP_KEY_PREFIX + enterpriseCode + PROP_KEY_SUFFIX_ICBC_BANK_CODE;
				String icbcCertIdKey = PROP_KEY_PREFIX + enterpriseCode + PROP_KEY_SUFFIX_ICBC_CERT_ID;
				String icbcPayAccNoKey = PROP_KEY_PREFIX + enterpriseCode + PROP_KEY_SUFFIX_ICBC_PAY_ACC_NO;
				String icbcPayAccNameCNKey = PROP_KEY_PREFIX + enterpriseCode + PROP_KEY_SUFFIX_ICBC_PAY_ACC_NAME_CN;
				String icbcDefaultPayTypeKey = PROP_KEY_PREFIX + enterpriseCode + PROP_KEY_SUFFIX_ICBC_DEFAULT_PAY_TYPE;

				String payerBankCode = props.getProperty(payerBankCodeKey);
				String payerCityName = props.getProperty(payerCityNameKey);
				String icbcCIS = props.getProperty(icbcCISKey);
				String icbcBankCode = props.getProperty(icbcBankCodeKey);
				String icbcCertId = props.getProperty(icbcCertIdKey);
				String icbcPayAccNo = props.getProperty(icbcPayAccNoKey);
				String icbcPayAccNameCN = props.getProperty(icbcPayAccNameCNKey);
				String icbcDefaultPayType = props.getProperty(icbcDefaultPayTypeKey);

				throwExceptionIfPropertyIsEmpty(payerBankCodeKey, payerBankCode);
				throwExceptionIfPropertyIsEmpty(payerCityNameKey, payerCityName);
				throwExceptionIfPropertyIsEmpty(icbcCISKey, icbcCIS);
				throwExceptionIfPropertyIsEmpty(icbcBankCodeKey, icbcBankCode);
				throwExceptionIfPropertyIsEmpty(icbcCertIdKey, icbcCertId);
				throwExceptionIfPropertyIsEmpty(icbcPayAccNoKey, icbcPayAccNo);
				throwExceptionIfPropertyIsEmpty(icbcPayAccNameCNKey, icbcPayAccNameCN);
				throwExceptionIfPropertyIsEmpty(icbcDefaultPayTypeKey, icbcDefaultPayType);

				EnterprisePayDefinition def = new EnterprisePayDefinition();
				def.setPayerEnterpriseCode(enterpriseCode);
				def.setPayerBankCode(payerBankCode);
				def.setPayerCityName(payerCityName);
				def.setIcbcCIS(icbcCIS);
				def.setIcbcBankCode(icbcBankCode);
				def.setIcbcCertId(icbcCertId);
				def.setIcbcPayAccNo(icbcPayAccNo);
				def.setIcbcPayAccNameCN(icbcPayAccNameCN);
				def.setIcbcDefaultPayType(icbcDefaultPayType);

				definitions.put(enterpriseCode, def);
			}
		}

		definitionsInitialized = true;
	}

	private void throwExceptionIfPropertyIsEmpty(String propertyKey, String actualPropertyValue)
			throws RuntimeException {
		if (!StringUtils.hasText(actualPropertyValue)) {
			throw new RuntimeException("工行银企互联配置中不能缺少key为" + propertyKey + "的属性项，且其属性值不能为空");
		}
	}
}
