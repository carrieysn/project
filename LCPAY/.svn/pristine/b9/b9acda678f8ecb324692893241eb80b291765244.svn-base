package com.cifpay.lc.core.cache.service;

import com.cifpay.lc.core.cache.pojo.LcBankCache;
import com.cifpay.lc.core.db.dao.AdminCifpayLcBankDao;
import com.cifpay.lc.core.db.pojo.AdminCifpayLcBank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class LcBankCacheServant {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private static final String KEY_PREFIX = "ADMIN_CIFPAY_LC_BANK_";

    @Autowired
    private AdminCifpayLcBankDao adminCifpayLcBankDao;

    @Cacheable(value = KEY_PREFIX, key = "'selectByPrimaryKey' + #bankCode")
    public LcBankCache selectByPrimaryKey(String bankCode) {
        AdminCifpayLcBank payBank = adminCifpayLcBankDao.selectByBankCode(bankCode);
        if (payBank != null) {
            LcBankCache lcBankCache = new LcBankCache();
            BeanUtils.copyProperties(payBank, lcBankCache);

            return lcBankCache;
        }
        return null;
    }

    @CacheEvict(value = KEY_PREFIX, key = "'selectByPrimaryKey' + #bankCode")
    public void removeCache(String bankCode) {
        if (logger.isDebugEnabled()) {
            logger.debug("删除lcBank缓存，bankCode:{}", bankCode);
        }
    }


}
