package com.cifpay.lc.gateway.integration.advice;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标识该Gateway接口通过普通表单或"name1=value1&name2=value"这种形式接收商户的请求数据。
 * 
 * 
 *
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MerchantFormRequest {
}
