package com.cifpay.lc.std.paychannel.impl;

import com.cifpay.lc.bankadapter.api.helper.GetTradeParamHelper;
import com.cifpay.lc.bankadapter.api.input.unionpay.OpenCifParam;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.constant.enums.PayMethod;
import com.cifpay.lc.core.db.dao.UnionUserAccountDao;
import com.cifpay.lc.core.db.pojo.LcOpen;
import com.cifpay.lc.core.db.pojo.PreLc;
import com.cifpay.lc.core.db.pojo.UnionUserAccount;
import com.cifpay.lc.core.exception.PaymentException;
import com.cifpay.lc.core.uid.UnionUserAccountIdWorker;
import com.cifpay.lc.domain.enums.AdminCardType;
import com.cifpay.lc.domain.lc.OpenLcOutputBean;
import com.cifpay.lc.domain.lc.OpenLcUnionCreditInputBean;
import com.cifpay.lc.std.domain.paychannel.FreezeInputBean;
import com.cifpay.lc.util.security.ThreeDESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 银联信用卡冻结开证
 */
@Component
public class UnionFreezeCredit extends UnionFreezeBase<OpenLcUnionCreditInputBean, OpenLcOutputBean> {

    @Autowired
    private UnionUserAccountDao userAccountDao;

    @Autowired
    private UnionUserAccountIdWorker unionUserAccountIdWorker;

    @Override
    public PayMethod getPayMethod() {
        return PayMethod.UNION_CREDIT;
    }

    @Override
    protected AdminCardType getCardType() {
        return AdminCardType.CREDIT;
    }

    @Override
    protected OpenCifParam createOpenCifParam(OpenLcUnionCreditInputBean creditInputBean, FreezeInputBean freezeInputBean) throws PaymentException {

        // 保存卡号信息
        saveUserUnionInfo(creditInputBean, freezeInputBean);

        PreLc preLc = freezeInputBean.getPreLc();

        LcOpen lcOpen = freezeInputBean.getLcOpen();

        OpenCifParam param = null;
        try {
            GetTradeParamHelper helper = GetTradeParamHelper.getInstance(GetTradeParamHelper.UNION_PAY);
            param = helper.getTradeParam(OpenCifParam.class, preLc.getLcType(), CREDIT);
        } catch (Exception e) {
            logger.error("构建银联参数出错：{}", e.getMessage(), e);
            throw new PaymentException(ReturnCode.CORE_STD_UNION_PARAM_ERROR, "构建银联参数出错");
        }

        param.setLcId(preLc.getLcId());
        param.setUserId(preLc.getMerUserId());
        param.setOrderId(lcOpen.getLcOpenId().toString()); // LC_OPEN_ID
        param.setOrderDesc(preLc.getOrderContent());
        param.setTxnTime(new SimpleDateFormat(AbstractUnion.TXN_TIME_FORMAT).format(new Date()));
        param.setTxnAmt(preLc.getLcAmount().longValue());
        param.setChannelType("07");
        param.setAccNo(creditInputBean.getAccNo());
        param.setCvn2(creditInputBean.getCvn2());
        param.setExpired(new SimpleDateFormat("yyMM").format(creditInputBean.getExpired()));
        param.setPhoneNo(creditInputBean.getPhoneNo());
        param.setCertifTp(creditInputBean.getCertifTp());
        param.setCertifId(creditInputBean.getCertifId());
        param.setCustomerNm(creditInputBean.getCustomerNm());

        return param;
    }

    @Override
    protected boolean validataMobile(FreezeInputBean freezeInputBean, String merId, String phoneNo) {
        return true;
    }

    protected void saveUserUnionInfo(OpenLcUnionCreditInputBean creditInputBean, FreezeInputBean freezeInputBean) {

        PreLc preLc = freezeInputBean.getPreLc();

        if (preLc == null) {
            logger.error("未找到预开证信息: {}", preLc);
            throw new PaymentException(ReturnCode.CORE_STD_PRE_LC_NOT_EXISTS, "未找到预开证信息");
        }

        UnionUserAccount unionUserAccount = userAccountDao.selectById(creditInputBean.getMerId(), preLc.getMerUserId(),
                AdminCardType.CREDIT.getCode());

        // 加密卡号及手机号
        String encryptAccNo = null;
        String encryptPhoneNo = null;
        try {
            encryptAccNo = ThreeDESUtil.desEncryption(creditInputBean.getAccNo());
            encryptPhoneNo = ThreeDESUtil.desEncryption(creditInputBean.getPhoneNo());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (null == unionUserAccount) {
            // 插入记录
            UnionUserAccount insertUnionUserAccount = new UnionUserAccount();
            insertUnionUserAccount.setpId(unionUserAccountIdWorker.nextId());
            insertUnionUserAccount.setMid(creditInputBean.getMerId());
            insertUnionUserAccount.setMerUserid(preLc.getMerUserId());
            insertUnionUserAccount.setPayerAccno(encryptAccNo);
            insertUnionUserAccount.setUserMobile(encryptPhoneNo);
            insertUnionUserAccount.setAccnoType(AdminCardType.CREDIT.getCode());
            insertUnionUserAccount.setCreateDate(new Date());
            userAccountDao.insert(insertUnionUserAccount);
        } else {
            // 更新信息
            UnionUserAccount updateUnionUserAccount = new UnionUserAccount();

            updateUnionUserAccount.setpId(unionUserAccount.getpId());
            updateUnionUserAccount.setPayerAccno(encryptAccNo);
            updateUnionUserAccount.setUserMobile(encryptPhoneNo);

            userAccountDao.updateByPrimaryKeySelective(updateUnionUserAccount);
        }
    }

}
