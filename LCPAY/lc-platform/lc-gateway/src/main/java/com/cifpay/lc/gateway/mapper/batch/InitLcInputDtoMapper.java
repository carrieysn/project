package com.cifpay.lc.gateway.mapper.batch;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.cifpay.lc.constant.enums.AccountPropertyType;
import com.cifpay.lc.domain.batch.BatchInitLcInputDto;
import com.cifpay.lc.gateway.input.batch.InitLcInputDto;
import com.cifpay.lc.util.StringUtil;

public class InitLcInputDtoMapper {

	public static BatchInitLcInputDto ToBatchInitLcInputDto(InitLcInputDto lcDto) {
		if (lcDto == null) {
			return new BatchInitLcInputDto();
		}

		BatchInitLcInputDto lcInputBean = new BatchInitLcInputDto();

		lcInputBean.setProductCode(lcDto.getProductCode());
		lcInputBean.setOrderId(lcDto.getOrderId());

		lcInputBean.setCurrency(lcDto.getCurrency());
		lcInputBean.setAmount(new BigDecimal(lcDto.getAmount()));

		lcInputBean.setRecvValidSecond(new Integer(StringUtil.isDigit(lcDto.getRecvValidSecond()) ? lcDto.getRecvValidSecond() : "0"));
		lcInputBean.setSendValidSecond(new Integer(StringUtil.isDigit(lcDto.getSendValidSecond()) ? lcDto.getSendValidSecond() : "0"));
		lcInputBean.setConfirmValidSecond(new Integer(StringUtil.isDigit(lcDto.getConfirmValidSecond()) ? lcDto.getConfirmValidSecond() : "0"));
		lcInputBean.setPayValidSecond(new Integer(StringUtil.isDigit(lcDto.getPayValidSecond()) ? lcDto.getPayValidSecond() : "0"));

		lcInputBean.setRecvBankCode(lcDto.getRecvBankCode());
		lcInputBean.setRecvBankAccountNo(lcDto.getRecvBankAccountNo());
		if ("c".equalsIgnoreCase(lcDto.getRecvAccountType())) {
			lcInputBean.setRecvAccountType(AccountPropertyType.PERSONAL);
		} else if ("b".equalsIgnoreCase(lcDto.getRecvAccountType())) {
			lcInputBean.setRecvAccountType(AccountPropertyType.CORPORATE);
		}
		lcInputBean.setRecvMobile(lcDto.getRecvMobile());

		// lcInputBean.setReturnUrl(lcDto.getReturnUrl());
		// lcInputBean.setNoticeUrl(lcDto.getNoticeUrl());
		lcInputBean.setMerOrderUrl(lcDto.getMerOrderUrl());

		lcInputBean.setRemark(lcDto.getRemark());

		return lcInputBean;
	}

	public static List<BatchInitLcInputDto> ToBatchInitLcInputDtoList(List<InitLcInputDto> lcDtoList) {
		if (lcDtoList == null || lcDtoList.isEmpty()) {
			return new ArrayList<BatchInitLcInputDto>();
		}

		List<BatchInitLcInputDto> result = new ArrayList<BatchInitLcInputDto>();

		for (InitLcInputDto lcDto : lcDtoList) {
			result.add(ToBatchInitLcInputDto(lcDto));
		}

		return result;
	}
}
