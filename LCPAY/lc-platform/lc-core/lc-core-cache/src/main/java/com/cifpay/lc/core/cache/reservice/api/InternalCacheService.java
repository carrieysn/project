package com.cifpay.lc.core.cache.reservice.api;

import com.cifpay.lc.domain.cache.CacheInput;

public interface InternalCacheService {

    /**
     * 清空缓存数据
     *
     * @param paramBean
     */
    public void removeCache(CacheInput paramBean);

    String getCacheType();

}
