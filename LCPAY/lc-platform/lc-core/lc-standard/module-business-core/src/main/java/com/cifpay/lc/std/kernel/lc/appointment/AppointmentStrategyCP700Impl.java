package com.cifpay.lc.std.kernel.lc.appointment;

import com.cifpay.lc.constant.ResultHandler;
import com.cifpay.lc.core.db.pojo.LcConfirmPay;
import com.cifpay.lc.core.db.pojo.LcSend;
import com.cifpay.lc.core.exception.CoreBusinessException;
import com.cifpay.lc.domain.lc.AppointmentInputBean;
import com.cifpay.lc.std.domain.kernel.ApplyKernelInputBean;
import org.springframework.stereotype.Component;

@Component
public class AppointmentStrategyCP700Impl implements AppointmentStrategy {

    @Override
    public String getLcType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResultHandler<LcSend> processAppointment(LcSend lcSend, AppointmentInputBean inputBean) throws CoreBusinessException {
        return null;
    }

    @Override
    public ResultHandler<LcConfirmPay> processApply(LcConfirmPay lcConfirmPay, ApplyKernelInputBean inputBean) throws CoreBusinessException {

        return null;
    }
}
