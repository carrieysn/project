/**
 * File: InsurancePolicyListenerRegister.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月2日 下午4:32:59
 */
package com.cifpay.insurance.push;

import java.util.Vector;

import com.cifpay.insurance.push.event.InsurancePolicyEvent;
import com.cifpay.insurance.push.listener.InsurancePolicyListener;

/**
 * 保单监听器注册者
 * 
 * @author 张均锋
 *
 */
public class InsurancePolicyListenerRegister {
	private Vector<InsurancePolicyListener> listeners = new Vector<InsurancePolicyListener>();

	public synchronized void addListener(InsurancePolicyListener a) {
		listeners.addElement(a);
	}

	public synchronized void removeListener(InsurancePolicyListener a) {
		listeners.removeElement(a);
	}

	@SuppressWarnings("unchecked")
	public void fireEvent(InsurancePolicyEvent evt) {
		Vector<InsurancePolicyListener> currentListeners = null;
		synchronized (this) {
			currentListeners = (Vector<InsurancePolicyListener>) listeners.clone();
		}
		for (InsurancePolicyListener ltn: currentListeners) {
			ltn.performed(evt);
		}
	}
}
