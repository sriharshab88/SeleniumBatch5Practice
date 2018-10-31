package results;

import org.testng.Assert;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import libraries.Configurations;

/**
 * This file consists of code related to Extent reports which generates a report 
 * file after each test execution
 * @author z002gh8
 *
 */
public class ExtentResults {

	public static ExtentReports extentReports = new ExtentReports(
			Configurations.resultsPath, true);
	public static ExtentTest log;
	
	public void createTestcase(String testcasename, String testname) throws Exception {
		
		log = extentReports.startTest(testcasename);
		log.assignCategory(testname);
		extentReports.endTest(log);
		
	}
	
	public void log(String message, boolean flag) throws Exception {
		try {
		Reporter.log(message, flag);
		log.log(LogStatus.PASS, "PASS -- "+message);
		}catch (Exception exp) {
			log.log(LogStatus.FAIL, "FAIL -- "+message, exp);
			throw new Exception(message);
		}
		finally {
			extentReports.endTest(log);
			extentReports.flush();
		}
	}
	
	public void assertEquals(String actual, String expected, String message) {
		try {
			Assert.assertEquals(actual, expected, message);
			log.log(LogStatus.PASS, "PASS -- "+message);
		}catch (AssertionError error) {
			log.log(LogStatus.FAIL, "FAIL -- "+message, error);
			throw new AssertionError();
		}finally {
			extentReports.endTest(log);
			extentReports.flush();
		}
	}

	
}
