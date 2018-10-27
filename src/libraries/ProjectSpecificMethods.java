package libraries;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.HomePageObjects;
import pageObjects.SignInPageObjects;
import testData.TestDataReader;

/**
 * This class file contains methods related to this application
 * @author z002gh8
 *
 */
public class ProjectSpecificMethods {
	
	WebDriver driver;
	WebDriverWait wait;
	HomePageObjects homePage;
	SignInPageObjects signInPage;
	
	public ProjectSpecificMethods(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		homePage = new HomePageObjects(driver, wait);
		signInPage = new SignInPageObjects(driver, wait);
	}
	
	/**
	 * This method performs the login functionality of the application
	 * @param emailId, password
	 */
	public void login(String emailId, String password) {
		
		homePage.clickSignInLink();
		String loginPageTitle = driver.getTitle();
		assertEquals(loginPageTitle, TestDataReader.loginPage, TestDataReader.loginPageMsg);
	
		String loginText = signInPage.getAuthenticationText();
		assertEquals(loginText, TestDataReader.signInPageText , TestDataReader.signInPageTextMsg);  //Compare the text with expected
		
		boolean isLoginModuleDisplayed = signInPage.isLoginModuleDisplayed();
		assertTrue(isLoginModuleDisplayed, TestDataReader.loginModuleDisplayMsg);
		
		signInPage.enterEmailId(emailId);
		signInPage.enterPassword(password);
		signInPage.clickSignInButton();
	}

}
