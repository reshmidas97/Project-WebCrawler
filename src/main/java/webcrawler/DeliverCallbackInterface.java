package webcrawler;

import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

public class DeliverCallbackInterface implements DeliverCallback
{
	/*
	 * Consumes data from the RabbitMQ
	 * Sends the data as argument to be pushed in MYSQL Database
	 */
	public void handle(String consumerTag, Delivery delivery)
	{
		try 
		{
			ConsumerJDBC.writeInDatabase(new String(delivery.getBody(), "UTF-8"));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
