package com.cifpay.lc.core.db.pojo;

import java.util.Date;

public class GuidWorkerId {
    private String machineId;

    private String appInstanceId;

    private Integer workerId;

    private String machineName;

    private String appBinaryPath;

    private Date initializedDate;

    private Date appLastStartedDate;

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId == null ? null : machineId.trim();
    }

    public String getAppInstanceId() {
        return appInstanceId;
    }

    public void setAppInstanceId(String appInstanceId) {
        this.appInstanceId = appInstanceId == null ? null : appInstanceId.trim();
    }

    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName == null ? null : machineName.trim();
    }

    public String getAppBinaryPath() {
        return appBinaryPath;
    }

    public void setAppBinaryPath(String appBinaryPath) {
        this.appBinaryPath = appBinaryPath == null ? null : appBinaryPath.trim();
    }

    public Date getInitializedDate() {
        return initializedDate;
    }

    public void setInitializedDate(Date initializedDate) {
        this.initializedDate = initializedDate;
    }

    public Date getAppLastStartedDate() {
        return appLastStartedDate;
    }

    public void setAppLastStartedDate(Date appLastStartedDate) {
        this.appLastStartedDate = appLastStartedDate;
    }
}