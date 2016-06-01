package by.epam.atl.google.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class FilterPage extends Page {
	
	private WebDriver driver;

	@FindBy (xpath = "//body/div[27]/div/div[2]/div[2]/span[2]/input")
	private WebElement fieldFrom;
	
	@FindBy (xpath = "//body/div[27]/div/div[2]/div[7]/span[1]/input")
	private WebElement checkBoxHasAttachment;
	
	@FindBy (xpath = "//body/div[27]/div/div[2]/div[9]/div[2]")
	private WebElement linkCreateFilterWithThisSearch;
	
	@FindBy (xpath = "//body/div[21]/div/div[2]/div[4]/div/div[6]/input")
	private WebElement checkBoxDeleteIt;
	
	@FindBy (xpath = "//body/div[21]/div/div[2]/div[4]/div/div[8]/input")
	private WebElement checkBoxAlwaysMarkAsImportant;
	
	@FindBy (xpath = "//body/div[21]/div/div[2]/div[5]/div")
	private WebElement buttonCreateFilter;
	
		
	public FilterPage(WebDriver currentDriver){
		
		super(currentDriver);
		driver = currentDriver;
		
	}
	
	//----- Methods
	
	public void fillFieldFrom(String fromUser){
		
		highlight(driver, fieldFrom);
		
		fieldFrom.sendKeys(fromUser);
	}
	
	public void markCheckBoxHasAttachment(){
	
		highlight(driver, checkBoxHasAttachment);
		checkBoxHasAttachment.click();
	}
	
	public void clickLinkCreateFilter(){
		
		linkCreateFilterWithThisSearch.click();
		
	}
	
	public void markCheckBoxDeleteIt(){
		
		highlight(driver, checkBoxDeleteIt);
		checkBoxDeleteIt.click();
	}
	
	public void markCheckBoxAlwaysMarkAsImportant(){
		
		highlight(driver, checkBoxAlwaysMarkAsImportant);
		checkBoxAlwaysMarkAsImportant.click();
	}
	
	public void clickButtonCreateFilter(){
		
		highlight(driver, buttonCreateFilter);
		buttonCreateFilter.click();
	}
	
}
