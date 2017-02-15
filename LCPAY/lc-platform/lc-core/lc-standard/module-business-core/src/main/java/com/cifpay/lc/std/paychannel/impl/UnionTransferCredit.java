package com.cifpay.lc.std.paychannel.impl;

import com.cifpay.lc.bankadapter.api.helper.GetTradeParamHelper;
import com.cifpay.lc.bankadapter.api.input.unionpay.PayCifParam;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.constant.enums.PayMethod;
import com.cifpay.lc.core.db.pojo.Lc;
import com.cifpay.lc.core.db.pojo.LcOpen;
import com.cifpay.lc.core.db.pojo.LcPay;
import com.cifpay.lc.core.exception.PaymentException;
import com.cifpay.lc.domain.enums.AdminCardType;
import com.cifpay.lc.domain.lc.TransferInputBean;
import com.cifpay.lc.std.domain.paychannel.UnfreezeInputBean;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 银联信用卡解冻划款
 */
@Component
public class UnionTransferCredit extends UnionTransferBase {

    @Override
    public PayMethod getPayMethod() {
        return PayMethod.UNION_CREDIT;
    }

    @Override
    protected AdminCardType getCardType() {
        return AdminCardType.CREDIT;
    }

    @Override
    protected PayCifParam createTransferCifParam(TransferInputBean inputBean, UnfreezeInputBean unfreezeInputBean) throws PaymentException {

        Lc lc = unfreezeInputBean.getLc();
        LcOpen lcOpen = unfreezeInputBean.getLcOpen();
        LcPay lcPay = unfreezeInputBean.getLcPay();

        PayCifParam payCifParam = null;
        try {
            payCifParam = GetTradeParamHelper.getInstance(GetTradeParamHelper.UNION_PAY).getTradeParam(PayCifParam.class, lc.getLcType(), CREDIT);
        } catch (Exception e) {
            logger.error("构建银联参数出错：{}", e.getMessage(), e);
            throw new PaymentException(ReturnCode.CORE_STD_UNION_PARAM_ERROR, "构建银联参数出错");
        }
        payCifParam.setLcId(inputBean.getLcId());
        payCifParam.setChannelType("07");
        payCifParam.setOrigOryId(lcOpen.getUnionSerialNo());
        payCifParam.setOrderId(lcPay.getLcPayId().toString());    // lcPayId 银联要求每次请求orderId必须不同
        payCifParam.setTxnTime(new SimpleDateFormat(TXN_TIME_FORMAT).format(new Date()));
        payCifParam.setTxnAmt(lcOpen.getLcAmount().longValue());

        return payCifParam;
    }

}
