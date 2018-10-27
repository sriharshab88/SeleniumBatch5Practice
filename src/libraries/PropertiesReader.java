package libraries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * This file will actually read the data from the Properties file. This will help in
 * fetching the test data for this project
 * @author Sri harsha
 */
public class PropertiesReader {
	
	Properties prop;

	public PropertiesReader() {
		
		File file = new File(Configurations.testDatapath);
		  
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		prop = new Properties();
		
		//load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		}

	public String getEmail() {
		return prop.getProperty("email");
	}
	
	public String getPassword() {
		return prop.getProperty("password");
	}
	
	public String getMyAccountPageTitle() {
		return prop.getProperty("myAccountPageTitle");
	}
	
	public String getMyAccountPageTitleAssertionMessage() {
		return prop.getProperty("myAccountPageTitleAssertionMessage");
	}
	
	public String getUserName() {
		return prop.getProperty("userName");
	}
	public String getUserNameAssertionMessage() {
		return prop.getProperty("userNameAssertionMessage");
	}
	public String getHomepageTitle() {
		return prop.getProperty("homepageTitle");
	}
	public String getHomepageTitleAssertionMessage() {
		return prop.getProperty("homepageTitleAssertionMessage");
	}
	public String getLoginPageTitle() {
		return prop.getProperty("loginPageTitle");
	}
	
	public String getLoginPageTitleAssertionMessage() {
		return prop.getProperty("loginPageTitleAssertionMessage");
	}
	
	public String getSignInPageText() {
		return prop.getProperty("signInPageText");
	}
	
	public String getSignInPageTextAssertionMessage() {
		return prop.getProperty("signInPageTextAssertionMessage");
	}
	
	public String getLoginModuleDisplayAssertionMessage() {
		return prop.getProperty("loginModuleDisplayAssertionMessage");
	}
	
	
	
}
