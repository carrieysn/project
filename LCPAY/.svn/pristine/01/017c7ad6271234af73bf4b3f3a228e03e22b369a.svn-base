package com.cifpay.lc.api.gateway.basic.signkey;

import com.cifpay.lc.api.BusinessInput;
import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.api.BusinessService;

/**
 * 商户私有信息提供服务，通过该服务接口，Gateway可以获取到指定商户的商户状态、签名Key等敏感信息。
 */
public interface MerPrivateInfoProviderService extends BusinessService {
    BusinessOutput<MerFrontValidationMaterial> execute(BusinessInput<String> input);
}
