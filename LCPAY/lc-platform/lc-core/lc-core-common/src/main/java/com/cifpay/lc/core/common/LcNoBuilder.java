package com.cifpay.lc.core.common;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.springframework.stereotype.Component;

/**
 * 银信证展示编号生成工具。
 * 
 * 
 *
 */
@Component
public class LcNoBuilder {

	private static final BigDecimal DECIMAL_100 = new BigDecimal(100);
	private static final DecimalFormat AMOUNT_FORMAT = new DecimalFormat("#,##0.00");

	/**
	 * 生成银信证展示编号
	 * 
	 * @param orgName
	 *            付款人所在银行或机构的名称，eg 工商银行、支付宝
	 * @param amountByFen
	 *            金额（单元为分）
	 * @param currencyCode
	 *            币种代码，例如CNY
	 * @param lcId
	 *            银信证ID
	 * @return
	 */
	public String buildLcNo(String payerOrgName, BigDecimal amountByFen, String currencyCode, Long lcId) {
		StringBuilder sb = new StringBuilder();

		BigDecimal amount = amountByFen.divide(DECIMAL_100);
		sb.append(payerOrgName).append("*").append(AMOUNT_FORMAT.format(amount)).append(currencyCode).append("*")
				.append(lcId);

		// FIXME: 后期可以考虑将展示编号中的lcId浓缩成简短易记的短字符串形式。

		return sb.toString();
	}

}
