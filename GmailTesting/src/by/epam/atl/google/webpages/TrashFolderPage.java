package by.epam.atl.google.webpages;

import org.openqa.selenium.WebDriver;

public class TrashFolderPage extends MessagesFolderPage {
	
	
	//@FindBy (xpath = "")
	//private WEbElement
	
	
	public TrashFolderPage(WebDriver currentDriver){
		
		super(currentDriver);
		
	}
	
	@Override
	public boolean isTrashBoxPage(){
		return true;
	}

}
