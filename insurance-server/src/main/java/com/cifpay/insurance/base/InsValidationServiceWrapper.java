/**
 * File: InsValidationServiceWrapper.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年11月27日 下午5:00:47
 */
package com.cifpay.insurance.base;

import java.util.List;
import java.util.Locale;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.iscreen.ObjectValidationException;
import org.iscreen.ValidationFactoryConfig;
import org.iscreen.ValidationFailure;
import org.iscreen.ValidationServiceWrapper;

import com.cifpay.insurance.exception.InsuranceBizRuntimeException;
import com.cifpay.starframework.cache.ServiceResultCodeCache;

/**
 * 交还险接口参数验证规则
 * 
 * @author 张均锋
 *
 */
public class InsValidationServiceWrapper {
	private static Logger LOG = LogManager.getLogger(InsValidationServiceWrapper.class);
	private static ServiceResultCodeCache resultCode = ServiceResultCodeCache.getInstance();

	private ValidationFactoryConfig validationFactory;

	public ValidationFactoryConfig getValidationFactory() {
		return validationFactory;
	}

	public void setValidationFactory(ValidationFactoryConfig validationFactory) {
		this.validationFactory = validationFactory;
	}

	/**
	 * 验证指定bean和验证定义集合
	 * 
	 * @param bean
	 *            要验证的bean
	 * @param validationSetName
	 *            bean对应的验证规则
	 * @throws InsuranceBizRuntimeException
	 *             验证失败抛出此异常
	 */
	@SuppressWarnings("unchecked")
	public void validate(Object bean, String validationSetName) {
		ValidationServiceWrapper service = new ValidationServiceWrapper(validationFactory, validationSetName);
		try {
			service.validateObject(bean, Locale.getDefault());
		} catch (ObjectValidationException e) {
			LOG.error("发现校验失败的请求参数！");
			List<ValidationFailure> failures = e.getFailures(ValidationFailure.FAILURE); // 获取验证错误的信息列
			StringBuilder buf = new StringBuilder();
			for (ValidationFailure vf : failures) {
				if (buf.length() > 0) {
					buf.append(" ");
				}
				buf.append(vf.getMessage());
			}
			throw new InsuranceBizRuntimeException(resultCode.get("common.request.param.error"), buf.toString());
		} catch (Exception ep) {
			LOG.error("请求参数不正确！"+ep.getMessage());
			throw new InsuranceBizRuntimeException(resultCode.get("common.request.param.error"), "请求参数不正确！");
		}
	}

}
