package by.epam.atl.google.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageWithEmptyLogin extends Page{
	
	//Locators
	@FindBy (name = "Email")
	private WebElement loginField;
	
	@FindBy (xpath = "//input[@id='next']")
	private WebElement nextButton; 
	
	@FindBy (xpath = "//div[contains(@class,'card signin-card')]")
	private WebElement centralForm;
	
	public PageWithEmptyLogin(WebDriver wbDriver){
		
		super(wbDriver);
		
	}
	
	public boolean isPageOpened(){
		return loginField.isDisplayed();
	}
	
	
	public void enterEmail(String email){
		
		loginField.sendKeys(email);
		nextButton.click();
		
	}
	
	@Override
	public boolean isPageForLoginEnter(){
		
		return loginField.isDisplayed();
	}
}
