package com.cifpay.lc.core.cache.service;

import com.cifpay.lc.core.cache.pojo.SmsCache;
import com.cifpay.lc.domain.message.SmsParamBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class SmsCacheServant extends BaseCacheServant<SmsCache> {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private static final String KEY_PREFIX = "SMS_";

    private static final long expires = 180L;

    public SmsCache getSmsCache(String key) {
        return super.getCache(key, SmsCache.class);
    }

    public boolean setSmsCache(String key, SmsParamBean smsParam) {
        SmsCache smsCache = new SmsCache();
        BeanUtils.copyProperties(smsParam, smsCache);
        return super.setCache(key, smsCache, expires);
    }

    public boolean removeSmsCache(String key) {
        return super.removeCache(key);
    }

    @Override
    public String getKey(String key) {
        return KEY_PREFIX + key;
    }


}
