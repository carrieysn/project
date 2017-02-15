package com.cifpay.lc.util.spring;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.util.CollectionUtils;

/**
 * 
 * 严格的WAR外部配置文件加载及配置类，在Spring原有的PropertyPlaceholderConfigurer基础上，若指定了local
 * properties(default
 * properties)，则在加载外部properties文件时，执行严格的检查，以保证外部的properties文件包含的key总和不能少于local
 * properties的key总和，即防止外部的properties文件遗漏了key而错误的使用了local properties中的默认值。
 * 
 * 
 *
 */
public class StrictProgramExternalPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private Properties mergedProperties;

	public Properties getMergedProperties() {
		return mergedProperties;
	}

	@Override
	protected Properties mergeProperties() throws IOException {
		this.mergedProperties = super.mergeProperties();
		return this.mergedProperties;
	}

	@Override
	protected void loadProperties(Properties props) throws IOException {
		// 如果指定了local properties，提现出所有local properties中已经定义的key
		Set<String> localPropNamesCopy = null;
		Set<String> localPropNames = props.stringPropertyNames();
		if (!localPropNames.isEmpty()) {
			localPropNamesCopy = new HashSet<String>(localPropNames);
		}

		if (null == localPropNamesCopy) {
			super.loadProperties(props);
		} else {
			// 加载properties文件
			Properties externalProps = new Properties();
			super.loadProperties(externalProps);

			// 检查加载到的文件是否包含了应有的全部key
			if (!externalProps.isEmpty()) {
				List<String> missingPropKeys = new ArrayList<String>();
				for (String localPropKey : localPropNamesCopy) {
					if (null == externalProps.getProperty(localPropKey)) {
						missingPropKeys.add(localPropKey);
					}
				}

				if (!missingPropKeys.isEmpty()) {
					for (String missingPropKey : missingPropKeys) {
						logger.error("程序外部Properties配置文件中缺少配置项，请检查外部配置部署时是否有遗漏: {}", missingPropKey);
					}
					throw new IOException("程序外部Properties配置文件中缺少配置项，具体请往上检查日志。");
				}
			}

			// 检查通过，则进行合并
			CollectionUtils.mergePropertiesIntoMap(externalProps, props);
		}
	}

}
