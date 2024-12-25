package com.frameworkpractice.pages;

import org.openqa.selenium.By;

import com.framework.enums.WaitLogic;
import com.framework.utils.Utilities;

public class LoginPage {

	private By myAccountMenu = By.xpath("//span[text()='My Account']");
	private By loginOption = By.linkText("Login");
	private By emailAddressField = By.id("input-email");
	private By passwordField = By.id("input-password");
	private By loginButton = By.xpath("//input[@value='Login']");
	private static By logoutButton = By.xpath("//*[@id=\"column-right\"]/div/a[13]");
	private static By messageUponLogin = By.linkText("Edit your account information");
	private static By actualErrorInvalidCred = By.xpath("//div[@class='alert alert-danger alert-dismissible']");
	private static By actualErrorLoginWithoutCred = By
			.xpath("//div[contains(@class,'alert alert-danger alert-dismissible')]");

	public void clickOnMyAccountMenu() {

		//Utilities.click(myAccountMenu);
		Utilities.click(myAccountMenu, WaitLogic.CLICKABLE, "My Account Menu");
	}

	public void clickOnLoginOption()  {

		Utilities.click(loginOption, WaitLogic.CLICKABLE, "Login Option");
	}

	public void enterValidEmailAddress(String emailAddress) {

		Utilities.sendKeys(emailAddressField, WaitLogic.NONE, "Email Address", emailAddress);
		//Utilities.sendKeys(emailAddressField, emailAddress);
	}

	public void enterValidPassword(String password) {

		//Utilities.sendKeys(passwordField, password);
		Utilities.sendKeys(passwordField, WaitLogic.NONE, "Email Address", password);
	}

	public void clickOnLoginButton() {

		Utilities.click(loginButton, WaitLogic.CLICKABLE, "Login Button");
		
	}

	public void scrollToLogoutButton() {

		Utilities.scrollToElement(logoutButton, "logoutButton");
	}

	public void clickOnLogoutButton() {
		Utilities.click(logoutButton, WaitLogic.CLICKABLE, "Login Option");

	}

	public String actualMessaegUponSuccessfulLogin() {
		return Utilities.actualMessageDisplayed(messageUponLogin);
	}

	public String actualErrorForInvalidLogin() {

		return Utilities.actualMessageDisplayed(actualErrorInvalidCred);
	}
	
	public String actualErrorMessageLoginWithoutCred() {
		
		return Utilities.actualMessageDisplayed(actualErrorLoginWithoutCred);
	}

}
