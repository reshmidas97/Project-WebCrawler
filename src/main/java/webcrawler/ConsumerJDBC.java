package webcrawler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;

public class ConsumerJDBC extends Thread
{	
	//Overriding run() of Thread
	@Override
	public void run()
	{
		try 
		{
			ConsumerJDBC.rabbitmq();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void writeInDatabase(String message) throws Exception
	{	
		Connection connection = DriverManager.getConnection(JDBCConfigs.url, JDBCConfigs.username, JDBCConfigs.password);
		
		//String query = "INSERT INTO links" + "(URL)" + "Values('" + message + "');";
		
		//Statement statement = connection.prepareStatement(query);
		//statement.execute(query);
		//statement.executeUpdate();
		
		String query = "INSERT INTO links (URL) VALUES(?)";
		
	    PreparedStatement statement = connection.prepareStatement(query);
	    
	    statement.setObject(1,message);;
	    statement.execute();

		statement.close();
		connection.close();
	}
	
	public static void rabbitmq() throws Exception
	{
		Channel channel = new ConnectionFactory().newConnection(CommonConfigs.AMQP_URL).createChannel();
		
		channel.basicConsume("mysqlQ", true, new DeliverCallbackInterface(), new CancelCallbackInterface());
		
		//channel.close();
	}

}
