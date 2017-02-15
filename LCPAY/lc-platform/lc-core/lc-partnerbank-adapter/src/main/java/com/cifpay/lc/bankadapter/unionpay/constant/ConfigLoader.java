package com.cifpay.lc.bankadapter.unionpay.constant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class ConfigLoader {
	private static Logger logger = LoggerFactory
			.getLogger(ConfigLoader.class);

	private Properties proOfSystem;

	public ConfigLoader(String file) {
		proOfSystem = load(file);

	}

	public static Properties load(String file) {
		Properties prop = new Properties();
		InputStream is = ConfigLoader.class.getResourceAsStream(file);
		if (is != null) {
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				prop.load(reader);
			} catch (IOException e) {
				logger.error("load properties file error, file=" + file, e);
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			logger.error("properties file not exist, file=" + file);
		}
		return prop;
	}

	private String getProperty(String key) {
		return StringUtils.trimWhitespace((String) proOfSystem.get(key));
	}

	public int getInt(String key, int deValue) {
		try {
			return Integer.parseInt(getProperty(key));
		} catch (Exception e) {
			return deValue;
		}
	}

	public boolean getBoolean(String key, boolean deValue) {
		try {
			return Boolean.parseBoolean(getProperty(key));
		} catch (Exception e) {
			return deValue;
		}
	}

	public String getString(String key) {
		return this.getProperty(key);
	}

	public Properties getProOfSystem() {
		return proOfSystem;
	}

}
