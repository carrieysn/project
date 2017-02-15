package com.cifpay.lc.std.component;

import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.std.domain.component.notify.AbstractNotifyBean;

/**
 * 商户通知服务
 */
public interface NotifyService {

    /**
     * @param merId      商户号
     * @param url
     * @param notifyBean
     * @return
     */
    BusinessOutput notify(String merId, String url, AbstractNotifyBean notifyBean);
}
