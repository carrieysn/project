package com.cifpay.lc.core.common;

import com.cifpay.lc.core.util.LcMd5SignTool;
import org.junit.Test;

public class LcMd5SignToolTest {

	@Test
	public void testSign(){
		
		String s = "amount=18&currency=CNY&merId=1000001&noticeUrl=http://192.168.59.193:9080/withdraw/notify&orderDesc=提现测试&orderId=20160622111617&payeeAcctType=1&payeeBankAcctNo=4000020829200148508&payeeBankCode=ICBC&payeeBankName=工商银行&payeeCityName=广东省广州市&payeeMobile=13800138000&payeeName=邻商惕半刺尝但农酵瘟入晋率咕&payerBankAcctNo=4000023029200124946&payerBankCode=ICBC&requestId=20160622111617&requestTime=20160622111617&userCode=1002";
		
		String r = LcMd5SignTool.signString(s, "11c9c951a5ad422d88ccbfafa6912d5e");
		System.out.println(r);
	}
}
