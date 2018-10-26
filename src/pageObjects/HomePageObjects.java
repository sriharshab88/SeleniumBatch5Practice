package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import libraries.GenericMethods;

public class HomePageObjects {
	
	WebDriver driver;
	WebDriverWait wait;
	GenericMethods genericMethods;
	
	public HomePageObjects(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		genericMethods = new GenericMethods(driver, wait);
	}

	public void clickSignInLink() {
		genericMethods.clickLinkByXpath("//a[@class='login']", "Sign Link");
	}
	
	
}
