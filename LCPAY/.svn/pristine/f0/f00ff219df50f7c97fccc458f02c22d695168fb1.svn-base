package com.cifpay.lc.gateway.controller.lc;

import com.cifpay.lc.api.BusinessInput;
import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.api.gateway.lc.RecvLcService;
import com.cifpay.lc.constant.enums.AccountPropertyType;
import com.cifpay.lc.domain.lc.RecvLcInputBean;
import com.cifpay.lc.domain.lc.RecvLcOutputBean;
import com.cifpay.lc.domain.security.MerchantRequest;
import com.cifpay.lc.gateway.common.exception.GatewayLcException;
import com.cifpay.lc.gateway.controller.GatewayBaseController;
import com.cifpay.lc.gateway.input.lc.RecvReq;
import com.cifpay.lc.gateway.output.lc.RecvResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 收证
 *
 * @author sweet
 */
@RestController
@RequestMapping("/lc")
public class RecvLcController extends GatewayBaseController {

    @Autowired
    private RecvLcService recvLcService;

    @ResponseBody
    @RequestMapping("/recv")
    public RecvResp recv(@RequestBody MerchantRequest<RecvReq> merReq) {
        logger.debug("===进入recv");

        RecvReq request = merReq.getData();
        RecvLcInputBean inputBean = new RecvLcInputBean();
        inputBean.setLcId(request.getLcId());
        inputBean.setMerId(merReq.getMerId());
        inputBean.setRecvBankCode(request.getRecvBankCode());
        inputBean.setRecvBankAccountNo(request.getRecvBankAccountNo());
        inputBean.setRecvAccountType(request.getRecvAccountType().equalsIgnoreCase("c") ? AccountPropertyType.PERSONAL : AccountPropertyType.CORPORATE);
        inputBean.setRemark(request.getRemark());

        BusinessOutput<RecvLcOutputBean> output = recvLcService.execute(new BusinessInput<RecvLcInputBean>(inputBean));

        if (output.isSuccess()) {
            RecvLcOutputBean outputBean = output.getData();

            RecvResp response = new RecvResp();
            response.setLcId(Long.toString(outputBean.getLcId()));
            response.setLcStatus(outputBean.getLcStatus());
            response.setLcStatusDesc(outputBean.getLcStatusDesc());
            return response;
        }

        throw new GatewayLcException(output.getReturnCode(), output.getReturnMsg());
    }
}
