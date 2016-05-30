package by.epam.atl.google.tests;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.firefox.MarionetteDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.log4j.Logger;

import by.epam.atl.google.action.Action;
import by.epam.atl.google.action.Step;
import by.epam.atl.google.exception.PageException;
import by.epam.atl.google.loggin.WebListener;


public class LettersFromUser1ToUser2MarkedAsSpam {
	
	private final Logger LOG = LogManager.getRootLogger();

	private final String LOGIN_1 = "by.atl.user.1@gmail.com";
	private final String PASSWORD_1 = "RedUser1";
	private final String LOGIN_2 = "by.atl.user.2@gmail.com";
	private final String PASSWORD_2 = "RedUser2";
	
	private final String topic1 = "letter for task 1";
	private final String text1 = "This is a message for test1. It is raining.";
	private final String topic2 = "letter should appears in spam";
	private final String text2 = "This is a message for spam. It is sun.";
	
	private WebDriver driver;
	private WebDriverWait wait;
	private EventFiringWebDriver e_driver;
	private WebListener eventListener;
	private Step step;

	@BeforeClass
	public void setup(){
		driver = new FirefoxDriver();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	
		
		//wait = new WebDriverWait(driver, 15);
		// Initializing EventFiringWebDriver using Firefox WebDriver instance
		e_driver = new EventFiringWebDriver(driver);

		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebListener();
		
		e_driver.register(eventListener);
		
		step = Step.getInstance(e_driver);
	}
	
	@Test
	public void testLoginGmail() throws InterruptedException{
		
		step.sendLetterFromUser1ToUser2(LOGIN_1, PASSWORD_1, LOGIN_2, topic1, text1);
	
		step.forUser2MarkLetterFromUser1AsSpam(LOGIN_2, PASSWORD_2, LOGIN_1, topic1);
		
		step.sendLetterFromUser1ToUser2(LOGIN_1, PASSWORD_1, LOGIN_2, topic2, text2);
		
		step.goToInboxForUser(LOGIN_1, PASSWORD_1);
		
		step.logOutUser();
		
		step.goToSpamFolderforUser(LOGIN_2, PASSWORD_2);
		
		boolean isMessage2FromUser1InSpam = step.isMessageFromSenderWithTopicInSpamFolder(LOGIN_1, topic2);
		
		Assert.assertTrue(isMessage2FromUser1InSpam);
				
	}
	
	@AfterClass
	public void close(){
		e_driver.close();
		driver.close();
		step = null;
	}
}
