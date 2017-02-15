package com.cifpay.lc.std.mapper.lc.input;

import com.cifpay.lc.domain.lc.InvalidateLcInputBean;
import com.cifpay.lc.std.domain.kernel.InvalidateLcKernelInputBean;

public final class InvalidateLcInputBeanMapper {

    public static InvalidateLcKernelInputBean toInvalidateLcKernelInputBean(InvalidateLcInputBean inputBean) {
        InvalidateLcKernelInputBean kernelInputBean = new InvalidateLcKernelInputBean();

        if (inputBean != null) {
            kernelInputBean.setLcId(inputBean.getLcId());
            kernelInputBean.setMerId(inputBean.getMerId());
            kernelInputBean.setInvalidateType(inputBean.getInvalidateType());
            kernelInputBean.setRemark(inputBean.getRemark());
        }

        return kernelInputBean;
    }
}
