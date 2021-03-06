package com.cifpay.lc.std.business.message.lc;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.api.message.lc.NotifyMerchMessageService;
import com.cifpay.lc.api.security.SecurityService;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.core.common.CoreBusinessContext;
import com.cifpay.lc.core.common.CoreBusinessInterceptorConfig;
import com.cifpay.lc.core.exception.CoreBusinessException;
import com.cifpay.lc.core.http.HttpUtils;
import com.cifpay.lc.domain.message.LcNotifyParamBean;
import com.cifpay.lc.domain.message.MessageOutputBean;
import com.cifpay.lc.domain.security.MerchantSignedResponse;
import com.cifpay.lc.std.interceptor.BusinessLoggingInterceptor;
import com.cifpay.lc.std.interceptor.MessageLockInterceptor;

/**
 * 异步通知商户
 *
 * @author Administrator
 */
@Service("notifyMerchMessageService")
@CoreBusinessInterceptorConfig({BusinessLoggingInterceptor.class, MessageLockInterceptor.class})
public class NotifyMerchMessageServiceImpl extends LcMessageServiceBase<LcNotifyParamBean> implements NotifyMerchMessageService {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private HttpUtils httpServant;

    @Override
    protected BusinessOutput<MessageOutputBean> handleMessage(LcNotifyParamBean messageParams,
                                                              CoreBusinessContext context) throws CoreBusinessException {
        logger.info("处理异步通知:{}", messageParams.getNotifyUrl());

        BusinessOutput<MerchantSignedResponse> encryptResponse = securityService.encryptData(messageParams.getMerId(), messageParams.getMap());
        if (encryptResponse.isFailed()) {
            return BusinessOutput.fail(encryptResponse.getReturnCode(), encryptResponse.getReturnMsg());
        }

        MerchantSignedResponse data = encryptResponse.getData();

        Map<String, String> request = new HashMap<>();
        request.put("encodedJsonData", data.getEncodedJsonData());
        request.put("sign", data.getSign());

        // 商户收到通知后响应信息
        String response = httpServant.post(messageParams.getNotifyUrl(), request);
        if ("success".equals(response)) {
            return BusinessOutput.success(new MessageOutputBean(true));
        } else {
            return BusinessOutput.fail(ReturnCode.CORE_STD_LC_ASYNCHRONOUS_FAIL, "异步通知商户失败!");
        }
    }

}
