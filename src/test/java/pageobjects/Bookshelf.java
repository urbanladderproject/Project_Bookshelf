package pageobjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import utils.ExcelUtils_Bookshelf_name_and_price;

public class Bookshelf {
	WebDriver driver;
	
	//assigns the xpath to the corresponding By classes	
	By clearpage = By.partialLinkText("Close");
	By searchboxtext = By.id("search");
	By btn_submit = By.xpath("//span[@class='search-icon icofont-search']");
	By chk_stock = By.id("filters_availability_In_Stock_Only");
	By storage_dropdown = By.xpath("//div[contains(text(),'Storage Type')]");
	By storage = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/ul[1]/li[3]/div[2]/div[1]/div[1]/div[1]/ul[1]/li[1]/input[1]");
	String pn_1 = "/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[3]/ul[1]/li[";
	String pn_2 = "]/div[1]/div[5]/a[1]/div[1]/span[1]";
	String pn_2_2 = "]/div[1]/div[3]/a[1]/div[1]/span[1]";
	String pp_1 = "/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[3]/ul[1]/li[";
	String pp_2 = "]/div[1]/div[5]/a[1]/div[2]/span[1]";
	String pp_2_2 = "]/div[1]/div[3]/a[1]/div[2]/span[1]";
	
	int flag = 0;

	
    public Bookshelf(WebDriver driver)
    {
    	this.driver = driver;
    }
    
    public void clearpage() {
    	
    	if(flag==0){
    		driver.findElement(clearpage).click();
    	   	System.out.println("Page cleared to search");
		flag++;
    	}
    	else
    	{
    		System.out.println("Page is already clear");
    	}
    }
    
    public void searchText(String searchdata) throws Exception {
    	
	    //Searches for bookshelf
    	//driver.findElement(searchboxtext).clear();
    	WebElement toClear = driver.findElement(searchboxtext);
    	toClear.sendKeys(Keys.CONTROL + "a");
    	toClear.sendKeys(Keys.DELETE);
    	driver.findElement(searchboxtext).sendKeys(searchdata);
    	System.out.println("searched");
    	
    }   
    
    public void searchbutton() {
    	
    	//Clicks on submit button
    	driver.findElement(btn_submit).click();
    	System.out.println("submited");
    	
    }
    
    public void stockdetails() {
    	
    	//Check box exclude out of box is checked
    	driver.findElement(chk_stock).click();
    	System.out.println("Excluded out of stock");
    	
    }
    
    public void storage_type() throws Exception {
    
    	System.out.println("Into storage type");
    	new Actions(driver).moveToElement(driver.findElement(storage_dropdown)).build().perform();
    	driver.findElement(storage).click();
	Thread.sleep(3000);	    
    	//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
    	System.out.println("Storage type is open");
    	
    }
    
    public void getresult(String browser) throws Exception, InterruptedException
	{
		String[][] result = new String[5][2];
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String productname_web,productprice_web;
		int productprice_int;
		//displays the first five mobile phone's name and price
		int j = 0;
		for(int i=1;j<3;i++)
		{			
			try
			{
				String productname=pn_1+i+pn_2;
				String productprice=pp_1+i+pp_2;
				productname_web= driver.findElement(By.xpath(productname)).getText().toString();
				productprice_web=driver.findElement(By.xpath(productprice)).getText().toString().substring(1);
				productprice_web = productprice_web.replaceAll("\\p{Punct}","");
				System.out.println(productname_web +" and the price of the product is : "+productprice_web);

			}
			catch(Exception ex)
			{
				String productname=pn_1+i+pn_2_2;
				String productprice=pp_1+i+pp_2_2;
				productname_web= driver.findElement(By.xpath(productname)).getText().toString();
				productprice_web=driver.findElement(By.xpath(productprice)).getText().toString().substring(1);
				productprice_web = productprice_web.replaceAll("\\p{Punct}","");
				System.out.println(productname_web +" and the price of the product is : "+productprice_web);

			}
			productprice_int = Integer.valueOf(productprice_web);
			if(productprice_int <= 15000)
			{
				result[j][0] = productname_web;
				result[j][1] = productprice_web;
				j++;
				
			}
			//Assert.assertTrue((Integer.valueOf(productprice_web)) < 30000);   
		}	
		ExcelUtils_Bookshelf_name_and_price.writeExcelData(result,browser);

		//waits for 30 seconds
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}
}
