package libraries;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

/**
 * This contains the Generic methods required to perform basic functions
 * @author z002gh8
 *
 */
public class GenericMethods {
	
	WebDriver driver;
	WebDriverWait wait;
	
	public GenericMethods(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}
	
	public void clickLinkByXpath(String xpath, String message) {
		try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		driver.findElement(By.xpath(xpath)).click();
		Reporter.log("PASS -- The element "+message+" is clicked successfully", true);
		}catch(Exception exp) {
			Reporter.log("FAIL -- The element "+message+" is not clicked successfully", true);
		}
	}
	
	
	public String getTextByXpath(String xpath, String message) {
		try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		String text = driver.findElement(By.xpath(xpath)).getText();
		Reporter.log("PASS -- The element "+message+" is displayed successfully", true);
		return text;
		}catch(Exception exp) {
			Reporter.log("FAIL -- The element "+message+" is not displayed successfully", true);
			return null;
		}
	}
	
	public boolean isModuleDisplayed(String xpath, String message) {
		try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		boolean value = driver.findElement(By.xpath(xpath)).isDisplayed();
		Reporter.log("PASS -- The element "+message+" is displayed successfully", true);
		return value;
		}catch(Exception exp) {
			Reporter.log("FAIL -- The element "+message+" is not displayed successfully", true);
			return false;
		}
	}
	
	
	public void enterTextByXpath(String xpath, String value) {
		try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		driver.findElement(By.xpath(xpath)).sendKeys(value);
		Reporter.log("PASS -- The value "+value+" is entered successfully", true);
		}catch(Exception exp) {
			Reporter.log("FAIL -- The value "+value+" is not entered successfully", true);
		}
	}
	
	
	public void enterTextById(String id, String value) {
		try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
		driver.findElement(By.id(id)).sendKeys(value);
		Reporter.log("PASS -- The value "+value+" is entered successfully", true);
		}catch(Exception exp) {
			Reporter.log("FAIL -- The value "+value+" is not entered successfully", true);
		}
	}
	
	
}
