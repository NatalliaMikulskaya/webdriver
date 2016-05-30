package by.epam.atl.google.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewMessagePage {
	
	private WebDriver driver;
	
	@FindBy (xpath = "//textarea[@name='to']")
	private WebElement toWhom;
	
	@FindBy (xpath = "//input[@name='subjectbox']")
	private WebElement topic;
	
	@FindBy (xpath = "//div[@role='textbox'][@aria-label='Message Body']")
	private WebElement text;
	
	@FindBy (xpath = "//div[@role='button'][.='Send']")
	private WebElement sendButton;
	
	
	public NewMessagePage(WebDriver currentDriver){
		
		driver = currentDriver;
		
		PageFactory.initElements(driver, this);
	}
	
	public void writeNewMessageAndSend(String forWhom, String messageTopic, String messageText){
		
		toWhom.sendKeys(forWhom);
		topic.sendKeys(messageTopic);
		text.sendKeys(messageText);
		
		sendButton.click();
	}
	

}
