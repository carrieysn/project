package com.cifpay.lc.std.mapper.lc.output;

import org.springframework.beans.BeanUtils;

import com.cifpay.lc.domain.lc.InitLcOutputBean;
import com.cifpay.lc.std.domain.kernel.InitLcKernelOutputBean;

public class InitLcKernelOutputBeanMapper {

	public static InitLcOutputBean ToInitLcOutputBean(InitLcKernelOutputBean outputBean) {
		InitLcOutputBean result = new InitLcOutputBean();

		if (outputBean != null) {
			BeanUtils.copyProperties(outputBean, result);
		}

		return result;
	}
}
