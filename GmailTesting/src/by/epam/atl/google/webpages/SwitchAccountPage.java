package by.epam.atl.google.webpages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SwitchAccountPage extends Page{
	
	private WebDriver driver;
	
	//page fields
	@FindBy (xpath = "//button[@name='Email']")
	private List<WebElement> accounts;
	
	@FindBy (id = "account-chooser-add-account")
	private WebElement linkAddAccount;
	
	//----- Constructor
	public SwitchAccountPage(WebDriver currentDriver){
		
		super(currentDriver);
		driver = currentDriver;

	}
	
	@Override
	public boolean isPageForChangeAccount(){
		return linkAddAccount.isDisplayed();
	}
	
	public Page chooseAccount(String accountName){
	
	
		highlight(driver, linkAddAccount);
		linkAddAccount.click();

		return new PageWithEmptyLogin(driver);

	}
	
	private void trash(){
		/*	if (accounts.size() > 0) {
		WebElement rightAccount = findAccount(accountName);
		System.out.println("SwitchAccountPage.findAccount: found account - "+ rightAccount);
		if (rightAccount != null) {
			highlight(driver, rightAccount);
			rightAccount.click();
			return new PageWithPassword(driver);
		}
	} */
	
	}
	

	private WebElement findAccount(String accName){
		
		System.out.println("SwitchAccountPage.findAccount: accName - "+ accName);
		for (WebElement nextAccount : accounts){
			
			String nextAcc = nextAccount.getAttribute("value");
			System.out.println("SwitchAccountPage.findAccount: account on page - "+ nextAcc);
			
			System.out.println("SwitchAccountPage.findAccount: nextAcc.equals(accName) - "+ nextAcc.equals(accName));
			if (nextAcc.equals(accName)){
				return nextAccount;
			}
		}
		 return null;
		
	}
}
