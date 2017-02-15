package com.cifpay.lc.domain.message;

import com.cifpay.lc.util.logging.LoggerEnum;

import java.math.BigDecimal;

/**
 * Created by sweet on 16-10-26.
 */
public class LcAppointmentParamBean extends MessageParamBean {
    private static final long serialVersionUID = 849683794782124359L;

    private long lcId;
    private String merId;

    private String orderId;            // 商户订单号
    private BigDecimal orderAmount;    // 订单金额(*)

    public LcAppointmentParamBean() {
        super(LoggerEnum.Scene.QUEUE_APPOINTMENT);
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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    @Override
    public String toString() {
        return "LcAppointmentParamBean{" +
                "lcId=" + lcId +
                ", merId='" + merId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", orderAmount=" + orderAmount +
                '}';
    }
}
