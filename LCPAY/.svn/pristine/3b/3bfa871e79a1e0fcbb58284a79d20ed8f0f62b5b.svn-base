package mock.merchant.common.tool;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class RabbitMQTool {
	private static String routingKey ="jason";
	//private static String host = "192.168.59.210";
	private static String host = "192.168.163.3";
	private static int port = 5672;
	private static String userName = "admin";
	private static String password = "abc123#";
	private static ConnectionFactory factory = null;
	static {
		factory = new ConnectionFactory();
		factory.setHost(host);
		factory.setPort(port);
		factory.setUsername(userName);
		factory.setPassword(password);
	}

	public static void send(String exchange, String msg) throws IOException, TimeoutException {
		Connection connection = null;
		Channel channel = null;
		try {
			connection = factory.newConnection();
			channel = connection.createChannel();
			//channel.queueDeclare("", true, false, false, null);
			channel.basicPublish(exchange, routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				channel.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
			connection.close();
		}
	}
	
	public static void main(String[] args) throws IOException, TimeoutException {
		String merId = "1000001";
		String orderId = "2dfijwsl";
		int i = 0;
		String mString = "{\"orderid\":\"" + orderId + "\",\"merid\":\"" + merId
				+ "\",\"type\":\"n100\",\"sno\":\"" + ++i + "\"}";
		RabbitMQTool.send("withdraw_sw_n01_exchange", mString);
	}
}
