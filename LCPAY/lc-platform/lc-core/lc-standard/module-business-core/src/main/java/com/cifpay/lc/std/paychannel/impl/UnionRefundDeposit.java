package com.cifpay.lc.std.paychannel.impl;

import com.alibaba.fastjson.JSON;
import com.cifpay.lc.bankadapter.api.helper.GetTradeParamHelper;
import com.cifpay.lc.bankadapter.api.input.unionpay.RefundCifParam;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.constant.enums.LcTypeEnum;
import com.cifpay.lc.constant.enums.PayHandler;
import com.cifpay.lc.constant.enums.PayMethod;
import com.cifpay.lc.core.db.pojo.Lc;
import com.cifpay.lc.core.db.pojo.LcOpen;
import com.cifpay.lc.core.exception.PaymentException;
import com.cifpay.lc.domain.enums.AdminCardType;
import com.cifpay.lc.domain.lc.RefundLcInputBean;
import com.cifpay.lc.std.domain.kernel.RefundKernalInputBean;
import com.cifpay.lc.std.domain.paychannel.ReqReservedBean;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 银联储蓄卡退款
 */
@Component
public class UnionRefundDeposit extends UnionRefundBase {

    @Override
    public PayMethod getPayMethod() {
        return PayMethod.UNION_DEPOSIT;
    }

    @Override
    protected AdminCardType getCardType() {
        return AdminCardType.DEPOSIT;
    }

    @Override
    protected RefundCifParam createRefundCifParam(RefundLcInputBean input, RefundKernalInputBean refundKernalInputBean, String subMerId) throws PaymentException {

        RefundCifParam param = null;
        try {
            GetTradeParamHelper helper = GetTradeParamHelper.getInstance(GetTradeParamHelper.UNION_PAY);
            param = helper.getTradeParam(RefundCifParam.class, refundKernalInputBean.getLc().getLcType(), DEPOSIT);
        } catch (Exception e) {
            logger.error("构建银联参数出错：{}", e.getMessage(), e);
            throw new PaymentException(ReturnCode.CORE_STD_UNION_PARAM_ERROR, "构建银联参数出错");
        }

        LcOpen lcOpen = refundKernalInputBean.getLcOpen();
        Lc lc = refundKernalInputBean.getLc();

        ReqReservedBean reqReservedBean = new ReqReservedBean();
        reqReservedBean.setPayHandler(PayHandler.Refund);

        param.setReqReserved(JSON.toJSONString(reqReservedBean));
        param.setLcId(lcOpen.getLcId());
        param.setOrderId(refundKernalInputBean.getLcRefund().getLcRefundId().toString());
        param.setTxnTime(new SimpleDateFormat(TXN_TIME_FORMAT).format(new Date()));
        param.setTxnAmt(input.getRefundAmount().longValue());
        param.setChannelType("07");
        param.setSubMerId(subMerId);

        if (LcTypeEnum.CP200.getCode().compareTo(lc.getLcType()) == 0) {
            param.setOrigOryId(lcOpen.getUnionSerialNo());
        } else if (LcTypeEnum.CP300.getCode().compareTo(lc.getLcType()) == 0) {
            param.setOrigOryId(refundKernalInputBean.getLcPay().getUnionSerialNo());
        }

        return param;
    }
}
