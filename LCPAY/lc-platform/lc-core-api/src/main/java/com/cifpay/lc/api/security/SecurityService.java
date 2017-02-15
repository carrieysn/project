package com.cifpay.lc.api.security;

import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.domain.security.MerchantSignedResponse;
import com.cifpay.lc.domain.security.Securable;

import java.util.Map;

/**
 * 安全通信服务
 */
public interface SecurityService {

    /**
     * 数据签名
     *
     * @param securable
     * @return
     */
    String signData(Securable securable);

    /**
     * 数据验签
     *
     * @param securable
     * @return
     */
    Boolean verifyData(Securable securable);

    /**
     * 将数据加密并签名
     *
     * @param merId    商户号
     * @param response 加密数据明文(dubbo可能需要类的全名称，故使用map)
     * @return 数据密文
     */
    BusinessOutput<MerchantSignedResponse> encryptData(String merId, Map<String, Object> response);
}
