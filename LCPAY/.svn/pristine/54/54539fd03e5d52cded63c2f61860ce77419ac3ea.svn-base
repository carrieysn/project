package com.cifpay.lc.core.cache.pojo;

import java.io.Serializable;

import com.cifpay.lc.core.cache.bean.BaseCache;
import com.cifpay.lc.util.security.Constants;

public class MerchantCache extends BaseCache implements Serializable {

    private static final long serialVersionUID = 4178766583441777514L;

    private Integer id;

    private String merCode;

    private String merchantName;

    private String merSiteName;

    private String merSiteDomain;

    private String encryptType;

    private String encryptKey;

    private String decryptKey;

    private String bankAccount;    //银行账号

    private String bankCode;    //银行代码

    private String merchantStatus;

    public MerchantCache() {
    }


    public boolean isExists() {
        return null != merCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getMerCode() {
        return merCode;
    }

    public void setMerCode(String merCode) {
        this.merCode = merCode;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerSiteName() {
        return merSiteName;
    }

    public void setMerSiteName(String merSiteName) {
        this.merSiteName = merSiteName;
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

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getMerchantStatus() {
        return merchantStatus;
    }

    public void setMerchantStatus(String merchantStatus) {
        this.merchantStatus = merchantStatus;
    }

    public String unpackEncryptKey() throws IllegalArgumentException {
        if (Constants.ENCRYPT_DES.equals(this.getEncryptType())
                || Constants.ENCRYPT_MD5.equals(this.getEncryptType())) {
            return this.getEncryptKey();
        } else if (Constants.ENCRYPT_RAS.equals(this.getEncryptType())) {
            //获取商户的公钥（RAS加密，数据库decryptKey存放商户的公钥）
            return this.getDecryptKey();
        } else {
            throw new IllegalArgumentException("暂时不支持的加密算法");
        }
    }

    public String unpackDecryptKey() throws IllegalArgumentException {
        if (Constants.ENCRYPT_DES.equals(this.getEncryptType())
                || Constants.ENCRYPT_MD5.equals(this.getEncryptType())
                || Constants.ENCRYPT_RAS.equals(this.getEncryptType())) {
            return this.getEncryptKey();
        } else {
            throw new IllegalArgumentException("暂时不支持" + this.getEncryptKey() + " 加密算法");
        }
    }

}
