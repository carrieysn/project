package com.cifpay.lc.std.kernel.lc.appointment;

import com.cifpay.lc.constant.ResultHandler;
import com.cifpay.lc.constant.enums.LcTranStatus;
import com.cifpay.lc.core.db.pojo.LcConfirmPay;
import com.cifpay.lc.core.db.pojo.LcSend;
import com.cifpay.lc.core.exception.CoreBusinessException;
import com.cifpay.lc.domain.lc.AppointmentInputBean;
import com.cifpay.lc.std.domain.kernel.ApplyKernelInputBean;
import org.springframework.stereotype.Component;

@Component
public class AppointmentStrategyCP300Impl implements AppointmentStrategy {

	private static final String LC_TYPE = "CP300";

	@Override
	public String getLcType() {
		return LC_TYPE;
	}

	@Override
	public ResultHandler<LcSend> processAppointment(LcSend lcSend, AppointmentInputBean inputBean) throws CoreBusinessException {
		lcSend.setOrderId(inputBean.getOrderId());
		lcSend.setOrderAmount(inputBean.getOrderAmount());
		lcSend.setOrderContent(inputBean.getOrderContent());
		lcSend.setLcConfirmAmount(inputBean.getOrderAmount());
		lcSend.setRemark(inputBean.getRemark());

		lcSend.setSendStatus(LcTranStatus.SUCCESS.getTranStatusStr());
		return ResultHandler.success(lcSend);
	}

	@Override
	public ResultHandler<LcConfirmPay> processApply(LcConfirmPay lcConfirmPay, ApplyKernelInputBean inputBean) throws CoreBusinessException {
		// 设置为“处理成功”
		lcConfirmPay.setConfirmStatus(LcTranStatus.SUCCESS.getTranStatusStr());
		lcConfirmPay.setRemark(inputBean.getRemark());

		return ResultHandler.success(lcConfirmPay);
	}
}
