/**
 * File: InsuranceCertListenerRegister.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月1日 下午5:54:04
 */
package com.cifpay.insurance.push;

import java.util.Vector;

import com.cifpay.insurance.push.event.InsuranceCertStateChangeEvent;
import com.cifpay.insurance.push.listener.InsuranceCertStateChangeListener;

/**
 * 保险证状态监听器注册者
 * 
 * @author 张均锋
 *
 */
public class InsuranceCertListenerRegister {

	private Vector<InsuranceCertStateChangeListener> listeners = new Vector<InsuranceCertStateChangeListener>();

	public synchronized void addListener(InsuranceCertStateChangeListener a) {
		listeners.addElement(a);
	}

	public synchronized void removeListener(InsuranceCertStateChangeListener a) {
		listeners.removeElement(a);
	}

	@SuppressWarnings("unchecked")
	public void fireEvent(InsuranceCertStateChangeEvent evt) {
		Vector<InsuranceCertStateChangeListener> currentListeners = null;
		synchronized (this) {
			currentListeners = (Vector<InsuranceCertStateChangeListener>) listeners.clone();
		}
		for (InsuranceCertStateChangeListener ltn: currentListeners) {
			ltn.performed(evt);
		}
	}
}
