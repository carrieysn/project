package com.cifpay.lc.std.paychannel;

import com.cifpay.lc.core.exception.PaymentException;
import com.cifpay.lc.domain.lc.OpenLcInputBean;
import com.cifpay.lc.domain.lc.OpenLcOutputBean;
import com.cifpay.lc.std.domain.paychannel.FreezeInputBean;
import com.cifpay.lc.std.domain.paychannel.FreezeOutputBean;

/**
 * 支持开证
 *
 * @param <I>
 * @param <O>
 */
public interface FreezeInterface<I extends OpenLcInputBean, O extends OpenLcOutputBean> extends BankChannel {

    /**
     * 执行开证操作
     *
     * @param inputBean
     * @param freezeInputBean
     * @return
     */
    FreezeOutputBean freeze(I inputBean, FreezeInputBean freezeInputBean) throws PaymentException;
}
