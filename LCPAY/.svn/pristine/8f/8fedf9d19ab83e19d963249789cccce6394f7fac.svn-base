package com.cifpay.lc.std.util;

import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.constant.enums.LcTranStatus;
import com.cifpay.lc.std.domain.kernel.RefundKernalOutputBean;
import com.cifpay.lc.std.domain.paychannel.ExpiryOutputBean;
import com.cifpay.lc.std.domain.paychannel.FreezeOutputBean;
import com.cifpay.lc.std.domain.paychannel.UnfreezeOutputBean;

import java.io.Serializable;

public class BeanFactory {

    public static <T extends Serializable> FreezeOutputBean<T> getSucFreezeOutputBean() {
        FreezeOutputBean<T> outputBean = new FreezeOutputBean<T>();
        outputBean.setReturnCode(ReturnCode.GENERAL_SUCCESS);
        outputBean.setReturnMsg("操作成功");
        return outputBean;
    }

    public static <T extends Serializable> FreezeOutputBean<T> getFailFreezeOutputBean(int errorCode, String message) {
        FreezeOutputBean<T> outputBean = new FreezeOutputBean<T>();
        outputBean.setReturnCode(errorCode);
        outputBean.setReturnMsg(message);
        outputBean.setLcTranStatus(LcTranStatus.FAIL);
        return outputBean;
    }

    public static <T extends Serializable> UnfreezeOutputBean<T> getSucUnfreezeOutputBean() {
        UnfreezeOutputBean<T> outputBean = new UnfreezeOutputBean<T>();
        outputBean.setSuccess(true);
        outputBean.setMessage("操作成功");
        outputBean.setLcTranStatus(LcTranStatus.SUCCESS);
        return outputBean;
    }

    public static <T extends Serializable> UnfreezeOutputBean<T> getFailUnfreezeOutputBean(String message) {
        UnfreezeOutputBean<T> outputBean = new UnfreezeOutputBean<T>();
        outputBean.setSuccess(false);
        outputBean.setMessage(message);
        outputBean.setLcTranStatus(LcTranStatus.FAIL);
        return outputBean;
    }

    public static <T extends Serializable> ExpiryOutputBean<T> getSucExpiryOutputBean() {
        ExpiryOutputBean<T> outputBean = new ExpiryOutputBean<T>();
        outputBean.setSuccess(true);
        outputBean.setMessage("操作成功");
        return outputBean;
    }

    public static <T extends Serializable> ExpiryOutputBean<T> getFailExpiryOutputBean(String message) {
        ExpiryOutputBean<T> outputBean = new ExpiryOutputBean<T>();
        outputBean.setSuccess(false);
        outputBean.setMessage(message);
        outputBean.setLcTranStatus(LcTranStatus.FAIL);
        return outputBean;
    }

    public static <T extends Serializable> RefundKernalOutputBean<T> getSucRefundOutputBean() {
        RefundKernalOutputBean<T> outputBean = new RefundKernalOutputBean<T>();
        outputBean.setSuccess(true);
        outputBean.setMessage("操作成功");
        return outputBean;
    }

    public static <T extends Serializable> RefundKernalOutputBean<T> getFailRefundOutputBean(String message) {
        RefundKernalOutputBean<T> outputBean = new RefundKernalOutputBean<T>();
        outputBean.setSuccess(false);
        outputBean.setMessage(message);
        outputBean.setLcTranStatus(LcTranStatus.FAIL);
        return outputBean;
    }
}
