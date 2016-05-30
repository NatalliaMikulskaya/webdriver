package by.epam.atl.google.webpages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class Page {

		
	public boolean isHomePage(){
		return false;
	}
	
	public boolean isPageForLoginEnter(){
		return false;
	}
	
	public boolean isPageForPasswordEnter(){
		return false;
	} 
	
	public boolean isPageForChangeAccount(){
		return false;
	}
	
	public boolean isEmailInboxPage(){
		return false;
	}
	
	public boolean isSpamBoxPage(){
		return false;
	}
	
	public boolean isNewMessagePage(){
		return false;
	}
	
	public boolean isAccountInformationPage(){
		return false;
	}
	
	public boolean isGoogleAccountPage(){
		return false;
	}
	
	public void highlight(WebDriver driver,WebElement element) throws InterruptedException{
		  //Creating JavaScriptExecuter Interface
		   JavascriptExecutor js = (JavascriptExecutor)driver;
		   for (int iCnt = 0; iCnt < 3; iCnt++) {
		      //Execute javascript
		         js.executeScript("arguments[0].style.border='4px groove green'", element);
		         Thread.sleep(1000);
		         js.executeScript("arguments[0].style.border=''", element);
		   }
		 }
}
