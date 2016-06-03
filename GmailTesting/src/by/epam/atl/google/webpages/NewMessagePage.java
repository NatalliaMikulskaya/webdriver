package by.epam.atl.google.webpages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewMessagePage extends Page{
	
	private WebDriver driver;
	
	@FindBy (xpath = "//textarea[@name='to']")
	private WebElement toWhom;
	
	@FindBy (xpath = "//input[@name='subjectbox']")
	private WebElement topic;
	
	@FindBy (xpath = "//div[@role='textbox'][@aria-label='Message Body']")
	private WebElement text;
	
	@FindBy (xpath = "//div[@role='button'][.='Send']")
	private WebElement sendButton;
	
	@FindBy (xpath = "//div[@command='Files']/div/div/div")
	private WebElement buttonAttachment;
	
	
	
	
	public NewMessagePage(WebDriver currentDriver){
		
		super(currentDriver);
		driver = currentDriver;
		
	}
	
	public void writeNewMessageAndSend(String forWhom, String messageTopic, String messageText){
		
		toWhom.sendKeys(forWhom);
		topic.sendKeys(messageTopic);
		text.sendKeys(messageText);
		
		sendButton.click();
	}
	
	public void writeNewMessageWithAttachmentAndSend(String forWhom, 
													String messageTopic, 
													String messageText,
													String fileForAttachment) {
		
		toWhom.sendKeys(forWhom);
		topic.sendKeys(messageTopic);
		text.sendKeys(messageText);
		
		attachFile(fileForAttachment);
		
		(new WebDriverWait(driver, 30)).
		until(ExpectedConditions.
				invisibilityOfElementLocated(By.xpath("//*[@role='progressbar']")));
		
		highlight(driver, sendButton);
		//sendButton.click();
		Actions act = new Actions(driver);
		
		act.click(sendButton).build().perform();
		
		try {
			(new WebDriverWait(driver, 30)).wait();
		}
		catch(InterruptedException e){
			
		}
		//until(ExpectedConditions.
				//invisibilityOfElementLocated(By.xpath("//*[@role='progressbar']")));
		
	}

	private void attachFile(String fileName){
		
		highlight(driver, buttonAttachment);
		buttonAttachment.click();
		
		StringSelection filepath = new StringSelection(fileName);

		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filepath, null);

		try {

			Robot robot = new Robot();

			robot.keyPress(KeyEvent.VK_CONTROL);

			robot.keyPress(KeyEvent.VK_V);

			robot.keyRelease(KeyEvent.VK_V);

			robot.keyRelease(KeyEvent.VK_CONTROL);

			robot.keyPress(KeyEvent.VK_ENTER);

			robot.keyRelease(KeyEvent.VK_ENTER);

		} catch (AWTException e) 
		{
			e.printStackTrace();
			}
		
		//sendKeys(fileName);
		
		
		
	}
}
