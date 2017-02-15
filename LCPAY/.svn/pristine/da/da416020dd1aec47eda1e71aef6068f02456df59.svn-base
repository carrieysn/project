package mock.merchant.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mock.merchant.common.LcGatewayConfig;
import mock.merchant.common.tool.Base64;
import mock.merchant.common.tool.HTTPTool;
import mock.merchant.common.tool.LcMd5SignTool;
import net.sf.json.JSONObject;

public class LcServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(LcServlet.class);
    private static final long serialVersionUID = -6825798700395503560L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = getType(req);
        String url = LcGatewayConfig.getLcGatewayInterfaceURL(type);

        JSONObject reqData = null;
        if (type.equals("batchOpen.lc")) {

            reqData = HTTPTool.getReqBatchDataOpen(req);

        } else if (type.equals("batchApply.lc")) {

            reqData = HTTPTool.getReqBatchDataApply(req);

        } else {
            reqData = HTTPTool.getReqData(req);
        }

        String base64ReqData = Base64.encode(reqData.toString());
        String strSign = LcMd5SignTool.signString(base64ReqData, LcGatewayConfig.getMockLcStandardMerchantSignKey());

        JSONObject jsonBody = new JSONObject();
        jsonBody.put("merId", LcGatewayConfig.getMockLcStandardMerchantId());
        jsonBody.put("sign", strSign);
        jsonBody.put("encodedJsonData", base64ReqData);

        logger.debug("url: " + url);
        logger.debug("merId: " + LcGatewayConfig.getMockLcStandardMerchantId());
        logger.debug("sign: " + strSign);
        logger.debug("encodedJsonData: " + base64ReqData);

        if (type.equals("payment.lc")) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/submit.jsp");
            req.setAttribute("url", url);
            req.setAttribute("merId", LcGatewayConfig.getMockLcStandardMerchantId());
            req.setAttribute("sign", strSign);
            req.setAttribute("encodedJsonData", base64ReqData);

            dispatcher.forward(req, resp);
        }

        try {
            String html = HTTPTool.post(url, jsonBody);

            if (html.startsWith("<")) {
                return;
            }

            JSONObject json = JSONObject.fromObject(html);
            if (json.has("encodedJsonData")) {
                String encodedJsonData = json.getString("encodedJsonData");
                String response = Base64.decode(encodedJsonData);

                resp.setContentType("application/json;charset=UTF-8");
                // resp.setCharacterEncoding("UTF-8");
                try (PrintWriter out = resp.getWriter()) {
                    out.print(response);
                    out.flush();
                }
            } else {
                try (PrintWriter out = resp.getWriter()) {
                    out.write(json.toString());
                }
            }

        } catch (
                Exception e)

        {
            logger.error("↓↓↓↓↓OpenLcServlet异常↓↓↓↓↓", e);

            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter out = resp.getWriter();

            out.print("异常：" + e.getMessage());
            out.flush();
            return;
        }

    }

    private String getType(HttpServletRequest req) {
        String reqURI = req.getRequestURI();
        if (reqURI.contains("preOpen.lc")) {
            // 预开证
            return "preOpen.lc";
        } else if (reqURI.contains("open.lc")) {
            // 开证
            return "open.lc";
        } else if (reqURI.contains("recv.lc")) {
            // 收证
            return "recv.lc";
        } else if (reqURI.contains("appointment.lc")) {
            // 履约
            return "appointment.lc";
        } else if (reqURI.contains("apply.lc")) {
            // 申请解付
            return "apply.lc";
        } else if (reqURI.contains("invalidate.lc")) {
            // 失效
            return "invalidate.lc";
        } else if (reqURI.contains("defer.lc")) {
            // 展期
            return "defer.lc";
        } else if (reqURI.contains("transfer.lc")) {
            return "transfer.lc";
        } else if (reqURI.contains("suspend.lc")) {
            return "suspend.lc";
        } else if (reqURI.contains("batchOpen.lc")) {
            return "batchOpen.lc";
        } else if (reqURI.contains("batchApply.lc")) {
            return "batchApply.lc";
        } else if (reqURI.contains("payment.lc")) {
            return "payment.lc";
        } else if (reqURI.contains("smssend.lc")) {
        	//发送短信
            return "smssend.lc";
        } else if (reqURI.contains("smscheck.lc")) {
        	//验证短信验证码
            return "smscheck.lc";
        }else if (reqURI.contains("cache.lc")) {
        	//缓存
            return "cache.lc";
        }


        return null;
    }
}
