package com.cifpay.lc.std.interceptor;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.core.autoconfigure.lock.DisLock;
import com.cifpay.lc.core.autoconfigure.lock.DistributeLockManager;
import com.cifpay.lc.core.common.CoreBusinessContext;
import com.cifpay.lc.core.component.CoreBusinessInterceptor;
import com.cifpay.lc.core.exception.CoreBusinessException;
import com.cifpay.lc.domain.lc.AbstractLcInputBean;
import com.cifpay.lc.exception.DistributeLockException;


@Component
public class BusinessLockInterceptor implements CoreBusinessInterceptor {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String LOCK_BY_LC_ID_PREFIX = "/LOCK/LCID";

    @Autowired
    private DistributeLockManager distributeLockManager;

    @Override
    public void beforeProcessBusiness(Object serviceInstance, Serializable inputData, CoreBusinessContext context) throws CoreBusinessException {
        AbstractLcInputBean lcInputData = (AbstractLcInputBean) inputData;
        DisLock lock = null;
        try {
            String lockPath = getLockPath(String.valueOf(lcInputData.getLcId()));
            lock = distributeLockManager.getDisLock(lockPath);
            if(lock.acquire(6,TimeUnit.SECONDS)){
            	context.setAttribute("disLock", lock);
            }else{
            	logger.error("=================== 无法获取到分布式锁  ==============");
            	throw new DistributeLockException(ReturnCode.CORE_COMMON_BEGIN_DB_TRANSACTION_FAILED,"无法获取分布式锁");
            }
        } catch (Exception e) {
            distributeLockManager.unlock(lock);
            throw new CoreBusinessException(ReturnCode.CORE_COMMON_UNSUPPORT_BUSINESS_LOCK, "该业务类不支持业务加锁，processBusiness()的参数类型必须是BaseLcInputBean");
        }
    }

    @Override
    public void afterProcessBusiness(Object serviceInstance, Serializable inputData, BusinessOutput<Serializable> businessOutput, CoreBusinessContext context) throws CoreBusinessException {
        DisLock lock = (DisLock)context.getAttribute("disLock");
        distributeLockManager.unlock(lock);
        context.setAttribute("disLock", null);
    }

    @Override
    public void onException(Object serviceInstance, Serializable inputData, BusinessOutput<Serializable> businessOutput, Throwable exception, CoreBusinessContext context) throws CoreBusinessException {
    	DisLock lock = (DisLock)context.getAttribute("disLock");
        distributeLockManager.unlock(lock);
        context.setAttribute("disLock", null);
    }


    public String getLockPath(String key) {
        return LOCK_BY_LC_ID_PREFIX + key;
    }

}
