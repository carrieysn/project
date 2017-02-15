package com.cifpay.lc.bankadapter.universal.tool;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cifpay.lc.bankadapter.universal.tool.MapCvtUtil;
import com.cifpay.lc.bankadapter.universal.tool.TypeWrapper;

/**
 * 转换器，javaBean转map,map转javaBean
 * 
 * @author vincent
 * 
 */
public class MapCvtUtil {
	private static Logger log = LoggerFactory.getLogger(MapCvtUtil.class);

	/**
	 * 将javaBean转换成Map
	 * 
	 * @param javaBean
	 * @return Map对象
	 */
	public static Map<String, Object> beanToMap(Object javaBean) {
		Map<String, Object> result = new HashMap<String, Object>();
		Method[] methods = javaBean.getClass().getMethods();
		Set<String> fieldsSet=new HashSet<String>();
		Class<?> c = javaBean.getClass();
		while (null != c && !c.equals(Object.class)) {
			for (Field f : c.getDeclaredFields()) {
				fieldsSet.add(f.getName());
			}
			c = c.getSuperclass();
		}
		for (Method method : methods) {
			if (method.getName().equalsIgnoreCase("getClass")) {
				continue;
			}
			try {
				if (method.getName().startsWith("get")) {
					String field = method.getName();
					field = field.substring(field.indexOf("get") + 3);
					field = field.toLowerCase().charAt(0) + field.substring(1);
					if(!fieldsSet.contains(field)) //方法名与字段名对照，如非小写即将首字母大写
						field = field.toUpperCase().charAt(0) + field.substring(1);
					Object value = method.invoke(javaBean, (Object[]) null);
					
					result.put(field, null == value ? "" : value.toString());
				}
			} catch (Exception e) {
				throw new RuntimeException("Convert Bean2Map Error!",e);
			}
		}
		return result;
	}
	
	public static Map<String, Object> beanToMapObj(Object javaBean) {
		Map<String, Object> result = new HashMap<String, Object>();
		Method[] methods = javaBean.getClass().getMethods();
		Set<String> fieldsSet=new HashSet<String>();
		Class<?> c = javaBean.getClass();
		while (null != c && !c.equals(Object.class)) {
			for (Field f : c.getDeclaredFields()) {
				fieldsSet.add(f.getName());
			}
			c = c.getSuperclass();
		}
		for (Method method : methods) {
			if (method.getName().equalsIgnoreCase("getClass")) {
				continue;
			}
			try {
				if (method.getName().startsWith("get")) {
					String field = method.getName();
					field = field.substring(field.indexOf("get") + 3);
					field = field.toLowerCase().charAt(0) + field.substring(1);
					if(!fieldsSet.contains(field)) //方法名与字段名对照，如非小写即将首字母大写
						field = field.toUpperCase().charAt(0) + field.substring(1);
					Object value = method.invoke(javaBean, (Object[]) null);
					
					result.put(field, value);
				}
			} catch (Exception e) {
				throw new RuntimeException("Convert Bean2Map Error!",e);
			}
		}
		return result;
	}

	/**
	 * 将map转换成Javabean
	 * 
	 * @param clazz
	 * @param map
	 * @return bean
	 */
	public static Object toJavaBean(Class<?> clazz, Map<?, ?> map) {
		Object bean = null;
		try {
			bean = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Init Clazz Erron In Convert Map2Bean!",e);
		}
		
		if(bean instanceof Map){
			return map;
		}
		Class<?> superclass = clazz.getSuperclass();
		List<Field> superFieldList = Arrays.asList(superclass
				.getDeclaredFields());
		List<Field> subFieldList = Arrays.asList(clazz.getDeclaredFields());

		List<Field> fieldList = new ArrayList<Field>();
		fieldList.addAll(superFieldList);
		fieldList.addAll(subFieldList);
		
		for (Field field : fieldList) {
			String fieldName = field.getName();
			Object value = map.get(fieldName);
			
			if (fieldName.equals("openType")) {
				log.info("openType");
			}
			
			if (null == value) {
				continue;
			}
			
			try {
				Method method = null;
				Constructor<?> construtor = null;
				Class<?> claz = field.getType();
				try {
					method = clazz.getDeclaredMethod("set"
							+ fieldName.substring(0, 1).toUpperCase()
							+ fieldName.substring(1), field.getType());
				} catch (NoSuchMethodException e) {
					method = superclass.getDeclaredMethod("set"
							+ fieldName.substring(0, 1).toUpperCase()
							+ fieldName.substring(1), field.getType());
				}
				
				try {
					construtor = claz.getDeclaredConstructor(String.class);
				} catch (NoSuchMethodException e) {
					construtor = TypeWrapper.getWrapperType(claz).getDeclaredConstructor(String.class);
				}
				
				method.invoke(bean, construtor.newInstance(value));
			} catch (NoSuchMethodException e) {
				continue;
			} catch (Exception e) {
				throw new RuntimeException("Convert Map2Bean Error! Field {"
						+ field + "} 转换出错", e);
			}
		}
		
		/*Method[] methods = bean.getClass().getMethods();
		Set<String> fieldsSet = makeFieldsSet(clazz, new HashSet<String>());
				
		for (Method method : methods) {
			String field = "";
			try {		
				if (method.getName().startsWith("set")) {
					field = method.getName();
					field = field.substring(field.indexOf("set") + 3);
					field = field.toLowerCase().charAt(0) + field.substring(1);
					if(!fieldsSet.contains(field))
						field = field.toUpperCase().charAt(0) + field.substring(1);
					Object value = map.get(field);
					if (clazz.getDeclaredField(field).getClass().getName().equals(BigDecimal.class.getName())) {
						if (null == value) {
							value = BigDecimal.ZERO;
						}
						value = new BigDecimal((String) value);
					}
//					method.invoke(bean, new Object[] { map.get(field) });
					BeanUtils.setProperty(bean,field,value);
				}
			} catch (Exception e) {
				throw new RuntimeException("Convert Map2Bean Error! Field:" + field + "转换出错",e);
			}
		}*/
		return bean;
	}
	
	/*private static Set<String> makeFieldsSet(Class<?> clazz,Set<String> fieldsSet){
			
		Field[] fields = clazz.getDeclaredFields();
		
		for (int i = 0; i < fields.length; i++) {
			fieldsSet.add(fields[i].getName());
		}
		
		if(clazz.getGenericSuperclass() != null){
			Class superclass = clazz.getSuperclass();			
			makeFieldsSet(superclass, fieldsSet);
		}else{

		}
			
		return fieldsSet;
	}*/
	
	
	
	
	/**
	 * 将URL参数包装成map
	 * @param url
	 * @return
	 */
	public static Map<String,String> urlToParamsMap(String url){
		Map<String,String> paramsMap=new HashMap<String, String>();
		String[] kv=null;
		if(url.indexOf("?")!=-1)
			kv=url.substring(url.indexOf("?")+1).split("&");
		else
			kv=url.split("&");
		for (int i = 0; i < kv.length; i++) {
			String[] tmp=kv[i].split("=");
			if(tmp.length>1)
				paramsMap.put(tmp[0], tmp[1]);
			else
				paramsMap.put(tmp[0], "");
		}
		return paramsMap;
	}
	
	public static String paramsMapToString(Map<String,Object> map){
		StringBuffer str=new StringBuffer();
		if(null!=map){
			boolean flag = true;
			for(Map.Entry entry : map.entrySet()){			
				String key = (String)entry.getKey();
				String value = (String)entry.getValue();			
				if(flag){
					str.append(key).append("=").append(value);
					flag = false;
				}else{
					str.append("&").append(key).append("=").append(value);
				}				
			}
		}
		
		return str.toString();
	}
	
	/** 将String转成MAP */
	public static Map stringToMap(String source){
		
		Map<String,String> params = new HashMap<String,String>();
		
		String[] arr = source.split("&");
		
		for(String tmp : arr){
			
			String key = tmp.substring(0,tmp.indexOf("="));
			String value = tmp.substring(tmp.indexOf("=")+1);
			
			params.put(key, value);
		}
		
		return params;
	}
	
		
}
