package com.cifpay.lc.std.interceptor;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.core.common.CoreBusinessContext;
import com.cifpay.lc.core.component.CoreBusinessInterceptor;
import com.cifpay.lc.core.exception.CoreBusinessException;

@Component
public class BusinessLoggingInterceptor implements CoreBusinessInterceptor {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void beforeProcessBusiness(Object serviceInstance, Serializable inputData, CoreBusinessContext context)
            throws CoreBusinessException {
        if (logger.isDebugEnabled()) {
            Logger logger = LoggerFactory.getLogger(serviceInstance.getClass());
            logger.debug("进入方法: {}.processBusiness", serviceInstance.getClass().getName());
            logger.debug("input参数：{}", inputData);
        }
    }

    @Override
    public void afterProcessBusiness(Object serviceInstance, Serializable inputData,
                                     BusinessOutput<Serializable> businessOutput, CoreBusinessContext context) throws CoreBusinessException {
        if (logger.isDebugEnabled()) {
            logger.debug("方法执行完毕: {}.processBusiness", serviceInstance.getClass().getName());
            logger.debug("output参数：{}", businessOutput);
        }
    }

    @Override
    public void onException(Object serviceInstance, Serializable inputData, BusinessOutput<Serializable> businessOutput,
                            Throwable exception, CoreBusinessContext context) throws CoreBusinessException {
    }

}
