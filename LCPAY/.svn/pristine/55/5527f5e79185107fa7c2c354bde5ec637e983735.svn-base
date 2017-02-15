package com.cifpay.lc.gateway.common;

import com.cifpay.lc.api.BusinessInput;
import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.api.gateway.basic.signkey.MerFrontValidationMaterial;
import com.cifpay.lc.api.gateway.basic.signkey.MerPrivateInfoProviderService;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.gateway.common.exception.GatewayAuthenticationFailure;
import com.cifpay.lc.gateway.common.exception.GatewayValidationRejectException;
import com.cifpay.lc.util.security.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 缓存商户概要信息，给gateway提供用于校验商户请求时所需要的商户概要信息，例如商户的状态、验证商户签名的KEY等，
 * 防止无效请求穿透到Core核心业务层。
 */
@Component
public class MerchantSummaryInfoCache {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MerPrivateInfoProviderService merPrivateInfoProviderService;

    public MerFrontValidationMaterial getMerchantSummary(String merId) {

        if (logger.isDebugEnabled()) {
            logger.debug("从核心业务层获取商户概要信息，商户ID：{}.", merId);
        }
        BusinessInput<String> bi = new BusinessInput<String>(merId);
        BusinessOutput<MerFrontValidationMaterial> bo = merPrivateInfoProviderService.execute(bi);
        if (bo.isFailed()) {
            throw new GatewayAuthenticationFailure(bo.getReturnCode(), bo.getReturnMsg());
        }
        MerFrontValidationMaterial material = bo.getData();
        return material;
    }

    public String getDecryptKey(MerFrontValidationMaterial material) throws GatewayValidationRejectException {
        if (Constants.ENCRYPT_DES.equals(material.getEncryptType())
                || Constants.ENCRYPT_MD5.equals(material.getEncryptType())) {
            return material.getEncryptKey();
        } else if (Constants.ENCRYPT_RAS.equals(material.getEncryptType())) {
            //获取商户的公钥（RAS加密，数据库decryptKey存放商户的公钥）
            return material.getDecryptKey();
        } else {
            throw new GatewayValidationRejectException(ReturnCode.GW_GENERIC_VALIDATION_REJECTED, "暂时不支持的加密算法");
        }
    }

    public String getEncryptKey(MerFrontValidationMaterial material) throws GatewayValidationRejectException {
        if (Constants.ENCRYPT_DES.equals(material.getEncryptType())
                || Constants.ENCRYPT_MD5.equals(material.getEncryptType())
                || Constants.ENCRYPT_RAS.equals(material.getEncryptType())) {
            return material.getEncryptKey();
        } else {
            throw new GatewayValidationRejectException(ReturnCode.GW_GENERIC_VALIDATION_REJECTED, "暂时不支持" + material.getEncryptKey() + " 加密算法");
        }
    }

}
