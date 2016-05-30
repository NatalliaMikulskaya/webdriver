package by.epam.atl.google.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InboxMailPage extends MessagesFolderPage {
	
	private WebDriver driver;
	//private String newLetterButtonName = "COMPOSE";
		
	@FindBy(xpath = "//div[@role = 'button'][contains(text(),'COMPOSE')]")
	private WebElement buttonNewLetter;
	
	@FindBy (xpath = "")
	private WebElement buttonLogOut;
	
	@FindBy (xpath = "//a[contains(@href,'SignOutOptions')]")
	private WebElement linkSignOut;
	
	@FindBy (xpath = "//div[@aria-label='Report spam']")
	private WebElement buttonSpam;
	
	@FindBy (xpath = "//div[@role='navigation']/div/div[2]/span/span[2]/div")
	private WebElement linkMoreAtFolderBar;
	
	@FindBy (xpath = "//a[contains(@title,'Spam')]")
	private WebElement linkSpamFolder;
	
	public InboxMailPage(WebDriver currentDriver){
		
		driver = currentDriver;
		
		PageFactory.initElements(driver,this);
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

	public void openMessage(String fromUser, String messageTopic){
		
		if (isLetterReceivedAndInInbox( driver, fromUser, messageTopic)){

			//look for message
			WebElement foundedMessage = lookForMessageFromSenderWithTopic(driver, fromUser, messageTopic);
			foundedMessage.click();

		}
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
	
	@Override
	public boolean isEmailInboxPage(){
		return true;
	}
}
