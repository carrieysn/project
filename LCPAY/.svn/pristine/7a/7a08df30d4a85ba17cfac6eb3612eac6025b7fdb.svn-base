package com.cifpay.lc.std.business.sms;

import com.cifpay.lc.api.BusinessInput;
import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.api.gateway.sms.SmsSendMessageService;
import com.cifpay.lc.api.gateway.sms.SmsSendUnionService;
import com.cifpay.lc.bankadapter.api.IBankTradeService;
import com.cifpay.lc.bankadapter.api.helper.GetTradeParamHelper;
import com.cifpay.lc.bankadapter.api.input.unionpay.SmsCifParam;
import com.cifpay.lc.bankadapter.api.output.GeneralTradeResult;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.constant.enums.LcTranStatus;
import com.cifpay.lc.constant.enums.SmsType;
import com.cifpay.lc.core.common.CoreBusinessContext;
import com.cifpay.lc.core.common.CoreBusinessInterceptorConfig;
import com.cifpay.lc.core.common.CoreBusinessServiceImplBase;
import com.cifpay.lc.core.db.dao.AdminLcMerCreDao;
import com.cifpay.lc.core.db.dao.PreLcDao;
import com.cifpay.lc.core.db.pojo.AdminLcMerCre;
import com.cifpay.lc.core.db.pojo.PreLc;
import com.cifpay.lc.core.exception.CoreBusinessException;
import com.cifpay.lc.core.exception.PaymentException;
import com.cifpay.lc.core.uid.LcOpenIdWorker;
import com.cifpay.lc.domain.enums.AdminCardType;
import com.cifpay.lc.domain.message.SmsParamBean;
import com.cifpay.lc.domain.sms.SmsSendOutputBean;
import com.cifpay.lc.domain.sms.SmsSendUnionInputBean;
import com.cifpay.lc.domain.sms.SmsSendUnionOutputBean;
import com.cifpay.lc.std.interceptor.BusinessLoggingInterceptor;
import com.cifpay.lc.std.mapper.LcTranStatusMapper;
import com.cifpay.lc.std.paychannel.impl.AbstractUnion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.cifpay.lc.domain.enums.AdminCardType.CREDIT;

@Service("smsSendUnionService")
@CoreBusinessInterceptorConfig({BusinessLoggingInterceptor.class})
public class SmsSendUnionServiceImpl extends CoreBusinessServiceImplBase<SmsSendUnionInputBean, SmsSendUnionOutputBean> implements SmsSendUnionService {
    @Autowired
    private PreLcDao preLcDao;

    @Autowired
    private SmsSendMessageService smsSendMessageService;    // 信用卡发送验证码

    @Autowired
    private AdminLcMerCreDao adminLcMerCreDao;

    @Autowired
    private IBankTradeService bankTradeService;             // 储蓄卡发送验证码

    @Autowired
    private LcOpenIdWorker lcOpenIdWorker;

    @Override
    protected BusinessOutput<SmsSendUnionOutputBean> processBusiness(SmsSendUnionInputBean inputBean, CoreBusinessContext context) throws CoreBusinessException {
        if (inputBean == null) {
            return BusinessOutput.fail(ReturnCode.CORE_LC_PARAMETER_INVALID, "参数不正确");
        }

        String mobileNo = inputBean.getPhoneNo();
        AdminCardType adminCardType = inputBean.getCardType();

        if (StringUtils.isEmpty(mobileNo) || !mobileNo.matches("^\\d{11}$")) {
            return BusinessOutput.fail(ReturnCode.CORE_LC_PARAMETER_INVALID, "请输入正确的手机号码");
        }

        PreLc preLc = preLcDao.selectByLcId(inputBean.getLcId());

        if (preLc == null) {
            throw new CoreBusinessException(ReturnCode.CORE_STD_PRE_LC_NOT_EXISTS, "银信证预开证记录不存在");
        }
        if (preLc.getOpenValidTime().before(new Date())) {
            throw new CoreBusinessException(ReturnCode.CORE_STD_PRE_LC_NOT_EXISTS, "已过开证时间");
        }

        logger.info("发送短信验证码: {}", mobileNo);

        switch (adminCardType) {
            case CREDIT: {
                return creditSendMsg(inputBean, preLc);
            }
            case DEPOSIT: {
                return depositSendMsg(inputBean, preLc);
            }
            default: {
                return BusinessOutput.fail(ReturnCode.CORE_LC_PARAMETER_INVALID, "不支持的卡类型");
            }
        }
    }

    private BusinessOutput<SmsSendUnionOutputBean> creditSendMsg(SmsSendUnionInputBean inputBean, PreLc preLc) {
        //根据银信证类型，发送短信
        SmsParamBean smsParamBean = new SmsParamBean();
        smsParamBean.setSmstype(SmsType.SMS_OPEN.getCode());
        smsParamBean.setMerId(preLc.getMid());
        smsParamBean.setPhone(inputBean.getPhoneNo());
        smsParamBean.setAmount(preLc.getLcAmount());
        smsParamBean.setOrderContent(preLc.getOrderContent());

        BusinessOutput<SmsSendOutputBean> smsOutputBean = smsSendMessageService.execute(new BusinessInput<SmsParamBean>(smsParamBean));

        if (smsOutputBean.isSuccess()) {
            logger.info("调用发送信用卡短信成功: {}", inputBean.getPhoneNo());
            return BusinessOutput.success(new SmsSendUnionOutputBean());
        }

        return BusinessOutput.fail(smsOutputBean.getReturnCode(), smsOutputBean.getReturnMsg());
    }

    private BusinessOutput<SmsSendUnionOutputBean> depositSendMsg(SmsSendUnionInputBean inputBean, PreLc preLc) {

        if (StringUtils.isEmpty(inputBean.getAccNo())) {
            return BusinessOutput.fail(ReturnCode.CORE_LC_PARAMETER_INVALID, "请输入正确的银行卡号");
        }

        SmsCifParam param = null;
        try {
            param = GetTradeParamHelper.getInstance(GetTradeParamHelper.UNION_PAY).getTradeParam(SmsCifParam.class, preLc.getLcType(), AbstractUnion.DEPOSIT);
        } catch (Exception e) {
            logger.error("构建银联参数出错：{}", e.getMessage(), e);
            throw new PaymentException(ReturnCode.CORE_STD_UNION_PARAM_ERROR, "构建银联参数出错");
        }

        Long lcOpenId = lcOpenIdWorker.nextId();

        AdminLcMerCre adminLcMerCre = queryAdminLcMerCreList(preLc.getMid(), inputBean.getCardType());

        Date now = new Date();
        param.setLcId(preLc.getLcId());
        String time = new SimpleDateFormat(AbstractUnion.TXN_TIME_FORMAT).format(now);
        param.setOrderId(lcOpenId.toString());
        param.setTxnTime(time);
        param.setTxnAmt(preLc.getLcAmount().longValue());
        param.setSubMerId(adminLcMerCre.getXnMerId());
        param.setPhoneNo(inputBean.getPhoneNo());
        param.setAccNo(inputBean.getAccNo());

        GeneralTradeResult result = bankTradeService.doTrade(param);
        LcTranStatus tranStatus = LcTranStatusMapper.parseUnionResult(result.getTradeResult());

        if (LcTranStatus.SUCCESS.compareTo(tranStatus) == 0) {
            logger.info("调用发送储蓄卡短信成功: {}", inputBean.getPhoneNo());
            SmsSendUnionOutputBean smsSendUnionOutputBean = new SmsSendUnionOutputBean();
            smsSendUnionOutputBean.setLcOpenId(lcOpenId);
            return BusinessOutput.success(smsSendUnionOutputBean);
        }

        return BusinessOutput.fail(ReturnCode.CORE_STD_SMS_SEND_ERROR, result.getResultDesc());
    }

    private AdminLcMerCre queryAdminLcMerCreList(String merId, AdminCardType adminCardType) throws PaymentException {

        List<AdminLcMerCre> adminLcMerCreList = adminLcMerCreDao.selectByMerCodeAndCardType(merId, adminCardType.getCode());

        if (adminLcMerCreList == null || adminLcMerCreList.size() != 1) {
            throw new PaymentException(ReturnCode.CORE_STD_SECOND_MER_NOT_EXIST, "未找到对应的银联二级商户号");
        }

        return adminLcMerCreList.get(0);
    }
}
