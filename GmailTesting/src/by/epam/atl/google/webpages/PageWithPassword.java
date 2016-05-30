package by.epam.atl.google.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageWithPassword extends Page{
	
	private WebDriver driver;
	
	@FindBy (id = "reauthEmail")
	private WebElement loginInfo;
	
	@FindBy (name = "Passwd")
	private WebElement passwordField;
	
	@FindBy (xpath = "//input[@name='signIn'][@id='signIn']")
	private WebElement buttonEnter;
	
	@FindBy (id = "account-chooser-link")
	private WebElement linkSignByDifferentAccount;
	
	@FindBy (id = "reauthEmail")
	private WebElement fieldWithUserLogin;
	
	// --- Constructor
	public PageWithPassword(WebDriver driver){
		
		this.driver = driver;
		
		PageFactory.initElements(driver,this);
	}
	
	
	// --- Methods
	
	@Override
	public boolean isPageForPasswordEnter(){
		return passwordField.isDisplayed();
	} 
	
	public boolean isPageOpen(){
		
		return passwordField.isDisplayed();
		
	}

	public void passwordEnter(String pswrd){
		
		passwordField.click();
		passwordField.clear();
		passwordField.sendKeys(pswrd);
		if (buttonEnter != null){
			buttonEnter.click();
		} else {
			System.out.println("Oooops!");
		}
		
	}
	
	public void clickLinkForSigningForDifferentAccount(){
		
		linkSignByDifferentAccount.click();
	}
	
	//compare current login with given login
	public boolean isTheSameLogin(String otherLogin){
		
		String currentLogin = fieldWithUserLogin.getText();
		
		if (otherLogin.equals(currentLogin)){
			return true;
		}
		
		return false;
	}
	
}
