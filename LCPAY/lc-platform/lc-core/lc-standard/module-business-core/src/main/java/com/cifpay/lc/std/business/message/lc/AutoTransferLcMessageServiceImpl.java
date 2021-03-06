package com.cifpay.lc.std.business.message.lc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cifpay.lc.api.BusinessInput;
import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.api.gateway.lc.TransferService;
import com.cifpay.lc.api.message.lc.AutoTransferLcMessageService;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.core.common.CoreBusinessContext;
import com.cifpay.lc.core.common.CoreBusinessInterceptorConfig;
import com.cifpay.lc.core.exception.CoreBusinessException;
import com.cifpay.lc.domain.lc.TransferInputBean;
import com.cifpay.lc.domain.lc.TransferOutputBean;
import com.cifpay.lc.domain.message.LcTansferParamBean;
import com.cifpay.lc.domain.message.MessageOutputBean;
import com.cifpay.lc.std.interceptor.BusinessLoggingInterceptor;
import com.cifpay.lc.std.interceptor.MessageLockInterceptor;

@Service("autoTransferLcMessageService")
@CoreBusinessInterceptorConfig({BusinessLoggingInterceptor.class, MessageLockInterceptor.class})
public class AutoTransferLcMessageServiceImpl extends LcMessageServiceBase<LcTansferParamBean> implements AutoTransferLcMessageService {


    @Autowired
    private TransferService transferService;

    @Override
    protected BusinessOutput<MessageOutputBean> handleMessage(LcTansferParamBean messageParams, CoreBusinessContext context) throws CoreBusinessException {


        TransferInputBean transferInputBean = new TransferInputBean();
        transferInputBean.setLcId(messageParams.getLcId());
        transferInputBean.setMerId(messageParams.getMerId());
        transferInputBean.setApplyId(messageParams.getApplyId());
        transferInputBean.setRemark("申请解付阶段自动执行解付");

        BusinessOutput<TransferOutputBean> output = transferService.execute(new BusinessInput<TransferInputBean>(transferInputBean));

        if (output.isSuccess()) {
            return BusinessOutput.success(new MessageOutputBean(true));
        }

        return BusinessOutput.fail(ReturnCode.CORE_MSG_UNKNOWN_ERROR, "消息处理失败");
    }

}
