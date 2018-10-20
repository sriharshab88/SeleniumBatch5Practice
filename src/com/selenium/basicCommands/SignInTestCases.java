package com.selenium.basicCommands;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.*;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SignInTestCases {
	
	WebDriver driver;
	
	@BeforeTest
	public void startBrowser() {
		launchBrowser();
		
	}
	
	public void launchBrowser() {
		if (System.getProperty("os.name").equals("windows")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + File.separator + "geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + File.separator + "geckodriver-0.23");

		}
		
		driver = new FirefoxDriver();  //This command will launch the Firefox browser
	}
	
	@Test
	public void validateSignInFunctionality() throws InterruptedException {
		
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
		String loginText = authenticationText.getText(); //Get the text from the web elelemnt
		assertEquals(loginText, "AUTHENTICATION" , "FAIL -- Authentication text did not match");  //Compare the text with expected
		
		WebElement loginModule = driver.findElement(By.xpath("//form[@id='login_form']")); //Fetching login module web element
		boolean isLoginModuleDisplayed = loginModule.isDisplayed();  //Checking whether the login module displayed or not
		assertTrue(isLoginModuleDisplayed, "FAIL -- Login module did not display in the login page");
		
		WebElement emailTextBox = driver.findElement(By.xpath("//input[@id='email']")); //Fetching email text box
		emailTextBox.sendKeys("testbatch5@test.com"); //Entering email value into the email text box
		
		driver.findElement(By.id("passwd")).sendKeys("123456");  //Identifying the password text box through id and entering password into it
		
		driver.findElement(By.xpath("//button[@id='SubmitLogin']")).click();  //Identifying the Submit button and clicking on it
		
		String myaccountTitle = driver.getTitle();
		assertEquals(myaccountTitle, "My account - My Store", "FAIL -- My account title did not match");
		
		String username = driver.findElement(By.xpath("//a[@class='account']/span")).getText();
		System.out.println(username);
		assertEquals(username, "Test Harsha", "FAIL -- Username did not match");
	}
	
	@DataProvider
	public Object [][] signInTestCaseData(){	
		return new Object[][] {
			{"Invalid email 1", "emailInvalid", "123456", "Invalid email address."},
			{"Invalid email 2","email@@@.ttt.com", "123456", "Invalid email address."},
			{"Blank email","", "123456", "An email address required."},
			{"Blank password","test@test.com", "", "Password is required."},
		    {"Invalid password", "testbatch5@test.com", "1", "Invalid password."},
		    {"Authentication error","testbatch5@test.com", "qqqwqsdhbsdhasd", "Authentication failed."},
		    {"All Blanks", "", "", "An email address required."		
		}};
	}
	
	@Test(dataProviderClass = SignInTestCases.class, dataProvider = "signInTestCaseData")
	public void validateNegativeSignInScenarios(String testCaseName, String email, 
			String password, String errorMessage) throws InterruptedException {
		
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
		String loginText = authenticationText.getText(); //Get the text from the web elelemnt
		assertEquals(loginText, "AUTHENTICATION" , "FAIL -- Authentication text did not match");  //Compare the text with expected
		
		WebElement loginModule = driver.findElement(By.xpath("//form[@id='login_form']")); //Fetching login module web element
		boolean isLoginModuleDisplayed = loginModule.isDisplayed();  //Checking whether the login module displayed or not
		assertTrue(isLoginModuleDisplayed, "FAIL -- Login module did not display in the login page");
		
		WebElement emailTextBox = driver.findElement(By.xpath("//input[@id='email']")); //Fetching email text box
		emailTextBox.sendKeys(email); //Entering email value into the email text box
		
		driver.findElement(By.id("passwd")).sendKeys(password);  //Identifying the password text box through id and entering password into it
		
		driver.findElement(By.xpath("//button[@id='SubmitLogin']")).click();  //Identifying the Submit button and clicking on it
		
		String actualErrorMessage = driver.findElement(By.xpath("//div[@class='alert alert-danger']/ol/li")).getText();
		assertEquals(actualErrorMessage, errorMessage, "FAIL -- Error message did not "
				+ "display properly for the testcase "+testCaseName+"");
		
	}
	
	@AfterTest
	public void endBrowser() {
		//driver.close();   //This will close the current active browser window
		driver.quit();    //This will quit the entire browser process from the backend
	}
	
	

}
