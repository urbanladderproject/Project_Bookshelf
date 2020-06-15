package libraryFunctions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshots {
	
	private WebDriver driver;
	static String url = "https://www.urbanladder.com";
	
	public Screenshots(WebDriver driver) {
		this.driver = driver;
	}
	public void take_Screenshots(String browser_type) {
		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try
		{
			if(browser_type.equalsIgnoreCase("Chrome")) {
				String savePath = System.getProperty("user.dir") + "\\Screenshot\\Chrome";
				Date todayTime = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH-mm-ss");
				String strDate= formatter.format(todayTime);
				FileUtils.copyFile(src, new File(savePath + strDate +".png"));
				System.out.println("done");

			}
			else if(browser_type.equalsIgnoreCase("Firefox") || browser_type.equalsIgnoreCase("Mozilla")) {
				String savePath = System.getProperty("user.dir") + "\\Screenshot\\Firefox";
				Date todayTime = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH-mm-ss");
				String strDate= formatter.format(todayTime);
				FileUtils.copyFile(src, new File(savePath + strDate +".png"));
				System.out.println("done");				

			}
			driver.navigate().to(url);

		}	 catch (IOException e) {

			e.printStackTrace();

		} 
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
}
