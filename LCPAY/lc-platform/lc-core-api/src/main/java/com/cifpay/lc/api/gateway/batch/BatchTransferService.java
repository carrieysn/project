package com.cifpay.lc.api.gateway.batch;

import com.cifpay.lc.api.CoreBusinessService;
import com.cifpay.lc.domain.batch.BatchTransferInputBean;
import com.cifpay.lc.domain.batch.BatchTransferOutputBean;

/**
 * 批量对已经开立的证进行解付操作。
 */
public interface BatchTransferService extends CoreBusinessService<BatchTransferInputBean, BatchTransferOutputBean> {

}
