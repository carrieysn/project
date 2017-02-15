package com.cifpay.lc.std.kernel;

import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.core.common.CoreBusinessContext;
import com.cifpay.lc.core.exception.CoreBusinessException;
import com.cifpay.lc.core.exception.CoreValidationRejectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * 银信证标准流程核心业务的基础逻辑。
 *
 * @param <I>
 * @param <O>
 */
public abstract class LcBaseKernel<I extends Serializable, O extends Serializable> {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected boolean loggerDebugEnabled = logger.isDebugEnabled();

    private final static String VALIDATE_LOGIC_RUN_ALREADY_FLAG = LcBaseKernel.class.getName() + "#validatedAlready";

    public BusinessOutput<O> invokeKernel(I input, CoreBusinessContext context)
            throws CoreValidationRejectException, CoreBusinessException {

        validateOnly(input, context);

        return processLcKernelLogic(input, context);
    }

    public void validateOnly(I input, CoreBusinessContext context) throws CoreValidationRejectException {

        if (null == context.getAttribute(VALIDATE_LOGIC_RUN_ALREADY_FLAG)) {

            validateLcStatus(input, context);

            validateLcValidity(input, context);

            context.setAttribute(VALIDATE_LOGIC_RUN_ALREADY_FLAG, Boolean.TRUE);
        }
    }

    /**
     * 银信证状态规则校验
     *
     * @param input
     * @param context
     */
    protected abstract void validateLcStatus(I input, CoreBusinessContext context) throws CoreValidationRejectException;

    /**
     * 银信证时间规则校验
     *
     * @param input
     * @param context
     */
    protected void validateLcValidity(I input, CoreBusinessContext context) throws CoreValidationRejectException {

    }

    /**
     * @param inputBean
     * @param context
     * @return
     * @throws CoreBusinessException
     */
    protected abstract BusinessOutput<O> processLcKernelLogic(I inputBean, CoreBusinessContext context) throws CoreBusinessException;
}
