package webcrawler;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver; 

public class WebCrawler extends Thread
{
	//Overriding run() of Thread
	@Override
	public void run()
	{
		try 
		{
			WebCrawler.extractLinks();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/*
	 * Extracts links from the target Website 
	 */
	public static void extractLinks() throws Exception
	{
		System.setProperty("webdriver.chrome.driver", "/Users/reshmidas/eclipse-workspace/WebDriver/chromedriver-mac-arm64/chromedriver");

		WebDriver driver = new ChromeDriver();

			driver.get("https://www.google.com");

			//System.out.println("BrowserStack is launched successfully on Chrome!");
			//System.out.println("Title : " + driver.getTitle());
			//System.out.println("Title : " + driver.getPageSource());
			//driver.manage().window().fullscreen();
			
			//Extracting HTML Elements with tag anchor
			//findElement() : return single Element matching the criteria
			//findElements() : returns multiple Elements matching the criteria
			//return type of this function is WebElement Object
			List<WebElement> linkList = driver.findElements(By.tagName("a"));
			
			//testing(linkList);
			
			Publisher.publishMessage(linkList);

			driver.close();
	}
	
	/*
	 * Test Function to check the Element count for Consumer
	 */
	public static void testing(List<WebElement> linkList) throws Exception
	{
		int count = 0;
		//Iterating over the anchor WebElements and printing their title and links
		for(WebElement webelement : linkList)
		{	
			//System.out.print(webelement.getText() + " : ");
			//System.out.println(webelement.getAttribute("href"));
			count++;
		}
		
		System.out.println("Count :" + count);
	}
}