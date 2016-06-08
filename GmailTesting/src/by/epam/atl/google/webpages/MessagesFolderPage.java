package by.epam.atl.google.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public abstract class MessagesFolderPage extends Page{

	@FindBy (xpath = "//div[@role='navigation']/div/div[2]/span/span[2]/div")
	private WebElement linkMoreAtFolderBar;
	
	@FindBy (xpath = "//a[contains(@title,'Spam')]")
	private WebElement linkSpamFolder;
	
	@FindBy (xpath = "//a[contains(@title,'Bin')]")
	private WebElement linkTrashFolder;
	
	@FindBy(xpath = "//div[@role = 'button'][contains(text(),'COMPOSE')]")
	private WebElement buttonNewLetter;
		
	@FindBy (xpath = "//a[contains(@href,'SignOutOptions')]")
	private WebElement linkSignOut;
	
	@FindBy (xpath = "//body/div[7]/div[3]/div/div[2]/div/div[2]/div/div/div/div/div/div/div[2]/div[2]/div/div[2]") 
	private WebElement settingsButton;
	
	@FindBy (xpath = "//div[@aria-haspopup='true'][@role='menu']/div/div[8]")
	//(xpath = "//div[@aria-haspopup='true'][@role='menu']/div/div[8]/div")
	private WebElement linkSettingsInPopupMenu;
	
	@FindBy (xpath = "//div[@aria-haspopup='true'][@role='menu']/div/div[8]/div")
	//(xpath = "//div[@aria-haspopup='true'][@role='menu']/div/div[8]/div")
	private WebElement linkSettingsInPopupMenu1;
	
	@FindBy (xpath = "//div/div[7]/div[@aria-haspopup='true'][@role='button']/div") 
	private WebElement buttonMore;
	
	@FindBy (xpath = "//div[19][@aria-haspopup='true'][@role='menu']/div/div[5]")
	private WebElement linkMarkAsNotImportant;
	
	public 	MessagesFolderPage(WebDriver currentDriver){
		super(currentDriver);
	}
	
	public final boolean isExistsMessageFromSender(WebDriver driver, String messageSenderName){
		
		WebElement messageSender = driver.findElement(By.xpath("//span[@email="+messageSenderName+"]"));
		
		if (messageSender != null) {
			return true;
		}
		return false;
	}

	public final boolean isExistsMessageWithTopic(WebDriver driver, String messageTopic){
		
		WebElement topic = driver.findElement(By.xpath("//span/b[.='"+messageTopic+"']"));

		if (topic != null) {
			return true;
		}
		
		return false;
	}
	
	public final boolean isLetterReceivedAndInFolder(WebDriver driver, String fromUser, String messageTopic){

		WebElement foundedMessage = lookForMessageFromSenderWithTopic(driver, fromUser, messageTopic);
		if (foundedMessage != null){

			return true;
		}
		
		return false;
	}
	
	public final boolean isLetterReceivedWithPartialTopicAndInFolder(WebDriver driver, String fromUser, String messageTopic){

		WebElement foundedMessage = lookForMessageFromSenderWithPartialTopic(driver, fromUser, messageTopic);
		if (foundedMessage != null){

			return true;
		}
		
		return false;
	}
	
	
	public final WebElement lookForMessageFromSenderWithTopic(WebDriver driver, String messageSenderName, String messageTopic){
		
		String strForSearchingMessage = "//tr[td[6]/div/div/div/span[1][.='"+messageTopic+
				"'] and  td[4]/div[2]/span[contains(@email,'"+messageSenderName+"')]]";
		
		WebElement message = driver.findElement(By.xpath(strForSearchingMessage));
		
		if (message != null) {
			return message;
		}
		
		return null;
	}
	
	public final WebElement lookForMessageWithAttachmentFromSenderWithTopic(WebDriver driver, String messageSenderName, String messageTopic){
		
		String strForSearchingMessage = "//tr[td[6]/div/div/div/span[1][.='"+messageTopic+
				"'] and  td[4]/div[2]/span[contains(@email,'"+messageSenderName+"')]"
				+ " and td[7]/img[@alt='Attachment']]";
		
		WebElement message = driver.findElement(By.xpath(strForSearchingMessage));
		
		if (message != null) {
			return message;
		}
		
		return null;
	}
	
	public final WebElement lookForMessageFromSenderWithPartialTopic(WebDriver driver, String messageSenderName, String messageTopic){
		
		String strForSearchingMessage = "//tr[td[6]/div/div/div/span[1]/b[contains(text(),'"+messageTopic+
				"')] and  td[4]/div[2]/span[contains(@email,'"+messageSenderName+"')]]";
		
		WebElement message = driver.findElement(By.xpath(strForSearchingMessage));
		
		if (message != null) {
			return message;
		}
		
		return null;
	}
	
	public final boolean isLetterWithAttachmentMarkAsImportandAndInFolder(WebDriver driver, String fromUser, String messageTopic){
		
		WebElement foundedMessage = lookForMessageWithAttachmentFromSenderWithTopic(driver, fromUser, messageTopic);
		
		highlight(driver, foundedMessage);
		
		if (foundedMessage != null){
			if (isImportant(driver, foundedMessage)){
				return true;
			}
			return false;
		}
		
		return false;
	}
	
	public void clickmenuSettingsInPopup(WebDriver driver){
		
		Actions act = new Actions(driver);
		act.moveToElement(settingsButton).clickAndHold().moveToElement(linkSettingsInPopupMenu).release().build().perform();
		
		
		
		/*highlight(driver, linkSettingsInPopupMenu);
		
		Actions act = new Actions(driver);
		act.moveToElement(linkSettingsInPopupMenu).click(linkSettingsInPopupMenu1).build().perform();*/
		
	}
	
	public void openMessage(WebElement message){
		
		message.click();
		
	}
	
	public void markMessage(WebDriver driver, String fromUser, String messageTopic){
		
		if (isLetterReceivedAndInFolder(driver, fromUser, messageTopic)){

			//look for message
			WebElement foundedMessage = lookForMessageFromSenderWithTopic(driver, fromUser, messageTopic);
			
			markMessage(driver, foundedMessage);
			
		}
	}
	
	public void markMessage(WebDriver driver, WebElement message){
		
		//look for checkbox in founded message 
		//WebElement markForMessage = message.findElement(By.xpath("//td[2]/div[@role='checkbox']/div"));
		//WebElement markForMessage = message.findElement(By.xpath("//td[2]/div/div"));
		WebElement markForMessage = message.findElement(By.xpath("//div[@role='checkbox']/div"));
		
		if (markForMessage != null){
			unhighlight(driver, message);
			
			System.out.println(markForMessage.toString());
			
			if (markForMessage.isDisplayed()){
				highlight(driver, markForMessage);
			
				markForMessage.click();
			}else{
				System.out.println("Oooops");
			}

		}
		
	}
	
	public void clickLinkMoreAtFolderBar(){
		
		linkMoreAtFolderBar.click();
	}
	
	public void clickLinkSpamFolder() {
		
		linkSpamFolder.click();
	}
	
	public void clickLinkTrashFolder() {
		
		linkTrashFolder.click();
	}
	
	public void clickButtonForCreatingNewMessage(){
		buttonNewLetter.click();
	}
	
	public void clickLinkAccountInfo(){
		
		linkSignOut.click();
	
	}
	
	private boolean isImportant(WebDriver driver, WebElement message){
		
		markMessage(driver, message);
		
		if (isMarkAsUnimportantExist(driver)){
			
			return true;
		}
		
		return false;
	}
	
	private boolean isMarkAsUnimportantExist(WebDriver driver){
		
		highlight(driver, buttonMore);
		
		buttonMore.click();
		
		if (linkMarkAsNotImportant.isSelected()){
			
			highlight(driver, linkMarkAsNotImportant);
			return true;
		}
		
		
		return false;
	}
}
