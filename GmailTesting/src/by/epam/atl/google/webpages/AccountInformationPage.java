package by.epam.atl.google.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountInformationPage extends Page{

	private WebDriver driver;
	
	@FindBy (xpath = "//a[contains(@href,'/Logout')]")
	private WebElement buttonSignOut;
	
	public AccountInformationPage(WebDriver currentDriver){
		
		driver = currentDriver;
		
		PageFactory.initElements(driver, this);
	}
	
	public void clickLogOutButton(){
		
		buttonSignOut.click();
	}
}
