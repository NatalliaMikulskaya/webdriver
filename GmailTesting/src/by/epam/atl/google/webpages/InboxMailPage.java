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
	
	/*	
	
	@FindBy (xpath = "")
	private WebElement buttonLogOut;*/
	
	
	@FindBy (xpath = "//div[@title='Report spam']")
	//"//div[@aria-label='Report spam']"
	private WebElement buttonSpam;
	
	
	
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

	public void clickSpamButton(){
		
		buttonSpam.click();
	
	}

	public void openMessage(String fromUser, String messageTopic, boolean lookForPartialTopik){
		
		if (lookForPartialTopik){
			if (isLetterReceivedWithPartialTopicAndInFolder( driver, fromUser, messageTopic)){

				//look for message
				WebElement foundedMessage = lookForMessageFromSenderWithPartialTopic(driver, fromUser, messageTopic);
				foundedMessage.click();
			}
			
		} else {
			if (isLetterReceivedAndInFolder( driver, fromUser, messageTopic)){

				//look for message
				WebElement foundedMessage = lookForMessageFromSenderWithTopic(driver, fromUser, messageTopic);
				foundedMessage.click();
			}
		}
	}
	
	@Override
	public boolean isEmailInboxPage(){
		return true;
	}
}
