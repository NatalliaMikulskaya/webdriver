package by.epam.atl.google.webpages;

import org.openqa.selenium.WebDriver;

public class SpamFolderPage extends MessagesFolderPage {
	
	
	//@FindBy (xpath = "")
	//private WEbElement
	
	
	public SpamFolderPage(WebDriver currentDriver){
		
		super(currentDriver);
		
	}
	
	@Override
	public boolean isSpamBoxPage(){
		return true;
	}

}
