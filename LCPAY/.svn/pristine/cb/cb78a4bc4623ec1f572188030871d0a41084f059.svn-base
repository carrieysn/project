package com.cifpay.lc.std.mapper.lc.input;

import org.springframework.beans.BeanUtils;

import com.cifpay.lc.domain.lc.ApplyInputBean;
import com.cifpay.lc.std.domain.kernel.ApplyKernelInputBean;

public class ApplyInputBeanMapper {
	public static ApplyKernelInputBean toApplyKernelInputBean(ApplyInputBean inputBean) {
		ApplyKernelInputBean result = new ApplyKernelInputBean();
		if (inputBean != null) {
			BeanUtils.copyProperties(inputBean, result);
		}

		return result;
	}
}
