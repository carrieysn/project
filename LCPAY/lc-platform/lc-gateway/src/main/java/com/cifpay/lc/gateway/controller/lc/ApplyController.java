package com.cifpay.lc.gateway.controller.lc;

import com.cifpay.lc.api.BusinessInput;
import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.api.gateway.lc.ApplyService;
import com.cifpay.lc.constant.BizConstants;
import com.cifpay.lc.domain.lc.ApplyInputBean;
import com.cifpay.lc.domain.lc.ApplyOutputBean;
import com.cifpay.lc.domain.security.MerchantRequest;
import com.cifpay.lc.gateway.common.exception.GatewayLcException;
import com.cifpay.lc.gateway.controller.GatewayBaseController;
import com.cifpay.lc.gateway.input.lc.ApplyReq;
import com.cifpay.lc.gateway.output.lc.ApplyResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 申请解付
 *
 * @author liwenfu
 */
@RestController
@RequestMapping("/lc")
public class ApplyController extends GatewayBaseController {

    @Autowired
    private ApplyService applyService;

    @ResponseBody
    @RequestMapping("/apply")
    public ApplyResp handleRequest(@RequestBody MerchantRequest<ApplyReq> merReq) {
        logger.debug("~~~进入apply");

        ApplyReq reqBean = merReq.getData();

        String strMerId = merReq.getMerId();
        String strSignCode = reqBean.getSignCode();
        String strRemark = reqBean.getRemark();

        ApplyInputBean inputBean = new ApplyInputBean();
        inputBean.setLcId(reqBean.getLcId());
        inputBean.setMerId(strMerId);
        inputBean.setLcAppointmentId(reqBean.getAppointmentId());
        inputBean.setSignCode(strSignCode);
        inputBean.setRemark(strRemark);
        BusinessOutput<ApplyOutputBean> output = applyService.execute(new BusinessInput<ApplyInputBean>(inputBean));

        if (output.isSuccess()) {
            ApplyOutputBean outputBean = output.getData();

            ApplyResp response = new ApplyResp();
            response.setLcId(String.valueOf(outputBean.getLcId()));
            response.setLcConfirmId(String.valueOf(outputBean.getLcConfirmId()));
            response.setLcStatus(outputBean.getLcStatus());
            response.setLcStatusDesc(outputBean.getLcStatusDesc());
            response.setAmount(BizConstants.decimalFormat.format(outputBean.getLcPayAmount()));

            return response;
        }

        throw new GatewayLcException(output.getReturnCode(), output.getReturnMsg());
    }
}
