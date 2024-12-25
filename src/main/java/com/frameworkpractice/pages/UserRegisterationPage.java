package com.frameworkpractice.pages;

import org.openqa.selenium.By;

import com.framework.enums.WaitLogic;
import com.framework.utils.Utilities;

public class UserRegisterationPage {

	private By myAccountMenu = By.xpath("//span[text()='My Account']");
	private By registerOption = By.linkText("Register");
	private By firstNameRegField = By.id("input-firstname");
	private By lastNameRegField = By.id("input-lastname");
	private By emailAddressRegField = By.id("input-email");
	private By telephoneRegField = By.id("input-telephone");
	private By passwordRegField = By.id("input-password");
	private By confirmPasswordRegField = By.id("input-confirm");
	private By subScribeField = By.xpath("//input[(@value='0')]");
	private By agreeCheckbox = By.xpath("//input[(@name='agree')]");
	private By continueButton = By.xpath("//input[@value='Continue']");
	private By actualMessageSuccessfulRegisteration = By.xpath("//div[@id='content']/h1");
	private By actualErrorRegisterationWithExistingEmail = By
			.xpath("//div[@class='alert alert-danger alert-dismissible']");
	private By actualErrorForMissingTelephone = By.xpath("//div[@class='text-danger']");

	public void clickOnMyAccountMenu() {

		//Utilities.click(myAccountMenu);
		Utilities.click(myAccountMenu, WaitLogic.CLICKABLE, "Account Menu");
	}

	public void clickOnRegisterOption() {

		//Utilities.click(registerOption);
		Utilities.click(registerOption, WaitLogic.CLICKABLE, "Registeration option");
	}

	public void enterFirstName(String firstName) {

		//Utilities.sendKeys(firstNameRegField, firstName);
		Utilities.sendKeys(firstNameRegField, WaitLogic.NONE, "First Name Field", firstName);
	}

	public void enterLastName(String lastName) {

		Utilities.sendKeys(lastNameRegField,WaitLogic.NONE, "Last Name Feild",lastName);
		
	}

	public void enterEmail(String emailReg) {

		Utilities.sendKeys(emailAddressRegField,WaitLogic.NONE,"Email Adress Field", emailReg);
	}

	public void enterTelephone(String telephone) {

		Utilities.sendKeys(telephoneRegField,WaitLogic.NONE, "Telephone Field", telephone);
	}

	public void enterPassword(String passwordReg) {

		Utilities.sendKeys(passwordRegField,WaitLogic.NONE,"Password Field", passwordReg);
	}

	public void reEnterPassword(String passwordReg) {

		Utilities.sendKeys(confirmPasswordRegField,WaitLogic.NONE,"Password Re-enter" ,passwordReg);
	}

	public void subscribe() {

		Utilities.click(subScribeField,WaitLogic.CLICKABLE,"Subscribe Field");
	}

	public void agreeTerms() {

		Utilities.click(agreeCheckbox,WaitLogic.CLICKABLE,"Agree Terms");
	}

	public void clickContinueButton() {

		Utilities.click(continueButton,WaitLogic.CLICKABLE,"Continue Button");
	}

	public String actualMessageSuccessfulRegi() {

		return Utilities.actualMessageDisplayed(actualMessageSuccessfulRegisteration);
	}

	public String actualErrorRegisterationWithExistingEmail() {

		return Utilities.actualMessageDisplayed(actualErrorRegisterationWithExistingEmail);
	}

	public String actualErrorForMissingTelephone() {

		return Utilities.actualMessageDisplayed(actualErrorForMissingTelephone);
	}

}
