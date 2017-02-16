/**
 * File: StringUtils.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年11月25日 下午8:17:50
 */
package com.cifpay.insurance.util;

import java.text.DecimalFormat;

/**
 * 
 * @author 张均锋
 *
 */
public class StringUtils {
	
	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
	
	/** 
	 * 格式化数字为千分位显示； 
	 * @param 要格式化的数字； 
	 * @return 
	 */  
	public static String fmtMicrometer(String text)  
	{  
	    DecimalFormat df = null;  
	    if(text.indexOf(".") > 0)  
	    {  
	        if(text.length() - text.indexOf(".")-1 == 0)  
	        {  
	            df = new DecimalFormat("###,##0.");  
	        }else if(text.length() - text.indexOf(".")-1 == 1)  
	        {  
	            df = new DecimalFormat("###,##0.0");  
	        }else  
	        {  
	            df = new DecimalFormat("###,##0.00");  
	        }  
	    }else   
	    {  
	        df = new DecimalFormat("###,##0");  
	    }  
	    double number = 0.0;  
	    try {  
	         number = Double.parseDouble(text);  
	    } catch (Exception e) {  
	        number = 0.0;  
	    }  
	    return df.format(number);  
	}  
}
