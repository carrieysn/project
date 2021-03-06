package com.cifpay.lc.std.business.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cifpay.lc.api.BusinessInput;
import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.api.gateway.sms.SmsValidateCodeService;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.core.cache.pojo.SmsCache;
import com.cifpay.lc.core.cache.service.SmsCacheServant;
import com.cifpay.lc.core.common.CoreBusinessInterceptorConfig;
import com.cifpay.lc.domain.message.SmsParamBean;
import com.cifpay.lc.domain.sms.SmsSendOutputBean;
import com.cifpay.lc.std.interceptor.BusinessLoggingInterceptor;

/**
 * 验证短信
 * @author Administrator
 *
 */

@Service("smsValidateCodeService")
@CoreBusinessInterceptorConfig({ BusinessLoggingInterceptor.class})
public class SmsValidateCodeServiceImpl implements SmsValidateCodeService{

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SmsCacheServant smsCacheServant;

	@Override
	public BusinessOutput<SmsSendOutputBean> execute(BusinessInput<SmsParamBean> input) {
		SmsParamBean smsParamBean = input.getData();
		String validate = validate(smsParamBean);
		if(!StringUtils.isEmpty(validate)){
			return BusinessOutput.fail(ReturnCode.SERVER_PARAM_ERROR, validate);
		}
		String key = smsParamBean.getMerId()+"_"+smsParamBean.getPhone()+"_"+smsParamBean.getSmstype();
		SmsCache smsCache = smsCacheServant.getSmsCache(key);
		if(smsCache==null){
			return BusinessOutput.fail(ReturnCode.CORE_STD_SMS_CODE_INCORRECT, "短信验证码不正确");
		}else{
			if(smsParamBean.getSmsCode().equals(smsCache.getSmsCode())){
				//验证成功后删除
				smsCacheServant.removeCache(key);
				return BusinessOutput.success(null);
			}else{
				return BusinessOutput.fail(ReturnCode.CORE_STD_SMS_CODE_INCORRECT, "短信验证码不正确");
			}
		}


	}

	private String validate(SmsParamBean smsParam){
		if(StringUtils.isEmpty(smsParam.getSmsCode())){
        	return "短信验证码为空";
        }
		if(StringUtils.isEmpty(smsParam.getPhone())){
        	return "手机号为空";
        }
		if(StringUtils.isEmpty(smsParam.getSmstype()) || smsParam.getSmstype() == 0){
        	return "短信模版类型为空";
        }
		if(StringUtils.isEmpty(smsParam.getMerId())){
        	return "商户号为空";
        }
		return null;
	}

}
