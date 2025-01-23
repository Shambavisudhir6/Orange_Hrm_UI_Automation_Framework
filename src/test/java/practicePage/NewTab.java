package practicePage;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import base.Base;

public class NewTab extends Base {

	WebDriver driver = launchBrowser();

	@Test(enabled = false)
	public void newTab() {
		driver.get("https://testautomationpractice.blogspot.com/");
		driver.findElement(By.xpath("//button[@onclick=\"myFunction()\"]")).click();
		String originalTab = driver.getWindowHandle();
		Set<String> allTabs = driver.getWindowHandles();
		for (String tab : allTabs) {
			if (!tab.equals(originalTab)) {
				driver.switchTo().window(tab);
			}
		}
		driver.switchTo().window(originalTab);
	}

	@Test(enabled = false)
	public void dragAndDrop() {
		driver.get("https://testautomationpractice.blogspot.com/");
		Actions actions = new Actions(driver);
		WebElement source = driver.findElement(By.id("draggable"));
		WebElement destination = driver.findElement(By.id("droppable"));
		actions.dragAndDrop(source, destination).build().perform();
	}

	@Test
	public void slide() {
		driver.get("https://testautomationpractice.blogspot.com/");
		WebElement slideLeft = driver.findElement(
				By.xpath("(//span[contains(@class,'ui-slider-handle ui-corner-all ui-state-default')])[1]"));
		WebElement slideRight = driver.findElement(
				By.xpath("(//span[contains(@class,'ui-slider-handle ui-corner-all ui-state-default')])[2]"));
		int[] shambaviArray = rangeReturn(driver);
		int startRange = shambaviArray[0];
		int endRange = shambaviArray[1];
		int targetStartRange = 200;
		int targetEndRange = 250;
		while(startRange<targetStartRange)
		{
		   slideLeft.sendKeys(Keys.ARROW_RIGHT);
		   int[] shanthiniArray = rangeReturn(driver);
		   startRange = shanthiniArray[0];
		}
		while(endRange>targetEndRange)
		{
		   slideRight.sendKeys(Keys.ARROW_LEFT);
		   int[] shanthiniArray = rangeReturn(driver);
		   endRange = shanthiniArray[1];
		}
//		driver.close();
	}

	public static int[] rangeReturn(WebDriver driver) {

		WebElement selectedAmount = driver.findElement(By.xpath("//input[@id='amount']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String selectedAmountText = (String) js.executeScript("return arguments[0].value;", selectedAmount);
		String replacedText = selectedAmountText.replaceAll("\\$", "");

		String[] firstElement = replacedText.split("-");
		int startingRange = Integer.parseInt(firstElement[0].trim());
		int endingRange = Integer.parseInt(firstElement[1].trim());
		System.out.println(startingRange + " " + endingRange);
		return new int[] { startingRange, endingRange };
	}
}
