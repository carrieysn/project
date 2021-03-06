package com.cifpay.lc.gateway.controller.lc;

import com.cifpay.lc.api.BusinessInput;
import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.api.gateway.lc.DeferLcService;
import com.cifpay.lc.constant.BizConstants;
import com.cifpay.lc.domain.lc.DeferLcInputBean;
import com.cifpay.lc.domain.lc.DeferLcOutputBean;
import com.cifpay.lc.domain.security.MerchantRequest;
import com.cifpay.lc.gateway.common.exception.GatewayLcException;
import com.cifpay.lc.gateway.controller.GatewayBaseController;
import com.cifpay.lc.gateway.input.lc.DeferReq;
import com.cifpay.lc.gateway.output.lc.DeferResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;

/**
 * 展期
 */
@RestController
@RequestMapping("/lc")
public class DeferController extends GatewayBaseController {

    @Autowired
    private DeferLcService deferLcService;

    @ResponseBody
    @RequestMapping("/defer")
    public DeferResp handleRequest(@RequestBody MerchantRequest<DeferReq> merReq) {
        logger.debug("~~~进入defer");

        DeferReq reqBean = merReq.getData();

        String strMerId = merReq.getMerId();
        String strRemark = reqBean.getRemark();

        DeferLcInputBean inputBean = new DeferLcInputBean();

        inputBean.setMerId(strMerId);
        inputBean.setLcId(reqBean.getLcId());
        inputBean.setRemark(strRemark);

        BusinessOutput<DeferLcOutputBean> output = deferLcService.execute(new BusinessInput<DeferLcInputBean>(inputBean));

        if (output.isSuccess()) {
            DeferLcOutputBean outputBean = output.getData();

            DeferResp response = new DeferResp();
            response.setLcId(String.valueOf(outputBean.getLcId()));
            response.setLcStatus(outputBean.getLcStatus());
            response.setLcStatusDesc(outputBean.getLcStatusDesc());
            SimpleDateFormat sdf = new SimpleDateFormat(BizConstants.DateFormat_std);
            response.setApplyValidDate(sdf.format(outputBean.getConfirmValidTime()));
            return response;
        }

        throw new GatewayLcException(output.getReturnCode(), output.getReturnMsg());
    }
}
