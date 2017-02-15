package com.cifpay.lc.api;

import com.cifpay.lc.util.logging.LoggerEnum;
import com.cifpay.lc.util.logging.AbstractInputBean;

/**
 *
 *
 */
public final class VoidObject extends AbstractInputBean {
    private static final long serialVersionUID = -1409255385019621988L;

    public VoidObject() {
        super(LoggerEnum.Scene.VOID);
    }
}
