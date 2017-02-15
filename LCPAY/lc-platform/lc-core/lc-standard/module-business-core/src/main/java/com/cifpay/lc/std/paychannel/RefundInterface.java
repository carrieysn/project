package com.cifpay.lc.std.paychannel;

import com.cifpay.lc.core.exception.PaymentException;
import com.cifpay.lc.domain.lc.RefundLcInputBean;
import com.cifpay.lc.std.domain.kernel.RefundKernalInputBean;
import com.cifpay.lc.std.domain.kernel.RefundKernalOutputBean;

import java.io.Serializable;

public interface RefundInterface<I extends RefundLcInputBean, O extends RefundKernalOutputBean<? extends Serializable>> extends BankChannel {

    public O refund(I input, RefundKernalInputBean input2) throws PaymentException;
}
