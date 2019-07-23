package mypack;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
public class Gmailmethods
{
	public WebDriver driver;
	public String launch(String o,String d,String c) 
            						throws Exception
    {
		if(o.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",
				      "E:\\batch235\\chromedriver.exe");
			driver=new ChromeDriver();
		}
		else if(o.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",
					   "E:\\batch235\\geckodriver.exe");
			driver=new FirefoxDriver();
		}
		else if(o.equalsIgnoreCase("ie"))
		{
			System.setProperty("webdriver.ie.driver",
					"E:\\batch235\\iedriverserver.exe");
			driver=new InternetExplorerDriver();
		}
		else if(o.equalsIgnoreCase("opera"))
		{
			System.setProperty("webdriver.opera.driver",
					"E:\\batch235\\operadriver.exe");
			driver=new OperaDriver();
		}
		else
		{
			System.out.println("Wrong browser");
			System.exit(0); //force stop
		}
		driver.get(d);
		Thread.sleep(5000);
		return("Done");
    }
	public String fill(String o,String d,String c) 
								  throws Exception
	{
		driver.findElement(By.xpath(o)).sendKeys(d);
		Thread.sleep(5000);
		return("Done");
	}
	public String click(String o,String d,String c) 
            						throws Exception
    {
		
		driver.findElement(By.xpath(o)).click();
		Thread.sleep(5000);
		return("Done");
    }
	public String validateuserid(String o,String d,
						  String c) throws Exception
	{
		try
		{
			if(c.equals("valid") && 
			driver.findElement(By.name("password"))
			.isDisplayed())
			{
				return("Valid Userid Test Passed");
			}
			else if(c.equals("invalid") &&
			driver.findElement(By.xpath("(//*[contains(text(),'Enter an email') or contains(text(),'find your Google')])[2]"))
			.isDisplayed())
			{
				return("Invalid Userid Test Passed");
			}
			else
			{
				return("Userid Test failed");
			}
		}
		catch(Exception e)
		{
			return("Userid Test was interrupted");
		}
	}
	public String close(String o,String d,String c) 
									throws Exception
	{
		driver.close();
		Thread.sleep(5000);
		return("Done");
	}
	public String validatepwd(String o,String d,String c)
										throws Exception
	{
		try
		{
			if(c.equalsIgnoreCase("valid") &&
			driver.findElement(By.xpath(
			"//*[text()='COMPOSE']")).isDisplayed())
			{
				return("Valid pwd test passed");
			}
			else if(c.equalsIgnoreCase("invalid") &&
			driver.findElement(By.xpath("(//*[contains(text(),'Wrong password') or contains(text(),'Enter a password')])[2]"))
			.isDisplayed())
			{
				return("Invalid pwd test passed");
			}
			else
			{
				return("Pwd test failed");
			}
		}
		catch(Exception ex)
		{
			return("Pwd test interrupted");
		}
	}
	public void screenshot()
	{
		
	}
	
}