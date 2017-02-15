package com.cifpay.lc.std.paychannel;

import com.cifpay.lc.core.exception.PaymentException;
import com.cifpay.lc.domain.lc.TransferInputBean;
import com.cifpay.lc.domain.lc.TransferOutputBean;
import com.cifpay.lc.std.domain.paychannel.UnfreezeInputBean;
import com.cifpay.lc.std.domain.paychannel.UnfreezeOutputBean;

/**
 * 支持解付
 */
public interface TransferInterface<I extends TransferInputBean, O extends TransferOutputBean> extends BankChannel {

    UnfreezeOutputBean unfreeze(I inputBean, UnfreezeInputBean unfreezeInputBean) throws PaymentException;
}
