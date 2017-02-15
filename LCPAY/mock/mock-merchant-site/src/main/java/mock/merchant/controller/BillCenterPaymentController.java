package mock.merchant.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Formatter;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mock.merchant.common.LcGatewayConfig;
import mock.merchant.common.tool.LcMd5SignTool;

@Controller
@RequestMapping("/billcenter/order")
public class BillCenterPaymentController {

	private final String merchantId = "1000002";
	private final String signKey = "3cd2fad8aaf34115a16a17346b041b0b";

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@RequestMapping(path = "/settlement", method = RequestMethod.GET)
	public String input(Model pageModel) {
		logger.info("~~~~~~~BillCenterPaymentController.input ... ");

		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, 7);
		String lastConfirmTime = dateFormat.format(c.getTime());
		pageModel.addAttribute("lastConfirmTime", lastConfirmTime);

		return "billcenter/OrderSettlement";
	}

	@RequestMapping(path = "/topay", method = RequestMethod.POST)
	public String topay(HttpServletRequest request, @RequestParam("tradeName") String tradeName,
			@RequestParam("amount") String amount, @RequestParam("cBankCode") String cBankCode,
			@RequestParam("cBankAccountNo") String cBankAccountNo, @RequestParam("mobile") String mobile,
			@RequestParam("mBankCode") String mBankCode, @RequestParam("mBankAccountNo") String mBankAccountNo,
			@RequestParam("mBankAccountName") String mBankAccountName,
			@RequestParam("lastConfirmTime") String lastConfirmTime, Model pageModel) {

		logger.info("~~~~~~~BillCenterPaymentController.topay ... ");

		String orderNo = "MK" + System.currentTimeMillis();
		String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
		String returnUrl = baseUrl + "/billcenter/order/return";
		String notifyUrl = baseUrl + "/billcenter/order/notify";

		Map<String, String> payData = new LinkedHashMap<String, String>();
		payData.put("merchantId", merchantId);
		payData.put("orderNo", orderNo);
		payData.put("tradeName", tradeName);
		payData.put("amount", amount);
		payData.put("currency", "CNY");
		payData.put("cBankAccountNo", cBankAccountNo);
		payData.put("cBankCode", cBankCode);
		payData.put("mBankAccountNo", mBankAccountNo);
		payData.put("mBankAccountName", mBankAccountName);
		payData.put("mBankCode", mBankCode);
		payData.put("mobile", mobile);
		payData.put("lastConfirmTime", lastConfirmTime);
		payData.put("returnUrl", returnUrl);
		payData.put("notifyUrl", notifyUrl);

		String encodedJsonData = "";
		try {
			encodedJsonData = Base64Utils.encodeToString(new ObjectMapper().writeValueAsBytes(payData));
		} catch (JsonProcessingException e) {
			throw new RuntimeException("JSON转换失败", e);
		}

		String cifpayWebPayUrl = LcGatewayConfig.getLcGatewayBaseURL() + "/webpay/mobile/topay";

		pageModel.addAttribute("cifpayWebPayUrl", cifpayWebPayUrl);
		pageModel.addAttribute("merId", merchantId);
		pageModel.addAttribute("sign", LcMd5SignTool.signString(encodedJsonData, signKey));
		pageModel.addAttribute("encodedJsonData", encodedJsonData);

		return "billcenter/Pay";
	}

	@RequestMapping(path = "/return", method = { RequestMethod.GET, RequestMethod.POST })
	public String cifpayReturn(@RequestParam Map<String, String> params, Model pageModel) {

		try (Formatter f = new Formatter()) {
			String returnData = f.format("<p>订单支付成功，银信证返回：</p><p>%s</p>", params).toString();
			pageModel.addAttribute("returnData", returnData);
		}

		return "billcenter/Result";

	}

	@RequestMapping(path = "/notify", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String cifpayNotify(@RequestBody Map<String, String> notify) {
		logger.info("收到银信证的异步通知:{}", notify);
		return "SUCCESS";

	}

}
