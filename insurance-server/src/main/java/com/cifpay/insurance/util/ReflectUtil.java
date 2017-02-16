/**
 * File: ReflectUtil.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月10日 下午4:20:58
 */
package com.cifpay.insurance.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 映射工具类
 * 
 * @author 张均锋
 *
 */
public class ReflectUtil {

	/**
	 * 获取对象定义的方法，当前对象类中没有定义，则向上父类查找
	 * 
	 * @param object
	 *            : 子类对象
	 * @param methodName
	 *            : 父类中的方法名
	 * @param parameterTypes
	 *            : 父类中的方法参数类型
	 * @return 父类中的方法对象
	 */

	public static Method getDeclaredMethod(Object obj, String methodName, Class<?>... parameterTypes) {
		Method method = null;
		for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				method = clazz.getDeclaredMethod(methodName, parameterTypes);
				return method;
			} catch (Exception e) {
				// 这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
				// 如果这里的异常打印或者往外抛，则就不会执行clazz =
				// clazz.getSuperclass(),最后就不会进入到父类中了
			}
		}

		return null;
	}

	/**
	 * 调用对象方法，当前对象不存在此方法定义，则向上父类调用，直至找到该方法定义（若存在）
	 * 
	 * @param obj
	 *            对象
	 * @param methodName
	 *            方法名
	 * @param parameterTypes
	 *            方法参数类型
	 * @param parameters
	 *            方法参数
	 * @return 方法的执行结果
	 */

	public static Object invokeMethod(Object obj, String methodName, Class<?>[] parameterTypes, Object[] parameters) {
		// 根据 对象、方法名和对应的方法参数 通过反射 调用上面的方法获取 Method 对象
		Method method = getDeclaredMethod(obj, methodName, parameterTypes);
		// method.setAccessible(true);
		try {
			if (null != method) {
				// 调用object 的 method 所代表的方法，其方法的参数是 parameters
				return method.invoke(obj, parameters);
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 获取类字段
	 * 
	 * @param _class
	 *            指定类
	 * @param includeParentClass
	 *            是否包含父类字段
	 * @return
	 */
	public static List<Field> getClassFields(Class<?> _class, boolean includeParentClass) {
		List<Field> fieldBox = new ArrayList<Field>();
		Field[] fields = _class.getDeclaredFields();
		for (Field field : fields) {
			fieldBox.add(field);
		}
		if (includeParentClass) {
			getParentClassFields(fieldBox, _class.getSuperclass());
		}
		return fieldBox;
	}

	/**
	 * 获取类字段及父类字段
	 * 
	 * @param fieldBox
	 * @param _class
	 */
	private static void getParentClassFields(List<Field> fieldBox, Class<?> _class) {
		if (_class == null)
			return;
		Field[] fields = _class.getDeclaredFields();
		for (Field field : fields) {
			fieldBox.add(field);
		}
		getParentClassFields(fieldBox, _class.getSuperclass());
	}

	/**
	 * 暴力获取字段值。忽略private,protected的安全检查
	 * 
	 * @param field
	 *            指定字段
	 * @param obj
	 *            指定对象
	 * @return
	 * @throws Exception
	 */
	public static Object getFieldValue(Object obj, Field field) {
		boolean isAccessibleOld = false;
		try {
			isAccessibleOld = field.isAccessible();
			if (!isAccessibleOld) {
				field.setAccessible(true);
			}
			return field.get(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (!isAccessibleOld) {
				field.setAccessible(false);
			}
		}
	}
	
	/**
	 * 暴力获取字段值。忽略private,protected的安全检查
	 * 
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static Object getFieldValue(Object obj, String fieldName) {
		Field field = getDeclaredField(obj, fieldName);
		return getFieldValue(obj, field);
	}

	/**
	 * 循环向上转型, 获取对象的 DeclaredField
	 * 
	 * @param obj
	 *            : 子类对象
	 * @param fieldName
	 *            : 父类中的属性名
	 * @return 父类中的属性对象
	 */

	public static Field getDeclaredField(Object obj, String fieldName) {
		Field field = null;
		Class<?> clazz = obj.getClass();
		for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				field = clazz.getDeclaredField(fieldName);
				return field;
			} catch (Exception e) {
				// 这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
				// 如果这里的异常打印或者往外抛，则就不会执行clazz =
				// clazz.getSuperclass(),最后就不会进入到父类中了
			}
		}

		return null;
	}

	/**
	 * 设置字段值，。忽略private,protected的安全检查
	 * 
	 * @param obj
	 * @param fieldName
	 * @param value
	 */
	public static void setFieldValue(Object obj, String fieldName, Object value) {
		Field field = getDeclaredField(obj, fieldName);
		field.setAccessible(true);
		try {
			field.set(obj, value);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
