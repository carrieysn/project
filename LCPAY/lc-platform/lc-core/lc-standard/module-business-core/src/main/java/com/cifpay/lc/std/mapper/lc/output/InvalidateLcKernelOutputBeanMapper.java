package com.cifpay.lc.std.mapper.lc.output;

import org.springframework.beans.BeanUtils;

import com.cifpay.lc.domain.lc.InvalidateLcOutputBean;
import com.cifpay.lc.std.domain.kernel.InvalidateLcKernelOutputBean;

public class InvalidateLcKernelOutputBeanMapper {

	public static InvalidateLcOutputBean toInvalidateLcOutputBean(InvalidateLcKernelOutputBean outputBean) {
		InvalidateLcOutputBean result = new InvalidateLcOutputBean();

		if (outputBean != null) {
			BeanUtils.copyProperties(outputBean, result);
		}

		return result;
	}
}
