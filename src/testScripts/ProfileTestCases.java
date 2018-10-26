package testScripts;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class ProfileTestCases {

	WebDriver driver;
	
	@Test
	public void validatingEditProfileFunctionality() throws InterruptedException {
	
		if (System.getProperty("os.name").equals("windows")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + File.separator + "geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + File.separator + "geckodriver-0.23");

		}
		
		driver = new FirefoxDriver();  //This command will launch the Firefox browser
		driver.get("http://automationpractice.com/index.php"); //This will launch the application
		String windowTitle = driver.getTitle(); //Fetch the title of the current window
		assertEquals(windowTitle, "My Store", "FAIL -- the window title did not match");
//		if(windowTitle.equalsIgnoreCase("My Store")) {
//			System.out.println("PASS -- Title matched");
//		}else {
//			System.out.println("FAIL -- The title did not match");
//		}
		
		WebElement signinLink = driver.findElement(By.xpath("//a[@class='login']"));  //Fetch the web element signinLink
		signinLink.click();  //Clicks on the signinLink web element
		Thread.sleep(3000);
		String loginPageTitle = driver.getTitle();
		assertEquals(loginPageTitle, "Login - My Store", "FAIL -- Login page title did not match");
		
		WebElement authenticationText = driver.findElement(By.xpath("//div[@id='center_column']/h1")); //Fetch the authentication web element
		String loginText = authenticationText.getText(); //Get the text from the web element
		assertEquals(loginText, "AUTHENTICATION" , "FAIL -- Authentication text did not match");  //Compare the text with expected
		
		WebElement loginModule = driver.findElement(By.xpath("//form[@id='login_form']")); //Fetching login module web element
		boolean isLoginModuleDisplayed = loginModule.isDisplayed();  //Checking whether the login module displayed or not
		assertTrue(isLoginModuleDisplayed, "FAIL -- Login module did not display in the login page");
		
		WebElement emailTextBox = driver.findElement(By.xpath("//input[@id='email']")); //Fetching email text box
		emailTextBox.sendKeys("testbatch5@test.com"); //Entering email value into the email text box
		
		driver.findElement(By.id("passwd")).sendKeys("123456");  //Identifying the password text box through id and entering password into it
		
		driver.findElement(By.xpath("//button[@id='SubmitLogin']")).click();  //Identifying the Submit button and clicking on it
		
		driver.findElement(By.xpath("//a[@title='Information']/span")).click();  //Click the My personal information button
		
		String infoText = driver.findElement(By.xpath("//div[@id='center_column']//h1")).getText(); //fetch the info text
		assertEquals(infoText, "YOUR PERSONAL INFORMATION" ,"FAIL -- Info text did not match");
		
		WebElement dateDropDown = driver.findElement(By.xpath("//select[@id='days']")); //Fetch the date drop down web element
		Select dateDD = new Select(dateDropDown);  //Put the web element into the select method
		dateDD.selectByIndex(6);  //select the option using index (6)
		Thread.sleep(4000);
		
		WebElement monthDropDown = driver.findElement(By.xpath("//select[@id='months']")); //Fetch the month drop down web element
		Select monthDD = new Select(monthDropDown);  //Put the web element into the select method
		monthDD.selectByValue("8");  //select the option using value (August)
		Thread.sleep(4000);
		
		WebElement yearDropDown = driver.findElement(By.xpath("//select[@id='years']")); //Fetch the date drop down web element
		Select yearDD = new Select(yearDropDown);  //Put the web element into the select method
		yearDD.selectByVisibleText("1985  ");  //select the option using visible text (1985)
		Thread.sleep(4000);
		
		driver.findElement(By.xpath("//input[@id='old_passwd']")).sendKeys("123456");  //enter old password
		driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys("12345678");  //enter new password
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("12345678");  //enter confirmation password
		 
		driver.findElement(By.xpath("//input[@id='newsletter']")).click();  //click on newsletter check box
		driver.findElement(By.xpath("//input[@id='optin']")).click();  //click on special offers check box
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@name='submitIdentity']")).click();  //click on the save button
		
	}
	
	
	@AfterTest
	public void endBrowser() {
		driver.quit();
	}
	
	
}
