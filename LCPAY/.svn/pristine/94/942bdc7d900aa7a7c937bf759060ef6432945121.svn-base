package com.cifpay.lc.std.kernel.lc;

import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.constant.BizConstants;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.constant.enums.LcStatusType;
import com.cifpay.lc.constant.enums.LcTranStatus;
import com.cifpay.lc.constant.enums.OpenChannel;
import com.cifpay.lc.core.cache.pojo.LcBankCache;
import com.cifpay.lc.core.cache.pojo.LcProductCache;
import com.cifpay.lc.core.cache.pojo.LcTypeCache;
import com.cifpay.lc.core.cache.service.LcBankCacheServant;
import com.cifpay.lc.core.cache.service.LcProductCacheServant;
import com.cifpay.lc.core.cache.service.LcTypeCacheServant;
import com.cifpay.lc.core.common.CoreBusinessContext;
import com.cifpay.lc.core.db.dao.LcOpenDao;
import com.cifpay.lc.core.db.dao.PreLcDao;
import com.cifpay.lc.core.db.pojo.LcOpen;
import com.cifpay.lc.core.db.pojo.PreLc;
import com.cifpay.lc.core.exception.CoreBusinessException;
import com.cifpay.lc.core.exception.CoreValidationRejectException;
import com.cifpay.lc.core.uid.LcIdWorker;
import com.cifpay.lc.std.domain.kernel.InitLcKernelInputBean;
import com.cifpay.lc.std.domain.kernel.InitLcKernelOutputBean;
import com.cifpay.lc.std.kernel.LcBaseKernel;
import com.cifpay.lc.std.util.BizUtil;
import com.cifpay.lc.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 初始化证（预开证）
 *
 * @author sweet
 */
// implements ApplicationContextAware
// private ApplicationContext applicationContext;
@Component
public class InitLcKernel extends LcBaseKernel<InitLcKernelInputBean, InitLcKernelOutputBean> {

    @Autowired
    private PreLcDao preLcDao;

    @Autowired
    private LcOpenDao lcOpenDao;

    @Autowired
    private LcProductCacheServant lcProductCacheServant;

    @Autowired
    private LcTypeCacheServant lcTypeCacheServant;

    @Autowired
    private LcBankCacheServant lcBankCacheServant;

    @Autowired
    private LcIdWorker lcIdWorker;

    @Override
    protected void validateLcStatus(InitLcKernelInputBean inputBean, CoreBusinessContext context) throws CoreValidationRejectException {

        // 验证参数
        if (inputBean.getAmount() == null || inputBean.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new CoreBusinessException(ReturnCode.CORE_STD_LC_AMT_INVALID, "银信证金额不正确");
        }

        // 查询产品信息
        LcProductCache lcProductCache = lcProductCacheServant.getLcProductCache(inputBean.getProductCode());
        if (lcProductCache == null) {
            throw new CoreBusinessException(ReturnCode.CORE_STD_LC_PRODUCT_NOT_EXISTS, "银信证产品不存在");
        }
        //LcProduct lcProduct = lcProductList.stream().findFirst().get();
        context.setAttribute("LC_PRODUCT_POJO", lcProductCache);

        // 验证币种
        if (!lcProductCache.getLcCurrency().equalsIgnoreCase(inputBean.getCurrency())) {
            throw new CoreBusinessException(ReturnCode.CORE_STD_LC_CURRENCY_INVALID, "银信证币种不正确，支持的币种：" + lcProductCache.getLcCurrency());
        }

        // 查询银信证类型
        LcTypeCache lcTypeCache = lcTypeCacheServant.getLcTypeCache(lcProductCache.getLcType());
        if (lcTypeCache == null) {
            throw new CoreBusinessException(ReturnCode.CORE_STD_LC_TYPE_NOT_EXISTS, "银信证类型不存在");
        }
        context.setAttribute("LC_TYPE_POJO", lcTypeCache);

        // 查询开证银行信息
        LcBankCache payBankCache;
        if (OpenChannel.BANK.equals(inputBean.getOpenChannel())) {
            payBankCache = lcBankCacheServant.selectByPrimaryKey(inputBean.getPayerBankCode());
        } else if (OpenChannel.UNION.equals(inputBean.getOpenChannel())) {
            payBankCache = lcBankCacheServant.selectByPrimaryKey(OpenChannel.UNION.getBankCode());
        } else {
            throw new CoreBusinessException(ReturnCode.CORE_STD_LC_BANK_NOT_EXISTS, "不支持的付款方式");
        }

        if (payBankCache == null) {
            logger.info("recvBankCode:开银行为空");
            throw new CoreBusinessException(ReturnCode.CORE_STD_LC_BANK_NOT_EXISTS, "未找到开证银行信息");
        }
        context.setAttribute("LC_PAY_BANK_POJO", payBankCache);

        // 查询收证银行信息
        LcBankCache recvBankCache = null;
        if (!StringUtils.isEmpty(inputBean.getRecvBankCode())) {
            recvBankCache = lcBankCacheServant.selectByPrimaryKey(inputBean.getRecvBankCode());
        }
        if (recvBankCache == null) {
            logger.info("recvBankCode:收证银行为空");
            recvBankCache = new LcBankCache();
        }
        context.setAttribute("LC_RECV_BANK_POJO", recvBankCache);

        if (inputBean.getRecvValidSecond() < 0) {
            throw new CoreBusinessException(ReturnCode.CORE_STD_LC_DATE_INVALID_RECV, "收证有效时间不正确");
        }
        if (inputBean.getSendValidSecond() < 0) {
            throw new CoreBusinessException(ReturnCode.CORE_STD_LC_DATE_INVALID_APPOINTMENT, "履约有效时间不正确");
        }
        if (inputBean.getConfirmValidSecond() < 0) {
            throw new CoreBusinessException(ReturnCode.CORE_STD_LC_DATE_INVALID_APPLY, "申请解付有效时间不正确");
        }
        if (inputBean.getPayValidSecond() < 0) {
            throw new CoreBusinessException(ReturnCode.CORE_STD_LC_DATE_INVALID_TRANSFER, "执行解付有效时间不正确");
        }
    }

    @Override
    protected void validateLcValidity(InitLcKernelInputBean inputBean, CoreBusinessContext context) throws CoreValidationRejectException {
        // TODO Auto-generated method stub
    }

    @Override
    public BusinessOutput<InitLcKernelOutputBean> processLcKernelLogic(InitLcKernelInputBean inputBean, CoreBusinessContext context) throws CoreBusinessException {
        logger.info("---进入预开证业务处理");

        String mid = inputBean.getMerId();
        String orderId = inputBean.getOrderId();

        // 查询产品信息
        LcProductCache lcProduct = (LcProductCache) context.getAttribute("LC_PRODUCT_POJO");
        // 查询银信证类型
        LcTypeCache lcType = (LcTypeCache) context.getAttribute("LC_TYPE_POJO");
        // 查询开证银行信息
        LcBankCache payBank = (LcBankCache) context.getAttribute("LC_PAY_BANK_POJO");
        // 查询收证银行信息
        LcBankCache recvBank = (LcBankCache) context.getAttribute("LC_RECV_BANK_POJO");


        // === 空单开证直接插入记录 ===
        if (!StringUtils.hasText(orderId)) {
            PreLc preLc = insertPreLc(inputBean, lcProduct, lcType, payBank, recvBank);

            // 成功返回
            InitLcKernelOutputBean result = getOutputBean(preLc);
            return BusinessOutput.success(result);
        }

        // === 跟单开证时，查询是否存在预开证记录 ===
        List<PreLc> preLcList = preLcDao.selectByMidOrderId(mid, orderId);

        if (null == preLcList || preLcList.isEmpty()) {
            // = 不存在预开证记录 =

            // 插入预开证记录
            PreLc preLc = insertPreLc(inputBean, lcProduct, lcType, payBank, recvBank);

            // 成功返回
            InitLcKernelOutputBean result = getOutputBean(preLc);
            return BusinessOutput.success(result);

        } else {
            // = 存在预开证记录 =

            if (preLcList.size() != 1) {
                throw new CoreBusinessException(ReturnCode.UNKNOWN_ERROR, "银信证预开证异常");
            }

            // 查询所有开证记录
            Long lcId = preLcList.get(0).getLcId();
            List<LcOpen> lcOpenList = lcOpenDao.selectByLcId(lcId);

            // = 预开证[可能已付款] =
            boolean isSuccess = false; // 至少一笔[开证成功]
            boolean isProcess = false; // 至少一笔[处理中]或[不确定]
            boolean isAllFailed = true;// 所有付款均失败
            for (int i = 0; i < lcOpenList.size(); i++) {
                LcOpen item = lcOpenList.get(i);
                String itemLcStatus = item.getLcOpenStatus();

                isSuccess = isSuccess || itemLcStatus.equals(LcTranStatus.SUCCESS.getTranStatusStr());
                isProcess = isProcess || (itemLcStatus.equals(LcTranStatus.INPROCESS.getTranStatusStr()) || itemLcStatus.equals(LcTranStatus.UNCERTAIN.getTranStatusStr()));
                isAllFailed = isAllFailed && itemLcStatus.equals(LcTranStatus.FAIL.getTranStatusStr());
            }

            // 付款已成功！
            if (isSuccess) {
                throw new CoreBusinessException(ReturnCode.CORE_STD_BANK_ITF_RESULT_SUCCESS, "已付款成功");
                // return success(getOutputBean(preLc));
            }
            // 付款状态不确定或处理中！
            if (isProcess) {
                throw new CoreBusinessException(ReturnCode.CORE_STD_BANK_ITF_RESULT_UNKNOWN, "付款正在处理中");
            }

            // = 预开证[未付款]（没有开证记录或开证均失败），删除预开证记录，重新插入数据，成功返回 =
            if (lcOpenList.isEmpty() || isAllFailed) {
                // 删除预开证记录
                preLcDao.softDeleteByLcId(lcId);
                // 插入预开证记录
                PreLc preLc = insertPreLc(inputBean, lcProduct, lcType, payBank, recvBank);
                // 成功返回
                InitLcKernelOutputBean result = getOutputBean(preLc);
                return BusinessOutput.success(result);
            }
        }

        throw new CoreBusinessException(ReturnCode.UNKNOWN_ERROR, "预开证失败");
    }

    // 插入预开证记录
    private PreLc insertPreLc(InitLcKernelInputBean inputBean, LcProductCache lcProduct, LcTypeCache lcType, LcBankCache payBank, LcBankCache recvBank) {

        String mid = inputBean.getMerId();
        String orderId = inputBean.getOrderId();

        PreLc entity = new PreLc();
        entity.setIsValid(true);
        entity.setLcId(lcIdWorker.nextId());
        entity.setBatchNo(inputBean.getBatchOpenId());

        entity.setLcNo(BizUtil.genLcNo(inputBean.getAmount(), inputBean.getCurrency(), inputBean.getOrderId()));
        entity.setMid(mid);
        entity.setOrderId(orderId);
        entity.setOrderContent(inputBean.getOrderContent());
        entity.setProductId(lcProduct.getProductId());
        entity.setProductCode(lcProduct.getProductCode());
        entity.setLcType(lcProduct.getLcType());
        entity.setLcCurrency(inputBean.getCurrency());
        entity.setLcAmount(inputBean.getAmount());

        // 开证人信息
        entity.setPayerAccno(inputBean.getPayerBankAccountNo());
        entity.setPayerBankCode(inputBean.getPayerBankCode());
        entity.setPayerBankName(payBank.getBankName());
        entity.setPayerType(inputBean.getPayerAccountType() != null ? inputBean.getPayerAccountType().getCode() : null);
        entity.setPayerMobile(inputBean.getPayerMobile());
        // entity.setPayerId(payerId);

        // 收证人信息
        entity.setRecvBankCode(inputBean.getRecvBankCode());
        entity.setRecvAccno(inputBean.getRecvBankAccountNo());
        entity.setRecvBankName(recvBank.getBankName());
        entity.setRecvType(inputBean.getRecvAccountType() != null ? inputBean.getRecvAccountType().getCode() : null);
        entity.setRecvMobile(inputBean.getRecvMobile());
        // entity.setRecvId(inputBean.);

        // 银信证有效期
        int openValidSecond = inputBean.getOpenValidSecond() > 0 ? inputBean.getOpenValidSecond() : 900;
        int recvValidSecond = inputBean.getRecvValidSecond() > 0 ? inputBean.getRecvValidSecond() : lcType.getMaxDaysToReceive() * 24 * 3600;
        int sendValidSecond = inputBean.getSendValidSecond() > 0 ? inputBean.getSendValidSecond() : lcType.getMaxDaysToSend() * 24 * 3600;
        int confirmValidSecond = inputBean.getConfirmValidSecond() > 0 ? inputBean.getConfirmValidSecond() : lcType.getMaxDaysToConfirmPay() * 24 * 3600;
        int payValidSecond = inputBean.getPayValidSecond() > 0 ? inputBean.getPayValidSecond() : lcType.getMaxDaysToPay() * 24 * 3600;

        Date now = new Date();
        Date openValidTime = DateUtil.addSecond(now, openValidSecond);
        Date recvValidTime = DateUtil.addSecond(now, recvValidSecond);
        Date sendValidTime = DateUtil.addSecond(now, recvValidSecond + sendValidSecond);
        Date confirmValidTime = DateUtil.addSecond(now, recvValidSecond + sendValidSecond + confirmValidSecond);
        Date payValidTime = DateUtil.addSecond(now, recvValidSecond + sendValidSecond + confirmValidSecond + payValidSecond);

        entity.setOpenValidTime(openValidTime);
        entity.setRecvValidTime(recvValidTime);
        entity.setSendValidTime(sendValidTime);
        entity.setConfirmValidTime(confirmValidTime);
        entity.setPayValidTime(payValidTime);

        // 回调地址
        entity.setLcStateReturnUrl(inputBean.getReturnUrl());
        entity.setLcStateNotifyUrl(inputBean.getNoticeUrl());
        entity.setLcOrderDetailUrl(inputBean.getMrchOrderUrl());

        // 银信证配置
        entity.setPayType(inputBean.getPayType().getCode());
        entity.setRemark(inputBean.getRemark());

        entity.setCreateTime(now);
        entity.setUpdateTime(now);

        //----------------(2016/10/17)增加商户用户标识------------------------//
        entity.setMerUserId(inputBean.getMerUserId());

        preLcDao.insert(entity);

        return entity;
    }

    // 拼接返回数据
    private InitLcKernelOutputBean getOutputBean(PreLc preLc) {

        if (preLc == null) {
            throw new CoreBusinessException(ReturnCode.CORE_STD_PRE_LC_NOT_EXISTS, "插入预开证记录失败");
        }

        InitLcKernelOutputBean outputBean = new InitLcKernelOutputBean();

        outputBean.setLcId(preLc.getLcId());
        outputBean.setLcNo(preLc.getLcNo());
        outputBean.setLcType(preLc.getLcType());
        outputBean.setCurrency(preLc.getLcCurrency());
        outputBean.setLcAmount(preLc.getLcAmount());

        outputBean.setPayerBankName(preLc.getPayerBankName());
        outputBean.setPayerBankCode(preLc.getPayerBankCode());
        outputBean.setPayerBankAccountNo(preLc.getPayerAccno());

        outputBean.setRecvBankName(preLc.getRecvBankName());
        outputBean.setRecvBankCode(preLc.getRecvBankCode());
        outputBean.setRecvBankAccountNo(preLc.getRecvAccno());

        outputBean.setOrderId(preLc.getOrderId());
        outputBean.setMrchOrderUrl(preLc.getLcOrderDetailUrl());

        SimpleDateFormat sdFormat = new SimpleDateFormat(BizConstants.DateFormat_std);
        outputBean.setRecvValidTime(sdFormat.format(preLc.getRecvValidTime()));
        outputBean.setSendValidTime(sdFormat.format(preLc.getSendValidTime()));
        outputBean.setConfirmPayValidTime(sdFormat.format(preLc.getConfirmValidTime()));
        outputBean.setPayValidTime(sdFormat.format(preLc.getPayValidTime()));

        outputBean.setLcStatus(LcStatusType.NEW.getStatusCode());
        outputBean.setLcStatusDesc(LcStatusType.NEW.getStatusDesc());

        return outputBean;
    }

}
