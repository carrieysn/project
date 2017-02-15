package com.cifpay.lc.std.business.lc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.core.cache.pojo.LcProductCache;
import com.cifpay.lc.core.cache.service.LcProductCacheServant;
import com.cifpay.lc.core.common.CoreBusinessContext;
import com.cifpay.lc.core.common.CoreBusinessServiceImplBase;
import com.cifpay.lc.core.db.dao.LcDao;
import com.cifpay.lc.core.db.pojo.Lc;
import com.cifpay.lc.core.exception.CoreBusinessException;
import com.cifpay.lc.core.exception.CoreValidationRejectException;
import com.cifpay.lc.domain.lc.AbstractLcInputBean;
import com.cifpay.lc.domain.lc.AbstractLcOutputBean;

public abstract class AbstractLcProductServiceImplBase<I extends AbstractLcInputBean, O extends AbstractLcOutputBean> extends CoreBusinessServiceImplBase<I, O> {

    public static final String LC_PRODUCT = "LC_PRODUCT";

    @Autowired
    private LcDao lcDao;

    @Autowired
    private LcProductCacheServant lcProductCacheServant;

    /**
     * 输入参数的基本校验，主要包括非空校验、字符长度、数值范围校验等。
     *
     * @param inputBean
     */
    protected void validateInputParameters(I inputBean) throws CoreValidationRejectException {
    }

    protected void validateLc(I inputBean, Lc lc) throws CoreValidationRejectException {
    }

    /**
     * 银信证产品规则（型号、渠道）校验
     *
     * @param inputBean
     * @param lcProductCache
     * @param context
     */
    protected void validateLcProductRule(I inputBean, LcProductCache lcProductCache, CoreBusinessContext context) throws CoreValidationRejectException {
    }

    @Override
    protected void validate(I inputBean, CoreBusinessContext context) throws CoreValidationRejectException {

        if (inputBean.getLcId() <= 0) {
            throw new CoreValidationRejectException(ReturnCode.CORE_LC_PARAMETER_INVALID, "银信证ID不允许为空");
        }
        if (StringUtils.isEmpty(inputBean.getMerId())) {
            throw new CoreValidationRejectException(ReturnCode.CORE_LC_PARAMETER_INVALID, "商户号不允许为空");
        }

        // 验证传入参数
        validateInputParameters(inputBean);

        Lc lc = lcDao.selectByPrimaryKey(inputBean.getLcId());
        LcProductCache lcProductCache = null;
        if (lc != null) {
            context.setAttribute("LC", lc);

            if (inputBean.getMerId().compareTo(lc.getMid()) != 0) {
                throw new CoreValidationRejectException(ReturnCode.CORE_LC_AUTHENTICATION_FAILURE, "银信证ID与商户号不匹配");
            }

            lcProductCache = lcProductCacheServant.getLcProductCache(lc.getProductCode());
            if (lcProductCache == null) {
                throw new CoreBusinessException(ReturnCode.CORE_STD_LC_PRODUCT_NOT_EXISTS, "银信证产品不存在");
            }
            context.setAttribute(LC_PRODUCT, lcProductCache);
        }

        // 验证银信证信息
        validateLc(inputBean, lc);

        // 验证产品规则
        validateLcProductRule(inputBean, lcProductCache, context);
    }
}
