package com.cifpay.lc.std.paychannel.impl;

import com.alibaba.fastjson.JSON;
import com.cifpay.lc.bankadapter.api.IBankTradeService;
import com.cifpay.lc.bankadapter.api.constant.TradeConfig;
import com.cifpay.lc.bankadapter.api.input.unionpay.RefundCifParam;
import com.cifpay.lc.bankadapter.api.output.GeneralTradeResult;
import com.cifpay.lc.constant.enums.LcTranStatus;
import com.cifpay.lc.constant.enums.PayHandler;
import com.cifpay.lc.core.db.pojo.AdminLcMerCre;
import com.cifpay.lc.core.exception.PaymentException;
import com.cifpay.lc.domain.lc.RefundLcInputBean;
import com.cifpay.lc.domain.message.union.CreditParam;
import com.cifpay.lc.std.domain.kernel.RefundKernalInputBean;
import com.cifpay.lc.std.domain.kernel.RefundKernalOutputBean;
import com.cifpay.lc.std.domain.paychannel.ReqReservedBean;
import com.cifpay.lc.std.mapper.LcTranStatusMapper;
import com.cifpay.lc.std.paychannel.RefundInterface;
import com.cifpay.lc.std.util.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by sweet on 17-1-9.
 */
public abstract class UnionRefundBase extends AbstractUnion implements RefundInterface<RefundLcInputBean, RefundKernalOutputBean<CreditParam>> {

    @Autowired
    private IBankTradeService bankTradeService;

    @Override
    public RefundKernalOutputBean<CreditParam> refund(RefundLcInputBean input, RefundKernalInputBean input2) throws PaymentException {

        try {

            //
            AdminLcMerCre adminLcMerCre = queryAdminLcMerCreList(input.getMerId());
            ReqReservedBean reqReservedBean = new ReqReservedBean();
            reqReservedBean.setPayHandler(PayHandler.Refund);
            reqReservedBean.setPayMethod(getPayMethod());

            RefundCifParam param = createRefundCifParam(input, input2, adminLcMerCre.getXnMerId());
            param.setReqReserved(JSON.toJSONString(reqReservedBean));

            GeneralTradeResult response = bankTradeService.doTrade(param);

            LcTranStatus tranStatus = LcTranStatusMapper.parseUnionResult(response.getTradeResult());

            RefundKernalOutputBean<CreditParam> outputBean = null;

            if (TradeConfig.TRADE_RESULT_SUCCEED_0.equalsIgnoreCase(response.getTradeResult())) {

                outputBean = BeanFactory.getSucRefundOutputBean();

                // 银连受理成功，还未真实解冻
                tranStatus = LcTranStatus.UNCERTAIN;

            } else {
                outputBean = BeanFactory.getFailRefundOutputBean(response.getResultDesc());
            }

            // 返回结果
            outputBean.setSerialNo(response.getQueryId());
            outputBean.setMessage(response.getResultDesc());
            outputBean.setLcTranStatus(tranStatus);

            CreditParam creditParam = new CreditParam();
            creditParam.setTradeResult(response.getTradeResult());
            creditParam.setResultDesc(response.getResultDesc());
            outputBean.setData(creditParam);

            return outputBean;

        } catch (Exception e) {
            return BeanFactory.getFailRefundOutputBean(e.getMessage());
        }
    }

    protected abstract RefundCifParam createRefundCifParam(RefundLcInputBean input, RefundKernalInputBean refundKernalInputBean, String subMerId) throws PaymentException;
}
