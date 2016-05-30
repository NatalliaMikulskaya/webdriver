package by.epam.atl.google.loggin;

import java.lang.invoke.MethodHandles;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class WebListener extends AbstractWebDriverEventListener{
	
	public static Logger log = LogManager.getLogger(MethodHandles.lookup().lookupClass());
	
	@Override
	public void afterChangeValueOf(WebElement arg0, WebDriver arg1) {
		 
		log.info(System.lineSeparator() +
				" **** " + arg1.getTitle() + System.lineSeparator() +
				" ---- Value of the:" + arg0.toString() + " after changes" );
		
	}

	@Override
	public void afterClickOn(WebElement arg0, WebDriver arg1) {
		log.info(System.lineSeparator() +
				" **** " + arg1.getTitle() + System.lineSeparator() + 
				" ---- Clicked on: " + arg0.toString());
		
	}

	@Override
	public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		log.info(System.lineSeparator() + 
				" **** " + arg2.getTitle() + System.lineSeparator() + 
				" ---- Found Element By : " + arg0.toString());
		
	}

	@Override
	public void afterScript(String arg0, WebDriver arg1) {
		log.info(System.lineSeparator() + 
				" **** " + arg1.getTitle() + System.lineSeparator() + 
				" ---- Script executed : " + arg0);
		
	}

	@Override
	public void beforeChangeValueOf(WebElement arg0, WebDriver arg1) {
		log.info(System.lineSeparator() + 
				" **** " + arg1.getTitle() +  System.lineSeparator() + 
				" ---- Before changing value : " + arg0.toString());
		
	}

	@Override
	public void beforeClickOn(WebElement arg0, WebDriver arg1) {
		log.info(System.lineSeparator() + 
				" **** " + arg1.getTitle() +  System.lineSeparator() + 
				" ---- before click on " + arg0.toString());
		
	}

	@Override
	public void beforeFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		log.info(System.lineSeparator() + 
				" **** " + arg2.getTitle() +  System.lineSeparator() + 
				" ---- before find by " + arg0.toString());
		
	}

	@Override
	public void beforeNavigateBack(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeNavigateForward(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeNavigateRefresh(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeNavigateTo(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeScript(String arg0, WebDriver arg1) {
		log.info(System.lineSeparator() + 
				" **** " + arg1.getTitle() +  System.lineSeparator() + 
				" ---- before script " + arg0.toString());
		
	}

	@Override
	public void onException(Throwable arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}
  
}
