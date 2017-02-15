package com.cifpay.lc.domain.security;

import java.io.Serializable;

/**
 * Gateway接口统一响应给商户的data格式。原则上所有Gateway接口的返回对象类型必须继承自本类型（
 * 除了开证等部分接口URL或HTML自动跳转的情形除外）。
 */
public abstract class AbstractMerchantResponse implements Serializable {
    private static final long serialVersionUID = -4368791878437856682L;
}
