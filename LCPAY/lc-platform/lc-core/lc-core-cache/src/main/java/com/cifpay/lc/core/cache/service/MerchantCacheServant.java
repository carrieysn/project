package com.cifpay.lc.core.cache.service;

import com.cifpay.lc.core.cache.pojo.MerchantCache;
import com.cifpay.lc.core.db.dao.AdminLcMerchantDao;
import com.cifpay.lc.core.db.pojo.AdminLcMerchant;
import com.cifpay.lc.util.security.AESUtils;
import com.cifpay.lc.util.security.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class MerchantCacheServant {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private static final String KEY_PREFIX = "LC_MERCHANT_";

    @Autowired
    private AdminLcMerchantDao adminLcMerchantDao;

    @Cacheable(value = KEY_PREFIX, key = "'Merchant_get' + #merCode")
    public MerchantCache getMerchantCache(String merCode) {
        AdminLcMerchant adminLcMerchant = adminLcMerchantDao.selectAdminLcMerchant(merCode);
        if (adminLcMerchant == null) {
            throw new RuntimeException("无合法商户信息");
        }
        MerchantCache merchantCache = new MerchantCache();
        merchantCache.setMerCode(merCode);
        merchantCache.setMerchantName(adminLcMerchant.getMerchantName());
        merchantCache.setMerchantStatus(adminLcMerchant.getMerchantStatus());
        merchantCache.setEncryptType(adminLcMerchant.getEncryptType());
        merchantCache.setMerSiteDomain(adminLcMerchant.getMerSiteDomain());
        merchantCache.setBankAccount(adminLcMerchant.getBankAccount());
        merchantCache.setBankCode(adminLcMerchant.getBankCode());

        if (!StringUtils.isEmpty(adminLcMerchant.getEncryptKey())) {
            merchantCache.setEncryptKey(unpacKey(adminLcMerchant.getEncryptKey()));
        }
        if (!StringUtils.isEmpty(adminLcMerchant.getDecryptKey())) {
            merchantCache.setDecryptKey(unpacKey(adminLcMerchant.getDecryptKey()));
        }
        return merchantCache;
    }

    @CacheEvict(value = KEY_PREFIX, key = "'Merchant_get' + #merCode")
    public void removeCache(String merCode) {
        if (logger.isDebugEnabled()) {
            logger.debug("删除Merchant缓存，merCode:{}", merCode);
        }
    }

    private String unpacKey(String secKey) throws IllegalArgumentException {
        try {
            return AESUtils.decryptStringFromBase64(secKey, Constants.AES_PWD_FOR_PROTECT_KEY);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("提取商户密钥异常");
        }
    }
}
