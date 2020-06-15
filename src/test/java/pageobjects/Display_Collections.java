package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class Display_Collections {
	
	WebDriver driver;
	
	//assigns the xpath to the corresponding By classes
	By list_data = By.xpath("/html[1]/body[1]/div[1]/header[1]/div[2]/div[1]/nav[1]/div[1]/ul[1]/li[10]/div[1]/div[1]/ul[1]/li[1]/ul[1]");
	By click_collections = By.xpath("/html[1]/body[1]/div[1]/header[1]/div[2]/div[1]/nav[1]/div[1]/ul[1]/li[10]/span[1]");
	
	
	public Display_Collections(WebDriver driver) {
		this.driver = driver;
	}
	
	public void collection_click() {
		driver.findElement(click_collections).click();
	}
	
	
	public String[] getdata() {
		
		String Data = driver.findElement(list_data).getText();
		String[] info = Data.split("\\r?\\n");
		return info;
		
	}
	
	public void displaydata(String[] data) {
		
		int length = data.length;
		System.out.println("BEING AT HOME");
		for(int i=0;i<length;i++) {
		System.out.println(data[i]);}
		
	}

}
