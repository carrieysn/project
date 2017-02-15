package com.cifpay.lc.core.autoconfigure.sms;

public class SmsProperties {
	
	private String url;
	
	private String username;
	
	private String password;
	
	private String sendflag;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSendflag() {
		return sendflag;
	}

	public void setSendflag(String sendflag) {
		this.sendflag = sendflag;
	}
	
	

}
