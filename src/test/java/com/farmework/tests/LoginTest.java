
package com.farmework.tests;

import java.time.Duration;

import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.framework.utils.PropertyFileRead;
import com.framework.utils.Utilities;
import com.frameworkpractice.pages.LoginPage;
import com.framework.annotations.FrameWorkPracticeReportAnnotation;
//import com.framework.annotations.FrameWorkPracticeReportAnnotation;
import com.framework.annotations.TestDataSheet;
import com.framework.base.Base;
import com.framework.enums.CategoryType;
import com.framework.enums.Modules;
//import com.framework.enums.CategoryType;
//import com.framework.enums.Modules;
import com.framework.enums.TestCaseSheet;
import com.framework.utils.DataProviderUtils;

import org.testng.annotations.Listeners;

@Listeners(com.framework.listeners.Listeners.class)
public class LoginTest extends Base {

	LoginPage loginPage = new LoginPage();
	private boolean loginSuccessful = false; // Flag to track login success

	private static final Logger log = Logger.getLogger(LoginTest.class.getName());
	
	@BeforeMethod (alwaysRun = true)
	private void login()   {

		loginPage.clickOnMyAccountMenu();
		loginPage.clickOnLoginOption();
	}

	@AfterMethod (alwaysRun = true)
	private void logout() {
		// Place logout logic here
		if (loginSuccessful) {
			loginPage.scrollToLogoutButton();
			Utilities.ThreadSleepTime(3000);
			loginPage.clickOnLogoutButton();
		}
	}

	//@FrameWorkPracticeReportAnnotation(module=Modules.LoginPage, category=CategoryType.SMOKE)
	@TestDataSheet(sheetName = "LoginData")
	@Test( dataProvider = "validCredentialSupplier", dataProviderClass = DataProviderUtils.class, alwaysRun = true)
	public void verify_Login_With_Valid_Cred1(Map<String, String> testData) {
		String emailAddress = testData.get("UserName");
		String password = testData.get("Password");
		String expectedMessage = testData.get("ExpectedMessage");
		// loginPage.clickOnMyAccountMenu();
		// loginPage.clickOnLoginOption();
		loginPage.enterValidEmailAddress(emailAddress);
		loginPage.enterValidPassword(password);
		loginPage.clickOnLoginButton();
		loginSuccessful = true; // Set login successful flag
		//Utilities.ThreadSleepTime(3000);
		String actualMessage = loginPage.actualMessaegUponSuccessfulLogin();
		Assert.assertEquals(actualMessage, expectedMessage, "Message Displayed is Incorrect");

		//Utilities.ThreadSleepTime(2000);
		// loginPage.scrollToLogoutButton();
		// Utilities.ThreadSleepTime(3000);
		// loginPage.clickOnLogoutButton();
	}

	//@FrameWorkPracticeReportAnnotation(module=Modules.LoginPage, category=CategoryType.SMOKE)
	@TestDataSheet(sheetName = "LoginData")
	@Test( dataProvider = "validCredentialSupplier", dataProviderClass = DataProviderUtils.class, alwaysRun = true)
	public void verify_Login_With_Valid_Cred2(Map<String, String> testData) {
		String emailAddress = testData.get("UserName");
		String password = testData.get("Password");
		String expectedMessage = testData.get("ExpectedMessage");
		// loginPage.clickOnMyAccountMenu();
		// loginPage.clickOnLoginOption();
		loginPage.enterValidEmailAddress(emailAddress);
		loginPage.enterValidPassword(password);
		loginPage.clickOnLoginButton();
		loginSuccessful = true; // Set login successful flag
		Utilities.ThreadSleepTime(3000);
		String actualMessage = loginPage.actualMessaegUponSuccessfulLogin();
		Assert.assertEquals(actualMessage, expectedMessage, "Message Displayed is Incorrect");
		Utilities.ThreadSleepTime(2000);
		loginSuccessful = true; // Set login successful flag
		// loginPage.scrollToLogoutButton();
		// Utilities.ThreadSleepTime(3000);
		// loginPage.clickOnLogoutButton();
	}

	//@FrameWorkPracticeReportAnnotation(module=Modules.LoginPage, category=CategoryType.SMOKE)
	@TestDataSheet(sheetName = "LoginData")
	@Test( dataProvider = "validCredentialSupplier", dataProviderClass = DataProviderUtils.class, alwaysRun = true)
	public void verify_Login_With_InValid_Cred(Map<String, String> testData) {
		String emailAddress = testData.get("UserName");
		String password = testData.get("Password");
		String expectedMessage = testData.get("ExpectedMessage");
		// loginPage.clickOnMyAccountMenu();
		// loginPage.clickOnLoginOption();
		loginPage.enterValidEmailAddress(emailAddress);
		loginPage.enterValidPassword(password);
		loginPage.clickOnLoginButton();
		loginSuccessful = false; // Set login unsuccessful flag
		String actualError = loginPage.actualErrorForInvalidLogin();
		// String actualError =
		// Utilities.getDriver().findElement(By.xpath("//div[@class='alert alert-danger
		// alert-dismissible']")).getText();
		Assert.assertEquals(actualError, expectedMessage, "Invalid Message");
		Utilities.ThreadSleepTime(2000);

	}

	// Test Case fetching data from config.properties file

	//@FrameWorkPracticeReportAnnotation(module=Modules.LoginPage, category=CategoryType.SMOKE)
	@Test
	public void verify_Login_With_Valid_Cred_Using_Properties_File() {

		loginPage.clickOnMyAccountMenu();
		loginPage.clickOnLoginOption();
		loginPage.enterValidEmailAddress(PropertyFileRead.readPropertiesFiles("EmailAddress"));
		loginPage.enterValidPassword(PropertyFileRead.readPropertiesFiles("Password"));
		loginPage.clickOnLoginButton();
		loginSuccessful = true; // Set login successful flag

		Assert.assertTrue(
				Utilities.getDriver().findElement(By.linkText("Edit your account information")).isDisplayed());
		Utilities.ThreadSleepTime(3000);
		loginPage.scrollToLogoutButton();
		Utilities.ThreadSleepTime(3000);
		loginPage.clickOnLogoutButton();

	}

	//@FrameWorkPracticeReportAnnotation(module=Modules.LoginPage, category=CategoryType.SMOKE)
	@TestDataSheet(sheetName = "LoginData")
	@Test( dataProvider = "validCredentialSupplier", dataProviderClass = DataProviderUtils.class, alwaysRun = true)
	public void verify_Login_Without_Enterig_Credentials(Map<String, String> testData) {
		String expectedMessage = testData.get("ExpectedMessage");
		loginPage.clickOnMyAccountMenu();
		loginPage.clickOnLoginOption();
		loginPage.clickOnLoginButton();
		loginSuccessful = false; // Set login unsuccessful flag
		Utilities.ThreadSleepTime(3000);
		String actualMessage = loginPage.actualErrorMessageLoginWithoutCred();
		// String actualMessage =
		// Utilities.getDriver().findElement(By.xpath("//div[contains(@class,'alert
		// alert-danger alert-dismissible')]")).getText();
		// Hard coding for actualMessage removed by
		// loginPage.actualErrorMessageLoginWithoutCred();
		Assert.assertTrue(actualMessage.equals(expectedMessage), "Expected Warning Message is NOT Displayed");
		Utilities.ThreadSleepTime(2000);

	}

}
