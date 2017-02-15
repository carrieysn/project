package com.cifpay.lc.core.http;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.core.exception.CoreHttpException;

@Component
public class HttpUtils {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    public static final String CHARSET = "UTF-8";

    @Autowired
    HttpConnectionManager connectionManager;

    public <T> T get(String path, Class<T> clazz) {
        CloseableHttpClient httpClient = connectionManager.getHttpClient();
        HttpGet get = new HttpGet(path);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(get);
            StatusLine statusLine = response.getStatusLine();
            if (statusLine.getStatusCode() == 200) {
                String json = EntityUtils.toString(response.getEntity(), CHARSET);
                return JSON.parseObject(json, clazz);
            } else {
                throw new CoreHttpException(statusLine.getStatusCode(), "http请求不成功");
            }
        } catch (Exception e) {
            logger.error("http请求异常,err:{}", e);
            throw new CoreHttpException(ReturnCode.UNKNOWN_ERROR, "http异常");
        } finally {
            connectionManager.close(response);
        }
    }

    public String post(String path, Map<String, String> params) {
        CloseableHttpClient httpClient = connectionManager.getHttpClient();
        HttpPost post = new HttpPost(path);
        CloseableHttpResponse response = null;
        try {
            if (params != null) {
                List<NameValuePair> list = new ArrayList<NameValuePair>();
                for (String key : params.keySet()) {
                    list.add(new BasicNameValuePair(key, params.get(key)));
                }
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, CHARSET);
                post.setEntity(entity);
            }
            response = httpClient.execute(post);
            StatusLine statusLine = response.getStatusLine();
            if (statusLine.getStatusCode() == 200) {
                String strResponse = EntityUtils.toString(response.getEntity(), CHARSET);
                return strResponse;
            } else {
                throw new CoreHttpException(statusLine.getStatusCode(), "http请求不成功");
            }
        } catch (Exception e) {
            logger.error("http请求异常,err:{}", e);
            throw new CoreHttpException(ReturnCode.UNKNOWN_ERROR, "http异常");
        } finally {
            connectionManager.close(response);
        }
    }

    public <T> T post(String path, Map<String, String> params, Class<T> clazz) {
        String strResponse = post(path, params);
        return JSON.parseObject(strResponse, clazz);
    }
}
