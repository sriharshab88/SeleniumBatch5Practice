package testData;

import org.testng.annotations.DataProvider;

public class CommonTestData {

	@DataProvider
	public Object [][] signInTestCaseData(){	
		return new Object[][] {
			{"Invalid email 1", "emailInvalid", "123456", "Invalid email address."},
			{"Invalid email 2","email@@@.ttt.com", "123456", "Invalid email address."},
			{"Blank email","", "123456", "An email address required."},
			{"Blank password","test@test.com", "", "Password is required."},
		    {"Invalid password", "testbatch5@test.com", "1", "Invalid password."},
		    {"Authentication error","testbatch5@test.com", "qqqwqsdhbsdhasd", "Authentication failed."},
		    {"All Blanks", "", "", "An email address required."		
		}};
	}
	
}
