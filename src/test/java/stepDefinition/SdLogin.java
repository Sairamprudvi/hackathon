package stepDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageFactory.BaseClass;
import pageobject.Cars;
import pageobject.Login;

public class SdLogin
{
	Hooks h=new Hooks();
	public WebDriver driver=h.getDriver();
	Login login = new Login(driver);
	Cars car = new Cars(driver);
	@Given("navigate to homepage of zigwheels")
	public void navigate_to_homepage_of_zigwheels() {
	    // Write code here that turns the phrase above into concrete actions
	    car.home();
	}

	@When("user clicks on login button")
	public void user_clicks_on_login_button() {
	    // Write code here that turns the phrase above into concrete actions
	   login.loginPage();
	}

	@Then("click on google")
	public void click_on_google() {
	    // Write code here that turns the phrase above into concrete actions
	   login.signInGoogle();
	}

	@Then("provide invalid email-id")
	public void provide_invalid_email_id() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
	   Set<String> windowIds = driver.getWindowHandles();
	   List<String> windowid = new ArrayList<String>(windowIds);
	   driver.switchTo().window(windowid.get(1));
		login.setEmail("xyz@gmail.com");
		Thread.sleep(2000);
	}

	@Then("click on next")
	public void click_on_next() {
	    // Write code here that turns the phrase above into concrete actions
	   login.clickOnNext();
	}

	@Then("capture the error message")
	public void capture_the_error_message() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		Thread.sleep(3000);
	   System.out.println(login.captureErrorMessage());
	}
}
