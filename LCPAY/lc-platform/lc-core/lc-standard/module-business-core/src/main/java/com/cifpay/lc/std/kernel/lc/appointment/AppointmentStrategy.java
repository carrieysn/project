package com.cifpay.lc.std.kernel.lc.appointment;

import com.cifpay.lc.constant.ResultHandler;
import com.cifpay.lc.core.db.pojo.LcConfirmPay;
import com.cifpay.lc.core.db.pojo.LcSend;
import com.cifpay.lc.core.exception.CoreBusinessException;
import com.cifpay.lc.domain.lc.AppointmentInputBean;
import com.cifpay.lc.std.domain.kernel.ApplyKernelInputBean;

public interface AppointmentStrategy {

	String getLcType();

	/**
	 * 履约处理逻辑
	 * 
	 * @param lcSend
	 * @param inputBean
	 * @return
	 * @throws CoreBusinessException
	 */
	ResultHandler<LcSend> processAppointment(LcSend lcSend, AppointmentInputBean inputBean) throws CoreBusinessException;

	/**
	 * 申请解付处理逻辑
	 * 
	 * @param lcConfirmPay
	 * @param inputBean
	 * @return
	 * @throws CoreBusinessException
	 */
	ResultHandler<LcConfirmPay> processApply(LcConfirmPay lcConfirmPay, ApplyKernelInputBean inputBean) throws CoreBusinessException;
}
