package com.cifpay.lc.gateway.controller.lc;

import com.cifpay.lc.api.BusinessInput;
import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.api.gateway.lc.InitLcService;
import com.cifpay.lc.api.gateway.lc.OpenLcService;
import com.cifpay.lc.constant.BizConstants;
import com.cifpay.lc.constant.enums.AccountPropertyType;
import com.cifpay.lc.constant.enums.OpenChannel;
import com.cifpay.lc.domain.lc.InitLcInputBean;
import com.cifpay.lc.domain.lc.InitLcOutputBean;
import com.cifpay.lc.domain.lc.OpenLcInputBean;
import com.cifpay.lc.domain.lc.OpenLcOutputBean;
import com.cifpay.lc.domain.security.MerchantRequest;
import com.cifpay.lc.gateway.common.exception.GatewayLcException;
import com.cifpay.lc.gateway.controller.GatewayBaseController;
import com.cifpay.lc.gateway.input.lc.InitReq;
import com.cifpay.lc.gateway.input.lc.OpenReq;
import com.cifpay.lc.gateway.output.lc.InitResp;
import com.cifpay.lc.gateway.output.lc.OpenResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 开证
 *
 * @author sweet
 */

@RestController
@RequestMapping("/lc")
public class OpenLcController extends GatewayBaseController {

    @Autowired
    private InitLcService initLcService;

    @Autowired
    private OpenLcService openLcService;

    @ResponseBody
    @RequestMapping("/init")
    public InitResp init(@RequestBody MerchantRequest<InitReq> merReq) {
        logger.debug("===进入init");

        InitReq request = merReq.getData();

        InitLcInputBean inputBean = new InitLcInputBean();
        inputBean.setMerId(merReq.getMerId());
        inputBean.setProductCode(request.getProductCode());
        inputBean.setOrderId(request.getOrderId());
        inputBean.setOrderContent(request.getOrderContent());

        inputBean.setAmount(request.getAmount());
        inputBean.setCurrency(request.getCurrency());
        inputBean.setOpenChannel(OpenChannel.parse(request.getChannel()));

        inputBean.setOpenValidSecond(request.getOpenValidSecond());
        inputBean.setRecvValidSecond(request.getRecvValidSecond());
        inputBean.setSendValidSecond(request.getSendValidSecond());
        inputBean.setConfirmValidSecond(request.getConfirmValidSecond());

        inputBean.setPayerBankCode(request.getPayerBankCode());
        inputBean.setPayerBankAccountNo(request.getPayerBankAccountNo());
        inputBean.setPayerMobile(request.getPayerMobile());
        if ("c".equalsIgnoreCase(request.getPayerAccountType())) {
            inputBean.setPayerAccountType(AccountPropertyType.PERSONAL);
        } else if ("b".equalsIgnoreCase(request.getPayerAccountType())) {
            inputBean.setPayerAccountType(AccountPropertyType.CORPORATE);
        }

        inputBean.setReturnUrl(request.getReturnUrl());
        inputBean.setNoticeUrl(request.getNoticeUrl());
        inputBean.setMrchOrderUrl(request.getMerOrderUrl());

        inputBean.setRemark(request.getRemark());

        // 执行业务逻辑
        BusinessOutput<InitLcOutputBean> output = initLcService.execute(new BusinessInput<InitLcInputBean>(inputBean));

        if (output.isSuccess() && output.getData() != null) {
            InitLcOutputBean outputBean = output.getData();

            InitResp response = new InitResp();
            response.setLcId(String.valueOf(outputBean.getLcId()));
            response.setLcStatus(String.valueOf(outputBean.getLcStatus()));
            response.setLcStatusDesc(outputBean.getLcStatusDesc());
            response.setLcNo(outputBean.getLcNo());
            response.setLcType(outputBean.getLcType());

            response.setLcAmount(BizConstants.decimalFormat.format(outputBean.getLcAmount()));
            response.setCurrency(outputBean.getCurrency());

            response.setPayerBankName(outputBean.getPayerBankName());
            response.setPayerBankCode(outputBean.getPayerBankCode());
            response.setPayerBankAccountNo(outputBean.getPayerBankAccountNo());

            response.setRecvBankName(outputBean.getRecvBankName());
            response.setRecvBankCode(outputBean.getRecvBankCode());
            response.setRecvBankAccountNo(outputBean.getRecvBankAccountNo());

            response.setOrderId(outputBean.getOrderId());
            response.setMrchOrderUrl(outputBean.getMrchOrderUrl());

            response.setRecvValidTime(outputBean.getRecvValidTime());
            response.setSendValidTime(outputBean.getSendValidTime());
            response.setConfirmPayValidTime(outputBean.getConfirmPayValidTime());
            response.setPayValidTime(outputBean.getPayValidTime());
            return response;
        }

        throw new GatewayLcException(output.getReturnCode(), output.getReturnMsg());
    }

    @ResponseBody
    @RequestMapping("/open")
    public OpenResp open(@RequestBody MerchantRequest<OpenReq> merReq) {

        OpenReq req = merReq.getData();
        OpenLcInputBean inputBean = new OpenLcInputBean();
        inputBean.setLcId(req.getLcId());
        inputBean.setMerId(merReq.getMerId());
        inputBean.setRemark(req.getRemark());

        // 执行开证逻辑
        BusinessOutput<OpenLcOutputBean> output = openLcService.execute(new BusinessInput<OpenLcInputBean>(inputBean));

        if (output.isSuccess()) {
            OpenLcOutputBean outputBean = output.getData();
            logger.debug("===获取结果参数outputBean,{}", outputBean);

            OpenResp response = new OpenResp();
            response.setLcId(Long.toString(outputBean.getLcId()));
            response.setOrderId(outputBean.getOrderId());
            response.setLcAmount(BizConstants.decimalFormat.format(outputBean.getLcAmount()));

            response.setChannel(outputBean.getPayMethod().getCode());
            response.setSerialNo(outputBean.getSerialNo());
            // 跳转到商户回调地址
            return response;
        }

        throw new GatewayLcException(output.getReturnCode(), output.getReturnMsg());
    }

}