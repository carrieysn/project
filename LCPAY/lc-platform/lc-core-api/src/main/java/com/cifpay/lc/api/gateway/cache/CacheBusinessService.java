package com.cifpay.lc.api.gateway.cache;

import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.api.BusinessService;
import com.cifpay.lc.domain.cache.CacheInput;
import com.cifpay.lc.domain.cache.CacheOutput;

public interface CacheBusinessService extends BusinessService {

    public BusinessOutput<CacheOutput> removeCache(CacheInput paramValue);
}

