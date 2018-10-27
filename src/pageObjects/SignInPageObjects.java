package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import libraries.GenericMethods;

public class SignInPageObjects {
	
	WebDriver driver;
	WebDriverWait wait;
	GenericMethods genericMethods;
	
	public SignInPageObjects(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		genericMethods = new GenericMethods(driver, wait);
	}

	public String getAuthenticationText() {
		return genericMethods.getTextByXpath("//div[@id='center_column']/h1", "Authentication Message");
	}
	
	
	public boolean isLoginModuleDisplayed() {
		return genericMethods.isModuleDisplayed("//form[@id='login_form']", "Login Module");
	}
	
	public void enterEmailId(String emailId) {
		genericMethods.enterTextByXpath("//input[@id='email']", emailId);
	}
	
	public void enterPassword(String password) {
		genericMethods.enterTextById("passwd", password);
	}
	
	public void clickSignInButton() {
		genericMethods.clickLinkByXpath("//button[@id='SubmitLogin']", "Sign In Button");
	}
	
	
	
}
