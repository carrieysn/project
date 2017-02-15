package com.cifpay.lc.gateway.mapper.batch;

import java.util.ArrayList;
import java.util.List;

import com.cifpay.lc.domain.batch.BatchInitLcOutputDto;
import com.cifpay.lc.gateway.output.batch.InitLcOutputDto;

public class InitLcOutputBeanMapper {

	public static InitLcOutputDto ToInitLcOutputDto(BatchInitLcOutputDto lcOutputBean) {
		if (lcOutputBean == null) {
			return new InitLcOutputDto();
		}

		InitLcOutputDto dto = new InitLcOutputDto();

		dto.setLcId(lcOutputBean.getLcId().toString());
		dto.setLcNo(lcOutputBean.getLcNo());
		dto.setLcType(lcOutputBean.getLcType());

		dto.setLcAmount(lcOutputBean.getLcAmount().toString());
		dto.setCurrency(lcOutputBean.getCurrency());

		dto.setPayerBankName(lcOutputBean.getPayerBankName());
		dto.setPayerBankCode(lcOutputBean.getPayerBankCode());
		dto.setPayerBankAccountNo(lcOutputBean.getPayerBankAccountNo());

		dto.setRecvBankName(lcOutputBean.getRecvBankName());
		dto.setRecvBankCode(lcOutputBean.getRecvBankCode());
		dto.setRecvBankAccountNo(lcOutputBean.getRecvBankAccountNo());

		dto.setOrderId(lcOutputBean.getOrderId());
		dto.setMrchOrderUrl(lcOutputBean.getMrchOrderUrl());

		dto.setRecvValidTime(lcOutputBean.getRecvValidTime());
		dto.setSendValidTime(lcOutputBean.getSendValidTime());
		dto.setConfirmPayValidTime(lcOutputBean.getConfirmPayValidTime());
		dto.setPayValidTime(lcOutputBean.getPayValidTime());

		return dto;
	}

	public static List<InitLcOutputDto> ToInitLcOutputDto(List<BatchInitLcOutputDto> lcOutputBeanList) {
		if (lcOutputBeanList == null || lcOutputBeanList.isEmpty()) {
			return new ArrayList<InitLcOutputDto>();
		}

		List<InitLcOutputDto> result = new ArrayList<InitLcOutputDto>();

		for (BatchInitLcOutputDto lcOutputBean : lcOutputBeanList) {
			result.add(ToInitLcOutputDto(lcOutputBean));
		}

		return result;
	}
}
