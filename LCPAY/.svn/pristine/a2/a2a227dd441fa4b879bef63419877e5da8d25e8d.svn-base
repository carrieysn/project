package com.cifpay.lc.api.gateway.basic.signkey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cifpay.lc.api.BusinessInput;
import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.constant.ReturnCode;

/**
 * 
 *
 */
public class MerPrivateInfoProviderServiceStub implements MerPrivateInfoProviderService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private MerPrivateInfoProviderService remote;

	public MerPrivateInfoProviderServiceStub(MerPrivateInfoProviderService remote) {
		this.remote = remote;
	}

	@Override
	public BusinessOutput<MerFrontValidationMaterial> execute(BusinessInput<String> input) {
		try {
			return remote.execute(input);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error("核心业务暂时不可用，" + e.getMessage(), e);
			} else {
				logger.error("核心业务暂时不可用，" + e.getMessage());
			}
			BusinessOutput<MerFrontValidationMaterial> output = new BusinessOutput<MerFrontValidationMaterial>();
			output.setReturnCode(ReturnCode.GW_CORE_BUSINESS_UNAVAILABLE);
			output.setReturnMsg("核心业务暂时不可用");
			return output;
		}
	}

}
