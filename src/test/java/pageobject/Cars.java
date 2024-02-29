package pageobject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class Cars extends BasePage
{
	List<String> popular_carmodels = new ArrayList<String>();

	public Cars(WebDriver driver) {
		super(driver);
	}
	
	//Locators
	
	//Usedcars
	@FindBy(xpath="//a[text()=\"Used Cars\"]")
	WebElement usedcars;
	
	//ChennaiUsedCars
	@FindBy(xpath="//span[@onclick=\"goToUrl('/used-car/Chennai')\"]")
	WebElement chennai;
	
	//Zigwheels logo
	@FindBy(xpath="//img[@data-track-label='zw-header-logo']")
	WebElement logo;
	
	//Brand and model (scrolling)
	@FindBy(xpath="//span[text()='Brand and Model']")
	
	public WebElement brandandmodel;
	
	//List of cars
	@FindBy(xpath="//ul[@class='zw-sr-secLev usedCarMakeModelList popularModels ml-20 mt-10']/li")
	List<WebElement> popular_models;
	
	
	
	//Action classes
	
	//to move to used cars
	
	public void used_cars()
	{
		Actions act = new Actions(driver);
		act.moveToElement(usedcars).perform();
	}
	
	//to select the chennai option
	
	public void chennai()
	{
		chennai.click();
	}
	
	public List<String> getpopularModels()
	{
		for(WebElement model :popular_models )
		{
			popular_carmodels.add(model.getText());

		}
		return popular_carmodels;
	}
	//to click on zigwheels logo
	
	public void home()
	{
		logo.click();
	}
	
	
	
}
