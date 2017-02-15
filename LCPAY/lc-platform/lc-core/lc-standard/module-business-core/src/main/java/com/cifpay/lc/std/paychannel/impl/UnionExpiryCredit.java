package com.cifpay.lc.std.paychannel.impl;

import com.cifpay.lc.bankadapter.api.helper.GetTradeParamHelper;
import com.cifpay.lc.bankadapter.api.input.unionpay.ExpiryCifParam;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.constant.enums.PayMethod;
import com.cifpay.lc.core.db.pojo.Lc;
import com.cifpay.lc.core.db.pojo.LcInvalid;
import com.cifpay.lc.core.db.pojo.LcOpen;
import com.cifpay.lc.core.exception.PaymentException;
import com.cifpay.lc.domain.enums.AdminCardType;
import com.cifpay.lc.std.domain.kernel.InvalidateLcKernelInputBean;
import com.cifpay.lc.std.domain.paychannel.ExpiryInputBean;
import org.springframework.stereotype.Component;

/**
 * 银联信用卡失效
 */
@Component
public class UnionExpiryCredit extends UnionExpiryBase {

    @Override
    public PayMethod getPayMethod() {
        return PayMethod.UNION_CREDIT;
    }

    @Override
    protected AdminCardType getCardType() {
        return AdminCardType.CREDIT;
    }

    @Override
    protected ExpiryCifParam createExpiryCifParam(InvalidateLcKernelInputBean invalidateLcInputBean, ExpiryInputBean expiryInputBean) throws PaymentException {
        Lc lc = expiryInputBean.getLc();
        LcOpen lcOpen = expiryInputBean.getLcOpen();
        LcInvalid lcInvalid = expiryInputBean.getLcInvalid();

        ExpiryCifParam expiryCifParam = null;
        try {
            GetTradeParamHelper helper = GetTradeParamHelper.getInstance(GetTradeParamHelper.UNION_PAY);
            expiryCifParam = helper.getTradeParam(ExpiryCifParam.class, lc.getLcType(), CREDIT);
        } catch (Exception e) {
            logger.error("构建银联参数出错：{}", e.getMessage(), e);
            throw new PaymentException(ReturnCode.CORE_STD_UNION_PARAM_ERROR, "构建银联参数出错");
        }

        expiryCifParam.setLcId(invalidateLcInputBean.getLcId());
        expiryCifParam.setChannelType("07");
        expiryCifParam.setOrderId(lcInvalid.getLcInvalidId().toString());
        expiryCifParam.setOrigOryId(lcOpen.getUnionSerialNo());
        expiryCifParam.setTxnTime(lcOpen.getUnionTxntime());
        expiryCifParam.setTxnAmt(lcOpen.getLcAmount().longValue());

        return expiryCifParam;
    }

}
