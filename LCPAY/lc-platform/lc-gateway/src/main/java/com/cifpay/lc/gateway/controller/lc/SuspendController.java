package com.cifpay.lc.gateway.controller.lc;

import com.cifpay.lc.api.BusinessInput;
import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.api.gateway.lc.SuspendLcService;
import com.cifpay.lc.domain.lc.SuspendLcInputBean;
import com.cifpay.lc.domain.lc.SuspendLcOutputBean;
import com.cifpay.lc.domain.security.MerchantRequest;
import com.cifpay.lc.gateway.common.exception.GatewayLcException;
import com.cifpay.lc.gateway.controller.GatewayBaseController;
import com.cifpay.lc.gateway.input.lc.SuspendReq;
import com.cifpay.lc.gateway.output.lc.SuspendResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lc")
public class SuspendController extends GatewayBaseController {

    @Autowired
    private SuspendLcService suspendLcService;

    @ResponseBody
    @RequestMapping("/suspend")
    public SuspendResp handleRequest(@RequestBody MerchantRequest<SuspendReq> merReq) {
        logger.debug("~~~进入suspend");

        SuspendReq reqBean = merReq.getData();

        String strMerId = merReq.getMerId();
        String strRemark = reqBean.getRemark();

        SuspendLcInputBean inputBean = new SuspendLcInputBean();
        inputBean.setLcId(reqBean.getLcId());
        inputBean.setLcConfirmId(reqBean.getApplyId());
        inputBean.setMerId(strMerId);
        inputBean.setRemark(strRemark);

        BusinessOutput<SuspendLcOutputBean> output = suspendLcService.execute(new BusinessInput<SuspendLcInputBean>(inputBean));

        if (output.isSuccess()) {
            SuspendLcOutputBean outputBean = output.getData();

            SuspendResp response = new SuspendResp();
            response.setLcId(String.valueOf(outputBean.getLcId()));
            response.setLcStatus(outputBean.getLcStatus());
            response.setLcStatusDesc(outputBean.getLcStatusDesc());
            return response;
        }
        throw new GatewayLcException(output.getReturnCode(), output.getReturnMsg());
    }
}
