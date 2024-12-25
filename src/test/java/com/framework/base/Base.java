package com.framework.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.framework.utils.PropertyFileRead;
import com.framework.utils.Utilities;

public class Base {
	
	@BeforeSuite
	public void setUp() {
		 
		// driver = initilizeBrowserOpenURL(prop.getProperty("browserName"));
		Utilities.getDriver();
		Utilities.getDriver().get(PropertyFileRead.readPropertiesFiles("url"));
		Utilities.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		Utilities.getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGELOAD_WAIT_TIME));
		
		Utilities.getDriver().manage().window().maximize();
		Utilities.implicitWait(20000);
	}

	@AfterSuite
	public void quitBrowser() {

		Utilities.getDriver().quit();	
		
		}
}
