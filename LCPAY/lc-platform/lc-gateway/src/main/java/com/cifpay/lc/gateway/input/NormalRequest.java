package com.cifpay.lc.gateway.input;

public class NormalRequest<T> {
	public static final String MER_REQUEST_ATTR_KEY = "normalRequest";
	
	private String appId; 	// 接入ID
	private String service; // 接口名称
	private String charset; // 字符编码
	private String signType;// 签名类型
	private String sign;	// 签名字符串
	private String version;	// 接口版本号
	private String timesTamp;	// 时间戳
	private String mobile;		// 手机号码
	private String data;		// 业务参数
	
	private T decryptData;	//解密后的参数

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getService() {
		return service;
	}

	public void setService(String serivce) {
		this.service = serivce;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getTimesTamp() {
		return timesTamp;
	}

	public void setTimesTamp(String timesTamp) {
		this.timesTamp = timesTamp;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public T getDecryptData() {
		return decryptData;
	}

	public void setDecryptData(T decryptData) {
		this.decryptData = decryptData;
	}

}
