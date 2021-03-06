package com.cifpay.lc.std.paychannel;

import com.cifpay.lc.constant.enums.PayMethod;

/**
 * 支付渠道
 * Created by sweet on 16-10-30.
 */
public interface BankChannel {
    /**
     * 支付渠道
     *
     * @return
     */
    PayMethod getPayMethod();
}
