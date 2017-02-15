package com.cifpay.lc.core.cache.service;

import com.cifpay.lc.core.cache.pojo.LcTypeCache;
import com.cifpay.lc.core.db.dao.LcTypeDao;
import com.cifpay.lc.core.db.pojo.LcType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class LcTypeCacheServant {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private static final String KEY_PREFIX = "LC_TYPE_";

    @Autowired
    LcTypeDao lcTypeDao;

    @Cacheable(value = KEY_PREFIX, key = "'LcType_get' + #lcTypeId")
    public LcTypeCache getLcTypeCache(String lcTypeId) {
        LcType lcType = lcTypeDao.selectByPrimaryKey(lcTypeId);
        LcTypeCache lcTypeCache = new LcTypeCache();
        if (lcType != null) {
            BeanUtils.copyProperties(lcType, lcTypeCache);
        }
        return lcTypeCache;
    }

    @CacheEvict(value = KEY_PREFIX, key = "'LcType_get' + #lcTypeId")
    public void removeCache(String lcTypeId) {
        if (logger.isDebugEnabled()) {
            logger.debug("删除lcType缓存，lcTypeId:{}", lcTypeId);
        }
    }

}
