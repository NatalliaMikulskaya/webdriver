package by.epam.atl.google.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleAccountPage extends Page {
	
	private WebDriver driver;
	
	@FindBy (xpath = "//a[contains(@href,'https://mail.google.com/mail')]")
	private WebElement gmailLink;
	
	public GoogleAccountPage(WebDriver currentDriver){
		
		super(currentDriver);
		driver = currentDriver;
	}

	public void clickGmailLink() {
		
		highlight(driver, gmailLink);
		gmailLink.click();
	}
	
}
