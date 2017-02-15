package com.cifpay.lc.core.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cifpay.lc.core.component.CoreBusinessInterceptor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.core.exception.CoreBusinessException;

public class CoreBusinessInterceptorHandler {

    private final Map<Class<? extends CoreBusinessInterceptor>, CoreBusinessInterceptor> interceptorsMapping = new HashMap<Class<? extends CoreBusinessInterceptor>, CoreBusinessInterceptor>();
    private final Map<Class<?>, CoreBusinessInterceptor[]> configMapping = new HashMap<Class<?>, CoreBusinessInterceptor[]>();

    @Autowired(required = false)
    public void setupInterceptors(List<CoreBusinessInterceptor> interceptors) {
        for (CoreBusinessInterceptor interceptor : interceptors) {
            interceptorsMapping.put(interceptor.getClass(), interceptor);
        }
    }

    @SuppressWarnings("rawtypes")
    public void applyPreProcessBusiness(CoreBusinessServiceImplBase serviceInstance, Serializable inputData,
                                        CoreBusinessContext context) throws CoreBusinessException {
        CoreBusinessInterceptor[] iterceptors = findCoreBusinessInterceptors(serviceInstance.getClass());
        if (iterceptors.length > 0) {
            for (CoreBusinessInterceptor iterceptor : iterceptors) {
                iterceptor.beforeProcessBusiness(serviceInstance, inputData, context);
            }
        }
    }

    @SuppressWarnings("rawtypes")
    public void applyPostProcessBusiness(CoreBusinessServiceImplBase serviceInstance, Serializable inputData, BusinessOutput<Serializable> output, CoreBusinessContext context) throws CoreBusinessException {
        CoreBusinessInterceptor[] iterceptors = findCoreBusinessInterceptors(serviceInstance.getClass());
        if (iterceptors.length > 0) {
            for (CoreBusinessInterceptor iterceptor : iterceptors) {
                iterceptor.afterProcessBusiness(serviceInstance, inputData, output, context);
            }
        }
    }

    @SuppressWarnings("rawtypes")
    public void applyOnProcessBusinessException(CoreBusinessServiceImplBase serviceInstance, Serializable inputData, BusinessOutput<Serializable> businessOutput, Throwable exception, CoreBusinessContext context) throws CoreBusinessException {
        CoreBusinessInterceptor[] iterceptors = findCoreBusinessInterceptors(serviceInstance.getClass());
        if (iterceptors.length > 0) {
            for (CoreBusinessInterceptor iterceptor : iterceptors) {
                iterceptor.onException(serviceInstance, inputData, businessOutput, exception, context);
            }
        }
    }

    private CoreBusinessInterceptor[] findCoreBusinessInterceptors(Class<?> serviceClass) {
        CoreBusinessInterceptor[] iterceptors = configMapping.get(serviceClass);
        if (null == iterceptors) {
            synchronized (configMapping) {
                iterceptors = configMapping.get(serviceClass);
                if (null == iterceptors) {
                    List<CoreBusinessInterceptor> list = new ArrayList<CoreBusinessInterceptor>();
                    CoreBusinessInterceptorConfig ic = serviceClass.getAnnotation(CoreBusinessInterceptorConfig.class);
                    if (null != ic) {
                        for (Class<? extends CoreBusinessInterceptor> iterceptorClz : ic.value()) {
                            CoreBusinessInterceptor iterceptor = interceptorsMapping.get(iterceptorClz);
                            if (null != iterceptor) {
                                list.add(iterceptor);
                            } else {
                                LoggerFactory.getLogger(serviceClass).warn("!!!! 未能在Spring容器中查找到{}的实例",
                                        iterceptorClz.getName());
                            }
                        }
                    }
                    iterceptors = list.toArray(new CoreBusinessInterceptor[list.size()]);
                    configMapping.put(serviceClass, iterceptors);
                }
            }
        }
        return iterceptors;
    }

}
