package webcrawler;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;

public class BindExchangeQueue 
{
	public static void main(String args[]) throws Exception
	{
		BindExchangeQueue.createExchange();
		BindExchangeQueue.createQueue();
		BindExchangeQueue.createBinding();
	}
	public static void createExchange() throws Exception
	{
		Channel channel = new ConnectionFactory().newConnection(CommonConfigs.AMQP_URL).createChannel();
		
		channel.exchangeDeclare("webcrawler", BuiltinExchangeType.DIRECT, true);
		
		channel.close();
	}
	
	public static void createQueue() throws Exception
	{
		Channel channel = new ConnectionFactory().newConnection(CommonConfigs.AMQP_URL).createChannel();
		
		channel.queueDeclare("mysqlQ", true, false, false, null);
		
		channel.close();
	}
	
	public static void createBinding() throws Exception
	{
		Channel channel = new ConnectionFactory().newConnection(CommonConfigs.AMQP_URL).createChannel();
		
		channel.queueBind("mysqlQ", "webcrawler", "readwrite");
		
		channel.close();
	}
}
