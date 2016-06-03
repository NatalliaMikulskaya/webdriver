package by.epam.atl.google.service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import by.epam.atl.google.exception.PageException;
import by.epam.atl.google.webpages.InboxMailPage;

public class Step {

	private WebDriver driver;
	private static Step instance;
	private static Operation operation;
	
	private static final String GMAIL_TEAM_EMAIL = "forwarding-noreply@google.com";
	private static final String FORWARD_MESSAGE_TOPIK = "Gmail Forwarding Confirmation - Receive Emails from ";
	
	private Step (WebDriver currentDriver){
		
		driver = currentDriver;
		operation = Operation.getInstance(driver);
	}
	
	public static Step getInstance(WebDriver currentDriver){
		
		if (instance == null){
			instance = new Step(currentDriver);
		}
		
		return instance;
	}
	
	public void goToSpamFolderforUser(String userLogin, String userPassword) throws InterruptedException{
		
		goToInboxForUser(userLogin, userPassword);
		
		operation.openSpamFolder();
		
	}
	
	public void forUser2MarkLetterFromUser1AsSpam(String userLogin, String userPassword, String senderLogin, String messageTopic) throws InterruptedException{
		
		//login user
		goToInboxForUser(userLogin, userPassword);
			
		//mark letter as spam
		operation.markLetterAsSpam(senderLogin, messageTopic);
		
		//log out
		operation.logOutFromInbox();
		
	}
	
	public void goToInboxForUser(String userLogin, String userPassword) {
		
		if (loginByUser(userLogin, userPassword)){
			//log in other user
		
			//go to gmail
			operation.clickGmailLink();
		}
		
	}
	
	public void logOutUser(){
		
		operation.logOutFromInbox();
	
		//clear cookies
		driver.manage().deleteAllCookies();
	}
	
	public void sendLetterFromUser1ToUser2(String userLogin, String userPassword, String forHwom, String messageTopic, String messageText) {
	
		if (loginByUser(userLogin, userPassword)){

			//go to gmail
			operation.clickGmailLink();

			//write and send message
			operation.clickButtonForNewMessage();

			operation.writeNewMessageAndSend(forHwom, messageTopic, messageText);

			//log out
			operation.logOutFromInbox();
		}
	}
	
	public void sendLetterWithAttachFromUser1ToUser2(String userLogin, 
													String userPassword, 
													String forHwom, 
													String messageTopic, 
													String messageText,
													String fileForAttachment) {
		
		if (loginByUser(userLogin, userPassword)){

			//go to gmail
			operation.clickGmailLink();

			//write and send message
			operation.clickButtonForNewMessage();

			operation.writeNewMessageWithAttachmentAndSend(forHwom, messageTopic, messageText, fileForAttachment);

			//log out
			operation.logOutFromInbox();
		}
	}
	
	public boolean loginByUser(String userLogin, String userPassword){
		
		//log in
		operation.openMainGooglePageAndPushSigInButton();

		try{
			operation.logInUser(userLogin, userPassword);
			return true;
		}
		catch (PageException e){
			Assert.assertTrue(false,"Can't log in. ");
		}
		
		return false;
		
	}
	
	public boolean isMessageFromSenderWithTopicInSpamFolder(String fromHwom, String messageTopic){
		
		return operation.isMessageFromSenderWithTopicInSpamFolder(fromHwom, messageTopic);

	}
	
	public void setupForwardingForUser(String forwardToUser){
		
		operation.clickSettings();
		
		operation.setupForwarding(forwardToUser);
		
	}
	
	public void confirmForwardingFromUser(String userHwoCongirm, String userPassword, String forWhomConfirm){
		
		//log in
		loginByUser(userHwoCongirm, userPassword);
		
		//go to gmail
		operation.clickGmailLink();
		
		String topikForSearch = FORWARD_MESSAGE_TOPIK + forWhomConfirm;
		
		if (operation.isMessageFromSenderWithPartialTopicReceived(GMAIL_TEAM_EMAIL, topikForSearch)){
			
			operation.openMessage(GMAIL_TEAM_EMAIL, topikForSearch);
			
			//remember our main window
			String mainWindow = driver.getWindowHandle();
			
			operation.clickSubmitLinkInForwardingSettingsMessage();
			
			//confirm forwarding
			operation.confirm(mainWindow);
			
			//return to our main window
			driver.switchTo().window(mainWindow);
			
		} else {
			System.out.println("Ooops... Message was not fouded.");
		}
	}
	
	public void turnOnForwartingforUser(String userToWhomForward){
		
		operation.clickSettings();
		
		operation.turnOnForwardingForUser(userToWhomForward);
	}
	
	public void setupFilter(String userFrom){
		
		operation.clickSettings();
		
		operation.setupFilter(userFrom);
	
	}
}
