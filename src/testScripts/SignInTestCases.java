package testScripts;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import libraries.Configurations;
import libraries.Utilities;
import pageObjects.HomePageObjects;
import pageObjects.MyAccountPageObjects;
import pageObjects.SignInPageObjects;

public class SignInTestCases {
	
	WebDriver driver;
	WebDriverWait wait;
	Utilities utilities = new Utilities();
	SignInPageObjects signInPage; 
	HomePageObjects homePage;
	MyAccountPageObjects myAccountPage;
	
	@BeforeTest
	public void startBrowser() {
		driver = utilities.launchBrowser();
		wait = new WebDriverWait(driver, 20);
		signInPage = new SignInPageObjects(driver, wait);
		homePage = new HomePageObjects(driver, wait);
		myAccountPage = new MyAccountPageObjects(driver, wait);
	}
	
	@Test
	public void validateSignInFunctionality() throws InterruptedException {
		
		homePage.clickSignInLink();
		String loginPageTitle = driver.getTitle();
		assertEquals(loginPageTitle, "Login - My Store", "FAIL -- Login page title did not match");
	
		String loginText = signInPage.getAuthenticationText();
		assertEquals(loginText, "AUTHENTICATION" , "FAIL -- Authentication text did not match");  //Compare the text with expected
		
		boolean isLoginModuleDisplayed = signInPage.isLoginModuleDisplayed();
		assertTrue(isLoginModuleDisplayed, "FAIL -- Login module did not display in the login page");
		
		signInPage.enterEmailId("testbatch5@test.com");
		signInPage.enterPassword("12345678");
		signInPage.clickSignInButton();
		
		String myaccountTitle = driver.getTitle();
		assertEquals(myaccountTitle, "My account - My Store", "FAIL -- My account title did not match");
		
		String username = myAccountPage.getUserName();
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
