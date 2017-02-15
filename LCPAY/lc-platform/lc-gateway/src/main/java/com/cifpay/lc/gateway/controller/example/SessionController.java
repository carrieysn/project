//package com.cifpay.lc.gateway.controller.example;
//
//import com.cifpay.lc.gateway.controller.GatewayBaseController;
//import org.slf4j.MDC;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpSession;
//import java.util.Date;
//import java.util.Random;
//
//@Controller
//@RequestMapping("/example/session")
//public class SessionController extends GatewayBaseController {
//
//    public static final String SESSION_KEY = "test_session_key";
//
//    @RequestMapping("/")
//    public String now() {
//        MDC.put("requestId", String.valueOf(new Random().nextLong()));
//        logger.info("test session index");
//        return new Date().toString();
//    }
//
//    @ResponseBody
//    @RequestMapping(path = "/set")
//    public String set(String value, HttpSession httpSession) {
//        httpSession.setAttribute(SESSION_KEY, value);
//        logger.info("set session: {}", value);
//
//        return "成功保存Session: " + value;
//    }
//
//    @ResponseBody
//    @RequestMapping(path = "/get")
//    public Object get(HttpSession httpSession) {
//        String value = (String) httpSession.getAttribute(SESSION_KEY);
//        logger.info("get session: {}", value);
//
//        return value;
//    }
//}
