package com.cifpay.lc.thirdtradeadapter.api.output.icbcbankent;

import java.io.Serializable;
import java.util.Date;
import java.util.Formatter;

/**
 * 
 *
 */
public class VirutalTradeDateInfo implements Serializable {
	private static final long serialVersionUID = 7612749599314965471L;
	private boolean runningInTestEnvironmentMode;
	private Date currentDatetimeForICBCTrade;

	public boolean isRunningInTestEnvironmentMode() {
		return runningInTestEnvironmentMode;
	}

	public void setRunningInTestEnvironmentMode(boolean runningInTestEnvironmentMode) {
		this.runningInTestEnvironmentMode = runningInTestEnvironmentMode;
	}

	public Date getCurrentDatetimeForICBCTrade() {
		return currentDatetimeForICBCTrade;
	}

	public void setCurrentDatetimeForICBCTrade(Date currentDatetimeForICBCTrade) {
		this.currentDatetimeForICBCTrade = currentDatetimeForICBCTrade;
	}

	@Override
	public String toString() {
		try (Formatter f = new Formatter()) {
			return f.format(
					"{runningInTestEnvironmentMode:\"%1$s\", "
							+ "currentDatetimeForICBCTrade:\"%2$tY-%2$tm-%2$td %2$tH:%2$tM:%2$tS\"}",
					new Object[] { runningInTestEnvironmentMode, currentDatetimeForICBCTrade }).toString();
		} catch (Exception e) {
			e.printStackTrace();
			return super.toString();
		}
	}

}
