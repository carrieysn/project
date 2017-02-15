package com.cifpay.lc.core.cache.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SmsCache implements Serializable{
	
	private static final long serialVersionUID = -6035521997661188978L;

	private int smstype;	//短信模版类型(模板类型：1:cp200 ,2:cp300,3:cp500,4:开证,5:退款)
    private String merId;
    private String merName;
    private String phone;
    private String orderContent;    //订单详情
    private String cardNo;      // 银行卡尾号（4位）
    private Date createTime;    // 交易时间
    private BigDecimal amount;  //交易金额（分）
    private String smsCode;		//短信验证码(入参时无须传参)
    
	public int getSmstype() {
		return smstype;
	}
	public void setSmstype(int smstype) {
		this.smstype = smstype;
	}
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	public String getMerName() {
		return merName;
	}
	public void setMerName(String merName) {
		this.merName = merName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getOrderContent() {
		return orderContent;
	}
	public void setOrderContent(String orderContent) {
		this.orderContent = orderContent;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getSmsCode() {
		return smsCode;
	}
	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

}
