package com.cifpay.lc.domain.security;

/**
 * Gateway接口最终响应给商户的统一内容格式。请留意，只有在商户请求满足基本校验、验签通过的情况才可以返回带签名的内容给客户端，防止客户端恶意套取、
 * 反推任意商户ID对应的签名KEY。
 */
public final class MerchantSignedResponse extends AbstractApiResponse {
    private static final long serialVersionUID = -6651089357052623869L;

    private String sign;
    private String encodedJsonData;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getEncodedJsonData() {
        return encodedJsonData;
    }

    public void setEncodedJsonData(String encodedJsonData) {
        this.encodedJsonData = encodedJsonData;
    }

}
