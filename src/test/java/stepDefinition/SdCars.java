package stepDefinition;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageFactory.BaseClass;
import pageobject.Cars;

public class SdCars 
{
	Hooks h=new Hooks();
	public WebDriver driver=h.getDriver();
	Cars cars = new Cars(driver);
	@Given("user will navigate to ZigWheels Website")
	public void user_will_navigate_to_zig_wheels_website() {
	    // Write code here that turns the phrase above into concrete actions
	   System.out.println("User naviagted");
	}

	@When("user hover on used cars")
	public void user_hover_on_used_cars() {
	    // Write code here that turns the phrase above into concrete actions
	   cars.used_cars();
	}

	@Then("click on chennai")
	public void click_on_chennai() {
	    // Write code here that turns the phrase above into concrete actions
	   cars.chennai();
	}

	@Then("get the information about popular models and print the same in excel")
	public void get_the_information_about_popular_models_and_print_the_same_in_excel() {
	    // Write code here that turns the phrase above into concrete actions
	  System.out.println(cars.getpopularModels());
	  
	}
}
