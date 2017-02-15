package com.cifpay.lc.std.sched.task.util;

import java.math.BigDecimal;

public class AmountUtil {

	/**金额为分的格式 */  
    public static final String CURRENCY_FEN_REGEX = "\\-?[0-9]+";  
      
    public static void main(String[] args) throws Exception {
    	BigDecimal amount = new BigDecimal("10010000.11");
    	String strAmount = changeF2Y(amount);
    	System.out.println(strAmount);
    	strAmount = digitUppercase(strAmount);
    	System.out.println(strAmount);
	}
    
    public static String digitUppercase(String amount){
    	String str = amount.replace(",", "");
    	double n = Double.parseDouble(str);
    	
        String fraction[] = {"角", "分"};
        String digit[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
        String unit[][] = {{"元", "万", "亿"},
                     {"", "拾", "佰", "仟"}};
 
        String head = n < 0? "负": "";
        n = Math.abs(n);
         
        String s = "";
        for (int i = 0; i < fraction.length; i++) {
            s += (digit[(int)(Math.floor(n * 10 * Math.pow(10, i)) % 10)] + fraction[i]).replaceAll("(零.)+", "");
        }
        if(s.length()<1){
            s = "整";    
        }
        int integerPart = (int)Math.floor(n);
 
        for (int i = 0; i < unit[0].length && integerPart > 0; i++) {
            String p ="";
            for (int j = 0; j < unit[1].length && n > 0; j++) {
                p = digit[integerPart%10]+unit[1][j] + p;
                integerPart = integerPart/10;
            }
            s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i] + s;
        }
        return head + s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "").replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");
    }
    
    public static String changeF2Y(BigDecimal amount) throws Exception {
    	String strAmount = amount.toString();
    	int iPos = strAmount.indexOf(".");
    	if(iPos > 0){
    		strAmount = strAmount.substring(0, iPos);
    	}
    	return changeF2Y(Long.parseLong(strAmount));
    }
    
    /**  
     * 将分为单位的转换为元并返回金额格式的字符串 （除100） 
     *  
     * @param amount 
     * @return 
     * @throws Exception  
     */  
    public static String changeF2Y(Long amount) throws Exception{  
        if(!amount.toString().matches(CURRENCY_FEN_REGEX)) {  
            throw new Exception("金额格式有误");  
        }  
          
        int flag = 0;  
        String amString = amount.toString();  
        if(amString.charAt(0)=='-'){  
            flag = 1;  
            amString = amString.substring(1);  
        }  
        StringBuffer result = new StringBuffer();  
        if(amString.length()==1){  
            result.append("0.0").append(amString);  
        }else if(amString.length() == 2){  
            result.append("0.").append(amString);  
        }else{  
            String intString = amString.substring(0,amString.length()-2);  
            for(int i=1; i<=intString.length();i++){  
                if( (i-1)%3 == 0 && i !=1){  
                    result.append(",");  
                }  
                result.append(intString.substring(intString.length()-i,intString.length()-i+1));  
            }  
            result.reverse().append(".").append(amString.substring(amString.length()-2));  
        }  
        if(flag == 1){  
            return "-"+result.toString();  
        }else{  
            return result.toString();  
        }  
    }  
      
    /** 
     * 将分为单位的转换为元 （除100） 
     *  
     * @param amount 
     * @return 
     * @throws Exception  
     */  
    public static String changeF2Y(String amount) throws Exception{  
        if(!amount.matches(CURRENCY_FEN_REGEX)) {  
            throw new Exception("金额格式有误");  
        }  
        return BigDecimal.valueOf(Long.valueOf(amount)).divide(new BigDecimal(100)).toString();  
    }  
      
    /**  
     * 将元为单位的转换为分 （乘100） 
     *  
     * @param amount 
     * @return 
     */  
    public static String changeY2F(Long amount){  
        return BigDecimal.valueOf(amount).multiply(new BigDecimal(100)).toString();  
    }  
      
    /**  
     * 将元为单位的转换为分 替换小数点，支持以逗号区分的金额 
     *  
     * @param amount 
     * @return 
     */  
    public static String changeY2F(String amount){  
        String currency =  amount.replaceAll("\\$|\\￥|\\,", "");  //处理包含, ￥ 或者$的金额  
        int index = currency.indexOf(".");  
        int length = currency.length();  
        Long amLong = 0l;  
        if(index == -1){  
            amLong = Long.valueOf(currency+"00");  
        }else if(length - index >= 3){  
            amLong = Long.valueOf((currency.substring(0, index+3)).replace(".", ""));  
        }else if(length - index == 2){  
            amLong = Long.valueOf((currency.substring(0, index+2)).replace(".", "")+0);  
        }else{  
            amLong = Long.valueOf((currency.substring(0, index+1)).replace(".", "")+"00");  
        }  
        return amLong.toString();  
    }  
}
