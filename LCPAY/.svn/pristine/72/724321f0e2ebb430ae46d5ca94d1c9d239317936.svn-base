package com.cifpay.lc.bankadapter.universal.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Properties;

import org.springframework.util.StringUtils;

import com.cifpay.lc.bankadapter.universal.IBankDeal;
import com.cifpay.lc.bankadapter.universal.config.BankAdapterBean;
import com.cifpay.lc.bankadapter.universal.config.BanksConfig;

public class BanksConfig {
	private static BanksConfig bc;
	private HashMap<String, BankAdapterBean> banks = new HashMap<String, BankAdapterBean>();

	private BanksConfig() {
	}

	public static BanksConfig getInstance() throws Exception {
		if (bc == null) {
			synchronized (BanksConfig.class) {
				if (bc == null) {
					bc = new BanksConfig();
					bc.init();
				}
			}
		}
		return bc;
	}

	private void init() throws Exception {
		Properties prop = load();
		String strBanks = StringUtils.trimWhitespace(prop.getProperty("Banks"));
		if (StringUtils.hasText(strBanks)) {
			String[] arrBanks = strBanks.split(" ");
			if (arrBanks != null && arrBanks.length > 0) {
				for (String bank : arrBanks) {
					String bankCode = StringUtils.trimWhitespace(bank);
					if (StringUtils.hasText(bankCode)) {
						BankAdapterBean bean = new BankAdapterBean();
						bean.setTradeBankCode(bankCode);
						
						String freeze = prop.getProperty(StringUtils.trimWhitespace(bankCode + ".Trade.Deal.Adapter.Freeze"));
						bean.setTradeDealFreezeAdapter(freeze);
						Class<IBankDeal> clzFreeze = (Class<IBankDeal>)Class.forName(freeze);
						bean.setClzOfFreeze(clzFreeze);
						
						String unfreeze = prop.getProperty(StringUtils.trimWhitespace(bankCode + ".Trade.Deal.Adapter.Unfreeze"));
						bean.setTradeDealFreezeAdapter(unfreeze);
						Class<IBankDeal> clzUnfreeze = (Class<IBankDeal>)Class.forName(freeze);
						bean.setClzOfFreeze(clzUnfreeze);
						
						String pay = prop.getProperty(StringUtils.trimWhitespace(bankCode + ".Trade.Deal.Adapter.Pay"));
						bean.setTradeDealFreezeAdapter(pay);
						Class<IBankDeal> clzPay = (Class<IBankDeal>)Class.forName(freeze);
						bean.setClzOfFreeze(clzPay);
						
						banks.put(bankCode, bean);
					}
				}
			}
		}
	}

	private Properties load() throws IOException, Exception {
		Properties prop = new Properties();
		InputStream is = BanksConfig.class
				.getResourceAsStream("/com/cifpay/lc/bankadapter/bankAdapterConfig.properties");
		if (is != null) {
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				prop.load(reader);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				is.close();
			}
		} else {
			throw new Exception("properties file not exist");
		}
		return prop;
	}

	public BankAdapterBean getTradeDealAdapter(String bankCode) {
		return banks.get(bankCode);
	}
}
