package com.cifpay.lc.gateway.controller.sms;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cifpay.lc.api.BusinessInput;
import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.api.gateway.sms.SmsSendMessageService;
import com.cifpay.lc.api.gateway.sms.SmsValidateCodeService;
import com.cifpay.lc.domain.message.SmsParamBean;
import com.cifpay.lc.domain.security.MerchantRequest;
import com.cifpay.lc.domain.sms.SmsSendOutputBean;
import com.cifpay.lc.gateway.controller.GatewayBaseController;
import com.cifpay.lc.gateway.output.AjaxResponse;

@Controller
@RequestMapping("/sms")
public class SmsController extends GatewayBaseController{

	@Autowired
	private SmsSendMessageService smsSendMessageService;
	
	@Autowired
	private SmsValidateCodeService smsValidateCodeService;
	
	@RequestMapping(path = "/sendSmsCode", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse sendSmsCode(@RequestBody MerchantRequest<SmsParamBean> merReq) {
		SmsParamBean smsParam = merReq.getData();
		smsParam.setCreateTime(new Date());
		smsParam.setMerId("SH0001");
		smsParam.setMerName("京东");
		smsParam.setOrderContent("京东订单");
		smsParam.setCardNo("9001");
		smsParam.setAmount(new BigDecimal(10000));
		BusinessOutput<SmsSendOutputBean> output = smsSendMessageService.execute(new BusinessInput<SmsParamBean>(smsParam));
		AjaxResponse response = new AjaxResponse();
		if(output.isSuccess()){
			 response.setSuccess(true);
		}else{
			response.setSuccess(false);
			response.setMessage(output.getReturnMsg());
		}
        return response;
    }
	
	@RequestMapping(path = "/checkSmsCode", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse checkSmsCode(@RequestBody MerchantRequest<SmsParamBean> merReq) {
		SmsParamBean smsParam = merReq.getData();
		smsParam.setMerId("SH0001");
		BusinessOutput<SmsSendOutputBean> output = smsValidateCodeService.execute(new BusinessInput<SmsParamBean>(smsParam));
		AjaxResponse response = new AjaxResponse();
		if(output.isSuccess()){
			 response.setSuccess(true);
		}else{
			response.setSuccess(false);
			response.setMessage(output.getReturnMsg());
		}
        return response;
    }
}
