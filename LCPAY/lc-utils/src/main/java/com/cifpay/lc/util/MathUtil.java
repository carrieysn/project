package com.cifpay.lc.util;

import java.math.BigDecimal;

public class MathUtil {

	public static BigDecimal add(BigDecimal bd1, BigDecimal bd2) {
		if(bd1 == null) {
			bd1 = new BigDecimal("0");
		}
		if(bd2 == null) {
			bd2 = new BigDecimal("0");
		}
		return bd1.add(bd2);
	}
	
	public static double add(double d1, double d2) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.add(bd2).doubleValue();
	}
	
	/**
	 * 减法
	 * @param bd1 被减数
	 * @param bd2 减数
	 * @return
	 */
	public static BigDecimal sub(BigDecimal bd1, BigDecimal bd2) {
		if(bd1 == null) {
			bd1 = new BigDecimal("0");
		}
		if(bd2 == null) {
			bd2 = new BigDecimal("0");
		}
		return bd1.subtract(bd2);
	}
	
	/**
	 * 减法
	 * @param d1 被减数
	 * @param d2 减数
	 * @return
	 */
	public static double sub(double d1, double d2) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.subtract(bd2).doubleValue();
	}
}
