package mock.merchant.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import mock.merchant.common.tool.ConnectionTool;

@Controller
public class GetFundController {
	private static final Logger LOGGER = LoggerFactory.getLogger(GetFundController.class);

	@RequestMapping(path="/getFundServlet")
	protected String doGet(HttpServletRequest req, HttpServletResponse resp,Model model) throws ServletException, IOException {
		String startDate = req.getParameter("startDate");
		String endDate = req.getParameter("endDate");
		String orderId = StringUtils.trimWhitespace(req.getParameter("order"));

		String startCreateTime = startDate + " 00:00:00";
		String endCreateTime = endDate + " 23:59:59";

		StringBuilder sql = new StringBuilder("SELECT * FROM CIFPAY_LC lc WHERE 1=1");
		if (!StringUtils.isEmpty(startDate)) {
			sql.append(" AND lc.CREATE_TIME >= STR_TO_DATE('" + startCreateTime + "', '%Y-%m-%d %H:%i:%s')");
		}
		if (!StringUtils.isEmpty(endDate)) {
			sql.append(" AND lc.CREATE_TIME <= STR_TO_DATE('" + endCreateTime + "', '%Y-%m-%d %H:%i:%s')");
		}
		if (!StringUtils.isEmpty(orderId)) {
			sql.append(" AND lc.BATCH_NO LIKE 'STARWISH#%" + orderId + "%'");
		}
		else{
			sql.append(" AND lc.BATCH_NO LIKE 'STARWISH#xy%'");
		}
		sql.append(" ORDER BY lc.CREATE_TIME DESC LIMIT 0,400");
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
		model.addAttribute("lcList", lcList);
		return "xyGetFund";
	}

}
