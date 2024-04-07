package webcrawler;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.rabbitmq.client.*;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Publisher
{	
	/*
	 * Publishes message in the RabbitMQ
	 */
	public static void publishMessage(List<WebElement> linkList) throws Exception
	{
		Channel channel = new ConnectionFactory().newConnection(CommonConfigs.AMQP_URL).createChannel();
		
		for(WebElement webelement : linkList)
		{			
			String message = webelement.getAttribute("href");
			channel.basicPublish("webcrawler", "readwrite", null, message.getBytes());
			
		}
		channel.close();
	}
}
