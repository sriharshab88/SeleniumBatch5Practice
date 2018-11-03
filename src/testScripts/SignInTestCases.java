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
import libraries.ProjectSpecificMethods;
import libraries.Utilities;
import pageObjects.HomePageObjects;
import pageObjects.MyAccountPageObjects;
import pageObjects.SignInPageObjects;
import results.ExtentResults;
import testData.CommonTestData;
import testData.TestDataReader;

public class SignInTestCases {
	
	WebDriver driver;
	WebDriverWait wait;
	Utilities utilities = new Utilities();
	SignInPageObjects signInPage; 
	HomePageObjects homePage;
	MyAccountPageObjects myAccountPage;
	ProjectSpecificMethods projectSpecificMethods;
	ExtentResults results = new ExtentResults();
	
	@BeforeTest(groups= {"Sanity", "Regression"})
	public void startBrowser() {
		driver = utilities.launchBrowser();
		wait = new WebDriverWait(driver, 20);
		signInPage = new SignInPageObjects(driver, wait);
		homePage = new HomePageObjects(driver, wait);
		myAccountPage = new MyAccountPageObjects(driver, wait);
		projectSpecificMethods = new ProjectSpecificMethods(driver, wait);
		
	}
	
	@Test(groups= {"Sanity", "Regression"})
	public void validateSignInFunctionality() throws Exception {
		
		results.createTestcase(Thread.currentThread()
				.getStackTrace()[1].getMethodName(), this.getClass().getSimpleName());
		
		projectSpecificMethods.login(TestDataReader.email, TestDataReader.password);
		
		String myaccountTitle = driver.getTitle();
		results.assertEquals(myaccountTitle, TestDataReader.myAccountPage, TestDataReader.myAccountPageMsg);
		
		String username = myAccountPage.getUserName();
		results.assertEquals(username, TestDataReader.userName, TestDataReader.userNameMsg);
	}
	
	
	
	@Test(groups= {"Sanity"},dataProviderClass = CommonTestData.class, dataProvider = "signInTestCaseData")
	public void validateNegativeSignInScenarios(String testCaseName, String email, 
			String password, String errorMessage) throws Exception {
		
		results.createTestcase(Thread.currentThread()
				.getStackTrace()[1].getMethodName() +"--"+testCaseName, this.getClass().getSimpleName());
		
		projectSpecificMethods.login(email, password);
		String actualErrorMessage = signInPage.getErrorMessage(testCaseName);
		results.assertEquals(actualErrorMessage, errorMessage, TestDataReader
				.errorMessageDisplayStatus + testCaseName);
		
	}
	
	@AfterTest(groups= {"Sanity", "Regression"})
	public void endBrowser() {
		//driver.close();   //This will close the current active browser window
		driver.quit();    //This will quit the entire browser process from the backend
	}
	
	

}
