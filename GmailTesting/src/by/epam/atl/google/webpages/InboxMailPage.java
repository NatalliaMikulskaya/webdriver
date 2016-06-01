package by.epam.atl.google.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InboxMailPage extends MessagesFolderPage {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
		
	@FindBy(xpath = "//div[@role = 'button'][contains(text(),'COMPOSE')]")
	private WebElement buttonNewLetter;
	
	@FindBy (xpath = "")
	private WebElement buttonLogOut;
	
	@FindBy (xpath = "//a[contains(@href,'SignOutOptions')]")
	private WebElement linkSignOut;
	
	@FindBy (xpath = "//div[@title='Report spam']")
	//"//div[@aria-label='Report spam']"
	private WebElement buttonSpam;
	
	@FindBy (xpath = "//div[@role='navigation']/div/div[2]/span/span[2]/div")
	private WebElement linkMoreAtFolderBar;
	
	@FindBy (xpath = "//a[contains(@title,'Spam')]")
	private WebElement linkSpamFolder;
	
	@FindBy (xpath = "//body/div[7]/div[3]/div/div[2]/div/div[2]/div/div/div/div/div/div/div[2]/div[2]/div/div[2]") 
	private WebElement settingsButton;
	
	@FindBy (xpath = "//div[@aria-haspopup='true'][@role='menu']/div/div[8]")
	//(xpath = "//div[@aria-haspopup='true'][@role='menu']/div/div[8]/div")
	private WebElement linkSettingsInPopupMenu;
	
	@FindBy (xpath = "//div[@aria-haspopup='true'][@role='menu']/div/div[8]/div")
	//(xpath = "//div[@aria-haspopup='true'][@role='menu']/div/div[8]/div")
	private WebElement linkSettingsInPopupMenu1;
	
	public InboxMailPage(WebDriver currentDriver){
		
		super(currentDriver);
		driver = currentDriver;
		
	}
	
	public boolean isPageOpen(String login){
		
		WebElement userLoginAtBanner = driver.findElement(By.xpath("//div[@role='banner']/*/*/*/*/div[.='"+login+"']"));
		
		if (userLoginAtBanner != null) {
			return true;
		}
		
		return false;
		
	}
	
	public void clickButtonForCreatingNewMessage(){
		buttonNewLetter.click();
	}
	
	public void clickLinkAccountInfo(){
		
		linkSignOut.click();
	
	}
	
	public void clickSpamButton(){
		
		buttonSpam.click();
	
	}

	public void openMessage(String fromUser, String messageTopic, boolean lookForPartialTopik){
		
		if (lookForPartialTopik){
			if (isLetterReceivedWithPartialTopicAndInInbox( driver, fromUser, messageTopic)){

				//look for message
				WebElement foundedMessage = lookForMessageFromSenderWithPartialTopic(driver, fromUser, messageTopic);
				foundedMessage.click();
			}
			
		} else {
			if (isLetterReceivedAndInInbox( driver, fromUser, messageTopic)){

				//look for message
				WebElement foundedMessage = lookForMessageFromSenderWithTopic(driver, fromUser, messageTopic);
				foundedMessage.click();
			}
		}
	}
	
	public void openMessage(WebElement message){
		
		message.click();
		
	}
	
	public void markMessage(String fromUser, String messageTopic){
		
		if (isLetterReceivedAndInInbox( driver, fromUser, messageTopic)){

			//look for message
			WebElement foundedMessage = lookForMessageFromSenderWithTopic(driver, fromUser, messageTopic);
			
			//look for checkbox in founded message 
			WebElement markForMessage = foundedMessage.findElement(By.xpath("//div[@role='checkbox']"));
			
			markForMessage.click();
		}
	}
	
	public void clickLinkMoreAtFolderBar(){
		
		linkMoreAtFolderBar.click();
	}
	
	public void clickLinkSpamFolder() {
		
		linkSpamFolder.click();
	}
	
	public void clickmenuSettingsInPopup(){
		
		Actions act = new Actions(driver);
		act.moveToElement(settingsButton).clickAndHold().moveToElement(linkSettingsInPopupMenu).release().build().perform();
		
		
		
		/*highlight(driver, linkSettingsInPopupMenu);
		
		Actions act = new Actions(driver);
		act.moveToElement(linkSettingsInPopupMenu).click(linkSettingsInPopupMenu1).build().perform();*/
		
	}
	
	@Override
	public boolean isEmailInboxPage(){
		return true;
	}
}
