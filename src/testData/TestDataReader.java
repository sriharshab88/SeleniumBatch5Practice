package testData;

import libraries.PropertiesReader;

public class TestDataReader {

	public static PropertiesReader propReader = new PropertiesReader();

	public static String email = propReader.getEmail();
	public static String password = propReader.getPassword();
	public static String myAccountPage = propReader.getMyAccountPageTitle();
	public static String myAccountPageMsg = propReader.getMyAccountPageTitleAssertionMessage();
	public static String userName = propReader.getUserName();
	public static String userNameMsg = propReader.getUserNameAssertionMessage();
	public static String homePage = propReader.getHomepageTitle();
	public static String homePageMsg = propReader.getHomepageTitleAssertionMessage();
	public static String loginPage = propReader.getLoginPageTitle();
	public static String loginPageMsg = propReader.getLoginPageTitleAssertionMessage();
	public static String signInPageText = propReader.getSignInPageText();
	public static String signInPageTextMsg = propReader.getSignInPageTextAssertionMessage();
	public static String loginModuleDisplayMsg = propReader.getLoginModuleDisplayAssertionMessage();
	public static String errorMessageDisplayStatus = propReader.getErrorMessageStatus();
	
	
	
}
