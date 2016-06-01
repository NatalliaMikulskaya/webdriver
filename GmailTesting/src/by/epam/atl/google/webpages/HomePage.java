package by.epam.atl.google.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Page{
	
	private WebDriver driver;
	
	// -- locators
	@FindBy (xpath = "//a[contains(@href,'accounts.google.com')]")
	private WebElement buttonEnter;
	
	public HomePage(WebDriver currentDriver){
		
		super(currentDriver);
		driver = currentDriver;
	
	}
	
	public void clickOnEnter(){
		buttonEnter.click();
	}
	
	
}
