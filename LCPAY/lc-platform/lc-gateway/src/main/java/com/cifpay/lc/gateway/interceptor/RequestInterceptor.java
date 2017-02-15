package com.cifpay.lc.gateway.interceptor;

import com.cifpay.lc.util.logging.LoggerEnum;
import com.cifpay.lc.util.RequestIdGen;
import com.cifpay.lc.util.logging.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		// TODO Auto-generated method stub
        try {
            String requestId = RequestIdGen.resolveReqId();
            LogUtil.initMDC(requestId, LoggerEnum.Scene.GATEWAY.val);
            logger.info("gateway requestId:{}", requestId);

        } catch (Exception e) {
            e.printStackTrace();
        }

		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

}
