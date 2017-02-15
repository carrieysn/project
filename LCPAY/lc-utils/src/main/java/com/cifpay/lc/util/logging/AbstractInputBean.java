package com.cifpay.lc.util.logging;

import java.io.Serializable;

public abstract class AbstractInputBean implements Serializable {
    private static final long serialVersionUID = 1432775955968252034L;
    private String requestId;
    private LoggerEnum.Scene scene;

    public AbstractInputBean(LoggerEnum.Scene scene) {
        this.scene = scene;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public LoggerEnum.Scene getScene() {
        return scene;
    }

    public void setScene(LoggerEnum.Scene scene) {
        this.scene = scene;
    }
}
