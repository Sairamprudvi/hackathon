package testclasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.testng.annotations.Test;

import pageobject.Login;
import testbase.BaseClass;

public class Testcase3 extends BaseClass {
     Login loginpage;
	
	@Test(priority=1, groups = "sanity")
	public void login() throws InterruptedException, IOException
	{
		
		loginpage = new Login(driver);
		loginpage.loginPage();
		logger.info("Login/Signup is selected");
	}
	@Test(priority=2,groups = {"regression"})
	public void signup() {
		
		loginpage.signInGoogle();
		logger.info("Sign u with google is selected ");
	}
	@Test(priority=3,groups = {"regression"})
	public void loginPageDetails() throws IOException, InterruptedException {
		Set<String> windowids = driver.getWindowHandles();
		List<String> windowid =new ArrayList<String>(windowids);
		driver.switchTo().window(windowid.get(1));
		loginpage.setEmail(getProperties().getProperty("email"));
		//Thread.sleep(5000);
		loginpage.clickOnNext();
		Thread.sleep(3000);
		logger.info("Error message is appered and is being captured ");
		System.out.println(loginpage.captureErrorMessage());
		
	}

}
