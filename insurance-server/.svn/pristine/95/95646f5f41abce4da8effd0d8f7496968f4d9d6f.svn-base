/**
 * File: InsuranceEventFactory.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月1日 下午4:50:16
 */
package com.cifpay.insurance.push;

import com.cifpay.insurance.push.event.InsuranceCertStateChangeEvent;
import com.cifpay.insurance.push.event.InsurancePolicyEvent;
import com.cifpay.insurance.push.listener.InsuranceCertStateChangeDefaultListener;
import com.cifpay.insurance.push.listener.InsurancePolicyDefaultListener;

/**
 * 交还险推送管理。默认注册监听器。
 * 
 * @author 张均锋
 *
 */
public class InsuranceEventDefaultHandler {
	/** 保单监听器注册者 **/
	private static InsurancePolicyListenerRegister policyListenerRegister = new InsurancePolicyListenerRegister();
	/** 保险证监听器注册者 **/
	private static InsuranceCertListenerRegister certListenerRegister = new InsuranceCertListenerRegister();

	static {//注册默认监听器
		policyListenerRegister.addListener(new InsurancePolicyDefaultListener());
		certListenerRegister.addListener(new InsuranceCertStateChangeDefaultListener());
	}
	
	private InsuranceEventDefaultHandler() {}
	
	private static class InsuranceEventFactoryInner {
		private final static InsuranceEventDefaultHandler INSTANCE = new InsuranceEventDefaultHandler();
	}
	
	/** 获取默认监听工厂 **/
	public static InsuranceEventDefaultHandler getInstance() {
		return InsuranceEventFactoryInner.INSTANCE;
	}
	
	/**
	 * 保单保费改变事件。
	 * 
	 * @param event
	 */
	public void doPremiumChange(InsurancePolicyEvent event) {
		policyListenerRegister.fireEvent(event);
	}

	/**
	 * 保险证状态改变事件。
	 * 
	 * @param event
	 */
	public void doInsuranceCertStateChange(InsuranceCertStateChangeEvent event) {
		certListenerRegister.fireEvent(event);
	}

}
