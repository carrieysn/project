package mock.merchant.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mock.merchant.common.tool.ConnectionTool;
import org.springframework.util.StringUtils;

public class GetLcListServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetLcListServlet.class);
    private static final long serialVersionUID = 3887231283442971919L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        String mrchId = req.getParameter("mrchId");
        String lcId = req.getParameter("lcId");

        String startCreateTime = startDate + " 00:00:00";
        String endCreateTime = endDate + " 23:59:59";

        StringBuilder sql = new StringBuilder("SELECT * FROM CIFPAY_LC lc WHERE 1=1");
        if (!StringUtils.isEmpty(startDate)) {
            sql.append(" AND lc.CREATE_TIME >= STR_TO_DATE('" + startCreateTime + "', '%Y-%m-%d %H:%i:%s')");
        }
        if (!StringUtils.isEmpty(endDate)) {
            sql.append(" AND lc.CREATE_TIME <= STR_TO_DATE('" + endCreateTime + "', '%Y-%m-%d %H:%i:%s')");
        }
        if (!StringUtils.isEmpty(lcId)) {
            sql.append(" AND lc.LC_ID LIKE '%" + lcId + "%'");
        }
        if (!StringUtils.isEmpty(mrchId)) {
            sql.append(" AND lc.MID LIKE '%" + mrchId + "%'");
        }
        sql.append(" ORDER BY lc.CREATE_TIME DESC LIMIT 0,100");
        Connection conn = null;
        List<Map<String, Object>> lcList = new ArrayList<Map<String, Object>>();
        try {
            conn = ConnectionTool.getConnection();
            lcList = new QueryRunner().query(conn, sql.toString(), new MapListHandler());

        } catch (Exception e) {
            LOGGER.error("#####GetRecvListServlet异常#####", e);
        } finally {
            try {
                DbUtils.close(conn);
            } catch (SQLException e) {
                LOGGER.error("#####GetRecvListServlet异常#####", e);
            }
        }
        req.setAttribute("lcList", lcList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("lcList.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
