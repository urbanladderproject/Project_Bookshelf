package testdata;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import driversetup.DriverSetup;
import libraryFunctions.Screenshots;
import utils.ExcelUtils_Bookshelf_name_and_price;
import utils.Excelutlis_Collections_List;
import pageobjects.Bookshelf;
import pageobjects.Display_Collections;
import pageobjects.GiftCard;
import reports.ExtentReport;

public class TestValidData extends ExtentReport {

	public static WebDriver driver;
	public static String browser_type;
	static String list_data[];
	static String url = "https://www.urbanladder.com";

	Screenshots ss;	
	Bookshelf bookshelf;
	Display_Collections collections;
	GiftCard giftcard;

	@BeforeTest(alwaysRun = true)
	@Parameters("browser")
	public void setup(String browser) {

		browser_type = browser;
		driver = DriverSetup.getDriver(browser);
		
		bookshelf = new Bookshelf(driver);
		collections = new Display_Collections(driver);
		giftcard = new GiftCard(driver);
		ss = new Screenshots(driver);
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
		
		test = extent.createTest("testwebpage");
		
		System.out.println(search_product + " " + Search_price + " " + Search_type + " " + browser_type);

		if((search_product.equalsIgnoreCase("Bookshelf")|| search_product.equalsIgnoreCase("Bookshelves")) && Search_price.equalsIgnoreCase("15000") && Search_type.equalsIgnoreCase("Open")) 
		{
			
			//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                         
			Thread.sleep(3000);

			bookshelf.clearpage();	

			//Criterias to be searched

			bookshelf.searchText(search_product);

			bookshelf.searchbutton();

			bookshelf.stockdetails();

			//bookshelf.storage_type();
			
			//waits for 30 seconds to load the page
			
			//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
			
			Thread.sleep(3000);

			//prints the name and price of first three bookshelves displayed in the navigated page			
			
			bookshelf.getresult(browser_type);

			//take screenshot of the navigated page         
			
			ss.take_Screenshots(browser_type);

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
		
		
		//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		Thread.sleep(3000);
				
		
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
	public void closeBrowser() throws InterruptedException{
		//close the driver
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Thread.sleep(3000);		
		//driver.close();
	}


}
