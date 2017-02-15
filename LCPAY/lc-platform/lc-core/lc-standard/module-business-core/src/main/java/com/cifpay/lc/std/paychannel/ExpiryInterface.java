package com.cifpay.lc.std.paychannel;

import com.cifpay.lc.core.exception.PaymentException;
import com.cifpay.lc.std.domain.kernel.InvalidateLcKernelInputBean;
import com.cifpay.lc.std.domain.kernel.InvalidateLcKernelOutputBean;
import com.cifpay.lc.std.domain.paychannel.ExpiryInputBean;
import com.cifpay.lc.std.domain.paychannel.ExpiryOutputBean;

/**
 * 失效（撤销）接口
 *
 * @param <I>
 * @param <O>
 */
public interface ExpiryInterface<I extends InvalidateLcKernelInputBean, O extends InvalidateLcKernelOutputBean> extends BankChannel {
    ExpiryOutputBean expiry(I inputBean, ExpiryInputBean expiryInputBean) throws PaymentException;
}
