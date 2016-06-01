package by.epam.atl.google.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SettingsPage extends Page {
	
	private WebDriver driver;

	@FindBy (xpath = "//a[@href = 'https://mail.google.com/mail/#settings/fwdandpop']")
	private WebElement forwardingTab;
	
	@FindBy (xpath = "//input[@act='add']")
	private WebElement buttonAddForwardingAdress;
	
	@FindBy (how = How.XPATH, using = "//div[@role='alertdialog']/div[2]/div/input")
	private WebElement fieldForForwardingEmail;
	
	@FindBy (how = How.NAME, using = "next")
	private WebElement buttonNext;
	
	@FindBy (xpath = "//button[@guidedhelpid='save_changes_button']")
	private WebElement buttonSaveChanges; 
	
	@FindBy (xpath = "//button[@name='ok']")
	private WebElement buttonOKatConfirmationPopup;
	
	@FindBy (xpath = "//a[@href = 'https://mail.google.com/mail/#settings/filters']")
	private WebElement filterTab;
	
	@FindBy (xpath = "//table[@role='list']/tbody/tr[6]/td/span[1]")
	private WebElement linkCreateNewFilter;
	
	public SettingsPage(WebDriver currentDriver){
		
		super(currentDriver);
		driver = currentDriver;
		
	}
	
	public void clickForwardingTab(){
		
		highlight(driver, forwardingTab);
		forwardingTab.click();
	}
	
	public void clickFilterTab(){
		
		highlight(driver, filterTab);
		filterTab.click();
	}
	
	public void clickButtonForwardingAdress(){
		
		buttonAddForwardingAdress.click();
	}

	public void enterForwardingEmail(String forwardTo){
		
		fieldForForwardingEmail.sendKeys(forwardTo);
		
	}
	
	public void clickButtonNext(){
		
		buttonNext.click();
		
	}
	
	public void clickButtonSaveChanges(){
		
		if (buttonSaveChanges.isEnabled()){
			
			highlight(driver, buttonSaveChanges);
			buttonSaveChanges.click();
			
		}
		
	}
	
	public void clickButtonOKatConfirmationPopup(){
		
		highlight(driver, buttonOKatConfirmationPopup);
		buttonOKatConfirmationPopup.click();
	}
	
	public void clickLinkCreateNewFilter(){
		
		highlight(driver, linkCreateNewFilter);
		
		linkCreateNewFilter.click();
		
	}
	
	public void turnOnForwarding(String userForWhomForward){
		
		String radioBoxPath = "//option[@value='0'][.='" + userForWhomForward + "']/../../../../td[1]/input";
		
		WebElement radioBox = driver.findElement(By.xpath(radioBoxPath));
		
		highlight(driver, radioBox);
		
		Actions act = new Actions(driver);
		
		act.click(radioBox).release().build().perform();
		
	}
}
