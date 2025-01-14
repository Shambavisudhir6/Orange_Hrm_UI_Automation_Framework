package loginPage;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.Base;
import pageObjects.HomePagePageObjects;
import pageObjects.LoginPagePageObjects;

public class HomePageTest extends Base {

	WebDriver driver;
	Properties prop = readDataProperties();

	@BeforeClass
	public void browserLaunch() {
		driver = launchBrowser();
		driver.get(prop.getProperty("url"));
	}

	@Test
	public void validateHomePageHeadersName() {

		LoginPagePageObjects lpo = new LoginPagePageObjects(driver);
		lpo.login(lpo.getUserLoginCredentials()[0], lpo.getUserLoginCredentials()[1]);
		HomePagePageObjects hp = new HomePagePageObjects(driver);
		String[] homePageHeadersList = hp.getDashboardHeadersName();
		Assert.assertEquals(homePageHeadersList, prop.getProperty("dashboardHeadersName").split(";"));
		int[] arr = new int[] {1,2,3};
	}
	
	@AfterClass
	public void quit()
	{
		quitBrowser(driver);
	}

}
