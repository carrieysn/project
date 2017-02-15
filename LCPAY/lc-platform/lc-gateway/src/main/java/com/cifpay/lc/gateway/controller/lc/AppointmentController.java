package com.cifpay.lc.gateway.controller.lc;

import com.cifpay.lc.api.BusinessInput;
import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.api.gateway.lc.AppointmentService;
import com.cifpay.lc.constant.BizConstants;
import com.cifpay.lc.domain.lc.AppointmentInputBean;
import com.cifpay.lc.domain.lc.AppointmentOutputBean;
import com.cifpay.lc.domain.security.MerchantRequest;
import com.cifpay.lc.gateway.common.exception.GatewayLcException;
import com.cifpay.lc.gateway.controller.GatewayBaseController;
import com.cifpay.lc.gateway.input.lc.AppointmentReq;
import com.cifpay.lc.gateway.output.lc.AppointmentResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 履约
 *
 * @author liwenfu
 */
@RestController
@RequestMapping("/lc")
public class AppointmentController extends GatewayBaseController {

    @Autowired
    private AppointmentService appointmentService;

    @RequestMapping("/appointment")
    @ResponseBody
    public AppointmentResp handleRequest(@RequestBody MerchantRequest<AppointmentReq> merReq) {

        logger.debug("~~~进入AppointmentController.handleRequest");

        AppointmentReq reqBean = merReq.getData();

        String strMerId = merReq.getMerId();
        String strOrderId = reqBean.getOrderId();
        String strSendSignCode = reqBean.getSendEvidence();
        String strSendOrgId = reqBean.getThirdPartyCode();
        String strSendOrderId = reqBean.getThirdPartyOrderId();
        String strRemark = reqBean.getRemark();

        AppointmentInputBean inputBean = new AppointmentInputBean();

        inputBean.setLcId(reqBean.getLcId());
        inputBean.setMerId(strMerId);
        inputBean.setOrderId(strOrderId);
        inputBean.setOrderAmount(reqBean.getAmount());
        inputBean.setOrderContent(reqBean.getOrderContent());
        inputBean.setSendSignCode(strSendSignCode);
        inputBean.setSendOrgId(strSendOrgId);
        inputBean.setSendOrderId(strSendOrderId);
        inputBean.setRemark(strRemark);

        BusinessOutput<AppointmentOutputBean> output = appointmentService.execute(new BusinessInput<AppointmentInputBean>(inputBean));

        if (output.isSuccess()) {
            AppointmentOutputBean outputBean = output.getData();

            AppointmentResp response = new AppointmentResp();
            response.setLcId(String.valueOf(outputBean.getLcId()));
            response.setLcStatus(outputBean.getLcStatus());
            response.setLcStatusDesc(outputBean.getLcStatusDesc());
            response.setAppointmentId(String.valueOf(outputBean.getAppointmentId()));
            response.setAppointmentAmount(BizConstants.decimalFormat.format(outputBean.getAppointmentAmount()));
            return response;
        }

        throw new GatewayLcException(output.getReturnCode(), output.getReturnMsg());
    }

}
