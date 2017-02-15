package com.cifpay.lc.core.cache.reservice;

import com.cifpay.lc.core.cache.reservice.api.InternalCacheService;
import com.cifpay.lc.core.cache.service.LcBankCacheServant;
import com.cifpay.lc.domain.cache.BankCacheInputBean;
import com.cifpay.lc.domain.cache.CacheInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.cifpay.lc.constant.enums.CacheEnum;

@Component
public class LcBankCacheComponent implements InternalCacheService {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    LcBankCacheServant lcBankCacheServant;

    @Override
    public void removeCache(CacheInput paramBean) {
        if (paramBean instanceof BankCacheInputBean) {
            BankCacheInputBean bankCacheInputBean = (BankCacheInputBean) paramBean;

            lcBankCacheServant.removeCache(bankCacheInputBean.getBankCode());
        }
    }

    @Override
    public String getCacheType() {
        return CacheEnum.CACHE_TYPE_BANK;
    }
}
