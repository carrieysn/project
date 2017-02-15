package com.cifpay.lc.std.domain.paychannel;

import com.cifpay.lc.constant.enums.PayHandler;
import com.cifpay.lc.constant.enums.PayMethod;

import java.io.Serializable;

/**
 * Created by sweet on 16-11-9.
 */
public class ReqReservedBean implements Serializable {
    private static final long serialVersionUID = 2455969538700315609L;

    private PayMethod payMethod;    //付款方式
    private PayHandler payHandler;  // 操作类型

    public PayMethod getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(PayMethod payMethod) {
        this.payMethod = payMethod;
    }

    public PayHandler getPayHandler() {
        return payHandler;
    }

    public void setPayHandler(PayHandler payHandler) {
        this.payHandler = payHandler;
    }
}
