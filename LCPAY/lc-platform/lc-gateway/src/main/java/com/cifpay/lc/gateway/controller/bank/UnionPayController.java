package com.cifpay.lc.gateway.controller.bank;

import com.cifpay.lc.api.BusinessInput;
import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.api.gateway.bank.OpenCardService;
import com.cifpay.lc.api.gateway.basic.signkey.MerFrontValidationMaterial;
import com.cifpay.lc.api.gateway.lc.OpenLcService;
import com.cifpay.lc.api.gateway.query.LcQueryService;
import com.cifpay.lc.api.gateway.sms.SmsSendMessageService;
import com.cifpay.lc.api.gateway.sms.SmsSendUnionService;
import com.cifpay.lc.api.gateway.union.MerInfoService;
import com.cifpay.lc.api.security.SecurityService;
import com.cifpay.lc.bankadapter.api.IBankTradeService;
import com.cifpay.lc.bankadapter.api.constant.TradeConstant;
import com.cifpay.lc.constant.BizConstants;
import com.cifpay.lc.constant.ResultHandler;
import com.cifpay.lc.constant.enums.SmsType;
import com.cifpay.lc.domain.bank.OpenCardInputBean;
import com.cifpay.lc.domain.enums.AdminCardType;
import com.cifpay.lc.domain.lc.OpenLcInputBean;
import com.cifpay.lc.domain.lc.OpenLcOutputBean;
import com.cifpay.lc.domain.lc.OpenLcUnionCreditInputBean;
import com.cifpay.lc.domain.lc.OpenLcUnionDepositInputBean;
import com.cifpay.lc.domain.message.SmsParamBean;
import com.cifpay.lc.domain.query.PreLcDto;
import com.cifpay.lc.domain.query.UnionUserAccountDto;
import com.cifpay.lc.domain.security.MerchantSignedResponse;
import com.cifpay.lc.domain.sms.SmsSendOutputBean;
import com.cifpay.lc.domain.sms.SmsSendUnionInputBean;
import com.cifpay.lc.domain.sms.SmsSendUnionOutputBean;
import com.cifpay.lc.gateway.common.MerchantSummaryInfoCache;
import com.cifpay.lc.gateway.common.exception.GatewayUnionException;
import com.cifpay.lc.gateway.controller.GatewayBaseController;
import com.cifpay.lc.gateway.input.bank.CreditReq;
import com.cifpay.lc.gateway.input.bank.DepositOpenCardReq;
import com.cifpay.lc.gateway.input.bank.DepositReq;
import com.cifpay.lc.gateway.input.bank.SendVerifyCodeReq;
import com.cifpay.lc.gateway.output.AjaxResponse;
import com.cifpay.lc.gateway.output.lc.OpenResp;
import com.cifpay.lc.util.StringUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 银联付款页面
 *
 * @author sweet
 */
@Controller
@RequestMapping(path = {"/bank/unionpay"})
public class UnionPayController extends GatewayBaseController {

    @Autowired
    private LcQueryService lcQueryService;

    @Autowired
    private OpenLcService openLcService;

    @Autowired
    private MerchantSummaryInfoCache merSummaryInfoCache;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private MerInfoService merInfoService;

    @Autowired
    private SmsSendUnionService smsSendUnionService;

    @Autowired
    private OpenCardService openCardService;

    private final static DecimalFormat decimalFormat = new DecimalFormat("0.00");
    private final static String SESSION_MID = "Session_mid";
    private final static String SESSION_CREDIT_ACCOUNT_NO = "Session_creditAccountNo";
    private final static String SESSION_CREDIT_MOBILE_NO = "Session_creditMobileNo";
    private final static String SESSION_DEPOSIT_ACCOUNT_NO = "Session_depositAccountNo";
    private final static String SESSION_DEPOSIT_MOBILE_NO = "Session_depositMobileNo";
    private final static String SESSION_DEPOSIT_ORDER_ID = "Session_depositOrderId";

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/yy"), true));
    }

    /**
     * 付款页面
     *
     * @param lcId      银信证Id
     * @param pageModel
     * @return
     */
    @RequestMapping(path = "/{lcId}", method = {RequestMethod.GET})
    public String paymentEntry(@PathVariable long lcId, HttpSession httpSession, Model pageModel) {

        logger.debug("===进入payment");

        //查询预开证信息
        ResultHandler<PreLcDto> selectResult = lcQueryService.selectPreLcByLcId(lcId);

        if (!selectResult.isSuccess()) {

            logger.debug(selectResult.getMessage());

            throw new GatewayUnionException(selectResult.getMessage());
        }

        PreLcDto preLcDto = selectResult.getData();

        //
        UnionUserAccountDto creditAccount = merInfoService.findById(preLcDto.getMid(), preLcDto.getMerUserId(),
                AdminCardType.CREDIT.getCode());

        //
        UnionUserAccountDto depositAccount = merInfoService.findById(preLcDto.getMid(), preLcDto.getMerUserId(),
                AdminCardType.DEPOSIT.getCode());

        //
        MerFrontValidationMaterial merInfo = merSummaryInfoCache.getMerchantSummary(preLcDto.getMid());

        pageModel.addAttribute("preLcDto", preLcDto);
        pageModel.addAttribute("merchantName", merInfo.getMerchantName());
        pageModel.addAttribute("amount", decimalFormat.format(preLcDto.getLcAmount().divide(BigDecimal.valueOf(100))));
        pageModel.addAttribute("now", new Date());

        // --------- 2016/10/18-------------- //
        if (creditAccount != null) {
            pageModel.addAttribute("creditAccountNoDisplay", getPettyDisplayNo(creditAccount.getPayerAccno(), 0, 4));
            pageModel.addAttribute("creditMobileNoDisplay", getPettyDisplayNo(creditAccount.getUserMobile(), 3, 4));

            httpSession.setAttribute(SESSION_CREDIT_ACCOUNT_NO, creditAccount.getPayerAccno());
            httpSession.setAttribute(SESSION_CREDIT_MOBILE_NO, creditAccount.getUserMobile());
        }

        if (depositAccount != null) {
            pageModel.addAttribute("depositAccountNoDisplay", getPettyDisplayNo(depositAccount.getPayerAccno(), 0, 4));
            pageModel.addAttribute("depositMobileNoDisplay", getPettyDisplayNo(depositAccount.getUserMobile(), 3, 4));

            httpSession.setAttribute(SESSION_DEPOSIT_ACCOUNT_NO, depositAccount.getPayerAccno());
            httpSession.setAttribute(SESSION_DEPOSIT_MOBILE_NO, depositAccount.getUserMobile());
        }

        httpSession.setAttribute(SESSION_MID, preLcDto.getMid());

        //返回银联支付页面
        logger.debug("===payment:成功返回");

        return "bank/payment_union";
    }

    /**
     * 信用卡付款
     *
     * @param creditReq
     * @param pageModel
     * @return
     */
    @RequestMapping(path = "/credit", method = RequestMethod.POST)
    public String credit(CreditReq creditReq, HttpSession httpSession, Model pageModel) throws JsonProcessingException {

        logger.debug("===进入 credit");

        Long lcId = creditReq.getLcId();
        String mid = (String) httpSession.getAttribute(SESSION_MID);
        String accountNo = (String) httpSession.getAttribute(SESSION_CREDIT_ACCOUNT_NO);
        String mobileNo = (String) httpSession.getAttribute(SESSION_CREDIT_MOBILE_NO);

        {
            if (!StringUtils.isEmpty(creditReq.getAccountNo()) && !creditReq.getAccountNo().contains("*")) {
                accountNo = creditReq.getAccountNo();
            }
            if (!StringUtils.isEmpty(creditReq.getMobileNo()) && !creditReq.getMobileNo().contains("*")) {
                mobileNo = creditReq.getMobileNo();
            }
        }
        if (StringUtils.isEmpty(accountNo)) {
            throw new GatewayUnionException("请输入正确的银行卡号码");
        }

        if (StringUtils.isEmpty(mobileNo) || !mobileNo.matches("^\\d{11}$")) {
            throw new GatewayUnionException("请输入正确的手机号码");
        }

        if (!StringUtil.isDigit(creditReq.getCvv2())) {
            throw new GatewayUnionException("请输入正确的银行卡验证码");
        }

        //验证短信验证码
        OpenLcUnionCreditInputBean openLcInputBean = new OpenLcUnionCreditInputBean();
        openLcInputBean.setMerId(mid);
        openLcInputBean.setLcId(lcId);

        openLcInputBean.setAccNo(accountNo);
        openLcInputBean.setCvn2(creditReq.getCvv2());
        openLcInputBean.setSmsCode(creditReq.getSmsCode());
        openLcInputBean.setExpired(creditReq.getExpiryDate());
        openLcInputBean.setPhoneNo(mobileNo);

        BusinessOutput<OpenLcOutputBean> businessOutput = openLcService.execute(new BusinessInput<OpenLcInputBean>(openLcInputBean));

        if (!businessOutput.isSuccess()) {

            logger.info("=== credit:" + businessOutput.getReturnMsg());

            throw new GatewayUnionException(businessOutput.getReturnCode(), businessOutput.getReturnMsg());
        }

        OpenLcOutputBean outputBean = businessOutput.getData();

        //将返回参数加密
        OpenResp response = new OpenResp();
        response.setLcId(Long.toString(outputBean.getLcId()));
        response.setOrderId(outputBean.getOrderId());
        response.setChannel(outputBean.getPayMethod().getCode());
        response.setSerialNo(outputBean.getSerialNo());
        response.setLcAmount(BizConstants.decimalFormat.format(outputBean.getLcAmount()));

        // 加密并返回
        Map map = new ObjectMapper().convertValue(response, Map.class);
        BusinessOutput<MerchantSignedResponse> encryptResponse = securityService.encryptData(mid, map);

        if (encryptResponse.isFailed()) {
            throw new GatewayUnionException(businessOutput.getReturnCode(), encryptResponse.getReturnMsg());
        }

        pageModel.addAttribute("returnCode", encryptResponse.getReturnCode());
        pageModel.addAttribute("returnMsg", encryptResponse.getReturnMsg());
        pageModel.addAttribute("encryptResponse", encryptResponse.getData());
        pageModel.addAttribute("returnUrl", outputBean.getReturnUrl());

        //返回商户returnUrl
        logger.debug("=== credit:成功返回");

        return "bank/paymentSuccess";
    }

    /**
     * 储蓄卡预授权
     *
     * @param depositOpenCardReq
     * @param resp
     * @return
     * @throws Exception
     */
    @RequestMapping(path = "/opencard", method = RequestMethod.POST)
    public void openCard(DepositOpenCardReq depositOpenCardReq, HttpSession httpSession, HttpServletResponse resp) throws Exception {

        OpenCardInputBean param = new OpenCardInputBean();

        String mid = (String) httpSession.getAttribute(SESSION_MID);

        param.setLcId(new Date().getTime());
        param.setTxnId(TradeConstant.TRADE_CONFIG.TRADE_UNIONPAY_WU_OPEN_CARD);//无跳转全渠道开通支付
        param.setTxnType("79");
        param.setTxnSubType("00");
        param.setLcId(depositOpenCardReq.getLcId());
        param.setOrderId(String.valueOf(depositOpenCardReq.getLcId()));
        param.setMerId(mid);
        param.setAccNo(depositOpenCardReq.getAccountNo());
        param.setTxnTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        param.setChannelType("07");

        String html = openCardService.openCard(param);

        if (html != null) {
            resp.getWriter().write(html);
        }
    }

    /**
     * 储蓄卡付款
     *
     * @param depositReq
     * @param pageModel
     * @return
     */
    @RequestMapping(path = "/deposit", method = RequestMethod.POST)
    public String deposit(DepositReq depositReq, HttpSession httpSession, Model pageModel) throws JsonProcessingException {
        logger.debug("===进入 deposit");

        Long lcId = depositReq.getLcId();

        String mid = (String) httpSession.getAttribute(SESSION_MID);
        String accountNo = (String) httpSession.getAttribute(SESSION_DEPOSIT_ACCOUNT_NO);
        String mobileNo = (String) httpSession.getAttribute(SESSION_DEPOSIT_MOBILE_NO);
        String smsCode = depositReq.getSmsCode();
        Long lcOpenId = (Long) httpSession.getAttribute(SESSION_DEPOSIT_ORDER_ID);

        if (StringUtils.isEmpty(accountNo)) {
            throw new GatewayUnionException("请输入正确的银行卡号码");
        }
        if (StringUtils.isEmpty(mobileNo) || !mobileNo.matches("^\\d{11}$")) {
            throw new GatewayUnionException("请输入正确的手机号码");
        }
        if (StringUtils.isEmpty(smsCode)) {
            throw new GatewayUnionException("请输入正确的短信验证码");
        }
        if (lcOpenId == null || lcOpenId == 0) {
            throw new GatewayUnionException("短信验证码已过期，请重新获取");
        }

        OpenLcUnionDepositInputBean openLcInputBean = new OpenLcUnionDepositInputBean();
        openLcInputBean.setLcId(lcId);
        openLcInputBean.setSmsCode(smsCode);
        openLcInputBean.setMerId(mid);
        openLcInputBean.setAccNo(accountNo);
        openLcInputBean.setPhoneNo(mobileNo);
        openLcInputBean.setLcOpenId(lcOpenId);

        BusinessOutput<OpenLcOutputBean> businessOutput = openLcService.execute(new BusinessInput<OpenLcInputBean>(openLcInputBean));

        if (!businessOutput.isSuccess()) {

            logger.info("=== deposit:" + businessOutput.getReturnMsg());

            throw new GatewayUnionException(businessOutput.getReturnCode(), businessOutput.getReturnMsg());
        }

        OpenLcOutputBean outputBean = businessOutput.getData();

        //将返回参数加密
        OpenResp response = new OpenResp();
        response.setLcId(Long.toString(outputBean.getLcId()));
        response.setOrderId(outputBean.getOrderId());
        response.setLcAmount(BizConstants.decimalFormat.format(outputBean.getLcAmount()));

        // 加密并返回
        Map map = new ObjectMapper().convertValue(response, Map.class);
        BusinessOutput<MerchantSignedResponse> encryptResponse = securityService.encryptData(mid, map);

        if (encryptResponse.isFailed()) {
            throw new GatewayUnionException(businessOutput.getReturnCode(), encryptResponse.getReturnMsg());
        }

        pageModel.addAttribute("returnCode", encryptResponse.getReturnCode());
        pageModel.addAttribute("returnMsg", encryptResponse.getReturnMsg());
        pageModel.addAttribute("encryptResponse", encryptResponse.getData());
        pageModel.addAttribute("returnUrl", outputBean.getReturnUrl());

        //返回商户returnUrl
        logger.debug("=== deposit:成功返回");

        return "bank/paymentSuccess";
    }

    /**
     * 发送银联短信验证码
     *
     * @return
     */
    @RequestMapping(path = "/sendVerifyCode", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse sendVerifyCode(@RequestBody SendVerifyCodeReq req, HttpSession httpSession) {

        SmsSendUnionInputBean sendUnionInputBean = new SmsSendUnionInputBean();

        if ("credit".compareToIgnoreCase(req.getCardType()) == 0) {
            String mobileNo = (String) httpSession.getAttribute(SESSION_CREDIT_MOBILE_NO);
            String accountNo = (String) httpSession.getAttribute(SESSION_CREDIT_ACCOUNT_NO);
            if (!StringUtils.isEmpty(req.getMobileNo()) && !req.getMobileNo().contains("*")) {
                mobileNo = req.getMobileNo();
            }

            sendUnionInputBean.setLcId(req.getLcId());
            sendUnionInputBean.setPhoneNo(mobileNo);
            sendUnionInputBean.setAccNo(accountNo);
            sendUnionInputBean.setCardType(AdminCardType.CREDIT);

        } else if ("deposit".compareToIgnoreCase(req.getCardType()) == 0) {
            String mobileNo = (String) httpSession.getAttribute(SESSION_DEPOSIT_MOBILE_NO);
            String accountNo = (String) httpSession.getAttribute(SESSION_DEPOSIT_ACCOUNT_NO);

            sendUnionInputBean.setLcId(req.getLcId());
            sendUnionInputBean.setPhoneNo(mobileNo);
            sendUnionInputBean.setAccNo(accountNo);
            sendUnionInputBean.setCardType(AdminCardType.DEPOSIT);
        }

        BusinessOutput<SmsSendUnionOutputBean> businessOutput = smsSendUnionService.execute(new BusinessInput<>(sendUnionInputBean));

        if (businessOutput.isSuccess()) {
            httpSession.setAttribute(SESSION_DEPOSIT_ORDER_ID, businessOutput.getData().getLcOpenId());
            return AjaxResponse.success();
        }

        return AjaxResponse.fail(businessOutput.getReturnMsg());
    }

    private String getPettyDisplayNo(String accountNo, int before, int last) {
        if (accountNo == null) {
            return null;
        }

        before = Math.max(0, before);
        last = Math.max(0, last);
        int len = accountNo.length();
        int maskLen = Math.max(0, len - before - last);

        StringBuilder sbPettyAccountNo = new StringBuilder();
        sbPettyAccountNo.append(accountNo.substring(0, Math.min(len, before)));
        sbPettyAccountNo.append(Strings.repeat("*", maskLen));
        sbPettyAccountNo.append(accountNo.substring(Math.max(0, Math.min(len, before + maskLen))));

        return sbPettyAccountNo.toString();
    }
}
