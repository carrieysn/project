package com.cifpay.lc.std.paychannel.impl;


import com.cifpay.lc.bankadapter.api.constant.TradeConfig;
import com.cifpay.lc.bankadapter.api.constant.TradeConstant;
import com.cifpay.lc.bankadapter.api.input.bank.FreezeTradeParam;
import com.cifpay.lc.bankadapter.api.input.bank.PayTradeParam;
import com.cifpay.lc.bankadapter.api.output.bank.GeneralTradeResult;
import com.cifpay.lc.bankadapter.api.output.bank.IBankTradeService;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.constant.enums.AccountPropertyType;
import com.cifpay.lc.constant.enums.LcTranStatus;
import com.cifpay.lc.constant.enums.PayMethod;
import com.cifpay.lc.core.db.pojo.Lc;
import com.cifpay.lc.core.db.pojo.LcOpen;
import com.cifpay.lc.core.db.pojo.LcPay;
import com.cifpay.lc.core.db.pojo.PreLc;
import com.cifpay.lc.core.exception.BankAdapterException;
import com.cifpay.lc.core.exception.CoreBusinessException;
import com.cifpay.lc.domain.lc.OpenLcInputBean;
import com.cifpay.lc.domain.lc.OpenLcOutputBean;
import com.cifpay.lc.domain.lc.TransferInputBean;
import com.cifpay.lc.domain.lc.TransferOutputBean;
import com.cifpay.lc.std.domain.paychannel.FreezeInputBean;
import com.cifpay.lc.std.domain.paychannel.FreezeOutputBean;
import com.cifpay.lc.std.domain.paychannel.UnfreezeInputBean;
import com.cifpay.lc.std.domain.paychannel.UnfreezeOutputBean;
import com.cifpay.lc.std.mapper.LcTranStatusMapper;
import com.cifpay.lc.std.paychannel.FreezeInterface;
import com.cifpay.lc.std.paychannel.TransferInterface;
import com.cifpay.lc.std.util.BeanFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by sweet on 16-10-17.
 */
@Component
public class Mock implements FreezeInterface<OpenLcInputBean, OpenLcOutputBean>, TransferInterface<TransferInputBean, TransferOutputBean> {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IBankTradeService tradeService;

    @Override
    public PayMethod getPayMethod() {
        return PayMethod.NORMAL;
    }

    @Override
    public FreezeOutputBean freeze(OpenLcInputBean inputBean, FreezeInputBean freezeInputBean) {
        try {
            PreLc preLc = freezeInputBean.getPreLc();
            LcOpen lcOpen = freezeInputBean.getLcOpen();

            FreezeTradeParam param = new FreezeTradeParam();
            param.setTradeType(TradeConstant.TRADE_CONFIG.TRADE_TYPE_FREEZE);
            param.setTradeBankCode(preLc.getPayerBankCode()); // TradeConstant.TRADE_CONFIG.TRADE_BANK_ICBC

            if (AccountPropertyType.PERSONAL.getCode().compareTo(preLc.getPayerType()) == 0) {
                param.setCustomerType(TradeConstant.TRADE_CONFIG.TRADE_CUSTOMER_TYPE_PERSONAL);
            }

            if (AccountPropertyType.CORPORATE.getCode().compareTo(preLc.getPayerType()) == 0) {
                param.setCustomerType(TradeConstant.TRADE_CONFIG.TRADE_CUSTOMER_TYPE_ENTERPRISE);
            }

            param.setLcId(preLc.getLcId());
            param.setFreezeAmount(preLc.getLcAmount().toString());
            param.setPayerBankCardNo(preLc.getPayerAccno());
            param.setCurrency(preLc.getLcCurrency());

            GeneralTradeResult response = tradeService.doTrade(param);

            //返回结果
            FreezeOutputBean outputBean = BeanFactory.getSucFreezeOutputBean();
            LcTranStatus tranStatus = LcTranStatusMapper.parseUnionResult(response.getTradeResult());

            outputBean.setLcTranStatus(tranStatus);

            return outputBean;

        } catch (BankAdapterException bae) {
            logger.error("银行冻结处理时发生错误", bae);
            throw bae;
        } catch (Exception e) {
            logger.error("**********GeneralTradeService.doTrade()处理异常！*********", e);
            throw new CoreBusinessException(ReturnCode.CORE_STD_BANK_ITF_EXCEPTION, "发送银行发生异常，请查询结果后处理，以防重复交易！", e);
        }
    }

    @Override
    public UnfreezeOutputBean unfreeze(TransferInputBean inputBean, UnfreezeInputBean unfreezeInputBean) {

        Lc lc = unfreezeInputBean.getLc();
        LcPay lcPay = unfreezeInputBean.getLcPay();

        String strCustomerType = TradeConfig.TRADE_CUSTOMER_TYPE_PERSONAL;
        if (lc.getRecvType().compareTo(AccountPropertyType.CORPORATE.getCode()) == 0) {
            strCustomerType = TradeConfig.TRADE_CUSTOMER_TYPE_ENTERPRISE;
        }

        PayTradeParam params = new PayTradeParam();
        params.setTradeType(TradeConfig.TRADE_TYPE_PAY);
        params.setTradeBankCode(lc.getRecvBankCode());
        params.setCustomerType(strCustomerType);
        params.setLcId(lc.getLcId());
        params.setPayAmount(lcPay.getTotalAmount().toString());
        params.setPayerBankCode(lc.getPayerBankCode());
        params.setPayerBankCardNo(lc.getPayerAccno());
        params.setPayeeBankCode(lc.getRecvBankCode());
        params.setPayeeBankCardNo(lc.getRecvAccno());
        params.setBizUnfreezeSerialNo(lc.getLcId());

        try {
            GeneralTradeResult response = tradeService.doTrade(params);

            UnfreezeOutputBean outputBean = new UnfreezeOutputBean();
            LcTranStatus tranStatus = LcTranStatusMapper.parseUnionResult(response.getTradeResult());

            outputBean.setLcTranStatus(tranStatus);

            return outputBean;

        } catch (Exception e) {
            logger.error("调用银行接口失败，{}", e.getMessage());
            throw new CoreBusinessException(ReturnCode.CORE_STD_BANK_ITF_EXCEPTION, "调用银行通道异常");
        }
    }
}
