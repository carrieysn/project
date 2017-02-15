package com.cifpay.lc.std.kernel.lc.appointment;

import com.cifpay.lc.constant.ResultHandler;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.constant.enums.LcTranStatus;
import com.cifpay.lc.core.db.dao.LcSendDao;
import com.cifpay.lc.core.db.pojo.LcConfirmPay;
import com.cifpay.lc.core.db.pojo.LcSend;
import com.cifpay.lc.core.exception.CoreBusinessException;
import com.cifpay.lc.domain.lc.AppointmentInputBean;
import com.cifpay.lc.std.domain.kernel.ApplyKernelInputBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentStrategyCP500Impl implements AppointmentStrategy {

    private static final String LC_TYPE = "CP500";
    @Autowired
    private LcSendDao lcSendDao; // 履约DAO

    @Override
    public String getLcType() {
        // TODO Auto-generated method stub
        return LC_TYPE;
    }

    @Override
    public ResultHandler<LcSend> processAppointment(LcSend lcSend, AppointmentInputBean inputBean) throws CoreBusinessException {
        lcSend.setOrderId(inputBean.getOrderId());
        lcSend.setOrderAmount(inputBean.getOrderAmount());
        lcSend.setOrderContent(inputBean.getOrderContent());
        lcSend.setLcConfirmAmount(inputBean.getOrderAmount());
        lcSend.setRemark(inputBean.getRemark());

        // 生成验证码
        String timestamp = Long.toString(System.currentTimeMillis());
        String sendSignCode = timestamp.substring(timestamp.length() - 4);
        lcSend.setSendSignCode(sendSignCode);
        lcSend.setSendStatus(LcTranStatus.SUCCESS.getTranStatusStr());

        // TODO: 发送验证码

        return ResultHandler.success(lcSend);
    }

    @Override
    public ResultHandler<LcConfirmPay> processApply(LcConfirmPay lcConfirmPay, ApplyKernelInputBean inputBean) throws CoreBusinessException {
        // 检查验证码
       /* LcSend lcSend = lcSendDao.selectBylcSendIds(inputBean.getLcAppointmentId()).stream().findFirst().get();

        if (null == lcSend.getSendSignCode()) {
            throw new CoreBusinessException(ReturnCode.CORE_STD_APPOINTMENT_CODE_INVALID, "生成的验证码有误，请联系管理员");
        }
        if (null == inputBean.getSignCode()) {
            throw new CoreBusinessException(ReturnCode.CORE_STD_APPOINTMENT_CODE_INVALID, "请输入验证码");
        }
        if (lcSend.getSendSignCode().compareTo(inputBean.getSignCode()) != 0) {
            throw new CoreBusinessException(ReturnCode.CORE_STD_APPOINTMENT_CODE_INVALID, "验证码不正确");
        }*/

        // 设置为“处理成功”
        lcConfirmPay.setConfirmStatus(LcTranStatus.SUCCESS.getTranStatusStr());
        lcConfirmPay.setRemark(inputBean.getRemark());

        return ResultHandler.success(lcConfirmPay);
    }
}
