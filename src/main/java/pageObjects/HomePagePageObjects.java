package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePagePageObjects {

	public HomePagePageObjects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

//	WebElement
	@FindBy(xpath = "//div[@class='orangehrm-dashboard-widget-header']//p")
	private List<WebElement> dashboardHeaders;

	public String[] getDashboardHeadersName() {
		String[] dashboardHeaderNames = new String[dashboardHeaders.size()];

		for (int i=0;i<dashboardHeaders.size();i++) {

			dashboardHeaderNames[i] = dashboardHeaders.get(i).getText();
		}
		return dashboardHeaderNames;
	}
}
