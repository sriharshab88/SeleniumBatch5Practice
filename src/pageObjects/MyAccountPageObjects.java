package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import libraries.GenericMethods;

public class MyAccountPageObjects {
	
	WebDriver driver;
	WebDriverWait wait;
	GenericMethods genericMethods;
	
	public MyAccountPageObjects(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		genericMethods = new GenericMethods(driver, wait);
	}
	
	public String getUserName() {
		return genericMethods.getTextByXpath("//a[@class='account']/span", "User Name");
	}
	
	
}
