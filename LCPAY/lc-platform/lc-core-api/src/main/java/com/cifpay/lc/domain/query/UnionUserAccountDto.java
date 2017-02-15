package com.cifpay.lc.domain.query;

import java.io.Serializable;
import java.util.Date;

public class UnionUserAccountDto implements Serializable {
    private static final long serialVersionUID = 5118611699148141977L;

    private String merUserid;

    private String mid;

    private String userMobile;

    private String payerAccno;

    private Integer accnoType;

    private Date createDate;

    public String getMerUserid() {
        return merUserid;
    }

    public void setMerUserid(String merUserid) {
        this.merUserid = merUserid == null ? null : merUserid.trim();
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid == null ? null : mid.trim();
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile == null ? null : userMobile.trim();
    }

    public String getPayerAccno() {
        return payerAccno;
    }

    public void setPayerAccno(String payerAccno) {
        this.payerAccno = payerAccno == null ? null : payerAccno.trim();
    }

    public Integer getAccnoType() {
        return accnoType;
    }

    public void setAccnoType(Integer accnoType) {
        this.accnoType = accnoType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}