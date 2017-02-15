package com.cifpay.lc.std.kernel.lc;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.constant.enums.LcTranStatus;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.constant.enums.LcPayType;
import com.cifpay.lc.constant.enums.LcStatusType;
import com.cifpay.lc.constant.enums.ProcessStatus;
import com.cifpay.lc.core.common.CoreBusinessContext;
import com.cifpay.lc.core.common.CoreBusinessExceptionHelper;
import com.cifpay.lc.core.common.DbTransaction;
import com.cifpay.lc.core.common.DbTransactionHelper;
import com.cifpay.lc.core.db.dao.LcDao;
import com.cifpay.lc.core.db.dao.LcInvalidDao;
import com.cifpay.lc.core.db.dao.LcSendDao;
import com.cifpay.lc.core.db.pojo.Lc;
import com.cifpay.lc.core.db.pojo.LcInvalid;
import com.cifpay.lc.core.db.pojo.LcSend;
import com.cifpay.lc.core.exception.CoreBusinessException;
import com.cifpay.lc.core.exception.CoreValidationRejectException;
import com.cifpay.lc.core.uid.LcInvalidIdWorker;
import com.cifpay.lc.std.kernel.LcBaseKernel;
import com.cifpay.lc.std.domain.kernel.InvalidateAppointmentKernelInputBean;
import com.cifpay.lc.std.domain.kernel.InvalidateAppointmentKernelOutputBean;

/**
 * 多次履约，将履约失效，金额返回银信证余额
 *
 * @author sweet
 */
@Component
public class InvalidateAppointmentKernel extends LcBaseKernel<InvalidateAppointmentKernelInputBean, InvalidateAppointmentKernelOutputBean> {

    @Autowired
    private LcDao lcDao; // 银信证DAO

    @Autowired
    private LcSendDao lcSendDao; // 履约DAO

    @Autowired
    private LcInvalidDao lcInvalidDao;

    @Autowired
    private LcInvalidIdWorker lcInvalidIdWorker;

    @Autowired
    private DbTransactionHelper dbTransactionHelper;

    @Override
    protected void validateLcStatus(InvalidateAppointmentKernelInputBean input, CoreBusinessContext context) throws CoreValidationRejectException {
        // TODO Auto-generated method stub

        // 获取银信证记录
        Lc lc = lcDao.selectByPrimaryKey(input.getLcId());
        if (lc == null) {
            throw new CoreValidationRejectException(ReturnCode.CORE_STD_LC_NOT_EXISTS, "银信证记录不存在");
        }
        context.setAttribute("lc", lc);

        // 只允许多次解付银信证
        if (lc.getPayType().compareTo(LcPayType.MULTIPAY_PAY_WITH_SAME_PAYEE.getCode()) != 0) {
            throw new CoreValidationRejectException(ReturnCode.CORE_STD_LC_INVALID_ONLY_MULTIPLE_SUPPORTED, "只有多次解付银信证可以对履约进行失效");
        }

        // 获取履约记录
        LcSend appointment = lcSendDao.selectByPrimaryKey(input.getLcAppointmentId());
        if (appointment == null) {
            throw new CoreValidationRejectException(ReturnCode.CORE_STD_LC_APPOINTMENT_NOT_EXISTS, "履约记录不存在");
        }
        context.setAttribute("appointment", appointment);

        // 履约没有被处理
        if (appointment.getProcessStatus() == null || appointment.getProcessStatus().compareTo(ProcessStatus.INPROCESS.getCode()) != 0) {
            throw new CoreValidationRejectException(ReturnCode.CORE_STD_LC_APPLY_IS_INPROCESS_EXISTS, "履约已处理");
        }
    }

    @Override
    protected void validateLcValidity(InvalidateAppointmentKernelInputBean input, CoreBusinessContext context) throws CoreValidationRejectException {
        // TODO Auto-generated method stub

    }

    @Override
    protected BusinessOutput<InvalidateAppointmentKernelOutputBean> processLcKernelLogic(InvalidateAppointmentKernelInputBean inputBean, CoreBusinessContext context) throws CoreBusinessException {
        logger.info("=== 进入部分履约失效:" + inputBean.getLcId() + ", 履约ID: " + inputBean.getLcAppointmentId());

        Lc lc = (Lc) context.getAttribute("lc");
        LcSend appointment = (LcSend) context.getAttribute("appointment");
        Date now = new Date();

        DbTransaction dbTransaction = dbTransactionHelper.beginTransaction();
        try {

            // 将履约标记为“已失效”
            LcSend updateAppointment = new LcSend();
            updateAppointment.setLcSendId(appointment.getLcSendId());
            updateAppointment.setUpdateTime(now);
            updateAppointment.setProcessStatus(ProcessStatus.INVALIDATE.getCode());
            boolean updateAppointmentSuccess = lcSendDao.updateByPrimaryKeySelective(updateAppointment) > 0;

            // 将“冻结金额”退回“余额”
            boolean unfreezeSuccess = lcDao.unfreezeAmount(lc.getLcId(), appointment.getLcConfirmAmount()) > 0;

            if (updateAppointmentSuccess && unfreezeSuccess) {
                // 插入失效记录
                LcInvalid lcInvalid = new LcInvalid();
                lcInvalid.setLcInvalidId(lcInvalidIdWorker.nextId());
                lcInvalid.setCreateTime(now);
                lcInvalid.setUpdateTime(now);
                lcInvalid.setLcId(lc.getLcId());
                lcInvalid.setInvalidType(inputBean.getInvalidateType().getCode());

                lcInvalid.setLcAmount(lc.getLcAmount());
                lcInvalid.setLcBalance(lc.getLcBalance());
                lcInvalid.setInvalidAmount(appointment.getLcConfirmAmount());
                lcInvalid.setRemark(inputBean.getRemark());

                lcInvalid.setLcInvalidStatus(LcTranStatus.SUCCESS.getTranStatusStr());
                lcInvalidDao.insert(lcInvalid);

                dbTransactionHelper.commitTransaction(dbTransaction);

                InvalidateAppointmentKernelOutputBean invalidateAppointmentKernelOutputBean = new InvalidateAppointmentKernelOutputBean();
                invalidateAppointmentKernelOutputBean.setLcId(lc.getLcId());
                invalidateAppointmentKernelOutputBean.setLcStatus(lc.getLcStatus());
                invalidateAppointmentKernelOutputBean.setLcStatusDesc(LcStatusType.getDesc(lc.getLcStatus()));
                invalidateAppointmentKernelOutputBean.setLcInvalidateId(lcInvalid.getLcInvalidId());
                invalidateAppointmentKernelOutputBean.setInvalidateAmount(lcInvalid.getInvalidAmount());

                return BusinessOutput.success(invalidateAppointmentKernelOutputBean);
            }

            throw new CoreBusinessException(ReturnCode.CORE_STD_INVALIDATE_APPOINTMENT_ERROR, "更新记录失败");

        } catch (Exception e) {
            dbTransactionHelper.rollbackTransaction(dbTransaction);
            CoreBusinessExceptionHelper.throwAsCoreBusinessException(e);
        }

        throw new CoreBusinessException(ReturnCode.CORE_STD_INVALIDATE_APPOINTMENT_ERROR, "履约失效失败");
    }

}
