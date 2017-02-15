package com.cifpay.lc.core.component;

import java.io.Serializable;

import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.core.common.CoreBusinessContext;
import com.cifpay.lc.core.exception.CoreBusinessException;

public interface CoreBusinessInterceptor {

    void beforeProcessBusiness(Object serviceInstance, Serializable inputData, CoreBusinessContext context) throws CoreBusinessException;

    void afterProcessBusiness(Object serviceInstance, Serializable inputData, BusinessOutput<Serializable> businessOutput, CoreBusinessContext context) throws CoreBusinessException;

    void onException(Object serviceInstance, Serializable inputData, BusinessOutput<Serializable> businessOutput, Throwable exception, CoreBusinessContext context) throws CoreBusinessException;

}
