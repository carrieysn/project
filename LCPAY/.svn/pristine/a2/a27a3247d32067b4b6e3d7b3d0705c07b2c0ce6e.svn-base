package com.cifpay.lc.std.interceptor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.cifpay.lc.core.message.biz.impl.LcMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.api.gateway.lc.ApplyService;
import com.cifpay.lc.api.gateway.lc.AppointmentService;
import com.cifpay.lc.api.gateway.lc.RecvLcService;
import com.cifpay.lc.api.message.lc.OpenLcNotifyMessageService;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.constant.enums.LcStatusType;
import com.cifpay.lc.constant.enums.MessageEnum;
import com.cifpay.lc.core.cache.pojo.LcProductCache;
import com.cifpay.lc.core.cache.service.LcProductCacheServant;
import com.cifpay.lc.core.common.CoreBusinessContext;
import com.cifpay.lc.core.component.CoreBusinessInterceptor;
import com.cifpay.lc.core.db.dao.LcDao;
import com.cifpay.lc.core.db.pojo.Lc;
import com.cifpay.lc.core.exception.CoreBusinessException;
import com.cifpay.lc.domain.lc.ApplyOutputBean;
import com.cifpay.lc.domain.lc.AppointmentOutputBean;
import com.cifpay.lc.domain.lc.AbstractLcInputBean;
import com.cifpay.lc.domain.lc.AbstractLcOutputBean;
import com.cifpay.lc.domain.message.LcApplyParamBean;
import com.cifpay.lc.domain.message.LcAppointmentParamBean;
import com.cifpay.lc.domain.message.LcRecvParamBean;
import com.cifpay.lc.domain.message.LcTansferParamBean;

@Component
public class LcAutoFlowProcessingInterceptor implements CoreBusinessInterceptor {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private LcDao lcDao;

    @Autowired
    private LcProductCacheServant lcProductCacheServant;

    @Autowired
    private LcMessageHandler lcMessageHandler;

    @Override
    public void beforeProcessBusiness(Object serviceInstance, Serializable inputData, CoreBusinessContext context)
            throws CoreBusinessException {
    }

    @Override
    public void afterProcessBusiness(Object serviceInstance,
                                     Serializable inputData,
                                     BusinessOutput<Serializable> businessOutput,
                                     CoreBusinessContext context) throws CoreBusinessException {

        if (logger.isDebugEnabled()) {
            logger.debug("LcAutoFlowProcessingInterceptor.afterProcessBusiness ...");
        }

        if (!(inputData instanceof AbstractLcInputBean)) {
            throw new CoreBusinessException(ReturnCode.CORE_STD_UNSUPPORT_AUTOFLOW, "当前节点不支持自动流程");
        }

        // 自动流程
        if (businessOutput.isSuccess()) {
            logger.debug("检查是否需要自动流程 ...");

            // 获取参数
            AbstractLcInputBean inputBean = (AbstractLcInputBean) inputData;

            Object businessOutputBean = businessOutput.getData();

            // 查询产品
            Lc lc = lcDao.selectByPrimaryKey(inputBean.getLcId());

            LcProductCache lcProductCache = lcProductCacheServant.getLcProductCache(lc.getProductCode());

            autoProcess(serviceInstance, inputBean, businessOutputBean, lc, lcProductCache);

            AbstractLcOutputBean nextOutputBean = updateOutputBean(businessOutputBean, lcProductCache);

            businessOutput.setReturnCode(businessOutput.getReturnCode());
            businessOutput.setReturnMsg(businessOutput.getReturnMsg());
            businessOutput.setData(nextOutputBean);
        }
    }

    @Override
    public void onException(Object serviceInstance,
                            Serializable inputData,
                            BusinessOutput<Serializable> businessOutput,
                            Throwable exception,
                            CoreBusinessContext context) throws CoreBusinessException {
    }

    // ↓↓↓↓↓↓↓↓↓↓ 自动流程相关逻辑 ↓↓↓↓↓↓↓↓↓↓
    private void autoProcess(Object businessInstance,
                             AbstractLcInputBean inputBean,
                             Object outputBean,
                             Lc lc,
                             LcProductCache lcProductCache) {

        if (lcProductCache == null) {
            logger.error("未找到对应产品，LcId=" + inputBean.getLcId());
            return;
        }

        // 开证阶段
        if (businessInstance instanceof OpenLcNotifyMessageService) {

            if (lcProductCache.getAutoRecv() != null && lcProductCache.getAutoRecv()) {
                logger.info("开证阶段自动收证...");

                LcRecvParamBean lcRecvParamBean = new LcRecvParamBean();
                lcRecvParamBean.setLcId(inputBean.getLcId());
                lcRecvParamBean.setMerId(inputBean.getMerId());
                lcMessageHandler.sendMessage(MessageEnum.MsgType.LC, MessageEnum.Scene.RECVLC, lcRecvParamBean);
            }
        }

        // 收证阶段
        if (businessInstance instanceof RecvLcService) {

            if (lcProductCache.getAutoSend() != null && lcProductCache.getAutoSend()) {
                logger.info("收证阶段自动履约...");

                LcAppointmentParamBean lcAppointmentParamBean = new LcAppointmentParamBean();
                lcAppointmentParamBean.setLcId(inputBean.getLcId());
                lcAppointmentParamBean.setMerId(inputBean.getMerId());
                lcAppointmentParamBean.setOrderId(lc.getOrderId());
                lcAppointmentParamBean.setOrderAmount(lc.getLcAmount());
                lcMessageHandler.sendMessage(MessageEnum.MsgType.LC, MessageEnum.Scene.APPOINTMENTLC, lcAppointmentParamBean);
            }
        }

        // 履约阶段
        if (businessInstance instanceof AppointmentService) {

            if (lcProductCache.getAutoConfirm() != null && lcProductCache.getAutoConfirm()) {
                logger.info("履约阶段自动申请解付...");

                if (false == outputBean instanceof AppointmentOutputBean) {
                    throw new CoreBusinessException(ReturnCode.CORE_STD_AUTOFLOW_ERROR, "履约阶段未正确返回参数，无法进行自动流程");
                }

                AppointmentOutputBean appointmentOutputBean = (AppointmentOutputBean) outputBean;
                LcApplyParamBean applyParamBean = new LcApplyParamBean();
                applyParamBean.setLcId(inputBean.getLcId());
                applyParamBean.setMerId(inputBean.getMerId());
                applyParamBean.setLcAppointmentId(appointmentOutputBean.getAppointmentId());
                applyParamBean.setRemark("履约阶段自动申请解付");
                lcMessageHandler.sendMessage(MessageEnum.MsgType.LC, MessageEnum.Scene.APPLYLC, applyParamBean);
            }
        }

        // 申请解付阶段
        if (businessInstance instanceof ApplyService) {

            if (lcProductCache.getAutoPay() != null && lcProductCache.getAutoPay()) {
                logger.info("申请解付阶段自动执行解付...");

                if (false == outputBean instanceof ApplyOutputBean) {
                    throw new CoreBusinessException(ReturnCode.CORE_STD_AUTOFLOW_ERROR, "履约阶段未正确返回参数，无法进行自动流程");
                }

                ApplyOutputBean applyOutputBean = (ApplyOutputBean) outputBean;
                LcTansferParamBean lcTansferParamBean = new LcTansferParamBean();
                lcTansferParamBean.setLcId(inputBean.getLcId());
                lcTansferParamBean.setMerId(inputBean.getMerId());
                lcTansferParamBean.setApplyId(applyOutputBean.getLcConfirmId());
                lcTansferParamBean.setRemark("申请解付阶段自动执行解付");
                lcMessageHandler.sendMessage(MessageEnum.MsgType.LC, MessageEnum.Scene.TRANSFERLC, lcTansferParamBean);
            }
        }
    }

    private AbstractLcOutputBean updateOutputBean(Object businessOutputBean, LcProductCache lcProductCache) {

        if (!(businessOutputBean instanceof AbstractLcOutputBean)) {
            return null;
        }

        AbstractLcOutputBean outputBean = (AbstractLcOutputBean) businessOutputBean;

        if (lcProductCache == null) {
            return outputBean;
        }

        // 将状态修改为最终要成为的状态
        LcStatusType finalStatus = getFinalStatus(
                LcStatusType.parse(outputBean.getLcStatus()), lcProductCache, null);

        outputBean.setLcStatus(finalStatus.getStatusCode());

        outputBean.setLcStatusDesc(LcStatusType.getDesc(finalStatus.getStatusCode()));

        return outputBean;
    }


    private LcStatusType getFinalStatus(LcStatusType currentStatus,
                                        LcProductCache lcProductCache,
                                        List<LcStatusType> set) {
        if (set == null) {

            set = new ArrayList<LcStatusType>();

            set.add(LcStatusType.OPENED);
            set.add(LcStatusType.RECIEVED);
            set.add(LcStatusType.APPOINTMENT_DONE);
            set.add(LcStatusType.APPLIED);
            set.add(LcStatusType.TRANSFERED);
            set.add(LcStatusType.SUCCESS);
        }

        // 找到当前位置
        int index = set.indexOf(currentStatus);

        index = Math.min(index + 1, set.size() - 1);

        LcStatusType nextStatus = set.get(index);

        while (nextStatus != null && checkIsAuto(lcProductCache, nextStatus)) {

            currentStatus = nextStatus;

            index = index + 1;

            if (index <= set.size() - 1) {

                // 找到下一位置
                nextStatus = set.get(index);
            } else {
                nextStatus = null;
            }
        }

        return currentStatus;
    }

    private boolean checkIsAuto(LcProductCache lcProductCache, LcStatusType nextStatus) {

        switch (nextStatus) {

            case RECIEVED:

                if (lcProductCache.getAutoRecv() != null && lcProductCache.getAutoRecv()) {
                    return true;
                }

                break;

            case APPOINTMENT_DONE:

                if (lcProductCache.getAutoSend() != null && lcProductCache.getAutoSend()) {
                    return true;
                }

                break;

            case APPLIED:

                if (lcProductCache.getAutoConfirm() != null && lcProductCache.getAutoConfirm()) {
                    return true;
                }

                break;

            case TRANSFERED:

                if (lcProductCache.getAutoPay() != null && lcProductCache.getAutoPay()) {
                    return true;
                }

                break;

            default:

                break;
        }

        return false;
    }

    // ↑↑↑↑↑↑↑↑↑↑ 自动流程相关逻辑 ↑↑↑↑↑↑↑↑↑↑
}
