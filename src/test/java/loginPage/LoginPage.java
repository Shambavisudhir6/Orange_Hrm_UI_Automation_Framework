package loginPage;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.Base;
import pageObjects.LoginPagePageObjects;

public class LoginPage extends Base {

	WebDriver driver;
	Properties prop = readDataProperties();

	@BeforeClass(description = "Intilize driver and launch the url")
	public void browserLaunch() {
		driver = launchBrowser();
		driver.get(prop.getProperty("url"));
	}

	@Test(priority = 1, dependsOnMethods = {"browserLaunch"} )
	public void userLogin() {
		LoginPagePageObjects loginPageObjects = new LoginPagePageObjects(driver);
		loginPageObjects.login(loginPageObjects.getUserLoginCredentials()[0], loginPageObjects.getUserLoginCredentials()[1]);
		Assert.assertEquals(loginPageObjects.loggedUserName(), "John Doe");
	}
	
	@Test (priority = 0, dependsOnMethods = {"browserLaunch"})
	public void loginWithIncorrectPassword()
	{
		int[] arr = {1,2,3,4};
		LoginPagePageObjects loginPageObjects = new LoginPagePageObjects(driver);
		loginPageObjects.login("Admin", "admin177");
		Assert.assertEquals(loginPageObjects.getLoginErrorMessage(), "Invalid credentials");
	}

	@AfterClass
	public void quit() {
		quitBrowser(driver);
	}
}
