package com.cifpay.lc.domain.lc;

import com.cifpay.lc.util.logging.LoggerEnum;

public class ApplyInputBean extends AbstractLcInputBean {

    private static final long serialVersionUID = 4100379068773292602L;

    private long lcAppointmentId;    // 履约ID(*)

    private String signCode;        // 申请解付凭证
    private String remark;            // 备注

    public ApplyInputBean() {
        super(LoggerEnum.Scene.APPLYLC);
    }

    public long getLcAppointmentId() {
        return lcAppointmentId;
    }

    public void setLcAppointmentId(long lcAppointmentId) {
        this.lcAppointmentId = lcAppointmentId;
    }

    public String getSignCode() {
        return signCode;
    }

    public void setSignCode(String signCode) {
        this.signCode = signCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "ApplyInputBean{" +
                "lcAppointmentId=" + lcAppointmentId +
                ", signCode='" + signCode + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
