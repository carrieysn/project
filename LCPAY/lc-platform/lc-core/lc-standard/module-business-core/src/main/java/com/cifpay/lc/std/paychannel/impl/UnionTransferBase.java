package com.cifpay.lc.std.paychannel.impl;

import com.alibaba.fastjson.JSON;
import com.cifpay.lc.bankadapter.api.IBankTradeService;
import com.cifpay.lc.bankadapter.api.constant.TradeConfig;
import com.cifpay.lc.bankadapter.api.input.unionpay.PayCifParam;
import com.cifpay.lc.bankadapter.api.output.GeneralTradeResult;
import com.cifpay.lc.constant.enums.LcTranStatus;
import com.cifpay.lc.constant.enums.LcTypeEnum;
import com.cifpay.lc.constant.enums.PayHandler;
import com.cifpay.lc.core.db.pojo.AdminLcMerCre;
import com.cifpay.lc.core.db.pojo.Lc;
import com.cifpay.lc.core.exception.PaymentException;
import com.cifpay.lc.domain.lc.TransferInputBean;
import com.cifpay.lc.domain.lc.TransferOutputBean;
import com.cifpay.lc.domain.message.union.CreditParam;
import com.cifpay.lc.std.domain.paychannel.ReqReservedBean;
import com.cifpay.lc.std.domain.paychannel.UnfreezeInputBean;
import com.cifpay.lc.std.domain.paychannel.UnfreezeOutputBean;
import com.cifpay.lc.std.mapper.LcTranStatusMapper;
import com.cifpay.lc.std.paychannel.TransferInterface;
import com.cifpay.lc.std.util.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by sweet on 17-1-9.
 */
public abstract class UnionTransferBase extends AbstractUnion implements TransferInterface<TransferInputBean, TransferOutputBean> {

    @Autowired
    private IBankTradeService bankTradeService;

    @Override
    public UnfreezeOutputBean<CreditParam> unfreeze(TransferInputBean inputBean, UnfreezeInputBean unfreezeInputBean) throws PaymentException {

        Lc lc = unfreezeInputBean.getLc();

        // CP200不需要发起交易，只把数据库数据补全
        if (LcTypeEnum.CP200.getCode().equalsIgnoreCase(lc.getLcType())) {
            return BeanFactory.getSucUnfreezeOutputBean();
        }

        try {

            //
            AdminLcMerCre adminLcMerCre = queryAdminLcMerCreList(inputBean.getMerId());
            ReqReservedBean reqReservedBean = new ReqReservedBean();
            reqReservedBean.setPayHandler(PayHandler.UNFREEZE);
            reqReservedBean.setPayMethod(getPayMethod());

            PayCifParam payCifParam = createTransferCifParam(inputBean, unfreezeInputBean);

            payCifParam.setSubMerId(adminLcMerCre.getXnMerId());
            payCifParam.setReqReserved(JSON.toJSONString(reqReservedBean));

            GeneralTradeResult response = bankTradeService.doTrade(payCifParam);
            LcTranStatus tranStatus = LcTranStatusMapper.parseUnionResult(response.getTradeResult());

            UnfreezeOutputBean<CreditParam> outputBean = null;

            if (TradeConfig.TRADE_RESULT_SUCCEED_0.equalsIgnoreCase(response.getTradeResult())) {

                outputBean = BeanFactory.getSucUnfreezeOutputBean();

                // 银连受理成功，还未真实解冻
                tranStatus = LcTranStatus.UNCERTAIN;

            } else {
                outputBean = BeanFactory.getFailUnfreezeOutputBean(response.getResultDesc());
            }

            // 返回结果
            outputBean.setSerialNo(response.getQueryId());
            outputBean.setMessage(response.getResultDesc());
            outputBean.setLcTranStatus(tranStatus);

            CreditParam creditParam = new CreditParam();
            creditParam.setTxnTime(payCifParam.getTxnTime());
            creditParam.setTradeResult(response.getTradeResult());
            creditParam.setResultDesc(response.getResultDesc());
            outputBean.setData(creditParam);

            return outputBean;

        } catch (Throwable e) {
            return BeanFactory.getFailUnfreezeOutputBean(e.getMessage());
        }
    }

    /**
     * @param inputBean
     * @param unfreezeInputBean
     * @return
     * @throws Exception
     */
    protected abstract PayCifParam createTransferCifParam(TransferInputBean inputBean, UnfreezeInputBean unfreezeInputBean) throws PaymentException;
}
