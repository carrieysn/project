package com.cifpay.lc.domain.batch;

import com.cifpay.lc.util.logging.LoggerEnum;

import java.util.List;

public class BatchApplyInputBean extends AbstractBatchInputBean {

    private static final long serialVersionUID = 8181746387429178725L;

    private List<BatchApplyInputDto> applyList;

    public BatchApplyInputBean() {
        super(LoggerEnum.Scene.BATCHAPPLYLC);
    }

    public List<BatchApplyInputDto> getApplyList() {
        return applyList;
    }

    public void setApplyList(List<BatchApplyInputDto> applyList) {
        this.applyList = applyList;
    }
}
