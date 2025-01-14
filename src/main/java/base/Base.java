package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

public class Base {

	public Properties readDataProperties() {
		Properties prop = new Properties();

		try {
			String file = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
					+ File.separator + "resources" + File.separator + "EnvironmentVariables" + File.separator
					+ "data.Properties";
			System.out.println(file);
			FileInputStream fis = new FileInputStream(file);
			prop.load(fis);
		} catch (FileNotFoundException e) {
			System.out.println("File not found on the given location " + e);
		} catch (IOException e) {
			System.out.println("IO exception occured " + e);
		}
		return prop;
	}

	/**
	 * Launches a web browser based on the configuration specified in a properties file.
	 * 
	 * <p>This method reads the browser type from a properties file and initializes 
	 * the corresponding WebDriver instance. Supported browsers are Chrome and Edge. 
	 * If the browser type is not specified or unsupported, appropriate exceptions 
	 * are thrown and logged.</p>
	 * 
	 * @return WebDriver An instance of the WebDriver for the specified browser. 
	 *         Returns null if the browser type is not specified or is unsupported.
	 * 
	 * @throws Exception if the browser type is not specified in the properties file 
	 *                   or if an unsupported browser type is encountered.
	 * 
	 * Example usage:
	 * <pre>
	 * {@code
	 * WebDriver driver = launchBrowser();
	 * driver.get("https://example.com");
	 * }
	 * </pre>
	 */
	public WebDriver launchBrowser() {

		Properties properties = readDataProperties();
		String browser = properties.getProperty("browser");
		WebDriver driver = null;
		try {
			if (browser.isEmpty() || browser == null) {
				throw new Exception("Browser type is not specified in the properties file.");
			}
		} catch (Exception e) {
			System.out.println("Browser type is not specified in the properties file. " + e.getMessage());
		}
		try {
			if (browser.equalsIgnoreCase("chrome")) {
				 driver = new ChromeDriver();
			} else if (browser.equalsIgnoreCase("edge")) {
				 driver = new EdgeDriver();
			} else {
				throw new Exception("Unsupported browser type");
			}
		} catch (Exception e) {
			System.out.println("Invalid supported browser type " + e.getMessage());
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		return driver;
	}
	
	public void quitBrowser(WebDriver driver)
	{
		if(driver!=null)
		{
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			driver.quit();
		}
		else
		{
			System.out.println("No driver opened");
		}
	}

}
