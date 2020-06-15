package testdata;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import driversetup.DriverSetup;
import utils.ExcelUtils_Bookshelf_name_and_price;
import utils.Excelutlis_Collections_List;
import pageobjects.Display_Collections;
import pageobjects.GiftCard;
import pageobjects.ResultPage;
import pageobjects.SearchPage;

public class TestValidData {

	public static WebDriver driver;
	public static String browser_type;
	static String url = "https://www.urbanladder.com";
	static String list_data[];

	
	ResultPage navigated_page;
	SearchPage search;
	Display_Collections collections;
	GiftCard giftcard;

	@BeforeClass
	@Parameters("browser")
	public void setup(String browser) {

		browser_type = browser;
		driver = DriverSetup.getDriver(browser);

		navigated_page = new ResultPage(driver);
		search = new SearchPage(driver);
		collections = new Display_Collections(driver);
		giftcard = new GiftCard(driver);
		System.out.println("before class done");

	}


	@DataProvider(name = "details")
	public static String[][] getExcelData() throws Exception {        
		//Call the method 'readExcelData' in class 'ExcelUtils' using sheet name 'customer_invalid'
		//Return the value
		System.out.println("into get exxcel data");
		String[][] values = ExcelUtils_Bookshelf_name_and_price.readExcelData("testdata");
		return values;
	}


	@Test(dataProvider = "details")
	public void testwebpage(String search_product,String Search_price,String Search_type, String amount,String r_name,String r_email,String s_name, String s_email,String s_phone) throws Exception{

		//String search_product = dataProvider1;

		//String Search_price = dataProvider2;

		//String Search_type = dataProvider3;

		//Search and display the name of first 3 bookshelves which ranges less than or equal to Rs.15000
		System.out.println(search_product + " " + Search_price + " " + Search_type + " " + browser_type);

		if((search_product.equalsIgnoreCase("Bookshelf")|| search_product.equalsIgnoreCase("Bookshelves")) && Search_price.equalsIgnoreCase("15000") && Search_type.equalsIgnoreCase("Open")) 
		{

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			search.clearpage();	

			//Criterias to be searched

			search.searchText(search_product);

			search.searchbutton();

			search.stockdetails();

			search.storage_type();
			
			//waits for 30 seconds to load the page
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 

			//prints the name and price of first three bookshelves displayed in the navigated page			
			
			navigated_page.getresult(browser_type);

			//take screenshot of the navigated page         
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
		else
		{
			if(!(search_product.equalsIgnoreCase("Bookshelf")) || !(search_product.equalsIgnoreCase("Bookshelves"))) {

				System.out.println("OPPSSS!! Your Project is to search for bookshelf");
			}

			if(!Search_price.equalsIgnoreCase("15000"))
			{
				System.out.println("OPPSSS!!!!! Your Project is to search for bookshelf of range upto 15000");
			}

			if(!Search_type.equalsIgnoreCase("Open"))
			{
				System.out.println("OPPSSS!!!!! Your Project is to search for bookshelf of Open type");
			}
		}
		
		
		//List the products that are displayed under "Beign At Home" in the "Collections" list
		collections.collection_click();
		list_data = collections.getdata();
		Excelutlis_Collections_List.ResultData(list_data, browser_type);
		collections.displaydata(list_data);
		
		
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		
		
		
		//Gift card form filling		
		giftcard.enter_to_giftcard();
		giftcard.select_gifttype();
		giftcard.enter_amount(amount);
		giftcard.next_page();
		giftcard.recepient_name(r_name);
		giftcard.recepient_email(r_email);
		giftcard.sender_name(s_name);
		giftcard.sender_email(s_email);
		giftcard.sender_phone(s_phone);
		giftcard.confirm();
		driver.navigate().to(url);
	}


	@AfterClass
	public void closeBrowser(){
		//close the driver
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//driver.close();
	}


}