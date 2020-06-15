package pageobjects;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.ExcelUtils_Bookshelf_name_and_price;


public class ResultPage extends SearchPage {


	String pn_1 = "/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[3]/ul[1]/li[";
	String pn_2 = "]/div[1]/div[5]/a[1]/div[1]/span[1]";
	String pn_2_2 = "]/div[1]/div[3]/a[1]/div[1]/span[1]";
	String pp_1 = "/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[3]/ul[1]/li[";
	String pp_2 = "]/div[1]/div[5]/a[1]/div[2]/span[1]";
	String pp_2_2 = "]/div[1]/div[3]/a[1]/div[2]/span[1]";

	public ResultPage(WebDriver driver) {
		super(driver);
	}


	//html[1]/body[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/a[3]/div[1]/div[1]
	//div[contains(@class,'_1HmYoV _35HD7C')]//div[2]//div[1]//div[2]//div[1]//a[2]	
	//html[1]/body[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]/a[2]

	
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
