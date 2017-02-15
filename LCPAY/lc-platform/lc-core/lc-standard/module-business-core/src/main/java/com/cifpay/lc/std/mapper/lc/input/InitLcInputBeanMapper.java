package com.cifpay.lc.std.mapper.lc.input;

import com.cifpay.lc.domain.lc.InitLcInputBean;
import com.cifpay.lc.std.domain.kernel.InitLcKernelInputBean;

public class InitLcInputBeanMapper {
    public static InitLcKernelInputBean toInitLcKernelInputBean(InitLcInputBean inputBean) {
        InitLcKernelInputBean result = new InitLcKernelInputBean();

        if (inputBean != null) {
            // BeanUtils.copyProperties(inputBean, result);

            // result.setBatchOpenId();

            result.setMerId(inputBean.getMerId());
            result.setProductCode(inputBean.getProductCode());
            result.setOrderId(inputBean.getOrderId());
            result.setOrderContent(inputBean.getOrderContent());

            result.setAmount(inputBean.getAmount());
            result.setCurrency(inputBean.getCurrency());
            result.setOpenChannel(inputBean.getOpenChannel());
            // result.setPayType();

            result.setOpenValidSecond(inputBean.getOpenValidSecond());
            result.setRecvValidSecond(inputBean.getRecvValidSecond());
            result.setSendValidSecond(inputBean.getSendValidSecond());
            result.setConfirmValidSecond(inputBean.getConfirmValidSecond());
            result.setPayValidSecond(inputBean.getPayValidSecond());

            result.setPayerBankCode(inputBean.getPayerBankCode());
            result.setPayerBankAccountNo(inputBean.getPayerBankAccountNo());
            result.setPayerAccountType(inputBean.getPayerAccountType());
            result.setPayerMobile(inputBean.getPayerMobile());

            result.setRecvBankCode(inputBean.getRecvBankCode());
            result.setRecvBankAccountNo(inputBean.getRecvBankAccountNo());
            result.setRecvAccountType(inputBean.getRecvAccountType());
            result.setRecvMobile(inputBean.getRecvMobile());

            result.setReturnUrl(inputBean.getReturnUrl());
            result.setNoticeUrl(inputBean.getNoticeUrl());
            result.setMrchOrderUrl(inputBean.getMrchOrderUrl());
            result.setMerUserId(inputBean.getMerUserId());

            result.setRemark(inputBean.getRemark());
        }

        return result;
    }

}
