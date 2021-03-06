package com.cifpay.lc.core.interceptor.annotation;

import com.cifpay.lc.util.logging.LoggerEnum;

import java.lang.annotation.*;

/**
 * 自动生成 requestId
 * 如果方法签名中含有AbstractInputBean参数，将从参数中读取requestId
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,})
@Documented
@Inherited
public @interface EnableGenerateRequestId {

    /**
     * 使用场景
     */
    LoggerEnum.Scene scene() default LoggerEnum.Scene.UNKNOWN;
}
