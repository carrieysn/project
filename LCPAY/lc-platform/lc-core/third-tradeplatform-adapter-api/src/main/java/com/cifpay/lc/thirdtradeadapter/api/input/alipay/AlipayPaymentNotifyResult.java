package com.cifpay.lc.thirdtradeadapter.api.input.alipay;

import java.io.Serializable;
import java.util.Formatter;

import com.cifpay.lc.constant.enums.LcCurrency;

/**
 * 
 *
 */
public class AlipayPaymentNotifyResult implements Serializable {
	private static final long serialVersionUID = 462897071155832657L;
	private String callerSystemId;
	private String sellerEnterpriseCode;
	private String instructionNo;
	private Long totalAmount; // 分
	private LcCurrency currency;
	private String goodsSummary;
	private String goodsShowUrl;
	private String returnUrl;
	private String notifyUrl;

	public String getCallerSystemId() {
		return callerSystemId;
	}

	public void setCallerSystemId(String callerSystemId) {
		this.callerSystemId = callerSystemId;
	}

	public String getSellerEnterpriseCode() {
		return sellerEnterpriseCode;
	}

	public void setSellerEnterpriseCode(String sellerEnterpriseCode) {
		this.sellerEnterpriseCode = sellerEnterpriseCode;
	}

	public String getInstructionNo() {
		return instructionNo;
	}

	public void setInstructionNo(String instructionNo) {
		this.instructionNo = instructionNo;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public LcCurrency getCurrency() {
		return currency;
	}

	public void setCurrency(LcCurrency currency) {
		this.currency = currency;
	}

	public String getGoodsSummary() {
		return goodsSummary;
	}

	public void setGoodsSummary(String goodsSummary) {
		this.goodsSummary = goodsSummary;
	}

	public String getGoodsShowUrl() {
		return goodsShowUrl;
	}

	public void setGoodsShowUrl(String goodsShowUrl) {
		this.goodsShowUrl = goodsShowUrl;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	@Override
	public String toString() {
		try (Formatter f = new Formatter()) {
			return f.format("{callerSystemId:\"%s\", " + "sellerEnterpriseCode:\"%s\", " + "instructionNo:\"%s\", "
					+ "totalAmount:\"%s\", " + "currency:\"%s\", " + "goodsSummary:\"%s\", " + "goodsShowUrl:\"%s\"}",
					new Object[] { callerSystemId, sellerEnterpriseCode, instructionNo, totalAmount, currency,
							goodsSummary, goodsShowUrl })
					.toString();
		} catch (Exception e) {
			return super.toString();
		}
	}

}
