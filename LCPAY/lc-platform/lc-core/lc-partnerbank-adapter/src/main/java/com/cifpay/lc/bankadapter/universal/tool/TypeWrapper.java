package com.cifpay.lc.bankadapter.universal.tool;

/**
 * 根据简单数据类型获取相对应的复合数据类型
 * @author liuzhongchao
 *
 */
public final class TypeWrapper {
	public static final Class<?> getWrapperType(Class<?> clazz) {
		BasicDataTypeEnum enumValue = BasicDataTypeEnum.getEnumByName(clazz.getName());
		return enumValue.getComplexType();
	}
	
	public enum BasicDataTypeEnum {
		INTEGER("int", Integer.class),
		BOOLEAN("boolean", Boolean.class),
		LONG("long", Long.class),
		DOUBLE("double", Double.class),
		SHORT("short", Short.class),
		FLOAT("float", Float.class),
		CHARACHER("char", Character.class),
		BYTE("byte", Byte.class);
		
		private String basicType;
		private Class<?> complexType;
		
		public String getBasicType() {
			return basicType;
		}

		public void setBasicType(String basicType) {
			this.basicType = basicType;
		}

		public Class<?> getComplexType() {
			return complexType;
		}

		public void setComplexType(Class<?> complexType) {
			this.complexType = complexType;
		}

		BasicDataTypeEnum(String basicType, Class<?> complexType) {
			this.basicType = basicType;
			this.complexType = complexType;
		}
		
		static BasicDataTypeEnum getEnumByName(String enumName) {
			BasicDataTypeEnum[] enumValues = BasicDataTypeEnum.values();
			for (BasicDataTypeEnum enumValue : enumValues) {
				if (enumValue.getBasicType().equals(enumName)) {
					return enumValue;
				}
			}
			return null;
		}
	}
}
