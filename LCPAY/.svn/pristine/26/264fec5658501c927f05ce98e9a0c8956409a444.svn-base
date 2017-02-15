package com.cifpay.lc.gateway.common.util;

import java.lang.reflect.Field;

public class CPUtil {

	/**
	 * bean属性拷贝，要求目标bean包含源bean的所有属性字段
	 * @param objectSrc 源
	 * @param objectDest 目标
	 * @return 拷贝成功返回true，失败返回false
	 */
	public static boolean reflectBean2Bean(Object objectSrc, Object objectDest) {
		boolean bRet = true;
		try{
			Class<? extends Object> classSrc = objectSrc.getClass();
			Class<? extends Object> classDest = objectDest.getClass();
			Field[] fieldsSrc = classSrc.getDeclaredFields();
			for(int i=0; i<fieldsSrc.length; i++) {
				Field fieldSrc = fieldsSrc[i];
				boolean bAccessibleSrc = fieldSrc.isAccessible();
				fieldSrc.setAccessible(true);
				String fieldName = fieldSrc.getName();
				if(fieldName.endsWith("serialVersionUID")) {
					continue;
				}
				Object fieldValue = fieldSrc.get(objectSrc);
				Field fieldDest = classDest.getDeclaredField(fieldName);
				boolean bAccessibleDest = fieldDest.isAccessible();
				fieldDest.setAccessible(true);
				fieldDest.set(objectDest, fieldValue);
				
				fieldSrc.setAccessible(bAccessibleSrc);
				fieldDest.setAccessible(bAccessibleDest);
			}
		} catch(Exception e) {
			bRet = false;
		}
		return bRet;
	}
}
