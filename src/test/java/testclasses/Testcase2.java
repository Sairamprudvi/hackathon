package testclasses;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import pageobject.Cars;
import testbase.BaseClass;
import utilities.ExcelUtilis;


public class Testcase2 extends BaseClass
{
	public Cars cars;
	@Test(priority=1, groups = "sanity")
	public void usedcars() throws IOException
	{
		cars = new Cars(driver);
		cars.used_cars();
		logger.info("Used cars is selected ");
	}
	@Test(priority=2,groups = {"regression"})
	public void state()
	{
		
		cars.chennai();
		logger.info("Used cars in chennai are shown now");
	}
	@Test(priority=3,groups = {"regression"})
	public void popularcars() throws IOException
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);",cars.brandandmodel);
		ExcelUtilis.excelOutput(cars.getpopularModels());
		/*List<String> popular_cars = cars.getpopularModels();
		for(String cars : popular_cars)
		{
			System.out.println(cars);
		}*/
		cars.home();
		logger.info("Again returned to the home page");
	}
	

}
