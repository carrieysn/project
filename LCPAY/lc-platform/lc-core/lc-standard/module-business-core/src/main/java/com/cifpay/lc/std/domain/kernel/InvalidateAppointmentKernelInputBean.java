package com.cifpay.lc.std.domain.kernel;

import com.cifpay.lc.constant.enums.LcInvalidateType;

public class InvalidateAppointmentKernelInputBean extends KernelBaseInputBean {

    private static final long serialVersionUID = -1063831474403587194L;

    private LcInvalidateType invalidateType;    // 失效类型(*)
    private long lcAppointmentId;               // 履约ID(*)
    private String remark;                      // 备注

    public LcInvalidateType getInvalidateType() {
        return invalidateType;
    }

    public void setInvalidateType(LcInvalidateType invalidateType) {
        this.invalidateType = invalidateType;
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
}
