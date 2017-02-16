/**
 * File: InsurancePolicyListener.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月2日 上午9:55:58
 */
package com.cifpay.insurance.push.listener;

import com.cifpay.insurance.push.event.InsurancePolicyEvent;

/**
 * 保单监听器
 * 
 * @author 张均锋
 *
 */
public interface InsurancePolicyListener extends java.util.EventListener {

	public void performed(InsurancePolicyEvent event);
}
