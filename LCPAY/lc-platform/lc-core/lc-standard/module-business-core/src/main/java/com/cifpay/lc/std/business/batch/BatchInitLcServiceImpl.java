package com.cifpay.lc.std.business.batch;

import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.api.gateway.batch.BatchInitLcService;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.constant.enums.AccountPropertyType;
import com.cifpay.lc.constant.enums.LcPayType;
import com.cifpay.lc.core.common.CoreBusinessContext;
import com.cifpay.lc.core.common.CoreBusinessExceptionHelper;
import com.cifpay.lc.core.common.DbTransaction;
import com.cifpay.lc.core.common.DbTransactionHelper;
import com.cifpay.lc.core.db.dao.AdminCifpayLcBankDao;
import com.cifpay.lc.core.db.dao.LcOpenBatchDao;
import com.cifpay.lc.core.db.dao.LcProductDao;
import com.cifpay.lc.core.db.pojo.AdminCifpayLcBank;
import com.cifpay.lc.core.db.pojo.LcOpenBatch;
import com.cifpay.lc.core.db.pojo.LcProduct;
import com.cifpay.lc.core.exception.CoreBusinessException;
import com.cifpay.lc.core.exception.CoreValidationRejectException;
import com.cifpay.lc.core.uid.LcOpenBatchIdWorker;
import com.cifpay.lc.domain.batch.BatchInitLcInputBean;
import com.cifpay.lc.domain.batch.BatchInitLcInputDto;
import com.cifpay.lc.domain.batch.BatchInitLcOutputBean;
import com.cifpay.lc.domain.batch.BatchInitLcOutputDto;
import com.cifpay.lc.std.business.lc.AbstractLcServiceImplBase;
import com.cifpay.lc.std.domain.enums.ProductPayerType;
import com.cifpay.lc.std.domain.kernel.InitLcKernelInputBean;
import com.cifpay.lc.std.domain.kernel.InitLcKernelOutputBean;
import com.cifpay.lc.std.kernel.lc.InitLcKernel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

@Service("batchInitLcService")
public class BatchInitLcServiceImpl extends AbstractLcServiceImplBase<BatchInitLcInputBean, BatchInitLcOutputBean> implements BatchInitLcService {

    @Autowired
    private InitLcKernel initLc;

    @Autowired
    private LcProductDao lcProductDao;

    @Autowired
    private AdminCifpayLcBankDao adminCifpayLcBankDao;

    @Autowired
    private LcOpenBatchDao lcOpenBatchDao;

    @Autowired
    private LcOpenBatchIdWorker lcOpenBatchIdWorker;

    @Autowired
    private DbTransactionHelper dbTransactionHelper;

    @Override
    protected void validateInputParameters(BatchInitLcInputBean inputBean) throws CoreValidationRejectException {

    }

    @Override
    protected void validateLcProductRule(BatchInitLcInputBean inputBean, CoreBusinessContext context) throws CoreValidationRejectException {

        // 查询开证银行信息
        AdminCifpayLcBank payBank = adminCifpayLcBankDao.selectByBankCode(inputBean.getPayerBankCode());
        if (payBank == null) {
            throw new CoreBusinessException(ReturnCode.CORE_STD_LC_BANK_NOT_EXISTS, "未找到开证银行信息");
        }
        context.setAttribute("LC_PAY_BANK_POJO", payBank);

        Set<String> productCodes = new HashSet<String>();
        for (BatchInitLcInputDto batchInitLcInputDto : inputBean.getLcList()) {
            productCodes.add(batchInitLcInputDto.getProductCode());
        }

        // 查询产品信息
        List<LcProduct> lcProductList = lcProductDao.selectBatchByProductCode(productCodes.toArray(new String[productCodes.size()]));
        if (lcProductList == null || lcProductList.size() != productCodes.size()) {
            throw new CoreBusinessException(ReturnCode.CORE_STD_LC_PRODUCT_NOT_EXISTS, "银信证产品不存在");
        }
        // 检查是否支持批量开证
        for (LcProduct lcProduct : lcProductList) {
            if (lcProduct.getAllowMultipleOpen().booleanValue() == false) {
                throw new CoreBusinessException(ReturnCode.CORE_STD_LC_PRODUCT_NOT_SUPPORTED_MULTIPLE_OPEN, "银信证产品不支持批量开证");
            }
        }
        context.setAttribute("lcProductList", lcProductList);

        // 检查产品规则
        for (BatchInitLcInputDto batchInitLcInputDto : inputBean.getLcList()) {
            LcProduct lcProduct = null;
            for (LcProduct p : lcProductList) {
                if (p.getProductCode().compareTo(batchInitLcInputDto.getProductCode()) == 0) {
                    lcProduct = p;
                }
            }
            if (lcProduct == null) {
                throw new CoreBusinessException(ReturnCode.CORE_STD_LC_PRODUCT_NOT_EXISTS, "银信证产品不存在");
            }

            if (lcProduct.getLcCurrency().compareToIgnoreCase(batchInitLcInputDto.getCurrency()) != 0) {
                throw new CoreBusinessException(ReturnCode.PRODUCT_CURRENCY_NOT_SUPPORTED, "产品不支持当前币种");
            }
            if (lcProduct.getLcAmountMin() != null && batchInitLcInputDto.getAmount().compareTo(lcProduct.getLcAmountMin()) < 0) {
                throw new CoreBusinessException(ReturnCode.PRODUCT_AMOUNT_MIN_NOT_SUPPORTED, "支付金额过小");
            }
            if (lcProduct.getLcAmountMax() != null && batchInitLcInputDto.getAmount().compareTo(lcProduct.getLcAmountMax()) > 0) {
                throw new CoreBusinessException(ReturnCode.PRODUCT_AMOUNT_MAX_NOT_SUPPORTED, "支付金额过大");
            }
            if (lcProduct.getLcStandard() != null && lcProduct.getLcStandard().booleanValue() == false) {
                if (batchInitLcInputDto.getRecvValidSecond() != 0 || batchInitLcInputDto.getSendValidSecond() != 0 || batchInitLcInputDto.getConfirmValidSecond() != 0 || batchInitLcInputDto.getPayValidSecond() != 0) {
                    throw new CoreBusinessException(ReturnCode.PRODUCT_STANDARD_CAN_NOT_MODIFY, "标准银信证不可以修改到期时间");
                }
            }
            if (lcProduct.getAutoOpen() != null && lcProduct.getAutoOpen()) {
                if ((StringUtils.isEmpty(inputBean.getPayerBankAccountNo()) || StringUtils.isEmpty(inputBean.getPayerBankCode()) || inputBean.getPayerAccountType() == null) && StringUtils.isEmpty(inputBean.getPayerMobile())) {
                    throw new CoreBusinessException(ReturnCode.PRODUCT_PAY_ACCOUNT_CAN_NOT_EMPTY, "自动开证时，付款人信息不可以为空");
                }
            }
            if (inputBean.getPayerAccountType() != null && inputBean.getPayerAccountType().compareTo(AccountPropertyType.PERSONAL) == 0) {
                if (lcProduct.getPayerType() != null && lcProduct.getPayerType().compareTo(ProductPayerType.PERSONAL.getCode()) != 0 && lcProduct.getPayerType().compareTo(ProductPayerType.ALL.getCode()) != 0) {
                    throw new CoreBusinessException(ReturnCode.PRODUCT_PAYER_TYPE_NOT_EMPTY, "产品不支持当前付款人类型");
                }
            }
            if (inputBean.getPayerAccountType() != null && inputBean.getPayerAccountType().compareTo(AccountPropertyType.CORPORATE) == 0) {
                if (lcProduct.getPayerType() != null && lcProduct.getPayerType().compareTo(ProductPayerType.CORPORATE.getCode()) != 0 && lcProduct.getPayerType().compareTo(ProductPayerType.ALL.getCode()) != 0) {
                    throw new CoreBusinessException(ReturnCode.PRODUCT_PAYER_TYPE_NOT_EMPTY, "产品不支持当前付款人类型");
                }
            }
        }

    }

    @Override
    protected BusinessOutput<BatchInitLcOutputBean> processLcBusiness(BatchInitLcInputBean inputBean, CoreBusinessContext context) throws CoreBusinessException {

        logger.info("开始批量开证");
        AdminCifpayLcBank payBank = (AdminCifpayLcBank) context.getAttribute("LC_PAY_BANK_POJO");
        @SuppressWarnings("unchecked")
        List<LcProduct> lcProductList = (List<LcProduct>) context.getAttribute("lcProductList");

        BatchInitLcInputBean batchInitLcInputBean = inputBean;

        BatchInitLcOutputBean result = new BatchInitLcOutputBean();
        List<BatchInitLcOutputDto> batchInitLcOutputDtos = new ArrayList<BatchInitLcOutputDto>();
        result.setLcList(batchInitLcOutputDtos);

        DbTransaction dbTransaction = dbTransactionHelper.beginTransaction();
        try {

            // 创建批次
            LcOpenBatch lcOpenBatch = insertLcOpenBatch(batchInitLcInputBean, payBank);
            result.setBatchOpenId(lcOpenBatch.getBatchOpenId());

            // 执行开证逻辑
            for (BatchInitLcInputDto batchLcInputBean : inputBean.getLcList()) {

                LcProduct lcProduct = null;
                for (LcProduct item : lcProductList) {
                    if (item.getProductCode().compareToIgnoreCase(batchLcInputBean.getProductCode()) == 0) {
                        lcProduct = item;
                    }
                }
                if (lcProduct == null) {
                    throw new CoreBusinessException(ReturnCode.CORE_STD_LC_PRODUCT_NOT_EXISTS, "银信证产品不存在");
                }

                InitLcKernelInputBean lcInputBean = new InitLcKernelInputBean();
                lcInputBean.setBatchOpenId(lcOpenBatch.getBatchOpenId().toString());

                lcInputBean.setMerId(batchInitLcInputBean.getMerId());
                lcInputBean.setProductCode(batchLcInputBean.getProductCode());
                lcInputBean.setOrderId(batchLcInputBean.getOrderId());

                lcInputBean.setAmount(batchLcInputBean.getAmount());
                lcInputBean.setCurrency(batchLcInputBean.getCurrency());

                lcInputBean.setRecvValidSecond(batchLcInputBean.getRecvValidSecond());
                lcInputBean.setSendValidSecond(batchLcInputBean.getSendValidSecond());
                lcInputBean.setConfirmValidSecond(batchLcInputBean.getConfirmValidSecond());
                lcInputBean.setPayValidSecond(batchLcInputBean.getPayValidSecond());

                lcInputBean.setPayerBankCode(batchInitLcInputBean.getPayerBankCode());
                lcInputBean.setPayerBankAccountNo(batchInitLcInputBean.getPayerBankAccountNo());
                lcInputBean.setPayerAccountType(batchInitLcInputBean.getPayerAccountType());
                lcInputBean.setPayerMobile(batchInitLcInputBean.getPayerMobile());

                lcInputBean.setRecvBankCode(batchLcInputBean.getRecvBankCode());
                lcInputBean.setRecvBankAccountNo(batchLcInputBean.getRecvBankAccountNo());
                lcInputBean.setRecvAccountType(batchLcInputBean.getRecvAccountType());
                lcInputBean.setRecvMobile(batchLcInputBean.getRecvMobile());

                lcInputBean.setReturnUrl(batchInitLcInputBean.getReturnUrl());
                lcInputBean.setNoticeUrl(batchInitLcInputBean.getNoticeUrl());
                lcInputBean.setMrchOrderUrl(batchLcInputBean.getMerOrderUrl());

                lcInputBean.setRemark(batchInitLcInputBean.getRemark());
                lcInputBean.setPayType((lcProduct.getAllowPartialPay() != null && lcProduct.getAllowPartialPay()) ? LcPayType.MULTIPAY_PAY_WITH_SAME_PAYEE : LcPayType.SINGLE_PAY);

                BusinessOutput<InitLcKernelOutputBean> businessOutput = initLc.invokeKernel(lcInputBean, context);

                if (businessOutput.isSuccess()) {
                    InitLcKernelOutputBean initLcOutputBean = businessOutput.getData();

                    BatchInitLcOutputDto outputDto = new BatchInitLcOutputDto();
                    outputDto.setLcId(initLcOutputBean.getLcId());
                    outputDto.setLcNo(initLcOutputBean.getLcNo());

                    outputDto.setLcType(initLcOutputBean.getLcType());
                    outputDto.setCurrency(initLcOutputBean.getCurrency());
                    outputDto.setLcAmount(initLcOutputBean.getLcAmount());

                    outputDto.setPayerBankName(initLcOutputBean.getPayerBankName());
                    outputDto.setPayerBankCode(initLcOutputBean.getPayerBankCode());
                    outputDto.setPayerBankAccountNo(initLcOutputBean.getPayerBankAccountNo());

                    outputDto.setRecvBankName(initLcOutputBean.getRecvBankName());
                    outputDto.setRecvBankCode(initLcOutputBean.getRecvBankCode());
                    outputDto.setRecvBankAccountNo(initLcOutputBean.getRecvBankAccountNo());

                    outputDto.setOrderId(initLcOutputBean.getOrderId());
                    outputDto.setMrchOrderUrl(initLcOutputBean.getMrchOrderUrl());

                    outputDto.setRecvValidTime(initLcOutputBean.getRecvValidTime());
                    outputDto.setSendValidTime(initLcOutputBean.getSendValidTime());
                    outputDto.setConfirmPayValidTime(initLcOutputBean.getConfirmPayValidTime());
                    outputDto.setPayValidTime(initLcOutputBean.getPayValidTime());

                    batchInitLcOutputDtos.add(outputDto);

                } else {
                    throw new CoreBusinessException(businessOutput.getReturnCode(), businessOutput.getReturnMsg());
                }

            }
            dbTransactionHelper.commitTransaction(dbTransaction);
            return BusinessOutput.success(result);

        } catch (Exception e) {
            dbTransactionHelper.rollbackTransaction(dbTransaction);
            CoreBusinessExceptionHelper.throwAsCoreBusinessException(e);
        }

        throw new CoreBusinessException(ReturnCode.CORE_STD_LC_BATCH_OPEN_ERROR, "批量开证失败");

    }

    private LcOpenBatch insertLcOpenBatch(BatchInitLcInputBean inputBean, AdminCifpayLcBank payBank) {
        List<BatchInitLcInputDto> lcList = inputBean.getLcList();
        BigDecimal totalAmount = new BigDecimal(0);
        Date now = new Date();

        for (BatchInitLcInputDto lc : lcList) {
            totalAmount = totalAmount.add(lc.getAmount());
        }

        LcOpenBatch lcOpenBatch = new LcOpenBatch();
        lcOpenBatch.setBatchOpenId(lcOpenBatchIdWorker.nextId());
        lcOpenBatch.setCreateTime(now);
        lcOpenBatch.setUpdateTime(now);

        lcOpenBatch.setMid(inputBean.getMerId());
        lcOpenBatch.setLcCurrency(lcList.get(0).getCurrency());
        lcOpenBatch.setLcBatchAmount(totalAmount);
        lcOpenBatch.setLcBatchBalance(totalAmount);

        // lcOpenBatch.setPayerId(payerId);
        lcOpenBatch.setPayerAccno(inputBean.getPayerBankAccountNo());
        lcOpenBatch.setPayerType(inputBean.getPayerAccountType() != null ? inputBean.getPayerAccountType().getCode() : null);
        lcOpenBatch.setPayerBankCode(payBank.getBankCode());
        lcOpenBatch.setPayerBankName(payBank.getBankName());

        lcOpenBatch.setRemark(inputBean.getRemark());

        lcOpenBatchDao.insert(lcOpenBatch);

        return lcOpenBatch;
    }

}
