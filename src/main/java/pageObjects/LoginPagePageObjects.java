package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPagePageObjects {
	
	public LoginPagePageObjects(WebDriver driver) {
		PageFactory.initElements(driver	, this);
		}

	@FindBy (xpath = "//div[@class='orangehrm-login-error']//p//preceding-sibling::p")
	private WebElement usernameText;
	
	@FindBy (xpath = "//div[@class='orangehrm-login-error']//p//following-sibling::p")
	private WebElement passwordText;
	
	@FindBy(css = "input[name='username']")
	private WebElement customerLogin;
	
	@FindBy(name = "password")
	private WebElement passWord;
	
	@FindBy(xpath = "//button[contains(@class,'orangehrm-login-button')]")
	private WebElement login;
	
	@FindBy(css="div.oxd-alert-content.oxd-alert-content--error")
	private WebElement loginErrorMessage;
	
	@FindBy(className="oxd-userdropdown-name")
	private WebElement loggedUserName;
	
	public void login(String userName, String password)
	{
		customerLogin.sendKeys(userName);
		passWord.sendKeys(password);
		login.click();
	}
	
	public String getLoginErrorMessage()
	{
		return loginErrorMessage.getText();
	}
	
	public String loggedUserName()
	{
		return loggedUserName.getText();
	}
	
	public String[] getUserLoginCredentials()
	{
		String usernameOne = usernameText.getText();
		String[] user = usernameOne.split(" ");
		String admin = user[2];
		
		String passwordOne = passwordText.getText();
		String[] pass = passwordOne.split(" ");
		String passNew = pass[2];
		String[] loginCredentials = new String[]{admin, passNew};
		return loginCredentials;
	}
	
	public void loginWithExtractedValues() {
	    // Get the array of credentials
	    String[] credentials = getUserLoginCredentials();

	    // Pass the credentials to the login() method
	    login(credentials[0], credentials[1]);
	}
}
