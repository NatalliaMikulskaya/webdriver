package by.epam.atl.google.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountInformationPage extends Page{

	@FindBy (xpath = "//a[contains(@href,'/Logout')]")
	private WebElement buttonSignOut;
	
	public AccountInformationPage(WebDriver currentDriver){
		
		super(currentDriver);
		
	}
	
	public void clickLogOutButton(){
		
		buttonSignOut.click();
	}
}
