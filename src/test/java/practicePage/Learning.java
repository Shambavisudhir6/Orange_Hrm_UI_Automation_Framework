package practicePage;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.Base;

public class Learning extends Base {

	WebDriver driver;
	Properties prop = readDataProperties();

	@BeforeClass
	public void launcBrowser() {
		driver = launchBrowser();
		driver.get(prop.getProperty("practice"));
	}

	@Test(enabled = false)
	public void verifyCurrentUrl() {
		String url = prop.getProperty("practice");
		Assert.assertEquals(url, driver.getCurrentUrl());
		System.out.println(driver.getCurrentUrl());
	}

	@Test(enabled = false)
	public void inputFiledVerify() {
		driver.findElement(By.name("email")).sendKeys("Shambavissudhir@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("895678907");
		driver.findElement(By.name("company")).sendKeys("Disney");
		driver.findElement(By.name("mobile number")).sendKeys("8976994478");
	}

	@Test(enabled=false)
	public void colorVerificationOfSubmit() {
		String rgbaValue = driver.findElement(By.cssSelector("button[value='Submit']")).getCssValue("background-color");
		String borderSubmit = driver.findElement(By.cssSelector("button[value='Submit']")).getCssValue("border");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(rgbaValue, "rgba(0, 0, 0, 0)");
		softAssert.assertAll();
	}

	@Test(enabled=false)
	public void verifyTextboxDisabled()
	{
		WebElement firstNameInputField = driver.findElement(By.cssSelector("input[placeholder='First Enter name']"));
		boolean disabledAttribute = firstNameInputField.isEnabled();

		if (disabledAttribute !=true) {
		    System.out.println("The button is disabled.");
		    JavascriptExecutor js = (JavascriptExecutor)driver;
		    WebElement boxm = driver.findElement(By.xpath("(//*[name()='path'][starts-with(@d, 'M20')])"));
		    js.executeScript("arguments[0].dispatchEvent(new MouseEvent('click', {bubbles: true, cancelable: true}));", boxm );
		}
		firstNameInputField.sendKeys("Katte SHREYAS");
	}
	
	@Test(enabled=false)
	public void verifyLastNameDisabled()
	{
		WebElement lastNameInputField = driver.findElement(By.cssSelector("input[placeholder='Enter Last name']"));
		boolean disabledAttribute = lastNameInputField.isEnabled();
		
		Assert.assertEquals(disabledAttribute, false);
	}
	
	@Test(enabled=false)
	public void selectFromCarDropdown()
	{
		WebElement carDropdownButton = driver.findElement(By.id("cars"));
		Select select = new Select(carDropdownButton);
		select.selectByValue("saab");
		String selectedCar = select.getFirstSelectedOption().getText();
		Assert.assertEquals(selectedCar, "Saab");
		System.out.println(selectedCar);
	}
	
	@Test
	public void selectDateFromCalendar()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement dateInput = driver.findElement(By.cssSelector("input[name='the_date']"));

		// Set the value to "2025-01-11" and trigger change/input events
		js.executeScript(
		    "arguments[0].value = '2025-01-15';" +
		    "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));" +
		    "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));",
		    dateInput
		);
	}

	@AfterClass
	public void quit() {
		quitBrowser(driver);
	}

}
