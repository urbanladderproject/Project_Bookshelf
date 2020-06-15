package driversetup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;


public class DriverSetup {

	 static WebDriver driver;
     static String URL = "https://www.urbanladder.com/";
     public static String path_chrome = System.getProperty("user.dir") + "\\chromedriver.exe";
     public static String path_mozilla = System.getProperty("user.dir") + "\\geckodriver.exe";
     @BeforeTest     
     public static WebDriver getDriver(String browser) {
    	 //for chrome browser
    	 if (browser.equalsIgnoreCase("chrome")) 
    	 {
    		 WebDriverManager.chromedriver().setup();
    		 driver = new ChromeDriver();
    		 driver.manage().window().maximize();
    		 driver.navigate().to(URL);
    	 }
    	 
    	 //for mozilla firefox browser
    	 else if(browser.equalsIgnoreCase("Firefox") || browser.equalsIgnoreCase("Mozilla"))
 		{
 			System.setProperty("webdriver.gecko.driver", path_mozilla);
 			FirefoxBinary firefoxBinary = new FirefoxBinary();
 			firefoxBinary.addCommandLineOptions("--headless");
 		    FirefoxProfile profile=new FirefoxProfile();
 		    profile.setPreference("marionette.logging", "TRACE");
 			FirefoxOptions firefoxOptions = new FirefoxOptions();
 			firefoxOptions.setBinary(firefoxBinary);
 			firefoxOptions.setProfile(profile);
 		    driver = new FirefoxDriver();
 		    driver.navigate().to(URL);
 		}
    	return driver;
     }
     
}
