package com.cifpay.lc.std.mapper.lc.output;

import org.springframework.beans.BeanUtils;

import com.cifpay.lc.domain.lc.ApplyOutputBean;
import com.cifpay.lc.std.domain.kernel.ApplyKernelOutputBean;

public class ApplyKernelOutputBeanMapper {

	public static ApplyOutputBean toApplyOutputBean(ApplyKernelOutputBean outputBean) {
		ApplyOutputBean result = new ApplyOutputBean();

		if (outputBean != null) {
			BeanUtils.copyProperties(outputBean, result);
		}

		return result;
	}
}
