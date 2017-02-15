package mock.merchant.common.tool;

import java.util.Properties;

public class ConfigLoader {

	private Properties proOfSystem;

	public ConfigLoader() {
		this("/config/sysConfig.properties");
	}

	public ConfigLoader(String file) {
		proOfSystem = PropertiesTool.load(file);
	}

	private String getProperty(String key) {
		return (String) proOfSystem.get(key);
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
