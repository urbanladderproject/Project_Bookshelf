package pageobjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SearchPage {
	WebDriver driver;
	
	//assigns the xpath to the corresponding By classes	
	By clearpage = By.partialLinkText("Close");
	By searchboxtext = By.id("search");
	By btn_submit = By.xpath("//span[@class='search-icon icofont-search']");
	By chk_stock = By.id("filters_availability_In_Stock_Only");
	By storage_dropdown = By.xpath("//div[contains(text(),'Storage Type')]");
	By storage = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/ul[1]/li[3]/div[2]/div[1]/div[1]/div[1]/ul[1]/li[1]/input[1]");
	
    public SearchPage(WebDriver driver)
    {
    	this.driver = driver;
    }
    
    public void clearpage() {
    	
    	try{
    		driver.findElement(clearpage).click();
    	   	System.out.println("Page cleared to search");
    	}
    	catch(Exception e)
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
    
    public void storage_type() {
    
    	System.out.println("Into storage type");
    	new Actions(driver).moveToElement(driver.findElement(storage_dropdown)).build().perform();
    	driver.findElement(storage).click();
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
    	System.out.println("Storage type is open");
    	
    }
    
}