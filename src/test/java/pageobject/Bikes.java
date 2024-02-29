package pageobject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class Bikes extends BasePage
{
	public List<String> model_names = new ArrayList<String>();
	public List<String> bike_prices = new ArrayList<String>();
	public List<String> launchdates = new ArrayList<String>();
	public Bikes(WebDriver driver)
	{

	super(driver);

}
	//Locators
	
	//newBikes
	@FindBy(linkText="New Bikes")
	WebElement newbikes;
	
	//upcomingBikes
	@FindBy(xpath="//span[text()=\'Upcoming Bikes\']")
	WebElement upcomingbikes;
	
	//Dropdown
	@FindBy(id="makeId")
	WebElement dropdown;
	
	//Readmore
	@FindBy(xpath="//span[text()=\"...Read More\"]")
	WebElement readmore;
	
	@FindBy(xpath="//tr[@class=\"ml-15 \"]//td[1]")
	List<WebElement> models ;
	
	@FindBy(xpath="//tr[@class=\"ml-15 \"]//td[2]")
	List<WebElement> prices ;
	
	@FindBy(xpath="//tr[@class=\"ml-15 \"]//td[3]")
	List<WebElement> launch_dates ;
	
	
	//Action classes
	
	
	//To move to new bikes
	public void new_bike()
	{
		Actions act = new Actions(driver);
		act.moveToElement(newbikes).perform();
	}
	
	//to select upcoming bikes
	
	public void upcoming_bikes()
	{
		upcomingbikes.click();
	}
	
	//to click on the drop down
	
	public void dropdown()
	{
		dropdown.click();
	}
	
	//to select the honda option
	
	public void honda()
	{
		Select sc = new Select(dropdown);
		sc.selectByValue("53");
	}
	
	//to click on read more
	
	public void read_more()
	{
		readmore.click();
	}
	
	public List<String> getModel()
	{
		for(WebElement model :models )
		{
			model_names.add(model.getText());
		}
		return model_names ;
	}
	
	public List<String> getPrice()
	{
		for(WebElement price : prices )
		{
			bike_prices.add(price.getText());
		}
		return bike_prices;
	}
	
	public List<String> getDate()
	{
		for(WebElement date : launch_dates)
		{
			launchdates.add(date.getText());
		}
		return launchdates;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}