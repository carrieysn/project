package com.cifpay.lc.std.business.lc;

import java.io.Serializable;

import com.cifpay.lc.util.logging.AbstractInputBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.api.CoreBusinessService;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.core.cache.pojo.LcProductCache;
import com.cifpay.lc.core.cache.service.LcProductCacheServant;
import com.cifpay.lc.core.common.CoreBusinessContext;
import com.cifpay.lc.core.common.CoreBusinessServiceImplBase;
import com.cifpay.lc.core.exception.CoreBusinessException;
import com.cifpay.lc.core.exception.CoreValidationRejectException;

/**
 * 银信证标准流程核心业务的基础逻辑。
 *
 *
 *
 * @param <I>
 * @param <O>
 */
public abstract class AbstractLcServiceImplBase<I extends AbstractInputBean, O extends Serializable> extends CoreBusinessServiceImplBase<I, O> implements CoreBusinessService<I, O> {

	@Autowired
	LcProductCacheServant lcProductCacheServant;

	@Override
	protected final void validate(I inputBean, CoreBusinessContext context) throws CoreValidationRejectException {
		// TODO 公共的校验

		// 验证传入参数
		validateInputParameters(inputBean);
		validateLcProductRule(inputBean, context);
	}

	/**
	 * 输入参数的基本校验，主要包括非空校验、字符长度、数值范围校验等。
	 *
	 * @param input
	 */
	protected abstract void validateInputParameters(I input) throws CoreValidationRejectException;

	/**
	 * 银信证产品规则（型号、渠道）校验
	 *
	 * @param inputBean
	 * @param context
	 */
	protected abstract void validateLcProductRule(I inputBean, CoreBusinessContext context) throws CoreValidationRejectException;

	@Override
	protected final BusinessOutput<O> processBusiness(I inputBean, CoreBusinessContext context) {
		BusinessOutput<O> result = null;

		// TODO 银信证级的公共业务逻辑处理

		// 调用子类的业务逻辑处理
		result = processLcBusiness(inputBean, context);

		// TODO 银信证级的公共业务逻辑处理

		return result;
	}

	/**
	 * 处理银信证业务
	 *
	 * @param inputBean
	 * @param context
	 * @return
	 */
	protected abstract BusinessOutput<O> processLcBusiness(I inputBean, CoreBusinessContext context);

	protected LcProductCache getProductByCode(String productCode) throws CoreBusinessException {

		LcProductCache lcProductCache = lcProductCacheServant.getLcProductCache(productCode);
		if (lcProductCache == null) {
			throw new CoreBusinessException(ReturnCode.CORE_STD_LC_PRODUCT_NOT_EXISTS, "银信证产品不存在");
		}
		//LcProduct lcProduct = lcProductList.stream().findFirst().get();

		return lcProductCache;
	}
}
