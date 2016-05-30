package by.epam.atl.google.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	private WebDriver driver;
	
	private static String homeURL = "http://www.google.com";
	
	// -- locators
	@FindBy (xpath = "//a[contains(@href,'accounts.google.com')]")
	private WebElement buttonEnter;
	
	public HomePage(WebDriver wbDriver){
		
		driver = wbDriver;
		driver.get(homeURL);
		
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnEnter(){
		buttonEnter.click();
	}
	
	
}
