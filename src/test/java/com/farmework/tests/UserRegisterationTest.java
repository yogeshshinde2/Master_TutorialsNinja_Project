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
import com.frameworkpractice.pages.UserRegisterationPage;

import org.testng.annotations.Listeners;

@Listeners(com.framework.listeners.Listeners.class)
public class UserRegisterationTest extends Base {
	
	LoginPage loginPage = new LoginPage();
	UserRegisterationPage registerationPage=new UserRegisterationPage();
	
	@BeforeMethod
	private void navigateTORegisterPage() {
		registerationPage.clickOnMyAccountMenu();
		registerationPage.clickOnRegisterOption();
		
	}
	
	@AfterMethod
	private void logout() {
		Utilities.ThreadSleepTime(2000);
		loginPage.scrollToLogoutButton();
		Utilities.ThreadSleepTime(3000);
		loginPage.clickOnLogoutButton();
		
	}
	
	//@FrameWorkPracticeReportAnnotation(module=Modules.RegisterationPage, category=CategoryType.SMOKE)
	@Test (priority = 4)
	public void registerWithAllMandatoryDetailsPropertyFileData() {
		//registerationPage.clickOnMyAccountMenu();
		//registerationPage.clickOnRegisterOption();
		registerationPage.enterFirstName(PropertyFileRead.readPropertiesFiles("FirstNameReg"));
		registerationPage.enterLastName(PropertyFileRead.readPropertiesFiles("LastNameReg"));
		registerationPage.enterEmail(Utilities.generateEmailWithTimeStamp());
		registerationPage.enterTelephone(PropertyFileRead.readPropertiesFiles("TelephoneReg"));
		registerationPage.enterPassword(PropertyFileRead.readPropertiesFiles("PassWordReg"));
		registerationPage.reEnterPassword(PropertyFileRead.readPropertiesFiles("PassWordReg"));
		registerationPage.subscribe();
		registerationPage.agreeTerms();
		registerationPage.clickContinueButton();
		String actualMessage = registerationPage.actualMessageSuccessfulRegi();
		//String actualMessage = Utilities.getDriver().findElement(By.xpath("//div[@id='content']/h1")).getText();
		// Hard coding of actualMessage is removed by registerationPage.actualMessageSuccessfulRegi();
		Assert.assertEquals(actualMessage, PropertyFileRead.readPropertiesFiles("expectedMessage"), "Message Displayed is Incorrect");
		//Utilities.ThreadSleepTime(2000);
		//loginPage.scrollToLogoutButton();
		//Utilities.ThreadSleepTime(3000);
		//loginPage.clickOnLogoutButton();

}
	//@FrameWorkPracticeReportAnnotation(module=Modules.RegisterationPage, category=CategoryType.SMOKE)
	@TestDataSheet(sheetName="RegisterationData")
	@Test(priority = 1, dataProvider="validCredentialSupplier",dataProviderClass=DataProviderUtils.class)
	public void verifyRegisterWithAllMandatoryDetailsExcelFileData(Map<String, String> testData) {
		String firstName = testData.get("FirstName");
		String lastName = testData.get("LastName");
		String emailAddress = Utilities.generateEmailWithTimeStamp();
		String telephone = testData.get("Telephone");
		String password = testData.get("Password");
		String confirmPassword = testData.get("ConfirmPassword");
		String expectedMessage = testData.get("ExpectedMessage");
		
		//registerationPage.clickOnMyAccountMenu();
		//registerationPage.clickOnRegisterOption();
		registerationPage.enterFirstName(firstName);
		registerationPage.enterLastName(lastName);
		registerationPage.enterEmail(emailAddress);
		registerationPage.enterTelephone(telephone);
		registerationPage.enterPassword(password);
		registerationPage.reEnterPassword(confirmPassword);
		registerationPage.subscribe();
		registerationPage.agreeTerms();
		registerationPage.clickContinueButton();
		String actualMessage = registerationPage.actualMessageSuccessfulRegi();
		//String actualMessage = Utilities.getDriver().findElement(By.xpath("//div[@id='content']/h1")).getText();
		// Hard coding for actualMessage removed by registerationPage.actualMessageSuccessfulRegi();
		Assert.assertEquals(actualMessage, expectedMessage, "Message Displayed is Incorrect");
		//Utilities.ThreadSleepTime(2000);
		//loginPage.scrollToLogoutButton();
		//Utilities.ThreadSleepTime(3000);
		//loginPage.clickOnLogoutButton();

}
	//@FrameWorkPracticeReportAnnotation(module=Modules.RegisterationPage, category=CategoryType.SMOKE)
	@TestDataSheet(sheetName="RegisterationData")
	@Test(priority = 2, dataProvider="validCredentialSupplier",dataProviderClass=DataProviderUtils.class)
	public void verifyRegisterWithExistingEmail (Map<String, String> testData) {
		String firstName = testData.get("FirstName");
		String lastName = testData.get("LastName");
		String emailAddress = testData.get("EmailAddress");
		String telephone = testData.get("Telephone");
		String password = testData.get("Password");
		String confirmPassword = testData.get("ConfirmPassword");
		String expectedMessage = testData.get("ExpectedMessage");
		//registerationPage.clickOnMyAccountMenu();
		//registerationPage.clickOnRegisterOption();
		registerationPage.enterFirstName(firstName);
		registerationPage.enterLastName(lastName);
		registerationPage.enterEmail(emailAddress);
		registerationPage.enterTelephone(telephone);
		registerationPage.enterPassword(password);
		registerationPage.reEnterPassword(confirmPassword);
		registerationPage.subscribe();
		registerationPage.agreeTerms();
		registerationPage.clickContinueButton();
		String actualMessage = registerationPage.actualErrorRegisterationWithExistingEmail();
		//String actualMessage = Utilities.getDriver().findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		// Hard coding for actualMessage removed by registerationPage.actualErrorRegisterationWithExistingEmail();
		Assert.assertEquals(actualMessage,expectedMessage,"Message Displayed is InCorrect");
		
}
	//@FrameWorkPracticeReportAnnotation(module=Modules.RegisterationPage, category=CategoryType.SMOKE)
	@TestDataSheet(sheetName="RegisterationData")
	@Test(priority = 3, dataProvider="validCredentialSupplier",dataProviderClass=DataProviderUtils.class)
	public void verifyErrorMessageForMandatoryField(Map<String, String> testData) {
		String firstName = testData.get("FirstName");
		String lastName = testData.get("LastName");
		String emailAddress = Utilities.generateEmailWithTimeStamp();
		//String telephone = testData.get("Telephone"); Intensionally Commented so that Telephone field will be kept as Blank
		String password = testData.get("Password");
		String confirmPassword = testData.get("ConfirmPassword");
		String expectedMessage = testData.get("ExpectedMessage");
		//registerationPage.clickOnMyAccountMenu();
		//registerationPage.clickOnRegisterOption();
		registerationPage.enterFirstName(firstName);
		registerationPage.enterLastName(lastName);
		registerationPage.enterEmail(emailAddress);
		//registerationPage.enterTelephone(telephone);
		registerationPage.enterPassword(password);
		registerationPage.reEnterPassword(confirmPassword);
		registerationPage.subscribe();
		registerationPage.agreeTerms();
		registerationPage.clickContinueButton();
		Utilities.ThreadSleepTime(5000);
		
		String actualMessage = registerationPage.actualErrorForMissingTelephone();
		//String actualMessage = Utilities.getDriver().findElement(By.xpath("//div[@class='text-danger']")).getText();
		//Hard coding for actualMessage is removed by registerationPage.actualErrorForMissingTelephone();
		Assert.assertEquals(actualMessage,expectedMessage,"Message Displayed is InCorrect"); 
		Utilities.ThreadSleepTime(5000);
	
}
	
}
	
