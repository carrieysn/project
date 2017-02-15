package com.cifpay.lc.core.cache.service;

import com.cifpay.lc.core.cache.pojo.LcProductCache;
import com.cifpay.lc.core.db.dao.LcProductDao;
import com.cifpay.lc.core.db.pojo.LcProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class LcProductCacheServant {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private static final String KEY_PREFIX = "LC_PRODUCT_";

    @Autowired
    private LcProductDao lcProductDao;

    @Cacheable(value = KEY_PREFIX, key = "'LcProduct_get' + #productCode", unless = "#result == null")
    public LcProductCache getLcProductCache(String productCode) {
        LcProductCache lcProductCache = null;
        LcProduct lcProduct = lcProductDao.selectByProductCode(productCode);
        if (lcProduct != null) {
            lcProductCache = new LcProductCache();
            BeanUtils.copyProperties(lcProduct, lcProductCache);
        }
        return lcProductCache;
    }

    @CacheEvict(value = KEY_PREFIX, key = "'LcProduct_get' + #productCode")
    public void removeCache(String productCode) {
        if (logger.isDebugEnabled()) {
            logger.debug("删除lcProduct缓存，productCode:{}", productCode);
        }
    }


}
