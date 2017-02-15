package com.cifpay.lc.util.logging;

import org.slf4j.MDC;
import com.cifpay.lc.util.hardware.LocalIpAddressUtil;

import java.util.Arrays;

/**
 * 设置 slf4j context
 * 使用方法：
 * logback-config.xml 中配置格式化文本 %X{requestId}
 * 例子：
 * <pattern>%date %level [%X{requestId}] [%thread] %logger{30} %msg%n</pattern>
 */
public final class LogUtil {

    /**
     * 请求Id
     */
    public static final String REQUEST_ID_KEY = "requestId";
    /**
     * 使用场景
     */
    public static final String SCENE_KEY = "scene";
    /**
     * Node 节点IP地址
     */
    public static final String IP_KEY = "ipAddress";

    /**
     * 设置MDC
     * 每个应用入口设置
     *
     * @param requestId
     */
    public static void initMDC(String requestId, String scene) {
        MDC.put(REQUEST_ID_KEY, requestId);
        MDC.put(SCENE_KEY, scene);
        MDC.put(IP_KEY, String.join(",", LocalIpAddressUtil.resolveLocalIps()));
    }
}
