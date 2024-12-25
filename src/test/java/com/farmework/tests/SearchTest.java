package com.farmework.tests;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//import com.framework.annotations.FrameWorkPracticeReportAnnotation;
import com.framework.annotations.TestDataSheet;
import com.framework.base.Base;
//import com.framework.enums.CategoryType;
//import com.framework.enums.Modules;
import com.framework.utils.DataProviderUtils;
import com.framework.utils.PropertyFileRead;
import com.framework.utils.Utilities;
import com.frameworkpractice.pages.LoginPage;
import com.frameworkpractice.pages.SearchPage;

import org.testng.annotations.Listeners;

@Listeners(com.framework.listeners.Listeners.class)
public class SearchTest extends Base {

	SearchPage searchPage = new SearchPage();
	LoginPage loginPage = new LoginPage();
	
	@BeforeMethod
	private void login() {
		loginPage.clickOnMyAccountMenu();
		loginPage.clickOnLoginOption();
	}
	
	@AfterMethod
	public void logout() {
		searchPage.scrollTomyAccountMenu();
		Utilities.ThreadSleepTime(5000);
		loginPage.clickOnMyAccountMenu();
		Utilities.ThreadSleepTime(5000);
		searchPage.clickOnLogoutOption();
		Utilities.ThreadSleepTime(5000);
	}
	
	

	//@FrameWorkPracticeReportAnnotation(module=Modules.SearchPage, category=CategoryType.SMOKE)
	@TestDataSheet(sheetName = "SearchData")
	@Test( dataProvider = "validCredentialSupplier", dataProviderClass = DataProviderUtils.class)
	public void verifySearchWithValidProductName(Map<String, String> testData) {
		String emailAddress = testData.get("UserName");
		String password = testData.get("Password");
		String searchValue = testData.get("SearchValue");
		//loginPage.clickOnMyAccountMenu();
		//loginPage.clickOnLoginOption();
		loginPage.enterValidEmailAddress(emailAddress);
		loginPage.enterValidPassword(password);
		loginPage.clickOnLoginButton();
		searchPage.enterSearchText(searchValue);
		searchPage.clickOnSearchButton();
		searchPage.scrollToProductNameHP();
		Assert.assertTrue(searchPage.displayStatusOfProductHP(), "Product is NOT displayed");
		// Assert.assertTrue(Utilities.getDriver().findElement(By.linkText("HP
		// LP3065")).isDisplayed(),"Product is NOT displayed");
		// In above line hard coding is removed by using
		// searchPage.displayStatusOfProductHP()
		Utilities.ThreadSleepTime(2000);
		//***Below Code is placed in After Method***//
		//searchPage.scrollTomyAccountMenu();
		//Utilities.ThreadSleepTime(5000);
		//loginPage.clickOnMyAccountMenu();
		//Utilities.ThreadSleepTime(5000);
		//searchPage.clickOnLogoutOption();
		//Utilities.ThreadSleepTime(5000);

		/*
		 * Normal code w/o using Page Object Model
		 * driver.findElement(By.xpath("//input[@name='search']")).sendKeys("HP");
		 * driver.findElement(By.xpath("//div[@id='search']//button")).click();
		 * 
		 * driver.findElement(By.linkText("HP LP3065")).getText();
		 * Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed(),
		 * "Product is NOT displayed");
		 */
	}

	//@FrameWorkPracticeReportAnnotation(module=Modules.SearchPage, category=CategoryType.SMOKE)
	@TestDataSheet(sheetName = "SearchData")
	@Test( dataProvider = "validCredentialSupplier", dataProviderClass = DataProviderUtils.class)
	public void verifySearchWithInValidProductName(Map<String, String> testData) {
		String emailAddress = testData.get("UserName");
		String password = testData.get("Password");
		String searchValue = testData.get("SearchValue");
		String expectedMessage = testData.get("ExpectedMessage");
		//loginPage.clickOnMyAccountMenu();
		//loginPage.clickOnLoginOption();
		loginPage.enterValidEmailAddress(emailAddress);
		loginPage.enterValidPassword(password);
		loginPage.clickOnLoginButton();
		searchPage.enterSearchText(searchValue);
		searchPage.clickOnSearchButton();
		Utilities.ThreadSleepTime(5000);

		String actualMessage = searchPage.actualMessageForInvalidProductSearch();
		// String actualMessage = Utilities.getDriver().findElement(By.xpath("//div[@id='content']/p[2]")).getText();
		Assert.assertEquals(actualMessage, expectedMessage, "Message Displayed is Incorrect");
		Utilities.ThreadSleepTime(2000);
		//***Below Code is placed in After Method***//
		//searchPage.scrollTomyAccountMenu();
		//loginPage.clickOnMyAccountMenu();
		//Utilities.ThreadSleepTime(5000);
		//searchPage.clickOnLogoutOption();
		//Utilities.ThreadSleepTime(5000);

		/*
		 * Normal code w/o using Page Object Model
		 * driver.findElement(By.xpath("//input[@name='search']")).sendKeys("Honda");
		 * driver.findElement(By.xpath("//div[@id='search']//button")).click(); String
		 * message =
		 * driver.findElement(By.xpath("//div[@id='content']/p[2]")).getText();
		 * Assert.assertEquals(message,
		 * "There is no product that matches the search criteria.",
		 * "Message Displayed is Incorrect");
		 */
	}

}
