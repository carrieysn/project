package com.cifpay.lc.gateway.controller.webpay.mobile;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cifpay.lc.api.gateway.basic.bankcode.BankCodeInfo;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.gateway.common.BankCodeInfoCache;
import com.cifpay.lc.util.LcMd5SignTool;
import com.cifpay.lc.gateway.common.exception.GatewayValidationRejectException;
import com.cifpay.lc.domain.security.MerchantRequest;
import com.cifpay.lc.gateway.input.webpay.mobile.MobileWebPayInputRequest;
import com.cifpay.lc.gateway.integration.advice.MerchantFormRequest;
import com.cifpay.lc.util.AmountUtil;

/**
 * 输入支付信息（适用手机、PAD等移动终端的浏览器，包括终端上APP应用内嵌的浏览器组件）
 * 
 * 
 *
 */
@Controller
@RequestMapping("/webpay/mobile")
public class MobileWebPayInputController extends MobileWebPayBaseController {
	private static final BigDecimal DECIMAL_100 = new BigDecimal(100);
	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#0.00");

	@Autowired
	private BankCodeInfoCache bankCodeInfoCache;

	@RequestMapping(path = "/topay", method = RequestMethod.POST)
	public String initInput(@MerchantFormRequest MerchantRequest<MobileWebPayInputRequest> req, Model pageModel) {
		MobileWebPayInputRequest reqData = req.getData();

		Map<String, BankCodeInfo> bankCodeMappings = bankCodeInfoCache.getBankCodeMappings();
		BankCodeInfo cBank = bankCodeMappings.get(reqData.getcBankCode());
		BankCodeInfo mBank = bankCodeMappings.get(reqData.getmBankCode());

		if (null == cBank) {
			throw new GatewayValidationRejectException(ReturnCode.GW_GENERIC_VALIDATION_REJECTED, "cBankCode无效");
		}
		if (null == mBank) {
			throw new GatewayValidationRejectException(ReturnCode.GW_GENERIC_VALIDATION_REJECTED, "mBankCode无效");
		}

		String cBankName = cBank.getBankName();
		String mBankName = mBank.getBankName();

		String formattedAmount = DECIMAL_FORMAT.format(new BigDecimal(reqData.getAmount().trim()).divide(DECIMAL_100));
		String[] amountParts = formattedAmount.split("\\.");
		String amountIntegerPart = amountParts[0];
		String amountFractionPart = amountParts[1];
		String chineseAmount = AmountUtil.digitUppercase(formattedAmount);

		pageModel.addAttribute("amountIntegerPart", amountIntegerPart);
		pageModel.addAttribute("amountFractionPart", amountFractionPart);
		pageModel.addAttribute("chineseAmount", chineseAmount);

		pageModel.addAttribute("cBankName", cBankName);
		pageModel.addAttribute("mBankName", mBankName);

		String markedMBankAccountNo = reqData.getmBankAccountNo();
		if (markedMBankAccountNo.length() > 8) {
			markedMBankAccountNo = markedMBankAccountNo.substring(0, 4) + "**"
					+ markedMBankAccountNo.substring(markedMBankAccountNo.length() - 4);
		}
		pageModel.addAttribute("markedMBankAccountNo", markedMBankAccountNo);

		Map<String, String> hiddenParams = new LinkedHashMap<String, String>();
		hiddenParams.put("merchantId", reqData.getMerchantId());
		hiddenParams.put("orderNo", reqData.getOrderNo());
		hiddenParams.put("tradeName", reqData.getTradeName());
		hiddenParams.put("amount", reqData.getAmount());
		hiddenParams.put("currency", reqData.getCurrency());
		hiddenParams.put("defaultCBankAccountNo", reqData.getcBankAccountNo());
		hiddenParams.put("defaultCBankCode", reqData.getcBankCode());
		hiddenParams.put("defaultCMobile", reqData.getMobile());
		hiddenParams.put("mBankAccountNo", reqData.getmBankAccountNo());
		hiddenParams.put("mBankAccountName", reqData.getmBankAccountName());
		hiddenParams.put("mBankCode", reqData.getmBankCode());
		hiddenParams.put("lastConfirmTime", reqData.getLastConfirmTime());
		hiddenParams.put("returnUrl", reqData.getReturnUrl());
		hiddenParams.put("notifyUrl", reqData.getNotifyUrl());

		String signKey = getMerchantSignKey(reqData.getMerchantId());
		String hiddenParamsSign = LcMd5SignTool.signMap(hiddenParams, signKey);
		pageModel.addAttribute("hiddenParams", hiddenParams);
		pageModel.addAttribute("hiddenParamsSign", hiddenParamsSign);

		pageModel.addAttribute("returnMerchantUrlMethod", "get");

		return "webpay/mobile/MobileWebPayInput";
	}

}
