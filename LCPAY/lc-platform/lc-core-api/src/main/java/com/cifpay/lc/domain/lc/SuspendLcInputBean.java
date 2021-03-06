package com.cifpay.lc.domain.lc;

import com.cifpay.lc.util.logging.LoggerEnum;

public class SuspendLcInputBean extends AbstractLcInputBean {

    private static final long serialVersionUID = 4100379068773292602L;

    private long lcConfirmId;    // 申请解付ID
    private String remark;        // 备注

    public SuspendLcInputBean() {
        super(LoggerEnum.Scene.SUSPENDLC);
    }

    public long getLcConfirmId() {
        return lcConfirmId;
    }

    public void setLcConfirmId(long lcConfirmId) {
        this.lcConfirmId = lcConfirmId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SuspendLcInputBean{" +
                "lcConfirmId=" + lcConfirmId +
                ", remark='" + remark + '\'' +
                '}';
    }
}
