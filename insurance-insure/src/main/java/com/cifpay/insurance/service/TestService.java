package com.cifpay.insurance.service;

import com.cifpay.starframework.adapter.HttpCall;
import com.cifpay.starframework.model.ServiceResult;

public interface TestService {
	public ServiceResult<String> get(HttpCall httpCall);
}
