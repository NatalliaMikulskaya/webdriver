package by.epam.atl.google.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import by.epam.atl.google.exception.PageException;

public class PageWithFilledLogin extends Page {
	
	private WebDriver driver;

	//Locators
	@FindBy (name = "Email")
	private WebElement loginField;
	
	@FindBy (xpath = "//input[@id='next']")
	private WebElement nextButton; 
	
	@FindBy (xpath = "//div[contains(@class,'card signin-card')]")
	private WebElement centralForm;
	
	
	// -- Constructor
	public PageWithFilledLogin(WebDriver wbDriver){
		driver = wbDriver;
		
		PageFactory.initElements(driver, this);
	}
	
	//-- Methods
	
	@Override
	public boolean isPageForLoginEnter(){
		return true;
	}
	
	public boolean isPageOpened(){
		
		if (loginField != null) { 
			return true;
		}
		
		return false;
	}
	
	public void enterEmail(String email) throws PageException{
		if (loginField != null) {
			loginField.sendKeys(email);
		} else {
			throw new PageException("Field 'login' was not founded.");
		}
		
		if (nextButton != null){
			nextButton.click();
		} else {
			throw new PageException("Button 'Next' was not founded.");
		}
		
	}
}
