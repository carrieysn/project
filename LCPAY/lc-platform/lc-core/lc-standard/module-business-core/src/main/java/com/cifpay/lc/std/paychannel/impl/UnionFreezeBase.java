package com.cifpay.lc.std.paychannel.impl;

import com.alibaba.fastjson.JSON;
import com.cifpay.lc.api.BusinessInput;
import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.api.gateway.sms.SmsValidateCodeService;
import com.cifpay.lc.api.gateway.union.MerInfoService;
import com.cifpay.lc.bankadapter.api.IBankTradeService;
import com.cifpay.lc.bankadapter.api.constant.TradeConfig;
import com.cifpay.lc.bankadapter.api.input.unionpay.OpenCifParam;
import com.cifpay.lc.bankadapter.api.output.GeneralTradeResult;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.constant.enums.LcTranStatus;
import com.cifpay.lc.constant.enums.PayHandler;
import com.cifpay.lc.constant.enums.SmsType;
import com.cifpay.lc.core.db.dao.AdminLcMerCreDao;
import com.cifpay.lc.core.db.pojo.AdminLcMerCre;
import com.cifpay.lc.core.exception.PaymentException;
import com.cifpay.lc.domain.enums.AdminCardType;
import com.cifpay.lc.domain.lc.OpenLcInputBean;
import com.cifpay.lc.domain.lc.OpenLcOutputBean;
import com.cifpay.lc.domain.message.SmsParamBean;
import com.cifpay.lc.domain.message.union.CreditParam;
import com.cifpay.lc.domain.query.UnionUserAccountDto;
import com.cifpay.lc.domain.sms.SmsSendOutputBean;
import com.cifpay.lc.std.domain.paychannel.FreezeInputBean;
import com.cifpay.lc.std.domain.paychannel.FreezeOutputBean;
import com.cifpay.lc.std.domain.paychannel.ReqReservedBean;
import com.cifpay.lc.std.mapper.LcTranStatusMapper;
import com.cifpay.lc.std.paychannel.FreezeInterface;
import com.cifpay.lc.std.util.BeanFactory;
import com.cifpay.lc.util.security.ThreeDESUtil;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class UnionFreezeBase<IOpen extends OpenLcInputBean, OOpen extends OpenLcOutputBean> extends AbstractUnion implements FreezeInterface<IOpen, OOpen> {

    @Autowired
    private SmsValidateCodeService smsValidateCodeService;

    @Autowired
    private MerInfoService merInfoService;

    @Autowired
    private IBankTradeService bankTradeService;

    protected abstract AdminCardType getCardType();

    /**
     * @param inputBean
     * @param freezeInputBean
     * @return
     * @throws Exception
     */
    protected abstract OpenCifParam createOpenCifParam(IOpen inputBean, FreezeInputBean freezeInputBean) throws PaymentException;

    /**
     * 开证模板方法
     */
    public FreezeOutputBean<CreditParam> freeze(IOpen inputBean, FreezeInputBean freezeInputBean) throws PaymentException {

        String merId = inputBean.getMerId();

        String phoneNo = inputBean.getPhoneNo();

        String smsCode = inputBean.getSmsCode();

        try {
            ReqReservedBean reqReservedBean = new ReqReservedBean();
            reqReservedBean.setPayHandler(PayHandler.FREEZE);
            reqReservedBean.setPayMethod(getPayMethod());

            //
            AdminLcMerCre adminLcMerCre = queryAdminLcMerCreList(merId);

            if (!validataMobile(freezeInputBean, merId, phoneNo)) {
                return BeanFactory.getFailFreezeOutputBean(ReturnCode.CORE_LC_PARAMETER_INVALID, "手机号错误");
            }

            //
            BusinessOutput<SmsSendOutputBean> businessOutput = querySmsCode(merId, phoneNo, smsCode);

            if (businessOutput.isFailed()) {
                return BeanFactory.getFailFreezeOutputBean(businessOutput.getReturnCode(), businessOutput.getReturnMsg());
            }

            //
            OpenCifParam param = createOpenCifParam(inputBean, freezeInputBean);

            param.setSubMerId(adminLcMerCre.getXnMerId()); // DONE:查询银联二级商户Id
            param.setReqReserved(JSON.toJSONString(reqReservedBean));

            GeneralTradeResult response = bankTradeService.doTrade(param);

            return generateFreezeResp(response, param.getTxnTime(), param);

        } catch (Throwable e) {
            return BeanFactory.getFailFreezeOutputBean(ReturnCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

    /**
     * 验证手机号码
     *
     * @param freezeInputBean
     * @param merId
     * @param phoneNo
     * @return
     */
    protected boolean validataMobile(FreezeInputBean freezeInputBean, String merId, String phoneNo) {

        UnionUserAccountDto depositAccount = merInfoService.findById(merId, freezeInputBean.getPreLc().getMerUserId(), getCardType().getCode());

        if (depositAccount == null || !depositAccount.getUserMobile().equals(phoneNo)) {
            return false;
        }

        return true;
    }

    /**
     * 验证短信验证码
     *
     * @param merId
     * @param phone
     * @param smsCode
     * @return 验证失败返回true, 否则返回false
     */
    protected BusinessOutput<SmsSendOutputBean> querySmsCode(String merId, String phone, String smsCode) {

        // 验证短信验证码
        SmsParamBean smsParamBean = new SmsParamBean();
        smsParamBean.setSmstype(SmsType.SMS_OPEN.getCode());
        smsParamBean.setMerId(merId);
        smsParamBean.setPhone(phone);
        smsParamBean.setSmsCode(smsCode);

        return smsValidateCodeService.execute(new BusinessInput<SmsParamBean>(smsParamBean));
    }

    protected FreezeOutputBean<CreditParam> generateFreezeResp(GeneralTradeResult response,
                                                               String txnTime,
                                                               OpenCifParam param) {

        LcTranStatus tranStatus = LcTranStatusMapper.parseUnionResult(response.getTradeResult());

        FreezeOutputBean<CreditParam> outputBean;

        if (TradeConfig.TRADE_RESULT_SUCCEED_0.equalsIgnoreCase(response.getTradeResult())) {
            outputBean = BeanFactory.getSucFreezeOutputBean();

            // 银联受理成功，还未真实冻结（设置LcTranStatus 更新数据库状态为“交易状态未知”）
            tranStatus = LcTranStatus.UNCERTAIN;
        } else {
            outputBean = BeanFactory.getFailFreezeOutputBean(response.getSysReturnCode(), response.getResultDesc());
        }

        // 返回结果
        outputBean.setSerialNo(response.getQueryId());
        outputBean.setReturnMsg(response.getResultDesc());
        outputBean.setLcTranStatus(tranStatus);

        CreditParam creditParam = new CreditParam();
        creditParam.setTradeResult(response.getTradeResult());
        creditParam.setResultDesc(response.getResultDesc());
        creditParam.setTxnTime(txnTime);
        try {
            creditParam.setAccNo(ThreeDESUtil.desEncryption(param.getAccNo()));
            creditParam.setPhoneNo(ThreeDESUtil.desEncryption(param.getPhoneNo()));
        } catch (Exception e) {
            logger.error("加密账号失败：lcId={}", param.getLcId());
        }
        outputBean.setData(creditParam);

        return outputBean;
    }

}
