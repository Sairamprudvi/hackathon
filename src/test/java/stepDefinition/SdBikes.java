package stepDefinition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageFactory.BaseClass;
import pageobject.Bikes;
import utilities.ExcelUtilis;

public class SdBikes 
{
	Hooks h=new Hooks();
	public WebDriver driver=h.getDriver();
	public static List<Integer> index_values = new ArrayList<Integer>();
	Bikes bike = new Bikes(driver);
	@Given("user navigated to ZigWheels Website")
	public void user_navigated_to_zig_wheels_website() {
	    // Write code here that turns the phrase above into concrete actions
	   System.out.println("User Navigated successfully to zigwheels website");
	}

	@When("user hover on NewBikes")
	public void user_hover_on_new_bikes() {
	    // Write code here that turns the phrase above into concrete actions
	    bike.new_bike();
	}

	@Then("click on upcoming bikes")
	public void click_on_upcoming_bikes() {
	    // Write code here that turns the phrase above into concrete actions
	   bike.upcoming_bikes();
	}

	@Then("click on manufacturer")
	public void click_on_manufacturer() {
	    // Write code here that turns the phrase above into concrete actions
		bike.dropdown();
	}

	@Then("select honda")
	public void select_honda() {
	    // Write code here that turns the phrase above into concrete actions
	   bike.honda();
	}

	@Then("click on readmore")
	public void click_on_readmore() {
	    // Write code here that turns the phrase above into concrete actions
	    bike.read_more();
	}

	@Then("get the information about upcoming bikes and print the same in excel")
	public void get_the_information_about_upcoming_bikes_and_print_the_same_in_excel() throws IOException
	{
	    // Write code here that turns the phrase above into concrete actions
		 int sizeofalist = bike.getPrice().size();
			for(int i=0;i<sizeofalist;i++)
			{
				//System.out.println(bikepage.getPriceValues().size());
				String numerical_value = bike.getPrice().get(i).replaceAll("[^\\d]", "");
				int value = Integer.parseInt(numerical_value) * 1000;
				if((value<400000 &&  !( bike.getPrice().get(i).contains("crore")))||! bike.getPrice().get(i).contains(" Lakh"))
				{
					index_values.add(i);
				}
			}
			//ExcelUtils.excelOutput(bikepage.getmodelNames(),bikepage.getPriceValues(),bikepage.getLaunchDates());
			//System.out.println("Upcoming bikes less than 4 lakhs are listed below : ");
			
			for(int i=0;i<sizeofalist;i++)
			{
				if(index_values.contains(i))
				{
					//ExcelUtilis.excelOutput(bike.getModel(),bike.getPrice(),bike.getDate());
					System.out.println(bike.getModel().get(i)+" -> "+bike.getPrice().get(i)+" -> "+bike.getDate().get(i));
				}
			}
	}

}
