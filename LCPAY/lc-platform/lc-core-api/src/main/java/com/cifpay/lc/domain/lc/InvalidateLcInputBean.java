package com.cifpay.lc.domain.lc;

import com.cifpay.lc.constant.enums.LcInvalidateType;
import com.cifpay.lc.util.logging.LoggerEnum;

import java.math.BigDecimal;

public class InvalidateLcInputBean extends AbstractLcInputBean {

    private static final long serialVersionUID = -2373493037584692700L;

    private LcInvalidateType invalidateType;    // 失效类型(*)
    private BigDecimal amount;                  // 退款金额(可选)
    private Long lcAppointmentId;               // 履约ID(可选)
    private String remark;                      // 备注

    public InvalidateLcInputBean() {
        super(LoggerEnum.Scene.INVALIDATELC);
    }

    public LcInvalidateType getInvalidateType() {
        return invalidateType;
    }

    public void setInvalidateType(LcInvalidateType invalidateType) {
        this.invalidateType = invalidateType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getLcAppointmentId() {
        return lcAppointmentId;
    }

    public void setLcAppointmentId(Long lcAppointmentId) {
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
        return "InvalidateLcInputBean{" +
                "invalidateType=" + invalidateType +
                ", amount=" + amount +
                ", lcAppointmentId=" + lcAppointmentId +
                ", remark='" + remark + '\'' +
                '}';
    }
}
