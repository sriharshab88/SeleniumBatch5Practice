package libraries;

import static org.testng.Assert.assertEquals;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import results.ExtentResults;

/**
 * This class file contains methods which are pre-requistes to the tests to run
 * @author z002gh8
 *
 */
public class Utilities {
	
	
	ExtentResults results = new ExtentResults();
	WebDriver driver;
	
	public WebDriver launchBrowser(String browser) {
		
		if(browser.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver(); //To launch the Firefox browser
			System.out.println("PASS -- Launched Firefox browser successfully");
			Reporter.log("PASS -- Application URL: "+driver.getCurrentUrl(), true);  //To get current url
		}else if(browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+File.separator+"chromedriver");
			driver = new ChromeDriver();
			Reporter.log("PASS -- Launched Chrome browser successfully", true);
			Reporter.log("PASS -- Application URL: "+driver.getCurrentUrl(), true);  //To get current url
		}else {
			Reporter.log("FAIL -- Browser cannot be recognised", true);
		}
		driver.get(Configurations.applicationUrl);   //Opens the application URL specified
		String windowTitle = driver.getTitle(); //Fetch the title of the current window
		assertEquals(windowTitle, "My Store", "FAIL -- the window title did not match");
		
		return driver;
	}
		


}
