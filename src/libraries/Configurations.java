package libraries;

import java.io.File;

/**
 * This class file consists of the all application url's and path's required for this project
 * @author z002gh8
 *
 */

public class Configurations {
	
	public static String applicationUrl = "http://automationpractice.com/index.php";
	public static String testDatapath = System.getProperty("user.dir") +File.separator+
			"src"+File.separator+"testData"+File.separator+"TestData";

}
