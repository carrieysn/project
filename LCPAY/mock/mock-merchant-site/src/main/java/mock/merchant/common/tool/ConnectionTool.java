package mock.merchant.common.tool;

import java.sql.Connection;
import java.sql.DriverManager;

import mock.merchant.common.LcGatewayConfig;

public class ConnectionTool {

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(LcGatewayConfig.getMysqlConnectString(), LcGatewayConfig.getMysqlUsername(), LcGatewayConfig.getMysqlPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;

    }
}
