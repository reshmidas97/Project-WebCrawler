package webcrawler;

import com.rabbitmq.client.CancelCallback;

public class CancelCallbackInterface  implements CancelCallback
{
	public void handle(String consumerTag)
	{
		System.out.println("Consumer Tag : " + consumerTag);
		System.out.println("Delivery Message Failed");
	}
}
