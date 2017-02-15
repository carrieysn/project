package com.cifpay.lc.domain.lc;

import java.math.BigDecimal;

public class InvalidateLcOutputBean extends AbstractLcOutputBean {

    private static final long serialVersionUID = -8125545791611347885L;

    private long lcInvalidateId;            // 失效流水Id
    private BigDecimal invalidateAmount;    // 失效金额

    public long getLcInvalidateId() {
        return lcInvalidateId;
    }

    public void setLcInvalidateId(long lcInvalidateId) {
        this.lcInvalidateId = lcInvalidateId;
    }

    public BigDecimal getInvalidateAmount() {
        return invalidateAmount;
    }

    public void setInvalidateAmount(BigDecimal invalidateAmount) {
        this.invalidateAmount = invalidateAmount;
    }

    @Override
    public String toString() {
        return "InvalidateLcOutputBean{" +
                "lcInvalidateId=" + lcInvalidateId +
                ", invalidateAmount=" + invalidateAmount +
                '}';
    }
}
