package com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * A manager of ICBC virtual trade date.
 */
@Component
public class ICBCTradeDateManager {
	private SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

	@Value("${icbcBankEnt.runInTestEnvironmentMode:false}")
	private String testEnvironmentMode;

	private String tradeDateObtainedFromICBCDateUiTool; // yyyy-MM-dd
	private long differentMillisAgainCalendarTime;

	public boolean isRunningInTestEnvironmentMode() {
		return null != testEnvironmentMode && "true".equalsIgnoreCase(testEnvironmentMode);
	}

	public synchronized void updateReferenceTradeDateTimeForTestMode(final Date icbcDatetime) {
		if (null != icbcDatetime && isRunningInTestEnvironmentMode()) {
			this.tradeDateObtainedFromICBCDateUiTool = dateFormat.format(icbcDatetime);

			Date calendarTime;
			try {
				calendarTime = datetimeFormat
						.parse(this.tradeDateObtainedFromICBCDateUiTool + " " + timeFormat.format(new Date()));
				this.differentMillisAgainCalendarTime = icbcDatetime.getTime() - calendarTime.getTime();
			} catch (ParseException e) {
			}
		}
	}

	public Date getCurrentDatetimeForICBCTrade() {
		if (isRunningInTestEnvironmentMode() && null != tradeDateObtainedFromICBCDateUiTool) {
			Date datetime;
			try {
				datetime = datetimeFormat
						.parse(tradeDateObtainedFromICBCDateUiTool + " " + timeFormat.format(new Date()));
				long virtualDatetime = datetime.getTime() + differentMillisAgainCalendarTime;
				return new Date(virtualDatetime);
			} catch (ParseException e) {
				return new Date();
			}
		} else {
			return new Date();
		}
	}

}
