package com.cifpay.lc.domain.message;

import com.cifpay.lc.util.logging.LoggerEnum;

/**
 * Created by sweet on 16-10-26.
 */
public class LcApplyParamBean extends MessageParamBean {
    private static final long serialVersionUID = 6581271144237146518L;

    private long lcId;
    private String merId;

    private long lcAppointmentId;   // 履约ID
    private String remark;          // 备注

    public LcApplyParamBean() {
        super(LoggerEnum.Scene.QUEUE_APPLY);
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

    public long getLcAppointmentId() {
        return lcAppointmentId;
    }

    public void setLcAppointmentId(long lcAppointmentId) {
        this.lcAppointmentId = lcAppointmentId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "LcApplyParamBean{" +
                "lcId=" + lcId +
                ", merId='" + merId + '\'' +
                ", lcAppointmentId=" + lcAppointmentId +
                ", remark='" + remark + '\'' +
                '}';
    }
}
