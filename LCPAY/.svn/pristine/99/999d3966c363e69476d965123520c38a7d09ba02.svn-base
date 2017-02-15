package com.cifpay.lc.std.interceptor;

import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.core.common.CoreBusinessContext;
import com.cifpay.lc.core.common.DbTransaction;
import com.cifpay.lc.core.common.DbTransactionHelper;
import com.cifpay.lc.core.component.CoreBusinessInterceptor;
import com.cifpay.lc.core.exception.CoreBusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class LcTransactionInterceptor implements CoreBusinessInterceptor {

    @Autowired
    private DbTransactionHelper dbTransactionHelper;

    DbTransaction transaction = null;

    // try {
    // } catch (Throwable e) {
    // dbTransactionHelper.rollbackTransaction(transaction);
    // CoreBusinessExceptionHelper.throwAsCoreBusinessException(e);
    // } finally {
    // dbTransactionHelper.commitTransactionInFinallyBlock(transaction);
    // }

    @Override
    public void beforeProcessBusiness(Object serviceInstance, Serializable inputData, CoreBusinessContext context) throws CoreBusinessException {
        transaction = dbTransactionHelper.beginTransaction();
    }

    @Override
    public void afterProcessBusiness(Object serviceInstance, Serializable inputData, BusinessOutput<Serializable> businessOutput, CoreBusinessContext context) throws CoreBusinessException {
        dbTransactionHelper.commitTransactionInFinallyBlock(transaction);
    }

    @Override
    public void onException(Object serviceInstance, Serializable inputData, BusinessOutput<Serializable> businessOutput, Throwable exception, CoreBusinessContext context) throws CoreBusinessException {
        dbTransactionHelper.rollbackTransaction(transaction);
    }
}
