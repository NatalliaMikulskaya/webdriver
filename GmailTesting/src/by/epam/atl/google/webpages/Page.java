package by.epam.atl.google.webpages;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page {

	public Page(WebDriver currentDriver){
		
		PageFactory.initElements(currentDriver, this);
	}	
	
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
	
	public boolean isTrashBoxPage(){
		return false;
	}

	
	public void highlight(WebDriver driver,WebElement element){
		//Creating JavaScriptExecuter Interface
		JavascriptExecutor js = (JavascriptExecutor)driver;
		for (int iCnt = 0; iCnt < 3; iCnt++) {
			//Execute javascript
			js.executeScript("arguments[0].style.backgroundColor='red'", element);
			// wait.withTimeout(3, TimeUnit.SECONDS);
			//js.executeScript("arguments[0].style.backgroundColor='initial'", element);
		}
	}
	
	
}
