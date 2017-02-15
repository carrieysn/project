package com.cifpay.lc.domain.lc;

import com.cifpay.lc.util.logging.LoggerEnum;

public class TransferInputBean extends AbstractLcInputBean {

    private static final long serialVersionUID = 4100379068773292602L;

    private long applyId;       // 申请解付ID(*)
    private String remark;      // 备注

    public TransferInputBean() {
        super(LoggerEnum.Scene.TRANSFERLC);
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
        return "TransferInputBean{" +
                "applyId=" + applyId +
                ", remark='" + remark + '\'' +
                '}';
    }
}
