package MainPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

	public class DriverUtills {

	    public static  WebDriver driver;

	    @BeforeTest(alwaysRun = true)
	    public void setUp() {
	    	System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\efdal.gunbay\\eclipse-workspace\\MigrosProject\\drivers\\chromedriver.exe");
	        driver = new ChromeDriver();
	    }

	    public static WebDriver getDriver() {
	        if ( driver == null) {
	        	System.setProperty("webdriver.chrome.driver",
						"C:\\Users\\efdal.gunbay\\eclipse-workspace\\MigrosProject\\drivers\\chromedriver.exe");
	            driver = new ChromeDriver();
	        }
			return driver;
	    }

	    @AfterTest(alwaysRun = true)
	    public void tearDown() throws Exception {
	        driver.close();
	       driver.quit();
	    }
	}

