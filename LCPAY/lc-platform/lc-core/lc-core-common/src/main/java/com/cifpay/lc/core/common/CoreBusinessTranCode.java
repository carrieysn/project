package com.cifpay.lc.core.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.cifpay.lc.constant.BizConstants.LcTranCode;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE)
public @interface CoreBusinessTranCode {

	LcTranCode value();
}
