package by.epam.atl.google.action;

import org.openqa.selenium.WebDriver;

import by.epam.atl.google.exception.PageException;
import by.epam.atl.google.webpages.AccountInformationPage;
import by.epam.atl.google.webpages.GoogleAccountPage;
import by.epam.atl.google.webpages.HomePage;
import by.epam.atl.google.webpages.InboxMailPage;
import by.epam.atl.google.webpages.NewMessagePage;
import by.epam.atl.google.webpages.Page;
import by.epam.atl.google.webpages.PageWithEmptyLogin;
import by.epam.atl.google.webpages.PageWithPassword;
import by.epam.atl.google.webpages.SpamFolderPage;
import by.epam.atl.google.webpages.SwitchAccountPage;

public class Action {

	private WebDriver driver;
	private static Action instance;
	
	private Action(WebDriver currentDriver){
		
		driver = currentDriver;
	}
	
	public static Action getInstance(WebDriver currentDriver){
		
		if (instance == null){
			instance = new Action(currentDriver);
		}
		
		return instance;
	}
	
	public void openMainGooglePageAndPushSigInButton(){
		
		HomePage homePage = new HomePage(driver);
		homePage.clickOnEnter();
	}
	
	public void logInUser(String userLogin, String userPassword) throws PageException{
		
		try {

			//expected that we at page for login entering
			enterLogin(userLogin);

			enterPassword(userPassword);
		}
		catch (PageException e){

			//if not suggest we at page with password

			//was opened other page (other login and password)
			PageWithPassword passwordPage = new PageWithPassword(driver);
			
			if (passwordPage.isPageOpen()){

				//check if login equals our login
				if (passwordPage.isTheSameLogin(userLogin)){
					
					passwordPage.passwordEnter(userPassword);
				} else {

					//don't find account. Click for add other account
					passwordPage.clickLinkForSigningForDifferentAccount();

					switchOtherLoginAtPasswordPage(userLogin, userPassword);
				}
			}
			else {
				
				throw new PageException("Unexpected page. Page for entering password expected.");
			}
		}
	}
	
	public void switchOtherLoginAtPasswordPage(String userLogin, String userPassword) throws PageException{
		
		//it is necessary to switch to other login
		
		SwitchAccountPage switchAccPage = new SwitchAccountPage(driver);
		
		if (switchAccPage.isPageForChangeAccount()){
			//try to choose account from list
			
			Page currentPage = switchAccPage.chooseAccount(userLogin);
			
			if (currentPage.isPageForPasswordEnter()){
				//if account is in list page with password will be opened
				PageWithPassword pswrdPage = (PageWithPassword) currentPage;
				pswrdPage.passwordEnter(userPassword);
				
			} else if (currentPage.isPageForLoginEnter()){
				//in other case page for login entering will be opened
				logInUser(userLogin, userPassword);
			}
			
		} else {
			
			throw new PageException("Unexpected page. Page for switch account expected.");
		}
		
	}
	
	public void enterLogin(String login) throws PageException{
		
		PageWithEmptyLogin loginPage = new PageWithEmptyLogin(driver);
		
		if (loginPage.isPageOpened()){

			loginPage.enterEmail(login);
			
		} else {

			throw new PageException("Unexpected page."); 
		}
				
	}
	
	public void enterPassword(String password){
		
		PageWithPassword passwordPage = new PageWithPassword(driver);
		passwordPage.passwordEnter(password);
		
	}
	
	public void clickButtonForNewMessage(){
		
		InboxMailPage inboxPage = new InboxMailPage(driver);
		inboxPage.clickButtonForCreatingNewMessage();
	}
	
	public void writeNewMessageAndSend (String forWhom, String messageTopic, String messageText){
		
		NewMessagePage newMessagePage = new NewMessagePage(driver);
		newMessagePage.writeNewMessageAndSend(forWhom, messageTopic, messageText);
	
	}
	
	public void logOutFromInbox(){
		
		InboxMailPage inboxPage = new InboxMailPage(driver);
		inboxPage.clickLinkAccountInfo();
		
		AccountInformationPage accountInfoPage = new AccountInformationPage(driver);
		accountInfoPage.clickLogOutButton();
	}
	
	public void clickGmailLink() throws InterruptedException{
		GoogleAccountPage accountPage = new GoogleAccountPage(driver);
		
		accountPage.clickGmailLink();
	}
	
	public void markLetterAsSpam(String fromUser, String messageTopic) throws InterruptedException{
		
		InboxMailPage inboxPage = new InboxMailPage(driver);
		
		inboxPage.markMessage(fromUser, messageTopic);
		
		inboxPage.clickSpamButton();
		
	}
	
	public void openSpamFolder() throws InterruptedException{
		
		InboxMailPage inboxPage = new InboxMailPage(driver);
		
		inboxPage.clickLinkMoreAtFolderBar();
		
		inboxPage = new InboxMailPage(driver);
		
		inboxPage.clickLinkSpamFolder();
		
	}
	
	public boolean isMessageFromSenterWithTopicInSpamFolder(String fromHwom, String messageTopic){
		
		SpamFolderPage spamPage = new SpamFolderPage(driver);
		
		return spamPage.isLetterReceivedAndInInbox(driver, fromHwom, messageTopic);
	}
	
}
