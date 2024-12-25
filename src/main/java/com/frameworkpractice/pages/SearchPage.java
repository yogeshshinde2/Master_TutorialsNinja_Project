package com.frameworkpractice.pages;

import org.openqa.selenium.By;

import com.framework.enums.WaitLogic;
import com.framework.utils.Utilities;

public class SearchPage {

	private By searchField = By.xpath("//input[@name='search']");
	private By searchButton = By.xpath("//div[@id='search']//button");
	private By productNameHP = By.linkText("HP LP3065");
	private By myAccountMenu = By.xpath("//span[text()='My Account']");
	private By logoutOption = By.linkText("Logout");
	private By actualMessageForInvalidProductSearch = By.xpath("//div[@id='content']/p[2]");

	public void enterSearchText(String productName) {

		//Utilities.sendKeys(searchField, productName);
		Utilities.sendKeys(searchField, WaitLogic.NONE, "Search Field", productName);

	}

	public void clickOnSearchButton() {
		//Utilities.click(searchButton);
		Utilities.click(searchButton, WaitLogic.CLICKABLE, "Search Button");
	}

	public void scrollToProductNameHP() {

		Utilities.scrollToElement(productNameHP, "productNameHP");
	}
	
	public void scrollTomyAccountMenu() {

		Utilities.scrollToElement(myAccountMenu, "myAccountMenu");
	}
	
	public void clickOnLogoutOption() {
		
		//Utilities.click(logoutOption);
		Utilities.click(logoutOption, WaitLogic.CLICKABLE, "Logout Option");
	}
	
	public boolean displayStatusOfProductHP() {
		
		return Utilities.isElementDisplayed(productNameHP);
	}
		
	public String actualMessageForInvalidProductSearch() {	
		
		return Utilities.actualMessageDisplayed(actualMessageForInvalidProductSearch);
	}

}
