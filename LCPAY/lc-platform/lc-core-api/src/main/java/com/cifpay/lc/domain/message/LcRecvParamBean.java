package com.cifpay.lc.domain.message;

import com.cifpay.lc.util.logging.LoggerEnum;

/**
 * Created by sweet on 16-10-26.
 */
public class LcRecvParamBean extends MessageParamBean {
    private static final long serialVersionUID = 153702891714286878L;

    private long lcId;
    private String merId;

    public LcRecvParamBean() {
        super(LoggerEnum.Scene.QUEUE_RECV);
    }

    public long getLcId() {
        return lcId;
    }

    public void setLcId(long lcId) {
        this.lcId = lcId;
    }

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

    @Override
    public String toString() {
        return "LcRecvParamBean{" +
                "lcId=" + lcId +
                ", merId='" + merId + '\'' +
                '}';
    }
}
