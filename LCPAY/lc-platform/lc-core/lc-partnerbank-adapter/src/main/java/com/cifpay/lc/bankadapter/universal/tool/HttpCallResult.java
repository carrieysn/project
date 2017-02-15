package com.cifpay.lc.bankadapter.universal.tool;

import org.apache.http.HttpEntity;

/**
 * Http响应包装对像
 * @author songhaipeng
 *2013年12月4日
 */ 
public class HttpCallResult {
    private int statusCode;
    private HttpEntity httpEntity;
    private String content;
    
    public int getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    public HttpEntity getHttpEntity() {
        return httpEntity;
    }
    public void setHttpEntity(HttpEntity httpEntity) {
        this.httpEntity = httpEntity;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    
    
    
}
