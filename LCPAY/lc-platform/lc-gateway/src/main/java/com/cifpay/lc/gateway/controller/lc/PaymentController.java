package com.cifpay.lc.gateway.controller.lc;

import com.cifpay.lc.api.BusinessInput;
import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.api.gateway.lc.InitLcService;
import com.cifpay.lc.constant.enums.AccountPropertyType;
import com.cifpay.lc.constant.enums.OpenChannel;
import com.cifpay.lc.domain.lc.InitLcInputBean;
import com.cifpay.lc.domain.lc.InitLcOutputBean;
import com.cifpay.lc.domain.security.MerchantRequest;
import com.cifpay.lc.gateway.common.exception.GatewayUnionException;
import com.cifpay.lc.gateway.controller.GatewayBaseController;
import com.cifpay.lc.gateway.input.lc.InitReq;
import com.cifpay.lc.gateway.integration.advice.MerchantFormRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/lc")
public class PaymentController extends GatewayBaseController {

    @Autowired
    private InitLcService initLcService;

    /**
     * 付款入口
     * 执行“预开证”操作，然后跳转到对应付款页面
     *
     * @param merReq
     * @return
     */
    @RequestMapping("/payment")
    public ModelAndView payment(@MerchantFormRequest MerchantRequest<InitReq> merReq) {
        logger.debug("===进入 payment");

        InitReq request = merReq.getData();

        InitLcInputBean inputBean = new InitLcInputBean();
        inputBean.setMerId(merReq.getMerId());
        inputBean.setProductCode(request.getProductCode());

        inputBean.setOrderId(request.getOrderId());
        inputBean.setOrderContent(request.getOrderContent());
        inputBean.setMerUserId(request.getMerUserId());

        inputBean.setAmount(request.getAmount());
        inputBean.setCurrency(request.getCurrency());
        inputBean.setOpenChannel(OpenChannel.parse(request.getChannel()));

        inputBean.setPayerBankCode(request.getPayerBankCode());
        inputBean.setPayerBankAccountNo(request.getPayerBankAccountNo());
        inputBean.setPayerMobile(request.getPayerMobile());
        if ("c".equalsIgnoreCase(request.getPayerAccountType())) {
            inputBean.setPayerAccountType(AccountPropertyType.PERSONAL);
        } else if ("b".equalsIgnoreCase(request.getPayerAccountType())) {
            inputBean.setPayerAccountType(AccountPropertyType.CORPORATE);
        }

        inputBean.setOpenValidSecond(request.getOpenValidSecond());
        inputBean.setRecvValidSecond(request.getRecvValidSecond());
        inputBean.setSendValidSecond(request.getSendValidSecond());
        inputBean.setConfirmValidSecond(request.getConfirmValidSecond());

        inputBean.setReturnUrl(request.getReturnUrl());
        inputBean.setNoticeUrl(request.getNoticeUrl());
        inputBean.setMrchOrderUrl(request.getMerOrderUrl());

        inputBean.setRemark(request.getRemark());

        // 执行业务逻辑
        BusinessOutput<InitLcOutputBean> output = initLcService.execute(new BusinessInput<InitLcInputBean>(inputBean));

        if (output.isSuccess() && output.getData() != null) {
            // 银联付款
            if (OpenChannel.UNION.getBankCode().compareToIgnoreCase(request.getChannel()) == 0) {
                return new ModelAndView("redirect:/bank/unionpay/" + output.getData().getLcId());
            }

//            if (OpenChannel.BANK.getCode().compareToIgnoreCase(request.getChannel()) == 0) {
//
//                return new ModelAndView("redirect:/bank/unionpay/" + output.getData().getLcId());
//            }


            throw new GatewayUnionException("不支持的支付方式：" + request.getChannel());
        }

        throw new GatewayUnionException(output.getReturnMsg());
    }

}
