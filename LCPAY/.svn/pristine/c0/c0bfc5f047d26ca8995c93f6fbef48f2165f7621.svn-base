package com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.helper;

import com.cifpay.lc.constant.enums.LcCurrency;
import com.cifpay.lc.thirdtradeadapter.api.constant.ICBCBankEntCurrencyType;

/**
 * 
 *
 */
public abstract class LcCurrencyICBCCurrencyMappingUtils {

	public static ICBCBankEntCurrencyType mappingFromLcCurrency(LcCurrency lcCurrency) {
		switch (lcCurrency) {
		case CNY:
			return ICBCBankEntCurrencyType.CNY;
		default:
			throw new IllegalArgumentException(
					"The LcCurrency [" + lcCurrency.getCode() + "] is not supported by the ICBC Bankent Adatper");
		}
	}

	public static LcCurrency mappingFromICBCBankEntCurrencyType(ICBCBankEntCurrencyType icbcCurrentType) {
		switch (icbcCurrentType) {
		case CNY:
			return LcCurrency.CNY;
		default:
			throw new IllegalArgumentException("The ICBCBankEntCurrencyType [" + icbcCurrentType.getCode()
					+ "] can not mappinged to any LcCurrency");
		}
	}
}
