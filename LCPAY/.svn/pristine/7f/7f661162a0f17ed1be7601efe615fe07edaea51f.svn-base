package com.cifpay.lc.gateway.mapper.batch;

import java.util.ArrayList;
import java.util.List;

import com.cifpay.lc.constant.BizConstants;
import com.cifpay.lc.domain.batch.OpenLcOutputBean;
import com.cifpay.lc.gateway.output.batch.OpenLcOutputDto;

public class OpenLcOutputBeanMapper {

	public static OpenLcOutputDto ToOpenLcOutputDto(OpenLcOutputBean outputBean) {
		if (outputBean == null) {
			return new OpenLcOutputDto();
		}

		OpenLcOutputDto dto = new OpenLcOutputDto();

		dto.setLcId(outputBean.getLcId().toString());
		dto.setLcStatus(outputBean.getLcStatus());
		dto.setOrderId(outputBean.getOrderId());
		dto.setLcAmount(BizConstants.decimalFormat.format(outputBean.getLcAmount()));

		return dto;
	}

	public static List<OpenLcOutputDto> ToOpenLcOutputDto(List<OpenLcOutputBean> outputBeanList) {

		if (outputBeanList == null || outputBeanList.isEmpty()) {
			return new ArrayList<OpenLcOutputDto>();
		}

		List<OpenLcOutputDto> result = new ArrayList<OpenLcOutputDto>();

		for (OpenLcOutputBean outputBean : outputBeanList) {
			result.add(ToOpenLcOutputDto(outputBean));
		}

		return result;
	}

}
