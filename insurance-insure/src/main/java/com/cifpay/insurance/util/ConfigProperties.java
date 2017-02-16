package com.cifpay.insurance.util;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.cifpay.insurance.config.WebConstant;

public  class ConfigProperties {
	private static final Logger LOG = LogManager.getLogger(ConfigProperties.class);
	
	public static Properties getConfigProperties(){
		Properties properties = new Properties();
		InputStream inputStream = null;
		try {
			inputStream = WebConstant.class.getResourceAsStream("/config.properties");
			properties.load(inputStream);
			
		} catch (Exception e) {
			LOG.error(e, e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
					inputStream = null;
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
				}
			}
		}
		return properties;
	}
}
