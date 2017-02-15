package com.cifpay.lc.std.paychannel.impl;

import com.alibaba.fastjson.JSON;
import com.cifpay.lc.bankadapter.api.IBankTradeService;
import com.cifpay.lc.bankadapter.api.constant.TradeConfig;
import com.cifpay.lc.bankadapter.api.input.unionpay.ExpiryCifParam;
import com.cifpay.lc.bankadapter.api.output.GeneralTradeResult;
import com.cifpay.lc.constant.enums.LcTranStatus;
import com.cifpay.lc.constant.enums.PayHandler;
import com.cifpay.lc.core.db.pojo.AdminLcMerCre;
import com.cifpay.lc.core.exception.PaymentException;
import com.cifpay.lc.domain.message.union.CreditParam;
import com.cifpay.lc.std.domain.kernel.InvalidateLcKernelInputBean;
import com.cifpay.lc.std.domain.kernel.InvalidateLcKernelOutputBean;
import com.cifpay.lc.std.domain.paychannel.ExpiryInputBean;
import com.cifpay.lc.std.domain.paychannel.ExpiryOutputBean;
import com.cifpay.lc.std.domain.paychannel.ReqReservedBean;
import com.cifpay.lc.std.mapper.LcTranStatusMapper;
import com.cifpay.lc.std.paychannel.ExpiryInterface;
import com.cifpay.lc.std.util.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by sweet on 17-1-9.
 */
public abstract class UnionExpiryBase extends AbstractUnion implements ExpiryInterface<InvalidateLcKernelInputBean, InvalidateLcKernelOutputBean> {

    @Autowired
    private IBankTradeService bankTradeService;

    @Override
    public ExpiryOutputBean<CreditParam> expiry(InvalidateLcKernelInputBean inputBean, ExpiryInputBean expiryInputBean) throws PaymentException {

        try {
            //
            AdminLcMerCre adminLcMerCre = queryAdminLcMerCreList(inputBean.getMerId());
            ReqReservedBean reqReservedBean = new ReqReservedBean();
            reqReservedBean.setPayHandler(PayHandler.Expiry);
            reqReservedBean.setPayMethod(getPayMethod());

            ExpiryCifParam expiryCifParam = createExpiryCifParam(inputBean, expiryInputBean);

            expiryCifParam.setSubMerId(adminLcMerCre.getXnMerId());
            expiryCifParam.setReqReserved(JSON.toJSONString(reqReservedBean));

            GeneralTradeResult response = bankTradeService.doTrade(expiryCifParam);

            LcTranStatus tranStatus = LcTranStatusMapper.parseUnionResult(response.getTradeResult());

            ExpiryOutputBean<CreditParam> outputBean = null;

            if (TradeConfig.TRADE_RESULT_SUCCEED_0.equalsIgnoreCase(response.getTradeResult())) {

                outputBean = BeanFactory.getSucExpiryOutputBean();

                // 银连受理成功，还未真实解冻
                tranStatus = LcTranStatus.UNCERTAIN;

            } else {
                outputBean = BeanFactory.getFailExpiryOutputBean(response.getResultDesc());
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

        } catch (Throwable e) {
            return BeanFactory.getFailExpiryOutputBean(e.getMessage());
        }
    }

    protected abstract ExpiryCifParam createExpiryCifParam(InvalidateLcKernelInputBean invalidateLcInputBean, ExpiryInputBean expiryInputBean) throws PaymentException;
}
