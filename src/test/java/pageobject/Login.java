package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login extends BasePage{
	public Login(WebDriver driver)
	{
		super(driver);
	}
	
	//Locators
	
	@FindBy(id="des_lIcon")
	WebElement login;
	
	@FindBy(xpath="//span[text()='Google']")
	WebElement google;
	
	@FindBy(xpath="//input[@autocomplete=\"username\"]")
	WebElement email;
	
	@FindBy(xpath="//span[text()=\"Next\"]")
	WebElement next;
	
	@FindBy(xpath="//div[@class=\"o6cuMc Jj6Lae\"]")
	WebElement warning_message;
	
	//action methods
	
	public void loginPage()
	{
		login.click();
	}
	
	public void signInGoogle()
	{
		google.click();
	}
	
	public void setEmail(String emaildata)
	{
		email.sendKeys(emaildata);
	}
	
	public void clickOnNext()
	{
		next.click();
	}
	
	public String captureErrorMessage()
	{
		String error_message = warning_message.getText();
		return error_message;
	}



}
