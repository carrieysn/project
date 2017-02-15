package com.cifpay.lc.api.gateway.batch;

import com.cifpay.lc.api.CoreBusinessService;
import com.cifpay.lc.domain.batch.BatchOpenLcInputBean;
import com.cifpay.lc.domain.batch.BatchOpenLcOutputBean;

/**
 * 批量开证接口。只负责开证，不用进行开证后其他环节的处理。
 */
public interface BatchOpenLcService extends CoreBusinessService<BatchOpenLcInputBean, BatchOpenLcOutputBean> {

}
