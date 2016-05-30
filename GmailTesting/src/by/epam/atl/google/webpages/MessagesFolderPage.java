package by.epam.atl.google.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class MessagesFolderPage extends Page{

		
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
	
	public final boolean isLetterReceivedAndInInbox(WebDriver driver, String fromUser, String messageTopic){

		WebElement foundedMessage = lookForMessageFromSenderWithTopic(driver, fromUser, messageTopic);
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
	
}
