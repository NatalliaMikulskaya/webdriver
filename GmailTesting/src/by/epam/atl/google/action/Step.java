package by.epam.atl.google.action;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import by.epam.atl.google.exception.PageException;

public class Step {

	private WebDriver driver;
	private static Step instance;
	private static Action action;
	
	private Step (WebDriver currentDriver){
		
		driver = currentDriver;
		action = Action.getInstance(driver);
	}
	
	public static Step getInstance(WebDriver currentDriver){
		
		if (instance == null){
			instance = new Step(currentDriver);
		}
		
		return instance;
	}
	
	public void goToSpamFolderforUser(String userLogin, String userPassword) throws InterruptedException{
		
		goToInboxForUser(userLogin, userPassword);
		
		action.openSpamFolder();
		
	}
	
	public void forUser2MarkLetterFromUser1AsSpam(String userLogin, String userPassword, String senderLogin, String messageTopic) throws InterruptedException{
		
		//login user
		goToInboxForUser(userLogin, userPassword);
			
		//mark letter as spam
		action.markLetterAsSpam(senderLogin, messageTopic);
		
		//log out
		action.logOutFromInbox();
		
	}
	
	public void goToInboxForUser(String userLogin, String userPassword) throws InterruptedException{
		
		if (loginByUser(userLogin, userPassword)){
			//log in other user
		
			//go to gmail
			action.clickGmailLink();
		}
		
	}
	
	public void logOutUser(){
		
		action.logOutFromInbox();
	}
	
	public void sendLetterFromUser1ToUser2(String userLogin, String userPassword, String forHwom, String messageTopic, String messageText) throws InterruptedException{
	
		if (loginByUser(userLogin, userPassword)){

			//go to gmail
			action.clickGmailLink();

			//write and send message
			action.clickButtonForNewMessage();

			action.writeNewMessageAndSend(forHwom, messageTopic, messageText);

			//log out
			action.logOutFromInbox();
		}
	}
	
	public boolean loginByUser(String userLogin, String userPassword){
		
		//log in
		action.openMainGooglePageAndPushSigInButton();

		try{
			action.logInUser(userLogin, userPassword);
			return true;
		}
		catch (PageException e){
			Assert.assertTrue(false,"Can't log in. ");
		}
		
		return false;
		
	}
	
	public boolean isMessageFromSenderWithTopicInSpamFolder(String fromHwom, String messageTopic){
		
		return action.isMessageFromSenterWithTopicInSpamFolder(fromHwom, messageTopic);

	}
}
