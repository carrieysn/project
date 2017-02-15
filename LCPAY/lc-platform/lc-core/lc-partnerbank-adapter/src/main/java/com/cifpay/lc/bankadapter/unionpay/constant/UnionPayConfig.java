package com.cifpay.lc.bankadapter.unionpay.constant;


public class UnionPayConfig {
	
	public static String frontTransUrl = UnionPayConfigTool.getString("unionpay.frontTransUrl",UnionPayConfigTool.PAY_CONFIG);
	public static String backTransUrl = UnionPayConfigTool.getString("unionpay.backTransUrl",UnionPayConfigTool.PAY_CONFIG);
	
	public static String singleQueryUrl = UnionPayConfigTool.getString("unionpay.singleQueryUrl",UnionPayConfigTool.PAY_CONFIG);
	public static String batchTransUrl = UnionPayConfigTool.getString("unionpay.batchTransUrl",UnionPayConfigTool.PAY_CONFIG);
	public static String fileTransUrl = UnionPayConfigTool.getString("unionpay.fileTransUrl",UnionPayConfigTool.PAY_CONFIG);
	public static String appTransUrl = UnionPayConfigTool.getString("unionpay.appTransUrl",UnionPayConfigTool.PAY_CONFIG);
	public static String cardTransUrl = UnionPayConfigTool.getString("unionpay.cardTransUrl",UnionPayConfigTool.PAY_CONFIG);
	public static String jfFrontTransUrl = UnionPayConfigTool.getString("unionpay.jfFrontTransUrl",UnionPayConfigTool.PAY_CONFIG);
	public static String jfBackTransUrl = UnionPayConfigTool.getString("unionpay.jfBackTransUrl",UnionPayConfigTool.PAY_CONFIG);
	public static String jfSingleQueryUrl = UnionPayConfigTool.getString("unionpay.jfCardTransUrl",UnionPayConfigTool.PAY_CONFIG);
	public static String jfCardTransUrl = UnionPayConfigTool.getString("unionpay.backTransUrl",UnionPayConfigTool.PAY_CONFIG);
	public static String jfAppTransUrl = UnionPayConfigTool.getString("unionpay.jfAppTransUrl",UnionPayConfigTool.PAY_CONFIG);
	
	
	
	public static String signCert_path = UnionPayConfigTool.getString("unionpay.signCert.path",UnionPayConfigTool.PAY_CONFIG);
	public static String signCert_pwd = UnionPayConfigTool.getString("unionpay.signCert.pwd",UnionPayConfigTool.PAY_CONFIG);
	public static String signCert_type = UnionPayConfigTool.getString("unionpay.signCert.type",UnionPayConfigTool.PAY_CONFIG);
	public static String validateCert_dir = UnionPayConfigTool.getString("unionpay.validateCert.dir",UnionPayConfigTool.PAY_CONFIG);
	public static String encryptCert_path = UnionPayConfigTool.getString("unionpay.encryptCert.path",UnionPayConfigTool.PAY_CONFIG);
	public static String singleMode = UnionPayConfigTool.getString("unionpay.singleMode",UnionPayConfigTool.PAY_CONFIG);
	
	public static String cifpayCert_path = UnionPayConfigTool.getString("cifpay.certPath",UnionPayConfigTool.PAY_CONFIG);
	public static String dingGou_merId = UnionPayConfigTool.getString("dingGou.merId",UnionPayConfigTool.PAY_CONFIG);
	public static String token_merId = UnionPayConfigTool.getString("token.merId",UnionPayConfigTool.PAY_CONFIG);
	
	public static String backUrl = UnionPayConfigTool.getString("cifpay.backUrl",UnionPayConfigTool.PAY_CONFIG);// 后台通知地址
	public static String backDingCertificationUrl =  UnionPayConfigTool.getString("cifpay.backDingCertificationUrl",UnionPayConfigTool.PAY_CONFIG);// 后台通知地址
	public static String openCartFrontUrL = UnionPayConfigTool.getString("cifpay.openCartFrontUrL",UnionPayConfigTool.PAY_CONFIG);
	public static String openCartBackUrL = UnionPayConfigTool.getString("cifpay.openCartBackUrL",UnionPayConfigTool.PAY_CONFIG);

}
