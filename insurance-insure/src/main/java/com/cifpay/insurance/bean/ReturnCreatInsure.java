package com.cifpay.insurance.bean;

import java.io.Serializable;
/**
 * 初始创建充值订单保单返回对象
 * 
 * @author 叶胜南
 *
 */
public class ReturnCreatInsure implements Serializable {
	
		private static final long serialVersionUID = 1L;
		/** 订单号 **/
		private String billNo;
		/** 保费  单位：分 **/
		private long premium;
		/** 保费  单位：元**/
		private String amount;
		/** 险种名称 **/
		private String product;
		public String getBillNo() {
			return billNo;
		}
		public void setBillNo(String billNo) {
			this.billNo = billNo;
		}
		public long getPremium() {
			return premium;
		}
		public void setPremium(long premium) {
			this.premium = premium;
		}
		public String getAmount() {
			return amount;
		}
		public void setAmount(String amount) {
			this.amount = amount;
		}
		public String getProduct() {
			return product;
		}
		public void setProduct(String product) {
			this.product = product;
		}
	
		
		
		

	


}
