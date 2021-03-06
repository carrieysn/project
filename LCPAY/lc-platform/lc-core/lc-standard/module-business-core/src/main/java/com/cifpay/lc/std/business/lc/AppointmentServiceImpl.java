package com.cifpay.lc.std.business.lc;

import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.api.gateway.lc.AppointmentService;
import com.cifpay.lc.constant.BizConstants;
import com.cifpay.lc.constant.BizConstants.LcTranCode;
import com.cifpay.lc.constant.ResultHandler;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.constant.enums.LcPayType;
import com.cifpay.lc.constant.enums.LcStatusType;
import com.cifpay.lc.constant.enums.LcTranStatus;
import com.cifpay.lc.constant.enums.ProcessStatus;
import com.cifpay.lc.core.cache.pojo.LcProductCache;
import com.cifpay.lc.core.common.CoreBusinessContext;
import com.cifpay.lc.core.common.CoreBusinessInterceptorConfig;
import com.cifpay.lc.core.common.CoreBusinessTranCode;
import com.cifpay.lc.core.db.dao.LcDao;
import com.cifpay.lc.core.db.dao.LcLogDao;
import com.cifpay.lc.core.db.dao.LcSendDao;
import com.cifpay.lc.core.db.pojo.Lc;
import com.cifpay.lc.core.db.pojo.LcLog;
import com.cifpay.lc.core.db.pojo.LcSend;
import com.cifpay.lc.core.exception.CoreBusinessException;
import com.cifpay.lc.core.exception.CoreValidationRejectException;
import com.cifpay.lc.core.uid.LcLogIdWorker;
import com.cifpay.lc.core.uid.LcSendIdWorker;
import com.cifpay.lc.domain.lc.AppointmentInputBean;
import com.cifpay.lc.domain.lc.AppointmentOutputBean;
import com.cifpay.lc.std.interceptor.BusinessLockInterceptor;
import com.cifpay.lc.std.interceptor.BusinessLoggingInterceptor;
import com.cifpay.lc.std.interceptor.LcAutoFlowProcessingInterceptor;
import com.cifpay.lc.std.interceptor.LcTransactionInterceptor;
import com.cifpay.lc.std.kernel.lc.appointment.AppointmentStrategy;
import com.cifpay.lc.util.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 履约
 *
 * @author sweet
 */
@Service("appointmentService")
@CoreBusinessInterceptorConfig({BusinessLoggingInterceptor.class, BusinessLockInterceptor.class, LcTransactionInterceptor.class, LcAutoFlowProcessingInterceptor.class})
@CoreBusinessTranCode(LcTranCode.APPOINTMENT)
public class AppointmentServiceImpl extends AbstractLcProductServiceImplBase<AppointmentInputBean, AppointmentOutputBean> implements AppointmentService {

    @Autowired
    private LcDao lcDao;  // 银信证DAO

    @Autowired
    private LcSendDao lcSendDao;

    @Autowired
    private LcLogDao lcLogDao; // 流水DAO

    @Autowired
    private LcLogIdWorker lcLogIdWorker;

    @Autowired
    private LcSendIdWorker lcSendIdWorker;

    private Map<String, AppointmentStrategy> appointmentStrategyMappings;

    @Autowired
    public void setAppointmentStrategys(List<AppointmentStrategy> appointmentStrategys) {
        this.appointmentStrategyMappings = new HashMap<String, AppointmentStrategy>();
        for (AppointmentStrategy st : appointmentStrategys) {
            this.appointmentStrategyMappings.put(st.getLcType(), st);
        }
    }


    @Override
    public void validateInputParameters(AppointmentInputBean inputBean) throws CoreValidationRejectException {

        if (inputBean.getLcId() <= 0) {
            throw new CoreValidationRejectException(103200, "银信证ID不允许为空");
        }

        if (inputBean.getOrderAmount() == null) {
            inputBean.setOrderAmount(BigDecimal.ZERO);
        }

        if (inputBean.getOrderAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new CoreValidationRejectException(103100, "金额不正确");
        }
    }

    @Override
    protected void validateLc(AppointmentInputBean inputBean, Lc lc) throws CoreValidationRejectException {
        if (lc == null) {
            throw new CoreValidationRejectException(ReturnCode.CORE_STD_LC_NOT_EXISTS, "银信证记录不存在");
        }
        if (lc.getLcStatus() != null && LcStatusType.RETREAT.getStatusCode().compareTo(lc.getLcStatus()) == 0) {
            throw new CoreValidationRejectException(ReturnCode.CORE_STD_LC_INVALID, "银信证已失效");
        }

        // 如果银信证状态不是“已收证”（部分履约），则抛出异常
        String lcStatus = lc.getLcStatus();
        boolean isAppointmentDone = lcStatus.equals(LcStatusType.RECIEVED.getStatusCode()) || lcStatus.equals(LcStatusType.APPOINTMENT_PART_DONE.getStatusCode());
        if (!isAppointmentDone) {
            throw new CoreValidationRejectException(ReturnCode.CORE_STD_LC_STATUS_CHECK_UNPASSED, "银信证状态不正确");
        }

        // 如果履约有效期小于当前时间，则过期
        Date now = new Date();
        if (lc.getSendValidTime().getTime() < now.getTime()) {
            throw new CoreValidationRejectException(ReturnCode.CORE_STD_LC_VALIDITY_FAIL_APPOINTMENT, "已过履约有效期");
        }

        // 验证 履约金额
        BigDecimal appointmentAmount = inputBean.getOrderAmount();
        BigDecimal lcBalance = lc.getLcBalance();
        if (appointmentAmount.compareTo(lcBalance) > 0) {
            throw new CoreValidationRejectException(ReturnCode.CORE_STD_LC_APPOINTMENT_AMT_EXCEED, "交易金额超限");
        }

        // 验证 履约类型
        String strPayType = lc.getPayType(); // 解付类型：SINGLE单次解付、MULTIPLE同收款人多次解付、
        BigDecimal inputAmount = inputBean.getOrderAmount();

        if (LcPayType.SINGLE_PAY.getCode().compareTo(strPayType) == 0) { // 单次解付判断金额是否相等
            inputBean.setOrderAmount(lc.getLcAmount());     // 单次解付时，使用余额
            inputBean.setOrderId(lc.getOrderId());// 单次解付时，保存订单信息
            inputBean.setOrderContent(lc.getOrderContent());// 单次解付时，保存订单信息
        } else if (LcPayType.MULTIPAY_PAY_WITH_SAME_PAYEE.getCode().compareTo(strPayType) == 0) { // 多次解付判断金额是否小于可用余额
            if (inputAmount.compareTo(lc.getLcBalance()) > 0) {
                throw new CoreValidationRejectException(ReturnCode.CORE_STD_LC_APPOINTMENT_AMT_EXCEED, "履约金额超限");
            }
        }

        // 检查银信证状态“已收证”或“多次解付”
        // 1）单笔
        // 2）批量
        String payType = lc.getPayType();

        if (LcPayType.SINGLE_PAY.getCode().compareTo(payType) == 0) {
            if (lcStatus.compareTo(LcStatusType.RECIEVED.getStatusCode()) != 0) {
                throw new CoreValidationRejectException(ReturnCode.CORE_STD_LC_STATUS_CHECK_UNPASSED, "履约交易状态校验不通过");
            }
            // 检查不存在履约记录
            List<LcSend> lcSendList = lcSendDao.selectBylcId(lc.getLcId());
            for (LcSend lcSend : lcSendList) {
                if (LcTranStatus.SUCCESS.getTranStatusStr().compareTo(lcSend.getSendStatus()) == 0) {
                    throw new CoreValidationRejectException(ReturnCode.CORE_STD_LC_STATUS_CHECK_UNPASSED, "银信证已履约");
                }
            }
        }
        if (LcPayType.MULTIPAY_PAY_WITH_SAME_PAYEE.getCode().compareTo(payType) == 0) {
            if (lcStatus.compareTo(LcStatusType.RECIEVED.getStatusCode()) != 0 && lcStatus.compareTo(LcStatusType.APPOINTMENT_PART_DONE.getStatusCode()) != 0 && lcStatus.compareTo(LcStatusType.DEFER.getStatusCode()) != 0) {
                throw new CoreValidationRejectException(ReturnCode.CORE_STD_LC_STATUS_CHECK_UNPASSED, "履约交易状态校验不通过");
            }
        }
    }

    @Override
    public void validateLcProductRule(AppointmentInputBean inputBean, LcProductCache lcProduct, CoreBusinessContext context) throws CoreValidationRejectException {

        context.setAttribute("LC_PRODUCT_POJO", lcProduct);
    }

    @Override
    public BusinessOutput<AppointmentOutputBean> processBusiness(AppointmentInputBean inputBean, CoreBusinessContext context) throws CoreBusinessException {
        logger.info("进入履约业务处理...");

        Lc lc = (Lc) context.getAttribute("LC"); // 银信证表数据
        Date now = new Date();

        AppointmentStrategy strategy = appointmentStrategyMappings.get(lc.getLcType());
        if (strategy == null) {
            throw new CoreBusinessException(ReturnCode.CORE_STD_UNSUPPORT_APPOINTMENT, "暂不支持当前类型的履约操作");
        }

        LcSend rawLcSend = new LcSend();
        rawLcSend.setLcId(lc.getLcId());
        rawLcSend.setLcSendId(lcSendIdWorker.nextId());
        rawLcSend.setMid(lc.getMid());
        rawLcSend.setSendType(lc.getLcType());
        rawLcSend.setSendStatus(LcTranStatus.INPROCESS.getTranStatusStr());
        rawLcSend.setProcessStatus(ProcessStatus.INPROCESS.getCode());
        rawLcSend.setValidTime(lc.getConfirmValidTime());

        // 进行履约操作
        ResultHandler<LcSend> response = strategy.processAppointment(rawLcSend, inputBean);

        // 插入履约表
        LcSend lcSend = response.getData();
        lcSend.setLcId(lc.getLcId());
        lcSend.setLcBalance(lc.getLcBalance());
        lcSend.setSendTime(now);
        lcSend.setCreateTime(now);
        lcSend.setUpdateTime(now);
        lcSendDao.insert(lcSend);

        if (response.isSuccess()) {

            // 更新银信证表状态（单次解付、多次解付）
            BigDecimal maxAmount = lc.getLcBalance(); // 可用金额
            BigDecimal sendAmount = maxAmount.min(lcSend.getOrderAmount()); // 履约金额

            Lc updateLc = new Lc();
            updateLc.setLcId(lc.getLcId());
            updateLc.setUpdateTime(now);
            updateLc.setLcStatus(lc.getLcStatus());
            updateLc.setLcFreezingAmount(MathUtil.add(lc.getLcFreezingAmount(), sendAmount));
            updateLc.setLcBalance(MathUtil.sub(lc.getLcBalance(), sendAmount));

            if (lc.getLcStatus().compareTo(LcStatusType.RECIEVED.getStatusCode()) == 0 && lc.getPayType().compareTo(LcPayType.MULTIPAY_PAY_WITH_SAME_PAYEE.getCode()) == 0) {
                updateLc.setLcStatus(LcStatusType.APPOINTMENT_PART_DONE.getStatusCode());
            }
            if (lc.getLcStatus().compareTo(LcStatusType.RECIEVED.getStatusCode()) == 0 && lc.getPayType().compareTo(LcPayType.SINGLE_PAY.getCode()) == 0) {
                updateLc.setLcStatus(LcStatusType.APPOINTMENT_DONE.getStatusCode());
            }

            lcDao.updateByPrimaryKeySelective(updateLc);

            // 插入流水表
            LcLog lcLog = new LcLog();
            long logId = lcLogIdWorker.nextId();
            lcLog.setLogId(logId);
            lcLog.setLcId(updateLc.getLcId());
            lcLog.setStepLogId(lcSend.getLcSendId());
            lcLog.setTradeCode(BizConstants.LcTranCode.APPOINTMENT.getTranCodeStr());
            lcLog.setFromStatus(lc.getLcStatus());
            lcLog.setToStatus(updateLc.getLcStatus());
            lcLog.setLcResponse(lcSend.getSendStatus());
            lcLog.setRemark("履约");
            lcLog.setCreateTime(now);
            lcLogDao.insertSelective(lcLog);

            AppointmentOutputBean appointmentOutputBean = new AppointmentOutputBean();
            appointmentOutputBean.setLcId(lc.getLcId());
            appointmentOutputBean.setLcStatus(updateLc.getLcStatus());
            appointmentOutputBean.setLcStatusDesc(LcStatusType.getDesc(updateLc.getLcStatus()));
            appointmentOutputBean.setAppointmentId(lcSend.getLcSendId());
            appointmentOutputBean.setAppointmentAmount(lcSend.getOrderAmount());

            logger.info("---离开履约业务处理");

            return BusinessOutput.success(appointmentOutputBean);
        }
        return BusinessOutput.fail(ReturnCode.CORE_STD_APPOINTMENT_ERROR, response.getMessage());
    }

}
