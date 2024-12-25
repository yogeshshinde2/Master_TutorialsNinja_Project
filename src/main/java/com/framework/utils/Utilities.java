package com.framework.utils;



import static com.framework.reports.FrameworkLogger.log;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.framework.enums.WaitLogic;
import com.framework.factories.ExplicitWaitFactory;
import com.framework.enums.LogType;

public class Utilities {
    
	public static final int IMPLICIT_WAIT_TIME = 10;
	public static final int PAGELOAD_WAIT_TIME = 5;
	

	// private static WebDriver driver = new FirefoxDriver();
	public static WebDriver driver = new ChromeDriver();
	// private static WebDriver driver = new EdgeDriver();

	public static WebDriver getDriver() {
		
		return driver;
	}
	
	public static String getscreenshotpath(String testcasename) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot) getDriver();
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile=System.getProperty("user.dir")+"\\reports\\"+testcasename+".png";
		FileUtils.copyFile(source,new File(destinationFile));
		return destinationFile;
	}
	
	public static void scrollToElement(By by, String elementname) {
	
		WebElement element = getDriver().findElement(by);
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		
	}
	

	public static void click(By by, WaitLogic waitstrategy, String elementname) {
		WebElement element = ExplicitWaitFactory.performExplicitWait(waitstrategy, by);
		element.click();
		//log(LogType.EXTENTANDCONSOLE, elementname + " is clicked");
		
	}
	
	//public static void click(By by) {
		//getDriver().findElement(by).click();
		
	//}

	public static void submit(By by) {
		getDriver().findElement(by).submit();
	}

	public static void sendKeys(By by, WaitLogic waitstrategy, String elementname, String value) {
		WebElement element = ExplicitWaitFactory.performExplicitWait(waitstrategy, by);
		element.sendKeys(value);
		//getDriver().findElement(by).sendKeys(value);
	}
	
	public static void ThreadSleepTime(int milliSeconds) {
		try {
			Thread.sleep(milliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isElementDisplayed(By by) {
		return getDriver().findElement(by).isDisplayed();
		
	}
	
	public static String actualMessageDisplayed(By by) {
		
		return getDriver().findElement(by).getText();
	}
	
	@SuppressWarnings("deprecation")
	public static void implicitWait(int milliSeconds) {
		getDriver().manage().timeouts().implicitlyWait(milliSeconds, TimeUnit.SECONDS);
	}

	public static String generateEmailWithTimeStamp() {

		Date date = new Date();

		String timeStamp = date.toString().replace(" ", "_").replace(":", "_");

		return "yogeshs" + timeStamp + "@gmail.com";

	}

	

}
