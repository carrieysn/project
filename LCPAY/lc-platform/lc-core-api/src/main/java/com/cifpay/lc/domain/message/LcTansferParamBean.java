package com.cifpay.lc.domain.message;

import com.cifpay.lc.util.logging.LoggerEnum;

/**
 * Created by sweet on 16-10-26.
 */
public class LcTansferParamBean extends MessageParamBean {
    private static final long serialVersionUID = -498928794524748618L;

    private long lcId;
    private String merId;

    private long applyId;   // 申请解付ID(*)
    private String remark;  // 备注

    public LcTansferParamBean() {
        super(LoggerEnum.Scene.QUEUE_TRANSFER);
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

    public long getApplyId() {
        return applyId;
    }

    public void setApplyId(long applyId) {
        this.applyId = applyId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "LcTansferParamBean{" +
                "lcId=" + lcId +
                ", merId='" + merId + '\'' +
                ", applyId=" + applyId +
                ", remark='" + remark + '\'' +
                '}';
    }
}
