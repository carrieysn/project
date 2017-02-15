package com.cifpay.lc.gateway.controller.bank;

import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.api.gateway.bank.PaymentSuccessService;
import com.cifpay.lc.gateway.common.exception.GatewayUnionException;
import com.cifpay.lc.gateway.controller.GatewayBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 银联回调处理
 *
 * @author sweet
 */
@Controller
@RequestMapping(path = {"/bank/unionpay/callback"})
public class UnionPayCallBackController extends GatewayBaseController {

    @Autowired
    private PaymentSuccessService paymentSuccessService;

    /**
     * 通用处理交易类的回调
     *
     * @param req
     * @param resp
     * @return
     * @throws IOException
     */
    @RequestMapping(path = "/general", method = {RequestMethod.POST, RequestMethod.GET})
    public void general(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.debug("*****************************UnionPayCallBackController_general()*******************");
        String encoding = req.getParameter("encoding");

        // 获取银联通知服务器发送的后台通知参数
        Map<String, String> reqParam = getAllRequestParam(req);

        HashMap<String, String> valideData = wrapValiData(encoding, reqParam);

        paymentSuccessService.GeneralCallBack(encoding, valideData);

        resp.setStatus(200);

        PrintWriter writer = resp.getWriter();
        writer.print("OK");
        writer.flush();
        writer.close();
    }

    /**
     * 通用处理交易类的回调
     *
     * @param req
     * @param resp
     * @return
     * @throws IOException
     */
    @RequestMapping(path = "/dingCertification", method = {RequestMethod.POST, RequestMethod.GET})
    public void dingCertification(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("*****************************UnionPayCallBackController_dingCertification()*******************");

        resp.setStatus(200);

        PrintWriter writer = resp.getWriter();
        writer.print("OK");
        writer.flush();
        writer.close();
    }

    @RequestMapping(path = "/openCardBack", method = {RequestMethod.POST, RequestMethod.GET})
    public void openCardBack(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.debug("*****************************UnionPayCallBackController_openCardBack()*******************");
        String encoding = req.getParameter("encoding");

        // 获取银联通知服务器发送的后台通知参数
        Map<String, String> reqParam = getAllRequestParam(req);

        HashMap<String, String> valideData = wrapValiData(encoding, reqParam);

        paymentSuccessService.openCardBack(encoding, valideData);

        resp.setStatus(200);

        PrintWriter writer = resp.getWriter();
        writer.print("OK");
        writer.flush();
        writer.close();
    }

    @RequestMapping(path = "/openCardFront", method = {RequestMethod.POST, RequestMethod.GET})
    public String openCardFront(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        logger.debug("*****************************UnionPayCallBackController_openCardFront()*******************");
        String encoding = req.getParameter("encoding");

        // 获取银联通知服务器发送的后台通知参数
        Map<String, String> reqParam = getAllRequestParam(req);

        HashMap<String, String> valideData = wrapValiData(encoding, reqParam);

        BusinessOutput<Boolean> openCardOutput = paymentSuccessService.openCardFront(encoding, valideData);

        if (openCardOutput.isSuccess()) {
            return "redirect:/bank/unionpay/" + reqParam.get("orderId") + "?tab=tabDeposit";
        }

        throw new GatewayUnionException(openCardOutput.getReturnMsg());
    }

    private HashMap<String, String> wrapValiData(String encoding, Map<String, String> reqParam)
            throws UnsupportedEncodingException {

        HashMap<String, String> valideData = null;

        if (null != reqParam && !reqParam.isEmpty()) {

            Iterator<Entry<String, String>> it = reqParam.entrySet().iterator();

            valideData = new HashMap<String, String>(reqParam.size());

            while (it.hasNext()) {
                Entry<String, String> e = it.next();
                String key = e.getKey();
                String value = e.getValue();
                value = new String(value.getBytes(encoding), encoding);
                valideData.put(key, value);
            }
        }

        return valideData;
    }

    private static Map<String, String> getAllRequestParam(final HttpServletRequest request) {

        Map<String, String> res = new HashMap<String, String>();

        Enumeration<String> temp = request.getParameterNames();

        if (null != temp) {

            while (temp.hasMoreElements()) {

                String en = temp.nextElement();

                String value = request.getParameter(en);

                res.put(en, value);

                if (null == res.get(en) || "".equals(res.get(en))) {
                    res.remove(en);
                }
            }
        }

        return res;
    }
}
