package com.cifpay.lc.std.business.message.lc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cifpay.lc.api.BusinessInput;
import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.api.gateway.lc.RecvLcService;
import com.cifpay.lc.api.message.lc.AutoRecvLcMessageService;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.constant.enums.AccountPropertyType;
import com.cifpay.lc.core.cache.pojo.MerchantCache;
import com.cifpay.lc.core.cache.service.MerchantCacheServant;
import com.cifpay.lc.core.common.CoreBusinessContext;
import com.cifpay.lc.core.common.CoreBusinessInterceptorConfig;
import com.cifpay.lc.core.exception.CoreBusinessException;
import com.cifpay.lc.domain.lc.RecvLcInputBean;
import com.cifpay.lc.domain.lc.RecvLcOutputBean;
import com.cifpay.lc.domain.message.LcRecvParamBean;
import com.cifpay.lc.domain.message.MessageOutputBean;
import com.cifpay.lc.exception.MessageBusinessException;
import com.cifpay.lc.std.interceptor.BusinessLoggingInterceptor;
import com.cifpay.lc.std.interceptor.MessageLockInterceptor;

@Service("autoRecvLcMessageService")
@CoreBusinessInterceptorConfig({BusinessLoggingInterceptor.class, MessageLockInterceptor.class})
public class AutoRecvLcMessageServiceImpl extends LcMessageServiceBase<LcRecvParamBean> implements AutoRecvLcMessageService {

    @Autowired
    private MerchantCacheServant merchantCacheServant;

    @Autowired
    private RecvLcService recvLcService;

    @Override
    protected BusinessOutput<MessageOutputBean> handleMessage(LcRecvParamBean lcRecvParamBean, CoreBusinessContext context) throws CoreBusinessException {

        MerchantCache merInfo = merchantCacheServant.getMerchantCache(lcRecvParamBean.getMerId());
        if (merInfo == null) {
            throw new MessageBusinessException(ReturnCode.CORE_MSG_NOT_FOUND_EXCEPTION_N108001, "商户信息不存在");
        }

        RecvLcInputBean recvLcInputBean = new RecvLcInputBean();
        recvLcInputBean.setLcId(lcRecvParamBean.getLcId());
        recvLcInputBean.setMerId(lcRecvParamBean.getMerId());
        recvLcInputBean.setRecvAccountType(AccountPropertyType.CORPORATE);
        recvLcInputBean.setRecvBankAccountNo(merInfo.getBankAccount());
        recvLcInputBean.setRecvBankCode(merInfo.getBankCode());
        recvLcInputBean.setRecvMobile("");

        BusinessOutput<RecvLcOutputBean> output = recvLcService.execute(new BusinessInput<RecvLcInputBean>(recvLcInputBean));

        if (output.isSuccess()) {
            return BusinessOutput.success(new MessageOutputBean(true));
        }

        return BusinessOutput.fail(ReturnCode.CORE_MSG_UNKNOWN_ERROR, "消息处理失败");
    }
}
