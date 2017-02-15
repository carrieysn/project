package com.cifpay.lc.api.gateway.sms;

import com.cifpay.lc.api.CoreBusinessService;
import com.cifpay.lc.domain.message.SmsParamBean;
import com.cifpay.lc.domain.sms.SmsSendOutputBean;

public interface SmsSendMessageService extends CoreBusinessService<SmsParamBean,SmsSendOutputBean> {

}
