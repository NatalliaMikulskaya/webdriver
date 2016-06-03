package by.epam.atl.google.loggin;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import by.epam.atl.google.tests.GMailTests;

public class TestListener implements ITestListener {
	
	private WebDriver driver;

	public static final Logger LOG = LogManager.getLogger(MethodHandles.lookup().lookupClass());
	
	@Override
	public void onTestStart(ITestResult result) {
		LOG.info("--------------------- Started test  " +  getTestMethodName(result));

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		LOG.info("--------------------- Test  " +  getTestMethodName(result) + " success");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		LOG.info("--------------------- Test  " +  getTestMethodName(result) + " failure. See screenshot.");

		Object currentClass = result.getInstance();
        driver = ((GMailTests) currentClass).getDriver();

		takescreen(result);

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}
	
	private static String getTestMethodName(ITestResult result) {
		
		return result.getMethod().getConstructorOrMethod().getName();

	}

	private void takescreen(ITestResult result) {

		String datTime = new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime());
		String failureImageFileName = getTestMethodName(result) + datTime ;

		ScreenshotMaker.makeScreenShot(driver, failureImageFileName);

		
	}

}
