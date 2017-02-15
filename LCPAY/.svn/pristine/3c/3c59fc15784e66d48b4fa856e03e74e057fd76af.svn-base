package com.cifpay.lc.api.gateway.basic.signkey;

import java.io.Serializable;

import com.cifpay.lc.util.security.AESUtils;
import com.cifpay.lc.util.security.Constants;

/**
 * 用于前端(Gateway)对商户请求合法性校验所需要的资料，包括商户状态、商户签名Key。
 */
public class MerFrontValidationMaterial implements Serializable {
    private static final long serialVersionUID = -4683809269053518377L;

    private String merId;

    private String merchantName;

    private String merchantStatus;

    private String merSiteDomain;

    private String encryptType;

    private String encryptKey;

    private String decryptKey;

    private String bankAccountNo;    // 银行账号

    private String bankCode;        // 银行代码

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

    public String getMerchantStatus() {
        return merchantStatus;
    }

    public void setMerchantStatus(String merchantStatus) {
        this.merchantStatus = merchantStatus;
    }

    public String getMerSiteDomain() {
        return merSiteDomain;
    }

    public void setMerSiteDomain(String merSiteDomain) {
        this.merSiteDomain = merSiteDomain;
    }

    public String getEncryptType() {
        return encryptType;
    }

    public void setEncryptType(String encryptType) {
        this.encryptType = encryptType;
    }

    public String getEncryptKey() {
        return encryptKey;
    }

    public void setEncryptKey(String encryptKey) {
        this.encryptKey = encryptKey;
    }

    public String getDecryptKey() {
        return decryptKey;
    }

    public void setDecryptKey(String decryptKey) {
        this.decryptKey = decryptKey;
    }

    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    /**
     * 获取密钥明文
     *
     * @return
     */
    public String getSignKey() {
        if (Constants.ENCRYPT_DES.equals(this.getEncryptType())
                || Constants.ENCRYPT_MD5.equals(this.getEncryptType())) {
            return this.getEncryptKey();
        } else if (Constants.ENCRYPT_RAS.equals(this.getEncryptType())) {
            //获取商户的公钥（RAS加密，数据库decryptKey存放商户的公钥）
            return this.getDecryptKey();
        }

        throw new IllegalArgumentException("暂时不支持的加密算法");
    }
}
