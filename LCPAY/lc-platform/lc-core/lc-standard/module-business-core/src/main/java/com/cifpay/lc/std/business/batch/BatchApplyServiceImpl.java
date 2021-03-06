package com.cifpay.lc.std.business.batch;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cifpay.lc.api.BusinessInput;
import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.api.gateway.batch.BatchApplyService;
import com.cifpay.lc.api.gateway.lc.ApplyService;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.core.common.CoreBusinessContext;
import com.cifpay.lc.core.common.CoreBusinessExceptionHelper;
import com.cifpay.lc.core.common.DbTransaction;
import com.cifpay.lc.core.common.DbTransactionHelper;
import com.cifpay.lc.core.exception.CoreBusinessException;
import com.cifpay.lc.core.exception.CoreValidationRejectException;
import com.cifpay.lc.domain.batch.BatchApplyInputBean;
import com.cifpay.lc.domain.batch.BatchApplyInputDto;
import com.cifpay.lc.domain.batch.BatchApplyOutputBean;
import com.cifpay.lc.domain.batch.BatchApplyOutputDto;
import com.cifpay.lc.domain.lc.ApplyInputBean;
import com.cifpay.lc.domain.lc.ApplyOutputBean;
import com.cifpay.lc.std.business.lc.AbstractLcServiceImplBase;

@Service("batchApplyService")
public class BatchApplyServiceImpl extends AbstractLcServiceImplBase<BatchApplyInputBean, BatchApplyOutputBean> implements BatchApplyService {

	@Autowired
	private ApplyService applyService;

	@Autowired
	private DbTransactionHelper dbTransactionHelper;

	@Override
	protected void validateInputParameters(BatchApplyInputBean inputBean) throws CoreValidationRejectException {

	}

	@Override
	protected void validateLcProductRule(BatchApplyInputBean inputBean, CoreBusinessContext context) throws CoreValidationRejectException {

	}

	@Override
	protected BusinessOutput<BatchApplyOutputBean> processLcBusiness(BatchApplyInputBean inputBean, CoreBusinessContext context) throws CoreBusinessException {

		logger.info("开始批量申请收款");
		BatchApplyInputBean batchApplyInputBean = inputBean;

		BatchApplyOutputBean result = new BatchApplyOutputBean();
		List<BatchApplyOutputDto> applyOutputList = new ArrayList<BatchApplyOutputDto>();
		result.setApplyOutputList(applyOutputList);

		DbTransaction dbTransaction = dbTransactionHelper.beginTransaction();
		try {
			for (BatchApplyInputDto batchApplyInputDto : batchApplyInputBean.getApplyList()) {

				ApplyInputBean applyInputBean = new ApplyInputBean();
				applyInputBean.setMerId(batchApplyInputDto.getMerId());
				applyInputBean.setLcId(batchApplyInputDto.getLcId());

				applyInputBean.setLcAppointmentId(batchApplyInputDto.getLcAppointmentId());
				applyInputBean.setSignCode(batchApplyInputDto.getSignCode());
				applyInputBean.setRemark(batchApplyInputDto.getRemark());

				BusinessOutput<ApplyOutputBean> businessOutput = applyService.execute(new BusinessInput<ApplyInputBean>(applyInputBean));

				if (businessOutput.isSuccess()) {
					ApplyOutputBean applyOutputBean = businessOutput.getData();

					BatchApplyOutputDto batchApplyOutputDto = new BatchApplyOutputDto();
					applyOutputList.add(batchApplyOutputDto);

					batchApplyOutputDto.setLcId(applyOutputBean.getLcId());
					batchApplyOutputDto.setLcStatus(applyOutputBean.getLcStatus());
					batchApplyOutputDto.setLcStatusDesc(applyOutputBean.getLcStatusDesc());

					batchApplyOutputDto.setLcAppointmentId(applyOutputBean.getLcAppointmentId());
					batchApplyOutputDto.setLcConfirmId(applyOutputBean.getLcConfirmId());
					batchApplyOutputDto.setLcPayAmount(applyOutputBean.getLcPayAmount());

				} else {
					throw new CoreBusinessException(businessOutput.getReturnCode(), businessOutput.getReturnMsg());
				}
			}

			dbTransactionHelper.commitTransaction(dbTransaction);
			return BusinessOutput.success(result);
		} catch (Exception e) {
			dbTransactionHelper.rollbackTransaction(dbTransaction);
			CoreBusinessExceptionHelper.throwAsCoreBusinessException(e);
		}
		throw new CoreBusinessException(ReturnCode.CORE_STD_LC_BATCH_APPLY_ERROR, "批量申请收款失败");
	}

}
