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
 * 输入支付信息（适用手机、PAD等移动终端的浏览器，包括终端上APP应用内嵌的浏览器组件）
 * 
 * 
 *
 */
@Controller
@RequestMapping("/webpay/mobile")
public class MobileWebPaySmsCodeController extends MobileWebPayBaseController {

	@RequestMapping(path = "/sendSmsCode", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, String> sendSmsCode(@RequestParam Map<String, String> params, Model pageModel) {
		Map<String, String> payResult = new LinkedHashMap<String, String>();
		
		try {
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
			String mobile = params.get("mobile");
			String lastConfirmTime = params.get("lastConfirmTime");
			String returnUrl = params.get("returnUrl");
			String notifyUrl = params.get("notifyUrl");

			String cBankAccountNo = params.get("cBankAccountNo");
			String smsCodeMobile = params.get("smsCodeMobile");

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

			String prevHiddenParamsSign = params.get("hiddenParamsSign");
			String signKey = getMerchantSignKey(merchantId);
			if (!LcMd5SignTool.signMap(prevSignedParams, signKey).equals(prevHiddenParamsSign)) {
				payResult.put("returnCode", "fail");
				payResult.put("returnMsg", "非法请求，交易参数已被篡改");
				return payResult;
			}

			prevSignedParams.put("cBankAccountNo", cBankAccountNo);
			prevSignedParams.put("smsCodeMobile", smsCodeMobile);
			String updatedHiddenParamsSign = LcMd5SignTool.signMap(prevSignedParams, signKey);

			logger.info("TODO：发送短信 ，手机号：{} ...", smsCodeMobile);

			payResult.put("returnCode", "success");
			payResult.put("updatedHiddenParamsSign", updatedHiddenParamsSign);
		} catch (Exception e) {
			logger.error("发送短信验证码失败，{}", e.getMessage(), e);
			payResult.put("returnCode", "fail");
			payResult.put("returnMsg", "发送短信验证码失败");
		}
		
		return payResult;
	}

}
