package com.cifpay.lc.util;

import java.lang.reflect.Type;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpEntity;
import org.springframework.util.StringUtils;

public class MethodParameterUtils {

	public static Class<?> getRequestDataType(MethodParameter parameter) {
		Class<?> dataType = null;
		Type genericParameterType = parameter.getGenericParameterType();

		if (null == genericParameterType) {
			throw new IllegalArgumentException("Controller方法" + parameter.getDeclaringClass().getName() + "." + parameter.getMethod().getName() + "()中的MerchantRequest参数未通过泛型指定data属性对应的类型");
		}

		// JDK1.8以上才支持：String genericTypeName =
		// genericParameterType.getTypeName();
		String genericTypeName = genericParameterType.toString();

		if (!StringUtils.hasLength(genericTypeName)) {
			throw new IllegalArgumentException("Controller方法" + parameter.getDeclaringClass().getName() + "." + parameter.getMethod().getName() + "()中的MerchantRequest参数未通过泛型指定data属性对应的类型");
		}

		int dataTypeNameBeginIdx = genericTypeName.indexOf('<');
		if (dataTypeNameBeginIdx < 0) {
			throw new IllegalArgumentException("Controller方法" + parameter.getDeclaringClass().getName() + "." + parameter.getMethod().getName() + "()中的MerchantRequest参数未通过泛型指定data属性对应的类型");
		}
		int dataTypeNameEndIdx = genericTypeName.indexOf('>', dataTypeNameBeginIdx);
		if (dataTypeNameEndIdx < 0) {
			throw new IllegalArgumentException("Controller方法" + parameter.getDeclaringClass().getName() + "." + parameter.getMethod().getName() + "()中的MerchantRequest参数未通过泛型指定data属性对应的类型");
		}
		String dataTypeName = genericTypeName.substring(dataTypeNameBeginIdx + 1, dataTypeNameEndIdx);
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		if (null == cl) {
			cl = MethodParameterUtils.class.getClassLoader();
		}
		try {
			dataType = cl.loadClass(dataTypeName);
		} catch (ClassNotFoundException e) {
			// should never happen
			throw new IllegalArgumentException("Controller方法" + parameter.getDeclaringClass().getName() + "." + parameter.getMethod().getName() + "()中的MerchantRequest参数通过泛型指定data属性对应的类型无法加载");
		}

		return dataType;
	}

	/**
	 * Return the generic type of the {@code returnType} (or of the nested type
	 * if it is a {@link HttpEntity}).
	 */
	public static Type getGenericType(MethodParameter returnType) {
		Type type;
		if (HttpEntity.class.isAssignableFrom(returnType.getParameterType())) {
			returnType.increaseNestingLevel();
			type = returnType.getNestedGenericParameterType();
		} else {
			type = returnType.getGenericParameterType();
		}
		return type;
	}
}
