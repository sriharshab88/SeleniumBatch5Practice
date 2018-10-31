package libraries;

import static org.testng.Assert.assertEquals;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import results.ExtentResults;

/**
 * This class file contains methods which are pre-requistes to the tests to run
 * @author z002gh8
 *
 */
public class Utilities {
	
	WebDriver driver;
	ExtentResults results = new ExtentResults();
	
	public WebDriver launchBrowser() {
		if (System.getProperty("os.name").equals("windows")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + File.separator + "geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + File.separator + "geckodriver-0.23");

		}
		driver = new FirefoxDriver();  //This command will launch the Firefox browser
		driver.get(Configurations.applicationUrl); //This will launch the application
		String windowTitle = driver.getTitle(); //Fetch the title of the current window
		assertEquals(windowTitle, "My Store", "FAIL -- the window title did not match");
		
		return driver;
	}

}
