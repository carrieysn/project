package com.cifpay.lc.std.domain.component.notify;

import com.cifpay.lc.constant.BizConstants;
import com.cifpay.lc.std.domain.enums.NotifyType;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by sweet on 16-11-2.
 */
public abstract class AbstractNotifyBean implements Serializable {
    private static final long serialVersionUID = -3925476673780000336L;

    private NotifyType notifyType;
    private String notifyTime;
    private String notifyId;

    public AbstractNotifyBean(NotifyType notifyType) {
        this.notifyType = notifyType;
        this.notifyTime = new SimpleDateFormat(BizConstants.DateFormat_std).format(new Date());
        this.notifyId = UUID.randomUUID().toString().replace("-", "");
    }

    public NotifyType getNotifyType() {
        return notifyType;
    }

    public String getNotifyTime() {
        return notifyTime;
    }

    public String getNotifyId() {
        return notifyId;
    }
}
