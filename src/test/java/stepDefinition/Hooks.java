package stepDefinition;
import java.io.IOException;
import java.util.Properties;
 
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import pageFactory.BaseClass;
 
 
public class Hooks {
 
	public static  WebDriver driver;
	public static Properties p;
	@BeforeAll
    public static void beforeAll() throws IOException
    {
    	driver=new ChromeDriver();
    	
    	
    	p=BaseClass.getProperties();
    	driver.manage().window().maximize();
    	driver.get(p.getProperty("appURL"));
    	

	}

    @AfterAll
    public static void afterAll() {
       driver.quit();
    }
    public WebDriver getDriver() {
    	return driver;
    }

 
    @AfterStep
    public void addScreenshot(Scenario scenario) {
    	// this is for cucumber junit report

        	TakesScreenshot ts=(TakesScreenshot) driver;
        	byte[] screenshot=ts.getScreenshotAs(OutputType.BYTES);
        	scenario.attach(screenshot, "image/png",scenario.getName());


    }
}


