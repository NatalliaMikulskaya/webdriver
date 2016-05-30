package by.epam.atl.google.webpages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SwitchAccountPage extends Page{
	
	private WebDriver driver;
	
	//page fields
	@FindBy (xpath = "//button[@name='Email']")
	private List<WebElement> accounts;
	
	@FindBy (id = "account-chooser-add-account")
	private WebElement linkAddAccount;
	
	//----- Constructor
	public SwitchAccountPage(WebDriver currentDriver){
		
		driver = currentDriver;
		
		PageFactory.initElements(driver, this);
	}
	
	@Override
	public boolean isPageForChangeAccount(){
		return linkAddAccount.isDisplayed();
	}
	
	public Page chooseAccount(String accountName){
	
		if (accounts.size() > 0) {
			WebElement rightAccount = findAccount(accountName);
			
			if (rightAccount != null) {
				rightAccount.click();
				return new PageWithPassword(driver);
			}
		} 
		
		linkAddAccount.click();

		return new PageWithFilledLogin(driver);

	}
	

	private WebElement findAccount(String accName){
		
		for (WebElement nextAccount : accounts){
			
			String nextAcc = nextAccount.getText();

			if (nextAcc.equals(accName)){
				return nextAccount;
			}
		}
		 return null;
		
	}
}
