package com.cifpay.lc.std.domain.paychannel;

import com.cifpay.lc.core.db.pojo.Lc;
import com.cifpay.lc.core.db.pojo.LcInvalid;
import com.cifpay.lc.core.db.pojo.LcOpen;

/**
 * Created by sweet on 16-11-15.
 */
public class ExpiryInputBean {
    private Lc lc;
    private LcOpen lcOpen;
    private LcInvalid lcInvalid;

    public Lc getLc() {
        return lc;
    }

    public void setLc(Lc lc) {
        this.lc = lc;
    }

    public LcOpen getLcOpen() {
        return lcOpen;
    }

    public void setLcOpen(LcOpen lcOpen) {
        this.lcOpen = lcOpen;
    }

    public LcInvalid getLcInvalid() {
        return lcInvalid;
    }

    public void setLcInvalid(LcInvalid lcInvalid) {
        this.lcInvalid = lcInvalid;
    }
}
