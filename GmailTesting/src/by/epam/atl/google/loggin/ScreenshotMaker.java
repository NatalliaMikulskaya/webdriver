package by.epam.atl.google.loggin;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotMaker {

	public static void makeScreenShot(WebDriver driver){
		
		try{
			File screenshotFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshotFile,new File("..\\Screenshots\\Webdriver\\" + GetTimeStampValue() + ".png"));
		}
		catch(IOException e){
			System.out.println("Can't make screenshot");
		}
		
	}
	
	public static void makeScreenShot(WebDriver driver, String fileName){
		
		try{
			File screenshotFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshotFile,new File("..\\Screenshots\\TestNG\\" + fileName + ".png"));
		}
		catch(IOException e){
			System.out.println("Can't make screenshot");
		}
		
	}
	
	private static String GetTimeStampValue()throws IOException{
	   
		Calendar cal = Calendar.getInstance();       
	    Date time=cal.getTime();
	    String timestamp=time.toString();

	    String systime=timestamp.replace(":", "-");

	    return systime;
	}
}
