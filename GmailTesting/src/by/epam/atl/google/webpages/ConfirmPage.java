package by.epam.atl.google.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConfirmPage extends Page {

	private WebDriver driver;
	
	@FindBy (xpath = "//input[@type='submit']")
	private WebElement buttonProceed;
	
	public ConfirmPage(WebDriver currentDriver){
		
		super(currentDriver);
		driver = currentDriver;
		
	}
	
	public boolean isPageOpen(){
		
		
		return true;
	}
	
	public void clickButtonProceed(){
		
		highlight(driver, buttonProceed);
		buttonProceed.click();
		
	}
	
}
