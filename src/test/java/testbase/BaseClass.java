package testbase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.impl.Log4JLogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.cucumber.java.After;



public class BaseClass {
	public static WebDriver driver;
	public  static Properties p;
	public static Logger logger;
	@BeforeTest (groups= {"sanity","smoke","regression"})
	@Parameters({"browser"})
	public void driverSetUp(String browser_name)
	{
		logger = LogManager.getLogger(this.getClass());
		if(browser_name.equalsIgnoreCase("Chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(browser_name.equalsIgnoreCase("Edge"))
		{
			driver = new EdgeDriver();
		}
		else
		{
			System.out.println("Enter above mentioned browser name");
		}
		driver.manage().deleteAllCookies();
		driver.get("https://www.zigwheels.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
	}
	@AfterTest(groups= {"sanity","smoke","regression"})
	public static void exit() {
		driver.quit();
	}

	public String takeScreenshot(String name) throws IOException
	{
		//File fileobj = ((TakesScreenshot)driver).getScreenshotAs;
		File fileobj = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	String targetFile = System.getProperty("user.dir")+"\\screenshots\\"+name+".png";
    	File targetfiles = new File(targetFile);
    	FileUtils.copyFile(fileobj, targetfiles);
    	return targetFile;
	}
	
	public Properties getProperties() throws IOException {
		 FileReader file=new FileReader("C:\\Users\\2303947\\OneDrive - Cognizant\\Desktop\\miniproject\\2303947\\src\\test\\resources\\config.properties");
		p=new Properties();
		 p.load(file);
		 return p;
	 }
}
