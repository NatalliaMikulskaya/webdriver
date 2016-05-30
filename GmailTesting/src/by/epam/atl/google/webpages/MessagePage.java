package by.epam.atl.google.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class MessagePage extends Page {

	private WebDriver driver;
	
	@FindBy (xpath ="") 
			//"//div[@class='T-I J-J5-Ji nN T-I-ax7 T-I-Js-Gs T-I-Js-IF ar7']")
	//"//body/div[7]/div[3]/div/div[2]/div/div[2]/div/div/div/div/div[3]/div/div/div[2]/div[2]")
			//"//div[@act='9']")
			//+ "////div[@aria-label='Report spam']")
	private WebElement buttonSpam;
	
	
	public MessagePage(WebDriver currentDriver){
		
		driver = currentDriver;
		
		PageFactory.initElements(driver, this);
	}
	
	public void clickButtonSpam(){
		
		//System.out.println("Spam button displayed: "+buttonSpam.isDisplayed());
		//System.out.println("Spam button enabled: "+buttonSpam.isEnabled());
		//System.out.println(": "+buttonSpam.getAttribute("aria-label").toString());
		//Actions ac = new Actions(driver);
		//ac.moveToElement(buttonSpam);
		//ac.click(buttonSpam);
		//((JavascriptExecutor) driver).executeScript("arguments[0].click()",buttonSpam);
		
		(new WebDriverWait(driver, 30)).
			until(ExpectedConditions.
			presenceOfElementLocated(By.xpath("//div[@class='T-I J-J5-Ji nN T-I-ax7 T-I-Js-Gs T-I-Js-IF ar7']"))).
			click();
		
		buttonSpam.click();
		
	}
}
