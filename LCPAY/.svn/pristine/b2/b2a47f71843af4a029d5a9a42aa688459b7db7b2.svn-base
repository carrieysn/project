package com.cifpay.lc.gateway.controller.webpay.mobile;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cifpay.lc.util.LcMd5SignTool;

/**
 * 确认并执行交易
 * 
 * 
 *
 */
@Controller
@RequestMapping("/webpay/mobile")
public class MobileWebPayTradeController extends MobileWebPayBaseController {

	@RequestMapping(path = "/confirmpay", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, String> confirmPay(@RequestParam Map<String, String> params, Model pageModel) {
		logger.info("确认支付...");
		Map<String, String> payResult = new LinkedHashMap<String, String>();

		String merchantId = params.get("merchantId");
		String orderNo = params.get("orderNo");
		String tradeName = params.get("tradeName");
		String amount = params.get("amount");
		String currency = params.get("currency");
		String defaultCBankAccountNo = params.get("defaultCBankAccountNo");
		String defaultCBankCode = params.get("defaultCBankCode");
		String defaultCMobile = params.get("defaultCMobile");
		String mBankAccountNo = params.get("mBankAccountNo");
		String mBankAccountName = params.get("mBankAccountName");
		String mBankCode = params.get("mBankCode");
		String lastConfirmTime = params.get("lastConfirmTime");
		String returnUrl = params.get("returnUrl");
		String notifyUrl = params.get("notifyUrl");

		String cBankAccountNo = params.get("cBankAccountNo");
		String smsCodeMobile = params.get("smsCodeMobile");
		String smsCode = params.get("smsCode");
		String hiddenParamsSign = params.get("hiddenParamsSign");
		try {
			Map<String, String> prevSignedParams = new LinkedHashMap<String, String>();
			prevSignedParams.put("merchantId", merchantId);
			prevSignedParams.put("orderNo", orderNo);
			prevSignedParams.put("tradeName", tradeName);
			prevSignedParams.put("amount", amount);
			prevSignedParams.put("currency", currency);
			prevSignedParams.put("defaultCBankAccountNo", defaultCBankAccountNo);
			prevSignedParams.put("defaultCBankCode", defaultCBankCode);
			prevSignedParams.put("defaultCMobile", defaultCMobile);
			prevSignedParams.put("mBankAccountNo", mBankAccountNo);
			prevSignedParams.put("mBankAccountName", mBankAccountName);
			prevSignedParams.put("mBankCode", mBankCode);
			prevSignedParams.put("lastConfirmTime", lastConfirmTime);
			prevSignedParams.put("returnUrl", returnUrl);
			prevSignedParams.put("notifyUrl", notifyUrl);
			prevSignedParams.put("cBankAccountNo", cBankAccountNo);
			prevSignedParams.put("smsCodeMobile", smsCodeMobile);

			String signKey = getMerchantSignKey(merchantId);
			if (!LcMd5SignTool.signMap(prevSignedParams, signKey).equals(hiddenParamsSign)) {
				payResult.put("returnCode", "fail");
				payResult.put("returnMsg", "非法请求，交易参数已被篡改");
			} else {

				// TODO 临时模拟逻辑
				String lcId = String.valueOf(System.currentTimeMillis());

				logger.info("支付成功，merchantId: {}, orderNo: {}, lcId：{}", merchantId, orderNo, lcId);

				payResult.put("returnCode", "success");
				payResult.put("lcId", lcId);
				payResult.put("orderNo", orderNo);
				payResult.put("amount", amount);
			}
		} catch (Exception e) {
			logger.error("支付确认时发生未知异常，{}", e.getMessage(), e);
			payResult.put("returnCode", "fail");
			payResult.put("returnMsg", "系统异常");
		}

		return payResult;
	}

}
