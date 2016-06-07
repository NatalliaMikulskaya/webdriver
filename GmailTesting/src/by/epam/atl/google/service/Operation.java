package by.epam.atl.google.service;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import by.epam.atl.google.exception.PageException;
import by.epam.atl.google.loggin.ScreenshotMaker;
import by.epam.atl.google.webpages.AccountInformationPage;
import by.epam.atl.google.webpages.ConfirmPage;
import by.epam.atl.google.webpages.FilterPage;
import by.epam.atl.google.webpages.GoogleAccountPage;
import by.epam.atl.google.webpages.HomePage;
import by.epam.atl.google.webpages.InboxMailPage;
import by.epam.atl.google.webpages.MessagePage;
import by.epam.atl.google.webpages.NewMessagePage;
import by.epam.atl.google.webpages.Page;
import by.epam.atl.google.webpages.PageWithEmptyLogin;
import by.epam.atl.google.webpages.PageWithPassword;
import by.epam.atl.google.webpages.SettingsPage;
import by.epam.atl.google.webpages.SpamFolderPage;
import by.epam.atl.google.webpages.SwitchAccountPage;
import by.epam.atl.google.webpages.TrashFolderPage;

public class Operation {

	private WebDriver driver;
	private static Operation instance;
	private static String homeURL = "http://www.google.com";
		
	private Operation(WebDriver currentDriver){
		
		driver = currentDriver;
	}
	
	public static Operation getInstance(WebDriver currentDriver){
		
		if (instance == null){
			instance = new Operation(currentDriver);
		}
		
		return instance;
	}
	
	public void openMainGooglePageAndPushSigInButton(){
		
		driver.get(homeURL);
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
		
		System.out.println("Operation.switchOtherLoginAtPasswordPage: isPageForChangeAccount - "+switchAccPage.isPageForChangeAccount());
		
		if (switchAccPage.isPageForChangeAccount()){
			//try to choose account from list
			
			Page currentPage = switchAccPage.chooseAccount(userLogin);
			
			System.out.println("Operation.switchOtherLoginAtPasswordPage: "  + currentPage.getClass().getName());
			
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
	
	public void writeNewMessageWithAttachmentAndSend (String forWhom, 
													String messageTopic, 
													String messageText,
													String fileForAttachment){
		
		NewMessagePage newMessagePage = new NewMessagePage(driver);
		newMessagePage.writeNewMessageWithAttachmentAndSend(forWhom, messageTopic, messageText, fileForAttachment);
	
	}
	
	public void logOutFromInbox(){
		
		InboxMailPage inboxPage = new InboxMailPage(driver);
		inboxPage.clickLinkAccountInfo();
		
		AccountInformationPage accountInfoPage = new AccountInformationPage(driver);
		accountInfoPage.clickLogOutButton();
	}
	
	public void clickGmailLink() {
		GoogleAccountPage accountPage = new GoogleAccountPage(driver);
		
		accountPage.clickGmailLink();
	}
	
	public void markLetterAsSpam(String fromUser, String messageTopic) throws InterruptedException{
		
		InboxMailPage inboxPage = new InboxMailPage(driver);
		
		inboxPage.markMessage(driver, fromUser, messageTopic);
		
		inboxPage.clickSpamButton();
		
	}
	
	public boolean isMessageFromSenderWithPartialTopicReceived(String fromUser, String messageTopic) {
		
		InboxMailPage inboxPage = new InboxMailPage(driver);
		
		return inboxPage.isLetterReceivedWithPartialTopicAndInFolder(driver, fromUser, messageTopic);
		
	}
	
	public void openMessage (String fromUser, String messageTopic){
		
		InboxMailPage inboxPage = new InboxMailPage(driver);
		
		inboxPage.openMessage(fromUser, messageTopic, true);
		
	}
	
	public void clickSubmitLinkInForwardingSettingsMessage(){
		
		driver.findElement(By.xpath("//a[4]")).click();
			
	}
	
	
	public void openSpamFolder() {
		
		InboxMailPage inboxPage = new InboxMailPage(driver);
		
		inboxPage.clickLinkMoreAtFolderBar();
		
		inboxPage = new InboxMailPage(driver);
		
		inboxPage.clickLinkSpamFolder();
		
	}
	
	public void openTrashFolder() {
		
		InboxMailPage inboxPage = new InboxMailPage(driver);
		
		inboxPage.clickLinkMoreAtFolderBar();
		
		inboxPage = new InboxMailPage(driver);
		
		inboxPage.clickLinkTrashFolder();
		
	}
	
	public boolean isMessageFromSenderWithTopicInSpamFolder(String fromHwom, String messageTopic){
		
		SpamFolderPage spamPage = new SpamFolderPage(driver);
		
		return spamPage.isLetterReceivedAndInFolder(driver, fromHwom, messageTopic);
	}
	
	public boolean isMessageWithAttachFromSenderWithTopicInTrashFolderAndImportant(String fromHwom, String messageTopic){
	
		TrashFolderPage trashPage = new TrashFolderPage(driver);
		
		return trashPage.isLetterWithAttachmentMarkAsImportandAndInFolder(driver, fromHwom, messageTopic);
		
	}
	
	public void clickSettings(){
		
		InboxMailPage inboxPage = new InboxMailPage(driver);
		
		inboxPage.clickmenuSettingsInPopup(driver);
	}
	
	public void setupForwarding(String forwardTo){
		
		SettingsPage settingPage = new SettingsPage(driver);
		
		settingPage.clickForwardingTab();
		settingPage = new SettingsPage(driver);
		settingPage.clickButtonForwardingAdress();
		
		settingPage = new SettingsPage(driver);
		settingPage.enterForwardingEmail(forwardTo);
		settingPage.clickButtonNext();
		
		String mainWindow = driver.getWindowHandle();
		
		confirm(mainWindow);
		
		driver.switchTo().window(mainWindow);
		
		//final confirmation forwarding
		settingPage = new SettingsPage(driver);
		settingPage.clickButtonOKatConfirmationPopup();
		
		//return to settings and save changes
		settingPage = new SettingsPage(driver);
		settingPage.clickButtonSaveChanges();
		
		
	}
	
	public void confirm(String mainWindow){
		
		
		String popupWindow = "";
		
		Set <String> handles =driver.getWindowHandles();
		
		Iterator<String> it = handles.iterator();
		//iterate through your windows
		while (it.hasNext()){
			String newWindow = it.next();
			if (!newWindow.equals(mainWindow)){
				popupWindow = newWindow;
			}
		}
		
		driver.switchTo().window(popupWindow);
		
		//confirm operation
		ConfirmPage confirmPage = new ConfirmPage(driver);
		confirmPage.clickButtonProceed();
		
		popupWindow = "";
		handles =driver.getWindowHandles();
		
	/*	it = handles.iterator();
		//iterate through your windows
		while (it.hasNext()){
			String newWindow = it.next();
			if (!newWindow.equals(mainWindow)){
				popupWindow = newWindow;
			}
		}
		if (!popupWindow.isEmpty()){
			driver.switchTo().window(popupWindow);
			driver.quit();
		}*/
		
	}
	
	public void turnOnForwardingForUser(String userForWhomForward){
		
		SettingsPage settingPage = new SettingsPage(driver);
		
		ScreenshotMaker.makeScreenShot(driver);
		
		settingPage.clickForwardingTab();
		settingPage = new SettingsPage(driver);
		
		settingPage.turnOnForwarding(userForWhomForward);
		ScreenshotMaker.makeScreenShot(driver);
		
		settingPage.clickButtonSaveChanges();
	}
	
	public void setupFilter(String userFrom){
		
		SettingsPage settingPage = new SettingsPage(driver);
		
		settingPage.clickFilterTab();

		settingPage.clickLinkCreateNewFilter();
		
		FilterPage filterPage = new FilterPage(driver);
		
		filterPage.fillFieldFrom(userFrom);
	
		filterPage.markCheckBoxHasAttachment();
		
		filterPage.clickLinkCreateFilter();
	
		//open new page, because page was changed
		filterPage = new FilterPage(driver);
		
		filterPage.markCheckBoxDeleteIt();
		
		filterPage.markCheckBoxAlwaysMarkAsImportant();
		
		filterPage.clickButtonCreateFilter();
		
	}
	
}
