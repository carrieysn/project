package com.cifpay.lc.domain.security;

/**
 * 定义商户请求Gateway接口时所需要提供的请求参数格式，其中merId、sign、
 * encodedJsonData这3个字段是商户调用Gateway接口时的原始请求字段；
 * data是由Gateway统一将encodedJsonData解码后得到的对象。
 *
 * @param <T> encodedJsonData解码后得到的对应的Java Bean类型。
 */
public final class MerchantRequest<T> {
    public static final String MER_REQUEST_ATTR_KEY = "merchantRequest";

    private String merId;   // 商户号
    private String version; // 版本号
    private String format;  // 传输格式
    private String charset; // 编码格式

    private String sign;    // 签名
    private String encodedJsonData; //加密数据

    // 由Gateway统一将encodedJsonData解码后得到的对象。
    private T data;

    public MerchantRequest() {
        this.charset = "UTF-8";
    }

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
