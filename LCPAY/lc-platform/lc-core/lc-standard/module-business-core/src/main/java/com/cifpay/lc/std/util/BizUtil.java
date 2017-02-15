package com.cifpay.lc.std.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class BizUtil {
    /**
     * 生成银信证展示编号
     *
     * @param amount   金额（分）
     * @param currency 币种
     * @param orderId  商户订单号
     * @return
     */
    public static String genLcNo(BigDecimal amount, String currency, String orderId) {
        StringBuffer sb = new StringBuffer();
        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        sb.append("银信证")
          .append("*")
          .append(decimalFormat.format(amount.divide(BigDecimal.valueOf(100))))
          .append(currency)
          .append("*")
          .append(orderId);

        return sb.toString();
    }

    /**
     * bean属性拷贝，要求目标bean包含源bean的所有属性字段
     *
     * @param objectSrc  源
     * @param objectDest 目标
     * @return 拷贝成功返回true，失败返回false
     */
    public static boolean reflectBean2Bean(Object objectSrc, Object objectDest) {
        boolean bRet = true;
        try {
            Class<? extends Object> classSrc = objectSrc.getClass();
            Class<? extends Object> classDest = objectDest.getClass();
            Field[] fieldsSrc = classSrc.getDeclaredFields();
            for (int i = 0; i < fieldsSrc.length; i++) {
                Field fieldSrc = fieldsSrc[i];
                boolean bAccessibleSrc = fieldSrc.isAccessible();
                fieldSrc.setAccessible(true);
                String fieldName = fieldSrc.getName();
                Object fieldValue = fieldSrc.get(objectSrc);
                Field fieldDest = classDest.getDeclaredField(fieldName);
                boolean bAccessibleDest = fieldDest.isAccessible();
                fieldDest.setAccessible(true);
                fieldDest.set(objectDest, fieldValue);

                fieldSrc.setAccessible(bAccessibleSrc);
                fieldDest.setAccessible(bAccessibleDest);
            }
        } catch (Exception e) {
            bRet = false;
        }
        return bRet;
    }
}
