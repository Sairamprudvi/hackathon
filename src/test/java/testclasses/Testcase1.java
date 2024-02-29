package testclasses;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import pageobject.Bikes;
import testbase.BaseClass;
import utilities.ExcelUtilis;

public class Testcase1 extends BaseClass
{
	public static List<Integer> index_values = new ArrayList<Integer>();
	public Bikes bikes;
	List<Integer> index = new ArrayList<Integer>();
	@Test(priority=1, groups = {"sanity"})
	public void newbikes() throws IOException
	{
	  bikes = new Bikes(driver);	
	  bikes.new_bike();}
	@Test(priority=2, groups = {"sanity"})
	public void upcomingbikes() {
	  bikes.upcoming_bikes();
	  logger.info("Successfully clicked on upcoming bikes");
	}
	@Test(priority=3,groups = {"regression"})
	public void brand() {
	  bikes.dropdown();
	  bikes.honda();
	  logger.info("Honda is selected from the dropdown ");
	}
	@Test(priority=4,groups = {"regression"})
	public void bikes() throws IOException
	{
	  bikes.read_more();
	  logger.info("Readmore is clicked successfully");
	  int sizeofalist = bikes.getPrice().size();
		for(int i=0;i<sizeofalist;i++)
		{
			//System.out.println(bikepage.getPriceValues().size());
			String numerical_value = bikes.getPrice().get(i).replaceAll("[^\\d]", "");
			int value = Integer.parseInt(numerical_value) * 1000;
			if((value<400000 &&  !( bikes.getPrice().get(i).contains("crore")))||! bikes.getPrice().get(i).contains(" Lakh"))
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
				ExcelUtilis.excelOutput(bikes.getModel(),bikes.getPrice(),bikes.getModel());
				//System.out.println(bikepage.getmodelNames().get(i)+" -> "+bikepage.getPriceValues().get(i)+" -> "+bikepage.getLaunchDates().get(i));
			}
		}
	
	}
		

}
