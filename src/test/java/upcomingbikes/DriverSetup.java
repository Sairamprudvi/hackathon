package upcomingbikes;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class DriverSetup
{
	public static WebDriver driver;
	static List<Integer> index = new ArrayList<Integer>();
	public static void driver() 
	{
	Scanner sc = new Scanner(System.in);
	System.out.println("Enter the browser name you want to perform testing:");
	String browser = sc.nextLine();
	sc.close();
	if(browser.equalsIgnoreCase("Chrome"))
	{
		driver = new ChromeDriver();
	}
	else if(browser.equalsIgnoreCase("firefox"))
	{
		driver = new FirefoxDriver();
	}
	else if(browser.equalsIgnoreCase("edge"))
	{
		driver = new EdgeDriver();
	}
	}
	public static void urlLogin() 
	{
	driver.manage().window().maximize();
	driver.get("https://www.zigwheels.com/");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	public static void cars() throws InterruptedException
	{
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//a[text()=\"Used Cars\"]"))).perform();
		driver.findElement(By.xpath("//span[@onclick=\"goToUrl('/used-car/Chennai')\"]")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement scroll_upto = driver.findElement(By.xpath("//span[text()=\"Brand and Model\"]"));
		js.executeScript("arguments[0].scrollIntoView(true);", scroll_upto);
		List<WebElement> popular_models = driver.findElements(By.xpath("//ul[@class=\"zw-sr-secLev usedCarMakeModelList popularModels ml-20 mt-10\"]//li"));
		for(WebElement pop_model:popular_models)
		{
			String model = pop_model.getText();
			System.out.println(model);
			
		}
		driver.findElement(By.xpath("//img[@data-track-label='zw-header-logo']")).click();
	}
	public static void bikes() throws InterruptedException 
	{
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//a[text()=\"New Bikes\"]"))).perform();
		driver.findElement(By.xpath("//span[text()=\"Upcoming Bikes\"]")).click();
		WebElement brands = driver.findElement(By.xpath("//select[@id=\"makeId\"]"));
		Select sc = new Select(brands);
		sc.selectByValue("53");
		Thread.sleep(2000);
		WebElement readmore = driver.findElement(By.xpath("//span[text()=\"...Read More\"]"));
		readmore.click();
		List<WebElement> model_names = driver.findElements(By.xpath("//tr[@class=\'ml-15 \']//td[1]"));
		List<WebElement> bike_prices = driver.findElements(By.xpath("//tr[@class=\'ml-15 \']//td[2]"));
		for(int i=0;i<bike_prices.size();i++)
		{
			String parseString = bike_prices.get(i).getText().replaceAll("[^\\d]", "");
			
			int value = Integer.parseInt(parseString) * 1000;
			if((value<400000 &&  !(bike_prices.get(i).getText().contains("crore")))||!bike_prices.get(i).getText().contains(" Lakh"))
			{
				index.add(i);
			}
			
		}
		List<WebElement> launch_dates = driver.findElements(By.xpath("//tr[@class=\'ml-15 \']//td[3]"));
		for(int i=0;i<model_names.size();i++)
		{
			if(index.contains(i))
			{
				System.out.println(model_names.get(i).getText()+" -> "+bike_prices.get(i).getText()+" -> "+launch_dates.get(i).getText());
			}
		}
		Thread.sleep(2000);
	}
	public static void loginGoogle() throws InterruptedException
	{
	driver.findElement(By.xpath("//*[@id=\"forum_login_title_lg\"]")).click();
	//Thread.sleep(5000);
	driver.findElement(By.xpath("//*[@id=\"myModal3-modal-content\"]/div[1]/div/div[3]/div[6]/div")).click();
	
	Set<String> windowid = driver.getWindowHandles();
    List<String> windowids = new ArrayList<String>(windowid);
	driver.switchTo().window(windowids.get(1));

	driver.findElement(By.xpath("//input[@autocomplete=\"username\"]")).sendKeys("sghsv@gmail.com");
	driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/div/button/span")).click();
	Thread.sleep(3000);
	String warning_text=driver.findElement(By.xpath("")).getText();
	System.out.println("The warning message : "+warning_text);//div[text()='Couldn’t find your Google Account']
	}
	
	public static void screenshot(String Name) throws IOException, InterruptedException
	
	
	{
		File fileobj = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
     	String targetFile = System.getProperty("user.dir")+"\\screenshots\\"+Name+".png";
     	File targetfiles = new File(targetFile);
     	FileUtils.copyFile(fileobj, targetfiles);
    	Set<String> windowid = driver.getWindowHandles();
        List<String> windowids = new ArrayList<String>(windowid);
    	driver.switchTo().window(windowids.get(1));
     	Thread.sleep(3000);
		
	}
	public static void main(String args[]) throws InterruptedException, IOException {
		
		driver();
		urlLogin() ;
		bikes();
		cars();
		loginGoogle();
		screenshot("warning");
		driver.quit();
		
	}
}