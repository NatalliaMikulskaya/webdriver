package by.epam.atl.google.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SpamFolderPage extends MessagesFolderPage {
	
	private WebDriver driver;
	
	//@FindBy (xpath = "")
	//private WEbElement
	
	
	public SpamFolderPage(WebDriver currentDriver){
		driver = currentDriver;
		
		PageFactory.initElements(driver, this);
	}
	
	@Override
	public boolean isSpamBoxPage(){
		return true;
	}

}
