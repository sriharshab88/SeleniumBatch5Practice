package libraries;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import results.ExtentResults;

/**
 * This contains the Generic methods required to perform basic functions
 * @author z002gh8
 *
 */
public class GenericMethods {
	
	WebDriver driver;
	WebDriverWait wait;
	ExtentResults results = new ExtentResults();
	
	public GenericMethods(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}
	
	public void clickLinkByXpath(String xpath, String message) throws Exception {
		try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		driver.findElement(By.xpath(xpath)).click();
		results.log("The element "+message+" is clicked successfully", true);
		}catch(Exception exp) {
			results.log("The element "+message+" is not clicked successfully", true);
		}
	}
	
	
	public String getTextByXpath(String xpath, String message) throws Exception {
		try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		String text = driver.findElement(By.xpath(xpath)).getText();
		results.log("The element "+message+" is displayed successfully", true);
		return text;
		}catch(Exception exp) {
			results.log("The element "+message+" is not displayed successfully", true);
			return null;
		}
	}
	
	public boolean isModuleDisplayed(String xpath, String message) throws Exception {
		try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		boolean value = driver.findElement(By.xpath(xpath)).isDisplayed();
		results.log("The element "+message+" is displayed successfully", true);
		return value;
		}catch(Exception exp) {
			results.log("The element "+message+" is not displayed successfully", true);
			return false;
		}
	}
	
	
	public void enterTextByXpath(String xpath, String value) throws Exception {
		try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		driver.findElement(By.xpath(xpath)).sendKeys(value);
		results.log("The value "+value+" is entered successfully", true);
		}catch(Exception exp) {
			results.log("The value "+value+" is not entered successfully", true);
		}
	}
	
	
	public void enterTextById(String id, String value) throws Exception {
		try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
		driver.findElement(By.id(id)).sendKeys(value);
		results.log("The value "+value+" is entered successfully", true);
		}catch(Exception exp) {
			results.log("The value "+value+" is not entered successfully", true);
		}
	}
	
	
}
