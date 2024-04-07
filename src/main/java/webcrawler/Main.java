package webcrawler;

public class Main {

	public static void main(String args[]) throws Exception
	{
		//Creating Exchange
		//BindExchangeQueue.createExchange();
		
		//Creating Queue
		//BindExchangeQueue.createQueue();
		
		//Binding Queue and Exchange using Routing Key
		//BindExchangeQueue.createBinding();
		
		WebCrawler webcrawler = new WebCrawler();
		ConsumerJDBC consumer = new ConsumerJDBC();
		
		//Starting crawler Thread
		webcrawler.start();
		
		//Starting ConsumerJDBC Thread
		consumer.start();
	}
}
