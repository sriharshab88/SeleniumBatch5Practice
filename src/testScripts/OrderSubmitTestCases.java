package testScripts;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class OrderSubmitTestCases {

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
	
	@Test(groups= {"Regression"})
	public void placeOrder() throws InterruptedException {
		driver.get("http://automationpractice.com/index.php"); //This will launch the application
		String windowTitle = driver.getTitle(); //Fetch the title of the current window
		assertEquals(windowTitle, "My Store", "FAIL -- the window title did not match");
		
		WebElement signinLink = driver.findElement(By.xpath("//a[@class='login']"));  //Fetch the web element signinLink
		signinLink.click();  //Clicks on the signinLink web element
		Thread.sleep(3000);
		String loginPageTitle = driver.getTitle();
		assertEquals(loginPageTitle, "Login - My Store", "FAIL -- Login page title did not match");
				
		WebElement loginModule = driver.findElement(By.xpath("//form[@id='login_form']")); //Fetching login module web element
		boolean isLoginModuleDisplayed = loginModule.isDisplayed();  //Checking whether the login module displayed or not
		assertTrue(isLoginModuleDisplayed, "FAIL -- Login module did not display in the login page");
		
		WebElement emailTextBox = driver.findElement(By.xpath("//input[@id='email']")); //Fetching email text box
		emailTextBox.sendKeys("testbatch5@test.com"); //Entering email value into the email text box
		
		driver.findElement(By.id("passwd")).sendKeys("12345678");  //Identifying the password text box through id and entering password into it
		
		driver.findElement(By.xpath("//button[@id='SubmitLogin']")).click();  //Identifying the Submit button and clicking on it
	
		System.out.println("****************************************************************");
		
		driver.findElement(By.xpath("//div[@id='header_logo']//img")).click();
		String productXpath = "//div[@class='tab-content']/ul/li[1]//img[@title='Faded Short Sleeve T-shirts']";
		driver.findElement(By.xpath(productXpath)).click();
		
		String productTitle = driver.findElement(By.xpath("//h1[@itemprop='name']")).getText();
		assertEquals(productTitle, "Faded Short Sleeve T-shirts", "FAIL -- Product title did not match");
		
		driver.findElement(By.xpath("//p[@id='add_to_cart']/button")).click();
		
		//String addToCartSuccessMsgXpath = "//div[@id='layer_cart']//div[contains(@class,'layer_cart_cart')]/h2/span";
		 //WebElement successModule = driver.findElement(By.xpath(addToCartSuccessMsgXpath));
		 //String addToCartSuccessMsg = successModule.getText();
		//assertEquals(addToCartSuccessMsg, "There is 1 item in your cart.", "FAIL -- Success message did not match");
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[@title='Proceed to checkout']")).click();
		
		WebElement orderDetailModule = driver.findElement(By.xpath("//div[@id='order-detail-content']"));
		assertTrue(orderDetailModule.isDisplayed(), "FAIL -- order details module is not displayed");
		
		WebElement deliveryAddressModule = driver.findElement(By.xpath("//ul[contains(@class,'address first_item')]"));
		assertTrue(deliveryAddressModule.isDisplayed(), "FAIL -- Delivery address module is not displayed");

		WebElement invoiceAddressModule = driver.findElement(By.xpath("//ul[contains(@class,'address last_item')]"));
		assertTrue(invoiceAddressModule.isDisplayed(), "FAIL -- Invoice address module is not displayed");
		
		driver.findElement(By.xpath("//p[@class='cart_navigation clearfix']"
				+ "/a[@title='Proceed to checkout']")).click();
		
		System.out.println("***************************Address page validations****************************************************");
		//Validate delivery address module
		//validate billing address module
		driver.findElement(By.xpath("//button[@name='processAddress']")).click();
		
		System.out.println("***************************Shipping page validations****************************************************");
		//Validate shipping option message
		//validate Delivery module
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//button[@name='processCarrier']")).click();
		
		System.out.println("***************************Payment page validations****************************************************");
		//Validate order detail module
		//validate In Stock message
		//validate 2 payment modules are displayed
		driver.findElement(By.xpath("//a[@class='bankwire']")).click();
		
		
		System.out.println("***************************Review page validations****************************************************");
		//Validate Review payment module
		//validate Payment title (BANK-WIRE PAYMENT.)
		driver.findElement(By.xpath("//p[@id='cart_navigation']/button")).click();
		
		System.out.println("***************************Confirmation page validations****************************************************");
		//Validate Order confirmation text
		//validate Payment title (BANK-WIRE PAYMENT.)
		String orderID = driver.findElement(By.xpath("//div[@class='box']/br[5]")).getText();
		driver.findElement(By.xpath("//a[@class='account']")).click();
		
		driver.findElement(By.xpath("//a[@title='Orders']")).click();
		String orderHistoryId = driver.findElement(By.xpath("//tr[contains(@class,'first_item')]"
				+ "//a[@class='color-myaccount']")).getText();
		
		boolean orderIDDisplayStatus = orderID.contains(orderHistoryId);
		assertTrue(orderIDDisplayStatus, "FAIL -- Order ID displayed in confirmation page and "
				+ "Order history page did not match");
		
		driver.findElement(By.xpath("logout")).click();
		
		
		
	}
	
}
