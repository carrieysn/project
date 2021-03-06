package com.cifpay.lc.std.paychannel.impl;

import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.bankadapter.api.helper.GetTradeParamHelper;
import com.cifpay.lc.bankadapter.api.input.unionpay.OpenCifParam;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.constant.enums.PayMethod;
import com.cifpay.lc.core.db.pojo.LcOpen;
import com.cifpay.lc.core.db.pojo.PreLc;
import com.cifpay.lc.core.exception.PaymentException;
import com.cifpay.lc.domain.enums.AdminCardType;
import com.cifpay.lc.domain.lc.OpenLcOutputBean;
import com.cifpay.lc.domain.lc.OpenLcUnionDepositInputBean;
import com.cifpay.lc.domain.sms.SmsSendOutputBean;
import com.cifpay.lc.std.domain.paychannel.FreezeInputBean;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 银联储蓄卡冻结开证
 */
@Component
public class UnionFreezeDeposit extends UnionFreezeBase<OpenLcUnionDepositInputBean, OpenLcOutputBean> {

    @Override
    public PayMethod getPayMethod() {
        return PayMethod.UNION_DEPOSIT;
    }

    @Override
    protected AdminCardType getCardType() {
        return AdminCardType.DEPOSIT;
    }

    @Override
    protected OpenCifParam createOpenCifParam(OpenLcUnionDepositInputBean inputBean,
                                              FreezeInputBean freezeInputBean) throws PaymentException {

        PreLc preLc = freezeInputBean.getPreLc();

        LcOpen lcOpen = freezeInputBean.getLcOpen();

        OpenCifParam param = null;
        try {
            GetTradeParamHelper helper = GetTradeParamHelper.getInstance(GetTradeParamHelper.UNION_PAY);
            param = helper.getTradeParam(OpenCifParam.class, preLc.getLcType(), DEPOSIT);
        } catch (Exception e) {
            logger.error("构建银联参数出错：{}", e.getMessage(), e);
            throw new PaymentException(ReturnCode.CORE_STD_UNION_PARAM_ERROR, "构建银联参数出错");
        }

//        param.setMerId(inputBean.getMerId());
        param.setLcId(preLc.getLcId());
        param.setUserId(preLc.getMerUserId());
//        param.setTxnId(TradeConstant.TRADE_CONFIG.TRADE_UNIONPAY_WU_CONSUME);
        param.setOrderId(lcOpen.getLcOpenId().toString()); // LC_OPEN_ID
        param.setTxnTime(new SimpleDateFormat(AbstractUnion.TXN_TIME_FORMAT).format(new Date()));
        param.setTxnAmt(preLc.getLcAmount().longValue());
        param.setChannelType("07");
        param.setAccNo(inputBean.getAccNo());
        param.setPhoneNo(inputBean.getPhoneNo());
        param.setSmsCode(inputBean.getSmsCode());
//        param.setOrderDesc(preLc.getOrderContent());
//        param.setCertifTp(inputBean.getCertifTp());
//        param.setCertifId(inputBean.getCertifId());
//        param.setCustomerNm(inputBean.getCustomerNm());

        return param;
    }

    @Override
    protected BusinessOutput<SmsSendOutputBean> querySmsCode(String merId, String phone, String smsCode) {
        return BusinessOutput.success(null); //"储蓄卡调用银联验证"
    }
}
