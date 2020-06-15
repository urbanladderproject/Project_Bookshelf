package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GiftCard {
	
	WebDriver driver;
	
	By select_giftcard = By.xpath("//a[contains(text(),'Gift Cards')]");
	By select_birthdayoranniversary = By.xpath("/html[1]/body[1]/div[1]/div[1]/main[1]/section[1]/section[1]/ul[1]/li[3]");
	By txt_amount = By.id("ip_2251506436");
	By next = By.xpath("/html[1]/body[1]/div[1]/div[1]/main[1]/section[1]/section[2]/div[1]/section[2]/button[1]");
	By receipent_name = By.name("recipient_name");
	By receipent_email = By.name("recipient_email");
	By sender_name = By.name("customer_name");
	By sender_email = By.name("customer_email");
	By sender_phone = By.name("customer_mobile_number");
	By btn_cnfrm = By.xpath("/html[1]/body[1]/div[1]/div[1]/main[1]/section[1]/section[3]/form[1]/button[1]");
	
	public GiftCard(WebDriver driver) {
		this.driver = driver;
	}
	
	public void enter_to_giftcard() {
		driver.findElement(select_giftcard).click();
	}
	
	public void select_gifttype() {
		driver.findElement(select_birthdayoranniversary).click();
	}
	
	public void enter_amount(String amount) {
		
		amount = amount.substring(0, 4);
		driver.findElement(txt_amount).sendKeys(amount);
	}

	public void next_page() {
		driver.findElement(next).click();
	}
	
	public void recepient_name(String name) {
		driver.findElement(receipent_name).sendKeys(name);
	}
	
	public void recepient_email(String email) {
		driver.findElement(receipent_email).sendKeys(email);
	}
	
	public void sender_name(String name) {
		driver.findElement(sender_name).sendKeys(name);
	}
	
	public void sender_email(String email) {
		driver.findElement(sender_email).sendKeys(email);
	}
	
	public void sender_phone(String phone) {
		driver.findElement(sender_phone).sendKeys(phone);
	}
	
	public void confirm() {
		driver.findElement(btn_cnfrm).click();
	}
}
