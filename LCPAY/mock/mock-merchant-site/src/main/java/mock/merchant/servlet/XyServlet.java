package mock.merchant.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeoutException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mock.merchant.common.LcGatewayConfig;
import mock.merchant.common.tool.DateTool;
import mock.merchant.common.tool.HTTPTool;
import mock.merchant.common.tool.LcMd5SignTool;
import mock.merchant.common.tool.RabbitMQTool;
import net.sf.json.JSONObject;
import org.springframework.util.StringUtils;

public class XyServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(XyServlet.class);
    private static final long serialVersionUID = -5187512519922471234L;
    public static HashMap<String, HashMap<String, String>> map = new HashMap<String, HashMap<String, String>>();
    public static HashMap<String, String> getFundMap = new HashMap<String, String>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String type = getType(req);
        if (type == null) {
            return;
        }
        if (dispatch(type, req, resp)) {
            return;
        }
        String url = LcGatewayConfig.getVersionBInterfaceURL(type);
        if (url == null) {
            return;
        }
        String amount = req.getParameter("amount");
        if (StringUtils.isEmpty(amount)) {
            return;
        }
        String requestId = String.valueOf(new Date().getTime());

        HashMap<String, String> reqDataMap = HTTPTool.getReqDataMap(req);
        reqDataMap.put("requestId", requestId);
        reqDataMap.put("requestTime", DateTool.getYYYYMMddHHmmss());
        reqDataMap.put("merId", LcGatewayConfig.getMockStarwishMerchantId());
        reqDataMap.put("amount", String.valueOf(Long.valueOf(amount) * 10));// 将角转化为分
        String lcIdString = null;
        if ("getFund".equals(type)) {
            lcIdString = reqDataMap.get("lcIdString");
            reqDataMap.remove("lcIdString");
        }
        String strSign = LcMd5SignTool.signMap(reqDataMap, LcGatewayConfig.getMockStarwishMerchantSignKey());
        reqDataMap.put("sign", strSign);

        if ("getFund".equals(type)) {
            JSONObject json = JSONObject.fromObject(reqDataMap);
            String html = "";
            try {
                html = HTTPTool.post(url, json);
            } catch (Exception e) {
                LOGGER.error("↓↓↓↓↓xyServlet异常↓↓↓↓↓", e);
                html = "异常：" + e.getMessage();
            }

            JSONObject obj = JSONObject.fromObject(html);
            String returnCode = obj.getString("returnCode");
            if ("SUCCESS".equals(returnCode)) {
                getFundMap.put(requestId, lcIdString);
            }
            req.setAttribute("returnCode", returnCode);
            req.setAttribute("returnData", html);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/result.jsp");
            dispatcher.forward(req, resp);

            return;
        }

        String html = "";
        try {
            map.put(requestId, reqDataMap);
            html = HTTPTool.postMap(url, reqDataMap);
            LOGGER.debug("银信证网关返回HTML：{}", html);
        } catch (Exception e) {
            LOGGER.error("↓↓↓↓↓OpenLcServlet异常↓↓↓↓↓", e);
            html = "异常：" + e.getMessage();
        }

		/*
         * resp.setContentType("text/html;charset=UTF-8"); //
		 * resp.setCharacterEncoding("UTF-8"); PrintWriter out =
		 * resp.getWriter(); out.print(html); out.flush();
		 */
        if ("rechargePay".equals(type)) {
            // 线上充值特别处理：
            if (html != null
                    && (html.startsWith("https://") || html.contains("http://"))) {
                resp.sendRedirect(html);
            } else {
                req.setAttribute("returnCode", "操作失败!");
                req.setAttribute("returnData", html);
                map.remove(requestId);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/result.jsp");
                dispatcher.forward(req, resp);
            }
        } else if ("offline".equals(type)) {
            JSONObject obj = JSONObject.fromObject(html);
            String returnCode = obj.getString("returnCode");
            if (!"SUCCESS".equals(returnCode)) {
                map.remove(requestId);
            } else {
                // 发送MQ
                try {
                    sendMQ(type, requestId, null);
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
            req.setAttribute("returnCode", returnCode);
            req.setAttribute("returnData", html);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/result.jsp");
            dispatcher.forward(req, resp);
        }

    }

    public boolean dispatch(String type, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        boolean b = false;
        if ("rechargeReturnUrl".equals(type)) {
            // 处理充值回调
            String returnCode = req.getParameter("returnCode");
            if ("SUCCESS".equals(returnCode)) {
                HashMap<String, String> reqDataMap = HTTPTool.getReqDataMap(req);
                req.setAttribute("returnCode", returnCode);
                req.setAttribute("returnData", reqDataMap);
            }
            b = true;
            RequestDispatcher dispatcher = req.getRequestDispatcher("/result.jsp");
            dispatcher.forward(req, resp);
        } else if ("rechargeNoticeUrl".equals(type)) {
            // 处理充值异步通知
            String returnCode = req.getParameter("returnCode");
            if ("SUCCESS".equals(returnCode)) {
                HashMap<String, String> reqDataMap = HTTPTool.getReqDataMap(req);
                req.setAttribute("returnCode", returnCode);
                req.setAttribute("returnData", reqDataMap);
                PrintWriter p = resp.getWriter();
                p.print("success");
                p.flush();
                LOGGER.info("处理提现异步通知答复:success");

                // 发送MQ
                try {
                    String requestId = reqDataMap.get("requestId");
                    sendMQ(type, requestId, null);
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
            b = true;
        } else if ("getFundNoticeUrl".equals(type)) {
            // 处理提现异步通知
            InputStream stream = req.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            StringBuilder json = new StringBuilder();
            String line;
            while (null != (line = br.readLine())) {
                json.append(line);
            }
            JSONObject jsonObj = JSONObject.fromObject(json.toString());
            String returnCode = jsonObj.getString("returnCode");
            String requestId = jsonObj.getString("requestId");
            String orderId = jsonObj.getString("orderId");
            if ("SUCCESS".equals(returnCode)) {
                // 发送MQ
                try {
                    sendMQ(type, requestId, orderId);
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
                PrintWriter p = resp.getWriter();
                p.print("SUCCESS");
                p.flush();
                LOGGER.info("处理提现异步通知答复:SUCCESS");
            }
        }
        return b;
    }

    private void sendMQ(String type, String requestId, String orderId) throws IOException, TimeoutException {

        if ("getFundNoticeUrl".equals(type)) {
            // 提现处理
            String[] lcList = getFundMap.get(requestId).split(",");
            if (lcList == null || lcList.length == 0) {
                return;
            }
            for (String lc : lcList) {

                String[] strings = lc.split("#");
                String msg = "{\"merid\":\"" + LcGatewayConfig.getMockStarwishMerchantId() + "\",\"withdrawOrderId\":\"" + orderId + "\",\"lcId\":\""
                        + strings[0] + "\"}";
                LOGGER.info("生成提现数据：" + msg);
                BigDecimal b = new BigDecimal(strings[1]);
                String m = String.valueOf(b.longValue());
                LOGGER.info("金额(分)：" + m);
                String exchange = "";
                if ("10000".equals(m)) {
                    exchange = "withdraw_personal_sw_n100_exchange";
                } else if ("5000".equals(m)) {
                    exchange = "withdraw_personal_sw_n50_exchange";
                } else if ("2000".equals(m)) {
                    exchange = "withdraw_personal_sw_n20_exchange";
                } else if ("1000".equals(m)) {
                    exchange = "withdraw_personal_sw_n10_exchange";
                } else if ("500".equals(m)) {
                    exchange = "withdraw_personal_sw_n5_exchange";
                } else if ("100".equals(m)) {
                    exchange = "withdraw_personal_sw_n1_exchange";
                } else if ("50".equals(m)) {
                    exchange = "withdraw_personal_sw_n05_exchange";
                } else if ("10".equals(m)) {
                    exchange = "withdraw_personal_sw_n01_exchange";
                }
                RabbitMQTool.send(exchange, msg);
            }

            map.remove(requestId);
            return;
        } else {
            HashMap<String, String> data = map.get(requestId);
            if (data == null) {
                LOGGER.info("未找到相关信息，requestId=" + requestId);
                return;
            }
            String amount = data.get("amount");
            String cusType = data.get("type");
            String pre = "";
            if ("1".equals(cusType) && type.contains("recharge")) {
                pre = "personal_sw";
            } else if ("2".equals(cusType) && type.contains("recharge")) {
                pre = "sw";
            } else if ("offline".equals(type)) {
                pre = "sw";
            }
            int amt = Integer.valueOf(amount);
            boolean is100 = true; // 0
            boolean is50 = true; // 1
            boolean is20 = true; // 2
            boolean is10 = true;// 3
            boolean is5 = true;// 4
            boolean is1 = true;// 5
            boolean is05 = true;// 6
            boolean is01 = true;// 7
            Random r = new Random();
            String order = data.get("orderId");

            int i = 0;
            while (amt > 0) {
                int random = r.nextInt(8);
                String msg = "";
                LOGGER.info("随机生成数：" + random);
                LOGGER.info("余额：" + amt);
                if (random == 0 && (amt - 10000) >= 0 && is100) {
                    msg = "{\"orderid\":\"" + order + "\",\"merid\":\"" + LcGatewayConfig.getMockStarwishMerchantId()
                            + "\",\"type\":\"n100\",\"sno\":\"" + ++i + "\"}";
                    LOGGER.info("随机生成信息：{}" + msg);
                    RabbitMQTool.send(pre + "_n100_exchange", msg);
                    amt = amt - 10000;
                    if (amt - 10000 < 0) {
                        is100 = false;
                    }
                } else if (random == 1 && (amt - 5000) >= 0 && is50) {
                    msg = "{\"orderid\":\"" + order + "\",\"merid\":\"" + LcGatewayConfig.getMockStarwishMerchantId()
                            + "\",\"type\":\"n50\",\"sno\":\"" + ++i + "\"}";
                    LOGGER.info("随机生成信息：{}" + msg);
                    RabbitMQTool.send(pre + "_n50_exchange", msg);
                    amt = amt - 5000;
                    if (amt - 5000 < 0) {
                        is50 = false;
                    }
                } else if (random == 2 && (amt - 2000) >= 0 && is20) {
                    msg = "{\"orderid\":\"" + order + "\",\"merid\":\"" + LcGatewayConfig.getMockStarwishMerchantId()
                            + "\",\"type\":\"n20\",\"sno\":\"" + ++i + "\"}";
                    LOGGER.info("随机生成信息：{}" + msg);
                    RabbitMQTool.send(pre + "_n20_exchange", msg);
                    amt = amt - 2000;
                    if (amt - 2000 < 0) {
                        is20 = false;
                    }
                } else if (random == 3 && (amt - 1000) >= 0 && is10) {
                    msg = "{\"orderid\":\"" + order + "\",\"merid\":\"" + LcGatewayConfig.getMockStarwishMerchantId()
                            + "\",\"type\":\"n10\",\"sno\":\"" + ++i + "\"}";
                    LOGGER.info("随机生成信息：{}" + msg);
                    RabbitMQTool.send(pre + "_n10_exchange", msg);
                    amt = amt - 1000;
                    if (amt - 1000 < 0) {
                        is10 = false;
                    }
                } else if (random == 4 && (amt - 500) >= 0 && is5) {
                    msg = "{\"orderid\":\"" + order + "\",\"merid\":\"" + LcGatewayConfig.getMockStarwishMerchantId()
                            + "\",\"type\":\"n5\",\"sno\":\"" + ++i + "\"}";
                    LOGGER.info("随机生成信息：{}" + msg);
                    RabbitMQTool.send(pre + "_n5_exchange", msg);
                    amt = amt - 500;
                    if (amt - 500 < 0) {
                        is5 = false;
                    }
                } else if (random == 5 && (amt - 100) >= 0 && is1) {
                    msg = "{\"orderid\":\"" + order + "\",\"merid\":\"" + LcGatewayConfig.getMockStarwishMerchantId()
                            + "\",\"type\":\"n1\",\"sno\":\"" + ++i + "\"}";
                    LOGGER.info("随机生成信息：{}" + msg);
                    RabbitMQTool.send(pre + "_n1_exchange", msg);
                    amt = amt - 100;
                    if (amt - 100 < 0) {
                        is1 = false;
                    }
                } else if (random == 6 && (amt - 50) >= 0 && is05) {
                    msg = "{\"orderid\":\"" + order + "\",\"merid\":\"" + LcGatewayConfig.getMockStarwishMerchantId()
                            + "\",\"type\":\"n05\",\"sno\":\"" + ++i + "\"}";
                    LOGGER.info("随机生成信息：{}" + msg);
                    RabbitMQTool.send(pre + "_n05_exchange", msg);
                    amt = amt - 50;
                    if (amt - 50 < 0) {
                        is05 = false;
                    }
                } else if (random == 7 && (amt - 10) >= 0 && is01) {
                    msg = "{\"orderid\":\"" + order + "\",\"merid\":\"" + LcGatewayConfig.getMockStarwishMerchantId()
                            + "\",\"type\":\"n01\",\"sno\":\"" + ++i + "\"}";
                    LOGGER.info("随机生成信息：{}" + msg);
                    RabbitMQTool.send(pre + "_n01_exchange", msg);
                    amt = amt - 10;
                    if (amt - 10 < 0) {
                        is01 = false;
                    }
                } else {
                    LOGGER.info("##### 随机生成信息余额不足！ #####");
                }
            }
            map.remove(requestId);
            LOGGER.info("随机生成信息数据数：" + i);
        }
    }

    public void sj() {

    }

    private String getType(HttpServletRequest req) {
        String reqURI = req.getRequestURI();
        if (reqURI.contains("rechargePay")) {
            // 充值
            return "rechargePay";
        } else if (reqURI.contains("rechargeReturnUrl")) {
            // 充值回调
            return "rechargeReturnUrl";
        } else if (reqURI.contains("rechargeNoticeUrl")) {
            // 充值异步通知
            return "rechargeNoticeUrl";
        } else if (reqURI.contains("offline")) {
            // 线下充值
            return "offline";
        } else if (reqURI.contains("getFund")) {
            // 提现
            return "getFund";
        } else if (reqURI.contains("fundNoticeUrl")) {
            // 提现异步通知
            return "getFundNoticeUrl";
        } else if (reqURI.contains("defer.lc")) {
            // 展期
            return "defer.lc";
        } else if (reqURI.contains("transfer.lc")) {
            return "transfer.lc";
        } else if (reqURI.contains("suspend.lc")) {
            return "suspend.lc";
        }
        return null;
    }
}
