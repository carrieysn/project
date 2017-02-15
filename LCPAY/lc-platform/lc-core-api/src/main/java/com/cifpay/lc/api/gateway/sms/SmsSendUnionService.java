package com.cifpay.lc.api.gateway.sms;

import com.cifpay.lc.api.CoreBusinessService;
import com.cifpay.lc.domain.sms.SmsSendUnionInputBean;
import com.cifpay.lc.domain.sms.SmsSendUnionOutputBean;

/**
 * 开证前，发送银联短信验证码
 */
public interface SmsSendUnionService extends CoreBusinessService<SmsSendUnionInputBean, SmsSendUnionOutputBean> {
}
